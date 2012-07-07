<?php include("include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Release Notes for the Quorum Programming Language';
</script>
<?php echo '<h2>' . $WEB_ROOT. '</h2>';?>
<h1>Quorum Release Notes</h1>
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
<?php include("include/footer.php"); ?>
