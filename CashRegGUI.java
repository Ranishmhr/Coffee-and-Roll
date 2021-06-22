
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//GUI	controls	for Cash	Register	app (buttons and text fields)
public class CashRegGUI	extends JPanel	implements ActionListener {
   private int	opt =	0;
   private JButton done;
   private JButton reset;
   private JTextField saleText, msgText, quantText, paidText, changeText;
   private JRadioButton	rb[] = new JRadioButton[6];
   private ButtonGroup bgroup;
   private JTextArea	denomArea;
   private JTextArea	tradeArea;
   private Product order[];
   private Change	change;

   // Constructor
   public CashRegGUI() {
      String str;
      Dimension d;
      Font font;
      final Color SR_COLOUR = new Color(212, 212, 212);   //silver Cash Register
   
   	//	Create and initialise objects
      order	= new	Product[6];								 //each element corresponds to a	product,	ignore 0	element
      order[1]	= new	Product("Schnitzel Roll");
      order[1].setPrice(1880);
      order[2]	= new	Product("Fish Roll");
      order[2].setPrice(1725);
      order[3]	= new	Product("Lamb Roll");
      order[3].setPrice(1460);
      order[4]	= new	Product("Ice Cream Roll");
      order[4].setPrice(675);
      order[5]	= new	Product("Coffee Latte");
      order[5].setPrice(340);
   
      change =	new Change();									//create	Change object
   
   	//	Base container	grid layout
      setLayout(new GridLayout(3, 2, 5, 0));				//split container	in	vertical	halfs
   
   	//	Create two buttons
      done = new JButton("Done");
      reset	= new	JButton("Reset");
      done.setPreferredSize(new Dimension(70, 20));
      reset.setPreferredSize(new	Dimension(70, 20));
   
   	//	Create panels
      JPanel pc1 = new JPanel(new GridLayout(2,	1));	//Title panel
      JPanel pc1a	= new	JPanel();							//sale panel
      JPanel pc1b	= new	JPanel();							//sale panel
      JPanel pc2 = new JPanel();								//radio button	panel
      JPanel pc3 = new JPanel(new GridLayout(5,	1));	//input/output	panel
      JPanel pc3a	= new	JPanel();							//quantity panel (pc3 grid)
      JPanel pc3b	= new	JPanel();							//paid panel (pc3	grid)
      JPanel pc3c	= new	JPanel();							//change	panel	(pc3 grid)
      JPanel pc3d	= new	JPanel();							//button	panel	(pc1 grid)
      JPanel pc3e	= new	JPanel();							//panel in input/output, space textfields
      JPanel p1 =	new JPanel();								//dummy panel
      JPanel p2 =	new JPanel();								//denominations textarea
      JPanel p3 =	new JPanel();								//daily trade textarea
      pc2.setBackground(new Color(255,	255, 230));    //yellow Menu Board
      pc1a.setBackground(SR_COLOUR);	//silver colour
      pc1b.setBackground(SR_COLOUR);
      pc3.setBackground(SR_COLOUR);
      pc3a.setBackground(SR_COLOUR);
      pc3b.setBackground(SR_COLOUR);
      pc3c.setBackground(SR_COLOUR);
      pc3d.setBackground(SR_COLOUR);
      pc3e.setBackground(SR_COLOUR);
      p1.setBackground(SR_COLOUR);
      p2.setBackground(SR_COLOUR);
      p3.setBackground(SR_COLOUR);
      setBackground(SR_COLOUR);
      
   	//	Create labels
      JLabel titleLab =	new JLabel("Coffee N Roll Cash Register");
      JLabel saleLab	= new	JLabel("Sale price ");
      JLabel quantLab =	new JLabel("Quantity");
      JLabel paidLab	= new	JLabel("Paid (cents)");
      JLabel changeLab = new JLabel("Change");
   
   	//	Create textfields
      saleText	= new	JTextField(6);
      msgText = new JTextField(23);
      quantText =	new JTextField(10);
      paidText	= new	JTextField(10);
      changeText = new JTextField(10);
   	
   	//set	textfields read only
      saleText.setEditable(false);
      msgText.setEditable(false);							//disable textfields
      changeText.setEditable(false);		
   
   	//	Set textfield and	label	properties
      font = new Font("Arial", Font.BOLD,	20);
      titleLab.setFont(new	Font("Century Schoolbook",	Font.BOLD +	Font.ITALIC, 20));
      saleLab.setFont(font);
      saleText.setFont(font);
      saleLab.setForeground(Color.RED);
      saleText.setForeground(Color.RED);
   
      font = new Font("Arial", Font.PLAIN, 14);
      msgText.setFont(font);
      msgText.setForeground(Color.RED);
      msgText.setBackground(new Color(255, 243,	243));
      quantText.setFont(font);
      changeText.setFont(font);
      paidText.setFont(font);
      quantLab.setFont(font);
      paidLab.setFont(font);
      changeLab.setFont(font);
   
      d = new Dimension(paidLab.getPreferredSize().width	+ 4, changeLab.getPreferredSize().height);
      quantLab.setPreferredSize(d);
      paidLab.setPreferredSize(d);
      changeLab.setPreferredSize(d);

      msgText.setPreferredSize(new Dimension(msgText.getPreferredSize().width, msgText.getPreferredSize().height + 2));
      d = new Dimension(quantText.getPreferredSize().width, quantText.getPreferredSize().height + 2);
      quantText.setPreferredSize(d);                  //increase box height to fit 14pt text (java bug?)
      paidText.setPreferredSize(d);
      changeText.setPreferredSize(d);
   
   	//	Create textAreas
      denomArea =	new JTextArea(9,40);
      tradeArea =	new JTextArea(8,40);
   
   	//	Set textareas read only
      denomArea.setEditable(false);                   //nothing to edit here
      tradeArea.setEditable(false);
   
   	//	Set textareas font
      font = new Font("Courier New", Font.BOLD,	12);
      denomArea.setFont(font);
      tradeArea.setFont(font);

      // Create Menu Board with product radio buttons 
      Box box1	= Box.createVerticalBox();
      bgroup =	new ButtonGroup();							//radio button	group
      box1.add(new JLabel("———————————————————————"));
      for (int	i = 1; i	< 6; i++) {							//	create and add	radio	buttons
         str =	String.format("%2d. %-15s%10s", i, order[i].getName(), change.currency(order[i].getPrice()));
         rb[i]	= new	JRadioButton(str);
         rb[i].setFont(new	Font("Courier New", Font.BOLD, 14));
         bgroup.add(rb[i]);
         rb[i].setBackground(new	Color(255, 255, 230));
         box1.add(rb[i]);
         rb[i].addActionListener(this);
      }
      box1.add(new JLabel("———————————————————————"));
   
   	//	Add controls
      pc1.add(pc1a);												//sales textfield
      pc1.add(pc1b);												//sales panel
      pc2.add(box1);                                  //Menu Board
      pc3.add(pc3a);												//error textfield
      pc3.add(pc3b);												//quantity panel
      pc3.add(pc3c);												//paid panel
      pc3.add(pc3d);												//change panel
      pc3.add(pc3e);												//button panel

      pc1a.add(titleLab);
      pc1b.add(saleLab);									   //sale price
      pc1b.add(saleText);
      p2.add(denomArea);									   //currency denominations textarea
      p3.add(tradeArea);									   //trade summary textarea
   
      pc3a.add(msgText);
      pc3b.add(quantLab);
      pc3b.add(quantText);
      pc3c.add(paidLab);
      pc3c.add(paidText);
      pc3d.add(changeLab);
      pc3d.add(changeText);
      pc3e.add(reset);											//button
      pc3e.add(Box.createRigidArea(new	Dimension(25, 0)));	//space between buttons
      pc3e.add(done);
 
   	//	Add panels to container
      add(pc1);													//sales panel
      add(p1);                                        //dummy pwnel
      add(pc2);													//Menu Board and radio button	panel
      add(p2);														//textArea daily trade
      add(pc3);													//input/output	panel
      add(p3);														//textArea daily trade
   
   	//Add	listeners to buttons
      done.addActionListener(this);
      reset.addActionListener(this);
      quantText.addActionListener(this);
      paidText.addActionListener(this);
   } //end of constructor

