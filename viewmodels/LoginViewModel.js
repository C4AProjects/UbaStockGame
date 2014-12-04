
  function LoginViewModel() {
    /// <summary>
    /// The view model that manages the view model back-stack
    /// </summary>
    var that = this;

    // ----- public fields
    // data stores
    this.username = ko.observable();
    this.password = ko.observable();
    this.errormessage=ko.observable();
    
    // ----- public functions


    
            //console.log(loginForm);
       
    this.validateLogin=function(){
      var loginForm = '{ "email": "'+that.username()+'","password": "'+that.password()+'"}';
      var url = apiUrl+"users/login";
            $.ajax({
                url: url,
                data: loginForm,
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
            }).then(function (result) {
                //successCallback(result);
                if(result.status!="error"){
                  userInfo.email=result.email;
                  userInfo.username=result.fname+" "+result.lname;
                  userInfo.apiKey=result.apiKey;
                  $("body").pagecontainer("change", "../views/portfolio.html", { transition: 'slide' });
                  
                }
                else {
                  that.errormessage(result.message)
                  $( "#dlg-invalid-credentials" ).popup( "open" );
                }
            },
            function (data) {
                //failureCallback(data);
                that.errormessage(data);
                $( "#dlg-invalid-server" ).popup( "open" );
            });
      /*if(that.username()==="admin" && that.password()==="admin"){
        
      //return true;
      }
        
    else {
       
       //return false;
    }*/
    };

  };
