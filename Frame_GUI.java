import javax.swing.*;

// Create JFrame container
public class Frame_GUI
{ public static void main(String[] args)// main method
{
JFrame frame = new JFrame("Main Register Menu");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().add(new CashRegGUI()); //Create GUI
frame.pack();
frame.setVisible(true);
}// end of main
}// end of Frame_GUI