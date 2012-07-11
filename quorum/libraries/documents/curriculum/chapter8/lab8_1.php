<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 8_1';
</script>

<h1>Lab 8.1: Reading and Writing to Files</h1>
<h2>Objectives</h2>
<p>The goal of this lab is to understand the following concepts:</p>
<ul><li>Directory structures and paths
</li><li>Using the File I/O classes in Quorum to read and write to files
</li><li>Writing applications which store data and retrieve it for use at a later time
</li></ul>
<h2>Overview</h2>
<p>
In this lab, we will introduce an important idea in computer programming: file input and output. Files are used to store data on the hard disk of a computer for retrieval at a later time. So far, our Quorum programs have only used data we have explicitly declared in the form of variable declarations, or input from the user. This lab will guide us through the fundamentals of reading and writing files, and demonstrate how files can be used to store important data for our programs to use. First, we will discuss some terminology related to files. We will then demonstrate use of the file input and output libraries available in Quorum. Finally, we will use this knowledge to write an address book application.
</p>
<h2>Task 0: Directory structures and paths</h2>
<p>
In your daily usage of computers, you may have used what are called <i>folders</i> to organize things such as photos, music or Microsoft Word documents. Folders, or <strong>directories</strong>, are structures which exist on the computer's hard drive which contain <strong>files</strong>. Files store data which we wish to keep long term, such as a song in a .MP3 format. We interact with files and directories daily when we use Windows Explorer on Microsoft Windows.
</p>
<p>
Today, hard drives may contain a significant amount of information. We use directories and files to organize this data, and we use <strong>paths</strong> to describe where these files live on the hard drive. You may have encountered paths when using computers. On Windows, paths often start with a drive letter, such as <tt>C:\</tt>, and end with the location of the file. For example, an essay might be stored in your My Documents folder, and would have a path such as <tt>C:\Users\Jeff\My Documents</tt>.
</p>
<h2>Task 1: The File, FileReader and FileWriter classes in Quorum</h2>
<p>
To start, we will need to introduce three new classes in Quorum. The first is the <tt>File</tt> class. A <tt>File</tt> object tells Quorum where a file lives on the hard disk. There are many actions the <tt>File</tt> class can perform, such as creating files, creating directories, getting information about when a file was last modified, etc. All classes in Quorum responsible for input and output to files utilize the <tt>File</tt> class.
</p>
<p>
The <tt>FileReader</tt> class allows us to read the contents from a file on the hard disk. This can be a file that we wrote ourselves using Quorum, or a file the user provides via input statements.
</p>
<p>
The <tt>FileWriter</tt> class permits writing to files. <tt>FileWriter</tt> can either create a new, blank file, or add to the contents of an existing file. The <tt>FileWriter</tt> class has two important modes:
</p>
<ul><li><strong>Overwrite</strong> - In this mode, when the <tt>FileWriter</tt> class is used on a file, if the file we wish to write already exists, its contents will be erased.
</li><li><strong>Append</strong> - In append mode, <tt>FileWriter</tt> will simply add whatever content we wish to write to the end of the file, after any existing contents.
</li></ul><h2>Task 2: Writing to Files</h2>
<p>
We will create a Quorum program that writes four lines to a file. In the next section, we will read the contents of this file back using Quorum.
</p>
<p>
Create a new blank Quorum project named <strong>Lab8_1Practice</strong>. To start, we will need to add the appropriate <tt>use</tt> statements to import the file classes we need. Import the <tt>Libraries.System.File</tt> class, as well as the <tt>Libraries.System.Blueprints.FileWriterBlueprint</tt> class.
</p>
<p>
Declare a variable of type <tt>text</tt> named <tt>path</tt>, and set its value to "thisIsMyFile.txt". Next, declare a variable of type <tt>File</tt> and name it <tt>myFile</tt>.In order for Quorum to understand that we wish to write to the file we specified in the <tt>path</tt> variable, we must set the path that the <tt>myFile</tt> object refers to. This is done using the <tt>SetPath</tt> action.
</p>
<p>
Once we have done the initial setup, we must tell Quorum we wish to open the file for writing purposes. The <tt>File</tt> object allows us to do this using the <tt>OpenForWrite</tt> action. Assign the return value of the <tt>OpenForWrite</tt> action to a variable named <tt>writer</tt>, of type <tt>FileWriterBlueprint</tt>.
</p>
<p>
Take a moment to explore the actions available in the <tt>FileWriterBlueprint</tt> class. The two actions we will use are the <tt>WriteLine</tt> and <tt>Close</tt> actions. As seen from the code completion window, the <tt>WriteLine</tt> action takes a single parameter, a <tt>text</tt> value. Use the <tt>WriteLine</tt> action four times to write lines of your choosing. After this, call the <tt>Close</tt> action from the <tt>writer</tt> object. Closing the file is a <strong>very</strong> important step when working with files, as it will ensure the contents we wrote using <tt>WriteLine</tt> are saved.
</p>
<p>
Run the program in Sodbeans. After the program closes, navigate to the projects window. Navigate through the <strong>Lab8_1Practice</strong> project, and notice a new file has appeared. This is the file we just created using Quorum code! Open it in Sodbeans; the contents we specified in each <tt>WriteLine</tt> call appear in the editor. Close this file before moving on to the next section.
</p>
<p>
The code below demonstrates the final product of this section.
</p>
<pre class="code">use Libraries.System.File
use Libraries.System.Blueprints.FileWriterBlueprint

