# The Quorum Programming Language #

Quorum is a general purpose programming language designed for several purposes. First, we regularly run experiments with people at various age and experience ranges, investigating ways to make the language easier to use. Evidence gathered from these studies is filtered back into the design, making quorum an "evidence-based" programming language. Second, as our team is interested in issues of equity for all people, perhaps especially people with disabilities, we are careful to design libraries that are friendly to the broad population. This means many internal libraries in the language have support for accessibility.

# Available Libraries and Materials #

Quorum has a mature and rich standard library for creating computer programs, in addition to a significant amount of documentation and [reference tutorials](https://quorumlanguage.com/reference.html). For high-school teachers, there is also a set of tracks available for the classroom, including [Computer Science Principles](https://quorumlanguage.com/lessons/code.html). The reference page provides an abridged list of all available libraries and tutorials supporting them. Roughly, this list includes features like:

1. Sound Libraries (e.g., digital signal processing, playback)
2. Graphics Libraries (e.g.,  2D, 3D, models, shaders, accessibility support)
3. Physics support (e.g., 2D, 3D, joints)
4. User Interface Libraries (e.g., buttons, trees, event systems)
5. Data Science libraries (e.g., accessible charts, statistical tests)
6. Compilation and Parsing (Quorum is self-hosted)
7. LEGO robotics through EV3
8. Database support
9. Data type support (e.g., CSV, JSON)

There are many other types of operations not listed here. For a complete list of currently supported standard library classes, see the [Standard Library Page](https://quorumlanguage.com/libraries.html). Note that Quorum can be run as Java Virtual Machine Bytecode or as JavaScript. Not all libraries are available for each language target.

### Developing in Quorum ###

The easiest way to develop for Quorum is to use the corresponding development environment, [Quorum Studio](https://github.com/qorf/quorum-studio). Quorum Studio provides many of the basic features of Quorum, like compiling and running programs. It can be downloaded from [The Quorum Download Page](https://quorumlanguage.com/download.html). Quorum can also be run online on the website and partners are encouraged to embed Quorum on their webpages.

### Building Quorum from Source ###

The easiest way to build Quorum is in Quorum Studio. To build it, please follow these directions:

1. Visit the Github page to get the latest source code for Quorum: [Quorum Repository](https://github.com/qorf/quorum-language).
1. Switch to the most current branch, if you wish (e.g., git checkout Quorum-10).
1. Clone the repository using Git from the terminal. The command will look something like "git clone https://github.com/qorf/quorum-language.git"
1. Open Quorum Studio 4.0 or above
1. Inside of Quorum Studio, open Quorum, which is under quorum-language/Quorum
1. Build the project

We can also regenerate the standard library by running RunLibrary.quorum or run the test suite by running CompilerTestSuite.quorum. Note that running the test suite, by default, runs only on the Java Virtual Machine. To test Quorum's JavaScript transpiled mode, you need to install [Node JS](https://nodejs.org).


### Overriding the Standard Library in Quorum Studio ###
Sometimes we need to tell Quorum Studio that we are requesting a change to the standard library itself. This is similar to telling Java we want a different version of the java development kit than the one it has installed. The following allows us to issue a standard library override (Requires Quorum Studio 4.0 or above):

1. Open the quorum compiler to the current version (Quorum-10 branch)
1. Make any changes to the standard library we want.
1. Call RunLibrary.quorum by doing "Set As Main" and running the project. This regenerates everything, so can take time.
1. Close Quorum Studio.
1. Open Quorum Studio's options file. This is usually located at
   1. Mac: /Users/myusername/Library/Application Support/Quorum Studio/Options.json
   1. PC: type %appdata% into explorer and find QuorumStudio/Options.json
1. Change the JSON, to the following two lines:

```
Override Standard Library": true,
"Library": "/Users/myusername/Repositories/quorum-language/Quorum/Library",
```

The actual library location is wherever our Quorum installation lives. We can then restart Quorum Studio and it will use our custom version of the standard library. Note that generally, overriding the standard library is only necessary if we are adjusting the standard library. For most users, it is not necessary. 
