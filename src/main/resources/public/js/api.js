function loadGetLogMsg() {
    let nameVar = document.getElementById("log").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("logmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/log?msg=" + nameVar);
    xhttp.send();
};

