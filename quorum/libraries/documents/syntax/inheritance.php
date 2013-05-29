<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Inheritance in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Inheritance in Quorum</h1>
		<p>Quorum uses a form of multiple inheritance for establishing
                relationships between classes.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h1>What is Inheritance?</h1>
<p>
    Inheritance is a way to establish relationships between classes on the system,
    most commonly referred to as an "is a" relationship. In Quorum, this is done
    with the "is" keyword, and allows for a specialized way to reuse existing
    code.
</p>
<h2>Inheriting A Single Class</h2>
<p>
    Inheritance of a class can be defined like so:
</p>
<p><pre class="code"><code>
class Truck is LandVehicle
end
</code></pre></p>
<p>
    In this case, the word <i>is</i> indicates that our Truck inherits from a 
    LandVehicle. Since our Truck is a LandVehicle, Truck now has access to the 
    features and attributes of the LandVehicle.
</p>
<p>
    Inheritance is useful when you want to reuse code that has already been 
    written. For example, if we had a LandVehicle, we might want to allow 
    people to decide the number of wheels that vehicle has. In this case, the 
    class LandVehicle keeps track of the number of wheels a vehicle has 
    (in a variable <code>wheels</code>), and how to manipulate and access the 
    number of wheels (actions setNumberOfWheels and getNumberOfWheels). In our 
    Truck class we will also want to know the number of wheels. Instead of 
    rewriting the code to track the number of wheels, we can inherit these 
    capabilities from the LandVehicle class.
</p>
<p>
    Here is an example of the !LandVehicle class:
</p>
<p><pre class="code"><code>
class LandVehicles
     integer wheels = 0
     action setNumberOfWheels(integer amount)
          wheels = amount
     end
     action getNumberOfWheels returns integer
          return wheels
     end
end
</code></pre></p>
<p>
    Through inheritance we can now set and get the number of wheels a truck has. 
    The following is an example of this:
</p>
<p><pre class="code"><code>
class Main
    action main
          Truck truck
          truck:setNumberOfWheels(4)
          integer a = truck:getNumberOfWheels()
          output &quot;A truck has &quot; + a + &quot; wheels&quot;
    end
end
</code></pre></p>
<p>
    In this example a truck object is created. Then the number of wheels the truck 
    has is set to 4 and the number of wheels a truck has is assigned to a 
    variable (a). Notice the truck can call the methods that were defined in the 
    LandVehicle object. This is possible because a Truck <i>is </i>a LandVehicle. 
    The result is that a string, &quot;A truck has 4 wheels&quot;, is printed to 
    the console.
</p>
<h2>Parent and Me References</h2>
<p>
    Sometimes you will need to reference a class or its parents directly. This 
    can be done by using the <i>me</i> or <i>parent</i> keywords. To reference 
    the class you are currently in you would use the <i>me</i> keyword. The 
    following is an example of this:
</p>
<p><pre class="code"><code>
class Truck is LandVehicle
    integer doors = 0
    action build
        me:doors = 2
    end
end
</code></pre></p>
<p>
    Using the <i>me</i> keyword, the variables and methods of the current class 
    can be accessed. In this example, the variable <code>doors</code> is assigned the 
    value 2.
</p>
<p>
    The <i>parent</i> keyword works in a similar way. Instead of allowing access 
    to the current class, it allows access to the parent classes. In the following 
    example, Truck will be modified to set the number of wheels using the 
    <i>parent</i> keyword.
</p>
<p><pre class="code"><code>
class Truck is LandVehicle
    action build
        parent:LandVehicle:setNumberOfWheels(4)
    end
end
</code></pre></p>
<p>
    In order to build our truck we need to tell it how many wheels it will have. 
    This is done by calling LandVehicle's method setNumberOfWheels and telling 
    the method how many wheels it has. To access a parent from within the Truck 
    object we need to tell the program to look for the method in a <i>parent</i> 
    then tell it which parent it should look in. In this case it looks for the 
    method in the class LandVehicle.
</p>
<h2>Inheriting Multiple Classes</h2>
<p>
    Multiple inheritance allows a class to inherit from multiple classes. In our 
    example Truck is a LandVehicle but Truck is also a GasPoweredVehicle. The 
    following is an example of how Truck inherits from both LandVehicle and 
    GasPoweredVehicle.
</p>
<p><pre class="code"><code>
class Truck is LandVehicle, GasPoweredVehicle
    action build
        parent:LandVehicle:setNumberOfWheels(4)
    end
end
</code></pre></p>
<p>
    The class GasPoweredVehicle is simply added onto the list of classes that 
    Truck inherits from, where each class is separated by a comma.  Now let's 
    set up the GasPoweredVehicle class. We will need to track the number of 
    gallons of gas a GasPoweredVehicle has. Then we will need a way to set and get the number of gallons.
</p>
<p><pre class="code"><code>
class GasPoweredVehicle
    integer gallons = 0
    action setGallons(integer amount)
        gallons = amount
    end
    action getGallons returns integer
        return gallons
    end
end
</code></pre></p>
<p>
    Similar to the way the LandVehicle class keeps track of how many wheels it 
    has, the GasPoweredVehicle keeps track of the number of gallons it has. 
    Looking back at the Truck class we can now build a Truck with 4 wheels and a 
    gas tank that holds 20 gallons.
</p>
<p><pre class="code"><code>
class Truck is LandVehicle, GasPoweredVehicle
    action build
        parent:LandVehicle:setNumberOfWheels(4)
        parent:GasPoweredVehicle:setGallons(20)
    end
end
</code></pre></p>
<p>
    Our Main method can now build our truck and it can find out the number of 
    wheels and the tank size of the truck. Here is an example of this:
</p>
<p><pre class="code"><code>
class Main
    action main
        Truck truck
        //build the truck
        truck:build()
        //how many wheels and gallons does the truck have?
        integer a = truck:getNumberOfWheels()
        output &quot;Number of wheels: &quot; + a
        integer b = truck:getGallons()
        output &quot;Size of gas tank (gallons): &quot; + b
    end
end
</code></pre></p>
<p>
    This program will print out &quot;Number of wheels: 4&quot; and &quot;Size 
    of gas tank (gallons): 20&quot;.
</p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>