<?php 
	$username = $_COOKIE['username'];
	$url = ($_SERVER['PHP_SELF'] == "/static/templates/user-headercontrols.template.php") ? $_SERVER['PHP_SELF'] : "";
	$signOutLink = "<a class=\"btn btn-primary\" href=\"/controllers/user.controller.php?action=signout\">Sign Out</a>"; 

	function Truncate($string, $length, $stopanywhere=false) {
		if (strlen($string) > $length) {
			return substr($string,0,$length) . "...";
		}
		else {
			return $string;
		}
	}
?>

<div class="user-controls-loggedin">
	<h3>Welcome, <?php print Truncate($_COOKIE['username'], 8); ?>!</h3> 
	<h4><?php print $signOutLink; ?></h4>
</div>