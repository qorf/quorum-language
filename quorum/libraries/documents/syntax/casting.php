<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Converting between types in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Type Casting</h1>
		<p>How to try and convert one type to another.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h2>Converting one type to another</h2>
<p>
    Sometimes you need to convert one type to another, a common task to do while
    programming often termed type casting. Suppose, for example, that we had 
    text like so:
</p>
<p><pre class="code"><code>
text someText = &quot;4.2&quot;
</code></pre></p>
<p>
    This text has the value of 4.2, which could also be a number, but how would 
    you convert it? In Quorum, we do this using the cast action:
</p>
<p><pre class="code"><code>
&lt;type&gt; &lt;variable&gt; = cast(&lt;type to convert to&gt;, &lt;value to be converted&gt;)
</code></pre></p>
<p>
    Cast needs to be passed two parameters, first the type we wish to convert 
    our value to and second the value value to be converted. Cast will then 
    convert the value and assign the value to a variable. Given the example 
    above we would convert 'someText' to a number with the following code:
</p>
<p><pre class="code"><code>
number someNumber = cast(number, someText)
</code></pre></p>
<p>
    The word cast here indicates to the computer that you are requesting that 
    the value of &quot;someText&quot; be converted into an actual number. Keep 
    in mind that you can pass invalid values here, like &quot;stuff&quot; that 
    is not a valid number. In these cases, your program may not run correctly 
    when you call cast.
</p>
<h2>Casting Objects</h2>
<p>
    It is also possible to cast object in Quorum. Suppose we had an Array and 
    we retrieved an object from the array, as follows:
</p>
<p><pre class="code"><code>
use Libraries.Containers.Array
Array&lt;Integer&gt; myArray
myArray:Add(1)
Object item = myArray:Get(0)
</code></pre></p>
<p>
    In the above code an array that contains Integer objects is created. Then 
    an integer is added to the array. Then we retrieve the item from the array 
    and store it in the Object 'item'.
</p>
<p>
    Now we can cast 'item' back to an Integer.
</p>
<p><pre class="code"><code>
Integer convertedItem = cast(Integer, item)
</code></pre></p>
<h2>Cast Errors</h2>
<p>
    Sometimes an error will happen when casting. In this next example we will 
    go over how to handle these errors. Consider the following code:
</p>
<p><pre class="code"><code>
text myText = &quot;1.3&quot;
integer result = cast(integer, myText)
</code></pre></p>
<p>
    In this example, we cast the text value 'myText' to an integer. However, 
    1.3, the text is actually a number. When this cast is executed an error will 
    be triggered and the program's output will show the error message,
    &quot;Cast Error: An error has occurred while trying to cast&quot;, and 
    the stack trace.
</p>
<p>
    To handle this runtime error we can use the check detect blocks handle the 
    error and prevent our code from stopping. The following code is an example 
    of how you might handle the cast error.
</p>
<p><pre class="code"><code>
use Libraries.Language.Errors.CastError
check
   text myText = &quot;1.3&quot;
   integer result = cast(integer, myText)
end
detect e of type CastError
   output myText + &quot; was not cast to an integer.&quot;
end
</code></pre></p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>