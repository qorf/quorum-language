<?php
require_once("models/codeSample.model.php");

//    echo 'code: ' . $_POST['code'];
//    echo 'completed: ' . $_POST['completed'];
//    echo 'code: ' . $_POST['slidenumber'];
//    echo 'code: ' . $_POST['pagenumber'];
    
    $codeSample = new CodeSample($_POST['code']);
    $codeSample->setOutput($_POST['resultCode']);
    $codeSample->setCompleted($_POST['completed']);

    $codeSample->setIP($_SERVER['REMOTE_ADDR']);

    $codeSample->setUUID($_POST['uuid']);
    $codeSample->setPagenr($_POST['pagenumber']);
    $codeSample->setSlidenr($_POST['slidenumber']);
    $codeSample->setPageURL($_POST['pageurl']);
    $codeSample->setGACookie($_POST['gacookie']);

    $codeSample->insertCodeSample();

?>
