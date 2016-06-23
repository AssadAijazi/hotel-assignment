   public class Room
   {
      private int roomNumber;
      private int beds;
      private int price;
      private boolean empty;
   
      public Room(int rn, int bd, int pr, boolean em)
      {
         roomNumber = rn;
         beds = bd;
         price = pr;
         empty = em;
      }
   
      public void setRoomNumber(int rn) {roomNumber = rn;}
      public void setBeds(int bd) {beds = bd;}
      public void setPrice(int pr) {price = pr;}
      public void setEmpty(boolean em) {empty = em;}
   
      public int getRoomNumber() {
         return roomNumber;}
      public int getBeds() {
         return beds;}
      public int getPrice() {
         return price;}
      public boolean getEmpty() {
         return empty;}	
      public String toString() {
      return roomNumber + " " + beds + " " + price + " " + empty;
      }
   }
