<?php
	function databaseConnection() {
		$databaseHost = "home.cs.siue.edu";
		$databaseName = "quorum";
		$databaseUsername = "quorum";
		$databasePassword ="quorum-123";

		$conn = new PDO('mysql:dbname=' . $databaseName . ';host=' . $databaseHost . ';charset=utf8', $databaseUsername, $databasePassword);
		$conn->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
		$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

		return $conn;
	}

	$stmt = $conn->prepare("INSERT INTO table (col1, col2) VALUES (:col1, :col2)");
	$stmt->bindValue(":col1", $col1);
	$stmt->bindValue(":col2", $col2);
	$stmt->execute();

	return true;
?>