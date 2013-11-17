<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Operators in Quorum';
</script>  
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Operators</h1>
		<p>How to use +, -, *, /, and other operators with various types.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>

<h1>What are operators?</h1>
<p>
    In computer science, we often want to ask the computer to do certain mathematical 
    operations. For example, we may want the computer to compare a set of numbers 
    to see if they are equal or we may want to add, subtract, multiply, or divide 
    numbers. In this section, we discuss this concept, operators, including which
    how they work in the Quorum programming language.
</p>
<h2>integer and number types</h2>
<p>
    Integers are those numbers, positive or negative, without decimal points, 
    while numbers are any numbers, including those with decimal points. Both 
    integers and numbers have the same operators defined.
</p>
<ul>
    <li>+: the plus operator adds together numbers and concatenates, or puts together text</li>
</ul>
<p>
    The following code would give the variable a the value of 10 and the variable b the value of &quot;firefox.&quot;
</p>
<p><pre class="code"><code>
integer a = 5 + 5
text b = &quot;fire&quot; + &quot;fox&quot;
</code></pre></p>
<ul>
    <li>-: the minus operator subtracts numbers</li>
</ul>
<p>
    The following code would give the variable a the value of 0
</p>
<p><pre class="code"><code>
integer a = 5 - 5
</code></pre></p>
<ul>
    <li>*: the star (or asterisk) operator multiplies numbers</li>
</ul>
<p>
    The following code would give the variable a the value of 25
</p>
<p><pre class="code"><code>
integer a = 5 * 5
</code></pre></p>
<ul>
    <li>/: the divide (forward slash) operator divides numbers</li>
</ul>
<p>
    The following code would give the variable a the value of 1
</p>
<p><pre class="code"><code>
integer a = 5 / 5
</code></pre></p>
<ul>
    <li>mod: the modulus (mod) operator divides numbers</li>
</ul>
<p>
    The following code would give the variable a the value of 2, which is the remainder of 5 divided by 3.
</p>
<p><pre class="code"><code>
integer a = 5 mod 3
</code></pre></p>
<ul>
    <li>&lt;: the less than operator tests to see if the number to the left of the symbol is smaller than the number to the right</li>
</ul>
<p>
    The following code would give the variable a the value of true
</p>
<p><pre class="code"><code>
boolean a = 5 &lt; 6
</code></pre></p>
<ul>
    <li>&lt;=: the less than or equal to operator tests to see if the number to the left of the symbol is smaller or equal to the number to the right</li>
</ul>
<p>
    The following code would give the variable a the value of true
</p>
<p><pre class="code"><code>
boolean a = 5 &lt;= 6
</code></pre></p>
<ul>
    <li>&gt;: the greater than operator tests to see if the number to the left of the symbol is bigger than the number to the right</li>
</ul>
<p>
    The following code would give the variable a the value of false
</p>
<p><pre class="code"><code>
boolean a = 5 &gt; 6
</code></pre></p>
<ul>
    <li>&gt;=: the greater than or equal to operator tests to see if the number to the left of the symbol is bigger or equal to the number to the right</li>
</ul>
<p>
    The following code would give the variable a the value of false
</p>
<p><pre class="code"><code>
boolean a = 5 &gt;= 6
</code></pre></p>
<h2>boolean types</h2>
<p>
    Boolean types are those that have only two values: true and false. Because they only allow two possible values, only equals and not equals can be used:
</p>
<ul>
    <li>=: the equals sign determines whether two values are the same</li>
</ul>
<p>
    The following code would give the variable a the value of true
</p>
<p><pre class="code"><code>
boolean a = true = true
</code></pre></p>
<ul>
    <li>not=: the not= value determines whether or not two values are not equivalent.</li>
</ul>
<p>
    The following code would give the variable a the value of true
</p>
<p><pre class="code"><code>
boolean a = false not= true
</code></pre></p>
<h2>text types</h2>
<p>
    The text type allows you to type a list of characters that the computer can process. Strings have only one operation that is allowed on them, the plus operator, which adds two strings together. This is called concatenation.
</p>
<p>
    The following code would give the variable combined the value of &quot;Hello, World!.&quot;
</p>
<p><pre class="code"><code>
action Main
    text a = &quot;Hello, &quot;
    text b = &quot; world!&quot;
    text combined = a + b
end
</code></pre></p>
<p>
    Text values can also be combined with other types, like integers, numbers, or booleans using the same operator, plus. For example, I could create an integer and add it to a text field:
</p>
<p>
    The following code would give the variable a the value of &quot;My favorite number is 5.&quot;
</p>
<p><pre class="code"><code>
    text a = &quot;My favorite number is &quot; + 5
</code></pre></p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>