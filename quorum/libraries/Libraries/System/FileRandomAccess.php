<?php $classPageTitle = "FileRandomAccess"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.FileRandomAccess</h1>
<input type="hidden" id="classkey" value="Libraries.System.FileRandomAccess" /><h2> <span class="controllable" data-componentType="class-name">Class FileRandomAccess</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Ths class represents a "random access" file reader/writer. It is the standard file random access reader/writer used in Quorum. By "random access," we mean that it is possible to read (or write) bytes of data at the beginning of the file, and then move to a different arbitrary location, such as the end of the file, and do the same. This is most commonly used for reading/writing binary files. Unlike the sequential reader and writer classes in Quorum, (see FileReader and FileWriter, respectively) FileRandomAccess permits both reading and writing to a file simultaneously. This class is not recommended for use with plain text files for efficiency reasons. Use the FileReader and FileWriter classes for plain text files.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Open a file for random access and write a byte to it.
File f
f:SetPath("settings.dat")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
// Write a single byte to the beginning of the file. This will overwrite
// whatever was there previously.
ra:Write("a")
// Always be sure to close files.
ra:Close()
// Read the first byte from a file, and then consume the rest.
f:SetPath("mySound.wav")
ra:OpenForRandomAccess(f)
// Get the first byte.
text firstByte = ra:Read(1)
// Consume the rest of the data.
text theRestOfTheBytes = ra:Read()
// Close the file.
ra:Close()
// Open a file and read the first 60 bytes.
f:SetPath("../familyPhoto.img")
ra:OpenForRandomAccess()
// Get the first 60 bytes (which might be, for example, the header of a binary file format).
text header = ra:Read(60)
// Close the file.
ra:Close()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/System/Blueprints/FileRandomAccessBlueprint.php">Libraries.System.Blueprints.FileRandomAccessBlueprint</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public system action Close()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Close"><p>When a file is opened for random access, the operating system keeps track of this and prevents other applications from opening the file. After we have finished manipulating our file, it is important that we tell the operating system that we wish to relinquish our hold on this file, so that other programs may use it. We do this using the Close() action.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Close"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Open a file, read a byte from it, and then close it.
File f
f:SetPath("C:\test.txt")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
// Read the first byte.
output ra:Read(1)
// Always be sure to Close() files, so that other programs may use it.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPosition">public system action GetPosition()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPosition"><p>Get the current position in the file. With random access files, it is possible to manipulate different parts of the file, with no particular order requirements, ulike with sequential files. The location we wish to manipulate is called the "position," and it is possible to change this position as well as retrieve it. The position changes any time we call SetPosition() or perform a read or write operation. This action tells us the current position. Positions are zero-indexed; that is, the minimum position is 0 and there is no limit on the maximum position.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: our current position in the file. This will always be a whole number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPosition"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess

// Open a file, write the string "hello" to it, and then find out what our
// position is.
f:SetPath("mySettings.dat")
FileRandomAccess ra
ra:OpenForRandomAccess(f)

// Write "hello world" to the file.
ra:Write("hello world")

// Get our current position.
number position = ra:GetPosition()
output "We are now at position " + position // As we started at the beginning of the file (position 0) and wrote an 11 character string, our new position will be 11.
// Always be sure to close files.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GoToBeginning">public action GoToBeginning()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GoToBeginning"><p>This is a convenience action. Calling this action is the same as calling SetPosition(0), as positions are zero-indexed.</p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsAtEndOfFile">public system action IsAtEndOfFile()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsAtEndOfFile"><p>Determine if the end of the file has been reached. When we reach the end of the file, it is no longer possible to read content from the file, unless the pointer is changed to a different location before the end of the file. In random access files, unlike sequential files, the end of the file is flexible; that is, if we write data beyond the previous size of the file, the "end of file" marker moves forward.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the end of the file has been reached; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsAtEndOfFile"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Open a file and read 3 bytes. If the file is only 3 bytes long, we
// will have reached the end of the file. If the file is less than 3 bytes, or doesn't exist, the
// Read() action will throw an EndOfFileError.
File f
f:SetPath("essay.doc")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
// Read 3 bytes.
text bytes = ra:Read(3)
// Are we at the end of the file?
output "We are at the end of the file: " + ra:IsAtEndOfFile()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForRandomAccess:Libraries.System.File">public action OpenForRandomAccess(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForRandomAccess:Libraries.System.File"><p>Open the specified File object for random access. This permits us to both read and write to the file. The contents of the file will be preserved if the file already exists. When a file is open for random access, we can perform read and write operations from any arbitrary location in the file. This location is tracked in the FileRandomAccess instance and can be changed using actions such as GetPosition() and SetPosition().</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForRandomAccess:Libraries.System.File"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Open a file for random access and write a byte to it.
File f
f:SetPath("settings.dat")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
// Write a single byte to the beginning of the file. This will overwrite
// whatever was there previously.
ra:Write("a")
// Always be sure to close files.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Read">public action Read()</span></h3>

	<p>Read from the current position to the end of the file. This is useful for consuming the rest of the data in a file after a certain point. As an example, if we were parsing WAVE sound files, we may wish to process header information and then consume the sample data after the headers that continues until the end of the file. The position is set to the end of the file after this action executes.</p><span class="controllable" data-componentType="action-description" data-actionkey="Read"><p>If the end of the file has been reached, an EndOfFileError will be raised. If any other complications arise, an InputOutputError will be raised.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: returns the read bytes from the file.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Read"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Read the first byte from a file, and then consume the rest.
