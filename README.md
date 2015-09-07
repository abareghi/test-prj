# test-prj
- mvn clean package to build the project
    - It will create an executable fat jar in target folder
    - This executable takes one parameter and pass it to a predefined Rest api.
    - It will save the result in a csv file with predefined name in the same folder of executing program. 
    - The program will overwrite an existing file.
    - It will also logs to the stdout and the file "output.log" in the same folder of executing program.