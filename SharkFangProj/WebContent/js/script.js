var xSize=10;
var ySize=10;
var rows='';

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

function generatePlayerBoard(){
  
  //Table Headers
  for(i=1; i<xSize+1; i++){
      document.getElementById("header").innerHTML += '<th>'+i+'</th>';

  }
  
  //Make Rows
  //Start with label
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
  
  document.getElementById("playerBoard").innerHTML += rows;
  
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