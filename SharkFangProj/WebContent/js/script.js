xSize = 10;
ySize = 10;

/*var xhttp = new XMLHttpRequest();
var url='servletName?ships='+checkedBoxes;		  
xhttp.onreadystatechange = function()
{
  console.log(xhttp.readyState+" "+xhttp.status);
  if(xhttp.readyState == 4 && xhttp.status == 200)
	{
		console.log("Did something");
	}
	
};

xhttp.open('GET', url, true);
xhttp.send();*/

function testFunction(){
	window.alert("test called");
};

//Unused due to Page Redirect
/*function toggleLoginModal(){
	
//If the user authenticates
  document.getElementById("loginModal").setAttribute("class", "modal fade");
  
//If the user fails to authenticate
  //document.getElementById("errorMessage").innerHTML = 'Authentication Failed';
  //$("#errorMessage").delay(5000).fadeOut();
};*/

function generatePlayerBoards(){
	
	//Get values from form w/o redirect
	xSize = document.sizeForm.xSize.value;
	ySize = document.sizeForm.ySize.value;
	var enemyID = document.sizeForm.enemyID.value;
	
	//Validate valid board size, else do nothing
	if((xSize >=10 && xSize <=25) && (ySize >=10 && ySize <=25)){
		console.log("Board size: "+xSize+" by "+ySize);
		document.getElementById("test").innerHTML += "Board size: "+xSize+" by "+ySize;
		
		////////////////////////////////////////////////
		//Generate Headers (Shared by both boards)//////
		////////////////////////////////////////////////
		var headers = '<tr><th></th>'; //Initialize with empty corner
		for(i=1; i<=xSize; i++){
	      headers += '<th>'+i+'</th>';	      
		}
		headers += '</tr>';
		
		
		/////////////////////////////
		//Generate Attack Board//////
		/////////////////////////////
		  var rows='';
		  var counter=0;
		  for(i=0; i<ySize; i++){
		    rows+='<tr><td><label>'+(i+1)+'</label></td>';
			//Append buttons
			for(j=0; j<xSize; j++){
				rows+='<td class="bg-info">';
				rows+='<label><input type="radio" name="cell" value="'+counter+'"></td></label>';
			    	counter++;
			    }
			rows+='</tr>';
			  }
		  document.getElementById("attackBoard").innerHTML = headers+rows; //Write to page
	  
		  
	  	///////////////////////////
		//Generate Ship Board//////
		///////////////////////////
		  var rows='';
		  var counter=0;
		  for(i=0; i<ySize; i++){
		    rows+='<tr><td><label>'+(i+1)+'</label></td>';
		    	//Append checkboxes
		        for(j=0; j<xSize; j++){
		        	
		        	//rows+='<div class="checkbox"><label>';
		        	rows+='<td class="bg-info" id="'+ counter +'">';
		        	rows+='<label><input type="checkbox" name="ship" value="'+counter+'"></td></label>';
		        	counter++;
		        	
		        	
		            /*<label>
		              <input type="checkbox"> Check me out
		            </label>
		          </div>*/
		        }
		    rows+='</tr>';
		  }
	  document.getElementById("shipBoard").innerHTML = headers+rows; //Write to page
	  document.getElementById("buttonArea").style.display = "inline"; //Reveal Place Ship button
	  
	  
	  ////////////////////////////
	  //Send data to servlet//////
	  ////////////////////////////
	  var xhttp = new XMLHttpRequest();
	  var url=	'initialize?xSize='+xSize+'&'
	  			+'ySize='+ySize+'&'
	  			+'enemyID='+enemyID;
	  
		  xhttp.onreadystatechange = function()
			{
				if(xhttp.readyState == 4 && xhttp.status == 200)
					{
						console.log(xhttp.readyState+"/"+xhttp.status);
					}
				
			};
			
			//make call to server asynchronously
			xhttp.open('GET', url ,true);
			xhttp.send();
	}	
};

