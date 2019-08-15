# `AccessibilityManagerWindows`

This is the JNI library for the `AccessibilityManager` plugin on Windows. It implements UI Automation providers for the Quorum controls.

## Building

This library is built with [CMake](https://cmake.org/). It requires CMake version 3.15 or later.

*TODO*: The build process doesn't currently copy the DLL into its final destination folders, as the old Visual Studio project file did. This should be fixed at some point.

To build with Visual Studio, assuming CMake is on your PATH, first run the following in a command prompt, in the same directory as this file:

```
mkdir build
cd build
cmake ..\src
```

Then in Visual Studio, open the `AccessibilityManagerWindows.sln` file in the new build directory, choose your build configuration, and build the solution.

An alternative for command-line fans is to use [Ninja](https://ninja-build.org/). For this to work, you need CMake, Ninja, and the Visual C++ toolchain on your path. The latter can be done by running the `vcvars64.bat` script included with Visual Studio. With all of that set up, run the following from the directory with this file:

```
mkdir build
cd build
cmake -G Ninja -DCMAKE_BUILD_TYPE=(type) ..\src
ninja
```

where `(type)` should be either `Debug` or `Release`.

In either case, whenever you make a change to `src/CMakeLists.txt`, re-run CMake in the build directory before rebuilding, as follows:

```
cmake .
```
