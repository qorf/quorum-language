<?php
    require_once("data.model.php");
    require_once("config.php"); 
    require_once("models/email.model.php"); 
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
            $query = "SELECT * FROM " . $this->badgesTable . " WHERE username = ? ORDER BY badge";
            $values = array($this->username);
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            $fetchResults = $this->returnResultsOfQuery($queryResults);     // the results in form of an array
            $data = $fetchResults;                                          // first result (our data)
            if ($data != null) {
                return $data;
            }
            else {
                return false;
            }
        }
        
        public function doesUserHaveBadge() {
            $query = "SELECT * FROM " . $this->badgesTable . " WHERE username = ? AND badge = ?";
            $values = array($this->username, $this->badge);
            $queryResults = $this->attemptQueryWithValues($query, $values); // the results
            return ($queryResults->rowCount() > 0);
        }

        public function emailUserAboutBadge($submission) {
            $user = new User(null, $this->username, null, null);
            $user->getDataFromUsername();

            // Transforms "novice-submitter" to "novice submitter" to "Novice Submitter"
            $englishified_badge_name = ucwords(str_replace('-', ' ', $this->badge)); 

            $accepted_messsage = ($this->badge_type == "type-accepted") ? "been accepted and has " : "";

            $message = "Thanks, " . $user->$username . "!\n";
            $message .= "Your library, " . $submission->libraryName . ", has " . $accepted_message . "earned you the " . $englishified_badge_name;
            $message .= " badge.";
            
            $message .= "Quorum is a complex project and we greatly appreciate your "
                    . "help. Making a vibrant and helpful community is not possible"
                    . " without people like you.\n\n";
            
            $message .= "Congratulations again on the new badge and thanks for helping science!.\n\n";
            
            $message .= "The Quorum Team";

            $email = new Email($user->email,"quorum@quorumlanguage.com","You have recieved a badge on QuorumLanguage.com!",$message);
            $email->send();
        }
    }