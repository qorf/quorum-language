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
    }
    
        class LibrarySubmissions extends QuorumDataModel {
                private $submissionsTable = "library_submissions";
                private $reviewersTable = "library_submission_reviewer";
                public $quorumVersion = "2.0";
        public $sort_by = "upvotes";
        public $ascending_or_descending = "DESC";
        public $search_query = "";
        public $page = 0;
        
                function __construct($sort_by, $ascending_or_descending, $search_query, $page) {
            parent::__construct();
            $this->sort_by = ($sort_by != null) ? $sort_by : $this->sort_by;
            $this->ascending_or_descending = ($ascending_or_descending != null) ? $ascending_or_descending : $this->ascending_or_descending;
            $this->search_query = ($search_query != null) ? $search_query : $this->search_query;
            $this->page = ($page != null) ? $page : $this->page;
                }
                
                public function getPublicSubmissions() {
            $values = array();
            
                        $query = "SELECT * FROM " . $this->submissionsTable . " WHERE public_display = true";

            if ($this->search_query != "") {
                // TODO: Fix this :(
                $query .= " AND library_description LIKE '% ? %'";
                array_push($values, $this->search_query);
            }
            
            $query .= " ORDER BY ?";
            array_push($values, $this->sort_by);
            
            $query .= " LIMIT ?, 25";
            array_push($values, ($this->page * 25));

            print $query;
            var_dump($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
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
                
                private function returnResultsOfQuery($queryResults) {
                        if (is_int($queryResults->rowCount())){
                                return $queryResults->fetchAll();
                        }
                }
        }       
?>