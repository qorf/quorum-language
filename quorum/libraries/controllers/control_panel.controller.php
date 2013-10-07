<?php
require("../models/user.model.php");
require("../models/libraryReviewers.model.php");
require_once("../config.php");

function addUserToLibrary() {
    $library_review = new LibraryReviewers($_POST['username'], $_POST['library_id']);
    try {
        $library_review->insertSubmission();
        return true;
    }
    catch (Exception $ex) {
        return false;
    }
}

function router($action) {
    switch ($action) {
        case "addUserToLibrary": addUserToLibrary(); break;
    }
}

router($_GET['action']);