<?php
	require_once("data.model.php");
	require_once("../config.php");

	class User extends QuorumDataModel {
		private $table_name = "sodbeans_users";
		public $email = "";
		public $username = "";
		public $password = "";
		public $google_id = "";
		private $ip;

		function __construct($email, $username, $password, $google_id) {
			parent::__construct();

			if ($password == null || $password == "") {
				$password = sha1(secretGooglePassword());
			}

			$this->email = $email;
			$this->username = $username;
			$this->password = sha1(saltPassword($password));
			$this->google_id = $google_id;
			$this->ip = $_SERVER['SERVER_ADDR'];
		}

		public function insert() {
			$sqlQuery = "INSERT INTO " . $this->table_name . " (email, username, password, reset_password_key, reset_password_expire, google_id) VALUES (?, ?, ?, NULL, NULL, ?)";

			$valuesToPrepare = array($this->email, $this->username, $this->password, $this->google_id);

			try {
				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);
				return true;
			}
			catch (Exception $ex) {
				return false;
			}

			return false;
		}

		public function checkLogin() {
			if ($google_id == null) { // Regular user
				//var_dump($this);
				$sqlQuery = "SELECT * FROM " . $this->table_name . " WHERE username=? AND password=? AND google_id IS NULL";
				$valuesToPrepare = array($this->username, $this->password);
			}
			else { // Google user
				$sqlQuery = "SELECT * FROM " . $this->table_name . " WHERE username=? AND google_id=?";
				$valuesToPrepare = array($this->username, $this->google_id);
			}

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			return ($preparedStatement->rowCount() == 1) ? true : false; // if we have one row, we are logged in.
		}

		public function createCookies() {
			ob_start();
			$timeUntilExpire = time()+(60*60*24*30); // one month
			setcookie( "username", $this->username, $timeUntilExpire, '/');
			setcookie( "password", $this->password, $timeUntilExpire, '/');
			ob_end_flush();
		}
	}
?>