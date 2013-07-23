<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 4_1';
</script>
<h1>Lab 4.1: An Introduction to Actions</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to create actions with no parameters or return types</li>
<li>How to create actions with parameters.</li>
<li>How to create actions with return types.</li>
<li>How to call actions without parameters</li>
<li>How to call actions with parameters</li>
<li>How to utilize returned values from actions</li>
</ul>
<h2>Overview</h2>
<p>
In this lab, you will focus solely on how to create and call actions. You will start out by creating the simplest possible action, one with no parameters or return values. Then you will work your way up to the creation of actions that have both parameters and return values. The goal of this tutorial is to master the creation and usage of actions, and to learn good programming practices for actions.
</p>
<h2>Task 1: Getting started</h2>
<p>
First, start Sodbeans. You will create a new project; however, you will not be creating a blank application this time. Instead, in the New Project dialog, select "Quorum Application." You must select this option when using actions due to the way Quorum understands your programs. (You will discuss this point later in Chapter 5). Name the project <strong>FunWithActions</strong>.
</p>
<p>
When your project opens in Sodbeans, <code>main.quorum</code> will contain the code listed below. You will be typing your code in two different locations--between <code>action Main</code> and the first <code>end</code>, and between <code>class Main</code> and the second <code>end</code>. 
</p>
<p><pre class="code"><code>
class Main
    action Main
    end
end
</code></pre></p>
<h2>Task 2: Creating Basic Actions</h2>
<p>
First, you will focus on the creation of actions that have no parameters or return value. These type of actions can help break up a program into logical sections. For example, let's say you want to greet the user, and then ask them for the balance in their bank account, and then exit. In theory, these operations don't require a lot of code, but over time, as you want to add more to these operations, it may become confusing to read the code.  Let's demonstrate the use of actions for these two operations. First, you will create a method called <code>GreetUser</code> like so:
</p>
<p><pre class="code"><code>
action GreetUser
    output &quot;Hello there!&quot;
end
</code></pre></p>
<p>
When you want to create an action, you must first use the <code>action</code> keyword. This keyword lets Quorum know that you preparing to declare a new action. Following the <code>action</code> keyword is the name of your action. Actions obey the same naming rules as variables--any valid variable name is a valid action name. After you state the name of the action, you place the code you want inside the action between the <code>action GreetUser</code> line and the <code>end</code> line. This is similar to the way the control structures you studied in Chapter 3 work.
</p>
<p>
Inside the <code>Main</code> action, let's add code to call this action. When you "call" an action, Quorum executes all of the code in that action (the code between the <code>action GreetUser</code> and <code>end</code> lines, in this case), and then comes back to the place in the code where you called from. To demonstrate how this works, enter the code below into the <code>Main</code> action.
</p>
<p><pre class="code"><code>
GreetUser()
output &quot;Back from GreetUser().&quot;
</code></pre></p>
<p>
Your code in the Sodbeans editor should now look like the following:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser()
        output &quot;Back from GreetUser().&quot;
    end
    action GreetUser
        output &quot;Hello there!&quot;
    end
end
</code></pre></p>
<p>
Run the program. In the Sodbeans Output Window, you will now see the following:
</p>
<p><pre class="code"><code>
Hello there!
Back from GreetUser().
</code></pre></p>
<p>
What happened here is that Quorum recognized you were calling an action by the syntax of the <code>GreetUser()</code> line. When you specify a name followed by an opened and closed parentheses, Quorum assumes you wish to execute the code in that action. In this example, Quorum executed the code inside the <code>GreetUser</code> action, and then came back to execute the next line, <code>output "Back from GreetUser()."</code>. Run the debugger now and step through the code to get an idea of how this program executes. Try stepping into the <code>GreetUser()</code> line of code.
</p>
<p>
Let's create one more action, called <code>GetUserBalance</code>, that asks the user to enter a dollar amount for a bank account. This action will then output the value the user entered to the screen. The code for this action is shown below.
</p>
<p><pre class="code"><code>
action GetUserBalance
    text in = input(&quot;How much money is in your savings account?&quot;)
    output &quot;You have $&quot; + in + &quot; in your savings account.&quot;
end
</code></pre></p>
<p>
You will also need to add code to your <code>Main</code> method to call this action. Your <code>main.quorum</code> file now looks like so:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser()
        output &quot;Back from GreetUser().&quot;
        GetUserBalance()
        output &quot;Leaving...&quot;
    end
    action GreetUser
        output &quot;Hello there.&quot;
    end
    action GetUserBalance
        text in = input(&quot;How much money is in your savings account?&quot;)
        output &quot;You have $&quot; + in + &quot; in your savings account.&quot;
    end
