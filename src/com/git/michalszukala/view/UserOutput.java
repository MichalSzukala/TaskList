package com.git.michalszukala.view;

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
        
    }
    
    
    void fileManipulationCommands(){
       
    }
    
   
    
    void taskManipulationCommands(){
       
    }
    
    void askForInput(){
        System.out.print("\nGive me a order: ");
    }
    
    void printErrorMessage(){
        System.out.println("Something is Wrong with Your Command!! Try again");
    }
    
}
