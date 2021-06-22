/*-----------------------------------------------
CoffeeNRollGUI.java

NIT2112 		Object Oriented Programming
Assignment: Sydney Campus
Date:	      5 January, 2020
Author:     Jakub Szajman
Revision		1.0
-------------------------------------------------*/

import javax.swing.*;

//Create JFrame container
public class CNR_GUI
{
	public static void main(String[] args) 	//main method
	{
		JFrame frame = new JFrame("CashReg");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new CashRegGUI());		//create GUI
		frame.pack();
		frame.setVisible(true);
	} //end of main
} //end CNR_GUI
