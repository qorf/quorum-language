Quorum 9.0

Quorum is a computer programming language, which you can use either from the console using flags (the program you just ran) or from a development environment (like NetBeans). For this version, the commands that Quorum knows take the following format:

    java -jar Quorum.jar (-flag value*)*

What this means is that you can pass a flag to Quorum combined with any number of values. The legal flags are listed as follows:
    -name This flag tells Quorum to change the name the file is outputs.
    -compile This flag tells Quorum to compile a set of files. If no flag is passed, this is the default.
    -web This flag tells Quorum to convert to a server-side application
    -javascript This flag converts the source code to JavaScript, so it can be run in a browser.
    -test This flag tells Quorum to run its test suite on itself.
    -library If a file path is passed to this flag, we can tell Quorum we have placed its standard library in a different folder.
    -setup If a file path is passed to this flag, it tells Quorum to recompile its standard library. Generally, unless you have modified the standard library, this flag should not be used.
    -server Starts a server on port 1269 to serve conversions to JavaScript from Quorum source.
    -help This flag tells Quorum to output this help screen. 


Here are a few examples of how you can use this program:
        java -jar Quorum.jar Hello.quorum 

    This would request that Quorum compiles the source file Hello.quorum.

        java -jar Quorum.jar -compile Hello.quorum

        java -jar Quorum.jar -name MyProgram -compile Hello.quorum

    This would cause Quorum to compile Hello.quorum and name the output MyProgram.

        java -jar Quorum.jar Hello.quorum Goodbye.quorum

    This would cause Quorum to Compile two source code files. The first file must have a Main action.

For more information on writing programs in Quorum, visit www.quorumlanguage.com.
