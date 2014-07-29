package standardgame.choice;

import model.choice.Choice;
import model.choice.ChoiceException;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A lock for choices that keeps a list of them that are waiting to be chosen.
 * @author Steven Weston
 */
public class ChoiceLock {

	private final ReentrantLock lock = new ReentrantLock();

	private final Set<Choice> awaitingLock = Collections.newSetFromMap(new ConcurrentHashMap<>());

	private Choice currentlyWaiting = null;

	private void lock(Choice choice) {
		awaitingLock.add(choice);
		lock.lock();
		assert currentlyWaiting == null;
		currentlyWaiting = choice;
	}

	private void unlock(Choice choice) {
		assert currentlyWaiting == choice;
		currentlyWaiting = null;
		lock.unlock();
		awaitingLock.remove(choice);
	}

	public ChoiceCondition newCondition(Choice choice) {
		Condition condition = lock.newCondition();
		return new ChoiceCondition(condition, choice);
	}

	public void waitUntilConditionMet(ChoiceCondition condition) throws ChoiceException {

		if (condition.conditionMet()) {
			return;
		}
		this.lock(condition.getChoice());
		try {
			condition.waitUntilConditionMet();
		} catch (InterruptedException e) {
			throw new ChoiceException(e);
		} finally {
			this.unlock(condition.getChoice());
		}
	}

	public Choice getCurrentlyWaiting() {
		return currentlyWaiting;
	}

	public Set<Choice> getChoicesAwaitingLock() {
		return awaitingLock;
	}

	public static class ChoiceCondition {

		private final Condition condition;
		private final Choice choice;

		public ChoiceCondition(Condition condition, Choice choice) {
			this.condition = condition;
			this.choice = choice;
		}

		private void waitUntilConditionMet() throws InterruptedException {
			while (!conditionMet()) {
				condition.await();
			}
		}

		public void signal() {
			condition.signal();
		}

		private Choice getChoice() {
			return choice;
		}

		public boolean conditionMet() {
			return choice.hasChosen();
		}
	}
}
