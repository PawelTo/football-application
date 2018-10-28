/**
 * File to combine HTML elements with JS code
 */

const listDropdownItems = document.querySelectorAll('.dropdown-item');
let myTable = document.getElementById("leagueTable");
let numberOfItem = 1;
let leagueName = document.getElementById("leagueName");

function showTable(id){
	$.ajax({
        url: "http://localhost:8080/table?id="+id
    }).then(function(data) {
    let jsonData = data;
    myTable = removeTableBody(myTable);    
    myTable = addDataToTable(myTable,jsonData);
    });
}

function addDataToTable(table,data){
   	for(let i=0;i<data.length;i++){
    	let jsonClubRecord = data[i];
    	let row = table.insertRow(i+1);
    	
    	let c1 = row.insertCell(0);
    	let c2 = row.insertCell(1);
    	let c3 = row.insertCell(2);
    	let c4 = row.insertCell(3);
    	c1.innerText = jsonClubRecord.teamNeame;
    	c2.innerText = jsonClubRecord.points;
    	c3.innerText = jsonClubRecord.goalsFor;
    	c4.innerText = jsonClubRecord.goalsAgainst;
    }
   	return table;
}

function removeTableBody(table){
	let numberRows = table.rows.length;
	for(let i=1;i<numberRows;i++){
		table.deleteRow(1);
	}
	return table;
}

function setNameOfLeague(text){
	leagueName.innerText = text;
}

// adding events to objects
document.addEventListener("DOMContentLoaded",function(){
	for(const item of listDropdownItems){
		(function(id) {
			item.addEventListener('click',function(){
				showTable(id);
			});
		})(numberOfItem);
		
		(function(text) {
			item.addEventListener('click',function(){
				setNameOfLeague(text);
			});
		})(item.innerText);
		numberOfItem++;
	}
});