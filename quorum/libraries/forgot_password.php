<?php
require_once("config.php");

// The user must provide a username via POST.
if (!array_key_exists("username", $_POST)) {
	deny();
}

$username = $_POST['username'];

// The user has 24 hours to reset their password.
$reset_time = time() + $expire_time; // 86400 s = 1 day
$reset_key = sha1($reset_time); // sequence represents encoded expire time

// Attempt to authenticate the user.
$database = new database();
$mysql = new PDO('mysql:dbname=' . $database->name . ';host=' . $database->host . ';charset=utf8', $database->username, $database->password);
$query = $mysql->prepare('UPDATE sodbeans_users SET reset_password_key=?, reset_password_expire=FROM_UNIXTIME(?) WHERE username=?');

$success = $query->execute(array($reset_key, $reset_time, $username));

if ($query->rowCount() > 0) {
	// Send email
	email($username, $reset_key, $url_prefix);
	success();
} else {
	deny();
}

function email($username, $reset_key, $url_prefix) {
	$database = new database();
	$mysql = new PDO('mysql:dbname=' . $database->name . ';host=' . $database->host . ';charset=utf8', $database->username, $database->password);
	$query = $mysql->prepare('SELECT email FROM sodbeans_users WHERE username=?');
	$executed = $query->execute(array($username));
	$result = $query->fetchAll();
	$email = $result[0][0];

    $headers = "To: " . $email . "\r\n" .
    		   "From: noreply@quorumlanguage.com\r\n" .
               "X-Mailer: Sodbeans Login Service\r\n";
	$message  = "Hello " . $email . ",\n\n";
	$message .= "You are receiving this email because you (or someone else) requested that your password be reset. If you initiated this request, please click on the link below to reset your password. If you did not initiate this request, you can simply ignore this email and nothing will happen.\n\n";
	$message .= "Please click on the link below within 24 hours:\n";
	$message .= domainRoot() . "reset_password.php?key=" . $reset_key . "&username=" . $username . "\n\n"; 
	$message .= "All the best,\n";
	$message .= "The Sodbeans Team";
	//print $message . "\n";
    mail($username, "Forgotten Password", $message, $headers);

}

function success() {
	// Just print a success message.
	require_once("static/templates/pageheader.template.php");
	print '<div class="content forgot-password-content"><h1>Success!</h1>Password reset email sent successfully.</div>';
	require_once("static/templates/pagefooter.template.php");
	exit(0);
}

function deny() {
	header('HTTP/1.1 401 Unauthorized');
	exit(0);
}

?>
