<div id="genreListing">
	
	<h2>Genres</h2>	
	
	<ul class="secondaryList">
	  
	    <?php
            
	       $genres = array();
           foreach ($arrayPaint as $paint) { $genres[] = $paint[9]; }

           $uniqueGenres = array_unique($genres);  
           sort($uniqueGenres); 

            foreach ($uniqueGenres as $genre) {
                      
            echo "<li><a href='index.php?genre={$genre}'>$genre</a></li>";
            
            }
        
        ?>
	  
	</ul>

</div>

<div id="artistListing">
	
	<h2>Artists</h2>
	
	<ul class="secondaryList">
		
		<?php
        
        foreach ($arrayArtist as $artist) {
            
            $artistID = $artist[0];
            $artistName = $artist[1];
            echo "<li><a href='singleArtist.php?id=$artistID'>$artistName</a></li>";
        
        }
        
        ?>
		
	</ul>

</div>	