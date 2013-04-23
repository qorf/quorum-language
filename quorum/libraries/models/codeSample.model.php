<?php
	require_once("data.model.php");
	require_once("../config.php");
	/**
	* 
	*/
	class CodeSample extends QuorumDataModel
	{
		private $tableName = "run_code";
		public $quorumVersion = "1.0";
		public $code = "";
		public $output = "";
		public $timesRequested = 1;
		
		function __construct($code)
		{
			parent::__construct();
			$this->code = $code;
		}
		public function getResultFromDatabase() {
			$updateResult = $this->getOutput();

			if (is_int($updateResult)) {
				// if no rows are updated, try to insert and return the result of the insertion.
				// otherwise the update was successful so return true.
				return ($updateResult == 0) ?  null : 1;
			}

			return $updateResult; // if we got here, we know the update threw an exception
		}
		public function setOutput($output)
		{
			$this->output = (string)$output;
		}
		public function fetchOutput() 
		{
			$sqlQuery = "SELECT output FROM " . $this->tableName . " WHERE code = ? AND quorum_version = ?";
			$valuesToPrepare = array($this->code, $this->quorumVersion);	
			$resultOfQuery = $this->attempSQLQuery($sqlQuery,$valuesToPrepare);
			if (is_int($resultOfQuery->rowCount()))
			if ($resultOfQuery->rowCount() == 1) 
			{
				$result = $resultOfQuery->fetchAll();
				$this->output = $result[0]['output'];
				return $resultOfQuery->rowCount();
			}
			else
			{ 
				return 0;
			}
		}
		public function getOutput()
		{
			return $this->output;
		}
		public function updateCount()
		{
			$sqlQuery = "UPDATE " . $this->tableName . " SET times_requested = times_requested + 1 WHERE code = ? AND quorum_version = ?";
			$valuesToPrepare = array($this->code, $this->quorumVersion);
			$queryResults = $this->attempSQLQuery($sqlQuery, $valuesToPrepare);
			$rows = $queryResults->rowCount();
			if (is_int($rows))
			{
				return $rows;
			}
			else 
			{
				return 0;
			}			
		}
		public function insertCodeSample()
		{
			$sqlQuery = "INSERT INTO " . $this->tableName . " (quorum_version, code, output, times_requested) VALUES (?, ?, ?, ?)";
			$valuesToPrepare = array($this->quorumVersion, $this->code, $this->output, $this->timesRequested);
			$queryResults = $this->attempSQLQuery($sqlQuery,$valuesToPrepare);
			$rows = $queryResults->rowCount();
			if(is_int($rows))
			{
				return $rows;
			}
			else 
			{
				return 0;
			}
		}
		private function attempSQLQuery($sqlQuery,$valuesToPrepare)
		{
			try {
				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);
				return $preparedStatement;
			}
			catch (Exception $ex) {
				return $ex;
			}
		}
	}
?>
