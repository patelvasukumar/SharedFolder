<?php 

/* https://www.php.net/manual/en/function.explode.php */

$paintings = file("resources\paintings.txt") or die('ERROR: Cannot find file');
$delimiter = '~';
$arrayPaint = array();

foreach ($paintings as $painting){
    
    $paintingFields = explode($delimiter,trim($painting));
    
    if (count($paintingFields) === 10) {
        
        $idP = $paintingFields[0];
        $naP = $paintingFields[1];  
        $tiP = $paintingFields[2];
        $yeP = $paintingFields[3];
        $wiP = $paintingFields[4];
        $heP = $paintingFields[5];
        $prP = $paintingFields[6];
        $deP = $paintingFields[7];
        $wikiP = $paintingFields[8];
        $geP = $paintingFields[9];
        
        
        $arrayPaint[] = array($idP,$naP,$tiP,$yeP,$wiP,$heP,$prP,$deP,$wikiP,$geP);
       
        
    }
    
}

$artists = file("resources\artists.txt") or die('ERROR: Cannot find file');
$delimiter = '~';
$arrayArtist = array();
$artistIdMap = array();

foreach ($artists as $artist){
    
    $artistFields = explode($delimiter,trim($artist));
    
    if (count($artistFields) === 7) {
        
        $idA = $artistFields[0];
        $naA = $artistFields[1];
        $naiA = $artistFields[2];
        $biA = $artistFields[3];
        $deA = $artistFields[4];
        $desA = $artistFields[5];
        $wikiA = $artistFields[6];
        
        $arrayArtist[] = array($idA,$naA,$naiA,$biA,$deA,$desA,$wikiA);
        
        
    }
    
}

    if (defined('onlyData')) { return; }
    

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>

<title>Assign2-Home Page of Art Gallery</title>

<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/text.css" />
<link rel="stylesheet" href="css/960.css" />
<link rel="stylesheet" href="css/assign2.css" />

</head>

<body>

	<div class="container_12">
	
	
		<?php require_once("utilities/header.php"); ?>
	
	
	<div class="grid_3">
	
		<?php require_once("utilities/navigation.php");  ?>
		
	</div>
	
	
	<div class="grid_9">
	
	
	<?php
	
	$selectedGenre = null;
	if (isset($_GET['genre'])) {
	    $selectedGenre = $_GET['genre'];
	}
	  
	foreach ($arrayPaint as $paint) { 
	    
	    if ($selectedGenre && $paint[9] !== $selectedGenre) {
	        continue; 
	    }
	    
	    $paintingID = $paint[0];
	    $artistName = $paint[1];
	    $title      = $paint[2];
	    $imagePath  = "resources/paintings/large/{$paintingID}.jpg";
	    
	    foreach ($arrayArtist as $artist) {
	        if ($artist[1] === $artistName) {
	            $artistID = $artist[0];
	        }
	    }
	    
	    
	    echo "<table style='width: 100%; margin-bottom: 10px;'>";
	    echo "<tr>";
	    echo "<td><img src='$imagePath' alt='Painting' width='100' /></td>";
	    echo "<td style='text-align: center;'>";
	    echo "<a href='singlePainting.php?id={$paintingID}'>$title</a>";
	    echo "</td>";
	    echo "<td style='text-align: right;'> <a href='singleArtist.php?id=$artistID'>$artistName</a> </td>";
	    echo "</tr>";
	    echo "</table>";
	}
	
    /* How to send data through query string
     *  reference: https://stackoverflow.com/questions/34158292/how-to-send-data-through-anchor-tag-in-php/34158354 */     
        
    ?>
	
	
	</div>
	 
	<div class="clear"> 
	</div>

	</div>

</body>

</html>