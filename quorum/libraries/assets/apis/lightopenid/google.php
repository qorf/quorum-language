<?php
session_start();

require 'openid.php';
if (!isset($_SESSION['referer'])) {
    $_SESSION['referer'] = $_POST['referer'];
    $_SESSION['redirectTo'] = $_POST['referer'] . "/?loginWith=google";
}

try {
    $openid = new LightOpenID("localhost"); // "localhost" = our domain

    // The user has clicked "Signin With Google" or "Signup with Google".
    if(!$openid->mode) { 
        if(isset($_GET['login'])) {

            $openid->required = array(
                'namePerson',
                'namePerson/first',
                'namePerson/last',
                'contact/email'
            );
            $openid->identity = 'https://www.google.com/accounts/o8/id';
            header('Location: ' . $openid->authUrl());
        }
    } 

    // The user has cancelled authorization.
    elseif($openid->mode == 'cancel') {
        $_SESSION['openIdData'] = null;
        header('Location: ' . $_SESSION['redirectTo']);

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

        header('Location: ' . $_SESSION['redirectTo']);

    }
} 

catch(ErrorException $e) {
    $_SESSION['openIdData'] = array("error" => $e->getMessage());
    header('Location: ' . $_SESSION['redirectTo']);
}

?>
