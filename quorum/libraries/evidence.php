<?php require_once("static/templates/pageheader.template.php"); ?>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Evidence-Oriented Programming</h1>
		<p>Bringing randomized controlled trials to human factors 
                    decisions in programming language design. You know ... science.</p>
	</div>
</div>

<div class="content index-content">
<h4>Programming languages should be designed with human factors as a primary concern.</h4>
<p align="justify">
    Traditional programming languages have been designed predominately with technical
    concepts and machines in mind. While such concerns are obviously critical, human beings
    ultimately use such tools in the broad development community. In evidence-oriented
    programming, human factors evidence takes a first-class seat in the language's 
    design. All factors related to programming are considered, up for debate, and are
    subject to change if a community member shows rigorous evidence that another 
    approach is better. This is true both for technical and human factors considerations.
    To our knowledge, Quorum is the first programming language to attempt this.
</p>
<h4>How does evidence-oriented programming work?</h4>
<ul>
    <li>Quorum's design is fixed, barring a new randomized controlled trial. Submitted claims
    will be examined by experts in potentially a variety of fields (e.g., statistics, experimental design, psychology, computer science).</li>
    <li>Community members may submit changes to any aspect of Quorum's design</li>
    <li>For technical matters (e.g., bugs, efficiency improvements), changes are done
    through a separate process, in order to expedite any critical adjustments</li>
    <li>For tool improvements to Quorum (e.g., debugger improvements, IDE improvements), 
        rigorous evidence gathering still applies. </li>
</ul>
<h4>What evidence is there for Quorum already?</h4>
<p align="justify">
    Programming language design is complicated, as is likely obvious. No amount of 
    study will fix all usability or human factors concerns, or appease everyone, but
    the general goal is to make the greatest number of humans as productive as
    possible. So far, we have published and collaborated with a team of scientists, world-wide, 
    on many systems, both in and out of Quorum. For example, the evidence on Quorum's
    <a href="http://dl.acm.org/citation.cfm?id=2534973">syntactic choices</a> is extensive. Similarly,
    we used all known empirical studies on the design of type systems when designing Quorum's. 
    While this description barely scratches the surface, published works on the design
    are available that want more information.
</p>

<h4>What is the standard of evidence for core changes to Quorum?</h4>
<p align="justify">Quorum requires a standard of evidence for language features that are commensurate
with the change being requested. For example, core designs for the syntax have
already been vetted in randomized controlled trials with human beings (e.g., see <a href="http://dl.acm.org/citation.cfm?id=2534973">Stefik and Siebert</a>)
Thus, changes require more, or better, evidence. For core changes, we highly recommend
familiarizing yourself with the <a href="http://ies.ed.gov/ncee/wwc/pdf/reference_resources/wwc_procedures_v2_1_standards_handbook.pdf">
    What Works Clearinghouse</a> standard. Anecdotal claims regarding core feature changes will almost
    certainly be rejected. Generally, the programming language wars appear to 
    be too complex to solve with beliefs alone.
</p>
<h4>What is the standard of evidence for Quorum's standard library?</h4>
<p>Submissions to add to the standard library require less evidence.
    We ask that the submission follows the coding standard, that the library 
    actually works, and it provides some kind of useful functionality. If you want to 
replace an existing part of the standard library, the burden is on you to prove 
that the change benefits human beings. Again, claims must be actionable and anecdotes 
are not acceptable, unless the claim is really obvious (e.g., a submission that fixes a known bug).
</p>

<h4>Submitting a change or library to Quorum</h4>
<ul>
    <li>First, you must login, through Google or through a Quorum account</li>
    <li>Next, submit a change through our <a href="submit_library.php">Library
            Submission System</a>. The license agreement must be accepted for your submission. 
            Special thanks to Terence Parr for allowing us to adapt the ANTLR submission license.</li>
    <li>Libraries, or requests for core feature changes, 
        can be submitted.</li>
    <li>The change request, and any evidence submitted will go
    through a peer review process. The depth of the review depends again on 
    what was submitted. Extensive change requests may require extensive reviews.</li>
    <li>If your change or library is accepted, you get a badge, because you are
        awesome. Multiple and better badges can be earned, depending on your contribution
        to the language.</li>
</ul>
<h4>The benefits of evidence-oriented design</h4>
<p align="justify">
Since Quorum is evidence based, the entire community has a buy-in toward adjusting 
and improving the language over time. To keep the language as stable as possible, 
core language features should be unchanged without solid empirical evidence. We
feel this is especially important, given the long history of 
language designers using little evidence (if any) when making human factors decisions.
Given that evidence does require the language to change, from time-to-time, developers are informed
of important changes as soon as possible. This is why, for example, the Quorum
designers started with syntax and type systems in controlled trials. The bar
to adjust these features is, thus, extremely high. 
</p>
</div>
<?php require_once("static/templates/pagefooter.template.php"); ?>