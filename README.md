# The Quorum Programming Language README #

The Quorum programming language is a general purpose language designed for several purposes. First, we regularly run experiments with people at various age and experience ranges, investigating ways to make the language easier to use. Evidence gathered from these studies is filtered back into the design, making quorum an "evidence-based" programming language. Second, as our team is interested in issues of equity for all people, perhaps especially people with disabilities, we are careful to design libraries that are friendly to the broad population.

### Developing in Quorum ###

The easiest way to develop for Quorum is to use the corresponding development environment, Quorum Studio. Quorum Studio provides many of the basic features of Quorum, like compiling and running programs, out of the box. Quorum Studio can be downloaded from [The Quorum Download Page] (https://quorumlanguage.com/download.html).

### Building Quorum from Source ###

The easiest way to build Quorum is in Quorum Studio. To build it, please follow these directions:

1. Visit the Bitbucket page to get the latest source code for Quorum: https://bitbucket.org/stefika/quorum-language
1. Clone the repository using Git from the terminal. The command will look something like "git clone https://stefika@bitbucket.org/stefika/quorum-language.git"
1.Open Quorum Studio 3.0 or above
1. Inside of Quorum Studio, open Quorum, which is under quorum-language/Quorum
1. Build the project normally

We can also regenerate the standard library by running RunLibrary.quorum or run the test suite by running CompilerTestSuite.quorum.