package model.alignment;

import java.util.List;

/**
 * @author Steven Weston
 */
public interface AlignmentResolver<A extends Alignment> {

	A resolveAlignment(List<? extends Aligned<A>> alignedList);

	A resolveVisibleAlignment(List<? extends Aligned<A>> alignedList);
}
