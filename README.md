# COS Course Scheduler
 
An app that helps you plan your schedule for the Computer Science major to graduate on time with no worries.

## Project description

A console application in Java, which has information about the required and elective COS courses offered at The American University in Bulgaria. The user will input their standing and the courses they have already taken. The program will make a schedule for the remaining semesters based on which courses have prerequisite courses. The program will take into account the max number of courses that can be taken per semester, as well as the max number of electives from each group of electives. Additionally, if there is not a suitable way to take all remaining courses from the major by the desired graduation time, the application will let the user know they cannot graduate with the COS major.

### Technologies used
Java Swing, Maven, YAML

### Challenges
The project was started using JavaFX, however there were multiple issues with the library such as the app not being able to find the javafx.swing module while successfully importing other modules from the same lib folder, and requesting that certain modules be reset from runtime to compile every time the project is run. I tried most solutions available online, but javafx.swing was still an issue. That was a disappointment since the UI part of the project had already been started and I had all the scenes I needed in the SceneBuilder, for which I also used css. I had spent too much time trying to fix the issues with JavaFX and I needed to start a new project using Swing because the deadline was fast approaching. Overall, I had no issues with Swing and Maven
### Future features
The project currently deals with the COS major only, which is also the reason why non-COS electives don't have prerequisites in the app, even if they have them in the AUBG academic catalog. However, the project can be scaled to a web app to deal with all majors and let students schedule courses for both of their majors and even minors, if they have any.

## How to install and run the project?
First, download and unarchive the project folder. 
Open the app whuch is located in: src\main\java\com\example\app\App.java
Depending on your Jalava language version, you might have to switch to an older version, Java 11, because of certain static methods for array manipulation that may not be used in newer versions of Java. If you are using an IDE such as IntelliJ, the editor will give you a hint to do that.
Finally, run the App.java file.


## How to use the project?
Once you successfully run the project, it will guide you through the app and provide instructions on what you need to do.
The app will prompt you to check all the courses you have taken so far. It then lets you choose your future courses and you can also save that selection. Then it will prompt you to give information about the number of remaining semesters you have in the university. Finally, the output is a schedule for the remaining semesters. If there is not a suitable way for you to take all the courses you want in the remaining semesters in university, the app will let you know that you cannot graduate with the COS major in the desired number of semesters.

### Credits
Project author: Anna Kukova
* Styling UI components in Swing: [javaTPoint Java Swing](https://www.javatpoint.com/java-swing), [CodeJava.net JCheckBox tutorial](https://www.codejava.net/java-se/swing/jcheckbox-basic-tutorial-and-examples)
* Using yaml to store information about the courses and their prerequisites: [CircleCI - What is yaml](https://circleci.com/blog/what-is-yaml-a-beginner-s-guide/?psafe_param=1&utm_source=google&utm_medium=sem&utm_campaign=sem-google-dg--emea-en-dsa-tROAS-auth-brand&utm_term=g_-_c__dsa_&utm_content=&gclid=Cj0KCQjwi46iBhDyARIsAE3nVrbC9_UamAU0IM6kJeYUlzcIdCVuwKbmb8sfBRlX-mPQiJvJffFCxbwaAuf9EALw_wcB), [Cloudbees yaml tutorial](https://www.cloudbees.com/blog/yaml-tutorial-everything-you-need-get-started)
* Creating a Maven project: [JetBrains Maven tutorial](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/creating-a-project/)








