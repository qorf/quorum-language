<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 8_3';
</script>

<h1>Assignment 8.3: Medical Records</h1>
<h2>Objectives</h2>
<p>The goal of this assignment is to learn the following:</p>
<ul>
    <li>
        How to parse text
    </li>
    <li>
        How to read/write to a file
    </li>
    <li>
        Data privacy and security
    </li>
</ul>
<h2>Overview</h2>
<p>
    Because many things are automated and digitized in today's world, its the constant job of the developer to consider the security and privacy of people's information. In this assignment you will write a program to create, encrypt, and decrypt medical records. Using the substitution cipher you created in <a href="lab8_3.php">lab 8_3</a> (slightly modified), users will be given the choice to create a medical record, encrypt a medical record, or decrypt a medical record.
</p>
<p>
    Create a new project and name it <b>Assignment8_3</b>. You will also need to create two more classes called <tt>Record</tt> and <tt>Cipher</tt>.
</p>
<h2>Class <tt>Cipher</tt></h2>
<p>
    You can use the class <tt>Cipher</tt> that you created in <a href="lab8_3.php">lab 8_3</a> for this assignment. Take a moment to walk through the class and familiarize yourself with what you implemented. Now think about the kinds of restrictions this cipher was limited to. In this assignment, you may need to encrypt and decrypt upper-case letters, numbers, and different special characters.
</p>
<p>
    Edit this class so that it can encrypt and decrypt all letters (upper-case and lower-case), all numbers, spaces, and the following special characters:
</p>
<pre class="code">
    /.,-@_
</pre>
<h2>Class <tt>Record</tt></h2>
<p>
    Class <tt>Record</tt> will contain the following actions:
</p>
<ul>
    <li>
        <b>
            action CreateRecord(text first, text last, text DOB, text gender, text SSN, text address, text phone, text email, text insuranceID, text insuranceName, text copay)
        </b>
    </li>
</ul>
<p>
    This action should ask the user for the name of a file, then write the arguments passed into it to the specified file. The file should have the following format:
</p>
<pre class="code">
    First Name: " "
    Last Name: " "
    Date of Birth: " "
    Gender: " "
    SSN: " "
    Address: " "
    Phone Number: " "
    Email: " "
    Insurance Provider: " "
    Insurance ID: " "
    Copay: " "
</pre>
<ul>
    <li>
        <b>
            action ReadRecord() returns Array &lt text&gt;
        </b>
    </li>
</ul>
<p>
    This action will open up a file specified by the user, read its contents, parse the line at the ":", add the text value after the ":" to an array, and then return that array. The goal is to only return the actual values from each line, and not the line "titles." For example:
</p>
<pre class="code">
    First Name:Brandon
</pre>
<p>
    Only the text "Brandon" should be added to the return array, and not "First Name:". To parse text, you can use the <tt>Split()</tt> action from class <tt>Text</tt>. For each line, read the contents of the line, parse it on the ":", and add the text after the ":" to the return array. You can use the <tt>ReadLine()</tt> action from class <tt>FileReader</tt>. <tt>ReadLine()</tt> will allow you to sequentially read each line from the file by making multiple calls to <tt>ReadLine()</tt>.
</p>
<ul>
    <li>
        <b>
            action EncryptRecord(text first, text last, text DOB, text gender, text SSN, text address, text phone, text email, text insuranceID, text insuranceName, text copay) returns Array &lt text &gt;
        </b>
    </li>
</ul>
<p>
    This action will encode the text values passed into the action as arguments (using the <tt>Encode()</tt> action from class <tt>Cipher</tt>), add the encrypted text to an array, and then return the array.
</p>
<ul>
    <li>
        <b>
            action DecryptRecord(text first, text last, text DOB, text gender, text SSN, text address, text phone, text email, text insuranceID, text insuranceName, text copay) returns Array &lt text&gt;
        </b>
    </li>
</ul>
<p>
    This action will do the same as <tt>EncryptRecord()</tt>, but will instead use the <tt>Decode()</tt> action to decrypt the text passed in as arguments.
</p>
<h2>Class <tt>Main</tt></h2>
<p>
    Create a small menu for the user, with a welcoming line to your program, and a list of options. There should be five different options. If the user selects 1, create a medical record using text values obtained by the user. If the user selects 2, encrypt a medical record and then write the encrypted record to a new file. If the user selects 3, decrypt a medical record and write the decrypted record to a new file. If the user selects 4, exit the program. If the user selects 5, repeat all the choices to them again. This should repeat until the user has selected a valid entry, and should tell the user they have entered invalid input if they do.
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following:
</p>
<pre class="code">
    Welcome to the interactive medical record encryption suite
    What would you like to do?
    Enter 1 to create a new medical record
    Enter 2 to encrypt a medical record
    Enter 3 to decrypt a medical record
    Enter 4 to exit this program
    Enter 5 to hear these options again
</pre>
<p>
    If user selects 1, you should get a file similar to this:
</p>
<pre class="code">
    First Name:Brandon
    Last Name:Spencer
    Date of Birth:10/21/1988
    Gender:M
    SSN:123456789
    Address:123 Main St. Pullman, WA 99163
    Phone Number:509-111-2222
    Email:brandon_spencer@wsu.edu
    Insurance Provider:Boeing
    Insurance ID:1122334455
    Copay:10
</pre>
<p>
    If user selects 2, you should get a file similar to this:
</p>
<pre class="code">
    First Name:Hptfqxf
    Last Name:Sjufeup
    Date of Birth:01/90/0233
    Gender:N
    SSN:098765432
    Address:098 Ntbf Sv. Jlwwntf, ZT 22058
    Phone Number:612-000-9999
    Email:hptfqxf_sjufeup@zsl.uql
    Insurance Provider:0099887766
    Insurance ID:Hxubfc
    Copay:01
</pre>
<p>
    If the user selects 3, you should get a file similar to this:
</p>
<pre class="code">
    First Name:Brandon
    Last Name:Spencer
    Date of Birth:10/21/1988
    Gender:M
    SSN:123456789
    Address:123 Main St. Pullman, WA 99163
    Phone Number:509-111-2222
    Email:brandon_spencer@wsu.edu
    Insurance Provider:Boeing
    Insurance ID:1122334455
    Copay:10
</pre>
<p>
    If the user selects 4:
</p>
<pre class="code">
    Goodbye!
</pre>
<p>
    If the user selects 5:
</p>
<pre class="code">
    What would you like to do?
    Enter 1 to create a new medical record
    Enter 2 to encrypt a medical record
    Enter 3 to decrypt a medical record
    Enter 4 to exit this program
    Enter 5 to hear these options again
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>