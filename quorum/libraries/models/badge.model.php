<?php
    require_once("data.model.php");
    require_once("config.php");
    /**
    * 
    */
    class Badge extends QuorumDataModel {
	    private $badgesTable = "badges";
        public $username = "";
        public $badge = "";
        public $date_received = "";
        
        function __construct($username, $badge) {
            parent::__construct();
            $this->username = $username;
            $this->badge = $badge;
            $this->date_received = date("Y-m-d H:i:s"); 
        }

        public function insertBadge() {
			$sqlQuery = "INSERT INTO " . $this->badgesTable . " (username, badge, date_received) VALUES (?, ?, ?)";
			$valuesToPrepare = array($this->username, $this->badge, $this->date_received);
			$queryResults = $this->attemptQueryWithValues($sqlQuery,$valuesToPrepare);
		}
        
        public function getBadgesForUser() {
            $query = "SELECT * FROM " . $this->badgesTable . " WHERE username = ?";
            $values = array("test_user");//$_COOKIE['username']);
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            $fetchResults = $this->returnResultsOfQuery($queryResults);     // the results in form of an array
            $data = $fetchResults;                                       // first result (our data)
            if ($data != null) {
                return $data;
            }
            else {
                return false;
            }
        }
        
        public function doesUserHaveBadge() {
            $query = "SELECT * FROM " . $this->badgesTable . " WHERE username = ? AND badge = ?";
            $values = array($this->username, $this->$badge);
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            return ($queryResults->rowCount() > 0);
        }

        public function getBadgeTitleForSubmittedBadge($badgeCount){
            if ($badgeCount == 0) {
                $this->badge = "novice-submitter";
            } 
            elseif ($badgeCount == 4) {
                $this->badge = "journeyman-submitter";
            }
        }
    }
?>    