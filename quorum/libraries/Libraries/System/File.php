<?php $classPageTitle = "File"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.File</h1>
<input type="hidden" id="classkey" value="Libraries.System.File" /><h2> <span class="controllable" data-componentType="class-name">Class File</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The File class is used to access and gather information about files on disk. This class can represent either a file or a directory, and provides actions for both manipulation and gathering of file data (such as file name, last time modified, etc). This class is not used for modifying the contents of files, but actions are provided that allow this functionality through other classes, such as OpenForRead(). For more information on modifying the contents of files, see the FileReader, FileWriter and FileRandomAccess classes.  Upon creation of a File instance, the File object refers to the program's working directory. The "working directory" of a program is the directory in which a program looks for files. The path that the File instance refers to can be changed using two actions: SetWorkingDirectory and SetPath. The File class only permits setting paths in a relative manner. Paths are relative to the working directory of the given instance of File, which may be changed using the SetWorkingDirectory action. The working directory must be an absolute path and must refer to a directory. See the examples below for common use cases of File.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.System.File
class Main
   action main
      // Read the contents of a file and output them to the screen.
      // This file is in our program's directory.
      File inputFile
      inputFile:SetPath("hello.txt")
      output inputFile:Read() // read and output  all contents to screen.
      
      // Write text to a file, erasing all old contents.
      // This file is located in the C:\Windows folder.
      File outputFile
      outputFile:SetWorkingDirectory("C:\Windows")
      outputFile:SetPath("secret_file.txt")
      outputFile:Write("hello there. this is a new file.")
      
      // Print the working directory of a new File instance. This is, by default,
      // the folder in which our program is stored, i.e.
      // C:\Users\jeff\My Documents\NetBeansProjects\TestProject
      File newFile
      output newFile:GetWorkingDirectory() // this action always returns the working directory.
      // To get the paths a particular File instance refers to, we use the
      // GetPath() and GetAbsolutePath() actions.
      File someFile
      someFile:SetWorkingDirectory("C:\Users\jeff")
      someFile:SetPath("settings.txt")
      output someFile:GetPath() // prints settings.txt.
      output someFile:GetAbsolutePath() // prints C:\Users\jeff\settings.txt
      output someFile:GetWorkingDirectory() // prints C:\Users\jeff
      // Find out if this file is a directory or not.
      File mysteryFile
      mysteryFile:SetPath("images") // assuming a file or directory exists in our working directory called 'images'.
      output "The mystery file object is a file: " + mysteryFile:IsFile()
      output "The mystery file object is a directory: " + mysteryFile:IsDirectory()
   end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public action Close()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Close"><p>If this file has been opened for reading, writing, or random access (see the actions OpenForRead(), OpenForWrite(), OpenForWriteAppend() and OpenForRandomAccess()), this action should be called to "close" the file. "Closing" a file means releasing it back to the operating system so that other programs may use it.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Close"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.Blueprints.FileWriterBlueprint
// Open a file for sequential writing and write a few lines. Then, close it.
File f
f:SetWorkingDirectory("/Users/jeff/")
f:SetPath("dictionary.txt")
FileWriterBlueprint writer = f:OpenForWrite()
// Create a small dictionary.
writer:WriteLine("abstraction")
writer:WriteLine("kafkaesque")
writer:WriteLine("quarry")
f:Close() // we're done with the file</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CreateDirectory">public system action CreateDirectory()</span></h3>

	<p>Create the directory specified in the path by this File object. This can only be used to create a single directory; it is not possible to create an entire path using this action. As an example, if the path "C:\blah\bb" does not already exist under "C:\", we cannot create all three directories in this path:</p><p>C:\blah\bb\foo</p><span class="controllable" data-componentType="action-description" data-actionkey="CreateDirectory"><p>Rather, we would create each individually (see example below).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if directory creation succeeds; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CreateDirectory"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Create a directory in /Users/jeff called "oldMusic".
