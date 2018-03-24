package com.git.michalszukala.view;



/**
 * Data Transfer Object for an Add Command, which stores information about Add Command 
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class AddCommandDTO extends CommandDTO {
    
    private String task;
    private String date;
    private String status;
    private String project;
    
    /**
    * Collects data to create Add Command 
    * @param command    Main command typed by the user
    * @param task       Task stored in the Task List
    * @param date       Due date of the task
    * @param status     Status of the task
    * @param project    Name of the project the task belongs to 
    */
    public AddCommandDTO(String command, String task, String date,  String status, String project) {
        super(command, "none");
        this.task = task;
        this.date = date;
        this.status = status;
        this.project = project;
    }

    /**
    * Getter for the task of the Add Command
    * 
    * @return Task stored in the Task List
    */
    public String getTask() {
        return task;
    }
    
    /**
    * Getter for the due date of the Add Command
    * 
    * @return Due date of the task
    */
    public String getDate() {
        return date;
    }

    /**
    * Getter for the status of the Add Command
    * 
    * @return Status of the task
    */
    public String getStatus() {
        return status;
    }
    
    /**
    * Getter for the project of the Add Command
    * 
    * @return Name of the project the task belongs to 
    */
    public String getProject() {
        return project;
    }
    
}
