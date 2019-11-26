jQuery(document).ready(function(){
    document.getElementById("enterWithVk").addEventListener("click",   function (ev) {
        var newWin = window.open("https://oauth.vk.com/authorize?client_id=7101802&display=page&redirect_uri=http://localhost:8080/JavaEEtest_war_exploded/project/OAuthJSP.jsp&scope=friends,email,pages,first_name,last_name&response_type=token&v=5.52", "JSSite",
            "width=420,height=230,resizable=yes,scrollbars=yes,status=yes");
    }, false);
});

function moveToNews() {
    window.location = "http://localhost:8080/JavaEEtest_war_exploded/project/news.jsp";
}


function get_cookie ( cookie_name ) {
    var results = document.cookie.match ( '(^|;) ?' + cookie_name + '=([^;]*)(;|$)' );

    if ( results )
        return ( unescape ( results[2] ) );
    else
        return null;
}
