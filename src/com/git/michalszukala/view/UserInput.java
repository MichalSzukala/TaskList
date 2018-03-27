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
    
    private final int numberOfOptionsForAddCommand = 5;
    private final int sizeOfPrintCommand = 2;
    private final int sizeOfCommandUsingTaskNumber = 2;
    private final int sizeOfFileManipulationCommand = 2;
    
    
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
    public void startGUI(){
        output.createDisplay();
        askForInput();
    }
    

    /**
    * Asks user for input until user will not finish program by typing "exit"
    */
      private void askForInput() 
    {              
        boolean finished = false;
        String command;
        
        while (! finished) {
            output.askForInputMessage();
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
    
    
    
    /**
    * Validate all the command if they are typed in the correct format
    * @param command    The command typed by the user
    * @return   True/false if command has valid format
    */
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
            case "save":
                isValidCommand = validateFileManipulationCommand(fullCommand);
                break;
            case "load":
                isValidCommand = validateFileManipulationCommand(fullCommand);
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
    * Split the command to array based on space for later processing
    * 
    * @param command    The command typed by the user
    * @return Separated command received from user 
    */
    public String[] processCommand(String command){//---------------------------------------public for testing
        command = command.trim().toLowerCase();
        String[] splitedCommand = command.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < splitedCommand.length; i++){
            splitedCommand[i] = splitedCommand[i].replaceAll("\"", "");
        }
        return splitedCommand;
    }
    
    
    
    public boolean validateAddCommand(String[] fullCommand){//------------------------------public for testing
        boolean test = false;
        if(checkingLengthOfCommand(fullCommand, numberOfOptionsForAddCommand)){
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
        return fullCommand.length == neededLengthOfCommand;
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
        if(checkingLengthOfCommand(fullCommand, sizeOfPrintCommand)){
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
    
   
    /**
     * Validate command which are using number of the task as a option 
     * @param fullCommand   The command received from user divided into pieces
     * @return True/false if command has valid format
     */
    private boolean validateCommandThatRequireNumberOfTask(String[] fullCommand){
        boolean test = false;
        if(checkingLengthOfCommand(fullCommand, sizeOfCommandUsingTaskNumber)){
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
    
    
    private boolean validateTaskNumber(String taskNumberInString){
        boolean test = true;

        try {
            Integer.parseInt(taskNumberInString);
        } catch (NumberFormatException e) {
            test = false;
        }
        
        return test;
    }    
    
    /**
     * Validate commands responsible for file manipulation like "save" and "load"
     * @param fullCommand   The command received from user divided into pieces
     * @return True/false if command has valid format
     */
    private boolean validateFileManipulationCommand(String[] fullCommand){
        return fullCommand.length == sizeOfFileManipulationCommand;
    }
    

    /**
     * Creates DTO object used to transport command between classes
     * @param command   The command typed by the user
     * @return DTO object of command
     */
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
    
    /**
     * Creates DTO object used to transport add command between classes
     * @param command   The command typed by the user
     * @return DTO object of add command
     */
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
    * @return If user will type "exit" it returns true and program will know to exit
    */
    private boolean interpreteCommand(CommandDTO command){
        
        
        boolean shouldExitProgram = false; 
        
        switch(command.getCommand()){
                
            case "save":
                saveToFile(command);
                break;
                
            case "load":
                loadFromFile(command);
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
                editTask(command);
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
    
    
    private void saveToFile(CommandDTO command){
        controller.saveListToFile(command.getOption());
    }
    
    private void loadFromFile(CommandDTO command){
        controller.loadListToFile(command.getOption());
    }

   
    public void addTaskToTaskList(CommandDTO command){//----------------------------------public for testing
        controller.addTaskToList(command);        
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
    

    private void removeTaskFromList(CommandDTO command){
        
        int index = Integer.parseInt(command.getOption());
        controller.removeTask(index);
    }
    

    private void markTaskAsDone(CommandDTO command){
       
        int index = Integer.parseInt(command.getOption());
        controller.markTaskAsDone(index);  
    }


    /**
     * Sends request to output class to print the list based on selected criteria
     * @param command Object used for transporting command between classes
     */
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
    

    
    /**
     * Ask user for name of the project which is part of the task
     * @return New name of the project
     */
    private String projectName(){
        output.askForProject();
        Scanner scanner = new Scanner(System.in);
        String project = scanner.nextLine();
        project = project.trim().toLowerCase();
        return project;
    }
    

    /**
     * After choosing "edit" command user is asked to specify option for "edit" command and specify 
     * new part of the task which will be use to replace old part of the task
     * @param command The command received from user
     */
    private void editTask(CommandDTO command){
        int index = Integer.parseInt(command.getOption());
        
        String userOption = askForOptionsForEditCommand();
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



