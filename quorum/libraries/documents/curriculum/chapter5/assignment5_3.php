<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'assignment 5_3';
</script>

<h1>Assignment: Student Grades Using Arrays</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul><li>Instantiating an Array data structure
</li><li>Creating, adding, and getting items in an array
</li><li>Using Arrays as parameters
</li><li>Designing and using classes
</li></ul><h2>Overview</h2>
<p>
In the <strong>StudentGrades</strong> application we will practice storing values in arrays and continue practicing object-oriented programming. First create a Quorum class named <strong>Student</strong>. In this class we will store the name of the student, the max possible score the student can get, and an array of scores the student has received on assignments. For this project we will assume each student only has 5 scores, each worth 100 points. This means the max possible score is 500 points. In addition to these class variables you will need to define the following actions in the Student class:
</p>
<pre class="code">use Libraries.Containers.Array
class Student 
    //define class variables here
    
    //sets the student name
    action SetName(text name)
    end

    //get the student name
    action GetName returns text
    end

    //set the scores for the student
    action SetScores(Array&lt;integer&gt; scores)
    end
    
    //Returns the students overall grade in the class
    //An A is 90 to 100 percent, a B is 80 to 89 percent,
    //a C is 70 to 79 percent, a D is 60 to 69 percent, and
    //an F is anything lower than 60 percent
    action GetFinalGrade returns text
        return ""
    end

    //a private action to help us calculate the total points
    //a student has (stored in the scores array).
    private action GetPointTotal returns number
        integer totalPoints = 0
        //your code here
        return totalPoints
    end

    //a private action that gets the final percentage a student
    //has in the class (their total points/ max points possible).
    private action GetFinalPercentage returns number
        number value = 0
        return value
    end
end
</pre><p>
In <strong>main.quorum</strong> we will need to define two Arrays one to store <tt>Student</tt> objects and another to store grades, <tt>integer</tt>. Once we have defined these arrays we can start to construct each student and their grades. Define two students with the names and grades listed below:
</p>
<p>
<br />
Students
</p>
<table class="table">
<tr><td><strong>Name</strong></td><td><strong>Score 1</strong></td><td><strong>Score 2</strong></td><td><strong>Score 3</strong></td><td><strong>Score 4</strong></td><td><strong>Score 5</strong>
</td></tr><tr><td>jenny</td><td>92</td><td>100</td><td>95</td><td>88</td><td>79
</td></tr><tr><td>john</td><td>73</td><td>91</td><td>87</td><td>88</td><td>95
</td></tr></table>
<p>
Once this information is stored we can loop through the Array of students and print out some basic information. The program should produce output that does the following:
</p>
<ul><li>Get the student from the array.
</li><li>Print or say the student name and the array index the student was found at.
</li><li>Calculate the students grade.
</li><li>Print or say the grade of the student
</li></ul><h2>Sample Output</h2>
<pre class="code">student jenny is at array index 0
jenny's grade is a A
student john is at array index 1
john's grade is a B
</pre><h2>Design Criteria</h2>
<ul><li>Create a new project and name it StudentGrades.
</li><li>There should be a total of 2 files.
<ul><li>main.quorum
</li><li>Student.quorum
</li></ul></li><li>Each file should have a corresponding class defined in the file.
</li><li>Use appropriate variables to store and keep track of values (including class variables).
</li><li>You should implement each action given in the assignment specification.
</li></ul>

<?php include("../../include/footer.php"); ?>