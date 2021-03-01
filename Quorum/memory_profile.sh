#!/bin/sh
java -XstartOnFirstThread -agentpath:/Applications/YourKit-Java-Profiler-2019.8.app/Contents/Resources/bin/mac/libyjpagent.dylib=alloc_object_counting -jar QuorumStudio.jar