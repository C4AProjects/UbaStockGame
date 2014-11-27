var triviadetailsvm = new function() {
   var that = this;

    // ----- public fields
    // data stores
    this.quiz = [
    {
        "question"      :   "The science that deals with the production, allocation, and use of goods and services, it is important to study how resources can best be distributed to meet the needs of the greatest number of people is",
        "choices"       :   [
        "Biology",
        "Psychology",
        "Economics"
        ],
        "correct"       :   "Economics"
    },
    {
        "question"      :   "The study of entire systems of economics is",
        "choices"       :   [
        "Macroeconomics",
        "Microeconomics",
        "Finance"
        ],
        "correct"       :   "Macroeconomics"
    },
    {
        "question"      :   "The study of how the systems affects one business or the study of parts of the economic system is",
        "choices"       :   [
        "Macroeconomics",
        "Microeconomics",
        "Finance"
        ],
        "correct"       :   "Microeconomics"
    },
    {
        "question"      :   "The economic system of medieval times characterized by a strict class structure and serfs working the land in payment for protection from nobles is",
        "choices"       :   [
        "Capitalism",
        "Socialism",
        "Feudalism",
        "Mercantilism"
        ],
        "correct"       :   "Feudalism"
    },
    {
        "question"      :   "The economic system of the major trading nations during the 16th, 17th, and 18th cent., based on the idea that the power and wealth of nations was best served by trade and marked by increases in manufacturing and commerce is",
        "choices"       :   [
        "Capitalism",
        "Socialism",
        "Feudalism",
        "Mercantilism"
        ],
        "correct"       :   "Mercantilism"
    },

    ];

    this.variable="Hello";
    this.foo = ko.observable();    
    this.questionTitle=ko.observable("Economics");
    //this.questions=ko.mapping.fromJS(that.quiz);
    this.questions=ko.viewmodel.fromModel(that.quiz);
    this.currentIndex=ko.observable(0);
    this.currentQuestion=ko.observable(that.questions()[that.currentIndex()]);
    this.score=ko.observable(0);
    this.submitted=ko.observable(false);
    // ----- public functions
//go to complete page, complete page must be aware of the score
this.loadCompletePage=function(){
    //send score to viewmodel
    triviacompletevm.score(that.score());
    $("body").pagecontainer("change", "../views/triviacomplete.html", { transition: 'slide' });
}
//Initialize display element and load next question
    this.nextQuestion=function(){
        console.log(that.currentIndex()+1);
        if(that.currentIndex()+1<5){
            that.currentIndex(that.currentIndex()+1);
            that.currentQuestion(that.questions()[that.currentIndex()]);
            that.submitted(false);
            var items=$(".achoice");
            $.each(items,function (index,item) {                
             $(item).removeClass('tgreen');
             $(item).removeClass('tred');
         });  
        }
        else
            that.loadCompletePage();


    }

        /*
         * After a selection is submitted, checks if its the right answer         
         */
         this.isGoodAnswer=function(choice){            

            if(that.currentQuestion().correct() === choice) { 
                if(!that.submitted())
                    that.score(that.score()+1);
                console.log(that.score());           
                return true;
            }
            else {
                console.log(that.score()); 
                return false;
            }
        }
        this.processQuestion=function(choice){
            //$('#choices').children().eq(1).text()
            console.log(choice);
            console.log(that.currentQuestion().correct());

            var items=$(".achoice");
            $.each(items,function (index,item) {
                if($(item).text()==that.currentQuestion().correct())
                 $(item).addClass('tgreen');
         }); 

            var items=$(".achoice")
            $.each(items,function (index,item) {                
             if($(item).text()==choice && !that.isGoodAnswer(choice))
                 $(item).addClass('tred');
         }); 
            that.submitted(true);
        }

        /**
         * Quiz ends, display summary page.
         */
         function endQuiz(){
         }


     };