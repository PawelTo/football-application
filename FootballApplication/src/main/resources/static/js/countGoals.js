/**
 * 
 */
function getTheBiggestScore() {
    document.getElementById("napis").innerHTML = "Paragraph changed.";
}

function openNewWindow(par){
	window.open(par);
}

function getDataFromHTML() {
	//method to prepare data from HTML to count the highest score
	var dataFromHTML = Array.prototype.slice.call(arguments);
	dataFromHTML = dataFromHTML.join();
	var arrayAfterSpliting = dataFromHTML.split("Match");
	var matchDetailsArray = [];
	for(i=1; i<arrayAfterSpliting.length; i++){
		matchDetailsArray[i-1]= arrayAfterSpliting[i].split(", ");
		console.log(matchDetailsArray[i-1]);
		matchDetailsArray[i-1][0] = parseInt(matchDetailsArray[i-1][0].substring(matchDetailsArray[i-1][0].search("=")+1));
		matchDetailsArray[i-1][4] = parseInt(matchDetailsArray[i-1][4].substring(matchDetailsArray[i-1][4].search("=")+1));
		matchDetailsArray[i-1][5] = parseInt(matchDetailsArray[i-1][5].substring(matchDetailsArray[i-1][5].search("=")+1));
	}
    document.getElementById("description").innerHTML = getMax(matchDetailsArray);
}

function getMax(parametr){
	//method to count the highest score
	var max = [0,-1];
	console.log(parametr);
	for(i=0;i<parametr.length;i++){
		if(parametr[i][4]+parametr[i][5]>max[1]){
			max[0] = parametr[i][0];
			max[1] = parseInt(parametr[i][4])+parseInt(parametr[i][5]);
		}
	}
    return "The biggest score id: " +max[0] + " number of goals: "+max[1];
}

function randomFunction(methodArg){
	//random function to test exporting function to other files (modules)
	methodArg.innerText = "text from other method";
}

//enable function to other files
export { randomFunction }