xSize = 10;
ySize = 10;
shipsPlaced = 0;
maxShips = 5;

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

	// hide the jumbotron
	document.getElementById('welcomeDiv').style.display = 'none';

	generateTable();
	generateShips();
	generateShipPiecesDiv();

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

	$('#newGameModal').modal('hide');
};

function generatePlayerBoardss(){
	
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
	$('#newGameModal').modal('hide');
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
	            
				txt+="<tr><td>Email</td><td><input type='email' id='email' value='" + userData.email +"'disabled></td></tr>";
				txt+="<tr><td>First Name</td><td><input type='text' id='fname' value='" + userData.fname +"'></td></tr>";
				txt+="<tr><td>Last Name</td><td><input type='text' id='lname' value='" + userData.lname +"'></td></tr>";
				txt+="<tr><td>New Password</td><td><input type='password' id='pword'></td></tr>";
				txt+="<tr><td>Confirm Password</td><td><input type='password' id='pwordconfirmed'></td></tr>";
	            
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
				console.log("Received hit/miss: " + data);
				if(data == 1)
					{
					$('input[name=cell]:checked').parent().parent().removeClass("bg-info");
					$('input[name=cell]:checked').parent().parent().addClass("hit");
					
					}
				else
					{
					$('input[name=cell]:checked').parent().parent().removeClass("bg-info");
					$('input[name=cell]:checked').parent().parent().addClass("miss");
					}
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
	if(validate()){
	var xhttp = new XMLHttpRequest();

	var txt = '';
	txt = "fname=" + $('#fname').prop('value') + "&";
	txt += "lname=" + $('#lname').prop('value') + "&";
	txt += "pword=" + $('#pword').prop('value');
	console.log(txt);

	//make call to server asynchronously
	xhttp.open('POST', 'UpdatePlayerInfo', true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(txt);
				

	}
};

function validate() {
    var password = document.getElementById("pword").value;
    var confirmPassword = document.getElementById("pwordconfirmed").value;
    if (password != confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }
    return true;
};

function generateTable() 
{
	var tableString = "";

	xSize = document.sizeForm.xSize.value;
	ySize = document.sizeForm.ySize.value;

	tableString += "<tr> <th colspan='" + (xSize+1) + " '><h2 class='playerGridCell'><strong>Player Board</strong></h2> </th> </tr>";
	tableString += '<tr><th></th>'; //Initialize with empty corner
	for(i=1; i<=xSize; i++)
	{
		tableString += '<th class="playerGridCell">' +i+'</th>';	      
	}

	for (var i = 0; i < ySize; i++)
	{
		tableString += "<tr>";
		tableString +='<td class="playerGridCell"><label>'+(i+1)+'</label></td>';
		for (var j = 0; j < xSize; j++)
		{
			tableString += "<td id='" + (ySize*i + j) + "' style='width: 51px; height:51px;' ondrop='drop(event)' ondragover='allowDrop(event)'></td>";
		}
		tableString += "</tr>";
	}
	document.getElementById("tableGrid").innerHTML = tableString;
};

function generateEnemyTable() {

	/////////////////////////////
	//Generate Attack Board//////
	/////////////////////////////
	var rows='';
	var counter=0;
	rows += "<tr> <th colspan='" + (xSize+1) + "'><h2 class='enemyGridCell' ><strong>Enemy Board</strong></h2> </th> </tr>";
	rows += '<tr><th></th>'; //Initialize with empty corner
	for(i=1; i<=xSize; i++)
	{
		rows += '<th class="enemyGridCell">' +i+'</th>';	      
	}

	  for(i=0; i<ySize; i++){
	    rows+='<tr><td class="enemyGridCell"><label>'+(i+1)+'</label></td>';
		//Append buttons
		for(j=0; j<xSize; j++){
			rows+='<td  class="enemyGridCell" style="width: 51px; height:51px;">';
			rows+='<label style="border:1 px solid yellow; padding-top:25%;"><input type="radio" name="cell" value="'+counter+'"></td></label>';
		    	counter++;
		    }
		rows+='</tr>';
		  }
	  document.getElementById("enemyTableGrid").innerHTML =rows;
};

/* make this more dynamic */ 
function rotate(elementTrigger) {
	var elementTriggerString = elementTrigger.id; /* ship2 ship31 ship32 .. */
	var reverseTriggerString = elementTriggerString + 'r';
	var shipNum = elementTriggerString.match(/\d+/g).map(Number); /* 2, 31, 32, 4, 5 */
	var shipNumOnly = shipNum;
	if (shipNum > 9)
	{
		shipNumOnly = Math.floor(shipNum/10);
	}
	if (document.getElementById(elementTriggerString).classList.contains('rotate90')) /* turn to horizontal */
	{
		document.getElementById(elementTriggerString).classList.remove('rotate90');
		for (var i = 1; i <= shipNumOnly; i++)
		{
			var shipDiv = elementTriggerString + i + 'div';
			document.getElementById(shipDiv).classList.remove(reverseTriggerString);
			document.getElementById(shipDiv).classList.remove('piece' + i + 'r');
			document.getElementById(shipDiv).classList.add(elementTriggerString);
			document.getElementById(shipDiv).classList.add('piece' + i);
		}
	}
	else /* turn to vertical */
	{
		document.getElementById(elementTriggerString).classList.add('rotate90');
		for (var i = 1; i <= shipNumOnly; i++)
		{
			var shipDiv = elementTriggerString + i + 'div';
			document.getElementById(shipDiv).classList.add(reverseTriggerString);
			document.getElementById(shipDiv).classList.add('piece' + i + 'r');
			document.getElementById(shipDiv).classList.remove(elementTriggerString);
			document.getElementById(shipDiv).classList.remove('piece' + i);
		}
	}
};

function allowDrop(ev) {
    ev.preventDefault();
};

function drag(ev) {
	ev.dataTransfer.setData("dragSource", ev.target.id);
};

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("dragSource"); /* ship2, ship31, ship32, ... */
    var shipNum = data.match(/\d+/g).map(Number); /* 2, 31, 32, 4, 5 */
    
    if (shipNum > 9)
	{
		shipNum = Math.floor(shipNum/10);
	}

    var targetCell = ev.target.id;
    var targetCellInt = parseInt(targetCell);
    var nextCell;

    xSize = document.sizeForm.xSize.value;
	ySize = document.sizeForm.ySize.value;

    /* check to see if it is possible to place the ship there */
    if (document.getElementById(data).classList.contains('rotate90'))
    {
    	if ( Math.floor(targetCellInt / xSize) >= (xSize - (shipNum - 1)) )
    	{
    		window.alert("Can't place ship there!");
    		return;
    	}
    }
    else
    {
	    if ( (targetCellInt % xSize) >= (xSize - (shipNum - 1)))
	    {
	    	alert("Can't place ship there!");
	    	return;
	    }
	}

	/* check to see if ship exists at location */
	if (isNaN(targetCell))
	{
		alert("Ship already exists here!");
		return;
	}

	var tempNextCell;
	for (var i = 1; i <= shipNum; i++)
	{
		if (document.getElementById(data).classList.contains('rotate90'))
		{
			tempNextCell = targetCellInt + (i-1)*xSize;
			/* check to see if ship exists */
			if (document.getElementById(tempNextCell.toString()).hasChildNodes())
			{
				alert("Ship already exists here!");
				return;
			}
		}
		else
		{
			tempNextCell = targetCellInt + (i-1);
			/* check to see if ship exists */
			if (document.getElementById(tempNextCell.toString()).hasChildNodes())
			{
				alert("Ship already exists here!");
				return;
			}
		}
	}

	for (var i = 1; i <= shipNum; i++)
	{
		if (document.getElementById(data).classList.contains('rotate90'))
		{
			tempNextCell = targetCellInt + (i-1)*xSize;
		}
		else
		{
			tempNextCell = targetCellInt + (i-1);
		}

		var shipDiv = data + i + 'div';
		document.getElementById(shipDiv).style.display = '';
		document.getElementById(tempNextCell.toString()).appendChild(document.getElementById(shipDiv));

	}
    document.getElementById(data).style.display = 'none';
    shipsPlaced++; 
    checkDonePlacingShips();
};

