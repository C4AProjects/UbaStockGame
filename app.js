//var apiUrl="http://127.0.0.1/Api2/v1/";
var apiUrl="http://127.0.0.1/slimmongo/api/v1/";
var userInfo=ko.observable();

var loginvm = new LoginViewModel();

var EnableToolbar=function(){
    $( "[data-role='footer']" ).toolbar();
}

$(document).on("pagecreate","section[data-role=page]", function () {

  if ($(this).attr("id") == "triviadetails") {
        // Only when on #tradedetails
        if(!ko.dataFor(document.getElementById("triviadetails")))
        ko.applyBindings(triviadetailsvm,document.getElementById("triviadetails"));
    }

    if ($(this).attr("id") == "trivia") {
        // Only when on #trivia
        if(!ko.dataFor(document.getElementById("trivia")))
        ko.applyBindings(triviavm,document.getElementById("trivia"));
    }

    if ($(this).attr("id") == "triviacomplete") {
        // Only when on #trivia
        if(!ko.dataFor(document.getElementById("triviacomplete")))
        ko.applyBindings(triviacompletevm,document.getElementById("triviacomplete"));
    }

    if ($(this).attr("id") == "trade") {
        // Only when on #trade
        if(!ko.dataFor(document.getElementById("trade")))
        ko.applyBindings(tradevm,document.getElementById("trade"));
    }
    if ($(this).attr("id") == "tradedetails") {
        // Only when on #trade
        if(!ko.dataFor(document.getElementById("tradedetails")))
        ko.applyBindings(tradedetailsvm,document.getElementById("tradedetails"));
    }
    if ($(this).attr("id") == "news") {
        // Only when on #news
        if(!ko.dataFor(document.getElementById("news")))
        ko.applyBindings(newsvm,document.getElementById("news"));
    }
});
$(document).on("pageshow","section[data-role=page]", function () {

if ($(this).attr("id") == "triviadetails") {
    triviadetailsvm.submitted(false);
    triviadetailsvm.score(0);
    triviadetailsvm.currentIndex(0);
}
  if ($(this).attr("id") == "tradedetails") {
        // Only when on #tradedetails
        console.log("tradedetails shown");
        var plot1 = $.jqplot ('chart1', [[2,1,3,2,4,5,9,7,10]], {
      axes:{
        xaxis:{
          label:'Date',
          showTicks: false
        },
        yaxis:{
          label:'Points',
          showTicks: false
        }
      }
  });
    }

    if ($(this).attr("id") == "trivia") {
        // Only when on #trivia        
        triviavm.loadQuestions();
    }
    if ($(this).attr("id") == "triviacomplete") {

var s1 = [['a',5-triviacompletevm.score()], ['b',triviacompletevm.score()]];
   
  var plot3 = $.jqplot('chart3', [s1], {
    seriesDefaults: {
      // make this a donut chart.
      renderer:$.jqplot.DonutRenderer,
      rendererOptions:{
        // Donut's can be cut into slices like pies.
        sliceMargin: 3,
        // Pies and donuts can start at any arbitrary angle.
        startAngle: -90,
        showDataLabels: false,
        // By default, data labels show the percentage of the donut/pie.
        // You can show the data 'value' or data 'label' instead.
        dataLabels: 'value'
      }
    },
    seriesColors: ["#fff", "#01c93f"],
  });

mytitle = $('<div class="my-jqplot-title" style="position:absolute;text-align:center;padding-top: 35%;width:100%"><h1 class="twhite">'+triviacompletevm.score()+' of '+'5'+'</h1></div>').insertAfter('.jqplot-grid-canvas');
    }
    
});
$(document).on("pagecontainerload",function(event,data){
  //alert("pagecontainerload event fired!\nURL: " + data.url);
  console.log("page loaded"+ data.url);
  if(data.url=="views/tradedetails.html") {
    console.log("page tradedetails loaded");    
}
});

$(document).ready(function () {
    $.jqplot.config.enablePlugins = true;
    
    $("#mypanel").panel();
    $("[data-role=panel]").panel().enhanceWithin();
    var p = document.getElementById("exit");
    p.onclick = ExitApp;

    var l = document.getElementById("loginBtn");
    l.onclick = EnableToolbar;
  // bind each view model to a jQueryMobile page
  ko.applyBindings(loginvm, document.getElementById("login"));
});

var ExitApp=function(){
  navigator.app.exitApp();
}

function intToString (value) {
    var suffixes = ["", "k", "m", "b","t"];
    var suffixNum = Math.floor((""+value).length/3);
    var shortValue = parseFloat((suffixNum != 0 ? (value / Math.pow(1000,suffixNum)) : value).toPrecision(2));
    if (shortValue % 1 != 0)  shortNum = shortValue.toFixed(1);
    return shortValue+suffixes[suffixNum];
}

function abbreviateNumber(value) {
    var newValue = value;
    if (value >= 1000) {
        var suffixes = ["", "k", "m", "b","t"];
        var suffixNum = Math.floor( (""+value).length/3 );
        var shortValue = '';
        for (var precision = 2; precision >= 1; precision--) {
            shortValue = parseFloat( (suffixNum != 0 ? (value / Math.pow(1000,suffixNum) ) : value).toPrecision(precision));
            var dotLessShortValue = (shortValue + '').replace(/[^a-zA-Z 0-9]+/g,'');
            if (dotLessShortValue.length <= 2) { break; }
        }
        if (shortValue % 1 != 0)  shortNum = shortValue.toFixed(1);
        newValue = shortValue+suffixes[suffixNum];
    }
    return newValue;
}