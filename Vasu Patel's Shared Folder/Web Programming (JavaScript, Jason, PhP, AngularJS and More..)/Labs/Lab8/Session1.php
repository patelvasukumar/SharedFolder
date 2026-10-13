<?php
session_start();

$firstName = '';
$LastName  = '';
$Phone     = '';
$Role      = '';
$Game      = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    if (isset($_POST["firstNameTextBox"])) {
        $_SESSION["firstName"] = $_POST["firstNameTextBox"];
        $firstName = $_POST["firstNameTextBox"];
    }

    if (isset($_POST["LastNameTextBox"])) {
        $_SESSION["LastName"] = $_POST["LastNameTextBox"];
        $LastName = $_POST["LastNameTextBox"];
    }

    if (isset($_POST["PhoneTextBox"])) {
        $_SESSION["Phone"] = $_POST["PhoneTextBox"];
        $Phone = $_POST["PhoneTextBox"];
    }

    if (isset($_POST["RoleRadio"])) {
        $_SESSION["Role"] = $_POST["RoleRadio"];
        $Role = $_POST["RoleRadio"];
    }

    if (isset($_POST["GameSelect"])) {
        $_SESSION["Game"] = $_POST["GameSelect"];
        $Game = $_POST["GameSelect"];
    }

    header("Location: Session2.php");
    exit;
}

if (isset($_SESSION["firstName"])) {
    $firstName = $_SESSION["firstName"];
}
if (isset($_SESSION["LastName"])) {
    $LastName = $_SESSION["LastName"];
}
if (isset($_SESSION["Phone"])) {
    $Phone = $_SESSION["Phone"];
}
if (isset($_SESSION["Role"])) {
    $Role = $_SESSION["Role"];
}
if (isset($_SESSION["Game"])) {
    $Game = $_SESSION["Game"];
}
?>

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
       
        echo '<form method="post" action="">';
        echo '<table style="width:100%">';
       
        echo '  <tr>';
        
        echo '    <td style="text-align:left">';
        echo '      First Name <input type="text" name="firstNameTextBox" value="' . $firstName . '" />';
        echo '    </td>';
        
        echo '  </tr>';
        
        echo '  <tr>';
        
        echo '    <td style="text-align:left">';
        echo '     Last Name <input type="text" name="LastNameTextBox" value="' . $LastName . '" />';
        echo '    </td>';
        
        echo '  </tr>';
        
        echo '  <tr>';
        
        echo '    <td style="text-align:left">';
        echo '     Phone Number <input type="text" name="PhoneTextBox" value="' . $Phone . '" />';
        echo '    </td>';

        echo '  </tr>';
        
        echo '  <tr>';
        echo '    <td style="text-align:left">';
        echo '      Role:<br />';
        echo '      <label><input type="radio" name="RoleRadio" value="Staff"> Staff</label> ';
        echo '      <label><input type="radio" name="RoleRadio" value="Student"> Student</label> ';
        echo '      <label><input type="radio" name="RoleRadio" value="Faculty"> Faculty</label>';
        echo '    </td>';
 
        echo '  </tr>';
        
        echo '  <tr>';
        echo '    <td style="text-align:left">';
        echo '      Game: ';
        echo '      <select name="GameSelect">';
        echo '        <option value="Hockey">Hockey</option>';
        echo '        <option value="Basketball">Basketball</option>';
        echo '        <option value="Soccer">Soccer</option>';
        echo '        <option value="Tennis">Tennis</option>';
        echo '      </select>';
        echo '    </td>';
     
        echo '  </tr>';
        
        
        echo '</table>';
        echo '<input type="submit" value="Submit" />';
        echo '</form>';
        
        ?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "footer.php"; ?>
	
	</div>
			
			
	</body>

</html>