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
	
	$total = 0;
	$index = 0;  
	$num1 = 0;
	$num2 = 0;
	$num3 = 0;
	$num4 = 0;
	$num5 = 0;
	
	while($index < 500){
	   
	    $number = rand(1,50);
	    
	           if (1 <= $number && $number <= 10){
	  
	               $num1++;
	       
	           } else if (11 <= $number && $number <= 20){
	               
	               $num2++;
	    
	           } else if (21 <= $number && $number <= 30){
	    
	               $num3++;
	    
	           } else if (31 <= $number && $number <= 40){
	    
	               $num4++;
	    
	           } else if (41 <= $number && $number <= 50){
	    
	               $num5++;
	        
	           } 
	
	   $total = $total + $number;
	   $index++;     
	
    }
    
    echo "<br> $num1 random number between 1 to 10 <br>";
    echo " $num2 random number between 11 to 20 <br>";
    echo " $num3 random number between 21 to 30 <br>";
    echo " $num4 random number between 31 to 40 <br>";
    echo " $num5 random number between 41 to 50 <br>";
    
    
	
	echo "<br> The total sum is : $total ";
	                   
	echo "<br><br> Histogram of stars as a percentage of the number of values are displayed below: <br>";
	
	function stars($message, $count) {
	    
	    $percent = round($count * 100 / 500);            
	   
	    echo "<br>$message: ";
	    
	    for ($i = 0; $i < $percent; $i++) { echo "*"; }
	    
	    echo "<br>";
	
	}
	
	stars("01 - 10", $num1);
	stars("11 - 20", $num2);
	stars("21 - 30", $num3);
	stars("31 - 40", $num4);
	stars("41 - 50", $num5);
	
	?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "footer.php"; ?>
	
	</div>
			
			
	</body>

</html>