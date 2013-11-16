<?php 
require_once("static/templates/pageheader.template.php"); 
?>
	<div class="hero-unit">
		<div class="hero-unit-container">
			<h1>Experimental Quorum Libraries</h1>
			<p>These libraries have been submitted by QuorumLanguage.com users and are awaiting approval into the Quorum Standard Library. Feel free to download the libraries and give them a try for yourself!</p>
	</div>
</div>

<?php
$user_type="guest";
require_once("static/templates/showlibraries.template.php"); 
require_once("static/templates/pagefooter.template.php"); 

?>