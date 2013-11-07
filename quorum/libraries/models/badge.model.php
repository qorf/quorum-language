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
        public $badge_type = "";
        public $date_received = "";

        function __construct($username, $badge, $badge_type) {
            parent::__construct();
            $this->username = $username;
            $this->badge = $badge;
            $this->badge_type = $badge_type;
            $this->date_received = date("Y-m-d H:i:s"); 
        }

        public function insertBadge() {
			$sqlQuery = "INSERT INTO " . $this->badgesTable . " (username, badge, badge_type, date_received) VALUES (?, ?, ?, ?)";
			$valuesToPrepare = array($this->username, $this->badge, $this->badge_type, $this->date_received);
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

        public function getBadgeCountForType() {
            $query = "SELECT * FROM " . $this->badgesTable . " WHERE username = ? AND badge_type = ?";
            $values = array($this->username, $this->badge_type);
            $queryResults = $this->attemptQueryWithValues($query, $values);
            return $queryResults->rowCount();
         }

        public function getBadgeTitleForSubmissiondBadge($badgeCount) { 
            if ($badgeCount == 0) {
                $this->badge = "novice-submitter";
            } 
            elseif ($badgeCount == 4) {
                $this->badge = "journeyman-submitter";
            }
        }

        public function getBadgeTitleForAcceptionBadge($badgeCount) {
            if ($badgeCount == 0) {
                $this->badge = "Quorum-private";
            } 
            elseif ($badgeCount == 1) {
                $this->badge = "Quorum-private-first-class";
            }
            elseif ($badgeCount == 3) {
                $this->badge = "Quorum-Specialist";
            }
            elseif ($badgeCount == 6) {
                $this->badge = "Quorum-Corporal";
            }
            elseif ($badgeCount == 10) {
                $this->badge = "Quorum-Sergeant";
            }
            elseif ($badgeCount == 15) {
                $this->badge = "Quorum-Staff-Sergeant";
            }
        }

        public function checkUserBadgeStatusForType($badge) {
            $userBadgeCountForType = $badge->getBadgeCountForType();
            
            if ($badge->badge_type == "type-acception") {
                $badge->getBadgeTitleForAcceptionBadge($userBadgeCountForType);    
            } elseif ($badge->badge_type == "type-submission") {
                $badge->getBadgeTitleForSubmissiondBadge($userBadgeCountForType);
            }

            if($badge->badge != null && !$badge->doesUserHaveBadge()) {
                $badge->insertBadge();
            }
        }
    }
?>    