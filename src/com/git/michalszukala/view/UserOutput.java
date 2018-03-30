package com.git.michalszukala.view;

import com.git.michalszukala.model.Task;
import com.git.michalszukala.controller.Controller;


/**
 * Prints all the messages and informations for the user 
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class UserOutput {
    
    private Controller controller;
    
    /**
    * Creates Controller object which will deliver all the tasks to proper classes
    * @param controller Controller responsible to delegating all the tasks to proper classes
    */
    public UserOutput(Controller controller){
        this.controller = controller;
    }
    
    /**
    * Display all the starting messages
    */
     public void createDisplay(){
        startMessage();
        fileManipulationCommands();
        taskManipulationCommands();
    }
    
    
    private void startMessage(){ 
        printStars();
        String formatString = String.format("%70s", "LIST OF TASKS TO DO by Michal Szukala");
        System.out.printf(formatString);
        printStars();
        
    }
    
    private void printStars(){
        System.out.println("\n**********************************************************************************************************");
    }
    
    /**
    * Display all commands responsible for file manipulation
    */
    public void fileManipulationCommands(){
        System.out.println("\n*******************************************List of commands***********************************************\n");
        
        String saveString = String.format("\n%-40s%s", "save[file name]","- Save to the File");
        System.out.printf(saveString);
        
        String loadString = String.format("\n%-40s%s", "load[file name]","- Load the File");
        System.out.printf(loadString);
    
        String helpString = String.format("\n%-40s%s", "help","- Prints all the Commands");
        System.out.printf(helpString);
        
        
        String exitString = String.format("\n%-40s%s", "exit","- Exit the Program");
        System.out.printf(exitString);
    }
    
   
    /**
    * Display all commands responsible for manipulating list of tasks
    */
    public void taskManipulationCommands(){
        String newTaskString = String.format("\n\n%-40s%s", "add[task][due date][status][project]","- Create New Task.  Due date is in yy/mm/dd format");
        System.out.printf(newTaskString);
        
        String editTaskString = String.format("\n%-40s%s", "edit[task number]","- Edit the Task");
        System.out.printf(editTaskString);
        
        String removeTaskString = String.format("\n%-40s%s", "remove[task number]","- Remove the Task");
        System.out.printf(removeTaskString);
        
        String doneTaskString = String.format("\n%-40s%s", "done[task number]","- Mark the Task as Done");
        System.out.printf(doneTaskString);
        
        String printTaskString = String.format("\n%-40s%s", "print[option]","- Print the List of Tasks.  Options choice: [-a][-d][-i][-p]\n");
        System.out.printf(printTaskString);
        
        printOptions();
        
        System.out.println("\nIt is possible to combine print options. Example: \"print -id\" will print important and urgent tasks");
        System.out.println("With the use of \"  \" it is possible to input multiple words");

        printStars();
    }
    
    
    private void printOptions(){
        String optionA = String.format("%11s%s", "[-a]","- print all the tasks\n");
        System.out.printf(optionA);
        
        String optionD = String.format("%11s%s", "[-d]","- print tasks which have due date today\n");
        System.out.printf(optionD);
        
        String optionI = String.format("%11s%s", "[-i]","- print tasks with \"important\" status\n");
        System.out.printf(optionI);
        
        String optionP = String.format("%11s%s", "[-p]","- print tasks belonging to certain project\n");
        System.out.printf(optionP);
    }
    
    /**
    * Ask user for input
    */
    public void askForInputMessage(){
        System.out.print("\nGive me an order: ");
    }
    
    /**
    * Ask user for name of the project
    */
    public void askForProject(){
        System.out.print("\nName of the Project: ");
    }
    
    /**
    * Ask user which part of the task he want to modify
    */
    public void askForOptionsForEditCommand(){
        System.out.print("\nOption's choices: [-t]task, [-d]due date, [-s]status, [-p]project, [-x]go back");
        System.out.print("\nWhat do you want to edit?: ");
    }
    
    /**
    * Ask user for information used to edit task
    */
    public void askForInputToEditTask(){
        System.out.print("\nGo ahead and type it: ");
    }
    
    /**
    * Prints Error message
    */
    public void printErrorMessage(){
        System.out.println("Something is Wrong with Your Command!! Try again");
    }
    
    /**
    * Prints no file message
    */
    public void printNoFileMessage(){
        System.out.println("The file is not existing, try different name");
    }
    
    /**
    * Prints first lines of the List of Tasks
    */
    public void printListOfTasksStartText(){
        System.out.println("\n*****************************************Your List of Tasks***********************************************\n");
        
        String listPropherties = String.format("%-7s%-40s%-15s%-15s%-15s", "Task#", "Task", "Due Date", "Status", "Project");
        System.out.printf(listPropherties + "\n");
    }
    
    /**
    * Prints single Task on the screen
    * @param task   Single task on the list of tasks
    * @param index  Index of the task
    */
    public void printSingleTask(Task task, int index){
        String taskNumber = String.format("%-7s", index);
        System.out.printf(taskNumber);
            
        String taskMessage = String.format("%-40s", task.getTask());
        System.out.printf(taskMessage);
            
        String dueDate = String.format("%-15tF", task.getDueDate());
        System.out.printf(dueDate);
            
        String taskStatus = String.format("%-15s", task.getStatus());
        System.out.printf(taskStatus);
            
        String taskProject = String.format("%-15s %n", task.getProject());
        System.out.printf( taskProject);
        
        
    }
    
    /**
    * Prints message if there is no tasks in the list of tasks
    */
    public void noTasksInList(){
        System.out.println("There is no tasks in the list\n");
    }

    /**
    * Prints all task from the list of tasks
    */
    public void printWholeListOfTasks(){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isTaskUrgent(index)){
                printSingleTask(task, index);
            }else{
                printSingleTask(task, index);
            }
        }
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    
    /**
    * Prints important tasks and tasks with due date of today from the list of tasks
    */
    public void printImportantAndUrgentTasks(){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isTaskUrgent(index) && controller.isTaskImportant(index)){
                printSingleTask(task, index);
            }   
        }
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    /**
    * Prints important tasks from the list of tasks
    */
    public void printImportantTasks(){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isTaskImportant(index)){
                printSingleTask(task, index);
            }   
        }
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    /**
    * Prints tasks which have due date of today
    */
    public void printUrgentTasks(){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isTaskUrgent(index)){
                printSingleTask(task, index);
            }   
        }
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    /**
    * Prints tasks belonging to specific project
    * @param project   Name of the project
    */
    public void printListOfTasksOfProject(String project){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isProjectInTask(project, index)){
                printSingleTask(task, index);
            }
        }
        
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    /**
    * Prints important tasks and tasks having due date of today
    * @param project   Name of the project
    */
    public void printImportantAndUrgentTasksOfProject(String project){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isProjectInTask(project, index) && controller.isTaskUrgent(index) && controller.isTaskImportant(index)){
                printSingleTask(task, index);
            }
        }
        
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    /**
    * Prints important tasks and tasks belonging to specific project from the list of tasks
    * @param project   Name of the project
    */
    public void printImportantTasksOfProject(String project){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isProjectInTask(project, index) && controller.isTaskImportant(index)){
                printSingleTask(task, index);
            }
        }
        
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    /**
    * Prints tasks with due date of today and tasks belonging to specific project from the list of tasks
    * @param project   Name of the project
    */
    public void printUrgentTasksOfProject(String project){
        printListOfTasksStartText();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            Task task = controller.getTaskFromList(index);
            if(controller.isProjectInTask(project, index) && controller.isTaskUrgent(index)){
                printSingleTask(task, index);
            }
        }
        
        if(controller.sizeOfTaskList() == 0){
            noTasksInList();
        }
    }
    
    /**
     * Prints message that list of tasks was loaded successfully
     */
    public void loadingListWasSuccess(){
        System.out.println("List of Tasks is loaded");
    }

}
