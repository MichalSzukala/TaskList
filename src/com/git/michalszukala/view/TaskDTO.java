package com.git.michalszukala.view;

import java.util.Date;

/**
 * Data Transfer Object for a Task, which stores information about Task 
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class TaskDTO {
    
    private String task;
    private Date dueDate;
    private String status;
    private String project;
    
    /**
    * Collects all the data needed to create Task 
    * @param task       Message of the task
    * @param dueDate    Due date of the task
    * @param status     Status of the task
    * @param project    Project name where the task is belonging
    */
    public TaskDTO(String task, Date dueDate, String status, String project){
        this.task = task;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }
    
    /**
    * Getter of the task
    * 
    * @return Task from the Task List
    */
    public String getTask(){
        return task;
    }
    
    /**
    * Getter for the due date of the task
    * 
    * @return Due date of the task
    */
    public Date getDueDate(){
        return dueDate;
    }
    
    /**
    * Getter for the status of the task
    * 
    * @return Status of the task
    */
    public String getStatus(){
        return status;
    }
    
    /**
    * Getter for the project name where the task is belonging
    * 
    * @return Project name of the task
    */
    public String getProject(){
        return project;
    }
    
    
    /**
    * Setter for the status of the project 
    * 
    * @param status     Status of the task 
    */
    public void setStatus(String status){
        this.status = status;
    }
    
}
