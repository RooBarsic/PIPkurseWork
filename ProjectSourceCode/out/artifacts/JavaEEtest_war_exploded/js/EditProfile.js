var app;

jQuery(document).ready(function(){

    app = new Vue({
        el : '#app',
        data : {
            loginMessage : '',
            passwordMessage : '',
            mailMessage : '',
            countryMessage : '',
            mainMessage : ''
        }
    });

    document.getElementById("uLogin").addEventListener("keyup", function (ev) {
        var userLogin = document.getElementById("uLogin").value;
        var loginChekingResult = "?";
        $.ajax({
            url : "../isUserWith/login",
            method : "GET",
            data : {
                ulogin : userLogin
            },
            success: function (response) {
                if(response.toString() == "1"){
                    document.getElementById("uLogin").style.color = "red";
                    document.getElementById("uLogin").style.fontSize = "11px";
                    document.getElementById("uLogin").style.border = "1px solid red";
                    app.loginMessage = "Логин занят";
                    app.mainMessage = app.loginMessage;
                    loginChekingResult = "true";
                }
                else if(response.toString() == "2"){
                    document.getElementById("uLogin").style.color = "red";
                    document.getElementById("uLogin").style.fontSize = "11px";
                    document.getElementById("uLogin").style.border = "1px solid red";
                    app.loginMessage = "Неверный формат Логина. Используйте латинские буквы, цифры и символ _";
                    app.mainMessage = app.loginMessage;
                    loginChekingResult = "true";
                }
                else {
                    loginChekingResult = "false";
                    document.getElementById("uLogin").style.color = "green";
                    document.getElementById("uLogin").style.fontSize = "11px";
                    document.getElementById("uLogin").style.border = "1px solid green";

                    app.loginMessage = "";
                    app.mainMessage = app.loginMessage;
                }
            },
            error : function (response) {
                alert("Ошибка с сетью!");
            }
        });
    } ,false);

    document.getElementById("uName").addEventListener("keyup", function (ev) {

        document.getElementById("uName").style.color = "green";
        document.getElementById("uName").style.fontSize = "11px";
        document.getElementById("uName").style.border = "1px solid green";
    } ,false);

    document.getElementById("uSurName").addEventListener("keyup", function (ev) {
        document.getElementById("uSurName").style.color = "green";
        document.getElementById("uSurName").style.fontSize = "11px";
        document.getElementById("uSurName").style.border = "1px solid green";
    } ,false);

    document.getElementById("uEmail").addEventListener("keyup", function (ev) {
        document.getElementById("uEmail").style.color = "green";
        document.getElementById("uEmail").style.fontSize = "11px";
        document.getElementById("uEmail").style.border = "1px solid green";


        var userEmail = document.getElementById("uEmail").value;
        var loginChekingResult = "?";
        $.ajax({
            url : "../isUserWith/email",
            method : "GET",
            data : {
                uemail : userEmail
            },
            success: function (response) {
                if(response.toString() == "1"){
                    document.getElementById("uEmail").style.color = "red";
                    document.getElementById("uEmail").style.fontSize = "11px";
                    document.getElementById("uEmail").style.border = "1px solid red";
                    app.mailMessage = "Эта почта уже зарегистрирована";
                    app.mainMessage = app.mailMessage;
                    loginChekingResult = "true";
                }
                else if(response.toString() == "2"){
                    document.getElementById("uEmail").style.color = "red";
                    document.getElementById("uEmail").style.fontSize = "11px";
                    document.getElementById("uEmail").style.border = "1px solid red";
                    app.mailMessage = "Неверный формат Почты. Используйте латинские буквы, цифры и символы . - _ @";
                    app.mainMessage = app.mailMessage;
                    loginChekingResult = "true";
                }
                else {
                    loginChekingResult = "false";
                    document.getElementById("uEmail").style.color = "green";
                    document.getElementById("uEmail").style.fontSize = "11px";
                    document.getElementById("uEmail").style.border = "1px solid green";

                    app.mailMessage = "";
                    app.mainMessage = app.mailMessage;
                }
            },
            error : function (response) {
                alert("Ошибка с сетью!");
            }
        });
    } ,false);

    document.getElementById("uCountry").addEventListener("keyup", function (ev) {
        if (isValidateCountry(document.getElementById("uCountry").value) == 1) {
            document.getElementById("uCountry").style.color = "green";
            document.getElementById("uCountry").style.fontSize = "11px";
            document.getElementById("uCountry").style.border = "1px solid green";
            app.countryMessage = "";
            app.mainMessage = app.countryMessage;
        }
        else {
            document.getElementById("uCountry").style.color = "red";
            document.getElementById("uCountry").style.fontSize = "11px";
            document.getElementById("uCountry").style.border = "1px solid red";
            app.countryMessage = "Неверный формат страны. Используйте латинские/русские буквы";
            app.mainMessage = app.countryMessage;
        }
    } ,false);

    document.getElementById("save").addEventListener("click", function (ev) {
        if((app.loginMessage == "") && (app.countryMessage == "") && (app.mailMessage == "")){
            $.ajax({
                url : "../editData",
                method : "POST",
                data : {
                    uLogin : document.getElementById("uLogin").value,
                    uName : document.getElementById("uName").value,
                    uSurName : document.getElementById("uSurName").value,
                    uEmail : document.getElementById("uEmail").value,
                    uCountry : document.getElementById("uCountry").value
                },
                success: function (response) {
                    location.href="http://localhost:8080/JavaEEtest_war_exploded/project/profile.jsp";
                },
                error : function (response) {
                    alert("Ошибка с сетью!");
                }
            });        }
    } ,false);
});

function passwordHashing(password) {
    return password + "_bubu";
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

function isValidateCountry(str) {
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
    if(str.length == result.length) return 1;
    return 0;
}