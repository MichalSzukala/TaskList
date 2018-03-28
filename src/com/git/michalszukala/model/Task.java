package com.git.michalszukala.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Task object which stores information about Task 
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class Task implements Comparable<Task>, Serializable{
    
    private String taskTitle;
    private Date dueDate;
    private String status;
    private String project;
    
    /**
    * Collects all the data needed to create Task 
    * @param task       Message of the taskTitle
    * @param dueDate    Due date of the taskTitle
    * @param status     Status of the taskTitle
    * @param project    Project name where the taskTitle is belonging
    */
    public Task(String task, Date dueDate, String status, String project){
        this.taskTitle = task;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }
    
    /**
    * Getter of the taskTitle
    * 
    * @return Task from the Task List
    */
    public String getTask(){
        return taskTitle;
    }
    
    /**
    * Getter for the due date of the taskTitle
    * 
    * @return Due date of the taskTitle
    */
    public Date getDueDate(){
        return dueDate;
    }
    
    /**
    * Getter for the status of the taskTitle
    * 
    * @return Status of the taskTitle
    */
    public String getStatus(){
        return status;
    }
    
    /**
    * Getter for the project name where the taskTitle is belonging
    * 
    * @return Project name of the taskTitle
    */
    public String getProject(){
        return project;
    }
    
    
    /**
    * Setter for the status of the project 
    * 
    * @param status     Status of the taskDTO object  
    */
    public void setStatus(String status){
        this.status = status;
    }
    
    /**
    * Setter for the taskTitle  
    * 
    * @param task     Task of the taskDTO object 
    */
    public void setTask(String task){
        this.taskTitle = task;
    }
    
    /**
    * Setter for the due date 
    * 
    * @param dueDate     Due date of the taskDTO object 
    */
    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }
    
    /**
    * Setter for the project 
    * 
    * @param project     Project the taskTitle is belonging to
    */
    public void setProject(String project){
        this.project = project;
    }
    
    
    @Override
    public int compareTo(Task object) {
        return this.dueDate.compareTo(object.getDueDate());
    }
}
