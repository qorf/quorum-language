<?php
	require_once("../config.php");

	class QuorumDataModel {
		public $database = new database();
		public $connection = null;

		function __construct() {
			$this->connection = connectToDatabase();
		}

		public function connectToDatabase() {
			$conn = new PDO('mysql:dbname=' . $database->name . ';host=' . $database->host . ';charset=utf8', $database->username, $database->password);
			$conn->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			return $conn;
		}
	}
?>