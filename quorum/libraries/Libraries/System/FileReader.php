<?php $classPageTitle = "FileReader"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.FileReader</h1>
<input type="hidden" id="classkey" value="Libraries.System.FileReader" /><h2> <span class="controllable" data-componentType="class-name">Class FileReader</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
TODO: Check to make sure a file is opened.  This class represents a sequential file reader. It is the standard file reader used in Quorum. By "sequential," we mean that it is possible to only move forward in the file; re-reading data is not possible. This is most commonly used for reading plain text files.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileReader
// Open a file, read all of its contents and close it.
File f
f:SetPath("/hello.txt")
FileReader reader
reader:OpenForRead(f)
text fileContents = reader:Read()
output "The contents are: " + fileContents
reader:Close()
// Open a file, read the first two lines and close it.
f:SetPath("./settings.dat")
reader:OpenForRead(f)
text line1 = reader:ReadLine()
text line2 = reader:ReadLine()
reader:Close()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/System/Blueprints/FileReaderBlueprint.php">Libraries.System.Blueprints.FileReaderBlueprint</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public system action Close()</span></h3>

	<p>When we open a file for reading, it is marked by the operating system as being in use by our application. To make it possible for other files to use this file at a later point in time, it is necessary to tell the operating system when we are done with this file. To do this, we use the Close() action. We should call Close() as soon as we know that a file is no longer needed. If no file is open, Close() will do nothing.</p><span class="controllable" data-componentType="action-description" data-actionkey="Close"><p>After Close() is called, a FileReader object is considered invalid, and no actions should be called on it, other than OpenForRead(), to open the same (or different) file.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Close"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileReader
// Open a file, read all the contents and close it.
File f
f:SetPath("To Do List.txt")
FileReader reader
reader:OpenForRead(f)
output "Tim, your to do list says:"
output reader:Read()

// Close the file, as we no longer need it.
reader:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsAtEndOfFile">public system action IsAtEndOfFile()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsAtEndOfFile"><p>Determine whether or not the end of the file has been reached. The "end of file" signifies that no further text can be read from the file.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if the end of the file has been detected; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsAtEndOfFile"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileReader
// Open the file for reading.
File f
f:SetPath("names.txt")
FileReader reader
reader:OpenForRead(f)
// Read all the contents.
text names = reader:Read()
// We are now at the end of the file.
boolean eof = reader:IsAtEndOfFile()
if eof
   output "End of File has been reached."
end
// Always close the file.
reader:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForRead:Libraries.System.File">public action OpenForRead(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForRead:Libraries.System.File"><p>Open a file for sequential reading. By "sequential reading," it is meant that it is impossible to go backwards in the file--we must make progress toward the end of the file with each successive read. This is extremely useful for reading plain text documents, but is much less useful for reading binary files. Binary files should use the FileRandomAccess class.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForRead:Libraries.System.File"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileReader
// Open the file for reading.
File f
f:SetPath("homework.txt")

// Read the first two lines and then be done.
FileReader reader
reader:OpenForRead(f)
output reader:ReadLine()
output reader:ReadLine()
// Always close the file.
reader:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Read">public action Read()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Read"><p>This action reads all of the contents of our file from the current position. When we open a file, we start at the beginning--so calling this action immediately after opening a file will read the entire file. If we perform some other reading action, such as ReadLine(), and then call this action, we will receive all file contents after the point where we have read. If the end of file has already been reached before calling this action, an EndOfFileError will be raised.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the file contents from our current position to the end of the file.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Read"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileReader
// Open a file, read all of its contents to a text value and then  output the text value.
File f
f:SetPath("Settings.dat")
FileReader reader
reader:OpenForRead(f)
text settings = reader:Read()
output "The settings are:"
output settings
reader:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Read:integer">public action Read(integer numberOfBytes)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Read:integer"><p>This action reads the specified number of bytes after our current position. When we open a file, we start at the beginning--calling this action with a parameter of, for example, 2, would read the first two bytes of the file. If we perform some other reading action such as ReadLine(), and then call this action, the specified number of bytes will be read after our current position. If the number of bytes specified exceeds the end of the file, content up to the end of the file will be returned. If the end of the file has been reached before this action is called, an EndOfFileError will be raised.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="numberOfBytes"><strong>integer</strong> <em>numberOfBytes</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="numberOfBytes">the number of bytes we wish to read after our current location in the file. This must be a positive number; if it is not, an InvalidArgumentError is raised.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the file contents we have read</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Read:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileReader
// Open a file, output the first four bytes.
File f
f:SetPath("Settings.dat")
FileReader reader
reader:OpenForRead(f)
output "The first four bytes are " + reader:Read(4)
reader:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ReadLine">public action ReadLine()</span></h3>

	<p>Read a single line from the file. What constitutes a line is dependent upon the operating system. In general, a "line" is created by pressing the enter key in a text editor. Quite often, file contents are delimited by separate lines. For example, a file containing a list of words may have the contents,</p><p>"hello textual inquire fizz"</p><span class="controllable" data-componentType="action-description" data-actionkey="ReadLine"><p>which may be easily read one at a time using the ReadLine() action.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the text read from the line.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ReadLine"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileReader
// Open a file, read all of its contents to a text value and then output the text value.
File f
f:SetPath("words.txt")
FileReader reader
reader:OpenForRead(f)
// We will assume 'words.txt' separates words by lines. Read the first two words.
output reader:ReadLine()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ReadLines">public action ReadLines()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ReadLines"><p>This action reads all remaining lines from the file, starting from our current position and moving to the end of the file.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Indexed</strong>: an indexable collection of text objects, representing all read lines.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ReadLines"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Blueprints.Indexed
use Libraries.System.File
use Libraries.System.FileReader

// Read all lines into a collection and output the third line. If there is no third line, the appropriate error will be raised.
File f
f:SetPath("/Users/jeff/helloWorld.txt")
FileReader reader
reader:OpenForRead(f)
Indexed&lt;text&gt; lines = reader:ReadLines()
output "The third line is " + lines:Get(2) // indices are zero-based.
reader:Close()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>