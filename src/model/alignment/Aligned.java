package model.alignment;

/**
 * @author Steven Weston
 */
public interface Aligned<A extends Alignment> {

	A getAlignment();

	A getVisibleAlignment();
}
