<?php
	function getRoot() { 
		return "http" . (($_SERVER['HTTPS'] == "on") ? "s://" : "://") . $_SERVER["SERVER_NAME"] . "/"; 
	}

	function getPage() { 
		return substr($_SERVER["REQUEST_URI"], 1);
	}

	function getDocRoot() { 
		return $_SERVER['DOCUMENT_ROOT'] . "/";
	}


	function createPageTitle() {
		$classPageTitle = $GLOBALS["classPageTitle"];
                
                if (isset($classPageTitle)) {
                    $pageTitle = $classPageTitle . " | ";
                }
                else {
			$page = substr(getPage(),1,-4);
			$pageTitle = "";

			switch($page) {
				case "": $pageTitle = "Introduction to the Quorum Programming Language"; break;
				case "index": $pageTitle = "Introduction to the Quorum Programming Language"; break;
				case "syntax": $pageTitle = "The Syntax of the Quorum Programming Language"; break;
				case "libraries": $pageTitle = "The Quorum Standard Library"; break;
				case "download": $pageTitle = "Download the Quorum Programming Language"; break;
	                        case "documents/console": $pageTitle = "Console Arguments in Quorum"; break;
			}

			$pageTitle = $pageTitle == "" ? $pageTitle : $pageTitle . " | ";
                }
            
            print "<title>" . $pageTitle . "Quorum Programming Language</title>\n";

	}

	function createScriptLink($src, $useRoot=false) {
		print '<script src="';
		print ($useRoot ? getRoot() : "") . $src;
		print '" type="text/javascript"></script>';
		print "\n\t\t";
	}

	function createCSSLink($src, $useRoot=false, $isLess=false) {
		print '<link href="';
		print ($useRoot ? getRoot() : "") . $src;
		print '" rel="stylesheet' . ($isLess ? "/less" : "");
		print '" type="text/css" />';
		print "\n\t\t";
	}

	function getFullUrl($url) {
		return  getRoot() . $url;
	}

	function createNavBarLink($name,$url, $useRoot=false) {
		$page = substr($_SERVER["REQUEST_URI"], 1);
		$fullUrl = ($useRoot ? getRoot() : "") . $url;
		print (($url == $page) ? '<li class="active">' : '<li>') . '<a href="'.$fullUrl.'">'.$name."</a></li>\n\t\t\t\t\t\t";
	} 

	function displayUserControls() {
		if (isset($_COOKIE['username'])) {
			require_once(getDocRoot() . "static/templates/user-headercontrols.template.php");
		}
		else {
			require_once(getDocRoot() . "static/templates/user-signinheader.template.php");
		}
	}

	function registrationModal() {
		require_once(getDocRoot() . "static/templates/user-registration.template.php");
	}

	function loginModal() {
		require_once(getDocRoot() . "static/templates/user-signin.template.php");
	}
?>