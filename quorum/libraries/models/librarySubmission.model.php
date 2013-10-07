<?php
        require_once("data.model.php");
        require_once("config.php");
        /**
        * 
        */
   
    class LibrarySubmission extends QuorumDataModel {
        private $submissionsTable = "library_submissions";
        private $reviewersTable = "library_submission_reviewer";
        public $quorumVersion = "2.0";
        public $libraryID = "";
        public $libraryName = "";
        public $uploaderUsername = "";
        public $authorName = "";
        public $libraryDescription = "";
        public $usageInstructions = "";
        public $submissionURL = "";
        public $supplementaryFilesURL = "";
        public $public_display = "";
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
        
        public function getSubmissionByID() {
            $values = array();
            $query = "SELECT * FROM " . $this->submissionsTable . " WHERE library_id = ?";
            array_push($values, $this->libraryID);
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            $fetchResults = $this->returnResultsOfQuery($queryResults);     // the results in form of an array
            $data = $fetchResults[0];                                       // first result (our data)
            if ($data != null) {
                return new LibrarySubmission($data[0], $data[1], $data[2], $data[3], $data[4], $data[5], $data[6], $data[7], $data[8], $data[9], $data[10]);
            }
            else {
                return false;
            }
        }

        public function insertSubmission()
		{
            $sqlQuery = "INSERT INTO " . $this->submissionsTable . " (library_id, library_name, uploader_username, author_name, library_description, usage_instructions, submission_url, supplementary_files_url, public_display, status, date_submitted)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			$valuesToPrepare = array($this->libraryID, $this->libraryName, $this->uploaderUsername, $this->authorName, $this->libraryDescription, $this->usageInstructions, $this->submissionURL, $this->supplementaryFilesURL, $this->public_display, $this->status, $this->dateSubmitted);
			$queryResults = $this->attemptQueryWithValues($sqlQuery,$valuesToPrepare);
		}

        public function updateSubmissionStatus()
		{
            if ($this->status == "pending-reviewer") {
                $this->status = "pending-admin";
            }
            $sqlQuery = "UPDATE " . $this->submissionsTable . " SET public_display=?, status=? WHERE library_id=?";            
			$valuesToPrepare = array($this->public_display, $this->status, $this->libraryID);
			$queryResults = $this->attemptQueryWithValues($sqlQuery,$valuesToPrepare);
		}
    }
    