# The Quorum Programming Language #

The Quorum programming language is a general purpose language designed for several purposes. First, we regularly run experiments with people at various age and experience ranges, investigating ways to make the language easier to use. Evidence gathered from these studies is filtered back into the design, making quorum an "evidence-based" programming language. Second, as our team is interested in issues of equity for all people, perhaps especially people with disabilities, we are careful to design libraries that are friendly to the broad population.

### Developing in Quorum ###

The easiest way to develop for Quorum is to use the corresponding development environment, Quorum Studio. Quorum Studio provides many of the basic features of Quorum, like compiling and running programs, out of the box. Quorum Studio can be downloaded from [The Quorum Download Page](https://quorumlanguage.com/download.html).

### Building Quorum from Source ###

The easiest way to build Quorum is in Quorum Studio. To build it, please follow these directions:

1. Visit the Bitbucket page to get the latest source code for Quorum: [Quorum Repository](https://bitbucket.org/stefika/quorum-language).
1. Switch to the most current branch, if you wish (e.g., git checkout Quorum9).
1. Clone the repository using Git from the terminal. The command will look something like "git clone https://stefika@bitbucket.org/stefika/quorum-language.git"
1. Open Quorum Studio 3.0 or above
1. Inside of Quorum Studio, open Quorum, which is under quorum-language/Quorum
1. Build the project normally

We can also regenerate the standard library by running RunLibrary.quorum or run the test suite by running CompilerTestSuite.quorum.


### Overriding the Standard Library in Quorum Studio ###
Sometimes you need to tell Quorum Studio that you are requesting a change to the standard library itself. This is similar to telling Java you want a different version of the java development kit than the one it has installed. The following allows us to issue a standard library override (Requires Quorum Studio 3.0 or above):

1. Open the quorum compiler to the current version (Quorum9 branch)
1. Make any changes to the standard library you want.
1. Call RunLibrary.quorum by doing Set As Main and running the project. 
1. Close Quorum Studio
1. Open Quorum Studio's options file. This is usually located at
   1. Mac: /Users/myusername/Library/Application Support/Quorum Studio/Options.json
   1. PC: type %appdata% into explorer and find QuorumStudio/Options.json
1. Change the JSON, to the following two lines:

```
Override Standard Library": true,
"Library": "/Users/myusername/Repositories/quorum-language/Quorum/Library",
```

The actual library location is wherever your Quorum installation lives. We can then restart Quorum Studio and it will use our custom version of the standard library. 
