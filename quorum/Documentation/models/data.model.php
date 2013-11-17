<?php
	require_once("config.php");

	class QuorumDataModel {
		public $database = null;
		public $connection = null;

		function __construct() {
            try {
			    $this->database = new database();
			    $this->connection = $this->connectToDatabase();
            }
            catch (Exception $ex) {
                //throw $ex;
            }
		}

		function connectToDatabase() {
			$conn = new PDO('mysql:dbname=' . $this->database->name . ';host=' . $this->database->host . ';charset=utf8', $this->database->username, $this->database->password);
			$conn->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			return $conn;
        }
        
        public function attemptQueryWithValues($query, $values) {
			$preparedStatement = $this->connection->prepare($query);
			$result = $preparedStatement->execute($values);	
            $error = ($this->connection->errorInfo());
            return $preparedStatement;
        }
        
        public function returnResultsOfQuery($queryResults) {
            if(!empty($queryResults) && $queryResults->rowCount() > 0) {
                return $queryResults->fetchAll();
            }
        }
	}
?>