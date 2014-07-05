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
 * Finish editing the control, submit data and revert the control to the default state.
 */
Control.prototype.finish = function() {};

/**
 * Cancel editing the control and revert the control to the default state.
 */
Control.prototype.cancel = function() {};