<?php

session_start();

    if (empty($_SESSION['email'])) {
        header('Location: Login.php');
        exit;
    }

    $host     = "localhost";
    $database = "cst8238";
    $username = "cst8238";
    $password = "cst@8238";

    $pdo = new PDO("mysql:host=$host;dbname=$database", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $string      = $pdo->query("SELECT * FROM employee");
    $employees = $string->fetchAll(PDO::FETCH_ASSOC);

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
        
        <h1>Session State Data</h1>
        <p>
            First Name: <?php echo $_SESSION["firstName"]; ?><br/>
            Last Name:  <?php echo $_SESSION["lastName"];  ?><br/>
            Email:      <?php echo $_SESSION["email"];     ?><br/>
            Phone:      <?php echo $_SESSION["phone"];     ?><br/>
            SIN:        <?php echo $_SESSION["sin"];       ?><br/>
            Password:   <?php echo $_SESSION["password"];  ?>
        </p>

        <h1>Database Data</h1>
        
        <?php if (empty($employees)): ?>
    
            <p>No employees found.</p>
        
        <?php else: ?>
            
        <table
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>SIN</th>
                        <th>Password</th>
                    </tr>
            
                    <?php foreach ($employees as $row): ?>
                        <tr>
                            <td> <?php echo $row["FirstName"];             ?></td>
                            <td> <?php echo $row["LastName"];              ?></td>
                            <td> <?php echo $row["EmailAddress"];          ?></td>
                            <td> <?php echo $row["TelephoneNumber"];       ?></td>
                            <td> <?php echo $row["SocialInsuranceNumber"]; ?></td>
                            <td> <?php echo $row["Password"];              ?></td>
                        </tr>
                    <?php endforeach; ?>
            
        </table>
        <?php endif; ?>
        
    </div>
    
    <div id="footer">
        <?php include "footer.php"; ?>
    </div>
    
</body>
</html>