<?php
require_once("data.model.php");
require_once("user.model.php");
/**
 * 
 */
class LibraryReviewers extends QuorumDataModel {
    
    private $reviewersTable = "library_submission_reviewer";
    public $quorumVersion = "2.0";
    public $username = "";
    public $libraryID = "";
    
    function __construct($username, $library_id) {
        parent::__construct();
        $this->username = $username;
        $this->libraryID = $library_id;
    }
    
    public function insertSubmission()
    {
        if (!$this->isReviewerAlreadyAssigned()) {
            $sqlQuery = "INSERT INTO " . $this->reviewersTable . " (library_id, username, review_submitted) VALUES (?, ?, ?);";
            $valuesToPrepare = array($this->libraryID, $this->username, 0);
            $queryResults = $this->attemptQueryWithValues($sqlQuery,$valuesToPrepare);
            return true;
        }
        else {
            return false;
        }
    }

    public function isReviewerAlreadyAssigned() {
        $sqlQuery = "SELECT * FROM " . $this->reviewersTable . " WHERE username = ? AND library_id = ?;";
        $valuesToPrepare = array($this->username, $this->libraryID);
        $queryResults = $this->attemptQueryWithValues($sqlQuery,$valuesToPrepare);
        //return ($queryResults->rowCount() > 0);
        return false;
    }
    
    public function setReviewSubmitted()
    {
        $sqlQuery = "UPDATE " . $this->reviewersTable . " SET review_submitted=? WHERE library_id=? AND username=?";
        $valuesToPrepare = array(1, $this->libraryID, $this->username);
        $queryResults = $this->attemptQueryWithValues($sqlQuery,$valuesToPrepare);
    }
}
?>    