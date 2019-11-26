var cvs;
var ctx;

var score = 0;

var explosion_image = new Image();
var explosions = [];

var ship_image = new Image();
var ship_health = 100;
var ship_x = 200;
var ship_y = 200;
var ship_move_speed = 4;
var ship_blaster_speed = 4;
var ship_coolDown = 15;
var ship_real_coolDown = 0;

var dron_image = new Image();
var drons = [];
var dron_move_speed = 3;
var dron_coolDown = 20;
var dron_blaster_speed = 4;

var red_lazer = {
    img : new Image(),
    lazers : []
};
var blue_lazer = {
    img : new Image(),
    lazers : []
};

var resetFlag = 0;
var stopFlag = 0;
var userEvent;

var userId = null;

jQuery(document).ready(function(){
    userId = get_cookie("userId");

    cvs = document.getElementById("canvas1");
    ctx = cvs.getContext("2d");
    var saveButton = document.getElementById("saveButton");
    saveButton.addEventListener("click", saveResult, false);
    var refreshButton = document.getElementById("refreshButton"); // new Game
    refreshButton.addEventListener("click", saveResult, false);
    refreshButton.addEventListener("click", function (ev) {
        userEvent = "newGame";
        if(stopFlag == 1){
            stopFlag = 0;
            processUserEvent();
        }
        else if(resetFlag == 0) stopFlag = 1;
        else {
            reset();
            resetFlag = 0;
            stopFlag = 0;
            alert("start");
            draw();
        }
    }, false);
    var pauseButton = document.getElementById("pauseButton"); // pause
    pauseButton.addEventListener("click", function (ev) {
        stopFlag = 1;
        userEvent = "pauseGame";
    }, false);
    var continueButton = document.getElementById("continueButton"); // continue
    continueButton.addEventListener("click", function (ev) {
        if(stopFlag == 1) {
            stopFlag = 0;
            userEvent = "continueGame";
            draw();
        }
    }, false);

    reset();

    explosion_image.src = "../../images/explosion.jpeg";
    blue_lazer.img.src = "../../images/lazer_blue.jpg";
    red_lazer.img.src = "../../images/lazer_red.jpg";
    dron_image.src = "../../images/enemy1.jpg";
    ship_image.src = "../../images/sheep2.jpg";

    ship_image.onload = draw();

    alert("start");

    //draw();

    //var el = document.getElementById("refreshButton");
    //el.addEventListener("click", refresh, false);
});

function reset(){
    while(explosions.length > 0){
        explosions.splice(0, 1);
    }

    while(blue_lazer.lazers.length > 0){
        blue_lazer.lazers.splice(0, 1);
    }

    while(red_lazer.lazers.length > 0){
        red_lazer.lazers.splice(0, 1);
    }

    // drow results
    ctx.fillStyle = "red";
    ctx.font = "24px Verdana";
    ctx.fillText("Счёт : " + score, 100, 20);
    ctx.fillText("Жизнь : " + ship_health, 100, 40);
    ctx.fillText("Конец игры!" , 100, 80);

    drons[0] = {
        coolDown : 15,
        x : 0,
        y : 0,
        health : 100
    };
    ship_x = 200;
    ship_y = 200;
    ship_health = 100;
}

function processUserEvent() {
    switch (userEvent) {
        case "newGame" : {
            reset();

            stopFlag = 0;
            alert("start");
            draw();
        } break;
    }
}

