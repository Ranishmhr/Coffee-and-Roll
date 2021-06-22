/*-----------------------------------------------
Product.java

NIT2112 		Object Oriented Programming
Assignment: Sydney Campus
Date:	      5 January, 2020
Author:     Jakub Szajman
-------------------------------------------------*/

public class Product {
   private String name;
   private int price;
   private int total;

   // Constructor, initialise product name
   public Product(String name) {
      this.name = name;
      total = 0;
   } // end of constructor

   // Update price of product
   public void setPrice(int cents) {
      price = cents;
   } // end of setPrice
   
   // Keep track of sum of day purchases
   public void addToTotal(int amount) {
      total += amount;
   } // end of addToTotal
   
   // Return product name
   public String getName() {
      return name;
   } // end of getName
   
   // Return product price
   public int getPrice() {
      return price;
   } // end of getPrice
   
   // Return total all sales
   public int getTotal() {
      return total;
   } // end of getTotal
   
   // Calculate sale's price
   public int salePrice(int quantity) {
      return quantity * price;                  //in cents
   }
   
   // Sets attributes to default value. Required by GUI app only
   public void reset() {
      total = 0;
   } // end of reset
} // end of class Product