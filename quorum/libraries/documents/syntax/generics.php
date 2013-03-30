<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Using generics in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Using Generics</h1>
		<p>Generics allow us to specify what type is in a container.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h1>What are Generics?</h1>
<p>
In computer programming, we often want to create a class that generically 
specifies an algorithm. For example, we may want to create a list of objects, 
like a list of people in a phone book. Or, we may want to create a list of 
numbers, integers, or anything else. There are two ways we could accomplish 
this: 1) we could create separate implementations of our list for each data 
type (e.g., one for numbers, one for integers), or 2) we could create 
a &quot;generic&quot; class.
</p>
<p>
Let us take the first case, a list of numbers or integers. First, here is a 
list of numbers:
</p>
<p><pre class="code"><code>
class List
   action add(integer item)
      //add our list item
   end
end
</code></pre></p>
<p>
But our implementation would be identical for numbers, except that the type 
changes, like so:
</p>
<p><pre class="code"><code>
class List
   action add(number item)
      //add our list item
   end
end
</code></pre></p>
<p>
It would be nice if we could derive a way to generically create our list. 
Then, our list could compute whatever it needs, without worrying about what 
kind of object is placed into it. To do this, we must first declare our new 
generic list class as so:
</p>
<p><pre class="code"><code>
class List&lt;Type&gt;
   action add(Type item)
      //add our list item
   end
   action removeFirst returns Type
      //do remove operation
      //we need to say &quot;return val&quot; where val is of our generic Type
   end
end
</code></pre></p>
<p>
Notice that our List class has less than and greater than signs surrounding the 
word Type. This indicates to the Quorum language that we have specified a 
generic. However, to now create an instance of our generic object, we need 
to tell Quorum what kind of generic we actually want to use. We do this like so:
</p>
<p><pre class="code"><code>
List&lt;integer&gt; list
list:add(1)
list:add(2)
list:add(3)
integer a = list:removeFirst()
integer b = list:removeFirst()
integer c = list:removeFirst()
</code></pre></p>
<p>
The above implementation would add, and then remove, three integers from our 
list. Similarly, we can use this list for any other type:
</p>
<p><pre class="code"><code>
//all of the following are fine
List&lt;string&gt; list1
List&lt;Dog&gt; list2 //assuming there is a Dog class somewhere
List&lt;boolean&gt; list3
</code></pre></p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>