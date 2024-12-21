# Compile the Java files into the out directory, maintaining the package structure
javac -d out *.java && 

# Run the Main class, using the output directory for classpath
java -cp out Main &&   

# Remove the compiled class files after execution is complete
rm -r out
