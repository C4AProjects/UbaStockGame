var triviavm = new function() {
 var that = this;

    // ----- public fields
    // data stores
    this.variable="Hello";
    this.foo = ko.observable();
    // ----- public functions
    this.loadQuestions=function(){
    window.setTimeout( that.loadDetails, 2000 ); // 5 seconds
  };

  this.loadDetails=function(){
    $("body").pagecontainer("change", "../views/triviadetails.html", { transition: 'slide' });
  };

};