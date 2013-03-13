<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Automatic conversion of primitives in Quorum';
</script>
<h1>Automatic conversion of primitives (auto-boxing)</h1>
<p>
    In Quorum, when using a generic class with a primitive, any primitive value 
    is converted to an object to be used in the class.  Each primitive has a 
    corresponding class: Boolean for boolean, Integer for integer, Number for 
    number, and Text for text.
</p>
<p>
    These objects can be used like any other class and include a single data 
    member (the type of this data member is the primitive corresponding to the 
    class) and several functions.  (Note: Inside the generic class, these 
    functions have no meaning.)
</p>
<h2>Mutator Function</h2>
<p>
    To change the value of the data member of an object, use the SetValue 
    function.  For instance, if you had an Integer object <code>int</code>, to 
    change the value associated with this object to 15, you would use the following code:
</p>
<p><pre class="code"><code>
Integer int
int:SetValue(15)
</code></pre></p>
<h2>Accessor Functions</h2>
<p>
    To retrieve the value of the data member of one of these classes, use the 
    GetValue function.  For instance, if you wanted to assign the value of the 
    Integer object in the code above to an integer primitive <code>i</code>, 
    you would use the following code:
</p>
<p><pre class="code"><code>
integer i
i = int:GetValue()
</code></pre></p>
<p>
    Objects of these classes cannot be typecast, but you can retrieve the value 
    as a different type than the type that corresponds to the class.  The 
    GetBooleanValue, GetIntegerValue, GetNumberValue, and GetTextValue functions 
    allow you to retrieve the value as a different type.  For instance, if you 
    wanted to get the value of the Integer object used in the above code as 
    text and assign that to a variable of type text, you would use the following code:
</p>
<p><pre class="code"><code>
text t
t = int:GetTextValue()
</code></pre></p>
<p>
    After executing this code, <code>t</code> will have the value &quot;15&quot;.
</p>
<h2>Comparison Function</h2>
<p>
    Objects of these classes cannot be compared using comparison operators.  
    That is, if you had two Integer objects, <code>int1</code> and <code>int2</code>, with 
    <code>int1</code> assigned to the value 5 and int2 assigned to the value 7, you c
    ould not use the &quot;greater than or equal to&quot; operator (&gt;=) to 
    find out that <code>int2 &gt;= int1</code> is true.  However, you can use the CompareTo 
    function to assess the equality or inequality of the values associated with 
    two objects.  This function will return 1 if the value of an object is greater 
    than the value of another object, -1 if the value of an object is greater 
    than the value of another object, and 0 if the values are equal.  For 
    instance, consider the following code:
</p>
<p><pre class="code"><code>
Integer int1
Integer int2
int1:SetValue(5)
int2:SetValue(7)
integer lessThan = int1:CompareTo(int2)
integer greaterThan = int2:CompareTo(int1)
boolean equalTo = (int1:CompareTo(int2) = 0)
</code></pre></p>
<p>
    After executing the above code:
</p>
<ul>
    <li><code>lessThan</code> will equal -1, because 5 is less than 7,</li>
    <li><code>greaterThan</code> will equal 1 because 7 is greater than 5, and</li>
    <li><code>equalTo</code> will equal <code>false</code> because 5 is not equal to 7, and thus the result of the function is not 0.</li>
</ul>
<p>
    Therefore:
</p>
<ul>
    <li>To determine whether a value is greater than another, you can compare the result of the CompareTo function to 1.</li>
    <li>To determine whether a value is less than another, you can compare the result of the CompareTo function to -1.</li>
    <li>To determine whether a value is equal to another, you can compare the result of the CompareTo function to 0.</li>
</ul>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../static/templates/pageheader.template.php"); ?>