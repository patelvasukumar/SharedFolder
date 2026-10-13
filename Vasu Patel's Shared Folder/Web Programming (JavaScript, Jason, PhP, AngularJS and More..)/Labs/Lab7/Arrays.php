<!DOCTYPE html>

<html>

	<head>
	
		<link href="StyleSheet.css" rel="StyleSheet"> 

	</head>
	
	<body>
	
	<div id='header'>
	
		<?php include "Header.php"; ?>
	
	</div>
	
	<div id="menu" >
	
		<?php include "Menu.php"; ?>
	
	</div>
	
	<div id="content" >
	<?php 	

	/* ref: 1. https://www.php.net/manual/en/function.var-dump.php
	 * 2. https://www.php.net/manual/en/function.gettype.php */
	
	$noKeyArray = array(10,20,30,40);
	echo var_dump($noKeyArray);
	
	echo "<br><br>";
	
	for($i = 0; $i < count($noKeyArray); $i++){
	echo "key: $i, value: ${noKeyArray[$i]}, key data type: " . gettype($i) . "<br>";
	} /*Q1: if I wirte + instead of . , it displays zero, not an error.*/
	
	echo "<br>";
	
	$stringKeyArray = array("Key1" => "item1", "Key2" => "item2");
	var_dump($stringKeyArray);
	/* does not make an error even if you use echo or not */
	
	echo "<br>";
	
	/* https://www.php.net/manual/en/function.array-keys.php */
	
	$stringKeyArray = array("Key1" => "item1", "Key2" => "item2");
    $keys = array_keys($stringKeyArray);
	
    echo "<br>";
    
    for ($i = 0; $i < count($keys); $i++) {
        echo "key: {$keys[$i]}, value: {$stringKeyArray[$keys[$i]]}, key data type: " . gettype($keys[$i]) . "<br>";
    }
	
    echo "<br>";
    
    $intKeyArray = array(0 => "item1", 1 => "item2", 3 => "item3");
    var_dump($intKeyArray);
    
    echo "<br><br>";
    
    $keys = array_keys($intKeyArray);
    
    for($i = 0; $i < count($intKeyArray); $i++){
        echo "key: {$keys[$i]}, value: {$intKeyArray[$keys[$i]]}, key data type: " . gettype($keys[$i]) . "<br>";
    }
    
    echo "<br>";
    
    $mixedKeyArray = array("Key1" => "item1", "Key2" => "item2", 2 => "item8", 4 => "item4", 5 => "item5", 3 => "item6", 1 => "item7");
    var_dump($mixedKeyArray);
	
    echo "<br><br>";
    
    $keys = array_keys($mixedKeyArray);
    
    for($i = 0;  $i < count($mixedKeyArray); $i++){
        echo "key: {$keys[$i]}, value: {$mixedKeyArray[$keys[$i]]}, key data type: " . gettype($keys[$i]) . "<br>";
    }
    
    echo "<br>";
    
    $multiKeyArray = array(
        array(10, 20),
        array(30, 40)
    );
    
    var_dump($multiKeyArray);
    
    echo "<br><br>";
    
    for ($i = 0; $i < count($multiKeyArray); $i++) {
        
        $keys = array_keys($multiKeyArray[$i]);
        
        echo "Level - 1 Key : $keys[$i] <br>";
        
        for ($j = 0; $j < count($keys); $j++) {
            $key = $keys[$j];
            $value = $multiKeyArray[$i][$key];
            echo "Level - 2 key: $key, value: $value, data type: " . gettype($value) . "<br>";
        }
        
        echo "<br>";
    }
    
    
	?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "Footer.php"; ?>
	
	</div>
			
			
	</body>

</html>