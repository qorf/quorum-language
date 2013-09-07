<?php




function router($action) {
    switch ($action) {
        case "googleUserLoginAuthenticate": googleUserLoginAuthenticate(); break;
    }
}

router($_GET['action']);