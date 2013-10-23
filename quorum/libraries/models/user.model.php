<?php
	require_once("data.model.php");
	require_once("config.php");

	class User extends QuorumDataModel {
		private $table_name = "sodbeans_users";
		public $email = "";
		public $username = "";
		public $password = "";
		public $google_id = "";
		public $administrator = ""; 
		public $first_name = "";
		public $last_name = "";
		private $ip;

		function __construct($email, $username, $password, $google_id, $first_name = "", $last_name = "") {
			parent::__construct();

			if ($password == null || $password == "") {
				$password = sha1(secretGooglePassword());
			}

			$this->email = $email;
			$this->username = $username;
			$this->password = sha1(saltPassword($password));
			$this->google_id = $google_id;
			$this->administrator = $administrator;
			$this->first_name = $first_name;
			$this->last_name = $last_name;
			$this->ip = $_SERVER['SERVER_ADDR'];
		}

		public function insert() {
			$sqlQuery = "INSERT INTO " . $this->table_name . " (email, username, password, reset_password_key, reset_password_expire, google_id, first_name, last_name) VALUES (?, ?, ?, NULL, NULL, ?, ?, ?)";

			$valuesToPrepare = array($this->email, $this->username, $this->password, $this->google_id, $this->first_name, $this->last_name);

			try {
				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);
				return true;
			}
			catch (Exception $ex) {
				$preparedStatement = $this->connection->prepare($sqlQuery);
				return false;
			}

			return false;
		}

		public function checkLogin() {
			if ($this->google_id == null) { // Regular user
				$sqlQuery = "SELECT * FROM " . $this->table_name . " WHERE username=? AND password=? AND google_id IS NULL";
				$valuesToPrepare = array($this->username, $this->password);
			}
			else { // Google user
				$sqlQuery = "SELECT * FROM " . $this->table_name . " WHERE email=? AND google_id=?";
				$valuesToPrepare = array($this->email, $this->google_id);
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

		public function getUsernameFromEmail() {
			$sqlQuery = "SELECT * FROM " . $this->table_name . " WHERE email=?";
			$valuesToPrepare = array($this->email);

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			if ($preparedStatement->rowCount() == 1) {
				$result = $preparedStatement->fetchAll();

				$this->username = $result[0]['username'];
				$this->password = $result[0]['password'];
				$this->google_id = $result[0]['google_id'];

				return true;
			}

			return false;
		}

		public function getUsernameFromGoogleId() {
			$sqlQuery = "SELECT * FROM " . $this->table_name . " WHERE email=? AND google_id=?";
			$valuesToPrepare = array($this->email, $this->google_id);

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			if ($preparedStatement->rowCount() == 1) {
				$result = $preparedStatement->fetchAll();

				$this->username = $result[0]['username'];
				$this->password = $result[0]['password'];

				return true;
			}

			return false;
		}

		public function getDataFromUsername() {
			$sqlQuery = "SELECT * FROM " . $this->table_name . " WHERE username=?";
			$valuesToPrepare = array($this->username);

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			if ($preparedStatement->rowCount() == 1) {
				$result = $preparedStatement->fetchAll();
				$this->email = $result[0]['email'];
				$this->administrator = $result[0]['administrator'];
				$this->first_name = $result[0]['first_name'];
				$this->last_name = $result[0]['last_name'];
				return true;
			}

			return false;
		}

		public function getAllUsers() {
			$sqlQuery = "SELECT * FROM " . $this->table_name;

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute();

			if ($preparedStatement->rowCount() > 0) {
				return $preparedStatement->fetchAll();
			}

			return false;
		}

		public function updateUser() {
			$values = array($this->password, $this->first_name, $this->last_name, $this->username);
			$preparedStatement = $this->connection->prepare('UPDATE sodbeans_users SET password=?, first_name=?, last_name = ? WHERE username=?');
			$preparedStatement->execute($values);
		}
	}
?>