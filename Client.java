   import java.util.*;

   public class Client
   {
      private int customerID;
      private String lastName;
      private String firstName;
      private Dates arrive;
      private Dates leave;
      private Room bedroom;
   
   //values have to be manually set to work
      public Client() {
         setCustomerID((int)(Math.random() * 10000) + 1) ;
      
      }
      public Client(String ln, String fn, Dates dateIn, Dates dateOut, Room rm)
      {
         setCustomerID((int)(Math.random() * 10000) + 1) ;
         setLastName(ln);
         setFirstName(fn);
         setArrive(dateIn);
         setLeave(dateOut);
         setBedroom(rm);
      }
      
      public Client(int id, String ln, String fn, Dates dateIn, Dates dateOut, Room rm)
      {
         setCustomerID(id);
         setLastName(ln);
         setFirstName(fn);
         setArrive(dateIn);
         setLeave(dateOut);
         setBedroom(rm);
      }
   
      public void setCustomerID(int id) {customerID = id;}
      public void setLastName(String ln) {lastName = ln;}
      public void setFirstName(String fn) {firstName = fn;}
      public void setArrive(Dates dateIn) {arrive = dateIn;}
      public void setLeave(Dates dateOut) {leave = dateOut;}
      public void setBedroom(Room rm) {bedroom = rm;}
   
      public int getCustomerID() {
         return customerID;}
      public String getLastName() {
         return lastName;}
      public String getFirstName() {
         return firstName;}
      public Dates getArrive() {
         return arrive;}
      public Dates getLeave() {
         return leave;}
      public Room getBedroom(){ 
         return bedroom;}
   
      public String toString() {
      
         return customerID + " " + lastName + " " + firstName + " " + arrive.toString() + " " + leave.toString() + " " + bedroom.toString();
      }
   
      public int bill()
      {
      //method that calculates the bill for this client
         return daysStayed() * bedroom.getPrice();
      }
   
   //finds the number of days between the arrival and departure date
      public int daysStayed(){
         Calendar dayOne = Calendar.getInstance();
         dayOne.set(arrive.getYear(), arrive.getMonth(), arrive.getDay());
         Calendar dayTwo = Calendar.getInstance();
         dayTwo.set(leave.getYear(), leave.getMonth(), leave.getDay());
      
         if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
            return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
         } 
         else {
            if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
            //swap them
               Calendar temp = dayOne;
               dayOne = dayTwo;
               dayTwo = temp;
            }
            int extraDays = 0;
         
            int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);
         
            while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
               dayOne.add(Calendar.YEAR, -1);
            // getActualMaximum() important for leap years
               extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
         
            return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
         }
      }
   }
