# COS Course Scheduler
 
An app that helps you plan your schedule for the Computer Science major to graduate on time with no worries.

## Project description

A GUI application in Java, which has information about the required and elective COS courses offered at The American University in Bulgaria. The user will input the courses they have already taken and which courses they want to take. The program will make a schedule for the remaining semesters based on which courses have prerequisite courses. The program will take into account the max number of courses that can be taken per semester, as well as the max number of electives from each group of electives.

### Technologies used
Java Swing, Maven, YAML

### Challenges
The project was started using JavaFX, however there were multiple issues with the library such as the app not being able to find the javafx.swing module while successfully importing other modules from the same lib folder, and requesting that certain modules be reset from runtime to compile every time the project is run. I tried most solutions available online, but javafx.swing was still an issue. That was a disappointment since the UI part of the project had already been started and I had all the scenes I needed in the SceneBuilder, for which I also used css. I had spent too much time trying to fix the issues with JavaFX and I had to start a new project using Swing. Overall, from then on I had no issues with Swing and Maven.
The logic of the app was also quite challenging since the courses may have multiple prerequisites, which can't be taken in the same semester.
### Future features
The project currently deals with the COS major only, which is also the reason why non-COS electives don't have prerequisites in the app, even if they have them in the AUBG academic catalog. However, the project can be scaled to a web app to deal with all majors and let students schedule courses for both of their majors and even minors, if they have any.

## How to install and run the project?
First, download and unarchive the project folder. 
Open the app which is located in: src\main\java\com\example\app\App.java
Depending on your Java language version, you might have to switch to an older version, Java 11, because of certain static methods for array manipulation that may not be used in newer versions of Java. If you are using an IDE such as IntelliJ, the editor will give you a hint to do that.
Finally, run the App.java file.


## How to use the project?
Once you successfully run the project, it will guide you through the app and provide instructions on what you need to do.
The app will prompt you to check all the courses you have taken so far. It then lets you choose your future courses and you can also save that selection. Then it will prompt you to give information about the number of remaining semesters you have in the university. Finally, the output is a schedule for the remaining semesters.

### Credits
Project author: Anna Kukova
* Styling UI components in Swing: [javaTPoint Java Swing](https://www.javatpoint.com/java-swing), [CodeJava.net JCheckBox tutorial](https://www.codejava.net/java-se/swing/jcheckbox-basic-tutorial-and-examples)
* Using yaml to store information about the courses and their prerequisites: [CircleCI - What is yaml](https://circleci.com/blog/what-is-yaml-a-beginner-s-guide/?psafe_param=1&utm_source=google&utm_medium=sem&utm_campaign=sem-google-dg--emea-en-dsa-tROAS-auth-brand&utm_term=g_-_c__dsa_&utm_content=&gclid=Cj0KCQjwi46iBhDyARIsAE3nVrbC9_UamAU0IM6kJeYUlzcIdCVuwKbmb8sfBRlX-mPQiJvJffFCxbwaAuf9EALw_wcB), [Cloudbees yaml tutorial](https://www.cloudbees.com/blog/yaml-tutorial-everything-you-need-get-started)
* Creating a Maven project: [JetBrains Maven tutorial](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/creating-a-project/)








