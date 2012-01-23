@echo off

SET QUORUM_INSTALLATION_PATH=C:\Users\jeff\Documents\quorum\trunk\quorum\dist

rem Run Quorum with all of our command line arguments

java -jar "%QUORUM_INSTALLATION_PATH%\Quorum.jar" %*