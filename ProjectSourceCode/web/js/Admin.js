jQuery(document).ready(function(){
    document.getElementById("swaper").addEventListener("click", function (ev) {
        var url = window.location + "&";
        var userLogin = getElement("user", url);

        $.ajax({
            url : "../setAdmin",
            method : "POST",
            data : {
                ulogin : userLogin
            },
            success: function (response) {
                //location.href="http://localhost:8080/JavaEEtest_war_exploded/project/profile.jsp";
            },
            error : function (response) {
                alert("Ошибка с сетью!");
            }
        });
    });
    document.getElementById("kiker").addEventListener("click", function (ev) {
        var url = window.location + "&";
        var userLogin = getElement("user", url);

        $.ajax({
            url : "../deleteUser",
            method : "POST",
            data : {
                ulogin : userLogin
            },
            success: function (response) {
                location.href="http://localhost:8080/JavaEEtest_war_exploded/project/scores.jsp";
            },
            error : function (response) {
                alert("Ошибка с сетью!");
            }
        });
    });
});

function getElement( el, text) {
    var start_id = -1;
    for(var i = 0; i < text.length; i++){
        var text_id = i;
        var el_id = 0;
        while((text_id < text.length) && (el_id < el.length) && (text[text_id] == el[el_id])){
            text_id++;
            el_id++;
        }
        if(el_id == el.length){
            start_id = i;
            break;
        }
    }
    if(start_id != -1){
        var val = "";
        var startflag = false;
        for(var i = start_id; i < text.length; i++){
            if(startflag){
                if(text[i] == '&') break;
                else val += text[i];
            }
            else if(text[i] == '=') startflag = true;
        }
        return val;
    }
    return "";
}