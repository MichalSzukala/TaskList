package com.git.michalszukala.model;

import com.git.michalszukala.view.AddCommandDTO;
import com.git.michalszukala.view.CommandDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Contains List where all the tasks are stored
 * @author Michal Szukala
 */
public class ListOfTasks implements ListOfTasksInterface<Task>{
    
    private List<Task> allTasks;
    

    /**
    * Creates List of Tasks
    */
    public ListOfTasks(){
        allTasks = new ArrayList<>();
        
    }
    

    /**
     * Convert from DTO object to task object which is stored in the list of thasks
     * @param command   Object used for transporting command between classes
     * @return  Task which is stored in the list of tasks
     */
    public Task convertCommandDTOToTask(CommandDTO command){

        AddCommandDTO addCommand = (AddCommandDTO)command;
        String task = addCommand.getTask();
        String dateInString = addCommand.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        Date date = null;
        try{
            date = formatter.parse(dateInString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        String status = addCommand.getStatus();
        String project = addCommand.getProject();
        
        Task createdTask = new Task(task, date, status, project);
        
        return createdTask;

    }
    
    /**
    * Add task to the list
    * @param task   Task which is stored in the list of tasks
    */
    public void addTaskToList(Task task){
        allTasks.add(task);
    }

    
    /**
    * Gets task from the list
    * @return Task from the list of tasks
    */
    public Task getTaskFromList(int index){
        if(index < allTasks.size()){
            return allTasks.get(index);
        }else{
            return null;
        }
        
    }
    
    
    /**
    * Size of the list of the tasks
    * @return Size of the list of tasks
    */
    public int sizeOfTaskList(){
        return allTasks.size();         
    }
    
    /**
    * Removes task from the list
    * @param index  Index of the task which will be removed
    */
    public void removeTask(int index){
        if(index < allTasks.size()){
            allTasks.remove(index);
        }
    }
    
    /**
    * Marks task as a done task
    * @param index  Index of the task which will be marked as done
    */
    public void markTaskAsDone(int index){
        if(index < allTasks.size()){
            allTasks.get(index).setStatus("done");
        }
    }
    

    /**
    * Checks if task is important
    * @param index  Index of the task which will checked
    * @return True/false depends if the task has important status
    */
    public boolean isTaskImportant(int index){
        String status = allTasks.get(index).getStatus();
        if(status.equals("important")){
            return true;
        }else{
            return false ;
        }
    }
    
   

    /**
    * Checks if task due date is passing today
    * @param index  Index of the task which will checked
    * @return True/false depends if the task expiring today
    */
    public boolean isTaskUrgent(int index){
        Date dueDate = allTasks.get(index).getDueDate();
            if(checkIfDateIsTodaysDate(dueDate)){
                return true;
            }else{
                return false;
            }
    }
    
    
    
    private boolean checkIfDateIsTodaysDate(Date date){
        boolean test = false;
        
        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        String today = formatter.format(new Date());
        String dateInString = formatter.format(date);
        
        if(dateInString.equals(today)){
            test = true;
        }
        return test;
    }
    
    /**
    * Checks if task belongs to specific project
    * 
    * @param index  Index of the task which will checked
    * @param project Name of the project the task is belonging
    * @return True/false depends if the task belong to some project
    */
    public boolean isProjectInTask(String project, int index){
        String projectOfTask = allTasks.get(index).getProject();
            if(projectOfTask.equals(project)){
                return true;
            }else{
                return false;
            }
    }  
    
    
    
    
}
