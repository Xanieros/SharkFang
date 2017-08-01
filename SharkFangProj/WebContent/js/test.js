/*
 * 
 * This file contains functions that are only activated when connected to the Servlet 
 * but do not rely on the Servlet response
 * eg. Writing over HTML
 * 
 * Please name functions in the format "methodTestFunction" to prevent namespace collision
 * 
 */


function activateTestFunction(){
//Activate top radio buttons			
				var fieldset = document.getElementById("attackBoardForm").getElementsByTagName("fieldset");
				fieldset[0].removeAttribute("disabled");
				
				//Reveal Save and Quit				
				document.getElementById("buttonArea").innerHTML = '<button form="attackBoardForm">Attack w Form</button>'
					+'<button onclick="sendMove(); ">Attack w JS</button>';
}

function placeShipsTestFuntion(){
	
}

function saveAndQuitTestFunction(){
	window.alert("Not implemented");
}

function removeBoxesTestFunction(){

	checkedBoxes = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "13", "14", "15", "16", "17", "18", "19"]
	
	var tabledata = document.getElementById("shipBoardForm").getElementsByTagName("td");
	console.log(tabledata);
	//var input = document.getElementById("shipBoardForm").getElementsByTagName("input");
	
	for(i=0; i<tabledata.length; i++){
		
		console.log(tabledata[i]);
		var value = tabledata[i].getAttribute("id");
		console.log(value);
		if(checkedBoxes.includes(value)){
			tabledata[i].setAttribute("class", "bg-warning");
			
		}
	}
	
	var fieldset = document.getElementById("shipBoardForm").getElementsByTagName("fieldset");
	fieldset[0].setAttribute("disabled", "");
	
}