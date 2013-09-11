<?php
	require_once("data.model.php");
	require_once("../config.php");
	/**
	* 
	*/
	class librarySubmissions extends QuorumDataModel {
		private $submissionsTable = "library_submissions";
		private $reviewersTable = "library_submission_reviewer"
		public $quorumVersion = "2.0";
		public $libraryID = "";
		public $libraryName = "";
		public $uploaderUsername = "";
		public $authorName = "";
		public $libraryDescription = "";
		public $usageInstructions = "";
		public $submissionURL = "";
		public $supplementaryFilesURL = "";
		public $publicDisplay = "";
		public $status = "";
		public $dateSubmitted  = "";

		function __construct($libraryID, $libraryName, $uploaderUsername, $authorName, $libraryDescription, $usageInstructions, $submissionURL, $supplementaryFilesURL, $public_display, $status, $dateSubmitted) {
			parent::__construct();
			$this->libraryID = $libraryID;
			$this->libraryName = $libraryName;
			$this->uploaderUsername = $uploaderUsername;
			$this->authorName = $authorName;
			$this->libraryDescription = $libraryDescription;
			$this->usageInstructions = $usageInstructions;
			$this->submissionURL = $submissionURL;
			$this->supplementaryFilesURL = $supplementaryFilesURL;
			$this->public_display = $public_display;
			$this->status = $status;
			$this->dateSubmitted = $dateSubmitted;
		}
		
		public function getPublicSubmissions() {
			$query = "SELECT * FROM " . $this->submissionsTable . " WHERE public_display = ?";
			$queryResults = $this->attempQuery($query);
			return $this->returnResultsOfQuery($queryResults);
		}

		public function getLibrarySubmissionsForUser($authorName) {
			$query = "SELECT * FROM " . $this->submissionsTable . "WHERE authorName = ?";
			$values = array($authorName);
			$queryResults = $this->attemptQueryWithValues($query, $values);
			return $this->returnResultsOfQuery($queryResults);
		}

		public function getLibrarySubmissionsForReviewer($reviewerUsername) {
			$query = "SELECT * FROM " . $this->submissionsTable . " INNER JOIN " . $this->reviewersTable . " ON " . $this->submissionsTable . ".library_id = " . $this->reviewersTable . ".library_id WHERE username = ?";
			$values = array($reviewerUsername);
			$queryResults = $this->attemptQueryWithValues($query, $values);
			return $this->returnResultsOfQuery($queryResults);
			
		}

		public function getUnacceptedLibrarySubmissions() {
			$query = "SELECT * FROM " . $this->submissionsTable . "WHERE status = 'not accepted'";
			$queryResults = $this->attemptQuery($query);
			return $this->returnResultsOfQuery($queryResults);
		}

		/* these 2 methods should probably be moved to data.model.php or whatever class this class extends */
		private function attemptQuery($query) {
			try{
				$preparedStatement = $this->connection->prepare($query);
				$preparedStatement->execute();
				return $preparedStatement;
			}
			catch (Exeception $ex){
				return $ex;
			}
		}

		private function attemptQueryWithValues($query, $values) {
			try{
				$preparedStatement = $this->connection->prepare($query);
				$preparedStatement->execute($values);
				return $preparedStatement;
			}
			catch (Exeception $ex){
				return $ex;
			}
		}
	/* these 2 methods should probably be moved to data.model.php or whatever class this class extends */
		
		private function returnResultsOfQuery($queryResults) {
			if (is_int($queryResults->rowCount())){
				return $queryResults->fetchAll();
			}
		}
	}	
?>