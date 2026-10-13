

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
session_start();

if (isset($_SESSION["firstName"])) {
    echo "First Name : " . $_SESSION["firstName"] . "<br>";
}

if (isset($_SESSION["LastName"])) {
    echo "Last Name : " . $_SESSION["LastName"] . "<br>";
}

if (isset($_SESSION["Phone"])) {
    echo "Phone : " . $_SESSION["Phone"] . "<br>";
}

if (isset($_SESSION["Role"])) {
    echo "Role : " . $_SESSION["Role"] . "<br>";
}

if (isset($_SESSION["Game"])) {
    echo "Game : " . $_SESSION["Game"] . "<br>";
}
?>





	</div>
			
	<div id="footer" >
	
	<?php include "footer.php"; ?>
	
	</div>
	
	
			
			
	</body>

</html>