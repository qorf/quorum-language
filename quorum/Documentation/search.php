<?php
	$query = urlencode("site:quorumlanguage.com " . $_POST['search-query']);
	header( 'Location: https://www.google.com/search?q=' . $query ) ;
?>