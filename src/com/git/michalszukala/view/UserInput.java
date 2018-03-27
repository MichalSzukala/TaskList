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
        output = new UserOutput(controller);
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
            if(validateCommand(command)){
                CommandDTO commandObject = createCommand(command);
                finished = interpreteCommand(commandObject);
            }else{
                output.printErrorMessage();
            }
        }
        
        System.out.println("See you later");
        System.exit(0);
    }
    
    
    
      
    private boolean validateCommand(String command){
        boolean isValidCommand = false;
        
        String[] fullCommand = processCommand(command);
        String mainCommand = getMainCommandFromFullCommand(fullCommand);
        
        switch(mainCommand){
            case "add":
                isValidCommand = validateAddCommand(fullCommand);
                break;
            case "print":
                isValidCommand = validatePrintCommand(fullCommand);
                break; 
            case "exit":
                isValidCommand = true;
                break;
            case "help":
                isValidCommand = true;
                break;
            default:
                isValidCommand = validateCommandThatRequireNumberOfTask(fullCommand);
                break; 
        }

        return isValidCommand;
    }
    
    private String getMainCommandFromFullCommand(String[] fullCommand){
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
        //String[] splitedCommand = command.split("\\s+");
        //" +(?=(([^\"]*\"){2})*[^\"]*$)"
        String[] splitedCommand = command.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < splitedCommand.length; i++){
            splitedCommand[i] = splitedCommand[i].replaceAll("\"", "");
        }
        return splitedCommand;
    }
    
    
    
     private boolean validateAddCommand(String[] fullCommand){
        boolean test = false;
        if(checkingLengthOfCommand(fullCommand, numberOfOptionsForAddCommand())){
            String date = getDateFromAddCommand(fullCommand);
            if(validateDate(date)){
                test = true;
            }
        }
        return test;  
    }
     
     private String getDateFromAddCommand(String[] fullCommand){
        return fullCommand[2];
    }
     
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
     
    
    /**
    * Validates format of "print" command
    * 
    * @param fullCommand    The command received from user divided into pieces
    * @return true/false of validation
    */
    private boolean validatePrintCommand(String[] fullCommand){
        boolean test = false;
        if(checkingLengthOfCommand(fullCommand, sizeOfPrintCommand())){
            String commandsOption = getOptionFromFullCommand(fullCommand);
            if(commandsOption.contains("-") && validOptionsLettersForPrintCommand(commandsOption)){
                test = true;
            }
        }
        return test;  
    }
    
    private boolean validOptionsLettersForPrintCommand(String word){
        boolean test = true;
        
        for(int i = 1; i < word.length(); i++){
            if(word.charAt(i) != 'a' && word.charAt(i) != 'i' && word.charAt(i) != 'd' && word.charAt(i) != 'p'){
                test = false;
            }
        }
        
        return test; 
    }
    
    private String getOptionFromFullCommand(String[] fullCommand){
        return fullCommand[1];
    }
    
    private int sizeOfPrintCommand(){
        return 2;
    }
    
    
    private boolean validateCommandThatRequireNumberOfTask(String[] fullCommand){
        boolean test = false;
        if(checkingLengthOfCommand(fullCommand, sizeOfCommandUsingTaskNumber())){
            String taskNumberInString = getOptionFromFullCommand(fullCommand);
            if(validateTaskNumber(taskNumberInString)){
                int taskNumber = Integer.parseInt(taskNumberInString);
                if(taskNumber < controller.sizeOfTaskList()){
                    test = true;
                }
            }
        }
        return test;  
    }
    
    private int sizeOfCommandUsingTaskNumber(){
        return 2;
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

    
    private CommandDTO createCommand(String command){
        String[] fullCommand = processCommand(command);
        String mainCommand = getMainCommandFromFullCommand(fullCommand);
        String optionOfCommand = null;
        if(fullCommand.length > 1){
            optionOfCommand = getOptionFromFullCommand(fullCommand);
        }
        
        CommandDTO commandObject = null;
        
        switch(mainCommand){
            case "add":
                commandObject =  createAddCommand(command);
                break;
            case "exit":
                commandObject = new CommandDTO(mainCommand);
                break;
            default:
                commandObject = new CommandDTO(mainCommand, optionOfCommand);
                break;    
        }
        
        return commandObject;
    }
    
    private AddCommandDTO createAddCommand(String command){
        
        String[] fullCommand = processCommand(command);
        String mainCommand = getMainCommandFromFullCommand(fullCommand);
        String task = getTaskFromAddCommand(fullCommand);
        String date = getDateFromAddCommand(fullCommand);
        String status = getStatusFromAddCommand(fullCommand);
        String project = getProjectFromAddCommand(fullCommand);
        AddCommandDTO addCommand = new AddCommandDTO(mainCommand, task, date, status, project);
        
        return addCommand;

    }

    
    private String getTaskFromAddCommand(String[] fullCommand){
        return fullCommand[1];
    }

    private String getStatusFromAddCommand(String[] fullCommand){
        return fullCommand[3];
    }
    
    private String getProjectFromAddCommand(String[] fullCommand){
        return fullCommand[4];
    }
    
    
    
    /**
    * Interprets and validates command received from user and follow the command order
    * 
    * @param command    The command received from user
    */
    private boolean interpreteCommand(CommandDTO command){
        
        
        boolean shouldExitProgram = false; 
        
        switch(command.getCommand()){
                
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
                addTaskToTaskList(command);
                break;
                
            case "edit":
                break;
                
            case "remove":
                removeTaskFromList(command);
                break;
                
            case "done":
                markTaskAsDone(command);
                break;
                
            case "print":
                printListOfTasks(command);
                break;

            default:
                output.printErrorMessage();
        }
        return shouldExitProgram;
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
    private void addTaskToTaskList(CommandDTO command){
            
        TaskDTO task = createTask((AddCommandDTO) command);
        controller.addTask(task);                      
    }
    
    /**
    * creates DTO object for transporting task to the list of tasks
    * 
    * @param fullCommand      The command received from user divided into pieces  
    * @return Task DTO object
    */
    private TaskDTO createTask(AddCommandDTO addCommand){
        
        String task = addCommand.getTask();
        String dateInString = addCommand.getDate();
        Date date = parseDate(dateInString);
        String status = addCommand.getStatus();
        String project = addCommand.getProject();
        
        TaskDTO createdTask = new TaskDTO(task, date, status, project);
        
        return createdTask;
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
    * Sends "remove" command typed by user to the Controller
    * 
    * @param fullCommand    The command received from user divided into pieces
    */
    private void removeTaskFromList(CommandDTO command){
        
        int index = Integer.parseInt(command.getOption());
        controller.removeTask(index);
    }
    
    

    /**
    * Sends "done" command typed by user to the Controller
    * 
    * @param fullCommand    The command received from user divided into pieces
    */
    private void markTaskAsDone(CommandDTO command){
       
            int index = Integer.parseInt(command.getOption());
            controller.markTaskAsDone(index);
        
    }
    
    
     private void printListOfTasks(CommandDTO command){
        
        String option = command.getOption();
        if(option.contains("a")){
            controller.sortTasksAccordingToDate();
            output.printWholeListOfTasks();
        }else if(option.contains("p")){
            controller.sortTasksAccordingToDate();
            printTasksInvolveProject(option);
        }else if(option.contains("i")){
            controller.sortTasksAccordingToDate();
            printTasksInvolveStatus(option);
        }else if(option.contains("d")){
            controller.sortTasksAccordingToDate();
            output.printUrgentTasks();
        }else{
            output.printErrorMessage();
        }
    }
    
    
    
    
    
    private void printTasksInvolveProject(String option){
        String project = null;
        if(option.contains("i") && option.contains("d")){
            project = projectName();
            output.printImportantAndUrgentTasksOfProject(project);
        }else if(option.contains("i")){
            project = projectName();
            output.printImportantTasksOfProject(project);
        }else if(option.contains("d")){
            project = projectName();
            output.printUrgentTasksOfProject(project);
        }else{
            project = projectName();
            output.printListOfTasksOfProject(project);
        }
       
    }
    
    
    private void printTasksInvolveStatus(String option){
        if(option.contains("d")){
            output.printImportantAndUrgentTasks();
        }else{
            output.printImportantTasks();
        }
    }
    

    
    
    private String projectName(){
        output.askForProject();
        Scanner scanner = new Scanner(System.in);
        String project = scanner.nextLine();
        project = project.trim().toLowerCase();
        return project;
    }
    
    private void editTask(CommandDTO command){
        int index = Integer.parseInt(command.getOption());
        
        String userOption =askForOptionsForEditCommand();
        if(validateEditTaskOption(userOption)){
            if(!isUserWantToGoBack(userOption)){
                String userEditMessage = askForInputToEditTask();
                if(validateEditTaskUserInput(userOption, userEditMessage)){
                    interpreteEditCommand(index, userOption, userEditMessage);
                }else {
                    output.printErrorMessage();
                }
            }
        }else{
            output.printErrorMessage();
        }
    }
    
    private String askForOptionsForEditCommand(){
        output.askForOptionsForEditCommand();
        Scanner scanner =  new Scanner(System.in);
        String userOption = scanner.nextLine();
        return userOption;
    }
    
    private String askForInputToEditTask(){
        output.askForInputToEditTask();
        Scanner scanner =  new Scanner(System.in);
        String userEditMessage = scanner.nextLine();
        return userEditMessage;
    }
            
    
    private boolean isUserWantToGoBack(String userOption){
        return userOption.equals("-x");
    }
    
    
    
    private boolean validateEditTaskUserInput(String userOption, String userEditMessage){
        boolean isUserMessageValid = true;
        if(userOption.contains("d")){
            if(!validateDate(userEditMessage)){
                isUserMessageValid = false;
            }
        }
        return isUserMessageValid;
    }
    
    
    
    
    private boolean validateEditTaskOption(String userOption){
        boolean isUserOptionValid = false;
        
        if(userOption.contains("-") && validOptionsLettersForEditCommand(userOption) && userOption.length() == 2){
                isUserOptionValid = true;
            }
        
        
        return isUserOptionValid;
    }
    
    
     private boolean validOptionsLettersForEditCommand(String word){
        boolean test = true;
        
        for(int i = 1; i < word.length(); i++){
            if(word.charAt(i) != 't' && word.charAt(i) != 'd' && word.charAt(i) != 's' && word.charAt(i) != 'p' && word.charAt(i) != 'x'){
                test = false;
            }

        }
        
        return test; 
    }
     
     
     private void interpreteEditCommand(int index, String userOption, String userEditMessage){
         switch(userOption){
            case "-t":
                controller.editTask(index, userEditMessage);
                break;
            case "-d":
                controller.editDueDate(index, parseDate(userEditMessage));
                break;
            case "-s":
                controller.editStatus(index, userEditMessage);
                break;
            case "-p":
                controller.editProject(index, userEditMessage);
                break;
            default:
                output.printErrorMessage();
                break;
         }
     }

    
}