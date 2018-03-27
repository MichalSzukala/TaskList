package com.git.michalszukala.startUp;


import com.git.michalszukala.view.UserInput;


/**
 * TaskToRememberMain program creates list of tasks which user does not want to forget
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class TaskToRememberMain {

    /**
     * Creates GUI and starts the program
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        UserInput graphicalInterface = new UserInput();
        graphicalInterface.startGUI();

    }
}
