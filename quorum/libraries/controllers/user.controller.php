<?php
	require("../models/user.model.php");

	function controllerRegister() {
		$email = $_POST['registration-email'];
		$username = $_POST['registration-username'];
		$password = $_POST['registration-password'];
		$google_id = $_POST['registration-identity'];
		$user = new User($email, $username, $password, $google_id);
		
		if ($user->insert() == true) {
			try {
				$user->createCookies();
				print true;
			}
			catch (Exception $ex) {
				print false;
			}
		}
		else {
			print false;
		}
	}

	function controllerLogin() {
		$username = $_POST['login-username'];
		$password = $_POST['login-password'];

		$user = new User(null, $username, $password, null);
		
		if ($user->checkLogin() == true) {
			try {
				$user->createCookies();
				print true;
			}
			catch (Exception $ex) {
				print false;
			}
		}
		else {
			print false;
		}
	}

	function controllerSignOut() {
		header("Location: " . $_GET['url']);

		ob_start();
		$timeUntilExpire = time() - 3600; // expire an hour in the past
		setcookie( "username", "", $timeUntilExpire, '/');
		setcookie( "password", "", $timeUntilExpire, '/');
		ob_end_flush();
	}

	function router($action) {
		switch ($action) {
			case "login": controllerLogin(); break;
			case "register": controllerRegister(); break;
			case "signout": controllerSignOut(); break;
		}
	}

	router($_GET['action']);
?>