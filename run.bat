@echo off
REM Create the output directory if it doesn't exist
if not exist out mkdir out

REM Compile the Java files into the out directory
javac -d out *.java
if errorlevel 1 (
    echo Compilation failed.
    exit /b 1
)

REM Run the Main class, using the output directory for classpath
java -cp out Main
if errorlevel 1 (
    echo Execution failed.
    exit /b 1
)

REM Remove the compiled class files after execution is complete
rmdir /s /q out
