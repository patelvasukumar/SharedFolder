<!-- general reference for clearity : https://www.w3schools.com/php/php_form_complete.asp  --> 

<!DOCTYPE html>

<html>

<head>
    <link href="StyleSheet.css" rel="StyleSheet">
</head>

<body>

<div id="header">
    <?php include "Header.php"; ?>
</div>

<div id="menu">
    <?php include "Menu.php"; ?>
</div>

<div id="content">
    
    <form action="Calculator.php" method="POST">

        <input type="text" name="firstBox"> 
        <select name="secondBox" id="operations">
            <option value="plus"> + </option>
            <option value="minus"> - </option>
            <option value="multi"> x </option>
            <option value="devide"> / </option>
            <option value="exp"> exp </option>
        </select> 
        <br>
        <input type="text" name="thirdBox">

        <b><sub>=</sub></b>
        <input type="submit" value="Calculate">

        <?php 
        
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
           
            $operation = $_POST['secondBox'];
            $a = (int) $_POST['firstBox'];
            $b = (int) $_POST['thirdBox'];
            $result = 0;

            switch ($operation) {
                
                case 'plus':
                   
                    $result = $a + $b;
                    break;
               
                case 'minus':
                   
                    $result = $a - $b;
                    break;
                
                case 'multi':
                   
                    $result = $a * $b;
                    break;
                
                case 'devide':
                   
                    if ($b == 0) {
                        $result = 'Error: division by zero';
                    } else {
                        $result = $a / $b;
                    }
                    break;
               
                case 'exp':
                    $result = pow($a, $b);
                    break;
            }

            echo $result;
            
            echo "<br><br>";
            
            function primeNumber($number){
                
                if($number <= 1){
                    return false;
                }
                
                for($index = 2; $index < $number; $index++){
                    
                    if($number % $index == 0){
                        return false;
                    }
                    
                    return true;
                      
                }
                
            }
            
            if( primeNumber($result) ){
                echo "$result is a prime number <br> ";
            } else {
                echo "$result is a not prime number <br> ";
            }
            
            if(($result % 2) == 0){
                echo "$result is an even number <br> ";
            } else {
                echo "$result is an odd number <br> ";
            }
       
        }
        
        ?>
   
    </form>

</div>

<div id="footer">
    <?php include "Footer.php"; ?>
</div>

</body>

</html>
