<?php
session_start();

    $host     = "localhost";
    $database = "cst8238";
    $username = "cst8238";
    $password = "cst@8238";

    $error = "";

    if (isset($_POST["email"])) {
    
        $pdo = new PDO("mysql:host=$host;dbname=$database", $username, $password);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $sqlQuery = "SELECT * FROM employee WHERE emailaddress = '".$_POST["email"]."' AND password = '".$_POST["password"]."'";

        $result   = $pdo->query($sqlQuery);
        $rowCount = $result->rowCount();

    if ($rowCount > 0) {
        
        $row = $result->fetch();

        $_SESSION["firstName"] = $row["firstname"];
        $_SESSION["lastName"]  = $row["lastname"];
        $_SESSION["email"]     = $row["emailaddress"];
        $_SESSION["phone"]     = $row["telephonenumber"];
        $_SESSION["sin"]       = $row["socialinsurancenumber"];
        $_SESSION["password"]  = $row["password"];

        header("Location: ViewAllEmployees.php");
        exit;
   
    } else {
    
        $error = "You entered an invalid email or password.";
    
    }

    $pdo = null;
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

        <p><b>Login</b></p>

        <form method="post">
            Email Address: <input type="text" name="email" /> <br/>
            Password:      <input type="text" name="password" /> <br/>
            <input type="submit" value="Login" />
        </form>

        <?php if ($error !== "") { echo "<p style='color:red;'>$error</p>"; } ?>

    </div>

    <div id="footer">
        <?php include "footer.php"; ?>
    </div>

</body>
</html>