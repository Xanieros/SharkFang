var xSize=10;
var ySize=10;
var rows='';

function testFunction(){
	window.alert("test called");
}

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