	//Process buttons	and textfields
   @Override
   public void	actionPerformed(ActionEvent e) {
      int total;
      int quantity;
      int sale;
      int paid;
      int chng;
      int notes[][];
      int coins[][];
   
      msgText.setText("");										//reset msg	textfield
      if	(e.getSource()	==	done)	{							//done button clicked
         tradeArea.setText("");
         for (int	i = 1; i	< 6; i++)
            tradeArea.append(String.format("Total %14s Sales: %9s%n", order[i].getName(),	change.currency(order[i].getTotal())));
         total	= 0;
         for (int	i = 1; i	< 6; i++)
            total	+=	order[i].getTotal();
         tradeArea.append("                           ——————————\n");
         tradeArea.append(String.format("Total %14s Sales: %9s%n", "Daily", change.currency(total)));
      
         opt =	0;
         saleText.setText("");							  //clear textfields
         msgText.setText("\"Reset\" button unlocks Cash Register");	//reset msg textfield
         quantText.setText("");
         paidText.setText("");
         changeText.setText("");
         denomArea.setText("");
         quantText.setEditable(false);
         paidText.setEditable(false);
         bgroup.clearSelection();							//deselect radio buttons
         for (int	i = 1; i	< 6; i++)
            rb[i].setEnabled(false);
      } else if (e.getSource() == reset) {				//reset button	clicked
         for (int	i = 1; i	< 6; i++)
            order[i].reset();									//zero Product	total
         clearText();
         opt =	0;
         bgroup.clearSelection();							//deselect radio buttons
         quantText.setEditable(true);
         paidText.setEditable(true);
         for (int	i = 1; i	< 6; i++)
            rb[i].setEnabled(true);
      } else if (e.getSource() == quantText)	{
         quantity	= validateQuantity();					//validate quantity
         paidText.setText("");
         if	(opt == 0) {
            msgText.setText("Select a product before entering quantity");
            quantText.setText("");
            paidText.setText("");
         } else {
            if	(quantity >	-1) {
               sale = order[opt].salePrice(quantity); //calculate	cost of purchase
               saleText.setText(change.currency(sale));
            }
         }
      } else if (e.getSource() == paidText) {
         quantity	= validateQuantity();			      //validate quantity
         if	(opt == 0) {
            msgText.setText("Select a product before entering quantity");
            quantText.setText("");
            paidText.setText("");
         } else if (quantity > -1) {
            paid = validatePaid();							//validate paid textfield
            if	(paid	> -1)	{
               sale = order[opt].salePrice(quantity);	//calculate	cost of purchase
               chng = paid	- sale;
               if	(chng	>=	0)	{
                  saleText.setText(change.currency(sale));
                  order[opt].addToTotal(sale);			//	track	total	sales	in	cents	for this	product
                  changeText.setText(change.currency(chng));
                  change.denChange(chng);					//	calculate currency denominations
                  notes	= change.getNotes();
                  coins	= change.getCoins();
                  denomArea.setText("");					//clear
                  for (int	i = 0; i	< 5; i++)
                     if	(notes[i][0] >	0)
                        denomArea.append(String.format("Number of %3d dollar notes: %2d%n", notes[i][1]/100, notes[i][0]));
                  for (int	i = 0; i	< 6; i++)
                     if	(coins[i][0] >	0)
                        if	(coins[i][1] <	100)
                           denomArea.append(String.format("Number of %3d cents  coins: %2d%n", coins[i][1],	coins[i][0]));
                        else
                           denomArea.append(String.format("Number of %3d dollar coins: %2d%n", coins[i][1]/100, coins[i][0]));
               } else {
                  msgText.setText("Not enough money tendered");
               }
            }
         }	else {
            quantText.setText("");							//invalid quantity, clear field
            paidText.setText("");
         } // end	of	paid quantity
      } else {
         for (int	i = 1; i	< 6; i++) {						//search for selected radio button
            if (e.getSource() == rb[i]) {
               opt = i;
               clearText();
            }     
         }
      }  
   }// end of actionPerformed

