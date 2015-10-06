#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-MacOSX
CND_DLIB_EXT=dylib
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/Source/BufferUtils.o \
	${OBJECTDIR}/Source/Font.o \
	${OBJECTDIR}/Source/Matrix4.o \
	${OBJECTDIR}/Source/PixelMap.o \
	${OBJECTDIR}/Source/gdx2d.o \
	${OBJECTDIR}/Source/jpgd.o \
	${OBJECTDIR}/Source/jpgd_c.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk dist/libGameEngineCPlugins.a

dist/libGameEngineCPlugins.a: ${OBJECTFILES}
	${MKDIR} -p dist
	${RM} dist/libGameEngineCPlugins.a
	${AR} -rv dist/libGameEngineCPlugins.a ${OBJECTFILES} 
	$(RANLIB) dist/libGameEngineCPlugins.a

${OBJECTDIR}/Source/BufferUtils.o: Source/BufferUtils.cpp 
	${MKDIR} -p ${OBJECTDIR}/Source
	${RM} "$@.d"
	$(COMPILE.cc) -g -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include/darwin -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include -I../freetype-2.5.5/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/Source/BufferUtils.o Source/BufferUtils.cpp

${OBJECTDIR}/Source/Font.o: Source/Font.c 
	${MKDIR} -p ${OBJECTDIR}/Source
	${RM} "$@.d"
	$(COMPILE.c) -g -I../freetype-2.5.5/include -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include/darwin -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/Source/Font.o Source/Font.c

${OBJECTDIR}/Source/Matrix4.o: Source/Matrix4.cpp 
	${MKDIR} -p ${OBJECTDIR}/Source
	${RM} "$@.d"
	$(COMPILE.cc) -g -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include/darwin -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include -I../freetype-2.5.5/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/Source/Matrix4.o Source/Matrix4.cpp

${OBJECTDIR}/Source/PixelMap.o: Source/PixelMap.cpp 
	${MKDIR} -p ${OBJECTDIR}/Source
	${RM} "$@.d"
	$(COMPILE.cc) -g -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include/darwin -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include -I../freetype-2.5.5/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/Source/PixelMap.o Source/PixelMap.cpp

${OBJECTDIR}/Source/gdx2d.o: Source/gdx2d.c 
	${MKDIR} -p ${OBJECTDIR}/Source
	${RM} "$@.d"
	$(COMPILE.c) -g -I../freetype-2.5.5/include -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include/darwin -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/Source/gdx2d.o Source/gdx2d.c

${OBJECTDIR}/Source/jpgd.o: Source/jpgd.cpp 
	${MKDIR} -p ${OBJECTDIR}/Source
	${RM} "$@.d"
	$(COMPILE.cc) -g -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include/darwin -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include -I../freetype-2.5.5/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/Source/jpgd.o Source/jpgd.cpp

${OBJECTDIR}/Source/jpgd_c.o: Source/jpgd_c.cpp 
	${MKDIR} -p ${OBJECTDIR}/Source
	${RM} "$@.d"
	$(COMPILE.cc) -g -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include/darwin -I/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/include -I../freetype-2.5.5/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/Source/jpgd_c.o Source/jpgd_c.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} dist/libGameEngineCPlugins.a

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
