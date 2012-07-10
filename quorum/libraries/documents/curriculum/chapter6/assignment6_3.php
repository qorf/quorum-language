<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 6_3';
</script>

<h1>Short Assignment: Employee Creator</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul><li>Using inheritance
</li><li>Creating class actions and using derived actions
</li><li>Using Polymorphism
</li></ul><h2>Overview</h2>
<blockquote>
<p>
In this assignment we will be creating a program that tells the user the different amounts an employee can make, depending on what kind of employee they are.  There are three different kinds of employees: a salaried employee, an hourly employee, and a commission employee.  Each one will require different inputs and will have a different method of calculating their earnings.
</p>
</blockquote>
<h2>Design Criteria</h2>
<ul><li>Create a new assignment and label it Assignment 6_3
</li><li>Create a Class called <tt>Employee</tt>
</li><li>Create a Class called <tt>SalariedEmployee</tt>
</li><li>Create a Class called <tt>HourlyEmployee</tt>
</li><li>Create a Class called <tt>CommissionEmployee</tt>
</li></ul><h2>Class <tt>Employee</tt></h2>
<p>
Class Employee will be your base class.  It needs the following actions:
</p>
<ul><li><tt>blueprint action Earnings() returns number</tt> //each class will have a separate implementation for <tt>Earnings()</tt>, so a blueprint action will work well.
</li><li><tt>SetFirstName(text name)</tt>
</li><li><tt>GetFirstName() returns text</tt>
</li><li><tt>SetLastName(text name)</tt>
</li><li><tt>GetLastName() returns text</tt>
</li><li><tt>SetSSN(integer value)</tt>
</li><li><tt>GetSSN() returns integer</tt>
</li><li><tt>Print()</tt>
</li></ul><p>
The print() action will be generic in this class, with only a say statement to tell the user the first and last name and social security number.  In other classes, we will use polymorphism to implement a more custom print action.
</p>
<h2>Class <tt>SalariedEmployee</tt></h2>
<p>
This class will inherit from Employee. It needs the following actions:
</p>
<ul><li><tt>SetWeeklySalary(number salary)</tt>
</li><li><tt>GetWeeklySalary() returns number</tt>
</li><li><tt>ReportSalariedEmployee(text first, text last, integer ssn, number salary)</tt>
</li></ul><p>
<tt>ReportSalariedEmployee</tt> should call the setters for name, ssn, and weekly salary. Action <tt>Earnings()</tt> should return the weekly salary.
</p>
<h2>Class <tt>HourlyEmployee</tt></h2>
<p>
This class also inherits from Employee.  It needs the following actions:
</p>
<ul><li><tt>SetWage(number wage)</tt>
</li><li><tt>GetWage()returns number</tt>
</li><li><tt>SetHours(number hours)</tt>
</li><li><tt>GetHours()returns number</tt>
</li><li><tt>ReportHourlyEmployee(text first, text last, integer ssn, number wage, number hours)</tt>
</li></ul><p>
<tt>ReportHourlyEmployee</tt> does the same thing <tt>ReportSalariedEmployee</tt> does, only it should call the setters for wage and hours instead. Action <tt>Earnings()</tt> should return the weekly earnings of an hourly employee.  If the hourly employee has worked over 40 hours, then make sure that your <tt>Earnings()</tt> action is giving him the proper overtime(overtime is 1.5x the normal pay).
</p>
<h2>Class <tt>CommissionEmployee</tt></h2>
<p>
This class also inherits from Employee. It needs the following actions:
</p>
<ul><li><tt>SetCommissionRate(number rate)</tt>
</li><li><tt>GetCommissionRate() returns number</tt>
</li><li><tt>SetSales(number value)</tt>
</li><li><tt>GetSales() returns number</tt>
</li><li><tt>ReportCommissionEmployee(text first, text last, integer ssn, number commissionRate, number sales)</tt>
</li></ul><p>
<tt>ReportCommissionEmployee</tt> will do as the previous Report actions do, except it will call the setters for commission rate and sales.  Action <tt>Earnings()</tt> should return the earnings of a commission employee (which is the commission rate * number of sales).
</p>
<h2>Class <tt>Main</tt></h2>
<p>
In class Main, create variables that will satisfy all the arguments for each class.  When ran, the program should report to the user the name of the employee, what kind of employee they are, and what their earnings are.  Make sure to do this for each type of employee.
</p>

<?php include("../../include/footer.php"); ?>