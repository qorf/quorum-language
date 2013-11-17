<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Creating Arrays in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Arrays</h1>
		<p>Arrays provide one way to store several values.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h1>Arrays</h1>
<p>
    In a computer, an Array is a block of memory that stores several versions of an 
    item. In Quorum, you can create an array by first telling the Quorum language 
    that you want access to this feature, using the &quot;use&quot; statement, 
    like so:
</p>
<p><pre class="code"><code>
use Libraries.Containers.Array
</code></pre></p>
<p>
    Next, in order to create an array, you first say the word Array, followed by a 
    <a href="types.php">type</a> (e.g., integer) in angle brackets and name of your 
    choosing, which in the following example is a:
</p>
<p><pre class="code"><code>
Array&lt;integer&gt; a
</code></pre></p>
<p>
    The array a, by default, now contains ten spots that are available for putting in 
    values, and these values are of type integer. Any value is accepted, including 
    integers, numbers, or custom data types of your making. For example, here is 
    how you would add two integer values, 10, and 15, into the array:
</p>
<p><pre class="code"><code>
a:Set(0, 10) //put a value in the first slot in the array, which is named 0
a:Set(1, 15) //put a value in the second slot in the array, which is named 1
</code></pre></p>
<p>
    We can also build up arrays automatically using the repeat statement. For 
    example, here is a program that fills an array with values from one to ten:
</p>
<p><pre class="code"><code>
use Libraries.Containers.Array
class Main
   action Main
      integer i = 0
      Array&lt;integer&gt; a
      repeat 10 times
         a:Set(i, i)
         i = i + 1
      end
   end
end
</code></pre></p>
<p>
    Similarly, if there is a value in the array, you can retrieve it using the 
    following syntax:
</p>
<p><pre class="code"><code>
Array&lt;integer&gt; a
a:Set(0, 10)
a:Set(1, 15)
integer b = a:Get(0) + a:Get(1)
</code></pre></p>
<p>
    In the above example, the integer b would be assigned the value of 25. And 
    finally, you are not limited to integer objects. Arrays can be asked to 
    store anything, including integer, text, boolean, or number values. They 
    can even store custom types of your own design. For example, suppose you 
    had a Dog <a href="classes.php">class</a>:
</p>
<p><pre class="code"><code>
class Dog
   integer NumLegs = 4
   action Bark()
   end
end
</code></pre></p>
<p>
    If you then wanted to create 12 Dog objects and place them in the array, you 
    would do so like this:
</p>
<p><pre class="code"><code>
use Libraries.Containers.Array
class Main
   action Main
      Array&lt;Dog&gt; a
      a:SetSize(12)
      repeat 12 times
         Dog dog
         a:Set(i, dog)
         i = i + 1
      end
   end
end
</code></pre></p>
<p>
    To change the attributes of a Dog in the array, you would first insert the Dog 
    object into a new class object and then use the appropriate functions to change 
    the Dog object.  First, let's give the Dog object an attribute called <code>name</code>
    and an action that lets you change the name called setName:
</p>
<p><pre class="code"><code>
class Dog
    integer NumLegs = 4
    text name
    action SetName(text n)
        name = n
    end
    action Bark()
end
</code></pre></p>
<p>
    Now, in the Main class, create a new Dog object and set it equal to the Dog in 
    the array that you would like to edit:
</p>
<p><pre class="code"><code>
Dog thisDog = a:Get(0)
</code></pre></p>
<p>
    Next, call the setName action.  If we, for instance, want to name the 
    dog &quot;Spot,&quot; we will call the setName action like so:
</p>
<p><pre class="code"><code>
thisDog:SetName(&quot;Spot&quot;)
</code></pre></p>
<p>
    Now, the Dog at position 0 in the array <code>a</code> has the name &quot;Spot.&quot;
</p>
<p>
    For further information on arrays, check out the standard library documentation 
    for <a href="../../Libraries/Containers/Array.html">the array class</a>.
</p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>