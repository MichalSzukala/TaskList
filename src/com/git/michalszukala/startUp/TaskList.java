package com.git.michalszukala.startUp;


import com.git.michalszukala.view.UserInput;


/**
 * TaskList program creates list of tasks which user does not want to forget
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class TaskList {

    /**
     * Creates userInput and starts the program
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TaskList taskList = new TaskList();
        taskList.start();
    }
    
    
    private void start(){
        UserInput userInput = new UserInput();
        userInput.createGUI();
    }
}
