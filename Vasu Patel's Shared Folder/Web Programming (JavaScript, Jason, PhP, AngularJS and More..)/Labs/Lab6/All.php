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
	
	while($bottles > 0){
	
	echo "$bottles bottles of beer on the wall...<br>"; $bottles--;
    echo "You take one down you pass it around...<br>";
    echo "$bottles bottles of beer on the wall. <br><br>"; 
    
	}
	
	echo "There are no more bottles of beer.";
    
	?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "footer.php"; ?>
	
	</div>
			
			
	</body>

</html>