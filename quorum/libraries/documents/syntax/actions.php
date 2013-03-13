<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Controlling behaviors in Quorum';
</script>
<h1>Actions in Quorum</h1>
<p>
In the Quorum programming language, actions are things that the language does. 
This is the same concept as &quot;method&quot; in other programming languages. 
For example, you may ask Quorum to add together some numbers. You can create an 
action with the following code:
</p>
<p><pre class="code"><code>
action add
end
</code></pre></p>
<p>
or in the context of a class:
</p>
<p><pre class="code"><code>
class Main
   action Main
   end
   action add
   end
end
</code></pre></p>
<p>
This code tells Quorum that you want to create an action.
</p>
<h2>Returning a value from an action</h2>
<p>
Sometimes, however, you want your action to calculate some values and 
to &quot;return&quot; to you the answer. An action can return a value by 
specifying it in the definition of the action. You do this by specifying the 
type of information being returned by the action and then actually returning 
that value from within the action. For example:
</p>
<p><pre class="code"><code>
action add returns integer
    return 5
end
</code></pre></p>
<p>
To specify the return type we say &quot;returns&quot; followed by the type being 
returned (in this case integer). Then within the action we use the 
keyword &quot;return&quot; followed by the value or variable to be returned by 
the action.
</p>
<h2>Calling an Action</h2>
<p>
The previous action would return a value and could be called or invoked like so:
</p>
<p><pre class="code"><code>
integer a = add()
</code></pre></p>
<p>
Now in the context of the start of your program, the Main action, lets call our 
method add and save the return value in a variable.
</p>
<p><pre class="code"><code>
class Main
   action Main
      integer a = add()
      print a
   end
   action add returns integer
      integer value = 15 + 10
      return value
   end
end
</code></pre></p>
<p>
The value printed to the output console should be 25.
</p>
<h2>Action Parameters</h2>
<p>
You sometimes want to tell an action to use information to do its calculations. 
These bits of information are called parameters and are specified in Quorum 
like so:
</p>
<p><pre class="code"><code>
action add(integer a, integer b)
end
</code></pre></p>
<p>
You can then use these parameters to send information to the action. Finally, 
here is a complete program that calls an action that adds together two numbers:
</p>
<p><pre class="code"><code>
action main
     integer addedNumbers = add(5,10)
end
action add(integer a, integer b) returns integer
     return a + b
end
</code></pre></p>
<h2>Solo Action Calls</h2>
<p>
And finally, occasionally, you want to have an action do some work, but not 
return a value on a line of code. You can do this like so:
</p>
<p><pre class="code"><code>
action Main
     add()
end
action add
end
</code></pre></p>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../static/templates/pageheader.template.php"); ?>