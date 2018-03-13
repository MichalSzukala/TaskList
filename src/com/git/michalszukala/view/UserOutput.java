package com.git.michalszukala.view;

import java.util.List;

/**
 * Prints all the messages and informations for the user 
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class UserOutput {
    
    
    /**
    * Display all the starting messages
    */
     public void createDisplay(){
        startMessage();
        fileManipulationCommands();
        taskManipulationCommands();
    }
    
    
    private void startMessage(){ 
        System.out.println("\n\n***************************************************************************************");
        String formatString = String.format("%60s", "LIST OF TASKS TO DO by Michal Szukala");
        System.out.printf(formatString);
        System.out.println("\n\n***************************************************************************************");
        
    }
    
    
    void fileManipulationCommands(){
        System.out.println("\n********************************List of commands***************************************\n");
        
        String saveString = String.format("\n%-40s%s", "save[file name]","- Save to the File");
        System.out.printf(saveString);
        
        String loadString = String.format("\n%-40s%s", "load[file name]","- Load the File");
        System.out.printf(loadString);
    
        String helpString = String.format("\n%-40s%s", "help","- Prints all the Commands");
        System.out.printf(helpString);
        
        
        String exitString = String.format("\n%-40s%s", "exit","- Exit the Program");
        System.out.printf(exitString);
    }
    
   
    
    void taskManipulationCommands(){
        String newTaskString = String.format("\n\n%-40s%s", "add[task][due date][status][project]","- Create New Task, due date is in yy/mm/dd format");
        System.out.printf(newTaskString);
        
        String editTaskString = String.format("\n%-40s%s", "edit[task number]","- Edit the Task");
        System.out.printf(editTaskString);
        
        String removeTaskString = String.format("\n%-40s%s", "remove[task number]","- Remove the Task");
        System.out.printf(removeTaskString);
        
        String doneTaskString = String.format("\n%-40s%s", "done[task number]","- Mark the Task as Done");
        System.out.printf(doneTaskString);
        
        String printTaskString = String.format("\n%-40s%s", "print[option]","- Print the List of Tasks.  Options choice: [-a][-d][-i][-p]\n");
        System.out.printf(printTaskString);
        printOptions();
        
        
        System.out.println("\n***************************************************************************************\n");
    }
    
    
    private void printOptions(){
        String optionA = String.format("%11s%s", "[-a]","- print all the tasks\n");
        System.out.printf(optionA);
        
        String optionD = String.format("%11s%s", "[-d]","- print tasks which have due date today\n");
        System.out.printf(optionD);
        
        String optionI = String.format("%11s%s", "[-i]","- print tasks with \"imortant\" status\n");
        System.out.printf(optionI);
        
        String optionP = String.format("%11s%s", "[-p]","- print tasks belonging to certain project\n");
        System.out.printf(optionP);
    }
    
    void askForInput(){
        System.out.print("\nGive me a order: ");
    }
    
    void askForProject(){
        System.out.print("\nName of the Project: ");
    }
    
    void printErrorMessage(){
        System.out.println("Something is Wrong with Your Command!! Try again");
    }
    
}
