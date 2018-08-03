/**
 * File to combine HTML elements with JS code
 */
import { randomFunction } from './countGoals.js'; //import function from other JS file

const infoButton = document.querySelector('#infoButton');
const changeButton = document.querySelector('#changeButton');
const insertButton = document.querySelector('#insertButton');
const tooltip = document.querySelector('#tooltip');
const table = document.getElementById('tabela'); //get table with results
const td = table.getElementsByTagName('td');

function showTooltip(){
	tooltip.innerText = 'Now you can choose which games save to external file';
}

function countAllGoalsInTable(){
	//method to sum of all goals in table
	let homeScore=0;
	let awayScore=0;
	let sum=0;
	for(let i=0; i<td.length;i=i+8){
		homeScore=td[i+3].innerText;
		awayScore=td[i+4].innerText;
		sum=sum+parseFloat(homeScore) + parseFloat(awayScore); //parse to number
	}
	tooltip.innerText = 'Sum of all goal in this games is: ' + sum;
	return sum;
}

function createInfoAboutGoals(){
	//method to create element with info about scored goal
	const newElement = document.createElement("div");
	newElement.id = "createdElement";
	newElement.innerText = 'Sum of all goal in this games is: ' + countAllGoalsInTable();
	newElement.style.setProperty("background-color", "#FF6633");
	return newElement;
}

function insertElement(){
	//method to insert element to HTML code
	const parentElement = document.getElementById("tooltip");
	parentElement.appendChild(createInfoAboutGoals());
}


//adding events to objects
document.addEventListener("DOMContentLoaded",function(){
	infoButton.addEventListener('click', showTooltip);
	infoButton.addEventListener('mouseover', function() {
		infoButton.innerText = 'Click to see advice';
	});
	infoButton.addEventListener('mouseout', function() {
	    this.style.color = 'yellow';
	});
	changeButton.addEventListener('dblclick', function() {
		randomFunction(tooltip);
	});
	changeButton.addEventListener('click', countAllGoalsInTable);
	insertButton.addEventListener('click', function() {
		insertElement();
	});
});