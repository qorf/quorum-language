<?php
require("../models/user.model.php");
require("../models/libraryReviewers.model.php");

function addUserToLibrary() {
    $library_review = new LibraryReviewers($_POST['username'], $_POST['library_id']);
    try {
        echo ($library_review->insertSubmission()) ? "true" : "false";
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