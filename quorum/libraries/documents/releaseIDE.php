<?php include("../static/templates/pageheader.template.php"); ?>

<div class="hero-unit">
    <div class="hero-unit-container">
        <h1>Sodbeans Release Notes</h1>
        <p>We post changes to Sodbeans, the standard development environment
            for Quorum, here. Sodbeans is blind accessible.</p>
    </div>
</div>
<div class="content index-content">
    <h2>Sodbeans 4.0.2 - July 24th, 2013</h2>
    <p>Sodbeans 4.0.2 is a bug fix release for the Sodbeans 4.0 branch. It includes
    an updated version of Quorum and several important bug fixes for the new debugger, 
    which were identified at the recent Quorum workshop in Vancouver, Washington.</p>
    <ul> 
        <li>Fixed a bug causing the debugger to not show variables
        correctly under some circumstances.</li>
        <li>Fixed a bug causing the debugger to speak incorrectly if the screen reader
        in Sodbeans is off.</li>
        <li>Fixed a bug causing the user to be able to incorrectly execute code
        "before and after" the execution of a program. The code the debugger was executing
        was injected bytecode that the debugger should have ignored, but wasn't.</li>
        <li>Fixed several bugs causing the debugger to force the user to step 
        too many times over a section of bytecode. This rare bug made it look as if you had
        to click the step over button several times on one line of code.</li>
        <li>Added a button to the debugger known as rewind to start. The button does
        what is claims. A new image was added for this button.</li>
        <li>Changed the behavior of the rewind button so that it honors breakpoints,
        making it work like the reverse the forward button. Like a video tape, rewinding
        stops if the beginning of the program is reached.</li>
        <li>Hid the restart button, which no longer appears to be used in newer
        versions of NetBeans.</li>
    </ul>
    
    
    
    <h2>Sodbeans 4.0.1 - June 23rd, 2013</h2>
    <p>Sodbeans 4.0.1 is a bug fix release for the Sodbeans 4.0 branch. It includes
    an updated version of Quorum and a host of important bug fixes.</p>
    <ul> 
        <li>Fixed a bug causing Sodbeans to not load properly on Windows 8.</li>
        <li>Fixed a host of bugs in the new accessibility libraries for Quorum.</li>
        <li>Fixed a bug causing the "create account" button to not work properly.</li>
        <li>Fixed a bug causing the public version of Sodbeans to incorrectly 
        require a login.</li>
        <li>Fixed a bug causing the public version of Sodbeans to update from
        the wrong auto-update center.</li>
    </ul>
    <h2>Sodbeans 4.0 - June 6th, 2013</h2>
    <p>In this release, we focused on creating a commercially scalable version of the omniscient 
        talking debugger in Quorum. This significantly increases the applicability of 
        the approach toward real-world applications, in addition to providing a proof of 
        concept for the JVM languages. In addition, we made a number
        of small changes to improve the usability of Quorum in the development environment.</p>
    <ul> 
        <li> <strong>Talking Omniscient Debugger 2.0</strong>: The talking omniscient
            debugger is now commercial scale and much more advanced. This version works
            paves the way for building accessible debuggers for other JVM languages, such
            as Java.
            <ul><li>Based on the excellent <a href=""http://pleiad.dcc.uchile.cl/tod/">TOD architecture</a> 
                    out of the University of Chile. </li>
                <li>Made significant improvements to the auditory cues for the talking
                    debugger. It should now be, hopefully, even more obvious to a blind
                    or visually impaired user what is going on in the debugger.</li>
                <li>Added a call stack window.</li>
                <li>Implemented the pause button.</li>
                <li>Unlike TOD, the Quorum debugger tracks control flow information. 
                    This makes our debugger slightly slower, but allows the user to work using a somewhat more
                    traditional user interface.</li>
                <li>Added a significant amount of architecture for looking at the history
                    of variables. We have not yet decided what we will do with it, but there
                    are significant options now for future research.</li>
            </ul>
        </li>
        <li><strong>Quorum Language Changes</strong>: We made a number of small
            modifications to support for Quorum in the environment.
            <ul>
                <li>Comment and uncomment menu options are now supported.</li>
                <li>Code completion has been updated to work with Quorum 2.0. In addition, 
                    several minor bugs have been fixed in this system.</li>

            </ul></li>
        <li>Upgraded all module dependencies to work under the 7.3 branch, 
            including the latest Java Development Kit release. This update now
            requires JDK 7 Update 10 or above, even on Mac OS X.</li>
    </ul>


    <h2>Sodbeans 3.5.0 - February 25th, 2013</h2>
    <p>In this release, we focused our attention primarily on fixes and enhancements
        for our partners at schools for the blind. This includes a host of enhancements
        for Quorum-based projects, especially with code completion and version control
        systems. This version also includes Quorum 1.7 and the start of a new debugger architecture.
        This new architecture may ultimately pave the way toward talking debuggers for 
        a variety of Java Virtual Machine (JVM) programming languages.
    </p>
    <ul> 
        <li> <strong>Code Completion</strong>: We have made significant improvements to the 
            code completion algorithms in Quorum. A number of practical enhancements 
            we made to this system are listed below.
            <ul>
                <li>Code completion is now much more flexible in when information can be presented.</li>
                <li>Code completion now does a much better job at predicting the kind
                    of information a user probably wants to have at a particular point in
                    an incomplete parse</li>
                <li>Package statements now correctly show code completion.</li>
                <li>Use and package statements have significantly improved code completion, 
                    including full documentation for any classes that have been compiled.</li>
                <li>When typing code completion at a blank line of code, a set of plausible
                    lines a programmer could type now appears. This includes common operations
                    like using primitive types, controls structures like loops, or using
                    any helper classes that have been loaded through use statements or
                    other means.</li>
                <li>Code completion now does a better job of filtering in incomplete expressions. This 
                    is especially true when a user is half way through typing arguments in actions.</li>
                <li>Fixed several bugs causing incorrect code completion to (rarely) be displayed.</li>
                <li>Fixed a bug that sometimes caused the special token, me:, to sometimes
                    give the wrong code completion.</li>
                <li>Adjusted the visual display of code completion to follow visual standards
                    inside of the NetBeans IDE. This includes standard icons, bolding, 
                    and other conventions.</li>
                <li>Code completion now tightly integrates with the generics system in Quorum.
                    As a result, it should now be much more obvious what code completion is
                    saying when using a generics based class like arrays or other data structures.</li>
            </ul>
        </li>

        <li><strong>Version Control</strong>: Added version control into Quorum projects. This new system allows users
            to view, aurally or visually, the history of a project.
            <ul> 
                <li>While there is still work to do, this new system includes partial 
                    accessibility support in Sodbeans for many of the visual features
                    observed in versioned projects.</li>
                <li>Users programming in Quorum can now use subversion, git, mercurial, cvs,
                    diffing tools, and other popular commercial systems for analyzing their code.</li>
                <li>Quorum source files now include the proper icon and HTML based annotations, 
                    which follow standard conventions in the NetBeans IDE.</li>
                <li>Added a minimal property dialog onto the project popup menu. Ultimately, this
                    dialog will be transformed with future versions of Quorum.</li>
            </ul>
        </li>

        <li><strong>Web Projects</strong>: Added support for Quorum web projects. This new support modifies
            Quorum projects, allowing Sodbeans to mimic a server-side scripting
            system.
            <ul> 
                <li>Users can now right click (or use the appropriate hotkey) and click properties on projects. A new project option
                    is now a "web" quorum project, which allows the student to write web pages.</li>
                <li>If a Quorum project is a web project, a browser now opens whenever the user runs their code.</li>
            </ul>
        </li>

        <li><strong>Debugging</strong>: Added a new Debugger Architecture, which expands greatly on the fantastic work
            done by Pothier and others on the TOD architecture (see http://pleiad.dcc.uchile.cl/tod/). This work is not
            yet fully integrated into Quorum, but substantial work was finished
            on the backend, which will be fully integrated in Sodbeans 4.0. This change
            will greatly speed up several systems.
        </li>

        <li><strong>User Interface Changes</strong>: A number of user interface changes took place in the new version of
            Sodbeans.
            <ul> 
                <li>Sodbeans now has a new user preferences window, which combines the 
                    two old windows and cleans them up.</li>
                <li>The Sodbeans tutorial window is now hidden by default.</li>
                <li>Users of Sodbeans that are doing so through a school are now required
                    to login using a free "Sodbeans account." This new account will allow us to track data
                    about usage of Sodbeans. Members of the public may continue to freely
                    use Sodbeans without logging in and without any substantive changes to the previous release.</li>
                <li>By default, Sodbeans now logs user data for school users (not the public),
                    which we note in our license agreement. Members of the public that
                    are not involved in our studies should not be impacted.</li>
                <li>Fixed a number of bugs related to the upgrade to the NetBeans 7.2 architecture, including
                    several related to the auditory and visual display of compiler errors, especially for blind users.</li>
            </ul></li>

    </ul>
    <h2>Sodbeans 3.0.1 - July 2nd, 2012</h2>
    <p>
        In Sodbeans 3.0.1, we have fixed the Windows installer and some minor problems with Quorum pluggins. If you installed Sodbeans 3.0 on windows and had a previous version of Sodbeans installed, please make sure you uninstall and clear your AppData -&gt; .sodbeans folder before attempting to re-install Sodbeans.
    </p>
    <h2>Sodbeans 3.0 - June 25th, 2012</h2>
    <p>
        In Sodbeans 3.0, we have spent a substantial amount of time on both performance and improving accessibility features. This is our first release that we are marking as mature and stable and we are pleased to announce that Sodbeans can now be used on a host of programming languages accessibly (e.g., Java, PHP, HTML, CSS), including the critical features of code completion and editor hints. Sodbeans 3.0 is now built using Sodbeans 3.0, includes a host of improvements, and we are proud to get this version out there. An abridged list of new features follows:
    </p>
    <ul><li>Accessibility: We have finished development of a substantial number of accessibility features, with a focus toward making more windows and controls read correctly, including custom controls for a variety of programming languages (e.g., Java).
            <ul><li>Code completion now accessible for multiple <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> programming languages.
                </li><li>Editor hints in <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> are now accessible.
                </li><li>Tabbed controls have been significantly improved. This affects a number of components, including the previously buggy Plugin window. 
                </li><li>The Help window is now accessible.
                </li><li>Accessibility of the Plugin window is now significantly improved. 
                </li><li>Modified Table Reading
                    <ul><li>In order to make it more clear when a user is at the top or the bottom of a table, short notifications are now played.
                        </li><li>As per a user request from the mailing list, Tables now announce that they are tables.
                        </li></ul></li><li>Editors
                    <ul><li>In single-line editor, pressing enter now says "enter" instead of "new line."  We thought that this new phrase made more sense under this condition.
                        </li><li>Editor focus events now follow the left-hand rule (e.g., "Main.quorum editor gained focus" rather than "Editor for Main.quorum").
                        </li></ul></li><li>Where Am I Key - Command + Shift + F5: This keystroke has been significantly revised and improved to give the user a better idea of where they are in Sodbeans. Its usage varies by context. For example, using it in an editor gives a line number, while using it in other components does not.
                </li><li>Screen-reading user wishing to disable screen reading can now do so from the Options window. While our focus is on accessibility, this feature was added due to some work done with Landmark college, which has a strong focus on students with learning difficulties. In the future, this will lead to users having significant control over what features are read in Sodbeans and what are not.
                </li><li>Added support for Jaws 9 and 13 sleep mode into the Sodbeans installer (due to a bug report from the progl mailing list).
                </li></ul></li><li>Quorum Integration
            <ul><li>Upgraded Quorum to the 1.5 release.
                </li><li>Stop, pause and run improvements from within Sodbeans. Generally, Quorum should feel and run snappier. 
                </li><li>Adjusted and improved Quorum highlighting and brace matching.
                </li><li>As Quorum now uses 1.5, which has a new interpreter, this new system has been integrated into Sodbeans 3.0. Generally, this allows Quorum programs to run substantially faster than the previous version.
                </li><li>A number of code completion improvements have been finished, especially in relation to how parent variables are accessed within Quorum.
                </li><li>Added a control to automatically generate documentation from within Sodbeans. This new control uses the new -document flag in Quorum 1.5 and generates HTML based documentation. 
                </li></ul></li><li>Data Collection: Added gesture collection support to record common compiler errors and other Sodbeans specific usage statistics. As always, users must "opt-in," otherwise tracking is disabled. With that said, we are tracking issues like compiler errors (Quorum only) and generic Sodbeans usage, which could help us get a better idea of what features the community is and is not using. As such, our hope is that this feature will help us better understand how Sodbeans can be improved.
        </li></ul><h2>Sodbeans 2.5 - January 30, 2012</h2>
    <ul><li>Programming Language Enhancements
            <ul><li>The Hop programming language has been pulled out of Sodbeans as a standalone tool. The name of the language has been changed to Quorum. The details can be found on the <a class="ext-link" href="https://sourceforge.net/apps/trac/quorum/"><span class="icon">Quorum trac page</span></a>. 
                </li><li>Full integration with Quorum into Sodbeans, including a significant alteration of previous Hop code.
                </li><li>Build, Clean and Build, and Clean in Sodbeans now both compile Quorum code and output traditional Java bytecode into the project folder.
                </li><li>Quorum now has a full compiler plugin system, allowing users to extend the language using either Java or C++ (through JNI).
                </li><li>Removed the "repeat from" syntax from Quorum.
                </li><li>The new Quorum bytecode system now allows any program to be run on a standard java virtual machine outside of Sodbeans. The code produced by Quorum is orders of magnitude faster than previous interpreted mode, although this mode is still available and is used for auditory debugging.
                </li><li>The quorum compiler in Sodbeans is now an event-based multi-threaded architecture, which should speed up the user interface a bit.
                </li><li>Created a new command line client for Quorum, for users that would like to use it as a general purpose programming language outside of Sodbeans, using the editor of their choice.
                </li></ul></li><li>General Sodbeans Enhancements
            <ul><li>Significantly overhauled the auditory cues in Sodbeans. We have conducted an analysis of multiple screen readers and platforms to modify what Sodbeans' speaks. There is still more work to do here, but we think it is an improvement and welcome feedback.
                </li><li>Significantly improved how Sodbeans interacts with the screen reader JAWS and NVDA. By default, Sodbeans (optionally) now asks various screen readers to sleep when Sodbeans is open.
                </li><li>Added in a new Magnification system for visually impaired users. Currently, this feature is only implemented for Windows Vista and 7 (32 or 64-bit).
                </li><li>The editor in Sodbeans is now faster, especially under the condition that JAWS and the Java Access Bridge are both installed.
                </li><li>Conducted an optimization pass of some components in Sodbeans.
                </li><li>Revamped the Sodbeans 2.0 tutorial system. This included a number of minor fixes, some new tutorials, and improvements to navigation and the user interface.
                </li><li>Upgraded the Sodbeans text-to-speech engine to Phonemic 1.2, which we released a few weeks ago. This new system fixes a number of minor bugs, optimizes the speech architecture, and adds support for the PowerPC architecture.
                </li><li>Starting in this version, we are releasing an "all" version of Sodbeans, which includes all languages currently supported by <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a>. We plan to put this version approximately 1-2 weeks after the initial Sodbeans release.
                </li></ul></li><li>New Sodbeans Windows Installer
            <ul><li>The Sodbeans installer has been significantly revised on Windows, now with a new user interface. Installation for Windows users has always been more complex than other OS's for Sodbeans, for a number of complicated reasons, but we think this new version will be a step forward. As always, we only have so many systems to test on in the lab, so users are welcome to send feedback with their exact system configuration if they experience problems.
                </li><li>The installer now inserts scripts into JAWS and NVDA, if they exist on the system. These scripts disable screen reading for Sodbeans and enable self voicing by default. We highly recommend using this new default, as we think it significantly improves the experience, but users that do not wish to do so can either remove the scripts manually or deselect the option in the installer.
                </li><li>Sodbeans now requires .NET, the Visual Studio 2010 Redistributable, and, as usual, the Java JDK. If a user does not have the appropriate dependencies, the installer now attempts to install them. Note that users which prefer versions other than those bundled (.NET 4.0, JDK 1.60 update 30). For example, while we have bundled JDK 6 for this release, we have conducted some testing with JDK 7, which also seems to work fine. 
                </li><li>On Windows, the installer now automatically tries to detect various system properties and rewrite the sodbeans.conf file in the /etc folder. We hope that this will resolve some of the issues users have reported regarding Sodbeans not booting properly. As there are a tremendous variety of systems out there, we highly recommend that users still having problems should file a bug report on Trac.
                </li></ul></li><li>Sodbeans tutorials and curriculum
            <ul><li>We are approximately 5 chapters into a new book on Quorum and Sodbeans. These materials are currently only available to partner schools on the project, but we hope that will provide significantly more documentation and information for new users on how to use the tools, once it is ultimately released. We are including this kind of info into the release notes largely to indicate the kind of thing we are working on.
                </li><li>Created a significant number of tutorials on using Quorum and Sodbeans. These materials are available in wiki form on the Quorum site and are being continually built as the project progresses.
                </li></ul></li></ul><p>

    </p>
    <h2>Sodbeans 2.0 - July 13, 2011</h2>
    <ul><li>Hop Language Enhancements
            <ul><li>Added Errors, called exceptions in other languages. These errors now unify the error architecture in Sodbeans, making it easier to give good auditory cues to blind users from the omniscient debugger when something goes wrong at runtime. In other words, Sodbeans will no longer say unhelpful messages like, "Sodbeans has thrown an error." Now, Sodbeans can detect what went wrong and tell you what the problem is in plain English.
                </li><li>Added constructors. Since studies show that constructors with parameters are a net loss in terms of usability, Hop only allows for default constructors. Constructors, for any class, are optional.
                </li><li>A complete rewrite of the external call system. Creating native libraries is now much easier. The old external call system has been removed and all external calls have been rewritten as "plugins" in Hop.
                </li><li>Added the "is a" keyword, allowing you to detect whether a given object is of a particular class at runtime.
                </li><li>Added a new Hop Standard Library, with many common features, like Lists, Stacks, Queues, and other common structures.
                </li><li>Changed some keywords, according to the latest empirical studies internal to our lab. The keyword "null," for example, is now called "undefined" and some of the loop/if statement syntax is being changed slightly (e.g., removed the "with counter" syntax).
                </li><li>Added a new "repeat until" construct.
                </li><li>Added an automatic documentation generation system.
                </li></ul></li><li>Text-to-speech Upgrade
            <ul><li>The text-to-speech system has been largely rewritten. The design is now much cleaner and more flexible, which has been necessary as Sodbeans has increased in complexity.
                </li><li>The new text-to-speech engine, post release, is available as a separate Jar file, called phonemic (see sourceforge.net), and can now be used from any Java application.
                </li><li>Now includes the ability to distinguish between capital and lowercase letters using pitch changes on capital letters (Microsoft SAPI, Mac OS X and ORCA only).
                </li><li>Now includes support for Windows XP 32, Vista 32 and 64, Windows 7 32 and 64, Mac OS X (1.6 or above), Ubuntu and Vinux. The engine may work with other linux distributions.
                </li><li>Now compatible with the the following text-to-speech engines or screen readers: Microsoft SAPI, Mac OS X Voice Over (mostly), JAWS, NVDA, and ORCA.
                </li><li>Added the ability to change the speed and pitch of text-to-speech in a cross-platform way. Some implementations (e.g., JAWS), do not support pitch changes through their APIs, and as such, these calls are ignored when used under these systems.
                </li><li>Added the ability to change the speech engine in use at runtime (e.g., from SAPI to JAWS).
                </li><li>Added a new priority queue system. This new system is significantly faster, cleaner, and generally works better for sending complex sets of prioritized speech.  
                </li></ul></li><li>Sodbeans tutorial System
            <ul><li>Added a new tutorial system to Sodbeans, which gives auditory tutorials for much of Sodbeans and the Hop programming language.
                </li><li>Added tutorials for navigating Sodbeans, creating projects, and other Sodbeans related issues (e.g., common hotkeys).
                </li><li>Added a number of tutorials for the Hop programming language, including most major language features.
                </li><li>Added a number of tutorials for the new Hop standard library, including Stacks, Queues, File input/output, and other features.
                </li><li>Added a new welcome screen for Sodbeans with the tutorial embedded. This tutorial starts on the first bootup, but can be turned off for subsequent runs, if desired, by selecting the appropriate checkbox on the form.
                </li></ul></li><li>General Accessibility
            <ul><li>Replaced the windows installer with one using NSIS. This new installer is accessible without the Java access bridge.
                </li><li>Added a quick reference sheet for finding common hotkeys.
                </li><li>Added a multimedia code completion system
                </li><li>Added an auditory brace matching system into the editor. For technical reasons, this only works with Hop, but not the other <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> supported languages.
                </li><li>Added a series of hotkeys for controlling the text-to-speech engine, like volume, speech, pitch, engine, and voices.
                </li><li>Added a new preferences pane for controlling the text-to-speech engine.
                </li><li>Fixed a number of issues with screen reading various user interface components.
                </li><li>Added the beginning of a prosodic cue infrastructure. In the current release, capital letters now "Yell," making them more obvious.
                </li><li>Made the text-to-speech engine (phonemic) free and open source on a number of platforms.
                </li><li>Added accessibility support on Ubuntu and Vinux.
                </li><li>Significantly improved the way windows are read when they are opened or closed. This seemingly minor enhancement, we think, makes it much more obvious as to how you can navigate around Sodbeans.
                </li><li>Added a clicking sound when buttons are selected, making the selections more obvious.
                </li><li>Added a number of auditory cues that were missing, like one that fires when Sodbeans is minimized.
                </li><li>Significantly improved the way a number of components are read by the Sodbeans screen reader, especially tables, sliders, spinners, and toggle buttons.
                </li><li>Added the beginning of a set of fixes for a number of long-standing accessibility bugs in <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a>, especially with issues involving popups that do not receive focus events, like the CTRL+TAB key for changing between tabs. Not all problems are fixed, but we have made major progress and think a complete solution is ultimately possible.
                </li></ul></li><li>Hop Standard Library
            <ul><li>Hop now has the beginning of a standard library. We are hoping that this set of libraries will expand over time and we would encourage community contributions.
                </li><li>Now includes a series of basic mathematical functions (e.g., absolute value, sine).
                </li><li>Now includes most common data structures (e.g., arrays, lists, stacks, queues).
                </li><li>Now includes basic file input and output.
                </li><li>Now includes some, but not all, of the more advanced features in the text-to-speech engine.
                </li></ul></li><li>Miscellaneous changes and bug fixes
            <ul><li><a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> 7.0 compatible.
                </li><li>Fixed a major bug in the lexical scoping system.
                </li><li>Fixed a number of issues in the Hop debugger architecture that was added in Sodbeans 1.5.
                </li><li>Fixed a number of critical bugs in the type/type erasure systems.
                </li><li>Fixed a number of problems with the Sodbeans output windows.
                </li><li>Fixed a number of minor problems with reading (text-to-speech) various windows in the <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> platform.
                </li><li>Added hundreds of new unit tests for Hop and the standard library.
                </li><li>Fixed many more bugs.
                </li></ul></li></ul><h2 id="Sodbeans1.5December18th2010">Sodbeans 1.5, December 18th, 2010</h2>
    <ul><li>Hop Language Enhancements
            <ul><li>Hop now includes support for Multiple Inheritance. 
                </li><li>A new Array syntax and architecture has been developed using Hop Native libraries. 
                </li><li>Added new external library calls into the language.
                </li><li>Added auto-boxing for converting from primitive types to corresponding objects automatically.
                </li><li>Templating/Generics has been added into Hop. For example, to create an array with integers, you now say Array&lt;integer&gt; a
                </li><li>Added the Hop equivalent of public and private, including abstract functions. Abstract/virtual functions are called blueprint actions.
                </li><li>Added approximately 260 new unit tests for the compiler.
                </li></ul></li><li>New Debugger Architecture
            <ul><li>Substantial optimization: 50,000 percent speed increase in informal internal tests.
                </li><li>Added Breakpoints
                </li><li>Added a new, much faster, local variable window. 
                </li><li>Speech calls made in the debugger continue to be non-blocking, but commands issued while a program is running are now blocking by default. Non-blocking calls can be made by the System.Speech class.
                </li></ul></li><li>Text-to-speech enhancements
            <ul><li>Added a reinitialize function, which asks the system to reevaluate what text to speech engine it uses.
                </li><li>Added in the ability to create blocking text-to-speech calls. Unfortunately, these do not work with JAWS or NVDA, as these APIs do not appear to support such calls.
                </li><li>Added a new C++ Test utility for text-to-speech, which will hopefully make it easier to debug problems on various platforms.
                </li><li>Added a new Text-to-Speech engine for Mac OS X. This new system is faster and fixes a number of bugs.
                </li><li>Text-to-speech can now be muted on any platform by pressing the left control key.
                </li><li>Added in the ability to repeat the most recent speech. 
                </li><li>Added in the ability to copy the most recent speech to the clipboard.
                </li><li>Text-to-speech is now supported on Windows 7 (32 and 64), Vista (32 and 64), and XP (32 only), and Mac OS X. Virtual Box installations for Windows (7, Vista, or XP 64-bit), are not currently supported.
                </li></ul></li><li>Editor Enhancements
            <ul><li>Visual editor features are now aurally displayed to blind users. This includes red compiler underlines, yellow lightbulbs, breakpoint annotations, program counter annotations and other features. This feature works in any <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> supported programming language (e.g., Java, C++, Ruby, Hop).
                </li></ul></li><li>Bug Fixes – Over 260
            <ul><li>The open and save dialog now has appropriate tags for all buttons, both in Sodbeans and <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> (Thanks Oracle!), and should now read correctly in a screen reader.
                </li><li>Fixed a number of bugs with the debugger controls, which were inconsistently jumping to the wrong place, especially Step Over.
                </li><li>Local variable window calls are now queried on request, not queued up, significantly increasing speed and fixing some bugs.
                </li><li>Fixed an issue with the compiler error window that was causing errors to duplicate and sometimes disappear.
                </li><li>Fixed a series of bugs with text-to-speech on various platforms, especially Windows XP (Thanks Jude!)
                </li><li>Fixed a major bug causing Sodbeans users to only be able to build and debug hop projects.
                </li><li>Fixed a number of problems causing the compiler to throw exceptions to the user under weird conditions.
                </li><li>Fixed a bug causing methods named Main to not accept parameters.
                </li><li>Fixed a bug in the debugger causing null pointers to show up as #-1 in the debugger.
                </li><li>Fixed several bugs causing the debugger controls to magically disappear.
                </li><li>Fixed a number of bugs with returning variables from functions.
                </li><li>Fixed a number of minor compiler bugs.
                </li></ul></li></ul>
    <h2>Sodbeans 1.0, July 9th, 2010</h2>
    <p>
        This is the first official release of Sodbeans. Major features include
    </p>
    <ul><li>Support for the Hop programming language, a new, object-oriented programming language designed in empirical studies on humans at Southern Illinois University Edwardsville, Central Washington University, and Washington State University.
        </li><li>A talking debugger that executes code forward and in reverse (the Hop Omniscient Debugger)
        </li><li>Hop file Navigator
        </li><li>Hop language documentation (wiki only)
        </li><li>Hop projects and File types supported within <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a>
        </li><li>External library support to connect Hop to Java (similar to the Java Native Interface, but for Hop)
        </li><li>Sodbeans is built on Sappy 1.0 and <a class="wiki" href="/apps/trac/sodbeans/wiki/NetBeans">NetBeans</a> 6.9. As such, it has the same accessibility features available in Sappy 1.0.
        </li>
    </ul><p>
</div>
<?php include("../static/templates/pagefooter.template.php"); ?>