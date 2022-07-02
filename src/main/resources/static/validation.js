function validateLoginForm() {
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;

    var result = true;

    if(login.length < 5) {
        result = false;
        document.getElementById("login").style.background = "#ff0000";
    } else {
        document.getElementById("login").style.background = "#ffffff";
    }

    if(password.length < 5) {
        result = false;
        document.getElementById("password").style.background = "#ff0000";
    } else {
        document.getElementById("password").style.background = "#ffffff";
    }

    return result;
}