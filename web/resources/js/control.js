/**
 * @interface
 */
function Control() {}

/**
 * Create an element for the control.
 * @return {HTMLElement}
 */
Control.prototype.create = function() {};

/**
 * @interface
 * @extends {Control}
 */
function EditableControl() {
	Control.call(this);
}

EditableControl.prototype = Object.create(Control.prototype);
EditableControl.prototype.constructor = EditableControl;

/**
 * Finish editing the control, submit data and revert the control to the default state.
 */
EditableControl.prototype.finish = function() {};

/**
 * Cancel editing the control and revert the control to the default state.
 */
EditableControl.prototype.cancel = function() {};