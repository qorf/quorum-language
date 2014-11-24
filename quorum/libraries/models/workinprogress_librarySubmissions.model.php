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
            $query = "SELECT * FROM " . $this->submissionsTable . " WHERE public_display = '1' ORDER BY " . $this->order_by . " " . $this->ascending_or_descending;//. " " . $this->ascending_or_descending;
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

        private function orderBy(&$value_array) {
            return " ORDER BY ? ?";
        } 

        private function limit(&$value_array) {
            array_push($value_array, ($this->page * $this->countPerPage));
            array_push($value_array, ($this->countPerPage));
            return " LIMIT ?, ?";
        }
    }
?>    