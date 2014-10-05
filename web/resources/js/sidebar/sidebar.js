/**
 * @constructor
 * @implements Control
 */
function Sidebar() {
	/**
	 * @type {Array.<SidebarSection>}
	 * @private
	 */
	this._sections = [];
}

Sidebar.prototype.create = function() {
	var control = $('<div/>').addClass('sidebar control');
	this._control = control;

	$.each(
		this._sections,
		/**
		 * @this {SidebarSection}
		 */
		function() {
			control.append(this.create());
		}
	);

	return control;
};


/**
 * Add a section to the sidebar and visually add it if the sidebar has been drawn.
 * @param {SidebarSection} sidebarSection
 */
Sidebar.prototype.addSection = function(sidebarSection) {
	this._sections.push(sidebarSection);
	if (this._control) {
		this._control.append(sidebarSection.create());
	}
};

//----------------------------------------------------------------------------------------------------------------------

/**
 * @interface
 * @extends Control
 */
function SidebarSection() {
	Control.call(this);
}

SidebarSection.prototype = Object.create(Control.prototype);
SidebarSection.prototype.constructor = SidebarSection;