function draw(){
    if(!stopFlag) {
        ctx.clearRect(0, 0, cvs.width, cvs.height);

        if (resetFlag == 1) {
            return;
        }

        // draw explosions
        for (var i = 0; i < explosions.length; i++) {
            ctx.drawImage(explosion_image, explosions[i].x, explosions[i].y, explosions[i].width, explosions[i].height);
            explosions[i].duration--;
            explosions[i].width--;
            explosions[i].height--;
            if (explosions[i].duration <= 0) {
                explosions.splice(i, 1);
                i--;
            }
        }


        // draw ship
        ctx.drawImage(ship_image, ship_x, ship_y);
        // add new blue lazer - if ship real coolDown == 0
        if (ship_real_coolDown == 0) {
            ship_real_coolDown = ship_coolDown;
            addNewBlueLazer(10, ship_x + (ship_image.width / 2), ship_y, 0, -ship_blaster_speed);
            addNewBlueLazer(10, ship_x + (ship_image.width / 2), ship_y, -1, -ship_blaster_speed);
            addNewBlueLazer(10, ship_x + (ship_image.width / 2), ship_y, 1, -ship_blaster_speed);
        }
        // count down - real ship coolDown
        ship_real_coolDown--;


        // draw drons
        for (var i = 0; i < drons.length; i++) {
            ctx.drawImage(dron_image, drons[i].x, drons[i].y);
            drons[i].coolDown--;

            // generate new explosion and new dron
            if (drons[i].health <= 0) {
                explosions[explosions.length] = {
                    x: drons[i].x,
                    y: drons[i].y,
                    duration: 50,
                    width: explosion_image.width,
                    height: explosion_image.height
                };
                drons[i].health = 100;
                drons[i].x = getRandomInt(400, 600);
                drons[i].y = 0;
            }

            // move dron
            if (ship_x + ship_image.width < drons[i].x) drons[i].x -= dron_move_speed;
            else if (drons[i].x + dron_image.width < ship_x) drons[i].x += dron_move_speed;
            else {
                // generate new red laser
                if (drons[i].coolDown <= 0) {
                    addNewRedLazer(2, drons[i].x + (dron_image.width / 2), drons[i].y + dron_image.height, 2, dron_blaster_speed);
                    addNewRedLazer(2, drons[i].x + (dron_image.width / 2), drons[i].y + dron_image.height, 1, dron_blaster_speed);
                    addNewRedLazer(2, drons[i].x + (dron_image.width / 2), drons[i].y + dron_image.height, 0, dron_blaster_speed);
                    addNewRedLazer(2, drons[i].x + (dron_image.width / 2), drons[i].y + dron_image.height, -1, dron_blaster_speed);
                    addNewRedLazer(2, drons[i].x + (dron_image.width / 2), drons[i].y + dron_image.height, -2, dron_blaster_speed);
                    drons[i].coolDown = dron_coolDown;
                }
                if (getRandomInt(0, 100) < 50) drons[i].y -= dron_move_speed;
                else drons[i].y += dron_move_speed;
                if (drons[i].y >= 10) drons[i].y = 10;
            }

            if (drons[i].x < 0) drons[i].x = 0;
            if (drons[i].y < 0) drons[i].y = 0;
            if (drons[i].x > cvs.width - dron_image.width) drons[i].x = cvs.width - dron_image.width;
            if (drons[i].y > cvs.height - dron_image.height) drons[i].y = cvs.height - dron_image.height;
        }

        // draw lazers
        for (var i = 0; i < blue_lazer.lazers.length; i++) {
            ctx.drawImage(blue_lazer.img, blue_lazer.lazers[i].x, blue_lazer.lazers[i].y);

            /// chek for beating drons
            for (var j = 0; j < drons.length; j++) {
                if ((drons[j].x <= getMiddleX(blue_lazer.lazers[i])) && (getMiddleX(blue_lazer.lazers[i]) <= drons[j].x + dron_image.width) &&
                    (drons[j].y <= blue_lazer.lazers[i].y) && (blue_lazer.lazers[i].y <= drons[j].y + dron_image.height)) {
                    drons[j].health -= blue_lazer.lazers[i].power;
                    blue_lazer.lazers[i].y = -100;
                    score += blue_lazer.lazers[i].power;
                }
            }
        }
        for (var i = 0; i < red_lazer.lazers.length; i++) {
            ctx.drawImage(red_lazer.img, red_lazer.lazers[i].x, red_lazer.lazers[i].y);

            /// try to check beating sheep by red lasers
            if ((ship_x <= getMiddleX(red_lazer.lazers[i])) && (getMiddleX(red_lazer.lazers[i]) <= ship_x + ship_image.width) &&
                (ship_y <= red_lazer.lazers[i].y) && (red_lazer.lazers[i].y <= ship_y + ship_image.height)) {
                ship_health -= red_lazer.lazers[i].power;
                red_lazer.lazers[i].y = -100;
            }
        }
        /// crusing laser by laser
        for (var i = 0; i < red_lazer.lazers.length; i++) {
            var flag = 0;
            for (var j = 0; j < blue_lazer.lazers.length; j++) {
                if ((blue_lazer.lazers[j].x <= red_lazer.lazers[i].x) && (red_lazer.lazers[i].x <= blue_lazer.lazers[j].x + blue_lazer.img.width)
                    && (blue_lazer.lazers[j].y <= red_lazer.lazers[i].y) && (red_lazer.lazers[i].y <= blue_lazer.lazers[j].y + blue_lazer.img.height)) {
                    flag = 1;
                    blue_lazer.lazers.splice(j, 1);
                    break;
                }
            }
            if (flag == 1) {
                red_lazer.lazers.splice(i, 1);
                i--;
            }
        }
        /// cleaning oldddd lazers
        for (var i = 0; i < blue_lazer.lazers.length; i++) {
            blue_lazer.lazers[i].x += blue_lazer.lazers[i].dx;
            blue_lazer.lazers[i].y += blue_lazer.lazers[i].dy;
            if (!((0 <= blue_lazer.lazers[i].x) && (0 <= blue_lazer.lazers[i].y) &&
                (blue_lazer.lazers[i].x <= cvs.width) && (blue_lazer.lazers[i].y <= cvs.height))) {
                blue_lazer.lazers.splice(i, 1);
                i--;
            }
        }
        for (var i = 0; i < red_lazer.lazers.length; i++) {
            red_lazer.lazers[i].x += red_lazer.lazers[i].dx;
            red_lazer.lazers[i].y += red_lazer.lazers[i].dy;
            if (!((0 <= red_lazer.lazers[i].x) && (0 <= red_lazer.lazers[i].y) &&
                (red_lazer.lazers[i].x <= cvs.width) && (red_lazer.lazers[i].y <= cvs.height))) {
                red_lazer.lazers.splice(i, 1);
                i--;
            }
        }

        if (ship_health <= 0) resetFlag = 1;

        // drow results
        ctx.fillStyle = "red";
        ctx.font = "24px Verdana";
        ctx.fillText("Счёт : " + score, 100, 20);
        ctx.fillText("Жизнь : " + ship_health, 100, 40);
        requestAnimationFrame(draw);
    }
    else processUserEvent();
}

