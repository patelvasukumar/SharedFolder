<?php
    
    /*https://www.php.net/manual/en/function.define.php*/


    define('onlyData', true);
    include 'index.php';
   
    if (isset($_GET['id'])) {
        $paintingID = $_GET['id'];
    } 
    
    $painting = null;
    foreach ($arrayPaint as $p) {
    
        if ($p[0] == $paintingID) {
            $painting = $p;
        }
    }

    $artistID = '';
    foreach ($arrayArtist as $a) {
    
        if ($painting[1] == $a[1]) {
            $artistID = $a[0];
        }
    }

    $imagePath = "resources/paintings/huge/$paintingID.jpg";
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>

<title>Assign2 - Single Painting</title>

<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/text.css" />
<link rel="stylesheet" href="css/960.css" />
<link rel="stylesheet" href="css/assign2.css" />

</head>   

<body>

	<div class="container_12">
		<?php require_once("utilities/header.php"); ?>
	</div>

	<div class="container_12 contentWhite">	
	
	
	<div class="grid_3">
		<?php require_once("utilities/navigation.php"); ?>		
	</div>
	
	<div class="grid_9">
		
		  <?php
            
		    echo "<img src='$imagePath' />";
            echo "<h2><strong>{$painting[2]}</strong></h2>";
            echo "<p><a href='singleArtist.php?id=$artistID'>{$painting[1]}</a></p>";
            echo "<p><i>({$painting[3]} - {$painting[4]}cm X {$painting[5]}cm)</i></p>";
            echo "<p>Price: {$painting[6]}</p>";
            echo "<p><a href='index.php?genre={$painting[9]}'>{$painting[9]}</a></p>";
            echo "<p>{$painting[7]}</p>";
            echo "<p><a href='{$painting[8]}'>{$painting[2]} on Wikipedia</a></p>";
            
           ?>	
		
					
	</div>
	
	
	</div>

</body>

</html>