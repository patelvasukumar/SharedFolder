<!DOCTYPE html>
<html>
<head>
    <link href="StyleSheet.css" rel="stylesheet">
</head>
<body>

    <div id="content">

	<?php
    
	
    $xml = file_get_contents("Books.xml");
    $xmlDoc = new DOMDocument();
    $xmlDoc->loadXML($xml);
    $books = $xmlDoc->getElementsByTagName('book');
    $query = $_GET["q"];

    for ($i = 0; $i < $books->length; $i++) {
        
        $book = $books->item($i);

        for ($j = 0; $j < $book->childNodes->length; $j++) {
        
             $object = $book->childNodes->item($j);

                if ($object->nodeName === "genre" && $object->nodeValue === $query) {
                
                    $fourX = $object->parentNode;

                        for ($yIndex = 0; $yIndex < $fourX->childNodes->length; $yIndex++) {
                        
                        $object = $fourX->childNodes->item($yIndex);
                        $name = $object->nodeName;

                    
                            switch ($name) {
                    
                            case "author":
                            case "title":
                            case "genre":
                            case "price":
                            case "publish_date":
                            case "description":
                            echo "<b>$name:</b> " . $object->nodeValue . "<br>";
                            break;
                
                            }
            
                        }

                        echo "<br><br>";
                }
        }
     }

    ?>

    </div>

</body>
</html>