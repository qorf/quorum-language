<?php
	require("../models/user.model.php");

	function register() {
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

	function login() {
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

	function signOut() {
		header("Location: " . $_GET['url']);

		ob_start();
		$timeUntilExpire = time() - 3600; // expire an hour in the past
		setcookie( "username", "", $timeUntilExpire, '/');
		setcookie( "password", "", $timeUntilExpire, '/');
		ob_end_flush();
	}

	function router($action) {
		switch ($action) {
			case "login": login(); break;
			case "register": register(); break;
			case "signout": signOut(); break;
		}
	}

	router($_GET['action']);
?>