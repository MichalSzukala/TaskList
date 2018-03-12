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
        
        switch(fullCommand[0]){
                
            case "save":
                controller.saveList();
                break;
                
            case "load":
                controller.loadList();
                break;
                
            case "exit":
                shouldExitProgram = true;
                break;    
                
            case "add":
                addCommand(fullCommand);
                break;
                
            case "print":
                controller.showAllTasks();
                break;
                
            case "remove":
                removeCommand(fullCommand);
                break;
                
            case "help":
                
                break;
                
            default:
                output.printErrorMessage();
        }
        return shouldExitProgram;
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
    * Sends "add" command typed by user to the Controller
    * 
    * @param fullCommand    The command received from user divided into pieces
    */
    private void addCommand(String[] fullCommand){
        if(validateAddCommand(fullCommand)){
            TaskDTO task = createTask(fullCommand);
            controller.addTask(task);        
        }else{
            output.printErrorMessage();
        }  
    }
    
    
    /**
    * Validates format of "add" command
    * 
    * @param fullCommand    The command received from user divided into pieces
    * @return true/false of validation
    */
    private boolean validateAddCommand(String[] fullCommand){
        boolean test = false;
        int lengthOfCommand = 5;
        if(fullCommand.length == lengthOfCommand){
            String date = fullCommand[2];
            if(validateDate(date)){
                test = true;
            }
        }
        return test;  
    }
    
    /**
    * Validates format of date
    * 
    * @param date    The date received from user who was adding new task to the list
    * @return true/false of validation
    */
    private boolean validateDate(String date){
        
        boolean test = true;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            formatter.parse(date);
        } catch (ParseException e) {
            test = false;
        }
        return test;
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
    * Changes due date of task from a String into Date type
    * 
    * @param dateInString      Due date of the task  
    * @return Date as a Date type
    */
    private Date parseDate(String dateInString){
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        
        try{
            date = formatter.parse(dateInString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return date;
    }
    
    /**
    * Sends "remove" command typed by user to the Controller
    * 
    * @param fullCommand    The command received from user divided into pieces
    */
    private void removeCommand(String[] fullCommand){
        
    }
    
    
    
    
}



