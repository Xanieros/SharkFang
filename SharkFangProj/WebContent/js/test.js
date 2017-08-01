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
				document.getElementById("buttonArea").innerHTML = '<button onclick="makeMove(); ">Attack</button>'
					+'<button onclick="SaveGame">Save &amp; Quit</button>';
}

function placeShipsTestFuntion(){
	
}

function saveAndQuitTestFunction(){
	window.alert("Not implemented");
}