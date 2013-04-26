<?php
session_start();

require 'openid.php';
require '../../../config.php';

if ($_POST['action'] == "login") { // is logging in
    $extension = "#googleLogin";
}
else if ($_POST['action'] == "register") { // is registration
    $extension = "#googleRegister";
}
else {
    $extension = null;
}

$_SESSION['referer'] = str_replace("#googleRegister", "", str_replace("googleLogin", "", $_POST['referer']));

if (($_SESSION['extension'] != $extension) && ($extension != null)) {
    $_SESSION['extension'] = $extension;
    $_SESSION['redirectTo'] = $_POST['referer'] . $extension;
}

try {
    $openid = new LightOpenID("test.quorumlanguage.com"); // "localhost" = our domain

    // The user has clicked "Signin With Google" or "Signup with Google".
    if(!$openid->mode) { 
            $openid->required = array(
                'namePerson',
                'namePerson/first',
                'namePerson/last',
                'contact/email'
            );
            $openid->identity = 'https://www.google.com/accounts/o8/id';
            header('Location: ' . $openid->authUrl());
    } 

    // The user has cancelled authorization.
    elseif($openid->mode == 'cancel') {
        $_SESSION['openIdData'] = null;
        $_SESSION['referer'] . $_SESSION['redirectTo'] . 'cancel';
        header('Location: ' . $_SESSION['redirectTo']);
        unset($_SESSION["referer"]);
    } 

    // The user has accepted authorization.
    else {
        $data = $openid->getAttributes();
        $openIdData = array(
                    "valid" => $openid->validate(),
                    "identity" => $openid->identity,
                    "email" => $data['contact/email'],
                    "firstName" => $data['namePerson/first'],
                    "lastName" => $data['namePerson/last']
                );

        $_SESSION['openIdData'] = $openIdData;

        //print $_SESSION['referer'] . $_SESSION['redirectTo'] . 'accept';
        header('Location: ' . $_SESSION['redirectTo']);
        unset($_SESSION["referer"]);
    }
} 

catch(ErrorException $e) {
    $_SESSION['openIdData'] = array("error" => $e->getMessage());
    // print $_SESSION['referer'] . $_SESSION['redirectTo'] . 'error';
    header('Location: ' . $_SESSION['redirectTo']);
}

?>