function resetBoard() {
	document.getElementById('ship2').style.display = '';
	document.getElementById('ship31').style.display = '';
	document.getElementById('ship32').style.display = '';
	document.getElementById('ship4').style.display = '';
	document.getElementById('ship5').style.display = '';

	if (document.getElementById('ship2').classList.contains('rotate90'))
	{
		rotate(document.getElementById('ship2'));
	}
	if (document.getElementById('ship31').classList.contains('rotate90'))
	{
		rotate(document.getElementById('ship31'));
	}
	if (document.getElementById('ship32').classList.contains('rotate90'))
	{
		rotate(document.getElementById('ship32'));
	}
	if (document.getElementById('ship4').classList.contains('rotate90'))
	{
		rotate(document.getElementById('ship4'));
	}
	if (document.getElementById('ship5').classList.contains('rotate90'))
	{
		rotate(document.getElementById('ship5'));
	}
	generateTable();
	generateShipPiecesDiv();
	shipsPlaced = 0;
};

function generateShipPiecesDiv() {
	var divString = 
	"<div id='ship21div' class='ship2 piece1' style='display:none;'></div>" +
	"<div id='ship22div' class='ship2 piece2' style='display:none;'></div>" +

	"<div id='ship311div' class='ship31 piece1' style='display:none'></div>" +
	"<div id='ship312div' class='ship31 piece2' style='display:none'></div>" +
	"<div id='ship313div' class='ship31 piece3' style='display:none'></div>" +

	"<div id='ship321div' class='ship32 piece1' style='display:none'></div>" +
	"<div id='ship322div' class='ship32 piece2' style='display:none'></div>" +
	"<div id='ship323div' class='ship32 piece3' style='display:none'></div>" +

	"<div id='ship41div' class='ship4 piece1' style='display:none'></div>" +
	"<div id='ship42div' class='ship4 piece2' style='display:none'></div>" +
	"<div id='ship43div' class='ship4 piece3' style='display:none'></div>" +
	"<div id='ship44div' class='ship4 piece4' style='display:none'></div>" +

	"<div id='ship51div' class='ship5 piece1' style='display:none'></div>" +
	"<div id='ship52div' class='ship5 piece2' style='display:none'></div>" +
	"<div id='ship53div' class='ship5 piece3' style='display:none'></div>" +
	"<div id='ship54div' class='ship5 piece4' style='display:none'></div>" +
	"<div id='ship55div' class='ship5 piece5' style='display:none'></div>";

	document.getElementById('shipPiecesDiv').innerHTML = divString;
};