	//	Return quantity or -1 on error
   public int validateQuantity()	{
      int quantity = -1;												//local variable
   
      if	(quantText.isEditable()) {		
         if  (!quantText.getText().isEmpty()) {
            try {
               quantity	= Integer.parseInt(quantText.getText());
               if	(quantity <	1 || quantity > 200)	{
                  msgText.setText("Quantity must be an integer [1-200]"); //error msg
                  quantity	= -1;									//quantity missing
               }
            }
            catch	(NumberFormatException ex)	{
               msgText.setText("Quantity must be an integer [1-200]"); //error msg
               quantity	= -1;										//non-numeric entry
               quantText.setText("");
            }
         }
      } else {
         msgText.setText("Click Reset button to unlock register for sales");
         quantity	= -1;											//register locked, textfield/areas disabled
      }
      return quantity;											//valid quantity or -1 if error
   } //end of validateQuantity()

	//	Return payment	or	-1	on	error
   public int validatePaid() {
      int paid;													//local variable
   
      if	(paidText.isEditable())	{
         try {
            paid = Integer.parseInt(paidText.getText());
            if	(paid	< 1 || paid	> 1000000) {
               msgText.setText("Payment must be 1,000,000¢ or less"); //error	msg
               paid = -1;										//error,	paid > 1,000,000,
            } else {
               if	(paid	% 5 != 0) {
                  paid = paid	+ (5 - paid	% 5);			//round of to the	next higer multiple of 5
                  paidText.setText("" + paid);
               }
            }
         }
         catch	(NumberFormatException ex)	{
            msgText.setText("Payment must be an integer [1-1,000,000¢]"); //error msg
            paid = -1;											//non-numeric entry
            paidText.setText("");
         }
      } else {
         msgText.setText("Click Reset button to unlock register for sales");
         paid = -1;												//register locked, textfield/areas disabled
      }
      return paid;												//valid payment or -1 if error
   } //end of validatePaid()

	//	Clear	text fields	and text	areas
   public void	clearText()	{
      saleText.setText("");
      quantText.setText("");
      paidText.setText("");     
      changeText.setText("");
      denomArea.setText("");
      tradeArea.setText("");
   }
} //end of class CashRegGUI()