// Here, we say the name of the file we wish to create.
text path = "thisIsMyFile.txt"

// Create a File object, set its path and open it for writing.
File myFile
myFile:SetPath(path)
FileWriterBlueprint writer = myFile:OpenForWrite()

// Write four lines to our new file.
writer:WriteLine("hello")
writer:WriteLine("from")
writer:WriteLine("the")
writer:WriteLine("Quorum Programming Language.")

// Close the file to save the contents.
writer:Close()
</pre><h2>Task 3: Reading the Contents of Files</h2>
<p>
After writing contents to a file, the next critical step is to read back the information we stored. Reading information from files is common practice in much of computer programming. For example, when one logs into Facebook or Twitter, the computer is reading your login information from a file on disk to ensure that your password is correct, load your friends list, etc.
</p>
<p>
While still working in the <strong>Lab8_1Practice</strong> project, comment out or delete all code following the line calling the <tt>SetPath</tt> action. As we now wish to use the <tt>FileReader</tt> class, add a use statement at the top of the file for the library <tt>Libraries.System.Blueprints.FileReaderBlueprint</tt>.
</p>
<p>
Opening a file for reading happens in a much similar way to opening a file for writing. Call the <tt>OpenForRead</tt> action on the <tt>myFile</tt> object in our code. Assign the return value to a variable named <tt>reader</tt>, of type <tt>FileReaderBlueprint</tt>.
</p>
<p>
Take a bit of time to investigate the actions available in the <tt>reader</tt> object, using code completion. The primary actions we are concerned with are <tt>ReadLine</tt>, <tt>Close</tt> and <tt>IsAtEndOfFile</tt>. While the first two are similar to the actions discussed for writing files, the third is new. When we read a file, we must be able to tell the computer when to stop reading from the hard disk. Otherwise, our program won't know precisely when our file contents end, and we may not get back the information we put in! This is done using a <tt>repeat until</tt> loop, with the <tt>IsAtEndOfFile</tt> action. Copy the code below into the <strong>Lab8_1Practice</strong> project.
</p>
<pre class="code">repeat until reader:IsAtEndOfFile()
    say reader:ReadLine()
end
</pre><p>
This code tells Quorum to read each individual line from the file we wrote previously, and then speak them out loud. Quorum will do this until there are no lines left in our file. Remember to call the <tt>Close</tt> action on the <tt>reader</tt> variable, as we did in the previous section when writing files.
</p>
<p>
Run the program. Notice that the lines we wrote to the file earlier are now being read back aloud. We have successfully stored information, and retrieved it back!
</p>
<h2>Task 4: Application: Address Book</h2>
<p>
The file manipulation techniques we have covered so far are quite powerful, and may be used to create many types of applications that require long-term storage of information. In this section of the lab, we will tie together the concepts we have learned and use them to write an address book application. This program will allow users to either <i>add a contact</i> or <i>list all contacts</i>. Each contact will have a name and email address.
</p>
<p>
Using the <tt>input</tt> keyword, control structures, and the File I/O techniques learned in this lab, build the address book application outlined above. Create a new project named <strong>Lab8_1</strong>. You are encouraged to use code from the previous parts of this lab in your final solution. Make use of the <tt>File</tt>, <tt>FileReaderBlueprint</tt>, and <tt>FileWriterBlueprint</tt> classes. Store the address book contents in a file named "addressBook.txt". Unlike in section two, use the <tt>OpenForWriteAppend</tt> action rather than the <tt>OpenForWrite</tt> action, so the contents of the address book aren't lost each time a contact is added.
</p>
<p>
A few example runs of the address book application are shown below.
</p>
<p>
Adding a contact:
</p>
<pre class="code">[input dialog] To add a contact, type A. Otherwise, type L to list all contacts.
[user input] A
[input dialog] What is the contact's name?
[user input] Jeff
[input dialog] What is the contact's email address?
[user input] jeff@mail.com
[output] Done. Contact added. Goodbye.
</pre><p>
Listing all contacts:
</p>
<pre class="code">[input dialog] To add a contact, type A. Otherwise, type L to list all contacts.
[user input] L
[output] You have the following contacts:
[output] Jeff jeff@mail.com
[output] Done. Contacts listed. Goodbye.
</pre><p>
Feel free to work with neighbors or ask the instructor questions. 
</p>

<?php include("../../include/footer.php"); ?>