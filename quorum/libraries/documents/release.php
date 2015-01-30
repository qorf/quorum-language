<?php include("../static/templates/pageheader.template.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Quorum Release Notes</h1>
		<p>We post changes to Quorum here.</p>
	</div>
</div>
<div class="content index-content">
<h2>Quorum 3.0 TBD 2015</h2>    
<p>Quorum 3.0 represents the most significant change to the Quorum programming
language since its inception and is a near-complete rewrite. When the Quorum project
first began, it was written in Java, was interpreted, and was created as a toy 
language as part of a National Science Foundation project for blind individuals. 
While we have stayed true to helping this with disabilities learn to program, we 
are pleased to announce that Quorum 3.0, now written in a special version of 
Quorum 2.2 (which was never released), is now a fully general purpose programming language.</p>

<p>Given our new goal to make Quorum as general purpose as possible, we have rewritten
the language from scratch with a number of technical goals in mind. We highlight 
some of the most significant alterations below:</p>
<ul> <p>Major language changes</p>
    <li>Quorum now uses an ANTLR 4 backend. Compiler phases in the language
        are now considerably faster.</li>
    <li>Quorum's type system has been significantly revised, removing
        some inconsistencies and significantly simplifying tiling. This approach is
        also somewhat more efficient than in previous releases and works for any
        future cross-compiling we do.
    </li>
    <li>Quorum's exception (called Errors in Quorum) system has been not just rewritten, but also redesigned
        from the ground up. In the new approach, users will always see
        Quorum errors, never the Java equivalent, providing a unified approach. 
        More than this, Quorum is now much better at dealing with internal system
        errors, and in gathering better information about what happened at runtime.
        We encourage users to try it out and give feedback on the new approach.
    </li>
    <li>Quorum can now generate Java bytecode directly using the excellent ASM
        bytecode library used as a backend by most, if not all, Java Virtual Machine Languages.</li>
    <li>Quorum's backend now supports a number of potential formats, not just
        the Java Virtual Machine. In post 3.0, we plan to create a first additional 
        backend with a Quorum to Javascript converter. A prototype using this
        technology can be found at <a href="http://quorumlanguage.com/documents/hourofcode/part1.php">
            quorumlanguage.com in our hour of code tutorial</a>.</li>
    <li>Quorum now supports action chaining (e.g., a:b():c()).</li>
    <li>A significant optimization pass has occurred for programs generated in Quorum, 
        making generated programs have faster execution and bootup times under some
        circumstances.</li>
    <li>Quorum is now fully written, and compiles in, itself. In the first release,
        Quorum 3.0 beta was compiled in Quorum 3.0 beta.</li>
    <li>Quorum's compiler error messages have been redesigned using evidence gathered
        <a href="http://dl.acm.org/citation.cfm?id=2016911.2016934&coll=DL&dl=GUIDE&CFID=622754201&CFTOKEN=67063107">Lee and Ko</a>
        as a loose guide. More work is needed here, as the language and messages are different than Lee/Ko's.
        Ours need to be vetted using evidence on each, which will occur over time.
    </li>
</ul>
<ul> <p>Type system/variable changes</p>
    <li>Removed a number of cases where Quorum was being slightly "too flexible" 
    in its conversions between various types.</li>
    <li>Fixed a host of issues where Quorum was making strange decisions in what it
    was allowing users to do (e.g., action Test returns text would allow return false).</li>
    <li>Fixed issues with not being able to pass undefined values to text fields.</li>
    <li>Fixed a couple bugs with how fields and variables were implemented in bytecode.</li>
    <li>Carefully fixed the amount of type inference allowed on the inside of actions. 
    The value is somewhat conservative and needs to be evaluated in empirical studies.
    Currently, there are no known randomized controlled trials on how much inference
    to allow.</li>
    <li>Made auto-boxing as transparent as possible, with the hopes that we can
    eventually disallow use of capital letter types (e.g., Text vs. text). Our goals to-date
    have been to keep this system as efficient as possible, while hiding/reducing object 
    conversions to only when they are absolutely necessary.</li>
    <li>In almost all cases now, actions can be called, and chained, on primitive
    values without any auto-boxing. In other words, calling actions on primitives
    is quite efficient in Quorum, as values have low memory footprints, but yet still
    "look like" calls to objects in other languages, even though they technically are not.</li>
</ul>
<ul> <p>Exceptions (Error) system changes</p>
    <li>This system has been fully redesigned.</li>
    <li>In the new system Quorum Error classes, those that act like exceptions in 
    other languages, must inherit from Error, and only Error. The reason is due to 
    a quirk in Quorum's inheritance system, but allows the runtime system to have
    considerable flexibility in how it handles errors.</li>
    <li>Errors generated by the underlying backend are now caught and automatically
        converted to Quorum style. This allows us flexibility in the system, so that
        we can study aspects of it in future empirical studies. Previously, we could
        not modify these internal aspects, making studies less useful.</li>
    <li>Detect blocks can still contain multiple types (e.g., detect e is CastError), 
    but the compiler now guarantees these are unique. This was previously a bug.</li>
    <li>Detect blocks may now be rewritten to a new location by the bytecode generator. 
    This will not impact the semantics of a program, but we have it in the notes
    in case it is confusing to someone that looks at the generated bytecode.</li>
    <li>Leaving off a type for a detect block makes it automatically of type Error,
    which is now a catch-all for all possible kinds of system or Quorum errors. This
    error is guaranteed to be written last in the bytecode generation. In other words, 
    any block of type Error is written last.</li>
    <li>Users that find a Java/other error that is not caught and converted properly
    by Quorum, instead showing as a generic error with no obvious semantics, should
    get in touch with the team and let us know, as these issues are now truly trivial
    to manage and adjust as new compiler releases occur.</li>
    <li>Fixed a bug that caused Quorum to run forever and need to be killed if an 
        exception occurred in a generated program. </li>
    <li>Adjusted the API for the Error classes making them more consistent, generally, 
    and also making them conform to more modern naming schemes used in Quorum.</li>
    <li>Considerably improved the stack trace information presented from errors.</li>
</ul>
<ul> <p>Control Structure changes</p>
    <li>If statements now properly short circuit. This was previously a bug.</li>
    <li>Included a number of optimizations at various points in the control
    structure pipeline.</li>
    <li>Simplified the bytecode generation for various kinds of expressions.</li>
    <li>Fixed a bug in if statements that very rarely caused a bytecode verification error</li>
</ul>
<ul> <p>Compiler Support</p>
    <li>Added preliminary support for the ASM bytecode library to Quorum. This initial
    version is only enough functionality to implement Quorum, but will likely be expanded 
    over time.
    </li>
    <li>Added parsing/semantic analysis libraries for Quorum. Others can inherit 
    from these and garner full ANTLR 4 support for reading Quorum source. </li>
    <li>All symbol tables, analyzers, and generators are public and easily modified for those
    wanting to write support for Quorum into IDEs or tools, either online or offline.</li>
    <li>Bytecode generation/semantic analysis is now done in two-passes, which considerably
    simplifies finding/fixing bugs and the generation process.</li>
</ul>
<ul> <p>Other changes</p>
    <li>Fixed a host of minor bugs throughout the language.</li>
    <li>Fixed a bug causing compiled Quorum programs to load very slowly.</li>
    <li>Input commands now work from the command line, but do not pop-up a window. 
    The window popping up behavior will be retained only in an IDE.</li>
    <li>Added a separate command called input, with no parameters, which allows
        the user to request input without outputting information first.
    </li>
</ul>
<h2>Quorum 2.1 December 16th, 2013</h2>
<p>Quorum 2.1 </p>
<ul> <p>Web changes</p>
    <li>Added a peer review system for Quorum's standard library.</li>
    <li>Added a submission system for the standard library.</li>
    <li>Added a badging system for recognizing users that contribute to Quorum.</li>
    <li>Added an information page on the submission system and process.</li>
    <li>Fixed a broken connection causing the accessibility libraries to not
    properly show up in the library list.</li>
</ul>
<p></p>
<ul> <p>Standard Library and compiler Fixes/Changes</p>
        <li>Fixed a bug causing certain primitive types to not hash correctly.</li>
        <li>Fixed several minor bugs in the standard library.</li>
        <li>Added new data libraries for reading XML files and CSV files.</li>
        <li>Added a number of new additional test cases for various components.</li>
        <li>Fixed a rare verifier error in the compiler.</li>
        <li>Fixed several other small compiler issues.</li>
</ul>
<h2>Quorum 2.0.1 August 13th, 2013</h2>
<p>This is a bug fix release for the Quorum 2.0 branch.</p>
<ul> 
        <li>Finished and integrated the Mac OS X version of the accessibility 
        libraries. While the accessibility model on Mac is very different than
        on PC, the Quorum libraries should work as similarly as we could make
        them on each platform.</li>
        <li>Fixed a bug causing the output buffer to not be cleared correctly on
        shutdown of a program.</li>
        <li>Fixed a number of bugs in the accessibility libraries.</li>
        <li>This is a Sodbeans release only. We are in the process of fixing one
        more important bug on the 2.0 branch before we put out a new Quorum
        installer. As such, those wanting this particular version of Quorum
        will need to get the updates in Sodbeans.</li>
</ul>
<h2>Quorum 2.0 June 6th, 2013</h2>
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
        <li>Changed the phrase "of type" in the Error architecture to "is."</li>
        <li>Changed the phrase "is a" in the inheritance system to "is."</li>
        <li>Finished a very basic initial version of accessibility APIs for Quorum. 
            This version is largely a proof of concept, but provides some infrastructure
            calls down to various operating systems and unifies accessibility calls
            (NSAccessible, IAccessible). This first version is available only
            for the Windows operating system and only works when using compiled mode. It 
            does not yet function in interpreted mode or on Mac or Linux.</li>
        <li>Fixed a bug in the compiler causing extra items to sometimes be 
        wrapped into an output .jar file.</li>
        <li>Added the -server command line flag. This flag turns Quorum into
        an HTTP server, where code can be sent to it and the output is returned.
        Additionally, the -server insecure can be called turn off security settings
        while in this mode. This last mode is not recommended unless the user is
        running the server locally.</li>
        <li>Added a new flag, -java. This flag allows the user to pass an existing
        Java jar file to Quorum. When passed, Quorum will automatically wrap 
        any code into the built jar's manifest on compile. This should make it
        easier for external groups to combine their existing Java code with
        Quorum projects.</li>
        <li>Finished a significant optimization pass of the compiler. There is always
        more work that can be done in this area, but a number of the bottlenecks
        in the Quorum 1.7 branch and earlier have been hammered out.</li>
        <li>Significantly improved the -verify flag, which now gives a much
        better idea as to which methods have non-compiling, missing, or otherwise
        broken examples in the documentation.</li>
        <li>Removed the "quote" token from the language. Now, the text primitive
        or object can return special character codes by calling actions, including
        arbitrary unicode values. Helper methods are available for common special
        characters (e.g., newline, quotes, tabs).</li>
        <li>Lots of bug fixes.</li>
    </ul>
    <li><strong>New Quorum Website</strong></li>
    <ul>
        
        We have substantially revamped the Quorum website, including adding the
        ability to run some Quorum programs online and the ability to rate 
        the words/symbols used in the standard library, in an effort to improve
        them further.
        
        <li>The website now has a much improved look and feel.</li>
        <li>The page for finding files in the standard library is now grouped 
            by category, hopefully making it easier to find classes.</li>
        <li>The Quorum website is now easily searchable.</li>
        <li>Users can now login to the website through google or a custom
        account.</li>
        <li>Pages with documentation generated by the compiler now allow the user
        to rate various attributes of a class (e.g., the class name, variable names, 
        action names). The user must be logged into to rate.</li>
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