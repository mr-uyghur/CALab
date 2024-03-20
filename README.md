# CS 151 Team Project: Cellular Automata Lab

This project depends on minimum Java 17, since it uses features such as switch-expressions. The IntelliJ project is configured to load a SDK named "17", you may need to go to Project Structure > Project > SDK and set that to your locally installed JDK.

The MVC framework lives in the package `mvc`. The test case for MVC is pulled directly from the [website](http://www.cs.sjsu.edu/faculty/pearce/modules/lectures/ood4/mvc/stoplight3/index.htm), and lives in `stoplight` (code kept in the same IDEA module rather than separate for the sake of simplicity). The CALab framework based on MVC lives in `calab`. The game of life customization lives in `life`. There are two main methods, in `stoplight.StoplightPanel` and `life.LifePanel` which launches the stoplight app and the game of life app respectively.

## A note of MVC terminology
Per assignment requirements, the classes suffixed "-Panel" and has the main method are the controllers of MVC. Model and View are named normally. This MVC utilizes the Java Swing's string-based action command system heavily ("edit commands" here), rather than inventing our own commands like other MVC frameworks.
