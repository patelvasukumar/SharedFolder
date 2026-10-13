<?php
    define('onlyData', true);
    include 'index.php';

    if (isset($_GET['id'])) {
        $artistID = $_GET['id'];
    } else {
        $artistID = '';
    }

    $artist = null;
    foreach ($arrayArtist as $a) {
        if ($a[0] === $artistID) {
            $artist = $a;
            break;
        }
    }
    
    /* https://www.php.net/manual/en/function.list.php#:~:text=list()%20is%20used%20to,can%20not%20be%20completely%20empty. */

    list($id, $name, $dob, $country, $dobDetails, $bio, $wiki) = $artist;

    $artistPaintings = [];
    foreach ($arrayPaint as $p) {
        if ($p[1] === $name) {
            $artistPaintings[] = $p;
        }
    }

    $imagePath = "resources/artists/large/{$artistID}.jpg";
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Assign2 - Single Artist</title>
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
                echo "<img src='$imagePath'/>";
                echo "<h2>$name</h2>";
                echo "<p><i>$country - $dobDetails</i></p>";
                echo "<p><i>($dob)</i></p>";
                echo "<p>$bio</p>";
                echo "<p><a href='$wiki'>$name on Wikipedia</a></p>";

                echo "<h3>Paintings</h3>";
                echo "<ul>";
                foreach ($artistPaintings as $paint) {
                    echo "<li><a href='singlePainting.php?id={$paint[0]}'>{$paint[2]}</a></li>";
                }
                echo "</ul>";
            ?>
        </div>
    </div>
</body>
</html>
