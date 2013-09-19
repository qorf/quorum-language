<?php
    require_once("data.model.php");
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
            $query = "SELECT * FROM " . $this->submissionsTable . " WHERE public_display = true" . search($values) . orderBy($values) . limit($values);

            print $query;
            var_dump($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getLibrarySubmissionsForUser($authorName) {
            $values = array($authorName);
            $query = "SELECT * FROM " . $this->submissionsTable . "WHERE authorName = ?" . search($values) . orderBy($values) . limit($values);
            
            print $query;
            var_dump($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

         public function getLibrarySubmissionsForReviewer($reviewerUsername) {
            $values = array($reviewerUsername);
            $query = "SELECT * FROM " . $this->submissionsTable . " INNER JOIN " . $this->reviewersTable . " ON " . $this->submissionsTable . ".library_id = " . $this->reviewersTable . ".library_id WHERE username = ?" . search($values) . orderBy($values) . limit ($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults); 
        }

        public function getUnacceptedLibrarySubmissions() {
            $values = array();
            $query = "SELECT * FROM " . $this->submissionsTable . "WHERE status = 'not accepted'" . search($values) . orderBy($values) . limit($values);
            
            print $query;
            var_dump($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        private function search($value_array) {
            if ($this->search_query != "") {
                array_push($value_array, $this->search_query);
                return " AND library_description LIKE '%?%'";
            } else {
                return "";
            }
        }

        private function orderBy($value_array) {
            array_push($value_array, $this->order_by);
            return " ORDER BY ?";
        } 

        private function limit($value_array) {
            array_push($value_array, ($this->page * $this->countPerPage));
            array_push($value_array, ($this->countPerPage));
            return " LIMIT ?, ?";
        }
            
        private function returnResultsOfQuery($queryResults) {
            if (is_int($queryResults->rowCount())){
                return $queryResults->fetchAll();
            }
    }
?>    