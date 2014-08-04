<?php 
	session_start();
	require_once($_SERVER['DOCUMENT_ROOT'] . "/controllers/page.functions.php"); 
?>

<!DOCTYPE>
<!--[if lt IE 9]>      <html class="lt-ie9"> <![endif]-->
<!--[if IE 9]>         <html class="ie9"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="full-page-height"> <!--<![endif]-->
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
				createCSSLink("static/css/style.php",1);

				createScriptLink("assets/js/less.min.js", 1);
				createScriptLink("assets/js/bootstrap.min.js", 1);
				createScriptLink("static/js/global.js", 1);

				if (isset($_COOKIE['username'])) {
					createScriptLink("static/js/useronly.js", 1);
				}
			}
		?>
                <script>
                    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
                    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

                    ga('create', 'UA-26662794-3', 'quorumlanguage.com');
                    ga('send', 'pageview');

                </script>
	</head>
	<body class="full-page-height">
            <div id="IDE" class="full-page-IDE">
<h2 class="full-screen-IDE-h2">Quorum IDE<span class="IDE-subtitle full-page-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>

   <textarea id="IDE-input" class="ide inputArea full-page-IDE-input" role="textbox" aria-multiline="true"></textarea>
<div id="IDE-output-container">
        <pre id="IDE-output" class="outputArea  full-page-IDE-output" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success full-page-IDE-run-button" href="#">Run</a>
        </div>
</div>
</div>
        </body>
</html>