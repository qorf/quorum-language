<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 8.4: Pirate Translator | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 8.4</h1>
		<p>Pirate Translator</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<!--<h1>Challenge Assignment 8.4: Pirate Translator</h1>-->
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
        How to handle multiple sets of parsed text
    </li>
</ul>
<h2>Overview</h2>
<p>
    A common excercise in software development is to change the contents of a file.  This may mean opening a file and modifying certain pieces, then closing it again, or it could mean writing a whole new file based on the information from another file.  In this assignment, you will be simulating this excercise.  You will be creating a program that reads text from a file chose by the user, then translating that file into Pirate-speak, and finally writing the translated text to a new file chosen by the user.
</p>
<p>
    Create a new project and name it <b>Assignment8_4</b>. You will need to create one more class, named <code>Translate</code>.
</p>
<h2>Class <code>Translate</code></h2>
<p>
    There are many different ways you could set up this translator.  Below is one option, but feel free to explore different design options and test your own implementation ideas.  To start, make your translator capable of handling only lower-case text with no grammer.  When you have this working, as an optional step, try to make it so your translator can handle capitalized words with commas, periods, exclamation marks, question marks, and semicolons.
</p>
<p>
    Class <code>Translate</code> will contain the following actions:
</p>
<ul>
    <li>
        <b>
            action ReadEnglishFileAndParse returns Array &lt text &gt;
        </b>
    </li>
</ul>
<p>
    This action should ask the user for the name of a file they want to translate.  Using error checking, they should continue to be prompted to enter a valid file name until they do.  One a valid file name is received, this action should open the file, read its contents, and parse the contents into usable tokens.  Here, "tokens" could mean individual words or individual letters.  As the programmer, its up to you to make the design decisions.  Once the contents of the file have been parsed, return the array holding the parsed text.
</p>
<ul>
    <li>
        <b>
            action ReadPirateFileAndParse returns Array &lt text &gt;
        </b>
    </li>
</ul>
<p>
    This action will read in the provided translation key (from english to pirate), parse the text, then return the array with parsed text.  Again, it's up to you to figure out how you want to parse the file.
</p>
</p>
<ul>
    <li>
        <b>
            action TranslateAndWrite(Array &lt text &gt; toTranslate, Array &lt text &gt; parsedPirate) returns boolean
        </b>
    </li>
</ul>
<p>
    Using the two arrays returned from the previous two actions <code>TranslateAndWrite()</code> should translate each word from the input file that has a pirate equivalent and translate that word into pirate.  One way to do this is to compare each word from the input file with each english word from the translation key.  If the two words are the same, then grab the equivalent pirate word.  Once all the words that have an quivalent pirate meaning are translated, prompt the user for the name of a file to write to.  Again, this action should continue prompting the user until a valid file name is entered.  Write the translated text to the user-specified file.  If the file gets written to successfully, this action should return true, otherwise it should return false.
</p>
<p>
    Something that should be considered is: "what if a particular word has no pirate equivalent?"  When writing to the new file, this action should also write the words that don't have any translation.  For example:
</p>
<pre class="code">
    [input file]
    hello there
    
    [output file]
    ahoy there
</pre>
<p>
    In the above example, the word "there" had to pirate translation, but it was still written to the output file in the correct position, so that when read, the text still makes sense.
</p>
<h2>Class <code>Main</code></h2>
<p>
    <code>Main</code> should instantiate an object of class <code>Translate</code>, then call the proper functions to translate a file. If the file was translated successfully, then output (or say) a message telling the user so.
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following if you didn't implement any grammer or capitalization handling:
</p>
<pre class="code">
    [input file]
    hello there friend want to go to the mall with me
    
    [output file]
    ahoy there mate want ter go ter th' market with me
</pre>
<p>
    If you did implement grammer and capitalization handling, you should get output similar to the following:
</p>
<pre class="code">
    [input file]
    Hello there, friend! Can you point me to where I can find a bank?  I have to get some money so I can go eat at a restaurant and then shop at the mall.

    [output file]
    Ahoy there, mate! Can you point me ter whar I can find a buried treasure?  I be havin' to get some doubloons so I can go eat at a galley and then shop at th' market.
</pre>