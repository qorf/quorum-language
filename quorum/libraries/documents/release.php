<?php include("../static/templates/pageheader.template.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Quorum Release Notes</h1>
		<p>We post changes to Quorum here.</p>
	</div>
</div>
<div class="content index-content">
<h2>Quorum 2.0 June 9th, 2013</h2>
<p>Quorum 2.0 includes a host of improvements to the type system, adds the 
    ability to call actions on primitives, includes minor syntactic improvements, 
    and enhances the code completion helper libraries.</p>
<ul>
    <li><strong>Type System Changes:</strong></li>
    <ul>
        
        A number of changes have been made to the type system, finishing off
        a set of long planned alterations. We hope now that Quorum conforms to 
        the best available empirical evidence. 
        
        <li>Actions that return a primitive text value can now return undefined.</li>
        <li>Text primitives can now be assigned as undefined.</li>
        <li>Added the ability to call actions on primitive values. For example, we can
        now add a colon after an integer and call any action provided by the Object
        version of the primitive. Generally, this makes it far less necessary to 
        instantiate an object for a primitive type. The solution for this is relatively
        efficient, as behind the scenes, Quorum calls a static action, with only very 
        slightly more overhead than calling the action on the object.</li>
        <li>Added all of the new type system features into the code completion
        APIs, meaning that integrated development environments using Quorum,
        like Sodbeans, should get them for automatically and without alteration.</li>
    </ul>
    <li><strong>Language Additions/Changes:</strong></li>
    <ul>
        <li>The "print" keyword has been changed to "output." This change
        conforms to data from our surveys with novices, in addition to conforming
        to common sense. For example, getting input from Quorum required using
        the word input, but getting output previously required using the word print. 
        This made little sense, as print could have meant printing to a printer, not
        necessarily printing to a console. This way, input implies input and output
        implies output, making the language more consistent. All
        standard library materials, book materials, and curricular materials have
        been altered for the change.</li>
        <li>Finished a very basic initial version of accessibility APIs for Quorum. 
            This version is largely a proof of concept, but provides some infrastructure
            calls down to various operating systems and unifies accessibility calls
            (NSAccessible, IAccessible).</li>
        <li>Fixed a bug in the compiler causing extra items to sometimes be 
        wrapped into an output .jar file.</li>
        <li>Added the -server command line flag. This flag turns Quorum into
        an HTTP server, where code can be sent to it and the output is returned.
        Additionally, the -server insecure can be called turn off security settings
        while in this mode. This last mode is not recommended unless the user is
        running the server locally.</li>
        <li>Finished a significant optimization pass of the compiler. There is always
        more work that can be done in this area, but a number of the bottlenecks
        in the Quorum 1.7 branch and earlier have been hammered out.</li>
        
    </ul>
</ul>
<h2>Quorum 1.7 February 25th, 2013</h2>
<p>Quorum 1.7 adds in a new type inference system, upgrades the internal architecture substantially,
and fixes a number of known bugs.</p>

<ul>
    
    <li>Quorum now has a type inference system that can be used on the inside of actions (but not in declarations). 
        In effect, this means that users can write phrases like "a = 5" and the compiler will 
        still statically check, but will allow the type declaration to be excluded under many circumstances. 
        This new system partially completes
        changes to the Quorum 2.0 type system, which conforms to user studies with humans done by
        our team and Stefan Hanenberg.
        </li>
    <li>Substantially altered the static analysis tools internally to Quorum. This
    greatly increases the quality of the types of tools that can be built on top
    of the compiler. For example, Sodbeans now has much better code completion support
    due to this upgrade.</li>
    <li>Added new web libraries for Quorum. This initial version is in beta and will
    be completed in Quorum 2.0. The current version, located in Libraries.Web supports
    the HTML 5 specification (or by calling print "my page here"). There are a number of 
    limitations in this first release, but a basic web server can be created by
    passing the -web tag to the compiler (or in Sodbeans).</li>
    <li>Added a hash table to the standard library under Libraries.Containers.HashTable.</li>
    <li>Added a class for handling bitwise operations to the standard library under
        Libraries.Compute.BitwiseOperations.</li>
    <li>Made more changes to the compiler errors output by Quorum as part of our 
        continuing mission to make Quorum easier to use.</li>
    <li>Updated and expanded the curriculum and documentation for Quorum.</li>
    <li>Added a way to pass and use command line arguments.</li>
    <li>Fixed a bug with the "me" keyword.</li>
    <li>Fixed a rare, but critical, bug in the inheritance system.</li>
    <li>Fixed an odd bug in the scoping system.</li>
    <li>Fixed a number of small edge-case bugs.</li>
</ul>

<h2>Quorum 1.6 September 9th, 2012</h2>
<p>Quorum 1.6 contains a number of important bug fixes.</p>
<ul>
    
    <li>Fixed a number of bugs with the error system (called exceptions in other languages). 
    These changes include a near rewrite of the bytecode generation algorithms for this system.</li>
    <li>In order to help identify problems in the future, we have added a considerable number of test cases
        for the errors system. We think these tests offer a much more 
        complete automated suite for this part of the compiler.</li>
    <li>Fixed some bugs in the control flow analysis algorithms. These fixes will 
        not impact the runtime behavior of any Quorum programs, but will
        make alternative control flow sequences that are illegal or impossible
        a little bit more obvious.</li>
    <li>Improved the clarity slightly of a few compiler errors. As always, making
    compiler errors easier to understand is a work in progress.</li>
    <li>Fixed a number of small edge-case bugs.</li>
</ul>
<h2>Quorum 1.5 June 26th, 2012</h2>
<ul>
    <li>Significantly altered the syntax for conditional statements (if). The reason for this change is because we ran an empirical study, which showed evidence that novices using this construct made consistent errors, especially in the use of the token &quot;then&quot; and &quot;end&quot; on the inside of else if constructs. There is still one more set of tests we would like to run on this construct, to reveal whether the keywords &quot;else if&quot; lead to higher accuracy than our current choice, &quot;elseif.&quot; We have not yet discovered clear empirical evidence in the academic literature, so will probably end up running the tests on our own. Below we give a quick example of the change.</li>
</ul>
<p>
    We previously had the following: 
</p>
<p><pre class="code"><code>
if a = b then
end else if c = d then
end else then
end
</code></pre></p>
<p>
    The syntax is now the following:
</p>
<p><pre class="code"><code>
if a = b
elseif c = d
else
end
</code></pre></p>
<p>
    We may change the design of this construct again in the future if we find more, or better, evidence for against any particular design. Users, or other researchers, that have conducted formal empirical studies, with corresponding evidence, that they think might illuminate more decisions here should let us know.
</p>
<ul>
    <li>Made minor changes to check-detect syntax. Like if statements, we removed the inner &quot;end&quot; statements. We did not run a formal empirical study on this change, but we thought it was a likely conclusion, given the if statement results. Further, this change keeps our language consistent across language constructs.</li>
    <li>We now have a new Quorum Interpreter</li>
    <ul>
        <ul>
            <li>In internal tests, this includes a speed increase from between 1,100% and 21,000% speed improvement for simple examples. This timing data includes just the speed the interpreter runs, not compilation speed.</li>
            <li>This interpreter is now used by default if the -interpret flag is used. For example, &quot;quorum -interpret Main.quorum&quot; would use the new version.</li>
        </ul>
    </ul>
    <li>Added quite a few new compiler tests. These tests have helped us to identify and fix a host of bugs (mostly minor) in the compiler, including a host of edge cases.</li>
    <li>Quorum now has a significantly improved documentation generation system</li>
    <ul>
        <ul>
            <li>Changed the -document flag so that it now auto-generates HTML5 documentation for Quorum libraries.</li>
            <li>Added a new flag called -verify, which automatically checks that any &quot;examples&quot; used in the documentation compile.</li>
            <li>Updated the online wiki Quorum documentation to the 1.5 syntax.</li>
            <li>Significantly improved the quality of code examples in Quorum documentation.</li>
        </ul>
    </ul>
    <li>Fixed a major compiler bug that didn't allow the user to access parent variables.</li>
    <li>Library Changes</li>
    <ul>
        <ul>
            <li>Completed a total redesign of the File class. We're looking for feedback on this class, but hope that it will be easier to use.</li>
            <li>Minor bug fixes in the music and sound classes.</li>
            <li>Fixed a number of bugs in classes like Random and the Containers.</li>
            <li>Added a new class called Libraries.Sound.Audio, which can playback media files. In this first release, it is designed to make it easy to playback .wav files easily. Future releases will probably expand the functionality further, depending on whether we get any user requests.</li>
            <li>Added a new set of Robot Control Libraries for interacting with iCreate's CBC robot controller.</li>
        </ul>
    </ul>
    <li>Adjusted the design of Compiler error messages. Our changes here are pretty minor, but our goal was to generally make the messages more consistent and obvious to users. users that find error messages that still are not clear should please feel free to file a bug report.</li>
    <li>Lots of bug fixes.</li>
</ul>
<h2>Quorum 1.0 January 30th, 2012</h2>
<p>
    This is the initial release of the Quorum programming language. Currently, Quorum has the following features:
</p>
<ul>
    <li>Fully compiled down to Java bytecode</li>
    <li>Static, but flexible, type checking</li>
    <li>Unique control structures, heavily tested to be easy to understand by novices and professionals</li>
    <li>Object-oriented language</li>
    <li>Standard Library (e.g., data structures, music generation, text-to-speech)</li>
    <li>Plugin system for extending Quorum</li>
    <li>Integration with the NetBeans IDE</li>
    <li>Talking Omniscient debugger (The debugger literally talks to you and tells you what is going on).</li>
</ul>
</div>
<?php include("../static/templates/pagefooter.template.php"); ?>