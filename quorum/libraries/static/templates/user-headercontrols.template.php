<?php 
	if ((function_exists('getRoot') == false)) {
		function getRoot() { 
			return "http" . (($_SERVER['HTTPS'] == "on") ? "s://" : "://") . $_SERVER["SERVER_NAME"] . "/"; 
		}

		function getPage() { 
			return substr($_SERVER["REQUEST_URI"], 1);
		}
	}

	$username = $_COOKIE['username'];
	$url = ($_SERVER['PHP_SELF'] == "/static/templates/user-headercontrols.template.php") ? $_SERVER['PHP_SELF'] : "";

	function Truncate($string, $length, $stopanywhere=false) {
		if (strlen($string) > $length) {
			return substr($string,0,$length) . "..";
		}
		else {
			return $string;
		}
	}
?>

<div class="user-controls-loggedin">
	<h3><a href="/control_panel.php">Welcome, <u><?php print Truncate($_COOKIE['username'], 10); ?>!</u></a></h3> 
	<form action="/controllers/user.controller.php?action=signout" method="post">
		<?php 
			print '<input type="hidden" name="referer" value="' . getRoot() . getPage() . '" />'; 
			print "<input type=\"submit\" class=\"btn btn-primary\" href=\"/controllers/user.controller.php?action=signout\" value=\"Sign Out\">"; 
		?>
	</form>
</div>