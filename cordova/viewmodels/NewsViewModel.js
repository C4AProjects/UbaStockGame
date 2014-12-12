var newsvm = new function() {
   var that = this;

    // ----- public fields
    // data stores
  this.variable="Hello";
    this.foo = ko.observable();
    // ----- public functions
    this.loadDetails=function(){
  $("body").pagecontainer("change", "newsdetails.html", { transition: 'slide' });
};
  };