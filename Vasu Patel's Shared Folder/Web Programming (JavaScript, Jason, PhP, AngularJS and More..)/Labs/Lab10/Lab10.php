<!DOCTYPE html>
<html>
<head>
    
    <link href="StyleSheet.css" rel="stylesheet">
    
    <script>
    
    function loadBooks(str) {
  
  			if (str == "") {
    		
    			document.getElementById("bookList").innerHTML = "";
  			
  			} else {
    
    			xmlhttp = new XMLHttpRequest();

				xmlhttp.onreadystatechange = function() {
      
      			if (this.readyState == 4 && this.status == 200) {
        			document.getElementById("bookList").innerHTML = this.responseText;
      			}
    		}
    		
    		xmlhttp.open("GET", "Books.php?q=" + str, true);
    		xmlhttp.send();
  			
  			}
	}
    
	</script>
    
</head>

<body>

    <div id="header">
        <?php include "header.php"; ?>
    </div>

    <div id="menu">
        <?php include "menu.php"; ?>
    </div>

    <div id="content">

	<form>
    		<label>Select Book Category:</label>
    		
    		<select name="books" onchange="loadBooks(this.value)">
        	
        		<option value="">Select Book Category</option>
        		<option value="Computer">Computer</option>
        		<option value="Fantasy">Fantasy</option>
        		<option value="Romance">Romance</option>
        		<option value="Horror">Horror</option>
        		<option value="Science Fiction">Science Fiction</option>
    
    		</select>
	
	</form>
	
	<p id="bookList"> <b> Books of same category will be listed here </b> </p>

    </div>



    <div id="footer">
        <?php include "footer.php"; ?>
    </div>

</body>
</html>