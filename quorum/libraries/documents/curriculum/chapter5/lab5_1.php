<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 5_1';
</script>

<h1>Lab 5.1: An Introduction to Classes and Objects</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to understand the following concepts:
</p>
<ul>
<li>Writing methods for an existing class</li>
<li>Understanding class &amp; method syntax</li>
</ul>
<h2>Overview</h2>
<p>
In this lab we will learn about classes and how they can be used. We will start by looking at the code for a basic Animal class, then we will code a few simple methods for the class and we will observe how we're able to change the class's attributes. The goal of this lab is to give us the foundation in classes needed for moving on to the next labs, and subsequent programming assignments.
</p>
<h2>Task 1: Getting started</h2>
<p>
First, start Sodbeans. We will create a new project; however, we will not be creating a blank application this time. Instead, in the New Project dialog, select &quot;Quorum Application.&quot; We must select this option when using actions due to the way Quorum understands our programs. (We will discuss this point later in Chapter 5). Name the project &quot;FunWithAnimals&quot;.
</p>
<p>
When our project opens in Sodbeans, main.quorum will contain the code listed below. We will be typing our code in two different locations--between action Main and the first end, and between class Main and the second end.
</p>
<p><pre class="code"><code>
class Main
    action Main
    end
end
</code></pre></p>
<h2>Task 2: Creating Basic Actions</h2>
<p>
First, we will code the foundation of our code.
</p>
<p><pre class="code"><code>
class Animal
    action Main
    end
end
</code></pre></p>
<p>
This sets our class name to Animal. Now we will code a few characteristics of animals. We will start with common things like number of arms, and the animal's color.
</p>
<p><pre class="code"><code>
class Animal
    integer arms = 2
    text color = &quot;brown&quot;
    action Main
    end
end
</code></pre></p>
<p>
So now our animal has two arms, and it's brown. From this point we want to print the animal's existing characteristics, so we are going to put a print line in the Main that has some text in it, as well as the variables we just coded. I coded the print statement to show the number of arms the animal has, but it's your job to code the print statement for it's color.
</p>
<p><pre class="code"><code>
class Animal
    integer arms = 2
    text color = &quot;brown&quot;
    action Main
        print &quot;The animal has &quot; + arms + &quot; arms.&quot;
    end
end
</code></pre></p>
<p>
So now we're telling the user a few things about our animal.
</p>
<p>
But what if the user would like to change the animal?
</p>
<p>
Next we're going to code a few actions for our class that will change the variables. These are called methods when they're actions for a class, and they're an integral part of class development.
</p>
<p>
Reusability is a fundamental part of programming, so we're going to move the print statements into their own method, called Display. The skeleton code is provided for you. Don't forget to call the Display method once you're finished.
</p>
<p><pre class="code"><code>
class Animal
    integer arms = 2
    text color = &quot;brown&quot;
    action Main
        print &quot;The animal has &quot; + arms + &quot; arms and &quot; + legs + &quot; legs.&quot;
    end
    action Display
    end
end
</code></pre></p>
<p>
So now our program has the same functionality as before, but it's been decomposed into a method. One great benefit of this is that we're able to reuse that method now, without having to type it all out again. Reusability is a big time-saver and really simplifies your program.
</p>
<p>
Next thing that we're going to do is create three more methods that change the characteristics of the animal. This way the user can create their own animal with any number of legs, arms, and color.
</p>
<p>
So we're going to name the methods &quot;Change-NameOfAttribute-&quot;, ChangeArms for example. Inside these methods we're going to ask the user to enter a number or text. Then we're going to reassign the already created variables to what the user entered. Before we can do that though we have to cast the user's entry from a text to an integer.
</p>
<p>
We also need to be sure that we call those methods from within the Main.
</p>
<p>
One method is provided for you. It's your job to write the other methods that allow the user to change the animal's color.
</p>
<p>
Once you're finished with writing those methods you should call the Display method again so the user can see that the changes that he made have been saved.
</p>
<p><pre class="code"><code>
class Animal
    integer arms = 2
    text color = &quot;brown&quot;
    action Main
    end
    action Display
    end
    action ChangeArms
        print &quot;How many arms would you like your animal to have?&quot;
        text result = input(&quot;Number of Arms&quot;)
        integer v = cast(integer, result)
        arms = v
    end
    action ChangeColor
    end
end
</code></pre></p>
<p>
From this foundation you should be able to code a few more characteristics, and a few more methods. Most animals have legs, so let's add a legs variable similar to our arm variable. Let's also add a variable so we can give our animal a name like &quot;Fido.&quot; Don't forget to add methods that allow the user to change those variables as well.
</p>
<p>
So by the time you're finished you should have 4 variables: arms, legs, name, and color. And these four methods: &quot;Display&quot;, &quot;ChangeArms&quot;, &quot;ChangeLegs&quot;, &quot;ChangeColor&quot;, and &quot;ChangeName&quot;.
</p>

<?php include("../../include/footer.php"); ?>