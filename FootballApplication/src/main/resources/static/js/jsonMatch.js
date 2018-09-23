/**
 * 
 */
//Function to get data from JSON and prepare it for HTML view
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/testSPAMatch?id=6"
    }).then(function(data) {
       $('.home-team').append(data.homeTeamScore);
       $('.away-team').append(data.awayTeamScore);
       $('.home-team-score').append(data.attendance);
    });
});

const nextButton = document.querySelector('#nextButton');
let nextMatchId = 5;
function goToNextMatch(){
	$.ajax({
        url: "http://localhost:8080/testSPAMatch?id="+nextMatchId
    }).then(function(data) {
       $('.home-team').html("Home Team Score: "+data.homeTeamScore);
       $('.away-team').html("Away Team Score: "+data.awayTeamScore);
       $('.home-team-score').html("Attendance: "+data.attendance);
    });
	nextMatchId = nextMatchId+1;
	nextButton.innerText = "Go to match: "+nextMatchId;
}

//adding events to objects
document.addEventListener("DOMContentLoaded",function(){
	nextButton.addEventListener('click', goToNextMatch);
});