function populateMyProfileModal(){
	var xhttp = new XMLHttpRequest();
	var txt='';
	console.log("populateMyProfileModal() Called");
	  
	  xhttp.onreadystatechange = function()
		{
		  //document.getElementById("test").innerHTML = xhttp.readyState+" "+xhttp.status;
		  console.log(xhttp.readyState+" "+xhttp.status);
			//check to see if readystate == 4 and status = 200
			if(xhttp.readyState == 4 && xhttp.status == 200)
				{
				console.log("Populating Modal");
				var data = xhttp.responseText;
				console.log(data);
	            var userData = JSON.parse(data);
				
	            //document.getElementById("test").innerHTML = userData.uid;

				document.getElementById("myProfileModalTitle").innerHTML = userData.uname;
	            
				txt+="<tr><td>Email</td><td><input type='email' id='email' value='" + userData.email +"'></td></tr>";
				txt+="<tr><td>First Name</td><td><input type='text' id='fname' value='" + userData.fname +"'></td></tr>";
				txt+="<tr><td>Last Name</td><td><input type='text' id='lname' value='" + userData.lname +"'></td></tr>";
				txt+="<tr><td>New Password</td><td><input type='password' id='pword' value='" + userData.lname +"'></td></tr>";
				txt+="<tr><td>Confirm Password</td><td><input type='password' id='pwordconfirmed' value='" + userData.lname +"'></td></tr>";
	            
				document.getElementById("myProfileTable").innerHTML = txt;
				
				}
			
		};
		//make call to server asynchronously
		xhttp.open('GET','viewInformation',true);
		xhttp.send();


};

function loadNewGame(){
	var xhttp = new XMLHttpRequest();
	var txt='';
	console.log("loadNewGame() Called");
	  
	  xhttp.onreadystatechange = function()
		{
		  //document.getElementById("test").innerHTML = xhttp.readyState+" "+xhttp.status;
		  console.log(xhttp.readyState+" "+xhttp.status);
			//check to see if readystate == 4 and status = 200
			if(xhttp.readyState == 4 && xhttp.status == 200)
				{
				console.log("Sent Load Request");
				//var data = xhttp.responseText;
				//console.log(data);
	            //var userData = JSON.parse(data);
				
				}
			
		};
		//make call to server asynchronously
		xhttp.open('POST','initialize',true);
		xhttp.send();
};

function placeShips(){
	
	var checkedBoxes = [];
	
	//Pushes checked values onto array
	$("input:checkbox[name=ship]:checked").each(function(){
	    checkedBoxes.push($(this).val());
	});
	
	//Require 17 boxes to start game
	if(checkedBoxes.length < 17){
		window.alert("Not enough ships!")
	}
	
	else if(checkedBoxes.length > 17){
		window.alert("Too many ships!")
	}
	
	else{
		var xhttp = new XMLHttpRequest();
		var url='InitializeShips?ships='+checkedBoxes;		  
		xhttp.onreadystatechange = function()
		{
		  //document.getElementById("test").innerHTML = xhttp.readyState+" "+xhttp.status;
		  console.log(xhttp.readyState+" "+xhttp.status);
		  //check to see if readystate == 4 and status = 200
		  if(xhttp.readyState == 4 && xhttp.status == 200)
			{
				console.log("Sent Ships");
				//Colorize Ship Board and Disable Checkboxes
				removeBoxes(checkedBoxes);
				
				//Activate top radio buttons			
				var fieldset = document.getElementById("attackBoardForm").getElementsByTagName("fieldset");
				fieldset[0].removeAttribute("disabled");
				
				//Reveal Save and Quit				
				document.getElementById("buttonArea").innerHTML = '<button onclick="sendMove(); ">Attack</button>'
					+'<button onclick="saveAndQuit();">Save &amp; Quit</button>';
			}
			
		};
		//make call to server asynchronously
		xhttp.open('GET', url, true);
		xhttp.send();
	}

	console.log(checkedBoxes);
	
};

