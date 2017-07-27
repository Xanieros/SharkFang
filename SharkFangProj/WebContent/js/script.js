var xSize=10;
var ySize=10;
var cells='';

function testFunction(){
	window.alert("test called");
}

function toggleLoginModal(){
	
//If the user authenticates
  //document.getElementById("loginModal").setAttribute("class", "modal fade");
  
//If the user fails to authenticate
  document.getElementById("errorMessage").innerHTML = 'Authentication Failed';
  //$("#errorMessage").delay(5000).fadeOut();
};

function generatePlayerBoard(){
  
  //Table Headers
  for(i=0; i<xSize; i++){
      document.getElementById("playerBoard").innerHTML += '<th>'+i+'</th>';

  }
  
  //Unique Cells
  for(i=0; i<xSize; i++){
    cells+='<td><label>'+i+'</label></td>'
    cells+='<td class="hit"><input type="radio" name="cell" value="'+i+'" disabled></td>';
    console.log(cells);
  }
  
  //Table Rows
  for(i=0; i<ySize; i++){
      document.getElementById("playerBoard").innerHTML += 
        +'<tr>'+cells
        //+'<td><label>'+i+'</label></td>'+cells
        //+'<td class="hit"><input type="radio" name="cell" value="A1" disabled></td>'
        +'</tr>';

  }
};