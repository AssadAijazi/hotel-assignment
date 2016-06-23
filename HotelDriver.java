/*
*created new makeClientFile.java which generates random clients for all rooms that are occupied. It should be run
directly after the makeRoomFile.java
*changed the way ID was generated: instead of being user assigned,
its a randomly generated integer between 1 and 10000. This ensures that it in practice it is unique for every new customer
*Changed the way the program finds the client. Instead of using name, it uses the unique user ID, which helps deal with the case where
two customers have the same number
*created GUI which outputs the data for the clients and the room via options 5 and 6, respectively
*/
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class HotelDriver{
   public static final int NUMROOMS = 100;
   private static Room allRooms[];
   private static ArrayList<Client> allClients;

   public static void main(String[] args){
      allClients = new ArrayList<Client>();
      try
      {
         loadRooms();
      }
      catch (Exception e)
      {
         System.out.println("Could not load room file. Exiting.");
         System.exit(0);
      } 
   
      try
      {
         loadClients();
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
      }
   
      Scanner keyboard = new Scanner(System.in);
      boolean getInput = true;
      int menuSelect = -1;
   
   // While loop to drive the main menu options       
      while (getInput == true)
      {
         display_menu();
         try
         {
            menuSelect = Integer.parseInt(keyboard.next());
         }
         catch (Exception e)
         {
            System.out.println("Invalid Input. Pick an option below");
            System.out.println("");
            continue;
         }
         if (menuSelect != 9)
            execute_menu_option(menuSelect, keyboard);
         else 
         // User selected Quit. 
            getInput = false;
         try {
            PrintStream outfile = new PrintStream(
                  new FileOutputStream("clients.txt")
                  );
            for(int i = 0; i < allClients.size(); i++) {
               outfile.println(allClients.get(i).toString());
            }
         }
         catch (Exception e) {
            System.out.println("Error in creating client file");
         }
              
         try {
            PrintStream outfile = new PrintStream(
                  new FileOutputStream("room.txt")
                  );
            for(int i = 0; i < allRooms.length; i++) {
               outfile.println(allRooms[i].toString());
            }
         }
         catch (Exception e) {
            System.out.println("Error in creating room file");
         }
      }
      System.exit(0); 
   }

   public static void loadRooms()throws Exception 
   {
   // load the room information from the room.txt file into the allRooms[] array
      int roomCount = 0;
      allRooms = new Room[NUMROOMS];
      Scanner infile = new Scanner(new File("room.txt") );
   
   //read the text file into the array     
      while (infile.hasNext() && roomCount < NUMROOMS)
      {
         allRooms[roomCount] = new Room(infile.nextInt(), infile.nextInt(), infile.nextInt(), infile.nextBoolean());
         infile.nextLine();
         roomCount++;  
      }
   }
   public static void loadClients() throws Exception
   {
      Scanner infile = new Scanner(new File("clients.txt"));
      while(infile.hasNext())
      {
         Client client = new Client(infile.nextInt(), infile.next(), infile.next(), new Dates(infile.nextInt(), infile.nextInt(), infile.nextInt()), new Dates(infile.nextInt(), infile.nextInt(), infile.nextInt()), new Room(infile.nextInt(), infile.nextInt(), infile.nextInt(), infile.nextBoolean()));
         allClients.add(client);
         infile.nextLine();
      }
   }
   public static void display_menu()
   {
   // display the main menu options
      System.out.println("Hotel Reservation System");
      System.out.println("Pick an option number");
      System.out.println("1: Find Rooms");
      System.out.println("2: Find Client");
      System.out.println("3: Reserve Room");
      System.out.println("4: Print Client Bill");
      System.out.println("5: Show all clients");
      System.out.println("6: Show all rooms");
      System.out.println("9: Quit");
      System.out.println("");
   }

   public static void execute_menu_option(int menuSelect, Scanner keyboard)
   {
   // execute the selected main menu option
      switch (menuSelect)
      {
         case 1: findRooms(keyboard);
            break;
         case 2: findClientReservation(keyboard);
            break;
         case 3: reserveRoom(keyboard);
            break;
         case 4: printClientBill(keyboard);
            break;
         case 5: showClients(keyboard);
            break;
         case 6: showRooms(keyboard);
            break;
      }
   }

   public static void findRooms(Scanner keyboard)
   {
   // Prompt the user for 1 or 2 beds, 
   // then display a list of all empty rooms with that number of beds.
      String numOfBeds;
      String output = "";
      do {
         System.out.println("Please enter number of beds. (1 or 2)");
         numOfBeds = keyboard.next();
      } while(!(numOfBeds.equals("1") || numOfBeds.equals("2")));
   
      for(int i = 0; i < allRooms.length; i++) {
         if(allRooms[i].getEmpty()) {
            if(allRooms[i].getBeds() == Integer.parseInt(numOfBeds)) {
               output += allRooms[i].getRoomNumber() + " ";
            }
         }
      }
      System.out.println(output);
   }

   public static void findClientReservation(Scanner keyboard)
   {
   // Prompt the user for a customer name 
   // then display the clients reservation
      System.out.println("Enter customer last name");
      String name = keyboard.next();
      System.out.println("Enter customer ID");
      int id = keyboard.nextInt();
      boolean clientFound = false;
      for(int i = 0; i < allClients.size(); i++) {
         if((allClients.get(i).getLastName().equals(name)) && (allClients.get(i).getCustomerID() == id)){
            System.out.println(allClients.get(i).toString());
            clientFound = true;
         }
         
      }
      if(!clientFound)
         System.out.println("No client found");  
   }

   public static void reserveRoom(Scanner keyboard)
   {
   // Prompts the user for customer reservation information,
   // then creates a new client object.
   
      System.out.print("Enter the Customer's Last Name: ");
      String lastName = keyboard.next();
      System.out.print("Enter the Customer's First Name: ");
      String firstName = keyboard.next();
   
      System.out.print("Enter the arrival month (1-12):");
      int arriveMonth = Integer.parseInt(keyboard.next());
      System.out.print("Enter the arrival date: ");
      int arriveDay = Integer.parseInt(keyboard.next());
      System.out.print("Enter the arrival year: ");
      int arriveYear = Integer.parseInt(keyboard.next());
   
      System.out.print("Enter the departure month (1-12):");
      int leaveMonth = Integer.parseInt(keyboard.next());
      System.out.print("Enter the departure date: ");
      int leaveDay = Integer.parseInt(keyboard.next());
      System.out.print("Enter the departure year: ");
      int leaveYear = Integer.parseInt(keyboard.next());
   
      boolean empty = false;
      int roomNumber = 0;
      while (!empty)
      {
         System.out.print("Enter the Room Number: ");
         roomNumber = Integer.parseInt(keyboard.next());
         if (allRooms[roomNumber-1].getEmpty() == true)
         {
            empty = true;
         }
         else
            System.out.println("The room is occupied.  Please enter a different room number");
      }
      allRooms[roomNumber - 1].setEmpty(false);
      Client client = new Client();
      client.setLastName(lastName);
      client.setFirstName(firstName);
      client.setArrive(new Dates(arriveMonth, arriveDay, arriveYear));
      client.setLeave(new Dates(leaveMonth, leaveDay, leaveYear));
      allRooms[roomNumber - 1].setEmpty(false);
      client.setBedroom(allRooms[roomNumber - 1]);
      allClients.add(client);
      
   	
      System.out.println("Reservation completed.\n");
   }

   public static void printClientBill(Scanner keyboard)
   {
   // Prompt the user for a customer name 
   // then prints the client's bill
      System.out.println("Please enter the client's last name");
      String name = keyboard.next();
      System.out.println("Enter customer ID");
      int id = keyboard.nextInt();
      Client client;
      for(int i = 0; i < allClients.size(); i++) 
      {
         if((allClients.get(i).getLastName().equals(name)) && (allClients.get(i).getCustomerID() == id)) {
            client = allClients.get(i);
            String output = "Client name: " + client.getFirstName() + " " + client.getLastName() + "; " + "Room Number: " + client.getBedroom().getRoomNumber() + "; " + "Arrival date: " + client.getArrive().output() + "; " + "Departure date: " + client.getLeave().output() + "; " + "Days stayed: " + client.daysStayed() + "; " + "Price per night: $" + client.getBedroom().getPrice() + "; " + "Total cost: $" + client.bill() +";"; 
            System.out.println(output); 
         }          
      } 
   
   }
   
   public static void showClients(Scanner keyboard)
   {
      JFrame mainFrame = new JFrame("Clients");
      mainFrame.setSize(700, 500);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      
      String[] columnNames = {"Customer ID", "Last Name", "First Name", "Arrive Date", "Departure Date", "Room Number"};
      Object[][] data = new Object[allClients.size()][6];
      for(int i = 0; i < allClients.size(); i++) {
         data[i][0] = allClients.get(i).getCustomerID();
         data[i][1] = allClients.get(i).getLastName();
         data[i][2] = allClients.get(i).getFirstName();
         data[i][3] = allClients.get(i).getArrive().output();
         data[i][4] = allClients.get(i).getLeave().output();
         data[i][5] = allClients.get(i).getBedroom().getRoomNumber();
      }
      JTable table = new JTable(data, columnNames);
      
      JScrollPane pane = new JScrollPane(table);
      table.setFillsViewportHeight(true);
      table.setEnabled(false);
      
      mainFrame.setContentPane(pane);
      mainFrame.setVisible(true);
      mainFrame.setAlwaysOnTop(true);
   }
   
   public static void showRooms(Scanner keyboard) {
      JFrame frame = new JFrame("Rooms");
      frame.setSize(500, 500);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
      String[] columnNames = {"Room Number", "Number of beds", "Price per night", "Empty?"};
      Object[][] data = new Object[NUMROOMS][4];
      for(int i = 0; i < NUMROOMS; i++) {
         data[i][0] = allRooms[i].getRoomNumber();
         data[i][1] = allRooms[i].getBeds();
         data[i][2] = "$" + allRooms[i].getPrice();
         data[i][3] = allRooms[i].getEmpty();
      }
      
      JTable table = new JTable(data, columnNames);
      JScrollPane pane = new JScrollPane(table);
      table.setFillsViewportHeight(true);
      table.setEnabled(false);
      
      frame.setContentPane(pane);
      
      frame.setVisible(true);
      frame.setAlwaysOnTop(true);
      
   
   }
}