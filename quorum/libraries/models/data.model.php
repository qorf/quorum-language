<?php
	require_once("../config.php");

	class QuorumDataModel {
		public $database = null;
		public $connection = null;

		function __construct() {
			$this->database = new database();
			$this->connection = $this->connectToDatabase();
		}

		function connectToDatabase() {
			$conn = new PDO('mysql:dbname=' . $this->database->name . ';host=' . $this->database->host . ';charset=utf8', $this->database->username, $this->database->password);
			$conn->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			return $conn;
		}
	}
?>