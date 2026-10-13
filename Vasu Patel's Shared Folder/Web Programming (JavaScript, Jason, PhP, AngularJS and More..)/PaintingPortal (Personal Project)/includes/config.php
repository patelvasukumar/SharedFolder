<?php

$host = "localhost";
$username = "portaluser";
$password = "portal@user";
$database = "paintingportal";

try {
    $pdo = new PDO("mysql:host=$host;dbname=$database", $username, $password);    
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);   
}

catch(PDOException $e) {
    echo "Connection failed:  " . $e->getMessage();
}
?>