<?php $classPageTitle = "EndOfFileError"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Errors.EndOfFileError</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Errors.EndOfFileError" /><h2> <span class="controllable" data-componentType="class-name">Class EndOfFileError</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The EndOfFileError class is an error or exception that is thrown when an attempt is made to read from a file when the end of the file has been reached. For example, reading an empty file will quickly result in an EndOfFileError.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Language.Errors.EndOfFileError
use Libraries.Containers.File
class Main
   action Main
      File file
      check
         file:OpenForRead("empty.txt")
         text line1 = file:ReadLine()
         text line2 = file:ReadLine() // will raise EndOfFileError
      detect e is EndOfFileError
         output "Reached end of file: " + e:GetErrorMessage()
      end
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Errors/Error.php">Libraries.Language.Errors.Error</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Errors/Error.php">Libraries.Language.Errors.Error</a> public action GetErrorMessage(), public action GetStackTrace(), public action PrintStackTrace(), public action SetErrorMessage(text message), public action SetStackTrace(Libraries.Containers.Array trace)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>