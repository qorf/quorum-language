<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 8.1: Nutrition Log | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 8.1</h1>
		<p>Nutrition Log</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<!--<h1>Assignment 8.1: Nutrition Log</h1>-->
<h2>Objectives</h2>
<p>The goal of this assignment is to learn the following:</p>
<ul>
    <li>
        How to use the Text class
    </li>
    <li>
        File I/O
    </li>
    <li>
        Data validation
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this assignment you will create a nutrition log program. This program will calculate caloric values for a meal or a single ingredient based on the amount of carbs, proteins, and fats (in grams) that are entered. It will display the total calories that are in that meal or ingredient, and will also display the ratio of calories from carbs, proteins, and fats. It will also let the user enter a target calorie value, and will tell them what percentage of their target calories the meal or ingredient has used. All of this information will be written to a file designated by the user, in a certain directory if the user chooses to do so. This program will rely heavily on data validation and error checking. In <a href="lab8_3.php">lab 8_3</a> you were exposed to a little data validation, but in this assignment you want the user to be able to enter nearly anything and not have the program crash. There are three key types of data validation this program will do:
</p>
<ul>
    <li>
        character checks
    </li>
    <li>
        data type checks
    </li>
    <li>
        file existence checks
    </li>
</ul>
<p>
    Create a new project and name it <b>Assignment8_1</b>. Create a second class and name it <code>NutritionLog</code>.
</p>
<h2>Class <code>NutritionLog</code></h2>
<p>
    You will need the following libraries for this assignment:
</p>
<ul>
    <li>
        use Libraries.System.File
    </li>
    <li>
        use Libraries.System.FileWriter
    </li>
    <li>
        use Libraries.Language.Types.Text
    </li>
    <li>
        use Libraries.System.DateTime
    </li>
    <li>
        use Libraries.Language.Errors.Error
    </li>
</ul>
<p>
    Class <code>NutritionLog</code> will have the following actions:
</p>
<ul>
    <li>
        <b>
            action CalculateCarbs() returns number
        </b>
    </li>
</ul>
<p>
    This action should prompt the user for a number type input for the amount of carbs consumed (in grams). If the user enters anything but a number or integer, they should get an error message telling them to only enter an integer or number, and the input should be set to zero. To calculate the amount of calories obtained from carbs, just multiply the input by four, since each gram of carbs is worth four calories. This number should be returned.
</p>
<ul>
    <li>
        <b>
            action CalculateProteins() returns number
        </b>
    </li>
</ul>
<p>
    This action will do the same as <code>CalculateCarbs()</code>, except the user will be prompted to enter grams of protein. A gram of protein is worth the same caloric value as a gram of carbs.
</p>
<ul>
    <li>
        <b>
            action CalculateFats() returns number
        </b>
    </li>
</ul>
<p>
    This action will do the same as <code>CalculateProteins</code>, except the user will be prompted for fats. A gram of fat is worth 9 calories instead of four.
</p>
<ul>
    <li>
        <b>
            action CalculateTotalCalories(number carbs, number proteins, number fats) returns number
        </b>
    </li>
</ul>
<p>
    <code>CalculateTotalCalories</code> will add up the values entered as arguments and return their sum.
</p>
<ul>
    <li>
        <b>
            action CalculateCarbsDaily(number carbs, number totalCalories) returns number
        </b>
    </li>
</ul>
<p>
    <code>CalculateCarbsDaily</code> will calculate the percentage of consumed calories that were carbs, then return that percentage.
</p>
<ul>
    <li>
        <b>
            action CalculateProteinsDaily(number proteins, number totalCalories) returns number
        </b>
    </li>
</ul>
<p>
    <code>CalculateProteinsDaily</code> will calculate the percentage of consumed calories that were proteins, then return that percentage.
</p>
<ul>
    <li>
        <b>
            action CalculateFatsDaily(number fats, number totalCalories) returns number
        </b>
    </li>
</ul>
<p>
    <code>CalculateFatsDaily</code> will calculate the percentage of consumed calories that were fats, then return that percentage.
</p>
<ul>
    <li>
        <b>
            action CalculateCaloriesMet(number totalCalories, number goal) returns number
        </b>
    </li>
</ul>
<p>
    <code>CalculateCaloriesMet</code> will calculate the percentage of calories consumed (totalCalories) as per the calorie goal that is entered, and then return that percentage.
</p>
<ul>
    <li>
        <b>
            action ConvertHour(integer hour) returns text
        </b>
    </li>
</ul>
<p>
    Because this log can be used across multiple days, you want to be able to time stamp each entry. You can do this by using the DateTime class in Quorum. In the next action, you will write the month, day, year, and hour of time to the log. However, the <code>GetHour</code> action from the <code>DateTime</code> class returns an integer for the hour in a 0-23 range, where 0-12 = 12Am to 12Pm and 13-23 = 1Pm to 11Pm. <code>ConvertHour</code> is going to convert the hour integer into normal time. The algorithm for this looks like the following:
</p>
<pre class="code">
    if hour &gt 12 and hour &lt 24
        hour = hour - 12
    elseif hour &gt= 0 and hour &lt 13
        if hour = 0
        return "12am"
    end
