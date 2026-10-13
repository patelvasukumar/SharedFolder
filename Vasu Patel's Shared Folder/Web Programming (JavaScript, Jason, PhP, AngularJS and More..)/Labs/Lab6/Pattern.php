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
	
	$row = 1;
	while ($row <= 9) {
	    
	    $column = 1;
	    
	    while ($column <= $row) {
	        
	        echo " * ";
	        $column++;
	    
	    }
	    
	    echo "<br>";
	    $row++;
	
	}
	
	$row = 8;
	while ($row >= 1) {
	   
	    $column = 1;
	    
	    while ($column <= $row) {
	     
	        echo " * ";
	        $column++;
	   
	    }
	   
	    echo "<br>";
	    $row--;
	
	}
	
	?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "footer.php"; ?>
	
	</div>
			
			
	</body>

</html>