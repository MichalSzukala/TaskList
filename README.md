# TaskList
## About the program
The Task List is useful tool for keeping your daily tasks organized. It is created in a style of Linux Bash
## Opening the software
**For Windows:**
1. Download the dist/windows/TaskList.jar and TaskList.bat 
2. Open the command prompt
2. In the command prompt, go to downloaded file
3. Type _**java -jar TaskList.jar**_  
**or**  
Click on the **TaskList.bat**, which will start the program automatically

**For Linux/Mac**
1. Download the dist/linux/TaskList.jar and startTaskList.sh
1. Open the terminal
2. In the terminal, go to downloaded file
3. Type _**java -jar TaskList.jar**_  
**or**   
Type _**sh startTaskList.sh**_, which will start the program automatically

## Commands
* With the use of _**"**_  _**"**_ it is possible to input multiple words  
* Options of **print** command are suitable for mixing.  For example: ***print -id*** will print important and urgent tasks    

**save** *[file name]*     - Save tasks to the file.  Example: _**save MyDailyTasks**_  
**load** *[file name]*     - Load tasks from the file.  Example: _**load MyDailyTasks**_  
**help**                   - Prints a help window with all the commands  
**exit**                   - Exit the Program

**add** *[task][due date][status][project]*  - Add a new task (Date: yy/mm/dd).  Example: _**add "wash car" 18/03/26 important auto**_  
**edit** *[task number]*                     - Edit chosen task   
**remove** *[task number]*                   - Remove chosen task from the list  
**done** *[task number]*                     - Mark chosen task as done.  Status of the task will change to _**done**_   
**print** *[option]*                         - Print the List of Tasks.  Example: _**print -id**_ It will print important tasks with due date of today

Options for **print** command:  
_**[-a]**_ all tasks, _**[-d]**_ tasks with due date of today, _**[-i]**_ tasks with important status, _**[-p]**_ tasks belonging to specific project

## Architecture
### Class Diagram
![](GraphicFiles/UMLClassDiagram.png)
