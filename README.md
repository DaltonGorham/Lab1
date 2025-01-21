# Object-Oriented Programming Lab 1
## This project is composed of two main methods:
### Register.java - Console Version
### MakingChange.java - GUI Version

## How It Works
## Clone the Repo: https://github.com/DaltonGorham/Lab1.git
## Run the Register.java class for the console version
#### In the console, enter the desired amount of change to receive, and the program will calculate and display the least number of bills and coins needed to provide that change.
## Run the MakingChange.java class for the GUI version 
#### In the text field, enter the desired amount of change to receive, and the program will visually display the least number of bills and coins as images, reflecting the optimal change calculation.


# Project Overview
#### All classes of this project reside within the MakingChange package
### Denomination.java is a record that defines the various bills and coins used in the register, it does not contain any methods
### Purse.java is a class that manages the cash within the register. It contains a private HashMap<Denomination, Integer> cash, which maps each denomination to the number of items of that denomination.
### Purse.java methods: 
#### Constructor: This initializes the purse to a new HashMap
#### public void add(Denomination type, int amt) : Adds a specified denomination and its quantity to the cash map.
#### public double remove(Denomination type, int amt) : Removes a specified denomination and quantity from the cash map and returns the total value of the removed denominations.
#### public double getValue() : This will return the total monetary value within the cash Map
#### public String toString() : Builds and returns a string representation of the cash map using a StringBuilder.
#### public boolean isEmpty() : Returns true or false if the map is empty
#### public Set<Map.Entry<Denomination, Integer>> entrySet() :  Returns all entries (denomination and quantity) in the cash map.
### Register.java is A class containing the main method for the console-based version. It creates a register, populates it with a list of denominations, and calculates change for a given amount.
### Register.java methods:
#### Constructor: initializes the register with a list of denominations, these denominations are common U.S. bills/coins found in a register
#### public Purse makeChange(BigDecimal amt) : this method takes in a requested amount of money to receive back as change. It will compute the lowest amount of bills/coins to get that amount and return it in a Purse
#### public static void main(String[] args) : This is the main method for the console version, it creates a new register and asks the user for how much change they would like back and prints the lowest amount of bills and coins to the console
### PursePanel.java is a class that extends JPanel. It is responsible for updating and creating the bills and coins for the GUI
### PursePanel.java methods:
#### Constructor : Gets the screen width and screen height and sets the panel's dimensions
#### public void setPurse() : calls the repaint method which updates the screen in the action listener
#### public void paintComponent(Graphics g) : Responsible for creating, updating, and modifying the GUI for each new input
#### public ImageIcon getImage(Denomination d) : Gets the path where the denomination's images are and returns a ImageIcon object using that path
### RegisterPanel.java is a class that extends JPanel and manages the user interface in the GUI. It implements the action listener to handle user input and updates the display accordingly.
### RegisterPanel.java methods:
#### Constructor: The constructor initializes a new Register instance to handle the calculation of the optimal change based on the entered amount. It also sets up the inputPanel, which contains a JTextField for user input, and creates an instance of the PursePanel class, responsible for visually displaying the calculated change in an organized manner.
#### InputListener : InputListener is a nested class that implements ActionListener to handle user input. Upon receiving input, it processes the entered amount, and calls the setPurse method to dynamically update the GUI, reflecting the calculated change.
### MakingChange.java is the class that contains the main method for launching the GUI version of the application. It creates a new JFrame instance and adds the RegisterPanel to the frame, setting up the user interface for calculating and displaying change.