File oldMusicDir
oldMusicDir:SetWorkingDirectory("/Users/jeff")
oldMusicDir:SetPath("oldMusic")
oldMusicDir:CreateDirectory()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Delete">public system action Delete()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Delete"><p>Delete the path specified by this File object. This operation does not send items to the appropriate recycle bin or trash can folder on the system, and it cannot be easily undone (if at all). This operation can only be used to delete single files or empty directories.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the operation succeeds; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Delete"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Create a directory and delete it.
File myDir
myDIr:SetWorkingDirectory("C:\Users\jeff\My DOcuments\")
myDir:SetPath("A_New_Folder")
myDir:CreateDirectory()
boolean ok = myDir:Delete() // delete it
if ok
   output "deletion succeeded."
   else
   output "deletion failed."
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Exists">public system action Exists()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Exists"><p>Test whether or not the path this File object refers to exists, that is, whether or not the path refers to a valid file or directory.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the file or directory exists; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Exists"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Does a folder called "Windows" exist under C:\? On Windows systems, it will;
// on other systems, it will not.
File winDir
winDir:SetWorkingDirectory("C:\")
winDir:SetPath("Windows")
output "Does 'C:\Windows' exist?  " + winDir:Exists() // on Windows systems, will output  "Does 'C:\Windows' exist? true"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAbsolutePath">public action GetAbsolutePath()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAbsolutePath"><p>Get the ABSOLUTE path this File object represents. Essentially, this is the same concept as GetPath, but the path is "absolute", that is, it is NOT dependent on the working directory of a File object. The absolute path is the working directory of File and the path of File concatenated together.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the absolute path this File object represents.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAbsolutePath"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Construct a file object to refer to "C:\Windows\System32".
File sys
sys:SetWorkingDirectory("C:\Windows")
sys:SetPath("System32")
output sys:GetPath() // prints "System32"
output sys:GetAbsolutePath() // prints "C:\Windows\System32"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDirectoryListing">public action GetDirectoryListing()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDirectoryListing"><p>Obtain a list of files and directories in the directory specified by this File object. If this File object does not represent a directory, undefined is returned.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: an indexable collection of File objects representing files and folders in the path of this File object. Returns undefined if this File object does not point to a directory.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDirectoryListing"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.Containers.Array
// Get the first item (alphabetical order) listed in C:\.
File cDrive
cDrive:SetWorkingDirectory("C:\")
Array&lt;File&gt; items = cDrive:GetDirectoryListing()
File f0 = items:Get(0)
output f0:GetPath()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFileExtension">public system action GetFileExtension()</span></h3>

	<p>Obtain the file extension of the path this File object represents. A file extension is any text after the last '.' character in a path; files and directories can have extensions. For example, the path</p><p>C:\Users\jeff\hello.txt</p><p>has the extension "txt". Similarly, the path below, although it represents a directory on Macintosh systems, has the extension "app".</p><p>/Applications/Sodbeans.app</p><span class="controllable" data-componentType="action-description" data-actionkey="GetFileExtension"><p>If no '.' character exists in the path, there is no extension in the path, and an empty string is returned.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The file extension or "" if no extension is present. The '.' character will not be present in the return value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFileExtension"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Get the extension of a music file.
File musicFile
musicFile:SetWorkingDirectory("C:\Users\jeff\Music")
musicFile:SetPath("Untitled.mp3")
output musicFile:GetFileExtension() // prints "mp3" on all systems.
// Get the extension of a directory.
File appDir
appDir:SetWorkingDirectory("/Applications/")
appDir:SetPath("TextEdit.app")
output appDir:GetFileExtension() // prints "app" on all systems.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFileName">public system action GetFileName()</span></h3>

	<p>Obtain the last part of the path name for this File object. This will be the last file or directory in the path. For example, if we had a File object that represented the path</p><p>C:\Users\jeff\My Documents</p><p>this action would return "My Documents." Similarly, if we had a File object that represented the path</p><p>C:\Users\jeff\My Documents\homework.doc</p><span class="controllable" data-componentType="action-description" data-actionkey="GetFileName"><p>this action would return "homework.doc". This action always returns the last thing in the path, so if our path is simply "C:\", this action will return "C:\". If our path is "/", this action will return an empty string ("").</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The last portion of the path name. This may be a file name, directory name, or drive letter.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFileName"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Get the file name from this path.
text path = "C:\Program Files\Sodbeans\bin\sodbeans.exe"
File file
file:SetWorkingDirectory("C:\Program Files\Sodbeans\bin\")
file:SetPath("sodbeans.exe")
output file:GetFileName() // will output  "sodbeans.exe". (on any system)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFileSize">public system action GetFileSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFileSize"><p>Get the total size of the file represented by this File object. This is the total number of bytes this file contains, but doesn't necessarily say how much disk space this file takes on disk. This action only returns a meaningful value for files; for directories, the value is always 0.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the file size in bytes. This may be 0 if the file does not exist, the path is a directory, or the path is invalid.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFileSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// How big is the "notepad" program on Windows?
File notepad
notepad:SetWorkingDirectory("C:\Windows")
notepad:SetPath("notepad.exe")
output "Notepad is " + notepad:GetFileSize() + " bytes."</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFreeDiskSpace">public system action GetFreeDiskSpace()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFreeDiskSpace"><p>Obtain the amount of unallocated hard drive space on the drive this path represents. For example, if our path starts with "C:\" on Windows systems, this will return the amount of free disk space on the "C" drive. On Unix and Mac OS X systems, the drive being referenced depends greatly on the system configuration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the amount of unallocated hard drive space in bytes. This value will always be a whole number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFreeDiskSpace"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// How much disk space is available on C in gigabytes?
File cDir
cDir:SetWorkingDirectory("C:\")
number amount = cDir:GetFreeDiskSpace() / 1024 / 1024 / 1024 // conversion from bytes to kilobytes, kilobytes to megabytes, megabytes to gigabytes
output "Drive C has " + amount + " GB free."
// How much disk space is available on the root drive? (Mac and Unix/Linux only).
File root
root:SetWorkingDirectory"/")
amount = root:GetFreeDiskSpace() / 1024 / 1024 / 1024 // conversion from bytes to kilobytes, kilobytes to megabytes, megabytes to gigabytes
output "The root drive has " + amount + " GB free."</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLastModifiedDate">public action GetLastModifiedDate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLastModifiedDate"><p>Obtain the date and time this file or directory was last modified. Typically, this is the date and time either the contents of the file (or directory) changed, or it was renamed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.System.DateTime</strong>: the modified date and time as a DateTime object, or undefined if the path represented by this File object does not exist.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLastModifiedDate"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.DateTime
// When was "Program Files" last modified?
File programFiles
programFiles:SetWorkingDirectory("C:\Program Files")
DateTime date
if date not= undefined
   output "Program files was last modified on" + date:GetMonth() + "/" + date:GetDayOfMonth() + "/" + date:GetYear()
   else
   output "Program files does not have any data. This is likely not a Windows system."
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetParentDirectory">public action GetParentDirectory()</span></h3>

	<p>Get the parent directory of this particular File object. The parent directory is the directory immediately above the file or directory this File object points in to. For example, if our File object represents the path</p><p>C:\Program Files\Sodbeans</p><p>the parent directory is "C:\Program Files"</p><p>If our File object represents a file, such as</p><p>/Users/jeff/hello.txt</p><span class="controllable" data-componentType="action-description" data-actionkey="GetParentDirectory"><p>the parent directory is /Users/jeff. In general, it is the path up to the last forward or backward slash. If there is no parent directory, as in the case of paths such as "C:\" or "/", undefined is returned.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.System.File</strong>: The parent directory of this File object as a new File object, or undefined if no parent exists.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetParentDirectory"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Get the parent directory of our working directory (remember, File points to our working directory if we don't set a path).
File workingDir
File parentDir = workingDir:GetParentDirectory()
// Is there a parent directory?
if parentDir not= undefined
   output parentDir:GetPath()
   else
   output "There is no parent to our working directory."
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPath">public action GetPath()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPath"><p>Get the path associated with this File object. If a File class is instantiated and the action SetPath has been called, then this will return an empty string. Otherwise, it will return the relative path set for this File instance. (Relative to the current working directory).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the path this file represents, or an empty string.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPath"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Set a path and get it back. This File object ultimately represents the
// path "/Users/jeff/Music"
File someFile
someFile:SetWorkingDirectory("/Users/jeff/")
someFile:SetPath("Music")
output someFile:GetPath() // prints "Music"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTotalDiskSpace">public system action GetTotalDiskSpace()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTotalDiskSpace"><p>Obtain the total amount of hard drive space on the drive this path represents. This is the total capacity of the disk. For example, if our path starts with "C:\" on Windows systems, this will return the capacity of the "C" drive. On Unix and Mac OS X systems, the drive being referenced depends greatly on the system configuration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the capacity of the hard disk in bytes. This value will always be a whole number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTotalDiskSpace"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// How much disk space is available on C in gigabytes?
File cDir
cDir:SetWorkingDirectory("C:\")
number amount = cDir:GetTotalDiskSpace() / 1024 / 1024 / 1024 // conversion from bytes to kilobytes, kilobytes to megabytes, megabytes to gigabytes
output "Drive C has a total capacity of " + amount + " GB."
// How much disk space is available on the root drive? (Mac and Unix/Linux only).
File root
root:SetWorkingDirectory("/")
amount = root:GetTotalDiskSpace() / 1024 / 1024 / 1024 // conversion from bytes to kilobytes, kilobytes to megabytes, megabytes to gigabytes
output "The root drive has a total capacity of  " + amount + " GB."</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWorkingDirectory">public action GetWorkingDirectory()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWorkingDirectory"><p>This action returns the "working directory" of our program. The "working directory" is the directory our program looks in by default when looking for files that aren't specified by an absolute path. For example, if we wish to open the path "dictionary.txt," this would refer to a file named "dictionary.txt" in our working directory. By default, the working directory is the directory where the program is stored. TODO: Update</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The directory our application is working in.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWorkingDirectory"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
File myFileObject
output "The working directory of this program is " + myFileObject:GetPath()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsDirectory">public system action IsDirectory()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsDirectory"><p>Test whether or not the path this File object refers to is a "directory." A directory is a folder on the file system that contains other files or directories.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the path of this File object is a directory; False if it is a directory or the path is invalid. (see Exists())</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsDirectory"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// On most systems, the path './' refers to the current directory.
// Is the current directory a directory?
File currentDir
currentDir:SetPath("./")
output "Are we in a directory? " + currentDir:IsDirectory() // On most systems, will output  "Are we in a directory? true"
// On Windows systems, is "C:\" a directory?
File cDrive
cDrive:SetWorkingDirectory("C:\")
output "Is C:\ a directory? " + cDrive:IsDirectory() // On Windows systems, will output  "Is C:\ a directory? true"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsFile">public system action IsFile()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsFile"><p>Determine whether or not the path this File object refers to is a "file," that is, an entity on the file system that is not a directory.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the path of this File object is a file; False if it is a directory or the path is invalid. (see Exists()).</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsFile"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Is C:\Windows a file?
File winDir
winDir:SetWorkingDirectory("C:\Windows")
output "Is C:\Windows a file? " + winDir:IsFile() // Will output  'Is C:\Windows a file? false'
// Is C:\Windows\System32\kernel32.dll a file?
File kernel32
kernel32:SetWorkingDirectory("C:\Windows\system32\")
kernel32:SetPath("ernel32.dll")
output "Is C:\Windows\System32\kernel32.dll a file? " + kernel32:IsFile() // On Windows systems, will output  "Is C:\Windows\System32\kernel32.dll a file? true"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsHidden">public system action IsHidden()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsHidden"><p>Test whether or not the path this File object refers to is a file with the "hidden" attribute set. "Hidden" files set an attribute, called "hidden," which hides them from the user by default. This attribute is not supported by all operating systems, so it is possible that this action will always return false on some systems.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the path of this File object is a file with the hidden attribute; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsHidden"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// On Windows systems, the "user32.dll" file is hidden.
File user32
user32:SetWorkingDirectory("C:\Windows\System32")
user32:SetPath("user32.dll")
output "Is user32.dll hidden? " + user32:IsHidden() // On Windows systems, will output  "Is user32.dll hidden? true"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Move:text">public system action Move(text newPath)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Move:text"><p>Move the file (or directory) specified to the new path specified. The behavior of this action is highly dependent on the platform and file system; always check the return value to ensure success.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newPath"><strong>text</strong> <em>newPath</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newPath">the new path to move the file to. May be an absolute path or a relative path.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the move succeeds; false otherwise.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Move:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Move a file from one directory to another.
File f
f:SetWorkingDirectory("/Users/jeff/Music/")
f:SetPath("Untitled.mp3")
boolean moveSucceeded = f:Move("/Users/jeff/oldMusic/Untitled.mp3")
if moveSucceeded
   output "Move succeeded."
   else
   output "Move failed."
end
// Move can also be used to rename a file.
File renameMe
renameMe:SetWorkingDirectory("/Users/jeff/Music/")
renameMe:SetPath("Untitled.mp3")
moveSucceeded = renameMe:Move("/Users/jeff/Music/Toccata and Fugue.mp3")
if moveSucceeded
   output "Move succeeded."
   else
   output "Move failed."
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForRandomAccess">public action OpenForRandomAccess()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForRandomAccess"><p>Open this file for random access. "Random access" implies that reading and/or writing to the file can occur in a non-sequential fashion. See FileRandomAccessBlueprint's documentaiton for more information.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.System.Blueprints.FileRandomAccessBlueprint</strong>: A FileRandomAccessBlueprint, sufficient for random access to the file's contents.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForRandomAccess"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.Blueprints.FileWriterBlueprint
// Open a file for sequential writing and write a few lines.
File f
f:SetWorkingDirectory("/Users/jeff/")
f:SetPath("dictionary.txt")
FileWriterBlueprint writer = f:OpenForWrite()
// Create a small dictionary.
writer:WriteLine("abstraction")
writer:WriteLine("kafkaesque")
writer:WriteLine("quarry")
f:Close() // we're done with the file, see Close() documentation.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForRead">public action OpenForRead()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForRead"><p>Open this file for sequential reading. See the documentation of FileReaderBlueprint for more information on sequential reading.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.System.Blueprints.FileReaderBlueprint</strong>: A FileReaderBlueprint that may be used to read this
        file sequentially.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForRead"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.Blueprints.FileReaderBlueprint
// Open a file for sequential reading and output all of its contents.
File f
f:SetWorkingDirectory("/Users/jeff/")
f:SetPath("dictionary.txt")
FileReaderBlueprint reader = f:OpenForRead()
output reader:Read()
f:Close() // we're done with the file, see Close() documentation.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForWrite">public action OpenForWrite()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForWrite"><p>Open this file for sequential writing. See the documentation of FileWriterBlueprint for more information on sequential writing.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.System.Blueprints.FileWriterBlueprint</strong>: A FileWriterBlueprint that may be used to write this
        file sequentially.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForWrite"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.Blueprints.FileWriterBlueprint
// Open a file for sequential writing and write a few lines.
File f
f:SetWorkingDirectory("/Users/jeff/")
f:SetPath("dictionary.txt")
FileWriterBlueprint writer = f:OpenForWrite()
// Create a small dictionary.
writer:WriteLine("abstraction")
writer:WriteLine("kafkaesque")
writer:WriteLine("quarry")
f:Close() // we're done with the file, see Close() documentation.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForWriteAppend">public action OpenForWriteAppend()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForWriteAppend"><p>Open this file for sequential writing. This differs from the OpenForWrite() action, as the previous contents of the file (if any) are preserved. See the documentation of FileWriterBlueprint for more information on sequential writing.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.System.Blueprints.FileWriterBlueprint</strong>: A fileWriterBlueprint that supports sequential file writing.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="OpenForWriteAppend"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.Blueprints.FileWriterBlueprint
// Open a file for sequential writing and write a few lines, preserving the old contents.
File f
f:SetWorkingDirectory("/Users/jeff/")
f:SetPath("dictionary.txt")
FileWriterBlueprint writer = f:OpenForWriteAppend()
// Add a few new words to our dictionary.
writer:WriteLine("concrete")
writer:WriteLine("freudian")
writer:WriteLine("steep")
f:Close() // we're done with the file, see Close() documentation.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Read">public action Read()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Read"><p>Read all of the contents of a file immediately into a string. When using this action, it is not required to call Close(). If the file does not exist, a FileNotFoundError will be raised. If the file cannot be opened for other reasons, an InputOutputError will be raised.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: everything in the file.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Read"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
// Open our dictionary and read all the contents at once. Print them.
File dict
dict:SetWorkingDirectory("/Users/jeff/")
dict:SetPath("dictionary.txt")
output dict:Read() //output  all the contents to screen.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPath:text">public action SetPath(text path)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPath:text"><p>Set the path this File represents. This path is what is called a "relative" path, that is, it does not represent a concrete path to some file. This path is relative to the working directory of this File object (see SetWorkingDirectory()).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="path"><strong>text</strong> <em>path</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="path">The relative path we wish to use for this File object.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPath:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.Path
// This File object will be constructed to refer to the "Program Files" directory
// on a Windows system. The working directory of "C:\" tells File to look for the path
// "Program Files" starting at "C:\". The full path then becomes "C:\Program Files".
File programFilesDir
programFilesDir:SetWorkingDirectory("C:\")
programFilesDir:SetPath("Program Files")
output "Is it a directory? " + programFilesDir:IsDirectory() // on Windows systems, prints "Is it a directory? true"</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWorkingDirectory:text">public action SetWorkingDirectory(text path)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWorkingDirectory:text"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="path"><strong>text</strong> <em>path</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="path"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Write:text">public action Write(text textToWrite)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Write:text"><p>Write all text contained in 'textToWrite' to the specified file. This will erase all previous contents of the file. If the file cannot be created, an InputOutputError will be raised.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="textToWrite"><strong>text</strong> <em>textToWrite</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="textToWrite"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Write:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.File
use Libraries.System.Blueprints.FileWriterBlueprint
// Write a single line to the file.
File f
f:SetWorkingDirectory("/Users/jeff/")
f:SetPath("ASimpleMessage.txt")
text message = "Anything I put in here will magically appear in the file."
f:Write(message)
// It is also possible to write multiple lines, like so:
f:SetWorkingDirectory("/Users/jeff/")
f:SetPath("AComplexMessage.txt")
message ="This is the first line
and here's another."
f:Write(message)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>