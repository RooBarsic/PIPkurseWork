var app;

jQuery(document).ready(function(){

    app = new Vue({
        el : '#app',
        data : {
            loginMessage : '',
            passwordMessage : '',
            mailMessage : ''
        }
    });

    document.getElementById("sendUser").addEventListener("click", function (ev) {
        var login = document.getElementById("ulogin").value;
        var email = document.getElementById("uemail").value;
        var password = document.getElementById("upassword").value;
        if((app.loginMessage == "") && (app.mailMessage == "") && (login != "") && (email != "") && (password != "")){
            $.ajax({
                url: "../doRegistration",
                method: "POST",
                data: {
                    ulogin: login,
                    uemail: email,
                    upassword: password
                },
                success: function (response) {
                    //alert(" result = " + response.toString())
                    if(response.toString() == "1") {
                        window.location = "http://localhost:8080/JavaEEtest_war_exploded/project/enter.jsp";
                    }
                    else {
                        window.location = window.location;
                    }
                },
                error: function (resp) {
                    alert("Ошибка с сетью!");
                    //alert(" error resp = " + resp.toString());
                    window.location = window.location;
                }
            });
        }
        else {
            if (login == "") {
                app.loginMessage = "Пустой логин!";
            }
            if (email == "") {
                app.mailMessage = "надо указать email!";
            }
            if (password == "") {
                app.passwordMessage = "Пустой пароль!";
            }
        }

    });

    document.getElementById("ulogin").addEventListener("keyup", function (ev) {
        var userLogin = document.getElementById("ulogin").value;
        var loginChekingResult = "?";
        $.ajax({
            url : "../isUserWith/login",
            method : "GET",
            data : {
                ulogin : userLogin
            },
            success: function (response) {
                if((response.toString() == "1") || (response.toString() == "2")){
                    document.getElementById("ulogin").style.color = "red";
                    document.getElementById("ulogin").style.fontSize = "11px";
                    document.getElementById("ulogin").style.border = "1px solid red";
                    if(response.toString() == "1") app.loginMessage = "занято";
                    else if(response.toString() == "2") app.loginMessage = "Неверный формат. Используйте латинские буквы, цифры и символ _";
                    loginChekingResult = "true";
                }
                else {
                    loginChekingResult = "false";
                    app.loginMessage = "";
                    document.getElementById("ulogin").style.color = "green";
                    document.getElementById("ulogin").style.fontSize = "11px";
                    document.getElementById("ulogin").style.border = "1px solid green";
                }
            },
            error : function (response) {
                alert("Ошибка с сетью!");
            }
        });
    } ,false);

    document.getElementById("upassword").addEventListener("keyup", function (ev) {
        var userPassword = document.getElementById("upassword").value;
        if(userPassword.length > 0){
            app.passwordMessage = "";
        }
        else {
            app.passwordMessage = "Пустой пароль!";
        }
    } ,false);

    document.getElementById("uemail").addEventListener("keyup", function (ev) {
        var userEmail = document.getElementById("uemail").value;
        var emailChekingResult = "?";
        $.ajax({
            url : "../isUserWith/email",
            method : "GET",
            data : {
                uemail : userEmail
            },
            success: function (response) {
                if((response.toString() == "1") || (response.toString() == "2")){
                    document.getElementById("uemail").style.color = "red";
                    document.getElementById("uemail").style.fontSize = "11px";
                    document.getElementById("uemail").style.border = "1px solid red";
                    if(response.toString() == "1") app.mailMessage = "мэйл уже зарегистрирован";
                    else if(response.toString() == "2") app.mailMessage = "Неверный формат. Используйте латинские буквы, цифры и символ @";
                    emailChekingResult = "true";
                }
                else {
                    app.mailMessage = "";
                    emailChekingResult = "false";
                    document.getElementById("uemail").style.color = "green";
                    document.getElementById("uemail").style.fontSize = "11px";
                    document.getElementById("uemail").style.border = "1px solid green";
                }
            },
            error : function (response) {
                alert("Ошибка с сетью!");
            }
        });
    } ,false);

/*    document.getElementById("enterWithVk").addEventListener("click",   function (ev) {
        var newWin = window.open("https://oauth.vk.com/authorize?client_id=7101802&display=page&redirect_uri=http://localhost:8080/JavaEEtest_war_exploded/project/OAuthJSP.jsp&scope=friends,email,pages&response_type=token&v=5.52", "JSSite",
            "width=420,height=230,resizable=yes,scrollbars=yes,status=yes");
        while((get_cookie("userId") != null) && (get_cookie("userId") != "")){

        }
    }, false);*/

});

function passwordHashing(password) {
   return password + "_bubu";
}