<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 4_1';
</script>
<h1>Lab 4.1: An Introduction to Actions</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to understand the following concepts:
</p>
<ul>
<li>Creating actions with no parameters or return types</li>
<li>Creating actions with parameters.</li>
<li>Creating actions with return types.</li>
<li>Calling actions without parameters</li>
<li>Calling actions with parameters</li>
<li>Utilizing returned values from actions</li>
</ul>
<h2>Overview</h2>
<p>
In this lab, we will focus solely on how to create and call actions. We will start out by creating the simplest possible action, one with no parameters or return values. Then we will work our way up to the creation of actions that have both parameters and return values. The goal of this tutorial is to master the creation and usage of actions, and to learn good programming practices for actions.
</p>
<h2>Task 1: Getting started</h2>
<p>
First, start Sodbeans. We will create a new project; however, we will not be creating a blank application this time. Instead, in the New Project dialog, select "Quorum Application." We must select this option when using actions due to the way Quorum understands our programs. (We will discuss this point later in Chapter 5). Name the project <strong>FunWithActions</strong>.
</p>
<p>
When our project opens in Sodbeans, <tt>main.quorum</tt> will contain the code listed below. We will be typing our code in two different locations--between <tt>action Main</tt> and the first <tt>end</tt>, and between <tt>class Main</tt> and the second <tt>end</tt>. 
</p>
<p><pre class="code"><code>
class Main
    action Main
    end
end
</code></pre></p>
<h2>Task 2: Creating Basic Actions</h2>
<p>
First, we will focus on the creation of actions that have no parameters or return value. These type of actions can help break up a program into logical sections. For example, let's say we want to greet the user, and then ask them for the balance in their bank account, and then exit. In theory, these operations don't require a lot of code, but over time, as we want to add more to these operations, it may become confusing to read the code.  Let's demonstrate the use of actions for these two operations. First, we will create a method called <tt>GreetUser</tt> like so:
</p>
<p><pre class="code"><code>
action GreetUser
    print &quot;Hello there!&quot;
end
</code></pre></p>
<p>
When we want to create an action, we must first use the <tt>action</tt> keyword. This keyword lets Quorum know that we preparing to declare a new action. Following the <tt>action</tt> keyword is the name of our action. Actions obey the same naming rules as variables--any valid variable name is a valid action name. After we state the name of the action, we place the code we want inside the action between the <tt>action GreetUser</tt> line and the <tt>end</tt> line. This is similar to the way the control structures we studied in Chapter 3 work.
</p>
<p>
Inside the <tt>Main</tt> action, let's add code to call this action. When we "call" an action, Quorum executes all of the code in that action (the code between the <tt>action GreetUser</tt> and <tt>end</tt> lines, in this case), and then comes back to the place in the code where we called from. To demonstrate how this works, enter the code below into the <tt>Main</tt> action.
</p>
<p><pre class="code"><code>
GreetUser()
print &quot;Back from GreetUser().&quot;
</code></pre></p>
<p>
Our code in the Sodbeans editor should now look like the following:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser()
        print &quot;Back from GreetUser().&quot;
    end
    action GreetUser
        print &quot;Hello there!&quot;
    end
end
</code></pre></p>
<p>
Run the program. In the Sodbeans Output Window, we will now see the following:
</p>
<p><pre class="code"><code>
Hello there!
Back from GreetUser().
</code></pre></p>
<p>
What happened here is that Quorum recognized we were calling an action by the syntax of the <tt>GreetUser()</tt> line. When we specify a name followed by an opened and closed parentheses, Quorum assumes we wish to execute the code in that action. In this example, Quorum executed the code inside the <tt>GreetUser</tt> action, and then came back to execute the next line, <tt>print "Back from GreetUser()."</tt>. Run the debugger now and step through the code to get an idea of how this program executes. Try stepping into the <tt>GreetUser()</tt> line of code.
</p>
<p>
Let's create one more action, called <tt>GetUserBalance</tt>, that asks the user to enter a dollar amount for a bank account. This action will then print the value the user entered to the screen. The code for this action is shown below.
</p>
<p><pre class="code"><code>
action GetUserBalance
    text in = input(&quot;How much money is in your savings account?&quot;)
    print &quot;You have $&quot; + in + &quot; in your savings account.&quot;
end
</code></pre></p>
<p>
We will also need to add code to our <tt>Main</tt> method to call this action. Our <tt>main.quorum</tt> file now looks like so:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser()
        print &quot;Back from GreetUser().&quot;
        GetUserBalance()
        print &quot;Leaving...&quot;
    end
    action GreetUser
        print &quot;Hello there.&quot;
    end
    action GetUserBalance
        text in = input(&quot;How much money is in your savings account?&quot;)
        print &quot;You have $&quot; + in + &quot; in your savings account.&quot;
    end
