import java.io.*;
public class MakeRoomFile
{
   public static void main(String[] args) throws Exception
   {
      final int NUMROOMS = 100; 
      int numBeds;
      int price;
      int emptyNum;
      boolean empty;
      
      PrintStream outfile = new PrintStream(
                        new FileOutputStream("room.txt")
                        );
   
      for(int x = 1; x <= NUMROOMS; x++)
      {
         numBeds = (int)(Math.random()*2+1);
         if (numBeds == 1)
            price = 89;
         else
            price = 99;
         emptyNum = (int)(Math.random()*2);
         if (emptyNum == 1)
            empty = true;
         else
            empty = false;
         outfile.println(x + " " + numBeds + " " + price + " " + empty);
      }
      outfile.close();
   }
}