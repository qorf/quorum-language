<?php
	require_once("data.model.php");
	/**
	* 
	*/
	class CodeSample extends QuorumDataModel
	{
		private $tableName = "run_code";
		public $quorumVersion = "2.0";
		public $code = "";
		public $output = "";
		public $timesRequested = 1;
                public $uuid = "";
                public $ip = "";
                public $slidenr = 0; //0 is undefined
                public $pagenr = 0; //0 is undefined
		
		function __construct($code)
		{
			parent::__construct();
			$this->code = $code;
		}
                
                public function setIP($ip) {
                    $this->ip = $ip;
                }
                public function setUUID($uuid) {
                    $this->uuid = $uuid;
                }
                public function setSlidenr($slide) {
                    $this->slidenr=$slide;
                }
                public function setPagenr($page) {
                    $this->pagenr=$page;
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
			$sqlQuery = "INSERT INTO " . $this->tableName . " (quorum_version, code, output, times_requested, UUID, IP, slidenr, pagenr) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			$valuesToPrepare = array($this->quorumVersion, $this->code, $this->output, $this->timesRequested, $this->uuid, $this->ip, $this->slidenr, $this->pagenr);
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
