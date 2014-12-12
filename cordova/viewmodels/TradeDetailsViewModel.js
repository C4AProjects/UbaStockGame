

var tradedetailsvm = new function() {
	var that = this;

    // ----- public fields
    // data stores
	this.showing = ko.observable(false);
this.buttonText=ko.observable("Buy");
    // ----- public functions
    this.show = function(btnText) {
that.buttonText(btnText);
		return (function(){ that.showing(!that.showing()); });
	}
    
};