end
</pre>
<p>
    Since the return type is text, you can add "pm" to the number if its pm and "am" to the number if its am.
</p>
<ul>
    <li>
        <b>
            action WriteToLog(number carbs, number proteins, number fats, number totalCalories, number caloriesMet)
        </b>
    </li>
</ul>
<p>
    This action is what will write all the needed values to a file. This action should ask the user if they would like to enter a custom directory to save their log. To check for their decision, the this action should check if they entered "y", "Y", "yes" or "Yes". For example:
</p>
<pre class="code">
    text choice = input("Would you like to set a custom directory to save your Nutrition Log?")
    if choice = "y" or choice = "Y" or choice = "Yes" or choice = "yes"
    ....do stuff
</pre>
<p>
    If they enter yes, then you need to get a valid directory. This can be done by using the <code>SetWorkingDirectory()</code> action from the File class. The <code>SetWorkingDirectory()</code> action can only be called after the <code>SetPath()</code> action, where the path should be set to the path the user enters. For example:
</p>
<pre class="code">
    File file
    text directory = "C:\Users\Brandon\Desktop"
    file: SetPath(directory)
    file::SetWorkingDirectory(directory)
</pre>
<p>
    However, you before setting the directory, you should check if the entered directory is valid or not. This can be done using the <code>IsDirectory()</code> action from the <code>File</code> class. If it's not a valid directory, give the user an error message telling them its not.
</p>
<p>
    Next, you need to get the name of a file to write to. Prompt the user to enter a name, followed by the extension type. Quorum can make many different file types, such as .txt files or .doc files. Again, check to make sure the user has entered a valid document name. For instance, they should not be allowed to enter only a name, without the extension, or to enter only numbers or integers. *Hint* a good way to do this is to check for the location of the "." in the name. Because the "." will always be followed by three characters, you should be able to create a loop that will only exit if the "." is in the correct spot (the fourth from the end of the name).
</p>
<p>
    Once a valid name is obtained, open the file in such a way so that items can continue to be written to it without the fear of erasing the whole document. Next, using the <code>WriteLine</code> action, write the date and hour into the log. The date can be obtained automatically using the <code>GetMonth()</code>, <code>GetDayOfMonth()</code>, and <code>GetYear()</code> actions from the <code>DateTime</code> class. Write these values to the log in the following format: mm/dd/yyyy. Next, write the total calories consumed so far, percentage of calories from carbs, percentage of calories from proteins, percentage of calories from fats, and percentage of calories consumed based off of the user's caloric goal. (All of these values are arguments entered into the action). Don't forget to call the <code>Close()</code> action so the file closes properly.
</p>
<h2>Main</h2>
<p>
    Because the user may only know the nutrition values for a given ingredient, they should get the option to enter as many ingredients as they have and have the program calculate everything based on the accumulation of the ingredients. In Main, ask the user if they want to enter a whole meal or an ingredient. As you did before, the program should check if they enter only a letter or a whole word for their answer, i.e. "meal", "Meal", "M", or "m" for meal. They should continue to be prompted this question if they enter anything other than acceptable inputs for meal or ingredients.
</p>
<p>
    If they want to enter ingredients, ask for how many ingredients. Using the actions you wrote in class NutritionLog, accumulate the needed values for all the ingredients they want to enter, then write to the log the results. In Quorum, the number type can extend for several digits, so you're going to use the Round action from class Math to round to the hundredths place for the percentages.
</p>
<p>
    If the user enters that they have only a meal to record, then you don't need to worry about accumulating multiple values and then writing them.
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following: For ingredients:
</p>
<pre class="code">
    Are you entering a whole meal or an ingredient?
    ingredient
    How many ingredients are you adding to the log?
    2
    What is your caloric goal?
    2500
    Enter the number of carbs consumed
    23
    Enter the number of proteins consumed
    11
    Enter the number of fats consumed
    4
    Enter the number of carbs consumed
    10
    Enter the number of proteins consumed
    8
    Enter the number of fats consumed
    2
    Would you like to set a custom directory?
    no
    Enter the name of your log
    Brandon's Log.txt
    **File created**
</pre>
<p>
    For a meal:
</p>
<pre class="code">
    Are you entering a whole meal or an ingredient?
    meal
    What is your caloric goal?
    2500
    Enter the number of carbs consumed
    10
    Enter the number of proteins consumed
    11
    Enter the number of fats consumed
    10
    Would you like to set a custom directory?
    yes
    What is the directory?
    C:\\Users\brandon_spencer\Desktop
    Enter the name of your log
    Brandon's Log.txt
    **File created**
</pre>
<p>
    Here is an example of the contents of a log once the program has been run a couple times:
</p>
<pre class="code">
    8/23/2012 at 4pm
    Calories consumed so far 39.0
    Percentage of calories from Carbs: 10.3%
    Percentage of calories from Proteins: 20.5%
    Percentage of calories from Fats: 69.2%
    Percentage of calories consumed so far: 2.0%

    8/24/2012 at 1pm
    Calories consumed so far 262.0
    Percentage of calories from Carbs: 50.4%
    Percentage of calories from Proteins: 29.0%
    Percentage of calories from Fats: 20.6%
    Percentage of calories consumed so far: 10.5%
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>