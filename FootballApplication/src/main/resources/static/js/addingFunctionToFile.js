/**
 * File to combine HTML elements with JS code
 */
import { randomFunction } from './countGoals.js';

const infoButton = document.querySelector('#infoButton');
const changeButton = document.querySelector('#changeButton');
const tooltip = document.querySelector('#tooltip');

function showTooltip(){
	tooltip.innerText = 'Now you can choose which games save to external file';
}

document.addEventListener("DOMContentLoaded",function(){
	infoButton.addEventListener('click', showTooltip);
	changeButton.addEventListener('click', function() {
		randomFunction(tooltip);
	});
	infoButton.addEventListener('mouseover', function() {
		infoButton.innerText = 'Click to see advice';
	});
	infoButton.addEventListener('mouseout', function() {
	    this.style.color = 'yellow';
	});
});