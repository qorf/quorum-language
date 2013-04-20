<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 8_2';
</script>

<h1>Lab 8.2: Sound Lab</h1>
<h2>Objectives</h2>
<p>The goal of this lab is to learn the following:</p>
<ul>
    <li>
        How to use the Audio class
    </li>
    <li>
        How to use the File class
    </li>
    <li>
        How to build arrays containing sounds files
    </li>
    <li>
        How to manipulate and play sound files
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this lab you will build an array of sound files. Included is a number of sound files that can be used, but you are encouraged to find or make your own sounds to use. To build this array, you will use the File class to get a list of files in a directory. From here, you will check to make sure the files are audio files, then add them to the array if true. The program will ask the user for integer indexes into the array, and will play the audio file at that index in the array. This will continue until the user enters 0 to quit.
</p>
<p>
    Create a new project and name it <b>Lab8_2</b>. This lab will be built only in <tt>Main</tt>.
</p>
<h2>Class <tt>Main</tt></h2>
<p>
    You will need the following libraries for this lab:
</p>
<pre class="code">
    use Libraries.Sound.Audio
    use Libraries.System.File
    use Libraries.Containers.Array
</pre>
<p>
    Begin by creating an array of type <tt>Audio</tt> and name it <tt>soundArray</tt>. You will also need to create objects of classes <tt>Audio</tt> and <tt>File</tt>. Name these <tt>audio</tt> and <tt>file</tt>, respectively. Next you will get a directory listing of the files in your project folder. This can be done like so:
</p>
<pre class="code">
    Array &lt File &gt files = file :GetDirectoryListing()
</pre>
<p>
    The above creates an array of type <tt>File</tt> named <tt>files</tt>. The array will contain all the files in the project folder. However, you only want to add sound files to your <tt>Audio</tt> array. The <tt>File</tt> class has an action called <tt>GetFileExtension</tt> that returns text if a file has text after the last "." in the file name. Quorum can use audio files with a "wav" extension, so perform the following steps:
</p>
<li>
    Check if the file is an audio file
</li>
<li>
    If true, "set" the file (use audio:SetFile(file) )
</li>
<li>
    Add the file to <tt>soundArray</tt>
</li>
<li>
    Repeat for as many files as there are in the directory. Hint: <tt>GetSize()</tt> will return an integer.
</li>
<p>
    Lastly, prompt the user for an index value into <tt>soundArray</tt>. Check to make sure the input is in valid range. If the user enters invalid input, prompt them again to enter something valid. Once valid input is obtained, access the sound file at the given index and play the sound and output the sound title. Continue to do this until the user enters "0", which will exit the program.
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following:
</p>
<pre class="code">
    Enter the number of a sound to play (1 - 26 or 0 to quit)
    1
    *play sound*
    Title1
    Enter the number of a sound to play (1 - 26 or 0 to quit)
    2
    *play sound*
    Title2
    Enter the number of a sound to play (1 - 26 or 0 to quit)
    0
</pre>
<p>
    When finished, debug and show your instructor your program.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>