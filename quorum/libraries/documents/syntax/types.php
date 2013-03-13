<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Variable Types in Quorum';
</script>
<h1>Types</h1>
<p>
    When programming a computer, we often say that a variable has a type. For 
    example, suppose that I have a variable with the name a. It could be the 
    case that this variable is a number, like 4.2, or it could some text that 
    I have written for a book, like, &quot;It was the best of times.&quot; In 
    Quorum, there are several types, and if those types are not enough, you 
    can define your own.
</p>
<h2>Primitive Types in Quorum</h2>
<p>
    In this section, we go over the various types allowed in the Quorum programming language.
</p>
<h3>integer</h3>
<p>
    An integer is a number, either positive, negative, or zero, that does not 
    have a decimal point. You can create an integer in Quorum by declaring it 
    as follows:
</p>
<p><pre class="code"><code>
integer a = 10
</code></pre></p>
<p>
    This would create a variable, named a, that has the value of 10.
</p>
<h3>number</h3>
<p>
    A number is similar to an integer, except that it allows decimal points. 
    For example:
</p>
<p><pre class="code"><code>
number a = 10.4
</code></pre></p>
<h3>boolean</h3>
<p>
    The type of boolean has only two possible values, true and false. You can 
    create a boolean as follows:
</p>
<p><pre class="code"><code>
boolean a = true
</code></pre></p>
<p>
    To use a boolean, you can either create it directly, like above, using the 
    keywords true or false. However, booleans come up regularly in computers 
    when we compare different values. For example, suppose we had two integers, 
    a and b, and we wanted to see whether a was &quot;bigger&quot; than b. We 
    could do this using the following code:
</p>
<p><pre class="code"><code>
integer a = 5
integer b = 6
boolean answer = a &gt; b
</code></pre></p>
<p>
    The variable answer in this case would be set to false, as a is less than b.
</p>
<h3>text</h3>
<p>
    The text type is designed to let you use normal written text in your computer 
    program. You can type any phrase, from a phrase to a page in a book. You 
    can create a variable of type text as follows:
</p>
<p><pre class="code"><code>
text a = &quot;Hello, World!&quot;
</code></pre></p>
<h2>Custom types</h2>
<p>
    If none of these types seem to work for you, you can also create your own 
    custom types in Quorum using <a href="classes.php">classes</a>. Creating 
    classes is more difficult than using the previous types, but it is 
    especially useful when you want to represent something complicated, like a 
    dog, a picture, or a chair.
</p>
<h2>Type conversion</h2>
<p>
    Sometimes you need to convert one type to another. This is called converting 
    types, or more commonly, type casting. Suppose, for example, that you had 
    text like so:
</p>
<p><pre class="code"><code>
text someText = &quot;4.2&quot;
</code></pre></p>
<p>
    This text has the value of 4.2, which looks like a number would, but how 
    would you convert it? In Quorum, you do this using the cast action:
</p>
<p><pre class="code"><code>
number someNumber = cast(number, someText)
</code></pre></p>
<p>
    The word cast here indicates to the computer that you are requesting that 
    the value of &quot;someText&quot; be converted into an actual number. 
    Keep in mind that you can pass invalid values here, like &quot;stuff&quot; 
    that is not a valid number. In these cases, your program may not run 
    correctly when you call cast.
</p>
<p>
    You can learn more about casting on the <a href="casting.php">type casting page</a>.
</p>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../static/templates/pageheader.template.php"); ?>