// user want move sheep
document.addEventListener("keydown", function(e){
    switch(e.keyCode){
        case 87 : // W - pressed
            ship_y -= ship_move_speed;
            break;
        case 83 : // S - pressed
            ship_y += ship_move_speed;
            break;
        case 65 : // A - pressed
            ship_x -= ship_move_speed;
            break;
        case 68 : // D - pressed
            ship_x += ship_move_speed;
            break;
        case 73 : // I - pressed
            addNewBlueLazer(10, ship_x + (ship_image.width / 2), ship_y, 0, -ship_blaster_speed);
            break;
    }

    if(ship_x < 0) ship_x = 0;
    if(ship_y < 0) ship_y = 0;
    if(ship_x > cvs.width - ship_image.width) ship_x = cvs.width - ship_image.width;
    if(ship_y > cvs.height - ship_image.height) ship_y = cvs.height - ship_image.height;
});

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

function getMiddleX(lazer){
    return lazer.x + (blue_lazer.img.width / 2);
}

function addNewBlueLazer(power, x, y, dx, dy){
    blue_lazer.lazers[blue_lazer.lazers.length] = {
        power : power,
        x : x,
        y : y,
        dx : dx,
        dy : dy
    };
}

function addNewRedLazer(power, x, y, dx, dy){
    red_lazer.lazers[red_lazer.lazers.length] = {
        power : power,
        x : x,
        y : y,
        dx : dx,
        dy : dy
    };
}

function SendPost() {
    if(userId != get_cookie("userId")){
        window.location = window.location;
    }
    else {
        $.ajax({
            url: "../../doSaveResult",
            type: "POST",
            data: {score: score.toString()}
        });
    }
/*    $.post(
        '../../doSaveResult',//url адрес файла обработчика
        {'score': toString(score)},//параметры запроса
        function() { alert("Успешное выполнение"); },
        function() { alert("Ошибка выполнения"); },
        function() { alert("Завершение выполнения"); }
    );*/
}

function saveResult(){
    SendPost();
    //SendRequest("post", "../../doSaveResult", "score" )
}

function get_cookie ( cookie_name ) {
    var results = document.cookie.match ( '(^|;) ?' + cookie_name + '=([^;]*)(;|$)' );

    if ( results )
        return ( unescape ( results[2] ) );
    else
        return null;
}
