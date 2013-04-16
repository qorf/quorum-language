<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Creating prototypes of actions in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Blueprints</h1>
		<p>Blueprints provide us an interface for what a 
                    <a href="classes.php">class</a> does
                without an implementation.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h1>Blueprints</h1>
<p>
When designing classes, we sometimes want to provide blueprints, which are 
descriptions of what kind of functionality a class will provide. The idea 
behind blueprints is to specify the structure of a class without providing an 
implementation. To better describe the idea of blueprints, let us first go 
over what a blueprint action is and then we will discuss how this relates to a 
class and inheritance.
</p>
<h2>Blueprint Actions</h2>
<p>
A blueprint action is specified by the keyword &quot;blueprint&quot; followed 
by the signature (a short description) of an action. The following is an 
example of a blueprint for an Add action:
</p>
<p><pre class="code"><code>
blueprint action Add(number value1, number value2) returns number
</code></pre></p>
<p>
Notice, the action does not have an implementation. This is because we are 
telling the computer that we want to allow the idea of &quot;Adding things&quot; 
into a class, but we do not want to specify exactly how the computer will 
accomplish this. Lets take a specific example by writing a class called Animal. 
We want to make sure that we can pet any kind of animal, whether it is a dog, 
a cat, or anything else.
</p>
<p><pre class="code"><code>
class Animal
   blueprint action Pet()
end
</code></pre></p>
<p>
Now we can create other classes that are certain kinds of the Animal, which in 
computer science is called inheritance. For example, if we had a Dog class that 
we wanted to have a pet action, we would inherit from the Animal class. Any 
class can now inherit from Animal and it will be required to have the blueprint 
actions in Animal.
</p>
<p><pre class="code"><code>
class Dog is Animal
   action Pet()
      say &quot;Pet the dog.&quot;
   end
end
</code></pre></p>
<p>
Once we have inherited from Animal, we will need to implement the inherited 
blueprint actions. In this case we implement the action Pet, which says &quot;pet 
the dog.&quot; when the action pet is called. Blueprints can be helpful when 
you want to make sure similar classes use the same actions with the same names 
to do similar things. Back in our example, we can now implement Pet for cats, 
pigs, and horses. The method will be consistent across all classes who inherit 
from Animal.

</p>
<p><pre class="code"><code>
class Cat is Animal
   action Pet()
      say &quot;Pet the cat.&quot;
   end
end
class Pig is Animal
   action Pet()
      say &quot;Pet the pig.&quot;
   end
end
class Horse is Animal
   action Pet()
      say &quot;Pet the horse.&quot;
   end
end
</code></pre></p>
<p>
Classes that include blueprints can also mix regular implemented actions if 
they want, but such classes may never be instantiated directly. For example, 
suppose we tried to instantiate the above class Animal, like this:
</p>
<p><pre class="code"><code>
//this would be a compiler error
Animal animal
//all of these work fine
Cat cat
Pig pig
Horse horse
</code></pre></p>
<p>
This would be a compiler error because there is no way to determine what the 
Pet blueprint should do when the computer executes our code. As such, only 
classes without blueprints may be instantiated. In other words, we can instantiate 
a Cat, a Pig, or a Horse, in our example, but not Animal.
</p>
<p><pre class="code"><code>
class Animal
   blueprint action Pet()
   action GetNumberOfLegs()
      return 4
   end
end
</code></pre></p>
<p>
Every class that inherits from Animal now has an action GetNumberOfLegs that 
returns 4. Lets look at another example where we have created a Mouse class, 
but we do not implement the Pet method from the parent Look at the following 
invalid code:
</p>
<p><pre class="code"><code>
class Mouse is Animal
//we would need to implement Pet here
end
</code></pre></p>
<p><pre class="code"><code>
class Main
   action main
      Mouse myMouse
   end
end
</code></pre></p>
<p>
We cannot create the Mouse object because Pet has not been implemented. This 
is what that might be like:
</p>
<p><pre class="code"><code>
class Mouse is Animal
   action Pet()
      say &quot;Pet the mouse.&quot;
   end
end
</code></pre></p>
<p><pre class="code"><code>
class Main
   action main
      Mouse myMouse
      myMouse:Pet()
      integer numberLegs = myMouse:GetNumberOfLegs()
   end
end
</code></pre></p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>