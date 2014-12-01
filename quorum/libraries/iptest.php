<?php

echo "Remote addr: " . $_SERVER['REMOTE_ADDR'] . "<br/>";
echo "X forward: " . $_SERVER['HTTP_X_FORWARDED_FOR'] . "<br/>";
echo "Client IP: " . $_SERVER['HTTP_CLIENT_IP'] . "<br/>";


 
$ip = $_SERVER['REMOTE_ADDR'];
 
    if (!empty($_SERVER['HTTP_CLIENT_IP'])) {
        $ip = $_SERVER['HTTP_CLIENT_IP'];
    } elseif (!empty($_SERVER['HTTP_X_FORWARDED_FOR'])) {
        $ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
    }
 
    echo "IP: "  . $ip;
?>
