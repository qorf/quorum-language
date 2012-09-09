<?php include("documents/include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Download the Quorum Programming Language';
</script>
<h1>The Quorum Download page</h1>
<p>To install Quorum as a standalone programming language, that can be
    used from the console (on Mac OS X or Windows), download the installer
    for your platform and run it. If you would prefer to use Quorum as part of
    an IDE directly, see the <a href="http://sodbeans.sourceforge.net/"> Sodbeans project</a></p>

<h2>Quorum 1.6 for Windows</h2>
<a href="http://sourceforge.net/projects/quorum/files/Quorum%201.6/Quorum%201.6.exe/download">
    <img src="documents/images/download.png"
         alt="Click this image to download the Quorum 1.6 Installer for Windows."/></a>

<h2>Quorum 1.6 for Mac OS X</h2>
<a href="https://sourceforge.net/projects/quorum/files/Quorum%201.6/Quorum%201.6.pkg/download">
    <img src="documents/images/download.png"
         alt="Click this image to download the Quorum 1.6 Installer for Apple OS X."/></a>

<h1>Getting started, after you install Quorum</h1>
<p>
Once you have installed Quorum on your machine, you can get started programming.

    
    
In this short tutorial, we will discuss getting started with the Quorum 
programming language. First, if you have arrived at this page after installing 
Quorum, this page will describe what you can do next to get started.
</p>
<p>
When we invoke Quorum through a command prompt (such as cmd.exe on windows, or 
Terminal on Mac), we can optionally specify various parameters to make Quorum 
compile programs. While a more complete guide on the command line arguments for
Quorum can be found on the <a href="documents/console.php">command line argument page</a>,
a brief description is as follows:
</p>
<p><pre class="code"><code>
quorum [flags] [files]
</code></pre></p>
<p>
Note that if we choose to specify command line flags, they must be specified 
<i>before</i> the name of the file(s) we wish to send to Quorum.
</p>
<h2>Hello World</h2>
<p>
Say we wanted to run a program named helloWorld.quorum, containing the following code:
</p>
<p><pre class="code"><code>
class Hello
    action Main
        print &quot;Hello world&quot;
    end
end
</code></pre></p>
<p>
To do this, we would first create a file named helloWorld.quorum and type in the above code. Then from the command line we can invoke Quorum in the following way:
</p>
<p><pre class="code"><code>
quorum -interpret helloWorld.quorum
</code></pre></p>
<p>
This will build and run your program, printing out &quot;Hello World&quot; to the terminal. If we would like to build a jar and execute our program we can use the -compile flag. This flag will make Quorum compile our code into Java bytecode that can then be executed. This flag is the default setting; when set, your program will not automatically run. If we wanted to compile a simple &quot;hello world&quot; program and then run it, one might type:
</p>
<p><pre class="code"><code>
quorum -compile helloWorld.quorum
</code></pre></p>
<p>
followed by
</p>
<p><pre class="code"><code>
java -jar ./Run/Default.jar
</code></pre></p>
<p>
which will invoke the newly compiled program. One alternative to this approach would to navigate in the command prompt to the distribute folder first, then execute the program from there. We can do this with the following commands:
</p>
<p><pre class="code"><code>
cd Run
java -jar ./Default.jar
</code></pre></p>
<p>
Suppose we wanted to rename the jar to something, we could use the -name flag. This flag tells Quorum what name to give to a newly compiled program. If, for example, we wanted to compile a simple &quot;hello world&quot; program and name the resulting program &quot;Hello,&quot; we would type
</p>
<p><pre class="code"><code>
quorum -name Hello helloWorld.quorum
</code></pre></p>
<p>
followed by
</p>
<p><pre class="code"><code>
java -jar ./Run/Hello.jar
</code></pre></p>
<p>
Finally, a list of all current Quorum commands can be found from the command line using the command:
</p>
<p><pre class="code"><code>
quorum -help
</code></pre></p>

<?php include("documents/include/footer.php"); ?>