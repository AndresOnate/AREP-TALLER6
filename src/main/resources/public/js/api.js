function loadGetLogMsg() {
    let nameVar = document.getElementById("log").value;
    if (nameVar.trim() === "") {
        alert("El campo no puede estar vacío.");
        return;
    }
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        const response = JSON.parse(this.responseText);
        buildTable(response);
    };
    xhttp.open("GET", "/log?msg=" + nameVar);
    xhttp.send();
}

function buildTable(data) {
    let tableHTML = "<table border='1'><tr><th>Date</th><th>Log</th></tr>";
    data.forEach(function(entry) {
        tableHTML += "<tr><td>" + entry.Date + "</td><td>" + (entry.log ? entry.log : "N/A") + "</td></tr>";
    });
    tableHTML += "</table>";
    document.getElementById("table-container").innerHTML = tableHTML;
}

