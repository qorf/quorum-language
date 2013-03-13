<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 8_1';
</script>

<h1>Lab 8.1: Reading and Writing to Files</h1>
<h2>Objectives</h2>
<p>The goal of this lab is to learn the following:</p>
<ul>
    <li>
        How to use directory structures and paths
    </li>
    <li>
        How to use the File I/O classes in Quorum
    </li>
    <li>
        How to write applications which store data and retrieve it for use at a later time
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this lab, you will learn about an important aspect of computer programming: file input and output. Files are used to store data on the hard disk of a computer for retrieval at a later time. So far, your Quorum programs have only used data you have explicitly declared in the form of variable declarations, or input from the user. This lab will guide you through the fundamentals of reading and writing files, and demonstrate how files can be used to store important data for your programs to use.
</p>
<p>
    In your daily usage of computers, you may have used what are called folders to organize things such as photos, music or Microsoft Word documents. Folders, or directories, are structures which exist on the computer's hard drive which contain files. Files store data which you wish to keep long term, such as a song in a .MP3 format. You interact with files and directories daily when you use Windows Explorer on Microsoft Windows.
</p>
<p>
    Today, hard drives may contain a significant amount of information. You use directories and files to organize this data, and you use paths to describe where these files live on the hard drive. You may have encountered paths when using computers. On Windows, paths often start with a drive letter, such as C:\, and end with the location of the file. For example, an essay might be stored in your My Documents folder, and would have a path such as C:\Users\Jeff\My Documents.
</p>
<p>
    In this lab, you will be using three new classes in Quorum. The first is the File class. A File object tells Quorum where a file lives on the hard disk. There are many actions the File class can perform, such as creating files, creating directories, getting information about when a file was last modified, etc. All classes in Quorum responsible for input and output to files utilize the File class.
</p>
<p>
    The FileReader class allows you to read the contents from a file on the hard disk. This can be a file that you wrote yourself using Quorum, or a file the user provides via input statements.
</p>
<p>
    The FileWriter class permits writing to files. FileWriter can either create a new, blank file, or add to the contents of an existing file. The FileWriter class has two important modes:
</p>
<ul>
    <li>
        <b>
            Overwrite
        </b>
    </li>
</ul>
<p>
    In this mode, when the FileWriter class is used on a file, if the file you wish to write already exists, its contents will be erased.
</p>
<ul>
    <li>
        <b>
            Append
        </b>
    </li>
</ul>
<p>
    In append mode, FileWriter will simply add whatever content you wish to write to the end of the file, after any existing contents.
</p>
<h2>Writing to Files</h2>
<p>
    You will create a Quorum program that writes four lines to a file. In the next section, you will read the contents of this file back using Quorum. Create a new blank Quorum project named Lab8_1Practice. For this program, you will need to include the following libraries:
</p>
<pre class="code">
    Libraries.System.File
    Libraries.System.Blueprints.FileWriterBlueprint
</pre>
<p>
    In Main, declare a variable of type text named path, and set its value to "thisIsMyFile.txt". Next, declare a variable of type File and name it myFile.In order for Quorum to understand that you wish to write to the file you specified in the path variable, you must set the path that the myFile object refers to. This is done using the SetPath action.
</p>
<p>
    Once you have done the initial setup, tell Quorum you wish to open the file for writing purposes. The File object allows you to do this using the OpenForWrite action. Assign the return value of the OpenForWrite action to a variable named writer, of type FileWriterBlueprint.
</p>
<p>
    Take a moment to explore the actions available in the FileWriterBlueprint class. The two actions you will use are the WriteLine and Close actions. As seen from the code completion window, the WriteLine action takes a single parameter, a text value. Use the WriteLine action four times to write lines of your choosing. After this, call the Close action from the writer object. Closing the file is a very important step when working with files, as it will ensure the contents you wrote using WriteLine are saved.