function printShipArray() {
	var shipList = [];

	xSize = document.sizeForm.xSize.value;
	ySize = document.sizeForm.ySize.value;

	for(var i = 0; i < xSize*ySize; i++)
	{
		if (document.getElementById(i.toString()).hasChildNodes())
		{
			shipList.push(i);
		}
	}
	/* need to pass this to servlet*/
	return shipList;
};

function checkDonePlacingShips() {
	if (shipsPlaced == maxShips)
	{
		document.getElementById('shipsImageDiv').style.width = '0';
		document.getElementById('tableGridDiv').style.float = 'left';
		generateEnemyTable();
		
		
		var xhttp = new XMLHttpRequest();
		var url='InitializeShips?ships='+printShipArray();		  
		xhttp.onreadystatechange = function()
		{
		  console.log(xhttp.readyState+" "+xhttp.status);
		  //check to see if readystate == 4 and status = 200
		  if(xhttp.readyState == 4 && xhttp.status == 200)
			{
				console.log("Sent Ships");
				
			/*	//Activate top radio buttons			
				var fieldset = document.getElementById("attackBoardForm").getElementsByTagName("fieldset");
				fieldset[0].removeAttribute("disabled");*/
				
				//Reveal Save and Quit				
				document.getElementById("buttonArea").innerHTML = '<button onclick="sendMove(); ">Attack</button>'
					+'<button onclick="saveAndQuit();">Save &amp; Quit</button>';
				document.getElementById("buttonArea").style.display= '';
			}
			
		};
		//make call to server asynchronously
		xhttp.open('GET', url, true);
		xhttp.send();
	}
};

function generateShips() {
	var shipString = "";
	shipString += "<img id='ship2' src='../images/ship2.png' draggable='true' ondblclick='rotate(this);' ondragstart='drag(event)' style='width: 100px; height:50px; float:left; margin:25px 0px;'></img>" +
		  	"<img id='ship31' src='../images/ship31.png' draggable='true' ondblclick='rotate(this);' ondragstart='drag(event)' style='width: 150px; height:50px; float:left; margin:50px 0px;'></img>" +
		  	"<img id='ship32' src='../images/ship32.png' draggable='true' ondblclick='rotate(this);' ondragstart='drag(event)' style='width: 150px; height:50px; float:left; margin:50px 0px;'></img>" +
		  	"<br>" +
		  	"<img id='ship4' src='../images/ship4.png' draggable='true' ondblclick='rotate(this);' ondragstart='drag(event)' style='width: 200px; height:50px; float:left; margin:75px 0px;'></img>" +
		  	"<img id='ship5' src='../images/ship5.png' draggable='true' ondblclick='rotate(this);' ondragstart='drag(event)' style='width: 250px; height:50px; float:left; margin:100px 0px;'></img>";
	document.getElementById('shipsImageDiv').innerHTML = shipString;
}

 $(function(){
        var x = 0;
        setInterval(function(){
            x-=1;
            $('body').css('background-position', x + 'px 0');
        }, 30);
    })

