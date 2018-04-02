package com.git.michalszukala.IO;

import com.git.michalszukala.controller.Controller;
import com.git.michalszukala.model.Task;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



/**
 * Saves and loads the task list to/from the file 
 * 
 * @author Michal Szukala
 * @version 1.0
 */
public class IO {
    
    private List<Task> listOfTasks;
    private Controller controller;
    
    /**
     * Creates list of task where information from the file will be moved
     * @param controller Responsible for sending information from file to list of tasks
     */
    public IO(Controller controller){
        listOfTasks = null;
        this.controller = controller;
        
    }

    
    /**
     * Saves serialized object of the whole list of tasks to the file on the hard drive in the project directory
     * @param nameOfFile    The name specify by the user
     */
    public void saveToFile(String nameOfFile){
        Path path = Paths.get("IO/" + nameOfFile);
        
        populateListOfTasks();
        try{
            if(!Files.isDirectory(path)){
                Files.createDirectories(path.getParent());
            }
            if(Files.notExists(path)){
                Files.createFile(path);
            } 
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path.toString()));
            outputStream.writeObject(listOfTasks);
            outputStream.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void populateListOfTasks(){
        listOfTasks = new ArrayList<>();
        
        for(int index = 0; index < controller.sizeOfTaskList(); index++){
            listOfTasks.add(controller.getTaskFromList(index));
        }
    }
    
    /**
    * Read object of list of tasks from the file and sends it to the ListOfTasks class
    * @param nameOfFile     The name specify by the user
    */
    public void readFromFile(String nameOfFile){
            
        Path path = Paths.get("IO/" + nameOfFile);
        String pathInString = "IO/" + nameOfFile;

        if(Files.exists(path)){
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(pathInString))){
                listOfTasks = (ArrayList<Task>)inputStream.readObject();
                listOfTasks.stream().forEach((Task task)->controller.addTaskToList(task));
                inputStream.close();
                controller.loadingListWasSuccess();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            controller.printNoFileMessage();
        }
            
    }
}