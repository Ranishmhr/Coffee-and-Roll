import java.util.*;

public class FinalBill {
   public static void main(String[] args){
      int item_number, quantity, sales, payment;
      int total = 0;
      int notes[][];
      int coins[][];
      String message1, message2, symbol1, symbol2;  // hline = hr_ln, triple
      validate_input in; // x = in
      Product order[];
      Change change;
      
      //create and initialize objects
      order = new Product[6];   //select = order
      order[1] = new Product("Chicken Sandwich");
      order[1].setPrice(600);
      order[2] = new Product("Egg Sandwich");
      order[2].setPrice(500);
      order[3] = new Product("Pork Sandwich");
      order[3].setPrice(700);
      order[4] = new Product("Beef Sandwich");
      order[4].setPrice(700);
      order[5] = new Product("Vegan Sandwich");
      order[5].setPrice(500);
      
      change = new Change();     //create Change to give object
      in = new validate_input();     //validate input
      symbol1 = new String(new char[34]).replace("\0", Character.toString(42));    // ASCII 196 = _
      symbol2 = new String(new char[34]).replace("\0", Character.toString(190));   //ASCII 190 = 
      
      do {
      // print menu, price in dollars and cents
      System.out.printf("%n" + symbol2);     // prints 
      System.out.printf("%n" + symbol1 + "%n");     // 
      for (int i=1; i<6; i++)
         System.out.printf("%4d. %-16s%10s%n", i, order[i].getName(), change.currency(order[i].getPrice()));
      System.out.printf("%4d. Done%n" + symbol1 + "%n%n", 6);     //
      
         do {
            //get the order number
            message1 = "Enter the item number you want to order: ";
            message2 = "Please choose option between 1 and 6.";
            item_number = in.getInput(message1, message2, 1, 6);      //description
            if (item_number ==6)
               break;            //exit the progrma
         
            //get ordered quantity, getInput() handles input errors
            message1 = "Enter the quantity ordered: ";
            message2 = "Note: Order quantity should be between 1 and 50.";
            sales = order[item_number].salePrice(in.getInput(message1, message2, 1, 50));       //limit qunatity to 50

         
            do {
            message1 = "Enter the amo2nt paid in cents[0-1000000]: ";
            message2 = "Note: Amount paid should be positive";
            payment = in.getInput(message1, message2, 1, 100000);        //max payment 1,000
            if (payment % 5 !=0)       //5 cents peiece is AU small amount
               System.out.println("*Amount must be in multiples of 5 cent.");
            if (payment < sales)
               System.out.println("Sorry, payment is not enough.");
            
            } while (payment % 5 != 0 || payment < sales);
            payment = payment - sales;
            if (payment > 0)
               System.out.println("The change to give is: " + change.currency(payment));
            else
               System.out.println("Don't need to give change.");
            System.out.println("");
         
            //print to screen formatted change currency denominations
            change.denChange(payment);
            notes = change.getNotes();
            coins = change.getCoins();
            System.out.println("The change given: ");
            System.out.printf(message1 + "%n");          //horizentol line
            for (int i=0; i<5; i++)
               if(notes[i][0] > 0)
                  System.out.printf("| Number of %3d dollar notes: %2d |%n", notes[i][1]/100, notes[i][0]);
               
            for (int i=0; i<6; i++)
               if(coins[i][0] > 0)
                  if(coins[i][1] < 100)
                     System.out.printf("| Number of %3d cents coins: %2d |%n", coins[i][1]/100, coins[i][0]);
                  else
                     System.out.printf("| Number of %3d dollar coins: %2d |%n", coins[i][1]/100, coins[i][0]);
            System.out.printf( "%n");
         
         }while (item_number <1 || item_number >6);
     }while (item_number != 6);
     
     for (int i=1; i<6; i++)
        total += order[i].getTotal();
     System.out.printf("Total %14s Sales: %9s%n", "Daily", change.currency(total));
     
     for (int i=1; i<6; i++)
     System.out.printf("Total %14s Sales: %9s %n",order[i].getName(), change.currency(order[i].getTotal()));
   }
}