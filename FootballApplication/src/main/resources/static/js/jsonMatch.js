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
const previousButton = document.querySelector('#previousButton');
let nextMatchId = 1;
let previousMatchId =-1;

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
	previousMatchId = previousMatchId+1;
	previousButton.innerText = "Go to match: "+previousMatchId;
}

function goToPreviousMatch(){
	$.ajax({
        url: "http://localhost:8080/testSPAMatch?id="+previousMatchId
    }).then(function(data) {
       $('.home-team').html("Home Team Score: "+data.homeTeamScore);
       $('.away-team').html("Away Team Score: "+data.awayTeamScore);
       $('.home-team-score').html("Attendance: "+data.attendance);
    });
	
	nextMatchId = nextMatchId-1;
	nextButton.innerText = "Go to match: "+nextMatchId;
	previousMatchId = previousMatchId-1;
	previousButton.innerText = "Go to match: "+previousMatchId;
}

//adding events to objects
document.addEventListener("DOMContentLoaded",function(){
	nextButton.addEventListener('click', goToNextMatch);
	previousButton.addEventListener('click', goToPreviousMatch);
});