</p>
<p>
    Run the program in Sodbeans. After the program closes, navigate to the projects window. Navigate through the Lab8_1Practice project, and notice a new file has appeared. This is the file you just created using Quorum code! Open it in Sodbeans; the contents you specified in each WriteLine call appear in the editor. Close this file before moving on to the next section. The code below demonstrates the final product of this section.
</p>
<pre class="code">
    use Libraries.System.File
    use Libraries.System.Blueprints.FileWriterBlueprint

    // Here, you say the name of the file you wish to create.
    text path = "thisIsMyFile.txt"

    // Create a File object, set its path and open it for writing.
    File myFile
    myFile:SetPath(path)
    FileWriterBlueprint writer = myFile:OpenForWrite()

    // Write four lines to your new file.
    writer:WriteLine("hello")
    writer:WriteLine("from")
    writer:WriteLine("the")
    writer:WriteLine("Quorum Programming Language.")

    // Close the file to save the contents.
    writer:Close()
</pre>
<h2>Reading the Contents of Files</h2>
<p>
    After writing contents to a file, the next critical step is to read back the information you stored. Reading information from files is common practice in much of computer programming. For example, when one logs into Facebook or Twitter, the computer is reading your login information from a file on disk to ensure that your password is correct, load your friends list, etc.
</p>
<p>
    While still working in the Lab8_1Practice project, comment out or delete all code following the line calling the SetPath action. As you now wish to use the FileReader class, add a use statement at the top of the file for the library Libraries.System.Blueprints.FileReaderBlueprint.
</p>
<p>
    Opening a file for reading happens in a much similar way to opening a file for writing. Call the OpenForRead action on the myFile object in your code. Assign the return value to a variable named reader, of type FileReaderBlueprint.
</p>
<p>
    Take a bit of time to investigate the actions available in the reader object, using code completion. The primary actions you should be concerned with are ReadLine, Close and IsAtEndOfFile. While the first two are similar to the actions discussed for writing files, the third is new. When you read a file, you must be able to tell the computer when to stop reading from the hard disk. Otherwise, your program won't know precisely when your file contents end, and you may not get back the information you put in! This is done using a repeat until loop, with the IsAtEndOfFile action. Copy the code below into the Lab8_1Practice project.
</p>
<pre class="code">
    repeat until reader:IsAtEndOfFile()
    say reader:ReadLine()
    end
</pre>
<p>
    This code tells Quorum to read each individual line from the file you wrote previously, and then speak them out loud. Quorum will do this until there are no lines left in your file. Remember to call the Close action on the reader variable, as you did in the previous section when writing files. Run the program. Notice that the lines you wrote to the file earlier are now being read back aloud. You have successfully stored information, and retrieved it back!
</p>
<h2>Application: Address Book</h2>
<p>
    The file manipulation techniques that have been covered so far are quite powerful, and may be used to create many types of applications that require long-term storage of information. In this section of the lab, you will tie together the concepts you have learned and use them to write an address book application. This program will allow users to either add a contact or list all contacts. Each contact will have a name and email address.
</p>
<p>
    Using the input keyword, control structures, and the File I/O techniques learned in this lab, build the address book application outlined above. Create a new project named Lab8_1. You are encouraged to use code from the previous parts of this lab in your final solution. Make use of the File, FileReaderBlueprint, and FileWriterBlueprint classes. Store the address book contents in a file named "addressBook.txt". Unlike in section two, use the OpenForWriteAppend action rather than the OpenForWrite action, so the contents of the address book aren't lost each time a contact is added.
</p>
<h2>Sample Output</h2>
<p>
    A few example runs of the address book application are shown below.
</p>
<p>
    Adding a contact:
</p>
<pre class="code">
    To add a contact, type A. Otherwise, type L to list all contacts.
    A
    What is the contact's name?
    Jeff
    What is the contact's email address?
    jeff@mail.com
    Done. Contact added. Goodbye.
</pre>
<p>
    Listing all contacts
</p>
<pre class="code">
    To add a contact, type A. Otherwise, type L to list all contacts.
    L
    You have the following contacts:
    Jeff jeff@mail.com
    Done. Contacts listed. Goodbye.
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>