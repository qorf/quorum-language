<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 6_3';
</script>

<h1>Assignment 6.3: Employee Creator</h1>
<h2>Objectives</h2>
<p>
    The goal of this assignment is to learn the following:
</p>
<ul>
    <li>
        How to use inheritance
    </li>
    <li>
        How to create class actions and use derived actions
    </li>
    <li>
        How to use Polymorphism
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this assignment you will create a program that tells the user the different amounts an employee can make, depending on what kind of employee they are. There are three different kinds of employees: a salaried employee, an hourly employee, and a commissioned employee. Each one will require different inputs and will require a different method for calculating earnings.
</p>
<h2>Requirements</h2>
<p>
    You will need five classes: <tt>Employee</tt>, <tt>SalariedEmployee</tt>, <tt>HourlyEmployee</tt>, <tt>CommissionEmployee</tt>, and <tt>Main</tt>. Class <tt>Employee</tt> will be your base class, and will contain actions and blueprint actions to be used in each class that inherits from <tt>Employee</tt>.
</p>
<h2>Class <tt>Employee</tt></h2>
<p>
    In this class, you will implement several setters and getters for variables that will be used in each class that inherits from Employee. Since every employee, regardless of how they are paid, share some common information (they have a name and a social security number), this class will obtain and provide that information. Class <tt>Employee</tt> will have the following actions:
</p>
<ul>
    <li>
        <b>
            blueprint action Earnings returns number
        </b>
    </li>
</ul>
<p>
    Each class will have a separate implementation for <tt>Earnings</tt>, so a blueprint action will work well here.
</p>
<ul>
    <li>
        <b>
            action SetFirstName(text name)
        </b>
    </li>
</ul>
<p>
    Setter for the text variable <tt>firstName</tt>
</p>
<ul>
    <li>
        <b>
            action GetFirstName returns text
        </b>
    </li>
</ul>
<p>
    Getter for the first name of an employee.
</p>
<ul>
    <li>
        <b>
            action SetLastName(text name)
        </b>
    </li>
</ul>
<p>
    Setter for the text variable <tt>lastName</tt>
</p>
<ul>
    <li>
        <b>
            action GetLastName returns text
        </b>
    </li>
</ul>
<p>
    Getter for the last name of an employee.
</p>
<ul>
    <li>
        <b>
            action SetSSN(integer value)
        </b>
    </li>
</ul>
<p>
    Setter for the integer variable <tt>ssn</tt>
</p>
<ul>
    <li>
        <b>
            action GetSSN returns integer
        </b>
    </li>
</ul>
<p>
    Getter for the social security number of an employee.
</p>
<ul>
    <li>
        <b>
            action Print
        </b>
    </li>
</ul>
<p>
    The <tt>Print</tt> action will be generic in this class, with only a say statement to tell the user the first and last name and social security number. In other classes, we will use polymorphism to implement a more custom print action.
</p>
<h2>Class <tt>SalariedEmployee</tt></h2>
<p>
    This class will inherit from <tt>Employee</tt>. It needs the following actions:
</p>
<ul>
    <li>
        <b>
            action SetWeeklySalary(number salary)
        </b>
    </li>
</ul>
<p>
    Setter for the number variable <tt>weeklySalary</tt>.
</p>
<ul>
    <li>
        <b>
            action GetWeeklySalary returns number
        </b>
    </li>
</ul>
<p>
    Getter for the weekly salary of an employee.
</p>
<ul>
    <li>
        <b>
            action ReportSalariedEmployee(text first, text last, integer ssn, number salary)
        </b>
    </li>
</ul>
<p>
    <tt>ReportSalariedEmployee</tt> should call the setters for the name(first and last), ssn, and weekly salary of an employee.
</p>
<ul>
    <li>
        <b>
            action Earnings returns number
        </b>
    </li>
</ul>
<p>
    <tt>Earnings</tt> should return the weekly salary of an employee by making a call to action <tt>GetWeeklySalary</tt>. It should look similar to the following:
</p>
<pre class="code">
    action Earnings returns number
    return GetWeeklySalary()
    end
