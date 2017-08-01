xSize = 0;
ySize = 0;

function testFunction(){
	window.alert("test called");
};

function toggleLoginModal(){
	
//If the user authenticates
  document.getElementById("loginModal").setAttribute("class", "modal fade");
  
//If the user fails to authenticate
  //document.getElementById("errorMessage").innerHTML = 'Authentication Failed';
  //$("#errorMessage").delay(5000).fadeOut();
};

function generatePlayerBoards(){
	
	xSize = document.sizeForm.xSize.value;
	ySize = document.sizeForm.ySize.value;
	var enemyID = document.sizeForm.enemyID.value;
	
	if((xSize >=10 && xSize <=25) && (ySize >=10 && ySize <=25)){
		var headers = '<tr><th></th>'; //Initialize with empty corner
		
		console.log("Board size: "+xSize+" by "+ySize)
		document.getElementById("test").innerHTML = "Board size: "+xSize+" by "+ySize;

	  //Generate Headers (Shared by both boards)
	  for(i=1; i<=xSize; i++){
	      headers += '<th>'+i+'</th>';
	      
	  }
	  
	  //Close row tag
	  headers += '</tr>';
	  
	  /*
	   * Generate Attack Board
	   * 
	   */
	  var rows='';
	  var counter=0;
	  for(i=0; i<ySize; i++){
	    rows+='<tr><td><label>'+(i+1)+'</label></td>';
	    	//Append buttons
	        for(j=0; j<xSize; j++){
	        	rows+='<td class="bg-info"><input type="radio" name="cell" value="'+counter+'"></td>';
	        	counter++;
	        }
	    //Close row tag once row made
	    rows+='</tr>';
	  }
	  
	  //Write Completed Table
	  document.getElementById("attackBoard").innerHTML = headers+rows;
	  
	  /*
	   * Generate Ship Board
	   * 
	   */
	  var rows='';
	  var counter=0;
	  for(i=0; i<ySize; i++){
	    rows+='<tr><td><label>'+(i+1)+'</label></td>';
	    	//Append checkboxes
	        for(j=0; j<xSize; j++){
	        	rows+='<td class="bg-info"><input type="checkbox" name="ship" value="'+counter+'"></td>';
	        	counter++;
	        }
	    //Close row tag once row made
	    rows+='</tr>';
	  }
	  
	  //Write Completed Table
	  document.getElementById("shipBoard").innerHTML = headers+rows;
	  document.getElementById("buttonArea").style.display = "inline";
	  
	  /*
	   * Send data to servlet
	   * 
	   */
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
	            
				txt+="<tr><td>Email</td><td>" + userData.email +"</td></tr>";
				txt+="<tr><td>First Name</td><td>" + userData.fname +"</td></tr>";
				txt+="<tr><td>Last Name</td><td>" + userData.lname +"</td></tr>";
	            
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
}

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
				//Rewrite Ship Board
				removeBoxes(checkedBoxes);
				colorBoxes();
				//Activate top radio buttons
				
				document.getElementById("buttonArea").innerHTML = '<button onclick="makeMove(); ">Attack</button>'
					+'<button onclick="saveAndQuit(); ">Save &amp; Quit</button>';
			}
			
		};
		//make call to server asynchronously
		xhttp.open('GET', url, true);
		xhttp.send();
	}

	console.log(checkedBoxes);
	
}

function removeBoxes(){
	var fieldset = document.getElementById("shipBoardForm").getElementsByTagName("fieldset");
	fieldset[0].setAttribute("disabled", "");
	
}

function saveAndQuit(){
		
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

function makeMove(){
	
	
}