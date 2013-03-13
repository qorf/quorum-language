<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Creating custom types with Quorum';
</script>
<h1>Classes in Quorum</h1>
<p>
A class in the Quorum programming language represents a collection of data and 
actions on that data. A new class can be defined like so:
</p>
<p><pre class="code"><code>
class Name
end
</code></pre></p>
<p>
In this case, the word class indicates that we want to create a structure. The 
word Name is the name of the structure that we want to create. Usually, we name 
classes nouns, like Dog, Building, or Banana. Classes are useful when we want 
to group together data and its actions. For example, if we had a bank, we might 
want to allow people to both deposit and withdraw money from an account. In this 
case, a class keeps track of the money (a variable), and how to manipulate 
that money (actions).
</p>
<p>
For example, here is one way we could create a very simple BankAccount class:
</p>
<p><pre class="code"><code>
class BankAccount
     integer money = 0
     action deposit(integer amount)
          money = money + amount
     end
     action withdraw(integer amount)
          money = money - amount
     end
end
</code></pre></p>
<h2>Instances of Classes</h2>
<p>
Classes are used to organize computer code. For example, if our computer program 
starts up, we can create a new &quot;instance&quot; of a class, called an 
object. We can then use this instance to conduct operations in the data stored 
in the object. Here is an example of a program that uses the Bank class and 
deposits ten dollars into the account:
</p>
<p><pre class="code"><code>
use BankAccount
class Main
     action Main
          BankAccount account
          account:deposit(10)
     end
end
</code></pre></p>
 === use Statement ===
<p>
Notice that the previous code first uses the line:
</p>
<p><pre class="code"><code>
use BankAccount
</code></pre></p>
<p>
This tells Quorum to look for a class named Bank and allow you to use it in your program.  If you do not include the use statement, then your main file cannot access the bank account's actions. Find out more about [wiki:documentation/Use use statements here].
</p>
<h3>Instantiating</h3>
<p>
Sometimes, however, you want to create a variable that is a placeholder for an object, but you do not want to actually create that object. You can do this using the &quot;undefined&quot; keyword (called &quot;null&quot; in many other languages). You can do this as so:
</p>
<p><pre class="code"><code>
BankAccount account = undefined
</code></pre></p>
<p>
The variable account now indicates that a bank account object can be placed there, 
but it does not actually create one. This means that attempting to call an action 
on the undefined object will fail. Similarly, you can check to see if a particular 
reference is defined or not with the following code:
</p>
<p><pre class="code"><code>
if SomeVariable = undefined then
   //do something
end
</code></pre></p>
<p>
To instantiate the <code>BankAccount</code> object simply state the type, 
<code>BankAccount</code> in this case, followed by the name, <code>account</code>. 
This will create an instance of that object which we can then call actions on and 
manipulate that object.
</p>
<p><pre class="code"><code>
use BankAccount
class Main
     action Main
          BankAccount account
          account:deposit(10)
     end
end
</code></pre></p>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../static/templates/pageheader.template.php"); ?>