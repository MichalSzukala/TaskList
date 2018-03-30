package com.git.michalszukala.model;

import java.util.Date;

/**
 * Generic interface for the list of tasks
 * @author Michal Szukala
 */
public interface ListOfTasksInterface <T>{
    
   public void addTaskToList(T t);
   public void removeTask(int index);
   public void markTaskAsDone(int index);
   public void editTask(int index, String userEditMessage);
   public void editStatus(int index, String userEditMessage);
   public void editDueDate(int index, Date userEditMessage);
   public void editProject(int index, String userEditMessage);
   
    
}