end
</code></pre></p>
<p>
Notice that we called <tt>GetUserBalance</tt> in the exact same way we called <tt>GreetUser</tt> previously.
</p>
<p>
Run the program. Our program will prompt us to enter a dollar amount. After we do, we will see something similar to the output below in the Sodbeans Output Window.
</p>
<p><pre class="code"><code>
Hello there.
Back from GreetUser().
You have $1.00 in your bank account.
Leaving...
</code></pre></p>
<p>
We will now tackle an extremely powerful feature of actions, called parameters.
</p>
<h2>Task 3: Using Parameters</h2>
<p>
An extremely useful feature of actions is that they allow us to pass in what are called <i>parameters</i>. Parameters are variables, much like the variables we discussed in chapter 2, that are available inside the scope of the function, but who's values are not explicitly stated in the action. So far, all of our actions have simply executed the code inside of them and returned. Parameters allow us to make this behavior much more interesting by making the behavior change based on the <i>input</i> to the action.
</p>
<p>
That sounds a bit complicated, so let's create an example to demonstrate. Earlier, we created a <tt>GreetUser</tt> action, that printed the message "Hello there!" to the screen. We could make this action slightly more friendly by passing it a parameter telling us the name of the user. We define such an action below. 
</p>
<p><pre class="code"><code>
action GreetUser(text name)
    print &quot;Hello there, &quot; + name + &quot;!&quot;
end
</code></pre></p>
<p>
Notice that the first line of this action is different from the previous <tt>GreetUser</tt> action. Instead of simply ending the line after the <tt>GreetUser</tt> name, we use an open parenthesis to tell Quorum we are going to specify parameters. In Quorum, parameters are <strong>always</strong> specified between a set of parenthesis. Once we have specified our parameter, we close the opened parenthesis. We have added one parameter to this action, called <tt>name</tt>, of type <tt>text</tt>. By doing so, we make the variable <tt>name</tt> available for use in our action. Multiple parameters can also be specified, separated by a comma (covered in section 5). We use it in the print statement on the next line. Notice that we did not initialize the variable <tt>name</tt> before using it in <tt>print</tt>. Rather, Quorum took care of this for us, and we can assume it already has some value.
</p>
<p>
Place this code into the Sodbeans editor and remove the old <tt>GreetUser</tt> action. Run the program. Notice that we receive the following compiler error:
</p>
<p><pre class="code"><code>
The method 'GreetUser' in class '.Main' has not been defined.
</code></pre></p>
<p>
We receive this error because our previous line for calling <tt>GreetUser</tt> does not specify a value for the <tt>name</tt> parameter. To correct this, we can change the line to look as follows:
</p>
<p><pre class="code"><code>
GreetUser(&quot;Jeff&quot;)
</code></pre></p>
<p>
Like before, we are still using an open parenthesis after the action name to indicate that the action will be called. However, we are no longer leaving it empty--instead, we are specifying a <tt>text</tt> value. Feel free to replace <tt>"Jeff"</tt> with your own name. Our full program will now look like this:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser(&quot;Jeff&quot;)
        print &quot;Back from GreetUser().&quot;
        GetUserBalance()
        print &quot;Leaving...&quot;
    end
    action GreetUser(text name)
        print &quot;Hello there, &quot; + name + &quot;!&quot;
    end
    action GetUserBalance
        text in = input(&quot;How much money is in your savings account?&quot;)
        print &quot;You have $&quot; + in + &quot; in your savings account.&quot;
    end
end
</code></pre></p>
<p>
When we run this program, the first line of output now looks like this:
</p>
<p><pre class="code"><code>
Hello there, Jeff!
</code></pre></p>
<p>
When Quorum ran our <tt>GreetUser</tt> action, it printed the value we put into quotations above. Experiment by changing the line <tt>GreetUser("Jeff")</tt> to have other names in quotations. We are not limited to constant values, either. We could also pass in a variable, like in the code below. The only restriction is that the value we pass in must be of type <tt>text</tt>, as that is the type we specified when we created the action above.
</p>
<p><pre class="code"><code>
text myName = &quot;Jeff&quot;
GreetUser(myName)
</code></pre></p>
<p>
Parameters are an extremely powerful feature of actions that have quite a bit of application in computer science. In the next section, we will cover the final important property of actions:  return values.
</p>
<h2>Task 4: Return Values</h2>
<p>
So far, we have covered how to create actions with and without parameters, and how to call these actions. An important feature of actions that we have so far not discussed is the concept of <i>return values</i>. Return values can be thought of as the "result" of an action. For example, if we created an action that added two numbers, say 3 and 4, the result of this operation is 7. Like parameters, return values are best demonstrated by example.
</p>
<p>
One way we might utilize return values in our existing program is to change the <tt>GetUserBalance</tt> action. Currently, this action asks the user for their balance, and then prints it to the screen. If we wanted to use this value later in the program, say, outside of the <tt>GetUserBalance</tt> action, we couldn't; it is simply lost when the <tt>GetUserBalance</tt> action ends. Let's change this action to return an <tt>integer</tt> containing the dollar amount the user enters. To do this, first remove the old <tt>GetUserBalance</tt> action, and then add the following code:
</p>
<p><pre class="code"><code>
action GetUserBalance returns integer
    text in = input(&quot;How much money is in your savings account?&quot;)
    integer amount = cast(integer, in)
    return amount
