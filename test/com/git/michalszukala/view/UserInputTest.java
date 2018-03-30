package com.git.michalszukala.view;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michal Szukala
 */
public class UserInputTest {
    
    public UserInputTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
     
    @Test
    public void testProcessCommandWithmultipleWords(){
        UserInput instance = new UserInput();
        String command = "add \"go to the park\"";
        String[] result = instance.processCommand(command);
        String[] expectedResult = {"add", "go to the park"};
        Assert.assertArrayEquals("Command not splited on multiple words", result, expectedResult);
    }
    
    @Test
    public void testProcessCommandWithSingleWords(){
        UserInput instance = new UserInput();
        String command = "add go to the park";
        String[] result = instance.processCommand(command);
        String[] expectedResult = {"add", "go", "to", "the", "park"};
        Assert.assertArrayEquals("Command not splited on single words", result, expectedResult);
    }
    
    @Test
    public void testProcessCommandWithEmptyString(){
        UserInput instance = new UserInput();
        String command = "";
        String[] result = instance.processCommand(command);
        String[] expectedResult = {""};
        Assert.assertArrayEquals("Command can not be empty", result, expectedResult);
    }
    
    
    
    @Test
    public void testValidateAddCommandWithToFewOptions(){
        UserInput instance = new UserInput();
        String[] command = {"add", "11/11/11", "sda"};
        boolean result = instance.validateAddCommand(command);
        assertFalse("Command have too few options", result);  
    }
    
    @Test
    public void testValidateAddCommandWithToManyOptions(){
        UserInput instance = new UserInput();
        String[] command = {"add", "11/11/11", "sda", "ads", "sdsad"};
        boolean result = instance.validateAddCommand(command);
        assertFalse("Command have too many options", result);  
    }
    
    @Test
    public void testValidateAddCommandWithMonthOutOfRange(){
        UserInput instance = new UserInput();
        String[] command = {"add", "asds", "11/13/11", "ads", "sdsad"};
        boolean result = instance.validateAddCommand(command);
        assertFalse("Month in date is above 12", result);  
    }
    
    @Test
    public void testValidateAddCommandWithDayOutOfRange(){
        UserInput instance = new UserInput();
        String[] command = {"add", "asds", "11/11/34", "ads", "sdsad"};
        boolean result = instance.validateAddCommand(command);
        assertFalse("Day in date is above 31", result);  
    }
    
    @Test
    public void testValidateAddCommandWithDateInWrongPlace(){
        UserInput instance = new UserInput();
        String[] command = {"add", "11/11/29","asds" , "ads", "sdsad"};
        boolean result = instance.validateAddCommand(command);
        assertFalse("Date is in wrong place in command", result);  
    }
}
