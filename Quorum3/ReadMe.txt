Quorum 3.0

This file describes the basic console version of Quorum. Generally, while the Quorum
team provides this version of Quorum for professionals that would like to interact
from a traditional console window, we strongly encourage all users to consider
using the version of Quorum built into NetBeans instead. 
For more information, please see http://quorumlanguage.com/documents/tutorials/started.php

Valid Flags in Quorum 3.0: quorum (-flag value*)*


    -name This flag tells Quorum to change the name the file is outputs.

    -compile This flag tells Quorum to compile a set of files.

    -test This flag tells Quorum to run its test suite on itself.

    -help This flag tells Quorum to output this help screen. 


Here are a few examples of how you can use this program:
    java -jar Quorum.jar Hello.quorum 

This would request that Quorum compiles the source file Hello.quorum.

    java -jar Quorum.jar -compile Hello.quorum

This would cause a program to compile and generate a file named MyProgram.jar (which you can execute from the keyboard or by double clicking).

    java -jar Quorum.jar -name MyProgram -compile Hello.quorum

This would cause Quorum to compile Hello.quorum and name the output MyProgram.

    java -jar Quorum.jar Hello.quorum Goodbye.quorum

This would cause Quorum to Compile two source code files. The first file must have a Main action.
For more information on writing programs in Quorum, visit www.quorumlanguage.com.

