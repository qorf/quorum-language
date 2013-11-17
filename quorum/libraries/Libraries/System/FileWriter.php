<?php $classPageTitle = "FileWriter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.FileWriter</h1>
<input type="hidden" id="classkey" value="Libraries.System.FileWriter" /><h2> <span class="controllable" data-componentType="class-name">Class FileWriter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class represents a sequential file writer. It is the standard file writer used in Quorum. By "sequential," we mean that it is possible to only move forward in the file. Once data has been written to a position, it is impossible to go backwards in the file and modify it. This is most commonly used for writing plain text files.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileWriter
// Write three lines to an existing file.
File f
f:SetPath("anExistingFile.txt")
FileWriter writer
writer:OpenForWriteAppend(f)
// Write 3 lines.
writer:WriteLine("1")
writer:WriteLine("2")
writer:WriteLine("3")
// Close.
writer:Close()
// Open a new file and write "abcdefgh" to it.
f:SetPath("MySpecialDocument.dat")
writer:OpenForWrite(f)
// Write "abcdefgh" to the file in two separate writes.
writer:Write("abcd")
writer:Write("efgh")
// Always call Close().
writer:Close()
// The file contents are now "abcdefgh".</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/System/Blueprints/FileWriterBlueprint.php">Libraries.System.Blueprints.FileWriterBlueprint</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public system action Close()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Close"><p>When we open a file for writing, the operating system marks the file as unavailable for other applications. When we are finished writing contents to the file, we must notify the operating system that other files are free to use this file again. To do this, we muse call the Close() action. It is best practice to call Close() as soon as possible after all file contents have been written.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Close"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileWriter
// Open a file, write a line to it, and then close it.
File f
f:SetPath("newFile.txt")
FileWriter writer
writer:OpenForWrite(f)
// Write a line to it
writer:WriteLine("a line.")
// Close the file to let the operating system know the file is free.
writer:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForWrite:Libraries.System.File">public action OpenForWrite(Libraries.System.File file)</span></h3>

	<p>Open a file for sequential writing. By "sequential writing," it is meant that it is only possible to write forward in the file. Previously written content cannot be erased, nor can it be modified. This is useful for creating and modifying plain text files, but not so useful for binary files. To manipulate binary files, FileRandomAccess should be used.</p><span class="controllable" data-componentType="action-description" data-actionkey="OpenForWrite:Libraries.System.File"><p>When this action is used, any previous contents of the file will be destroyed; to preserve previous contents and add on to the end of the file, use OpenForWriteAppend().</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForWrite:Libraries.System.File"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileWriter
// Open a file for writing, write a line and close it.
File f
f:SetPath("myFile.txt") // open myFile.txt for writing (myFile.txt will appear in the working directory of our program)
FileWriter writer
writer:OpenForWrite(f)
// Write two lines.
writer:WriteLine("first line")
writer:WriteLine("second line")
// Close the file.
writer:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForWriteAppend:Libraries.System.File">public action OpenForWriteAppend(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForWriteAppend:Libraries.System.File"><p>Open a file for sequential writing, but preserve previous file contents. This action is very similar to OpenForWrite(), in that it is only possible to write in one direction, but instead of starting with a fresh file, we may also start with a file that has existing contents. We will append on to the end of the file when we write. If the file does not exist, a new one is created and the behavior is identical to OpenForWrite().</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForWriteAppend:Libraries.System.File"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileWriter

// Open a file, myFile.txt, for writing, but preserve the previous contents.
File f
f:SetPath("myFile.txt")
FileWriter writer
writer:OpenForWriteAppend(f)

// Add a couple lines (or possibly create a new file with only these contents -- if myFile.txt doesn't exist)
writer:WriteLine("i added this line today")
writer:WriteLine("it was pretty easy to do.")
// Close the file.
writer:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="PushToDisk">public system action PushToDisk()</span></h3>

	<p>When using FileWriter, the contents written to the file are not necessarily written directly to the hard drive immediately. This is for efficiency--as the hard drive (on most systems) is a physically moving platter, it is the slowest component on the system. As such, to save time, file contents are "buffered" and written at a later time.</p><span class="controllable" data-componentType="action-description" data-actionkey="PushToDisk"><p>Sometimes, it is necessary to ensure that the contents of our file are written at an exact moment in time. To do this, use the PushToDisk() action. This action should be used sparingly, as it is very inefficient. Typically, file contents are written to disk upon calling Close().</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="PushToDisk"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileWriter
// Write some content to a file, and call PushToDisk() for extra security.
File f
f:SetPath("mySettings.dat")
FileWriter writer
writer:OpenForWrite(f)
writer:WriteLine("value")
writer:WriteLine("anotherValue")

// Force the operating system to push the lines we have written to disk.
writer:PushToDisk() // this action may be very slow, depending on how much data needs to be written.
// Always be sure to call Close().
writer:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Write:text">public action Write(text textToWrite)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Write:text"><p>Writes the given text to the file. This text will be written immediatley after any previously written data (or after any previously existing contents, if the file is opened for appending). If an error occurs during the process, an InputOutputError will be raised.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="textToWrite"><strong>text</strong> <em>textToWrite</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="textToWrite">the text we wish to place in the file. Note
        that this does not create a new line in the file; use WriteLine() for this.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Write:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileWriter
// Open a new file and write "abcdefgh" to it.
File f
f:SetPath("MySpecialDocument.dat")
FileWriter writer
writer:OpenForWrite(f)
// Write "abcdefgh" to the file in two separate writes.
writer:Write("abcd")
writer:Write("efgh")
// Always call Close().
writer:Close()
// The file contents are now "abcdefgh".</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="WriteLine:text">public action WriteLine(text textToWrite)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="WriteLine:text"><p>Writes the given text to the file, but also appends a newline character on the end, making it possible to start the next writing operation on a new line. What newline character is inserted depends on the operating system. In conjunction with the FileReader's ReadLine() action, it is possible to quickly write and read contents to files consistently.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="textToWrite"><strong>text</strong> <em>textToWrite</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="textToWrite">the contents of the line we wish to write to the file.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="WriteLine:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileWriter
// Write three lines to an existing file.
File f
f:SetPath("anExistingFile.txt")
FileWriter writer
writer:OpenForWriteAppend(f)
// Write 3 lines.
writer:WriteLine("1")
writer:WriteLine("2")
writer:WriteLine("3")
// Close.
writer:Close()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>