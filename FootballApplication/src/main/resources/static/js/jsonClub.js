/**
 * 
 */
//Function to get data from JSON and prepare it for HTML view
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/testResponse"
    }).then(function(data) {
       $('.club-name').append(data.clubName);
       $('.club-stadiumName').append(data.stadiumName);
       $('.club-stadiumCapacity').append(data.stadiumCapacity);
    });
});