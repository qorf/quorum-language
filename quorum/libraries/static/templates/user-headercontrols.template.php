<?php 
	$username = $_COOKIE['username'];
	$url = $_SERVER['PHP_SELF'];
	$signOutLink = "<a href=\"/controllers/user.controller.php?action=signout&url=" . $url . "\">Sign Out</a>"; 
?>

<div class="user-controls-loggedin">
	<h3>Welcome, <?php print $_COOKIE['username']; ?>!</h3> 
	<h4><?php print $signOutLink; ?></h4>
</div>