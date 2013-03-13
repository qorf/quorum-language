<?php
	class QuorumDataModel {
		public function connectToDatabase() {
			$databaseHost = "home.cs.siue.edu";
			$databaseName = "quorum";
			$databaseUsername = "quorum";
			$databasePassword ="quorum-123";

			$conn = new PDO('mysql:dbname=' . $databaseName . ';host=' . $databaseHost . ';charset=utf8', $databaseUsername, $databasePassword);
			$conn->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

			return $conn;
		}
	}
?>