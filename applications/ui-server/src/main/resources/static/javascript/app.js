$(document).ready(function(){

    function toJSONString( form ) {
        var obj = {};
        var elements = form.querySelectorAll( "input, input" );
        for( var i = 0; i < elements.length; ++i ) {
            var element = elements[i];
            var name = element.name;
            var value = element.value;

            if( name ) {
                obj[ name ] = value;
            }
        }

        return obj;
    }

    $("#login-form").submit(function(event){
    // curl 'http://localhost:8999/oauth/token' -i -u 'tracker-client:supersecret' -X POST -H 'Accept: application/json' -H 'Content-Type: application/x-www-form-urlencoded' -d 'grant_type=client_credentials&response_type=token&token_format=opaque'
        event.preventDefault();
        var formData = toJSONString(this);
        var username = formData.username;
        var password = formData.password;
        console.log('login with ', username, password);

        $.ajax({
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/x-www-form-urlencoded',
              'Authorization': 'Basic ' + btoa(username + ":" + password)
          },
          method: 'POST',
          url: "http://localhost:8999/oauth/token",
          data: {
            'grant_type': 'client_credentials',
            'response_type': 'token',
            'token_format': 'opaque'
          }
        }).done(function(data) {
          sessionStorage.setItem("api-token", data.access_token);
          window.location.href = './ui/index.html'
        }).fail(function(data) {
          console.log(data);
        });
    });

    $('#toke-load').click(function() {
        console.log(sessionStorage.getItem("api-token"));
    });
});