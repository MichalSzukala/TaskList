package com.git.michalszukala.view;

/**
 * Data Transfer Object for a Command, which stores information about Command 
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class CommandDTO {
    private String command;
    private String option;
    
    
    /**
    * Collects main command and options for command and create command 
    * @param command   Main command typed by the user
    * @param option   Project name where the task is belonging
    */
    public CommandDTO(String command, String option){
        this.command = command;
        this.option = option;
    }
    
    /**
    * Collects main command and create command, option is getting "none" value
    * @param command   Main command typed by the user
    */
    public CommandDTO(String command){
        this.command = command;
        this.option = "none";
    }
    
    /**
    * Getter for the main command
    * 
    * @return Main command
    */
    public String getCommand() {
        return command;
    }
    
    /**
    * Getter for the option of the command
    * 
    * @return Option of the command
    */
    public String getOption() {
        return option;
    }
    
}