function saveAndQuit(){

	  var xhttp = new XMLHttpRequest();
	  
	  xhttp.onreadystatechange = function()
		{
			//check to see if readystate == 4 and status = 200
			if(xhttp.readyState == 4 && xhttp.status == 200)
				{
					console.log("Called Save and quit");
					document.getElementById("test").innerHTML += ("Called Save and quit");
				}
			
		}
		//make call to server asynchronously
		xhttp.open('POST','SaveGame',true);
		xhttp.send();
		
		};

function removeBoxes(checkedBoxes){
	
	var tabledata = document.getElementById("shipBoardForm").getElementsByTagName("td");
	console.log(tabledata);
	
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
	
};

//Function Delegated to Middle Tier
/*function saveAndQuit(){
		
	//Array representing player board state
	var playerBoard = []
	//Get all td elements from attackBoard
	var tdElements = document.getElementById("attackBoard").getElementsByTagName("td");
	
	for(i=0; i<tdElements.length; i++){
		
		//Get the bg-* values from the ship board
		var tdElement = tdElements[i].getAttribute("class");
		switch(tdElement){
		case "bg-info":
			playerBoard.push(0);//water
			break;
		case "bg-danger":
			playerBoard.push(1);//hit
			break;
		case "bg-faded":
			playerBoard.push(2);//miss
			break;
		case "bg-inverse":
			playerBoard.push(3);//ship
			break;
		default:
			break;
	}
}
console.log(playerBoard);
}
*/
function sendMove(){

	var checkedCell = $('input[name=cell]:checked').val();

	if(checkedCell === undefined){
		window.alert("Please make a move!")
	}
	
	else{
		
		console.log(checkedCell);
		
		var xhttp = new XMLHttpRequest();
		var url='PlayerAttack?move='+checkedCell;
		xhttp.onreadystatechange = function()
		{
		  console.log(xhttp.readyState+" "+xhttp.status);
		  if(xhttp.readyState == 4 && xhttp.status == 200)
			{
				console.log("sent Move");
				var data = xhttp.responseText;
				console.log("Received move: "+data);
				//var opponentMove = JSON.parse(data);
				//console.log("Received move: "+opponentMove);
				
			}
			
		};

		xhttp.open('GET', url, false);//synchronous
		xhttp.send();
		//receiveMove();
	}
};

function receiveMove(){
	
	var xhttp = new XMLHttpRequest();
	var url='servletName?ships='+checkedBoxes;		  
	xhttp.onreadystatechange = function()
	{
	  console.log(xhttp.readyState+" "+xhttp.status);
	  if(xhttp.readyState == 4 && xhttp.status == 200)
		{
		  	var data = xhttp.responseText;
			var opponentMove = JSON.parse(data);
			console.log("Received move: "+opponentMove);
			//Update value on shipBoard
			
		}
		
	};

	xhttp.open('GET', url, true);
	xhttp.send();
};

function updateProfile()
{
	console.log("updateProfile() Called");
	var xhttp = new XMLHttpRequest();

	var txt = '';
	txt = $('#email').attr('value') + ":";
	txt += $('#fname').attr('value') + ":";
	txt += $('#lname').attr('value');
	console.log(txt);
	var url='UpdatePlayerInfo?values=' + txt;
	//make call to server asynchronously
	xhttp.open('GET', url,true);
	xhttp.send();


};
function rotate() {
	if (document.getElementById("ship2").classList.contains('rotate90')) /* already rotated */
	{
		document.getElementById("ship2").classList.remove('rotate90');
	}
	else /* it is not rotated */
	{
		document.getElementById("ship2").classList.add('rotate90');
	}
};

function allowDrop(ev) {
    ev.preventDefault();
};

function drag(ev) {
    ev.dataTransfer.setData("image", "piece1");
};

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("image");
    ev.target.appendChild(document.getElementById(data));
};