<?php
require_once("config.php");

// The user must provide a username and password via POST.
if (!array_key_exists("username", $_GET) || !array_key_exists("key", $_GET)) {
	deny();
}
$username = $_GET['username'];
$key = $_GET['key'];

// See if the username and key together are valid.
$database = new database();
$mysql = new PDO('mysql:dbname=' . $database->name . ';host=' . $database->host . ';charset=utf8', $database->username, $database->password);
$query = $mysql->prepare('SELECT username FROM sodbeans_users WHERE username=? AND reset_password_key=? AND reset_password_expire > CURDATE()');
$success = $query->execute(array($username, $key));
$result = $query->fetchAll();

// Did we find it?
if (count($result) != 1) {
	// No. deny access
	deny();
} else {
	// Yes. Should we show the form, or be looking for changes?
	if (array_key_exists('post', $_GET)) {
		setPassword($username, $key, $mysql);
	} else {
		showForm($username, $key);
	}
}

function setPassword($username, $key, $mysql) {
	// Generate salted password
	if (!array_key_exists('password', $_POST)) {
		deny();
	}
	$salted_password = saltPassword($_POST['password']);
	$query = $mysql->prepare('UPDATE sodbeans_users SET password=SHA1(?), reset_password_key=NULL, reset_password_expire=NULL WHERE  username=? AND reset_password_key=? AND reset_password_expire > CURDATE()');
	$success = $query->execute(array($salted_password, $username, $key));
	if ($query->rowCount() > 0) {
		passwordChanged();
	} else {
		deny();
	}		
}

function passwordChanged() {
	require_once("static/templates/pageheader.template.php");
	print '<div class="content forgot-password-content"><h1>Password reset!</h1><p>Your password has been successfully reset. You may now log in to Sodbeans.</p></div>';
	require_once("static/templates/pagefooter.template.php");
}

function showForm($username, $key) {
	require_once("static/templates/pageheader.template.php");
		print '<div class="content forgot-password-content">';
        print "<h1>Reset your Password</h1>";
        print '<form method="POST" class="reset-password-form" action="reset_password.php?post=true&username=' . $username . '&key=' . $key .' ">';
?>
        <p><strong>Enter the new password for <b><? $username ?></b> below.</strong></p>
        <p>Password:<input name="password" type="password" /></p>
		<p><input type="submit" class="btn btn-primary" value="Reset password" /></p>
        </form>
       	</div>
<?php
	require_once("static/templates/pagefooter.template.php");
}

function deny() {

	header('HTTP/1.1 401 Unauthorized');
	print "Unauthorized. More than likely, the link you clicked on has expired. Please reset your password again.";
	exit(0);
}

?>
