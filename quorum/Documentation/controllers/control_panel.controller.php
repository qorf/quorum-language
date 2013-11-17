<?php
require("../models/user.model.php");
require("../models/email.model.php");
require("../models/libraryReviewers.model.php");

function addUserToLibrary() {
    $library_review = new LibraryReviewers($_POST['username'], $_POST['library_id']);
    $user = new User(null, $_POST['username'], null, null);
    $user->getDataFromUsername();

    try {
        echo ($library_review->insertSubmission()) ? "true" : "false";

        $message = "<p>Hello! You have been requested to review a library that has been submitted for approval ";
        $message .= "to the Quorum Language standard library. To see the library, visit this URL: ";
        $message .= "http://quorumlanguage.com/submitted_library.php?id=" . $library_review->libraryID . "</p>";
        $message .= "<p>To review the library, log into the website and visit your control panel.</p>";
        $message .= "<p>Thank you for your assistance and contributions to the Quorum Language!</p>";

        $email = new Email($user->email,"quorum@quorumlanguage.com","You have selected to review a submission on QuorumLanguage.com",$message);
        $email->send();
    }
    catch (Exception $ex) {
        echo "false";
    }
}

function router($action) {
    switch ($action) {
        case "addUserToLibrary": addUserToLibrary(); break;
    }
}

router($_GET['action']);