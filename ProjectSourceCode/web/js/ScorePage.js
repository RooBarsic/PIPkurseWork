jQuery(document).ready(function(){

    document.getElementById("findByCountry").addEventListener("click", function (ev) {
        var login = document.getElementById("searchingLogin").value;
        login = validateLogin(login);
        var country = document.getElementById("searchingCountry").value;
        country = validateCountry(country);
        var score = document.getElementById("searchingScore").value;
        score = validateScore(score);
        window.location = "./scores.jsp?login=" + login + "&country=" + country + "&score=" + score;
         $.ajax({
             url: "./scores.jsp",
             method: "GET",
             data: {
                 login : login,
                 country : country,
                 score : score
             },
             success: function (response) {
                // window.location = window.location;
             }
         });
    });
});

function validateLogin(str) {
    var result = "";
    for(var i = 0; i < str.length; i++){
        if((('a' <= str[i]) && (str[i] <= 'z')) ||
            (('A' <= str[i]) && (str[i] <= 'Z')) ||
            (('0' <= str[i]) && (str[i] <= '9')) ||
            (str[i] == '_')){
            result += str[i];
        }
    }
    return result;
}

function validateCountry(str) {
    var result = "";
    for(var i = 0; i < str.length; i++){
        if((('а' <= str[i]) && (str[i] <= 'я')) ||
            (('А' <= str[i]) && (str[i] <= 'Я')) ||
            (('a' <= str[i]) && (str[i] <= 'z')) ||
            (('A' <= str[i]) && (str[i] <= 'Z')) ||
            (('0' <= str[i]) && (str[i] <= '9'))){
            result += str[i];
        }
    }
    return result;
}

function validateScore(str) {
    var result = "";
    for(var i = 0; i < str.length; i++){
        if(('0' <= str[i]) && (str[i] <= '9')){
            result += str[i];
        }
    }
    return result;
}