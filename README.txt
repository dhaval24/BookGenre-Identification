Steps to run the program:
    
    Method 1 (Recommended): Execute using the Executable JAR file from Command Line.
        1. Navigate to the directory where JAR file is located inside the project. The location of JAR file is ExtractedFolderName\out\artifacts\BookBub_jar
        2. Execute the following command: 
            java -jar Bookbub.jar "absoulte path BookJSON.txt" "absolute path keywordsfile.csv" "output file absolute path" "output_file.txt absolute path"
        3. You will find the answer in the output file mentioned.

        This method prevents you from taking tedious steps to import necessary dependency to run the program. Just execute the JAR and Voila!

    Method 2 :
        1. Extract the zip file on the desired location.
        2. Add the jar file json-simple-1.1.1 to the class path. (You can download this JAR from this link : https://code.google.com/archive/p/json-simple/downloads)
        3. Navigate to the src folder inside the project directory via termial/Command line.
        4. Compile the file InputParser.java using command javac InputParser.java fileName1 fileName2
        5. Execute the file InputParser.java using command java InputParser
        6. You will find the output inside the data folder in project directory in the file name Output.txt
    
    Note: This project has been created in Intellij Idea Ultimate 2016 with Java 8 SDK and uses many features of Java 8 including lambdas and <> operators.
    Please update the JDK version to 8.0 and above to execute this project.
    This project also contains a dependency JAR file called simple-json-1.1.1 which is required for successful execution of the project. Link
    to download the JAR file is provided above.


Interesting points noted:
    
    This program generates the following ouput for the sample file linked in the problem description:
        Hunger Games

        sci-fi, 16
        action, 15

        Infinite Jest

        literary fiction, 14

    This ouput should be the actual answer according to the problem description because the sample_csv.txt file has following keywords associated with Sci-fi : - {distant future, dystopian}
    whereas the expected output in the problem description is different as it produces it accoriding to the keywords sample provided in the description which is different from the one attached as the link.


Tradeoffs and Edgecases:

    1. In order to reduce the time complexity additional space was required in terms on hashmap member variables inside the book class. Therefore there is more data redundancy.
    2. This project was developed assuming in mind that if there is a bigram keyword associated to a genre there would not be unigram keywords of that bigram associated with particular genre. eg (action : distant future, action : distant, action : future) this type of situation is not possible and would be difficult to handle.

Approximate time taken to complete the test - 2:45 Minutes (Includes time taken to read the description)

What could be done better if provided more time:
 
    1. I can encorporate Unit test cases to ensure correctness of the program
    2. Can come up with a better design which can provide results in better space and time complexity.
    3. Provided more data a better way would be to take a machine learning approach and apply multi-class classification models which can accurately predict the genre.
    4. Rather than evaluating only unigrams and bi-grams we can extend the solution to incorporate n-grams according to the need of the hour.