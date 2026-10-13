 <?php

session_start();

if ($_SERVER["REQUEST_METHOD"] === "POST") {

    $host     = "localhost";
    $database = "cst8238";
    $username = "cst8238";
    $password = "cst@8238";
      
    $pdo = new PDO("mysql:host=$host;dbname=$database", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $sqlQuery = "INSERT INTO employee (firstname, lastname, emailaddress, telephonenumber, socialinsurancenumber, password)
                 VALUES ('".$_POST["firstName"]."', '".$_POST["lastName"]."', '".$_POST["email"]."', '".$_POST["phone"]."',
                         '".$_POST["sin"]."','".$_POST["password"]."')";

    $pdo->query($sqlQuery);
    $pdo = null;

    $_SESSION["firstName"] = $_POST["firstName"];
    $_SESSION["lastName"]  = $_POST["lastName"];
    $_SESSION["email"]     = $_POST["email"];
    $_SESSION["phone"]     = $_POST["phone"];
    $_SESSION["sin"]       = $_POST["sin"];
    $_SESSION["password"]  = $_POST["password"];

    header("Location: ViewAllEmployees.php");
    exit;
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

        <p><b>Create your new account</b></p>

        <form method="post">
            First Name:    <input type="text" name="firstName" /> <br/>
            Last Name:     <input type="text" name="lastName"  /> <br/>
            Email Address: <input type="text" name="email"     /> <br/>
            Phone Number:  <input type="text" name="phone"     /> <br/>
            SIN:           <input type="text" name="sin"       /> <br/>
            Password:      <input type="text" name="password"  /> <br/>
            <input type="submit" value="Submit Information" />
        </form>

    </div>

    <div id="footer">
        <?php include "footer.php"; ?>
    </div>

</body>
</html>