# The Quorum Programming Language

Quorum is an evidence-based programming language. Its design is shaped by
studies of how people of many ages and experience levels learn and use it,
and its libraries are built to be accessible, including to people with
disabilities.

## Learning and using Quorum

Everything for writing programs in Quorum is on
[quorumlanguage.com](https://quorumlanguage.com):

- **[Download](https://quorumlanguage.com/download.html):** Quorum Studio,
  the development environment.
- **[Learn](https://quorumlanguage.com/learn.html) and
  [Reference](https://quorumlanguage.com/reference.html):** tutorials for the
  language and its libraries.
- **[Libraries](https://quorumlanguage.com/libraries.html):** every class in
  the standard library.
- **[Release notes](https://quorumlanguage.com/release.html)** and
  **[Evidence](https://quorumlanguage.com/evidence.html)** (the research
  behind the language).

Quorum programs compile to Java bytecode, JavaScript or Python. Not every
library is available for every target.

## Using Quorum from the console

Quorum's own installer, for Mac and Windows, installs the compiler with a
`quorum` command. It carries its own Java, so nothing else needs to be
installed. After installing, open a new terminal window:

- `quorum Hello.quorum` compiles `Hello.quorum` into `Run/Default.jar`.
- `quorum -run Hello.quorum` compiles it and, if it built, runs it.
- `quorum -help` lists the other flags, such as `-name`, `-javascript` and
  `-python`.

The first compile in a folder copies the compiled standard library (about
290 MB) into that folder's `Run` folder.

- **Mac:** Quorum installs into `/Library/Quorum`, and every user's PATH
  gets `/Library/Quorum/bin` (through `/etc/paths.d/quorum`) in zsh and
  bash. Shells that don't read `/etc/paths.d`, such as fish, need that
  folder added by hand. macOS has no uninstaller for it: to remove Quorum,
  delete `/Library/Quorum` and `/etc/paths.d/quorum`.
- **Windows:** Quorum installs into `Program Files\Quorum`, and its `bin`
  folder is added to the system PATH. Uninstall it from Windows' list of
  installed apps, which also removes it from the PATH.

## Repositories

Quorum is split across these repositories. Clone them into the same folder,
next to each other.

- **[quorum-language](https://github.com/qorf/quorum-language)** (this one):
  the compiler and the standard library.
- **[quorum-studio](https://github.com/qorf/quorum-studio)**: Quorum Studio,
  the development environment, and InstallerBuilder, which makes Quorum
  Studio's installers and Quorum's.
- **[quorum-natives](https://github.com/qorf/quorum-natives)**: the native
  code the standard library uses on each platform.
- **[quorum-test](https://github.com/qorf/quorum-test)**: the compiler's
  tests.

## Building Quorum from source

You need [Quorum Studio](https://quorumlanguage.com/download.html) and Git.

1. Clone the repository:
   `git clone https://github.com/qorf/quorum-language.git`
2. In Quorum Studio, open the project in `quorum-language/Quorum`.
3. Build the project.

## Changing the standard library

The standard library's source is in `quorum-language/Quorum/Library/Standard`.
To try your changes in Quorum Studio, rebuild the library and point Studio at it.

**Before you start:** rebuilding the library also builds its Java plugins with
gradle, which needs the Android SDK with API 34 or later. If it isn't
installed, the build stops and lists what to install.

1. Make your changes in `Quorum/Library/Standard`.
2. Run the Quorum project. It starts at `SourceCode/RunLibrary.quorum`, which
   rebuilds the library into `Quorum/Library/Compiled`. This takes a while.
3. Open **Edit > Edit Quorum Studio Properties** (Control+Alt+P; on a Mac,
   Control+Option+P) and go to the **General** tab.
4. In the **Library** card, check **Override Library**, and set
   **Override Library Path** to the full path of your `Library` folder, the
   one that holds `Standard` and `Compiled`. For example:
   - Mac: `/Users/yourname/Repositories/quorum-language/Quorum/Library`
   - Windows: `C:\Users\yourname\Repositories\quorum-language\Quorum\Library`
5. Select **Ok**, then restart Quorum Studio. It reads the library only when
   it starts.

If the path isn't an existing folder, Studio turns the override off without
saying so. If your changes don't appear, reopen the dialog and check that
**Override Library** is still checked.

To go back to the library that came with Quorum Studio, uncheck
**Override Library** and restart.

## Testing

The compiler's tests are in [quorum-test](https://github.com/qorf/quorum-test),
which goes next to quorum-language. Open its `QuorumTest` project in Quorum
Studio and run it. After it's built, you can also run `run.sh` (macOS and
Linux) or `run.bat` (Windows) in its `QuorumTest` folder. It tests bytecode,
JavaScript and Python, and downloads the Java and Node.js it needs the first
time.
