# Project Title
 cos-course-planner
 
An app that helps you plan your schedule for the Computer Science major to graduate on time with no worries.

## Project description

A console application in Java, which has information about the required COS courses offered at The American University in Bulgaria. The user will input their standing and the courses they have already taken. The program will make a schedule for the remaining semesters based on which courses have prerequisite courses. The program will take into account the max number of courses that can be taken per semester. Additionally, if there is not a suitable way to take all remaining courses from the major by the desired graduation time, the application will let the user know they cannot graduate with the COS major.
### Technologies used
Java
### Challenges
### Future features

## How to install and run the project?
First, download the project folder. 

Then, make sure you have installed JavaFX from this link:
https://gluonhq.com/products/javafx/

Add the VM option as shown here:
https://openjfx.io/openjfx-docs/

If you are using IntelliJ IDEA, go to File -> Project Structure -> Modules. Make all modules that may be with runtime scope to compile scope. It seems that the project needs that to be done every time it's run, specifically for the following modules:
eu.hansolo.fx.countries, eu.hansolo.fx.heatmap, eu.hansolo.toolboxfx, eu.hansolo.toolbox.

The file you should run is called HelloApplication.java and you can find it in:
scr/java/com/example/testscenebuilder/HelloApplication.java

## How to use the project?
Once you successfully run the project, it will guide you through the app and provide instructions on what you need to do.
The app will prompt you to check all the courses you have taken so far. Then it will prompt you to give information about the number of remaining semesters you have in the university. Finally, the output is either a well-formatted schedule for the remaining semesters or a message that says you cannot graduate in the given number of semesters.

### Credits
The project is authored by me, Anna Kukova.
For setting up JavaFX, I consulted the following :
Bro Code - JavaFX GUI Full Course ☕【𝙁𝙧𝙚𝙚】(https://www.youtube.com/watch?v=9XJicRt_FaI)
GeeksforGeeks - JavaFX Reference (https://www.geeksforgeeks.org/javafx-combobox-with-examples/)








