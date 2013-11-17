<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Dealing with errors in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Handling Errors</h1>
		<p>How to deal with the inevitable.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h1>Error Handling</h1>
<p>
    In Quorum, we may occasionally encounter an error. Errors in computer programs
    we write can be caused by a wide variety of problems, from incorrect user input
    to bugs in our programs. For example, if a user attempts 
    to divide a number by zero, Quorum will alert us to a DivideByZeroError. We can
    reproduce this error in Quorum with the following code:
</p>
<p><pre class="code"><code>
number result = 12/0
</code></pre></p>
<p>
    When this error is encountered Quorum will stop running and the user will 
    hear an error message, indicating that a divide by zero error has occurred.
</p>
<p>
    Sometimes we may want Quorum to do something more intelligent when an error 
    is encountered. This is what error handling will allow us to do. In the 
    sections to follow you will learn how to handle errors that might happen 
    and how to create our own custom errors.
</p>
<h2>The Error class</h2>
<p>
    The error class is a class in the Quorum standard library that represents a 
    general error in Quorum(Quorum.Language.Errors.Error). This error stores an error 
    message, a general message telling the user something about the error, and 
    a stack trace, the state of the call stack when the error occurred.
</p>
<p>
    All errors in Quorum inherit from the Error class (this will be important 
    when we create our own custom error). Some of these errors include, 
    CastError, IndexOutOfBoundsError, and DivideByZeroError.
</p>
<h2>Detecting an Error</h2>
<p>
    Sometimes you might want to handle an error that has occurred. For example, 
    using the divide by zero example, suppose your code asked the user to 
    enter a divisor. If the user entered 0 your program would stop running and 
    display an error message(see the following code example).
</p>
<p><pre class="code"><code>
text userInput = input(&quot;divide by?&quot;)
integer divisor = cast(integer, userInput)
result = 12/divisor
</code></pre></p>
<p>
    Instead of causing the program to fail, we could detect the error, let the 
    user know about the problem, and set the divisor to 1. We can do this using 
    the check/detect statement. In the check block you place the code that may 
    cause the error.
</p>
<p><pre class="code"><code>
integer result = 0
check
    text userInput = input(&quot;divide by?&quot;)
    integer divisor = cast(integer, userInput)
    result = 12/divisor
end
</code></pre></p>
<p>
    Then you can use the detect block to specify what type of error to handle 
    or detect. The detect follows the format 
    <code>detect &lt;name&gt; is &lt;error type&gt;</code>. 
    It first specifies the name of the error variable (this can be a name of 
    your choosing, but we use &quot;e&quot;). Then you can specify the type of 
    error you will detect. In this case we want the DivideByZeroError.
</p>
<p><pre class="code"><code>
detect e is DivideByZeroError
    //give the user the error and set the divisor to 1
    say e:GetErrorMessage()
    result = 12/1
end
</code></pre></p>
<p>
    In addition to the check and detect blocks we can specify an optional 
    always block. In the always block you can specify code that needed to 
    execute no matter what the outcome of the check block (it will execute 
    if an error occurred or no error occurred).
</p>
<p><pre class="code"><code>
always
    say &quot;calculating result&quot;
end
</code></pre></p>
<p>
    Putting it all together: the following code will ask the user for a 
    number and check it for the specified error. In this detect block we 
    will detect more than one type of error, a general Error and a 
    DivideByZeroError, because the user could input text which would also be 
    invalid (e.g. &quot;zero&quot;). Once the check and detect blocks are 
    executed the always will be called and a result will be given.
</p>
<p><pre class="code"><code>
use Libraries.Language.Errors.Error
use Libraries.Language.Errors.DivideByZeroError

class Main
    action Main
        number result = 0
        check
            text userInput = input(&quot;divide by?&quot;)
            integer divisor = cast(integer, userInput)
            result = 12/divisor
        detect e is Error or DivideByZeroError
            say e:GetErrorMessage()
            result = 12/1
        always
            say &quot;calculating result&quot;
        end
        say &quot;The result is &quot; + result
    end
end
</code></pre></p>
<h2>Alerts</h2>
<p>
    Sometimes we may need to trigger an error manually. This can be done using 
    alerts which follow two formats, alert(&lt;text&gt;) and alert(&lt;error&gt;). 
    In the first format an error of type Error is generated with the message 
    specified by &lt;text&gt;.
</p>
<p><pre class="code"><code>
alert(&quot;Zero is not a valid input&quot;)
</code></pre></p>
<p>
    In the last format the full error is passed. The following is an example:
</p>
<p><pre class="code"><code>
DivideByZeroError error
error:SetErrorMessage(&quot;Zero is not a valid input&quot;)
alert(error)
</code></pre></p>
<h2>Custom Errors</h2>
<p>
    Creating your own custom errors in Quorum is requires that we extend
    the Libraries.Language.Errors.Error class.
</p>
<p><pre class="code"><code>
use Libraries.Language.Errors.Error
class MyError is Error
end
</code></pre></p>
<p>
    It is also important to note that the error handling system does not support 
    inheritance hierarchies to detect an error. When using the detect statement 
    you can either detect all types of errors with Error or a list of specific 
    errors you wish to detect.
</p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>