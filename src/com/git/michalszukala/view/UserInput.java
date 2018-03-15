package com.git.michalszukala.view;

import com.git.michalszukala.controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Receives and validates user input
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class UserInput {
    
    private Controller controller;
    private UserOutput output;
    
    /**
    * Creates Controller object which is delegating all the tasks to proper classes
    * Creates UserOutput object which is displaying all the output on the screen
    */
    public UserInput(){
        controller = new Controller();
        output = new UserOutput();
    }
    
    /**
    * Sends request to UserOutput class to create GUI, and starts asking user for input
    */
    public void startProgram(){
        output.createDisplay();
        askForInput();
    }
    
    /**
    * Asks user for input until user will not finish program by typing "exit"
    */
    public void askForInput() 
    {              
        boolean finished = false;
        String command;
        
        while (! finished) {
            output.askForInput();
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine();
            finished = interpreteCommand(command);
        }
        
        System.out.println("See you later");
        System.exit(0);
    }
    
    /**
    * Interprets and validates command received from user and follow the command order
    * 
    * @param command    The command received from user
    */
    private boolean interpreteCommand(String command){
        
        String[] fullCommand = processCommand(command);
        boolean shouldExitProgram = false; 
        
        switch(getCommand(fullCommand)){
                
            case "save":
                controller.saveList();
                break;
                
            case "load":
                controller.loadList();
                break;
                
            case "help":
                printAllCommands();
                break;    
                
            case "exit":
                shouldExitProgram = true;
                break;    
                
            case "add":
                addTaskToTaskList(fullCommand);
                break;
                
            case "edit":
                break;
                
            case "remove":
                removeTaskFromList(fullCommand);
                break;
                
            case "done":
                markTaskAsDone(fullCommand);
                break;
                
            case "print":
                printListOfTasks(fullCommand);
                break;

            default:
                output.printErrorMessage();
        }
        return shouldExitProgram;
    }
    
    
    private String getCommand(String[] fullCommand){
        return fullCommand[0];
    }
    
    
    
    /**
    * Cleans the command from extra white spaces and upper cases
    * Turn the command to array for later processes
    * 
    * @param command    The command received from user
    * @return Separated command received from user 
    */
    private String[] processCommand(String command){
        command = command.trim().toLowerCase();
        String[] splitedCommand = command.split("\\s+");
        return splitedCommand;
    }
    
    
    /**
    * User types "help" and menu with all of the commands is printed
    */
    private void printAllCommands(){
        output.fileManipulationCommands();
        output.taskManipulationCommands();
    }
    
    
    /**
    * Sends "add" command typed by user to the Controller
    * 
    * @param fullCommand    The command received from user divided into pieces
    */
    private void addTaskToTaskList(String[] fullCommand){
        if(validateAddCommand(fullCommand)){
            TaskDTO task = createTask(fullCommand);
            controller.addTask(task);        
        }else{
            output.printErrorMessage();
        }  
    }
    
    
    private boolean validateAddCommand(String[] fullCommand){
        boolean test = false;
        if(checkingLengthOfCommand(fullCommand, numberOfOptionsForAddCommand())){
            String date = fullCommand[positionOfDateInAddCommand()];
            if(validateDate(date)){
                test = true;
            }
        }
        return test;  
    }
    
    /**
    * Checks if command have needed number of command's options
    * 
    * @param fullCommand            The command received from user divided into pieces
    * @param neededLengthOfCommand  Needed number of options following the command
    * @return true/false if number of options is correct
    */
    private boolean checkingLengthOfCommand(String[] fullCommand, int neededLengthOfCommand){
        if(fullCommand.length == neededLengthOfCommand){
            return true;
        }else{
            return false;
        }
    }
    
    
    private int numberOfOptionsForAddCommand(){
        return 5;
    }
    
    private int positionOfDateInAddCommand(){
        return 2;
    }
    
    
    
    private boolean validateDate(String date){
        
        boolean test = true;
        
        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        formatter.setLenient(false);
        
        
        try {
            formatter.parse(date);
        } catch (ParseException e) {
            test = false;
        }
        return test;
    }
    
    
    private Date parseDate(String dateInString){
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        Date date = null;
        
        try{
            date = formatter.parse(dateInString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return date;
    }
    
    /**
    * creates DTO object for transporting task to the list of tasks
    * 
    * @param fullCommand      The command received from user divided into pieces  
    * @return Task DTO object
    */
    private TaskDTO createTask(String[] fullCommand){
        
        String task = fullCommand[1];
        String dateInString = fullCommand[2];
        Date date = parseDate(dateInString);
        String status = fullCommand[3];
        String project = fullCommand[4];
        
        TaskDTO createdTask = new TaskDTO(task, date, status, project);
        
        return createdTask;
    }
    
    
    
    /**
    * Sends "remove" command typed by user to the Controller
    * 
    * @param fullCommand    The command received from user divided into pieces
    */
    private void removeTaskFromList(String[] fullCommand){
        if(validateCommandThatRequireNumberOfTask(fullCommand)){
            int index = Integer.parseInt(getTaskNumberFromCommand(fullCommand));
            controller.removeTask(index);
        }else{
            output.printErrorMessage();
        }
    }
    
    private String getTaskNumberFromCommand(String[] fullCommand){
        return fullCommand[1];
    }
    
    
    /**
    * Validates commands which are using number of the task as part of the command
    * 
    * @param fullCommand    The command received from user divided into pieces
    * @return true/false of validation
    */
    private boolean validateCommandThatRequireNumberOfTask(String[] fullCommand){
        boolean test = false;
        if(checkingLengthOfCommand(fullCommand, sizeOfCommandUsingTaskNumber())){
            String taskNumberInString = fullCommand[postionOfTaskNumberInCommandUsingTaskNumber()];
            if(validateTaskNumber(taskNumberInString)){
                int taskNumber = Integer.parseInt(taskNumberInString);
                if(taskNumber < controller.sizeOfTaskList()){
                    test = true;
                }
            }
        }
        return test;  
    }
    
    
    private boolean validateTaskNumber(String taskNumberInString){
        boolean test = true;

        try {
            Integer.parseInt(taskNumberInString);
        } catch (NumberFormatException e) {
            test = false;
        }
        
        return test;
    }    
    
    
    
    private int sizeOfCommandUsingTaskNumber(){
        return 2;
    }
    private int postionOfTaskNumberInCommandUsingTaskNumber(){
        return 1;
    }
    
    
    /**
    * Sends "done" command typed by user to the Controller
    * 
    * @param fullCommand    The command received from user divided into pieces
    */
    
    private void markTaskAsDone(String[] fullCommand){
        if(validateCommandThatRequireNumberOfTask(fullCommand)){
            int index = Integer.parseInt(getTaskNumberFromCommand(fullCommand));
            controller.markTaskAsDone(index);
        }else{
            output.printErrorMessage();
        }
    }
    
    private void printListOfTasks(String[] fullCommand){}
    
    
    
    
}



