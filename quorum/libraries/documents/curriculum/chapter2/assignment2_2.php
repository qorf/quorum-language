<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 2';
</script>

<h1>Short Assignment: Math</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul>
<li>More math calculations</li>
<li>Analyzing word problems</li>
</ul>
<h2>Overview</h2>
<p>
Write a program to compute the average of the distance a mouse runs during trials in an experiment.  The experiment data is provided to calculate the average distance in terms of yards. In addition, write code to convert yards to different units of measurement.
</p>
<h2>Description</h2>
<p>
The experiment was conducted to determine how far a mouse could run before encountering roadblocks or becoming exhausted. Six trails were conducted. The results of the experiment are shown in the table below:
</p>
<p>
<table class="table">
<tr><td>Trials</td><td>Distance (yards)
</td></tr><tr><td>1</td><td>98.2
</td></tr><tr><td>2</td><td>85.4
</td></tr><tr><td>3</td><td>76.7
</td></tr><tr><td>4</td><td>64.4
</td></tr><tr><td>5</td><td>89.9
</td></tr><tr><td>6</td><td>82.5
</td></tr></table>
</p>
<p>
The program adds all of the trial distances together and computes the average distance in yards.  The average is then converted into different measurement units.  The table below shows the conversion of the yard into various units.
</p>
<p>
<table class="table">
<tr><td>Units</td><td>Centimeters (cm)</td><td>Feet (ft)</td><td>Inches (in)</td><td>Meters (m)</td><td>Miles (mi)
</td></tr><tr><td>Yard (yd)</td><td>91.44</td><td>3</td><td>36</td><td>0.9144</td><td>1/1760
</td></tr></table>
</p>
<p>
All results are rounded to the nearest two decimal points.  The results should match the following output:
</p>
<p><pre class="code"><code>
The average in yards is 87.1 yd.
The average in centimeters is 7964.42 cm.
The average in feet is 261.3 ft.
The average in inches is 3135.6 in.
The average in meters is 79.64 m.
The average in miles is 0.05 mi.
</code></pre></p>
<h2>Analysis</h2>
<ul><li>Calculate the average distance in yards.
</li><li>Convert the average in yards to each of the provided units (centimeters, feet, inches, meters, and miles).
</li><li>Round each result to the nearest two decimal points. For this, you will need to use the <tt>Math</tt> library. The <tt>Math</tt> library requires the following <strong><tt>use</tt></strong> statement:
<pre class="code">use Libraries.Compute.Math
</pre></li></ul>
<h2>Design Criteria</h2>
<ul><li>Create a new project and name it <strong>Assignment2_2</strong>.
</li></ul>

<?php include("../../include/footer.php"); ?>