end
</code></pre></p>
<p>
Notice that you called <code>GetUserBalance</code> in the exact same way you called <code>GreetUser</code> previously.
</p>
<p>
Run the program. Your program will prompt You to enter a dollar amount. After you do, you will see something similar to the output below in the Sodbeans Output Window.
</p>
<p><pre class="code"><code>
Hello there.
Back from GreetUser().
You have $1.00 in your bank account.
Leaving...
</code></pre></p>
<p>
You will now tackle an extremely powerful feature of actions, called parameters.
</p>
<h2>Task 3: Using Parameters</h2>
<p>
An extremely useful feature of actions is that they allow You to pass in what are called <i>parameters</i>. Parameters are variables, much like the variables you discussed in chapter 2, that are available inside the scope of the function, but who's values are not explicitly stated in the action. So far, all of your actions have simply executed the code inside of them and returned. Parameters allow You to make this behavior much more interesting by making the behavior change based on the <i>input</i> to the action.
</p>
<p>
That sounds a bit complicated, so let's create an example to demonstrate. Earlier, you created a <code>GreetUser</code> action, that printed the message "Hello there!" to the screen. You could make this action slightly more friendly by passing it a parameter telling You the name of the user. You define such an action below. 
</p>
<p><pre class="code"><code>
action GreetUser(text name)
    output &quot;Hello there, &quot; + name + &quot;!&quot;
end
</code></pre></p>
<p>
Notice that the first line of this action is different from the previous <code>GreetUser</code> action. Instead of simply ending the line after the <code>GreetUser</code> name, you use an open parenthesis to tell Quorum you are going to specify parameters. In Quorum, parameters are <strong>always</strong> specified between a set of parenthesis. Once you have specified your parameter, you close the opened parenthesis. You have added one parameter to this action, called <code>name</code>, of type <code>text</code>. By doing so, you make the variable <code>name</code> available for use in your action. Multiple parameters can also be specified, separated by a comma (covered in section 5). You use it in the output statement on the next line. Notice that You did not initialize the variable <code>name</code> before using it in <code>output</code>. Rather, Quorum took care of this for you , and you can assume it already has some value.
</p>
<p>
Place this code into the Sodbeans editor and remove the old <code>GreetUser</code> action. Run the program. Notice that youreceive the following compiler error:
</p>
<p><pre class="code"><code>
The method 'GreetUser' in class '.Main' has not been defined.
</code></pre></p>
<p>
You receive this error because your previous line for calling <code>GreetUser</code> does not specify a value for the <code>name</code> parameter. To correct this, you can change the line to look as follows:
</p>
<p><pre class="code"><code>
GreetUser(&quot;Jeff&quot;)
</code></pre></p>
<p>
Like before, you are still using an open parenthesis after the action name to indicate that the action will be called. However, you are no longer leaving it empty--instead, you are specifying a <code>text</code> value. Feel free to replace <code>"Jeff"</code> with your own name. Your full program will now look like this:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser(&quot;Jeff&quot;)
        output &quot;Back from GreetUser().&quot;
        GetUserBalance()
        output &quot;Leaving...&quot;
    end
    action GreetUser(text name)
        output &quot;Hello there, &quot; + name + &quot;!&quot;
    end
    action GetUserBalance
        text in = input(&quot;How much money is in your savings account?&quot;)
        output &quot;You have $&quot; + in + &quot; in your savings account.&quot;
    end
end
</code></pre></p>
<p>
When yourun this program, the first line of output now looks like this:
</p>
<p><pre class="code"><code>
Hello there, Jeff!
</code></pre></p>
<p>
When Quorum ran your <code>GreetUser</code> action, it printed the value you put into quotations above. Experiment by changing the line <code>GreetUser("Jeff")</code> to have other names in quotations. You are not limited to constant values, either. You could also pass in a variable, like in the code below. The only restriction is that the value you pass in must be of type <code>text</code>, as that is the type you specified when you created the action above.
</p>
<p><pre class="code"><code>
text myName = &quot;Jeff&quot;
GreetUser(myName)
</code></pre></p>
<p>
Parameters are an extremely powerful feature of actions that have quite a bit of application in computer science. In the next section, you will cover the final important property of actions:  return values.
</p>
<h2>Task 4: Return Values</h2>
<p>
So far, you have covered how to create actions with and without parameters, and how to call these actions. An important feature of actions that you have so far not discussed is the concept of <i>return values</i>. Return values can be thought of as the "result" of an action. For example, if you created an action that added two numbers, say 3 and 4, the result of this operation is 7. Like parameters, return values are best demonstrated by example.
</p>
<p>
One way you might utilize return values in your existing program is to change the <code>GetUserBalance</code> action. Currently, this action asks the user for their balance, and then prints it to the screen. If you wanted to use this value later in the program, say, outside of the <code>GetUserBalance</code> action, you couldn't; it is simply lost when the <code>GetUserBalance</code> action ends. Let's change this action to return an <code>integer</code> containing the dollar amount the user enters. To do this, first remove the old <code>GetUserBalance</code> action, and then add the following code:
</p>
<p><pre class="code"><code>
action GetUserBalance returns integer
    text in = input(&quot;How much money is in your savings account?&quot;)
    integer amount = cast(integer, in)
    return amount
