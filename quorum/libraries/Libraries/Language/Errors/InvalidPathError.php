<?php $classPageTitle = "InvalidPathError"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Errors.InvalidPathError</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Errors.InvalidPathError" /><h2> <span class="controllable" data-componentType="class-name">Class InvalidPathError</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The InvalidPathError class is an error or exception that is thrown when a path specified for a required operation is not valid. There may be many reasons for this, including referencing a path using illegal characters (such as including a "<" in a file name on Windows), or if a specific type of path is required and is not given (e.g. an absoltue path vs. a relative path).</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Language.Errors.EndOfFileError
use Libraries.Containers.File
class Main
   action Main
      File file
      check
         file:SetRelativePath("C:\hello.txt") // see "Path" documentation for more information on absolute vs. relative paths.
      detect e is InvalidPathError
         // We will detect an error here, as "C:\hello.txt" is not a relative path!
         output "Bad path: " + e:GetErrorMessage()
      end
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Errors/Error.php">Libraries.Language.Errors.Error</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Errors/Error.php">Libraries.Language.Errors.Error</a> public action GetErrorMessage(), public action GetStackTrace(), public action PrintStackTrace(), public action SetErrorMessage(text message), public action SetStackTrace(Libraries.Containers.Array trace)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>