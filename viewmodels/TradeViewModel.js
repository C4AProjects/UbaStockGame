
var tradevm = new function() {
   var that = this;

    // ----- public fields
    // data stores
  this.variable="Hello";
    this.foo = ko.observable();
    // ----- public functions
    this.loadDetails=function(){
  $("body").pagecontainer("change", "views/tradedetails.html", { transition: 'slide' });
};
  };