end
</code></pre></p>
<p>
This code introduces You to two new keywords. On the first line of this code, you see the keyword <code>returns</code>. This keyword tells Quorum what type your action will return. The second keyword, <code>return</code>, actually specifies <i>what</i> you are returning. In this case, your action returns a type of <code>integer</code>, and returns the variable <code>amount</code>, which contains the dollar amount the user entered.
</p>
<p>
Before you continue, run the program. Your program no longer prints the value that the user entered. However, unlike before, you can now retrieve the value that the user entered and use it outside of the <code>GetUserBalance</code> action. You can utilize its return value by calling the action in a slightly different way. When an action returns a value, the action can be used in any expression that requires that type. Let's define an integer variable <code>amount</code>, and assign it the return value of <code>GetUserBalance</code>:
</p>
<p><pre class="code"><code>
integer amount = GetUserBalance()
output &quot;You entered $&quot; + amount
</code></pre></p>
<p>
Notice that, like before, you are calling <code>GetUserBalance</code> using the opened/closed parentheses syntax. However, to the left of this code, you are performing an assignment to the variable <code>amount</code>.
</p>
<p>
your full program now looks like this:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser(&quot;Jeff&quot;)
        output &quot;Back from GreetUser().&quot;
        integer amount = GetUserBalance()
        output &quot;You entered $&quot; + amount
        output &quot;Leaving...&quot;
    end
    action GreetUser(text name)
        output &quot;Hello there, &quot; + name + &quot;!&quot;
    end
    action GetUserBalance returns integer
        text in = input(&quot;How much money is in your savings account?&quot;)
        integer amount = cast(integer, in)
        return amount
    end
end
</code></pre></p>
<p>
When yourun this program, your output now looks like so:
</p>
<p><pre class="code"><code>
Hello there, Jeff!
Back from GreetUser().
You entered $33
Leaving...
</code></pre></p>
<h2>Task 5: Putting It All Together</h2>
<p>
Now that you have covered the basics of actions, parameters and return values, let's try putting all of this together and create an action with parameters <i>and</i> a return value. Let's create a couple of actions, <code>multiply</code> and <code>divide</code>. You will walk through the creation of the <code>multiply</code> action step-by-step, and leave the <code>divide</code> action up to you , with a word of caution.
</p>
<p>
To start out, let's erase all of the code in the Sodbeans editor so that it again looks like it did when you started:
</p>
<p><pre class="code"><code>
class Main
    action Main
    end
end
</code></pre></p>
<p>
When designing actions, it is important to consider the input of the action and any potential results. In the case of the <code>multiply</code> action, you need to pass it two numbers, <code>a</code> and <code>b</code>, and your result also needs to be a number. Given this, you wind up with the action below:
</p>
<p><pre class="code"><code>
action Multiply(number a,  number b) returns number
    number result = a * b
    return result
end
</code></pre></p>
<p>
Notice that this action is different than others you have studied so far, as it accepts multiple parameters. When specifying multiple parameters, you separate each parameter with a comma. Let's also add code to call this action from <code>Main</code>, as in below:
</p>
<p><pre class="code"><code>
number k = Multiply(2, 4)
output k
</code></pre></p>
<p>
Now, your code in the editor looks like the following:
</p>
<p><pre class="code"><code>
class Main
    action Main
        number k = Multiply(2, 4)
        output k
    end
    action Multiply(number a, number b) returns number
        number result = a * b
        return result
    end
end
</code></pre></p>
<p>
When yourun this code, the value in the variable <code>k</code> will be 8.
</p>
<p>
For the final portion of this lab, write a method, called <code>Divide</code>, that takes two parameters of type <code>number</code>, <code>a</code> and <code>b</code>, and returns the type <code>number</code>. Note that division by zero is not allowed. Write a method that performs division, but returns the number 0.0 when division by zero will occur. Use your knowledge from Chapters 2 and 3. Hint: Make use of the <code>if</code> construct.
</p>
<p>
To check your work, here are a few examples of input and the values that will be returned.
</p>
<p><pre class="code"><code>
number k = Divide(2, 4)
</code></pre></p>
<p>
The value of <code>k</code> should be 0.5.
</p>
<p><pre class="code"><code>
number k = Divide(9, 3)
</code></pre></p>
<p>
The value of <code>k</code> should be 3.0.
</p>
<p><pre class="code"><code>
number k = Divide(6, 0)
</code></pre></p>
<p>
The value of <code>k</code> should be 0.0. (division by zero has occurred).
</p>
 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>