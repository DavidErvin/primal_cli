# primal_cli
Simple CLI to generate prime numbers

# Requirements
* JDK 8
* Maven 3.2+ (tested on 3.2.3, probably works on higher versions)

# Building
Execute `mvn clean package` to compile, test, and assemble the project

# Running
The primary build artifact is `target/primal_cli-1.0.0.one-jar.jar`
Execute it with the command `java -jar target/primal_cli-1.0.0.one-jar.jar [rangeStart] [rangeEnd]`
rangeStart and rangeEnd should be positive integers.
The CLI will output all prime numbers between rangeStart and rangeEnd, inclusive.
Values for rangeStart and rangeEnd which are not positive integers will produce an error.
