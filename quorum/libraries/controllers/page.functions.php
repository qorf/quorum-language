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


	function Truncate($string, $length, $stopanywhere=false) {
		if (strlen($string) > $length) {
			return substr($string,0,$length) . "..";
		}
		else {
			return $string;
		}
	}

	function createPageTitle() {
		$classPageTitle = $GLOBALS["classPageTitle"];
                
        if (isset($classPageTitle)) {
            $pageTitle = $classPageTitle . " | ";
        }
        else {
			$page = ucfirst(substr(strrchr(getPage(), '/'),1,-4));
                        
			$pageTitle = "";
                        
			switch($page) {
				case "index": $pageTitle = "Introduction to the Quorum Programming Language"; break;
				case "syntax": $pageTitle = "The Syntax of the Quorum Programming Language"; break;
				case "libraries": $pageTitle = "The Quorum Standard Library"; break;
				case "download": $pageTitle = "Download the Quorum Programming Language"; break;
				case "documents/console": $pageTitle = "Console Arguments in Quorum"; break;
				case "control_panel": $pageTitle = "Control Panel"; break;
				case "submit_library": $pageTitle = "Submit a Library to Quorum"; break;
				case "submitted_library_index": $pageTitle = "Libraries Submitted to Quorum"; break;
                                case "curriculum": $pageTitle = "Learn Quorum"; break;
				case "": 
					default: $pageTitle = $page; break;
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
    
    function slugify($text)
    { 
      $text = str_replace("'", "", $text);
      $text = preg_replace('~[^\\pL\d]+~u', '-', $text);
      $text = trim($text, '-');
      $text = iconv('utf-8', 'us-ascii//TRANSLIT', $text);
      $text = strtolower($text);
      $text = preg_replace('~[^-\w]+~', '', $text);

      return empty($text) ? "" : $text;
    }
?>