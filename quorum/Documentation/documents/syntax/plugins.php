<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Writing Plugins for Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Writing Quorum Plugins</h1>
		<p>How to connect to Java (or other languages) from Quorum</p>
	</div>
</div>

<?php include("../../static/templates/contentwrapperheader.template.php"); ?>

<h1>Calling Java or C++ from Quorum</h1>
<p>
It is sometimes necessary, in any programming language, to want to use a feature
found in another, like Java or C++. Given this, many languages provide 
a method for calling from one language to another, and this includes Quorum. In 
Java, for example, we might
use the Java Native Interface (JNI) to call down to C or C++. Or, in C, we might 
embed assembly code. In
Quorum, we accomplish the same kind of idea through plugins. On this page,
we describe how we write plugins for the Java programming language. As this is
an advanced topic, we recommend being familiar with Quorum before attempting
this material.
</p>

<h2>Creating System Actions</h2>
<p>In order to make a call down to Java, we first must write a Quorum class
that defines &quot;system actions.&quot; A system action, by definition, is 
an action that calls down to another programming language.</p>
<p><pre class="code"><code>
package Libraries.Mine

class MyPlugin
    system action Test
end
</code></pre></p>


<p>
In the above code, notice that we have defined a regular class, but we have an 
action with the word &quot;system&quot; before it, with no &quot;end&quot; at
the end of the line. This signifies to the compiler that the content of this action
will be defined by Java. Here is a second example with more complex system
actions.
</p>

<p><pre class="code"><code>
package Libraries.Mine

class MyPlugin
    system action Test
    system action Add(number a, number b) returns number
end
</code></pre></p>
<p>Notice that the second action defines parameters, return values,
and otherwise looks like a normal action. This is normal, as all features of a
typical action are allowed in system actions.
</p>
<h2>Creating the Java Plugins</h2>
<p>Once we have created our system actions, we need to create corresponding
Java plugins, so our system actions have something to call. Let's define a plugin for our
second code example to show how this can be done.</p>

<p><pre class="code"><code>
package plugins.quorum.Libraries.Mine

public class MyPlugin
    public java.lang.Object $me = null;
    public void Test() {
    }

    public double Add(double a, double b) {
        return a + b;
    }
end
</code></pre></p>
<p>In the first line of our plugin, notice that the package is defined as 
plugins.quorum.Libraries.Mine. This is required, as the Quorum compiler will expect that,
since we are defining a system action in the class MyPlugin in the package
Libraries.Mine, that the Java plugin is also called MyPlugin and that it is located
in the package plugins.quorum.Libraries.Mine.</p>

<p>Second, notice that Quorum actions translate directly into public methods in the plugin.
The Test action, which in Quorum has no return type, translates into a void method
in Java. Similarly, actions in Quorum that
have parameters are translated according to their values. With primitives, the translation
is integer to int, number to double, boolean to boolean, and text to String. For 
object types, an object with a fully qualified name of org.Test in Quorum would be
quorum.org.Test when used in a parameter.</p>

<p>As one final point, notice the line
public java.lang.Object $me = null;. All Java plugins for Quorum are required
to have this line, otherwise we will throw an exception at runtime. This line
allows Quorum to pass the original object that will call down to our plugin, 
in case we need it. This variable, $me, will be a valid value once the
plugin first boots. </p>

<h2>Installing the Java Plugins</h2>
<p>
Once we have successfully created our plugin, we deploy it by installing it into
the Quorum compiler directly. To do this, we first build our plugin and retrieve
the file labeled .class. In the case of our current example, this would a file
named MyPlugin.class. We will be copying this into the plugin directory
in the compiler.
</p>

<p>Once we have our plugin's .class file from Java, we go to our installed
Quorum compiler and add it to the folder labeled libraries/plugins, in an 
appropriate subfolder. For our example, if the full package name we specified in 
our system action class was Libraries.Mine.MyPlugin, then we would put the .class
file for our plugin in the folder libraries/plugins/quorum/Libraries/Mine/MyPlugin.class.
</p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>