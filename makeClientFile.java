   import java.util.*;
   import java.io.*;

   public class makeClientFile {
      private static Room[] allRooms;
      private static final int NUMROOMS = 100;
      private static String[] firstNames;
      private static String[] lastNames;
   
      public static void main(String[] args) {
         firstNames = new String[100];
         lastNames = new String[100];
         try
         {
            loadRooms();
            loadNames();
            createOutputFile();
         }
            catch (Exception e)
            {
               System.out.println("Could not load files. Exiting.");
               System.exit(0);
            }
      
      
      }
      private static void createOutputFile() throws Exception{
         PrintStream outfile = new PrintStream(
                        new FileOutputStream("clients.txt")
                        );
         for(int i = 0; i < allRooms.length; i++) {
            if(!allRooms[i].getEmpty()) {
               outfile.println(generateRandomClient(allRooms[i]).toString());
            }
         }
      
      }
      private static void loadNames() throws Exception {
         Scanner namesFile = new Scanner(new File("names.txt"));
         for(int i = 0; i < 100; i++) {
            firstNames[i] = namesFile.next();
            lastNames[i] = namesFile.next();
            namesFile.nextLine();
         }
      
      
      }
      private static Client generateRandomClient(Room room) {
      
      
         Client client = new Client();
         client.setFirstName(firstNames[(int)(Math.random() * firstNames.length)]);
         client.setLastName(lastNames[(int)(Math.random() * lastNames.length)]);
         int month = (int)(Math.random() * 12) + 1;
         int startDay = (int)(Math.random() * 15) + 1;
         int endDay = (int)(Math.random() * 14) + 15;
         client.setArrive(new Dates(month, startDay, 2016));
         client.setLeave(new Dates(month, endDay, 2016)); 
         client.setBedroom(room);
         return client;
      }
      private static void loadRooms()throws Exception 
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
   
   }