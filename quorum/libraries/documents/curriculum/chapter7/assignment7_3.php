<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'assignment7_3';
</script>

<h1>Short Assignment 7.3: Hash Tables</h1>
<h2>Objectives</h2>
<p>
    The goal of this assignment is to learn the following:
</p>
<ul>
    <li>
        How to create and use hash tables
    </li>
    <li>
        Hash table efficiencies
    </li>
    <li>
        Hash table collision handling
    </li>
    <li>
        Open address vs closed address collision handling
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this assignment you will create a program that builds hash tables. Each table you build will use a form of collision resolution called chaining. There are two general types: open addressing and closed addressing resolutions. Under closed addressing, there is a technique called chaining. Chaining is where you store colliding keys in a linked list at the same hash table index. For instance, if your hash function is (N mod 10), where <code>N</code> is your key value, and you input 60, 70, and 80, then all of those values will be stored in a linked list at index 0 in the hash table. Open addressing consists of techniques that store colliding keys elsewhere in the same table.
</p>
<p>
    Given a good hash function, search times should be as follows:
</p>
<ul>
    <li>
        Best case: O(1)
    </li>
    <li>
        Worst case: O(n)
    </li>
    <li>
        Average case: O(a)
    </li>
</ul>
<p>
    Where <code>n</code> = number of elements stored in the hash table and <code>a</code> = load factor(the average length of a chain).
</p>
<p>
    Chaining is one of the most common collision resolution techniques because it provides a relatively easy way to decrease insertion time and increase how uniform the items are in the array. Because chaining uses linked lists, you get constant-time access to lists given a key. This means that you get constant-time insert ( O(1) ). However, if the size of the array is too large, you get too many empty array entries. If the array is too small, then the lists within become too long, and the efficiency drops.
</p>
<h2>Class <code>LinkedHashEntry</code></h2>
<p>
    This class should contain two integer values: integer <code>key</code>, and integer <code>value</code>. It will also need an object of <code>LinkedHashEntry</code> that is set to undefined. You will need to create getters and setters for key, value, and your <code>LinkedHashEntry</code> object. This might look similar to this:
</p>
<pre class="code">
    LinkedHashEntry next = undefined
    action GetNext returns LinkedHashEntry
    return next
    end
    action SetNext(LinkedHashEntry newNext)
    next = newNext
    end
</pre>
<h2>Class <code>Chaining</code></h2>
<p>
    In this class, you will be creating actions that create an empty hash table, inserts items into the hash table (using chaining), and gets items from the hash table. In order to "chain" items in the same index in the hash table, you need to create an array that consists of lists which are of type <code>LinkedHashEntry</code>. Yours should look like this:
</p>
<pre class="code">
    Array &lt List &lt LinkedHashEntry&gt&gt table
</pre>
<p>
    Now you have an array that contains multiple lists called table.
</p>
<ul>
    <li>
        <b>
            action HashMap
        </b>
    </li>
</ul>
<p>
    This action should create an array filled with 128 lists that are set to undefined.
</p>
<ul>
    <li>
        <b>
            action Get(integer key) returns integer
        </b>
    </li>
</ul>
<p>
    Use the hash function:
</p>
<pre class="code">
    integer hash = (key mod tableSize) //tableSize = 128
</pre>
<p>
    This action should return an integer from the hash table given the key. If there is no value at the given index, then return -1.
</p>
<ul>
    <li>
        <b>
            action Put(integer key, integer value)
        </b>
    </li>
</ul>
<p>
    Again, use the hash function:
</p>
<pre class="code">
    integer hash = (key mod tableSize) //tableSize = 128
</pre>
<p>
    This action should insert the input value into the hash table according to the key and hash function. *Hint* If an item is present at the current index, "chain" the value into the list(current hash table index).
</p>
<h2>Class <code>Main</code></h2>
<p>
    In <code>Main</code>, create a <code>Chaining</code> object. Next, create a <code>DateTime</code> object (use Libraries.System.DateTime) and a random integer generator between 0 and 256(use Libraries.Compute.Random). Create an empty hash table, and populate it with 96 random integers. Use the <code>SetEpochTime(number)</code> and <code>GetSecond()</code> actions to create a timer to time how long it takes to insert the items into the hash table. Report this number to the user. Next, search the hash table 96 times for a random integer. Again, create a timer to time how long the searches take. Report this number to the user. Repeat the above insertion and searching again, but this time insert 384 random integers, and search 384 times for a random integer. Notice the difference in times for the insertions? How about the searches?
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following:
</p>
<pre class="code">
    It took 5 seconds to populate a hash table with 96 values using chaining.
    It took 10 seconds to search a hash table for 96 values using chaining.
    It took 6 seconds to populate a hash table with 384 values using chaining.
    It took 11 seconds to search a hash table for 384 values using chaining.
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>