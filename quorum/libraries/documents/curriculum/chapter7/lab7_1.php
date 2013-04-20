<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab7_1';
</script>

<h1>Lab 7.1: An Introduction To Arrays</h1>
<h2>Objectives</h2>
<p>
    The goal of this lab is to learn the following:
</p>
<ul>
    <li>
        How to create and use arrays
    </li>
    <li>
        How to add, delete, and output items from an array
    </li>
    <li>
        How to use strings in arrays
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this lab, you will learn how to use the data structure, arrays. You will create a class that counts the number of times a randomly rolled die will show any particular face, a class that tallies the number of votes, and a class that stores peoples' names and the sports they like in an array. An array can be visualized as a 1D table, where each "cell" is a placeholder for a piece of data. Arrays can hold any type of data, including integers, numbers, text, and even class objects. In a computer, an array is a block of memory, where each item or "cell" in the array is contiguous in memory. For example, look at the table below:
</p>
<table class="table">
    <tr>
        <td>
            <b>
                Index #
            </b>
        </td>
        <td>
            <b>
                Value
            </b>
        </td>
        <td>
            <b>
                Memory address
            </b>
        </td>
    </tr>
    <tr>
        <td>
            0
        </td>
        <td>
            42.0
        </td>
        <td>
            4000
        </td>
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            28.5
        </td>
        <td>
            4001
        </td>
    </tr>
    <tr>
        <td>
            2
        </td>
        <td>
            78.9
        </td>
        <td>
            4002
        </td>
    </tr>
</table>
<p>
    As seen above, you can know that the value at index <i>i </i>will be located in memory between <i>i-1</i> and <i>i+1</i>. This can be handy when traversing a large amount of data. In Quorum, you can create an array by first telling Quorum that you want to access this feature, using the "use" statement "Libraries.Containers.Array." To create an array, you first say the word Array, followed by the data type in angle brackets, and then a name for the array:
</p>
<pre class="code">
    use Libraries.Containers.Array
    Array&lt;integer&gt; myArray
</pre>
<p>
    By default, the array now takes up enough memory for ten integers, so it has 10 cells with indexes 0-9. With arrays, the first value is accessed at index 0. As a rule of thumb, the last value in an array will be accessed at index <i>n-1</i>, where n is the number of values stored in the array.
</p>
<p>
    Start Sodbeans. Create a new “Quorum Application” project, and name it <b>Lab7_1</b>. The Main.quorum file should contain a <tt>Main</tt> class and <tt>Main</tt> action.
    You will create three additional classes in this project. Name the classes <tt>RollingDie</tt>, <tt>PollCount</tt>, and <tt>StringArray</tt>.
</p>
<h2>Class <tt>RollingDie</tt></h2>
<p>
    In class <tt>RollingDie</tt>, create an array of type integer and name it <tt>frequency</tt>. You will also be using the <tt>Random</tt> class to generate random integers between 1 and 500. The goal of this class is to create a function that tallies the number of times a particular face shows up on a randomly thrown die, for 6000 rolls. How can you keep track of that many variables? An array would be an excellent choice, since you can keep adding values into this one structure and will be able to easily access those same values. Your class may look something like the following:
</p>
<pre class="code">
    class RollingDie
    integer face = 0 //random die value 1 - 6
    integer roll = 0 //roll counter 1-6000
    Array&lt;integer&gt; frequency
    integer randomValue = random:RandomIntegerBetween(1, 500)

 action Tally()
  /*roll the die 6000 times
   *calculate a random # between 1 and 6, and assign that value to face
   *frequency:Add(face)
   */

  //output the results
  /*repeat 6 times
   *output "frequency from 1-6:" + frequency:Get(face)
   */
</pre>
<p>
    Notice that one way to add values to an array is by using the Add(type) action. This action adds values to the end of the array. You can access those values by calling the <tt>Get:()</tt> action. The results should look similar to:
</p>
<pre class="code">
    Frequency from 1-6:
    1029
    951
    987
    1033
    1010
    990
</pre>
<h2>Class <tt>PollCount</tt></h2>
<p>
    Now that you have a better understanding of how arrays work, you will use them for a common computer science problem that is well suited for arrays: poll counting. Like class <tt>RollingDie</tt>, <tt>PollCount</tt> will contain an action that counts the frequency of a rating (1-10). It will then output how many times that rating was voted for. Create 2 arrays:
</p>
<pre class="code">
        Array&lt;integer&gt; frequency
        Array&lt;integer&gt; response
</pre>
<p>
    Next, using the <tt>Add:()</tt> action, add 20 integer vales between 1 and 10 to the array response. Using what you learned from the <tt>RollDie</tt> class, implement the action so it tallies up the number of votes each rating got. Hint: you will need to use the <tt>Get()</tt> action nested inside an <tt>Add()</tt> action. Finish the action by printing off the results to the user:
</p>
<pre class="code">
    Ratings from 1 to 10:
    1
    1
    1
    1
    2
    3
    3
    2
    5
    1
</pre>
<h2>Class <tt>StringArray</tt></h2>
<p>
    Now that you've seen integer use in arrays, it's time to use text in arrays. In class <tt>StringArray</tt>, create 2 arrays:
</p>
<pre class="code">
    Array&lt;text&gt; names
    Array&lt;text&gt; sports
</pre>
<p>
    This class will contain an action that asks the user for their name, inputs that text into array <tt>names</tt>, then asks the user for a sport they enjoy, and inputs that text into array <tt>sports</tt>. This should repeat 3 times, and then output the name of the person coupled with the sport they enjoy playing. Example output:
</p>
<pre class="code">
    Enter a name:
    Brandon
    Enter a sport you like:
    Track and Field

    Brandon likes Track and Field
</pre>
<h2>Sample Output</h2>
<p>
    In Main, create objects of each class and call each of the actions you just made, so that when ran, all the results will be shown one after the other. Output may look like this:
</p>
<pre class="code">
    Frequency from 1-6:
    1029
    951
    987
    1033
    1010
    990
    Ratings from 1 to 10:
    1
    1
    1
    1
    2
    3
    3
    2
    5
    1

    Enter a name:
    Brandon
    Enter a sport you like:
    Track and Field
    Brandon likes Track and Field
</pre>
<p>
    Show your instructor your code when you're finished.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>