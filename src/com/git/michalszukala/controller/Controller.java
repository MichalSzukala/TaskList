package com.git.michalszukala.controller;


import com.git.michalszukala.view.TaskDTO;


/**
 * Is responsible for receiving commands typed by the user and redirecting them to proper classes for processing
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class Controller {

    
    
    public Controller(){

    }
    
    

    public void saveList(){
        System.out.println("LIST SAVED");
    }
    
    public void loadList(){
        System.out.println("LIST LOADED");
    }
    
   
    
    public void addTask(TaskDTO task){
        System.out.println("TASK ADDED");
    }
    
    
   public void showAllTasks(){
       System.out.println("PRINT ALL TASKS");
       
   }
   
   public int sizeOfTaskList(){
        return 5;         
    }
   
   public void removeTask(int index){
       System.out.println("TASK REMOVED");
   }
   
   
}

