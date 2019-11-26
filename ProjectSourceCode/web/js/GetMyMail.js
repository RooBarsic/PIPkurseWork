jQuery(document).ready(function(){

    document.getElementById("showURL").onclick = function (ev) {
        var newWin = window.open("https://oauth.vk.com/authorize?client_id=7101802&display=page&redirect_uri=http://localhost:8080/JavaEEtest_war_exploded/project/OAuthJSP.jsp&scope=friends,email,pages&response_type=token&v=5.52", "JSSite",
        "width=420,height=230,resizable=yes,scrollbars=yes,status=yes");
        //var newWin = window.open("https://oauth.vk.com/authorize?client_id=7101802&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,email,pages&response_type=token&v=5.52", "JSSite",
        //    "width=420,height=230,resizable=yes,scrollbars=yes,status=yes");
    };

    document.getElementById("getMyMail").onclick = function (ev) {
         $.ajax({
             url : "http://localhost:8080/JavaEEtest_war_exploded/getUserMail",
             method : "POST",
             data : {

             },
             success : function (response) {
                 alert("Yur mail = " + response.toString() );
             },
             error : function (response) {
                 alert("Some error =( " + response.toString());
             }
         });
    };


});

//https://oauth.vk.com/blank.html#access_token=6d5e99f06f6e6f9e2293c2ede19cb77f157817b623a4be4d643e1f691b84a02ceca59e213d0bad322dc91&expires_in=86400&user_id=415938349&email=Karimov.farrux@list.ru
//https://oauth.vk.com/blank.html#access_token=e3d7ea4f988e11c963040fb9f4ddeff9bb71ed294a6fa54d446580d42fa5ba7c5bc88adac79e2163c5758&expires_in=86400&user_id=415938349&email=Karimov.farrux@list.ru
//https://oauth.vk.com/blank.html#access_token=edf433e495c12d9e0a42a11638bb1af0b2945c480291d2736cf99c759c1fc9297b9c92d7d63f0417c4b18&expires_in=86400&user_id=415938349&email=Karimov.farrux@list.ru
//https://oauth.vk.com/blank.html#access_token=0b5a2bdb567770a74c1d4f48be240ebebde2efcbb3bdf0829e4409eede9e14d1b5e3a44c6921ac0c079f0&expires_in=86400&user_id=415938349&email=Karimov.farrux@list.ru

//localhost:8080/JavaEEtest_war_exploded/project/OAuthJSP.jsp#access_token=0bd8561adcb647ee315aef378180452c0ff3e2ee443b35d6cb541206fdb5ab4592664e26bea70b8950134&expires_in=86400&user_id=415938349&email=Karimov.farrux@list.ru
//localhost:8080/JavaEEtest_war_exploded/project/OAuthJSP.jsp#access_token=2cdde9446adf657507f00798f9b5c09e313673e3e5515060ea320ee84cfbdce6af066467ca03b1cc98e54&expires_in=86400&user_id=415938349&email=Karimov.farrux@list.ru
//localhost:8080/JavaEEtest_war_exploded/project/OAuthJSP.jsp#access_token=d12987f45bb06840da4ee609c3d7bdc268a4799b7a71018e89d7bb45cbb8b734d13064118e0ad851f00c7&expires_in=86400&user_id=415938349&email=Karimov.farrux@list.ru