</pre>
<p>
    Since action <tt>GetWeeklySalary</tt> returns a value, we can call the action in a return statement, as seen above.
</p>
<h2>Class <tt>HourlyEmployee</tt></h2>
<p>
    This class also inherits from <tt>Employee</tt>. It needs the following actions:
</p>
<ul>
    <li>
        <b>
            action SetWage(number wage)
        </b>
    </li>
</ul>
<p>
    Setter for the number variable <tt>hourlyWage</tt>.
</p>
<ul>
    <li>
        <b>
            action GetWagereturns number
        </b>
    </li>
</ul>
<p>
    Getter for the hourly wage of an employee.
</p>
<ul>
    <li>
        <b>
            action SetHours(number hours)
        </b>
    </li>
</ul>
<p>
    Setter for the number variable <tt>hoursWorked</tt>.
</p>
<ul>
    <li>
        <b>
            action GetHours returns number
        </b>
    </li>
</ul>
<p>
    Getter for the hours worked by an employee.
</p>
<ul>
    <li>
        <b>
            action ReportHourlyEmployee(text first, text last, integer ssn, number wage, number hours)
        </b>
    </li>
</ul>
<p>
    <tt>ReportHourlyEmployee</tt> does the same thing <tt>ReportSalariedEmployee</tt> does, only it should call the setters for wage and hours instead.
</p>
<ul>
    <li>
        <b>
            action Earnings returns number
        </b>
    </li>
</ul>
<p>
    <tt>Earnings</tt> should return the weekly earnings of an hourly employee. If the hourly employee has worked over 40 hours, then make sure that action <tt>Earnings</tt> is giving the employee the proper overtime(overtime is 1.5x the normal pay).
</p>
<h2>Class <tt>CommissionEmployee</tt></h2>
<p>
    This class also inherits from <tt>Employee</tt>. It needs the following actions:
</p>
<ul>
    <li>
        <b>
            action SetCommissionRate(number rate)
        </b>
    </li>
</ul>
<p>
    Setter for the number variable <tt>commissionRate</tt>
</p>
<ul>
    <li>
        <b>
            action GetCommissionRate returns number
        </b>
    </li>
</ul>
<p>
    Getter for the commission rate of an employee.
</p>
<ul>
    <li>
        <b>
            action SetSales(number value)
        </b>
    </li>
</ul>
<p>
    Setter for the number variable <tt>sales</tt>
</p>
<ul>
    <li>
        <b>
            action GetSales returns number
        </b>
    </li>
</ul>
<p>
    Getter for the number of sales an employee makes.
</p>
<ul>
    <li>
        <b>
            action ReportCommissionEmployee(text first, text last, integer ssn, number commissionRate, number sales)
        </b>
    </li>
</ul>
<p>
    <tt>ReportCommissionEmployee</tt> will do as the previous "Report" actions do, except it will call the setters for commission rate and sales.
</p>
<ul>
    <li>
        <b>
            action Earnings returns number
        </b>
    </li>
</ul>
<p>
    <tt>Earnings</tt> should return the earnings of a commissioned employee. This values can be calculated by commission rate * number of sales.
</p>
<h2>Class <tt>Main</tt></h2>
<p>
    In class <tt>Main</tt>, create variables that will satisfy all the arguments for each class. Then call the appropriate actions from each class to satisfy the requirements in the Sample Output section.
</p>
<h2>Sample Output</h2>
<p>
    When run, the program should report to the user the name of the employee, what kind of employee they are, and what their earnings are. For the hourly employee, tell the user what their hourly wage and number of hours worked are, in addition to their name, earnings, and type of employee. For a commissioned employee, tell the user their commission rate and the number of sales they made, in addition to their name, earnings, and type of employee. Output should be similar to the following:
</p>
<pre class="code">
    Salaried employee Brandon Spencer. Total earnings for this salaried employee is 60,000 dollars.
    Commission employee Brandon Spencer. Gross sales is 20 sales at 4 dollars per a sale. Total earnings for this commissioned employee is 80 dollars.
    Hourly employee Brandon Spencer. Hourly wage is 16.0 and number of hours worked is 40. Total earnings for this hourly employee is 640 dollars.
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>