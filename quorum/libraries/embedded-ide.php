<?php 
    require_once($_SERVER['DOCUMENT_ROOT'] . "/controllers/page.functions.php"); 
?>

<!DOCTYPE>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=1190, initial-scale=0.268, maximum-scale=1.0"/>
        <?php
            createPageTitle();

            createScriptLink("http://code.jquery.com/jquery-1.8.3.min.js");
            createScriptLink("http://code.jquery.com/ui/1.9.2/jquery-ui.js");
            createScriptLink("assets/js/dot.min.js", 1);

            createCSSLink("http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700");
            createCSSLink("http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css");
            createCSSLink("assets/css/bootstrap.min.css",1);
            createCSSLink("static/css/stylesheet.less",1,1);

            $usabilityMode = 0;

            if (!$usabilityMode) {
                createCSSLink("http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700");
                createCSSLink("http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css");
                createCSSLink("assets/css/bootstrap.min.css",1);
                createCSSLink("static/css/stylesheet.less",1,1);

                createScriptLink("assets/js/less.min.js", 1);
                createScriptLink("assets/js/bootstrap.min.js", 1);
                createScriptLink("static/js/global.js", 1);

                if (isset($_COOKIE['username'])) {
                    createScriptLink("static/js/useronly.js", 1);
                }
            }
        ?>
    </head>
    <body class="ide-embedded">
        <?php require("ide.php"); ?>
    </body>
</html>