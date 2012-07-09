<?php include("../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Creating Arrays in Quorum';
</script>
= Arrays =

In a computer, an Array is a block of memory that stores several versions of an item. In Quorum, you can create an array by first telling the Quorum language that you want access to this feature, using the "use" statement, like so:

{{{
use Libraries.Containers.Array
}}}

Next, in order to create an array, you first say the word Array, followed by a [wiki:documentation/DataTypes data type] (i.e., [wiki:documentation/Integer integer]) in angle brackets and name of your choosing, which in the following example is a:

{{{
Array<integer> a
}}}

The array a, by default, now contains ten spots that are available for putting in values, and these values are of type integer. Any value is accepted, including integers, numbers, or custom data types of your making. For example, here is how you would add two integer values, 10, and 15, into the array:

{{{
a:Set(0, 10) //put a value in the first slot in the array, which is named 0
a:Set(1, 15) //put a value in the second slot in the array, which is named 1
}}}

We can also build up arrays automatically using the repeat statement. For example, here is a program that fills an array with values from one to ten:

{{{
use Libraries.Containers.Array
class Main
   action Main
      integer i = 0
      Array<integer> a
      repeat 10 times
         a:Set(i, i)
         i = i + 1
      end
   end
end
}}}


Similarly, if there is a value in the array, you can retrieve it using the 
following syntax:

{{{
Array<integer> a
a:Set(0, 10)
a:Set(1, 15)
integer b = a:Get(0) + a:Get(1)
}}}

In the above example, the integer b would be assigned the value of 25. And 
finally, you are not limited to integer objects. Arrays can be asked to store 
anything, including integer, text, boolean, or number values. They can even 
store custom types of your own design. For example, suppose you had a 
Dog [wiki:documentation/Class class]:

{{{
class Dog
   integer NumLegs = 4
   action Bark() 
   end
end
}}}


If you then wanted to create 12 Dog objects and place them in the array, you would do so like this:

{{{
use Libraries.Containers.Array
class Main
   action Main
      Array<Dog> a
      a:SetSize(12)
      repeat 12 times
         Dog dog
         a:Set(i, dog)
         i = i + 1
      end
   end
end
}}}

To change the attributes of a Dog in the array, you would first insert the 
Dog object into a new class object and then use the appropriate functions to 
change the Dog object.  First, let's give the Dog object an attribute called {{{name}}} and an action that lets you change the name called setName:

{{{
class Dog
    integer NumLegs = 4
    text name
    
    action SetName(text n)
        name = n
    end
    action Bark()
end
}}}

Now, in the Main class, create a new Dog object and set it equal to the Dog in 
the array that you would like to edit:

{{{
Dog thisDog = a:Get(0)
}}}

Next, call the setName action.  If we, for instance, want to name the dog "Spot," 
we will call the setName action like so:

{{{
thisDog:SetName("Spot")
}}}

Now, the Dog at position 0 in the array {{{a}}} has the name "Spot."

For further information on arrays, check out the standard library documentation 
for [wiki:documentation/QuorumStandardLibrary/Libraries/Containers/Array array].

<?php include("../include/footer.php"); ?>