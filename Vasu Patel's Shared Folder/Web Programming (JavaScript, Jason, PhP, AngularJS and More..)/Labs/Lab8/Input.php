<?php 
    
    $firstName = '';

    if (isset($_POST["firstNameTextBox"])) {
        $firstName = $_POST["firstNameTextBox"];
    }
    
    $LastName = '';
    
    if (isset($_POST["LastNameTextBox"])) {
        $LastName = $_POST["LastNameTextBox"];
    }
    
    $Phone = '';
    
    if (isset($_POST["PhoneTextBox"])) {
        $Phone = $_POST["PhoneTextBox"];
    }
    
    $Role = '';
    if (isset($_POST["RoleRadio"])) {
        $Role = $_POST["RoleRadio"];
    }
    
    $Game = '';
    if (isset($_POST["GameSelect"])) {
        $Game = $_POST["GameSelect"];
    }
    
    
?>

<!DOCTYPE html>

<html>

<head>
    <link href="StyleSheet.css" rel="stylesheet">
</head>

<body>

    <div id="header">
        <?php include "header.php"; ?>
    </div>

    <div id="menu">
        <?php include "menu.php"; ?>
    </div>

    <div id="content">
        <?php 
       
        echo '<form method="post" action="">';
        echo '<table style="width:100%">';
       
        echo '  <tr>';
        
        echo '    <td style="text-align:left">';
        echo '      First Name <input type="text" name="firstNameTextBox" value="' . $firstName . '" />';
        echo '    </td>';
        echo '    <td style="text-align:left">';
        echo '      First Name: ' . $firstName;
        echo '    </td>';
        
        echo '  </tr>';
        
        echo '  <tr>';
        
        echo '    <td style="text-align:left">';
        echo '     Last Name <input type="text" name="LastNameTextBox" value="' . $LastName . '" />';
        echo '    </td>';
        echo '    <td style="text-align:left">';
        echo '      Last Name: ' . $LastName;
        echo '    </td>';
        
        echo '  </tr>';
        
        echo '  <tr>';
        
        echo '    <td style="text-align:left">';
        echo '     Phone Number <input type="text" name="PhoneTextBox" value="' . $Phone . '" />';
        echo '    </td>';
        echo '    <td style="text-align:left">';
        echo '      Phone Number: ' . $Phone;
        echo '    </td>';
        
        echo '  </tr>';
        
        echo '  <tr>';
        echo '    <td style="text-align:left">';
        echo '      Role:<br />';
        echo '      <label><input type="radio" name="RoleRadio" value="Staff"> Staff</label> ';
        echo '      <label><input type="radio" name="RoleRadio" value="Student"> Student</label> ';
        echo '      <label><input type="radio" name="RoleRadio" value="Faculty"> Faculty</label>';
        echo '    </td>';
        echo '    <td style="text-align:left">';
        echo '      Selected Role: ' . $Role;
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
        echo '    <td style="text-align:left">Selected Game: ' . $Game . '</td>';
        echo '  </tr>';
        
        
        echo '</table>';
        echo '<input type="submit" value="Submit" />';
        echo '</form>';
        
        ?>
    
    </div>

    <div id="footer">
        <?php include "footer.php"; ?>
    </div>

</body>

</html>
