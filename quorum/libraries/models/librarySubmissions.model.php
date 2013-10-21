<?php
    require_once("data.model.php");
    require_once("user.model.php");
    require_once("config.php");
    /**
    * 
    */
    class LibrarySubmissions extends QuorumDataModel {
	   
	    private $submissionsTable = "library_submissions";
	    private $reviewersTable = "library_submission_reviewer";
	    public $quorumVersion = "2.0";
	    public $order_by = "upvotes";
	    public $ascending_or_descending = "DESC";
	    public $search_query = "";
	    public $page = 0;
	    public $countPerPage = 25;
    
        function __construct($order_by, $ascending_or_descending, $search_query, $page) {
             parent::__construct();
            $this->order_by = ($order_by != null) ? $order_by : $this->order_by;
            $this->ascending_or_descending = ($ascending_or_descending != null) ? $ascending_or_descending : $this->ascending_or_descending;
            $this->search_query = ($search_query != null) ? $search_query : $this->search_query;
            $this->page = ($page != null) ? $page : $this->page;
        }
        
        public function getPublicSubmissions() {
            $values = array();
            $query = "SELECT * FROM " . $this->submissionsTable . " WHERE public_display = '1'" . $this->search($values) . $this->orderBy($values) . $this->limit($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getPublicLibrarySubmisionCount()
        {
            $public = '1';
            $values = array($public);
            $query = "SELECT COUNT(*) FROM " . $this->submissionsTable . " WHERE public_display = ?";
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getAllSubmissions() {
            $values = array();
            $query = "SELECT * FROM " . $this->submissionsTable . " " . $this->search($values) . $this->orderBy($values) . $this->limit($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getLibrarySubmisionCount()
        {
            $sqlQuery = "SELECT COUNT(*) FROM" . $this->submissionsTable;
            return $this->returnResultsOfQuery($queryResults);

        }

        public function getLibrarySubmissionsForUser($authorName) {
            $values = array($authorName);
            $query = "SELECT * FROM " . $this->submissionsTable . "WHERE authorName = ?" . $this->search($values) . $this->orderBy($values) . $this->limit($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

         public function getLibrarySubmissionsForReviewer($reviewerUsername) {
            $values = array($reviewerUsername);
            $query = "SELECT * FROM " . $this->submissionsTable . " INNER JOIN " . $this->reviewersTable . " ON " . $this->submissionsTable . ".library_id = " . $this->reviewersTable . ".library_id WHERE username = ? AND review_submitted = 0";
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults); 
        }

        public function getUnacceptedLibrarySubmissions() {
            $values = array();
            $query = "SELECT * FROM " . $this->submissionsTable . "WHERE status = 'not accepted'" . $this->search($values) . orderBy($values) . limit($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getLibrariesForAdmin($status) {
            $values = array($status);
            $user = new User(null, $_COOKIE['username'], null, null);
            $user->getDataFromUsername();
            if ($user->administrator == 1) {
                $query = "SELECT * FROM " . $this->submissionsTable . " WHERE status = ?";
                $queryResults = $this->attemptQueryWithValues($query, $values);
                return $this->returnResultsOfQuery($queryResults);
            }
            else {
                return false;
            }
        }

        private function search(&$value_array) {
            if ($this->search_query != "") {
                array_push($value_array, $this->search_query);
                return " AND library_description LIKE '%?%'";
            } else {
                return "";
            }
        }

        private function orderBy(&$value_array) {
            array_push($value_array, $this->order_by);
            return " ORDER BY ?";
        } 

        private function limit(&$value_array) {
            array_push($value_array, ($this->page * $this->countPerPage));
            array_push($value_array, ($this->countPerPage));
            return " LIMIT ?, ?";
        }
    }
?>    