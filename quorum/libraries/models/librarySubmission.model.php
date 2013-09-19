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
?>