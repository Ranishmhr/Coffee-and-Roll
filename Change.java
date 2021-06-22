/*-----------------------------------------------
Change.java

NIT2112 		Object Oriented Programming
Assignment: Sydney Campus
Date:	      5 January, 2020
Author:     Jakub Szajman
-------------------------------------------------*/
import java.text.NumberFormat;
 
public class Change {
   private int notes[][] = new int[5][2];
   private int coins[][] = new int[6][2];
   NumberFormat formatter = NumberFormat.getCurrencyInstance();

   // Constructor, inialise notes[] and coin[] arrays
   public Change() {
      notes[0][1] = 10000;                      //banknotes in cents in desc order: $100
      notes[1][1] = 5000;                       //$50
      notes[2][1] = 2000;                       //$20
      notes[3][1] = 1000;                       //$10
      notes[4][1] = 500;                        //$5
      coins[0][1] = 200;                        //coins in desc order: $2
      coins[1][1] = 100;                        //$1
      coins[2][1] = 50;                         //50c
      coins[3][1] = 20;                         //20c
      coins[4][1] = 10;                         //10c
      coins[5][1] = 5;                          //5c
   }

   // Calculate currency denominatios from amount in cents
   public void denChange(int amount) {
      int x = amount;                           //cents
      for (int i = 0; i < 5; i++) {
         notes[i][0] = x/notes[i][1];           //integer division, number of notes of specific denomination
         x %= notes[i][1];                      //reminder
      }
      for (int i = 0; i < 6; i++) {
         coins[i][0] = x/coins[i][1];           //integer division, number of coins of specific denomination
         x %= coins[i][1];                      //reminder
      }
   }

   // Return array of banknote denominations
   public int[][] getNotes() {
      return notes;
   }

   // Return array of coin denominations
   public int[][] getCoins() {
      return coins;
   }
   
   // Convert cents to $dollars.cents string
   public String currency(int cents) {
      return formatter.format((double)cents/100);
   }
}
