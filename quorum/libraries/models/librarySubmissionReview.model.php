<?php
    require_once("data.model.php");
    require_once("config.php");
    /**
    * 
    */
    class LibrarySubmissionReview extends QuorumDataModel {
	    private $submissionsTable = "library_submissions";
	    private $reviewsTable = "library_submission_reviews";
	    private $reviewersTable = "library_submission_reviewer";
	    public $libraryID = "";
        public $username = "";
        public $feedback = "";
        public $status = "";
        public $technical_rating = "";
        public $usability_rating = "";
        public $date_reviewed = "";
        
        function __construct($libraryID, $username, $feedback, $confidential_feedback, $status, $technical_rating, $usability_rating) {
            parent::__construct();
            $this->libraryID = $libraryID;
            $this->username = $username;
            $this->feedback = $feedback;
            $this->confidential_feedback = $confidential_feedback;
            $this->status = $status;
            $this->technical_rating = $technical_rating;
            $this->usability_rating = $usability_rating;
            $this->date_reviewed = date("Y-m-d H:i:s"); 
        }

        public function insertReview() {
			$sqlQuery = "INSERT INTO " . $this->reviewsTable . " (library_id, username, feedback, confidential_feedback, status, technical_rating, usability_rating, date_reviewed) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			$valuesToPrepare = array($this->libraryID, $this->username, $this->feedback, $this->confidential_feedback, $this->status, $this->technical_rating, $this->usability_rating, $this->date_reviewed);
			$queryResults = $this->attemptQueryWithValues($sqlQuery,$valuesToPrepare);
		}
        
        public function getReviewersForReviewer() {
            $values = array();
            $query = "SELECT * FROM " . $this->$reviewersTable . " WHERE review_submitted = '0' AND username = ?";
            array_push($values, $_COOKIE['username']);
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            $fetchResults = $this->returnResultsOfQuery($queryResults);     // the results in form of an array
            $data = $fetchResults[0];                                       // first result (our data)
            if ($data != null) {
                return $data;
            }
            else {
                return false;
            }
        }
        
        public function getFeedbackForLibrary() {
            $values = array($this->libraryID);
            $query = "SELECT * FROM " . $this->reviewsTable . " WHERE library_id = ?";
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            $fetchResults = $this->returnResultsOfQuery($queryResults);     // the results in form of an array
            if ($fetchResults != null) {
                return $fetchResults;
            }
            else {
                return false;
            }
        }
        
        public function getFeedbackForLibraryAndUser() {
            $values = array($this->libraryID, $_COOKIE['username']);
            $query = "SELECT * FROM " . $this->reviewsTable . " WHERE library_id = ? AND username = ?";
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            $fetchResults = $this->returnResultsOfQuery($queryResults);     // the results in form of an array
            $data = $fetchResults[0];                                       // first result (our data)
            if ($data != null) {
                return $data;
            }
            else {
                return false;
            }
        }
    }
?>    