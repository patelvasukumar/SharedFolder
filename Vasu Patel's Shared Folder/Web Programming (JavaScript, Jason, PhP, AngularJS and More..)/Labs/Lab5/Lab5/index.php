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
	date_default_timezone_set("America/Toronto");
	$firstName = "Vasu";
	$lastName = "Patel";
	
	echo "<br>" . $firstName . " " . $lastName;
	
	define("NUMBER","Student Number : 041153940");
	
	echo "<br><br>" . NUMBER;
	
	echo "<br><br>Hello World !! " . "This is the first time that I am using PHP !!";

    echo "<br><br> Today is " . date("d/m/Y");
    echo "<br> Today is " . date("Y/m/d");
    echo "<br> Today is " . date("D-M-y");
    /*
     * small and capital changes the output
     * reference : https://www.geeksforgeeks.org/php/php-date-time/
     */
    
    date_default_timezone_set("America/Toronto");
    echo "<br><br> The current time is " . date("h:i:s a");
    /*
     * reference : https://www.geeksforgeeks.org/php/php-date-time/
     */
    
    $date=strtotime("-1 Day");
    $lastFriday=strtotime(" last friday"); /* small and capital does not matter */
    echo "<br><br> Yesterday was " . date("Y-m-d",$date);
    echo "<br> The last friday was " . date("Y-m-d",$lastFriday);
        /*
         * full name : strings to a timestamp
         * reference 1st : https://www.geeksforgeeks.org/php/php-date-time/
         * 2nd : https://www.php.net/manual/en/function.strtotime.php
         * 
         */
    
    echo "<br><br> Minimux value of (0,150,-50,100,-150) is " . max(0,150,-50,100,-150);
    echo "<br> Maximum value of (0,150,-50,100,-150) is " . min(0,150,-50,100,-150);
    
    $circleRadius = pi() * pow(5, 2);
    
    echo "<br><br> The area of the circle (5 cm) is: " . number_format($circleRadius,2,'.','');
    echo "<br><br>";
    /*
     * reference : https://www.php.net/manual/en/function.number-format.php
     */
    
	?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "footer.php"; ?>
	
	</div>
			
			
	</body>

</html>