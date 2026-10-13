<!DOCTYPE html>

<html>

	<head>
	
		<link href="StyleSheet.css" rel="StyleSheet"> 

	</head>
	
	<body>
	
	<div id='header'>
	
		<?php include "header.php"; ?>
	
	</div>
	
	<div id="menu" >
	
		<?php include "menu.php"; ?>
	
	</div>
	
	<div id="content" >
	<?php 
	
	$bottles=99;
	
	while($bottles > 1){
	    
	    if(($bottles % 2) == 0){
	    
	    echo "$bottles bottles of beer can serve even number of guests<br>";
	  
	    } else {
	    
	    echo "$bottles bottles of beer can serve odd number of guests<br>";
	    
	    }
	    $bottles--;
	}
	
	echo "1 bottle of beer can serve ONLY ONE guest";
	 
	?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "footer.php"; ?>
	
	</div>
			
			
	</body>

</html>