File f
f:SetPath("mySound.wav")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
// Get the first byte.
text firstByte = ra:Read(1)

// Consume the rest of the data.
text theRestOfTheBytes = ra:Read()
// Close the file.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Read:integer">public action Read(integer numberOfBytes)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Read:integer"><p>Read from the current position the given number of bytes. This is useful if, for example, we are reading a certain type of image file and know that the header is exactly 60 bytes. This advances the pointer to the current position plus numberOfBytes. If the end of the file is reached, as many bytes are read as possible.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="numberOfBytes"><strong>integer</strong> <em>numberOfBytes</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="numberOfBytes">the number of bytes we wish to read after the current position in the file.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the read bytes. The length of this string may be less than the actual number of bytes we wished to read, if, for example, the end of the file is reached.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Read:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Open a file and read the first 60 bytes.
File f
f:SetPath("../familyPhoto.img")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
// Get the first 60 bytes (which might be, for example, the header of a binary file format).
text header = ra:Read(60)
// Close the file.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ReadLine">public action ReadLine()</span></h3>

	<p>Read from the current position until a newline sequence is encountered. A newline sequence is the sequence of characters created when the "enter" button is pressed in a text editor. The bytes required to make a newline sequence depends on the operating system. This action is slightly less useful for binary formats and may jump to unpredicted locations in non-plain text files. One should be especially cautious when using this action for Unicode character encodings, as they are not supported.</p><span class="controllable" data-componentType="action-description" data-actionkey="ReadLine"><p>The pointer is advanced the length of the read line plus the length of the newline sequence, which is typically one or two bytes.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the read line, minus the newline sequence.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ReadLine"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Read the first line from a plain text file.
File f
f:SetPath("dataset.txt")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
output ra:ReadLine() // grab the first line

// Close the file.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ReadLines">public action ReadLines()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ReadLines"><p>Read all remaining lines in the file. The same warnings from the ReadLine() action apply. The position will be advanced to the end of the file after this action executes.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Indexed</strong>: an indexable collection of text items representing the lines in the file.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ReadLines"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
use Libraries.Containers.Blueprints.Indexed
// Read all the lines from a file, print the first and third.
File f
f:SetPath("I_Contain_Three_Lines.txt")
FileRandomAccess ra
ra:OpenForRandomAccess(f)
// Get all lines.
Indexed&lt;text&gt; lines = ra:ReadLines()
output lines:Get(0) // first line
output lines:Get(2) // third line
// Close the file.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPosition:number">public action SetPosition(number position)</span></h3>

	<p>Set the current position in the file. As described in GetPosition(), the position in the file is where we wish to perform operations. The position determines where reading and writing occur in the file. The position is zero-indexed, that is, the minimum position is 0 and there is no limit to the maximum position. If the position is less than zero, an InvalidArgumentError will be raised. If for some reason the position change fails, an InputOutputError will be raised.</p><span class="controllable" data-componentType="action-description" data-actionkey="SetPosition:number"><p>Note that in other implementations of random access files, this operation is known as "seeking."</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="position"><strong>number</strong> <em>position</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="position">the position we wish to go to in the file. This must be greater than or equal to zero, and may extend beyond the current file size.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPosition:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Open a file, seek to byte 20 and write a string.
File f
f:SetPath("randomAccessTest.txt")
FileRandomAccess ra
ra:OpenForRandomAccess(f)

// Seek to position 20 (the 21st byte of the file, as positions are zero-indexed).
ra:SetPosition(20)

// Write the string "after the 21st byte" to the file.
ra:Write("after the 21st byte")
// Assuming the file was empty from the start, the file will not look like so:
// "                     after the 21st byte"
// This is because we skipped over the first 21 bytes.
// Close the file.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Write:text">public action Write(text textToWrite)</span></h3>

	<p>Write the specified string to the file starting at the current position. This will advance the position in the file to the current position plus the length of the string.</p><span class="controllable" data-componentType="action-description" data-actionkey="Write:text"><p>If an error is encountered during writing, an InputOutputError will be raised.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="textToWrite"><strong>text</strong> <em>textToWrite</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="textToWrite">the text we wish to write to the file.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Write:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Write "abcd" to the file, from the third byte onward.
File f
f:SetPath("alphabet_empty.txt")
FileRandomAccess ra
ra:OpenForRandomAccess(f)

// Go to position 2 (byte 3).
ra:SetPosition(2)
// Write "abcd"
ra:Write("abcd")
// If the file was empty when we started, the file now looks like this:
// "   abcd"
// Close the file.
ra:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="WriteLine:text">public action WriteLine(text textToWrite)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="WriteLine:text"><p>Write the given string to the file starting from the current position, and add the appropriate newline sequence on to the end. A newline sequence is the sequence of bytes crated when one presses the "enter" key in a text editor. This will advance the pointer from the current position to the current position plus the length of the string, plus the length of the newline sequence. The length of the newline sequence depends upon the operating system, but it is typically two characters (on Widows) or one character (on Mac and other Unix based systems).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="textToWrite"><strong>text</strong> <em>textToWrite</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="textToWrite">the string we wish to write to the file starting at the current position.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="WriteLine:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.FileRandomAccess
// Write two lines to a file.
File f
f:SetPath("my_two_line_file.txt")
FileRandomAccess ra

// Write the two lines.
ra:WriteLine("line 1")
ra:WriteLine("line 2")
// Close the file.
ra:Close()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>