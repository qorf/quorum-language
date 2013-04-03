<?php
	require_once("data.model.php");

	class User extends QuorumDataModel {
		private $table_name;
		public $email = "";
		public $username = "";
		public $password = "";
		public $google_id = "";

		function __construct($email, $username, $password, $google_id) {
			parent::__construct();
			$this->email = $email;
			$this->username = $username;
			$this->password = $password;
			$this->google_id = $google_id;
		}

		public function insert() {
			// this needs tested, i've always done existence checks in PHP prior to this
			$sqlQuery = "IF EXISTS (SELECT * FROM " . $table_name . " WHERE email=? OR username=? OR google_id=?) INSERT INTO " . $table_name
					  . " (email, username, password, reset_password_key, reset_password_expire, google_id) VALUES (?, ?, SHA1(?), ?, ?)";

			$valuesToPrepare = array($this->email, $this->username, $this->password, NULL, NULL, $this->google_id); var_dump($valuesToPrepare);
			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			return $preparedStatement;
		}

		public function checkLogin() {
			if ($google_id == "") { // Regular user
				$sqlQuery = "SELECT * FROM " . $table_name . " WHERE email=? AND password=SHA1(?) AND google_id IS NULL";
				$valuesToPrepare = array($this->email, $this->password);
			}
			else { // Google user
				$sqlQuery = "SELECT * FROM " . $table_name . " WHERE email=? AND google_id=?";
				$valuesToPrepare = array($this->email, $this->google_id);
			}

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			return ($preparedStatement->rowCount() == 1) ? true : false; // if we have one row, we are logged in.
		}

		public function createCookies() {

		}
	}
?>