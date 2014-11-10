<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 8.3: Substitution Cipher | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Lab 8.3</h1>
		<p>Substitution Cipher</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<!--<h1>Lab 8.3: Substitution Cipher</h1>-->
<h2>Objectives</h2>
<p>The goal of this lab is to learn the following:</p>
<ul>
    <li>
        How to use the Text class
    </li>
    <li>
        How to manipulate strings
    </li>
    <li>
        Simple encryption techniques
    </li>
    <li>
        Data validation
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this lab, you will build a program to encrypt and decrypt messages. This will be done using an encryption technique knows as a substitution cipher. The idea behind a substitution cipher is to replace units of plaintext with ciphertext. "Units" may be made up of single letters (known as a simple substitution cipher, the one you will be creating), pairs of letters, mixtures of letters, and so on. "Ciphertext" is the name given to the mixed-up version of the alphabet which will be used for encryption. The ciphertext can be created by shifting the alphabet, reversing it, or scrambling it in some fashion or another. In terms of security, a simple substitution cipher can be a very weak form of encryption, since the encryption is based on how complex the cipher alphabet is. Decoding the encryption is done the exact same way, only inverted; so to decode a message, you would use the ciphertext alphabet the same way you would use the plaintext alphabet to encrypt. Below is a short example of a substitution cipher:
</p>
<pre class="code">
    Plaintext alphabet: abcdefghijklmnopqrstuvwxyz
    Ciphertext alphabet: zyxwvutsrqponmlkjihgfedcba

    plainMessage = "hello world"

    encryptedMessage = "svool dliow"
</pre>
<p>
    As you can see from the above, the letters are directly exchanged for letter that corresponds to it in the Ciphertext alphabet. "h" at index 7 in the Plaintext alphabet corresponds to "s" at index 7 in the Ciphertext alphabet. To do all this, you will be using the <code>Text</code> class in Quorum, which has some powerful string manipulation actions needed for creating this cipher.
</p>
<p>
    Create a new project and name it <b>Lab8_3</b>. Create a second class and name it <code>Cipher</code>.
</p>
<h2>Class <code>Cipher</code></h2>
<p>
    You will need the following libraries for this lab:
</p>
<ul>
    <li>
        use Libraries.Language.Types.Text
    </li>
    <li>
        use Libraries.Language.Errors.Error
    </li>
</ul>
<p>
    Class Cipher will only have two actions:
</p>
<ul>
    <li>
        <b>
            action Encode(text string) returns text
        </b>
    </li>
    <li>
        <b>
            action Decode(text string) returns text
        </b>
    </li>
</ul>
<p>
    Both actions will take a text value (the message to be encoded or decoded) from the user, encode or decode it, and then return the modified message. Start by creating three Text objects. One of them will hold the alphabet, one will hold the ciphertext alphabet, and the last will hold encoded message. You can use the <code>SetValue</code> action to set the values of the objects to the needed text. For example:
</p>
<pre class="code">
    Text myString
    myString:SetValue("Hello Students!")
</pre>
<p>
    The basic algorithm for a substitution cipher is as follows:
</p>
<pre class="code">
    repeat stringLength times
    if string:GetCharacter(j) = alphabet:GetCharacter(i)
    append(codedAlphabet:GetCharacter(i))
    increment j
    reset i
    else
    increment i
</pre>
<p>
    You can use the <code>GetLength()</code> action to get the length of a text object. Notice that a substitution cipher works only on what is provided in the plaintext and ciphertext alphabets. For example, if you want users to be able to enters spaces between words, then the plaintext and ciphertext alphabet must contain a space in them somewhere. The same holds true for special characters, uppercase characters, etc. This is a chance to make your program more robust. By including capital letters, common punctuation, and spaces, the user will be provided with increased functionality and will be less likely to crash your program.
</p>
<h2>Main</h2>
<p>
    In <code>Main</code>, create a menu so that the user can enter 1 to encode a message, 2 to decode a message, or 3 to quit. If the user enters 1, encrypt their message and tell them what it is. If the user enters 2, decrypt their message and tell them what it is. If they enter 3, the program should exit without doing anything further. You will need to do some data validation here to make sure the user has entered valid input. Data validation is making sure a program operates on clean, correct, and useful data. For example, your program should handle if the user enters text when prompted instead of entering integers(1, 2, or 3). Also, if the user enters any other numbers, the program should tell them to enter a valid option, and then prompt them again to enter 1, 2, or 3.
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following:
</p>
<pre class="code">
    Would you like to encode or decode a message? Enter 1 to encode a message, 2 to decode a message, or 3 to quit.
    1
    Enter a message to encode (must be in all lower-case).
    hello
    Your encoded message: svool
</pre>

</div>
<?php include("../../../static/templates/pagefooter.template.php"); ?> 