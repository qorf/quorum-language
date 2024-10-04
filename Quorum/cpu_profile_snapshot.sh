#!/bin/sh
java -XstartOnFirstThread -agentpath:/Applications/YourKit-Java-Profiler-2022.9.app/Contents/Resources/bin/mac/libyjpagent.dylib=sampling,onexit=snapshot -jar Run/Default.jar
