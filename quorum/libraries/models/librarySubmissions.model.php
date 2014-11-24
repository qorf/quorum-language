<?php
    require_once("data.model.php");
    require_once("user.model.php");
    /**
    * 
    */
    class LibrarySubmissions extends QuorumDataModel {
       
        private $submissionsTable = "library_submissions";
        private $reviewersTable = "library_submission_reviewer";
        public $quorumVersion = "2.0";
        public $order_by = "date_submitted";
        public $ascending_or_descending = "DESC";
        public $search_query = "";
        public $countPerPage = 10;
    
        function __construct($order_by, $ascending_or_descending, $search_query, $page) {
             parent::__construct();
            $this->order_by = ($order_by != null) ? $order_by : $this->order_by;
            $this->ascending_or_descending = ($ascending_or_descending != null) ? $ascending_or_descending : $this->ascending_or_descending;
            $this->search_query = ($search_query != null) ? $search_query : $this->search_query;
        }

        public function getPageOfResults($page, $submissions) {
            $page_results = array();
            $page = $page - 1;
            $offset = $page * $this->countPerPage;
            for ($i = $offset; $i < $offset + $this->countPerPage; $i++) { 
                if ($submissions[$i] != NULL) {
                    array_push($page_results, $submissions[$i]);
                }
            }

            return $page_results;
        }
        
        public function getPublicSubmissions() {
            $query = "SELECT * FROM " 
                    . $this->submissionsTable . " WHERE public_display = '1' "
                    . "ORDER BY " . $this->order_by . " " 
                    . $this->ascending_or_descending;

            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getAllSubmissions() {
            $values = array();
            $query = "SELECT * FROM " . $this->submissionsTable . " " . $this->orderBy($values) . $this->limit($values);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getLibrarySubmisionCount()
        {
            $sqlQuery = "SELECT COUNT(*) FROM " . $this->submissionsTable;
            return $this->returnResultsOfQuery($queryResults);
        }

        public function getLibrarySubmissionsForUser($uploader_username, $status = "") {
            $values = array();
            $query = "";

            if ($status == "") {
                $values = array($uploader_username);
                $query = "SELECT * FROM " . $this->submissionsTable . " WHERE uploader_username = ?";   
            }
            else {
                $values = array($uploader_username, $status);
                $query = "SELECT * FROM " . $this->submissionsTable . " WHERE uploader_username = ? AND status = ?";   
            }

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