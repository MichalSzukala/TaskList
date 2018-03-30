package com.git.michalszukala.controller;


import com.git.michalszukala.IO.InputOutputToFile;
import com.git.michalszukala.model.ListOfTasks;
import com.git.michalszukala.view.CommandDTO;
import com.git.michalszukala.model.Task;
import com.git.michalszukala.view.UserOutput;

import java.util.Date;



/**
 * Is responsible for receiving commands typed by the user and redirecting them to proper classes for processing
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class Controller {
    
    
    private ListOfTasks listOfTasks;
    private InputOutputToFile inputOutput;
    private UserOutput userOutput;
   
    
    /**
    * Creates List of Tasks which will contain all of the tasks
    * Creates UserOutput object which is displaying all the output on the screen
    * Creates InputOutputToFile which is responsible for saving and loading data
    */
    public Controller(){
        listOfTasks = new ListOfTasks();
        inputOutput = new InputOutputToFile(this);
        userOutput = new UserOutput(this);
        
    }
    
   
    /**
     * Save list of tasks to file
     * @param nameOfFile    Name of the file the user want to save the list
     */
    public void saveListToFile(String nameOfFile){
        inputOutput.saveToFile(nameOfFile);
    }
    
    /**
     * Loads list of tasks from file
     * @param nameOfFile    Name of the file from where user want to load the list
     */
    public void loadListToFile(String nameOfFile){
        listOfTasks = new ListOfTasks();
        inputOutput.readFromFile(nameOfFile);
    }
    
   /**
    * Adds task to the list
    * @param command    CommandDTO use for moving data between classes
    */
    public void addTaskToList(CommandDTO command){
        Task task = listOfTasks.convertCommandDTOToTask(command);
        listOfTasks.addTaskToList(task);
    }
 
    /**
    * Adds task to the list
    * @param task    Task will be stored in the list of tasks
    */
    public void addTaskToList(Task task){
        listOfTasks.addTaskToList(task);
    }

    /**
     * Provide information about size of the task's list
     * @return Size of the task's list
     */
    public int sizeOfTaskList(){
        return listOfTasks.sizeOfTaskList();               
    }
    
    /**
     * Edits the task
     * @param index     Index of task which will be edited
     * @param userEditMessage New input that will edit the old one
     */
    public void editTask(int index, String userEditMessage){
        listOfTasks.editTask(index, userEditMessage );
    }
    
    /**
     * Edit status of the task
     * @param index     Index of task which will be edited
     * @param userEditMessage New input that will edit the old one
     */
    public void editStatus(int index, String userEditMessage){
        listOfTasks.editStatus(index, userEditMessage );
    }
    
    /**
     * Edit due date of the task
     * @param index     Index of task which will be edited
     * @param userEditMessage New date that will edit the old one
     */
    public void editDueDate (int index, Date userEditMessage){
        listOfTasks.editDueDate(index, userEditMessage );
    }
    
    /**
     * Edit project of the task
     * @param index     Index of task which will be edited
     * @param userEditMessage New input that will edit the old one
     */
    public void editProject(int index, String userEditMessage){
        listOfTasks.editProject(index, userEditMessage );
    }
   
    /**
     * Remove task from the list of tasks
     * @param index Index of task which will be removed
     */
    public void removeTask(int index){
       listOfTasks.removeTask(index);
    }
   
    /**
     * Gets single task from the list
     * @param index Index of task which will be received
     * @return Task from the list of tasks
     */
    public Task getTaskFromList(int index){
       return listOfTasks.getTaskFromList(index);
    }
    
    /**
     * Sorts list of tasks according to the due date
     */
    public void sortTasksAccordingToDate(){
        listOfTasks.sortTasksAccordingToDate();
    }
    
    /**
     * Prints the message if file is not found
     */
    public void printNoFileMessage(){
        userOutput.printNoFileMessage();
    }
   
   /**
    * Checks if the task have "important" status
    * @param index  Index of task which will be checked
    * @return True if task is important
    */
    public boolean isTaskImportant(int index){
       return listOfTasks.isTaskImportant(index);
   }
    
   
   /**
    * Checks if the task due date is today
    * @param index  Index of task which will be checked
    * @return True if task is due today
    */
    public boolean isTaskUrgent(int index){
       return listOfTasks.isTaskUrgent(index);
   }
   
    /**
    * Checks if the task belong to some project
    * @param index  Index of task which will be checked
    * @param project Project name which is verified 
    * @return True if task is belonging to some project
    */
    public boolean isProjectInTask(String project, int index){
        return listOfTasks.isProjectInTask(project, index);    
    }
    /**
     * Marks status of the task as "done"
     * @param index Index of task which will be checked
     */
    public void markTaskAsDone(int index){
        listOfTasks.markTaskAsDone(index);
    }
    
    /**
     * Prints message that list of tasks was loaded successfully
     */
    public void loadingListWasSuccess(){
        userOutput.loadingListWasSuccess();
    }
}

