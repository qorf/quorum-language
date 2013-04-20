<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab7_2';
</script>

<h1>Lab 7.2: Lists vs Arrays</h1>
<h2>Objectives</h2>
<p>
    The goal of this lab is to learn the following:
</p>
<ul>
    <li>
        How to create and use arrays
    </li>
    <li>
        How to create and use lists
    </li>
    <li>
        How to use lists vs arrays, and when to use each
    </li>
    <li>
        How to add and delete items from strings and arrays
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this lab, you will learn how the data structures arrays and lists differ from each other. Both data structures are able to hold large amounts of data, including multiple types of values such as integer, number, and text. Recall to <a href="lab2_1.php">lab 7_1</a>, where an array was described as a 1D table, where each "cell" in the table held a value. In <a href="lab2_1.php">lab 7_1</a>, the arrays never had more than 20 "cells." As was mentioned in <a href="lab2_1.php">lab 7_1</a>, arrays are defaulted to a size of 10, and if they go over that, then quorum automatically re-sizes the array to hold more. This has to be done because arrays hold values that are contiguous in memory. The list structure differs because it stores items in nodes. Each of these nodes stores an item and a reference to the next and previous node in the sequence. This is known as a doubly-linked list, which is how all lists in quorum are created. Because of this, the list data structure does not need to re-size itself like an array would when the structure is filled. An array can be visualized like this:
</p>
<table class="table">
    <tr>
        <td>
            <b>
                Value
            </b>
        </td>
        <td>
            Item 1
        </td>
        <td>
            Item 2
        </td>
        <td>
            Item 3
        </td>
        <td>
            Item 4
        </td>
        <td>
            Item 5
        </td>
    </tr>
    <tr>
        <td>
            <b>
                Index
            </b>
        </td>
        <td>
            0
        </td>
        <td>
            1
        </td>
        <td>
            2
        </td>
        <td>
            3
        </td>
        <td>
            4
        </td>
    </tr>
</table>
<p>
    Where as a list can be visualized like this:
</p>
<table class="table">
    <tr>
        <td>
            NULL
        </td>
        <td>
            <b>
                &lt;
            </b>
        </td>
        <td>
            Item 1
        </td>
        <td>
            <b>
                &gt;
            </b>
        </td>
        <td>
            <b>
                &lt;
            </b>
        </td>
        <td>
            Item 2
        </td>
        <td>
            <b>
                &gt;
            </b>
        </td>
        <td>
            <b>
                &lt;
            </b>
        </td>
        <td>
            Item 3
        </td>
        <td>
            <b>
                &gt;
            </b>
        </td>
        <td>
            <b>
                &lt;
            </b>
        </td>
        <td>
            Item 4
        </td>
        <td>
            <b>
                &gt;
            </b>
        </td>
        <td>
            <b>
                &lt;
            </b>
        </td>
        <td>
            Item 5
        </td>
        <td>
            &gt;
        </td>
        <td>
            NULL
        </td>
    </tr>
</table>
<p>
    As seen above, each node in a list contains a reference to the node before it and after it, with the exception of the end cases. One downside to lists is that each node can only be accessed through the first or last nodes, since lists are not held contiguous in memory, there's no way to access any specific node without accessing the first or last one. In order to better understand the differences between lists and arrays, you're going to build a program that populates both an array and a list with random integers, replaces a portion of those integers, and then deletes each item in the structures. You will be using the <tt>Libraries.System.DateTime</tt> to time how long it takes each structure complete the before-mentioned tasks, and then output the time for each to the user.
</p>
<p>
    Start Sodbeans. Create a new “Quorum Application” project, and name it <b>Lab7_2</b>. Create a second class called <tt>Compute</tt>. You will need to use the following libraries for this lab:
</p>
<pre class="code">
    use Libraries.Containers.List
    use Libraries.Containers.Array
    use Libraries.Compute.Random
    use Libraries.System.DateTime
</pre>
<h2>Class <tt>Compute</tt></h2>
<p>
    In class <tt>Compute</tt>, create an array and a list. A list can be made using the same syntax as the array:
</p>
<pre class="code">
    List&lt;integer&gt;
    myList
    Array&lt;integer&gt; myArray
</pre>
<p>
    In order to create a timer for these actions, you will need to create a <tt>DateTime</tt> object, and use the <tt>SetEpochTime()</tt> action. This action sets the date/time to be represented by the specified value. So, if you set it to 0, you can later call the <tt>GetSecond</tt> action from the <tt>DateTime</tt> object and it will return the number of seconds passed since the call to <tt>SetEpochTime</tt>:
</p>
<pre class="code">
    DateTime timer

    timer:SetEpochTime(0.0)
    //do work here
    integer time = timer:GetSecond()
    say "time elapsed:" + time
</pre>
<p>
    Next, you will create two actions
</p>
<ul>
    <li>
        <b>
            action ComputeList
        </b>
    </li>
</ul>
<p>
    Use the <tt>Add()</tt> action to add 128 random integers into the list/array. Use the <tt>Set()</tt> action to set the first 64 items in the list/array to a new random integer. Use the <tt>RemoveFromFront()</tt> action to remove all the items in the list/array. Time how long it took to populate, re-populate, then delete the values from the list/array. Report the time to the user (output or say).
</p>
<ul>
    <li>
        <b>
            action ComputeArray
        </b>
    </li>
</ul>
<p>
    <tt>ComputeArray</tt> will do the same as <tt>ComputeList</tt>, but it will do it with the Array structure instead.
</p>
<h2>Sample Output</h2>
<p>
    In <tt>Main</tt>, call the actions you previously created. Output should look similar to this:
</p>
<pre class="code">
    Starting list computations...
    Time elapsed for list computations was 10 seconds

    Starting array computations...
    Time elapsed for array computations was 15 seconds
</pre>
<p>
    When you're done, show your program to your instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>