end
</code></pre></p>
<p>
This code introduces us to two new keywords. On the first line of this code, we see the keyword <tt>returns</tt>. This keyword tells Quorum what type our action will return. The second keyword, <tt>return</tt>, actually specifies <i>what</i> we are returning. In this case, our action returns a type of <tt>integer</tt>, and returns the variable <tt>amount</tt>, which contains the dollar amount the user entered.
</p>
<p>
Before we continue, run the program. Our program no longer prints the value that the user entered. However, unlike before, we can now retrieve the value that the user entered and use it outside of the <tt>GetUserBalance</tt> action. We can utilize its return value by calling the action in a slightly different way. When an action returns a value, the action can be used in any expression that requires that type. Let's define an integer variable <tt>amount</tt>, and assign it the return value of <tt>GetUserBalance</tt>:
</p>
<p><pre class="code"><code>
integer amount = GetUserBalance()
print &quot;You entered $&quot; + amount
</code></pre></p>
<p>
Notice that, like before, we are calling <tt>GetUserBalance</tt> using the opened/closed parentheses syntax. However, to the left of this code, we are performing an assignment to the variable <tt>amount</tt>.
</p>
<p>
Our full program now looks like this:
</p>
<p><pre class="code"><code>
class Main
    action Main
        GreetUser(&quot;Jeff&quot;)
        print &quot;Back from GreetUser().&quot;
        integer amount = GetUserBalance()
        print &quot;You entered $&quot; + amount
        print &quot;Leaving...&quot;
    end
    action GreetUser(text name)
        print &quot;Hello there, &quot; + name + &quot;!&quot;
    end
    action GetUserBalance returns integer
        text in = input(&quot;How much money is in your savings account?&quot;)
        integer amount = cast(integer, in)
        return amount
    end
end
</code></pre></p>
<p>
When we run this program, our output now looks like so:
</p>
<p><pre class="code"><code>
Hello there, Jeff!
Back from GreetUser().
You entered $33
Leaving...
</code></pre></p>
<h2>Task 5: Putting It All Together</h2>
<p>
Now that we have covered the basics of actions, parameters and return values, let's try putting all of this together and create an action with parameters <i>and</i> a return value. Let's create a couple of actions, <tt>multiply</tt> and <tt>divide</tt>. We will walk through the creation of the <tt>multiply</tt> action step-by-step, and leave the <tt>divide</tt> action up to you, with a word of caution.
</p>
<p>
To start out, let's erase all of the code in the Sodbeans editor so that it again looks like it did when we started:
</p>
<p><pre class="code"><code>
class Main
    action Main
    end
end
</code></pre></p>
<p>
When designing actions, it is important to consider the input of the action and any potential results. In the case of the <tt>multiply</tt> action, we need to pass it two numbers, <tt>a</tt> and <tt>b</tt>, and our result also needs to be a number. Given this, we wind up with the action below:
</p>
<p><pre class="code"><code>
action Multiply(number a,  number b) returns number
    number result = a * b
    return result
end
</code></pre></p>
<p>
Notice that this action is different than others we have studied so far, as it accepts multiple parameters. When specifying multiple parameters, we separate each parameter with a comma. Let's also add code to call this action from <tt>Main</tt>, as in below:
</p>
<p><pre class="code"><code>
number k = Multiply(2, 4)
print k
</code></pre></p>
<p>
Now, our code in the editor looks like the following:
</p>
<p><pre class="code"><code>
class Main
    action Main
        number k = Multiply(2, 4)
        print k
    end
    action Multiply(number a, number b) returns number
        number result = a * b
        return result
    end
end
</code></pre></p>
<p>
When we run this code, the value in the variable <tt>k</tt> will be 8.
</p>
<p>
For the final portion of this lab, write a method, called <tt>Divide</tt>, that takes two parameters of type <tt>number</tt>, <tt>a</tt> and <tt>b</tt>, and returns the type <tt>number</tt>. Note that division by zero is not allowed. Write a method that performs division, but returns the number 0.0 when division by zero will occur. Use your knowledge from Chapters 2 and 3. Hint: Make use of the <tt>if</tt> construct.
</p>
<p>
To check your work, here are a few examples of input and the values that will be returned.
</p>
<p><pre class="code"><code>
number k = Divide(2, 4)
</code></pre></p>
<p>
The value of <tt>k</tt> should be 0.5.
</p>
<p><pre class="code"><code>
number k = Divide(9, 3)
</code></pre></p>
<p>
The value of <tt>k</tt> should be 3.0.
</p>
<p><pre class="code"><code>
number k = Divide(6, 0)
</code></pre></p>
<p>
The value of <tt>k</tt> should be 0.0. (division by zero has occurred).
</p>
<?php include("../../include/footer.php"); ?>