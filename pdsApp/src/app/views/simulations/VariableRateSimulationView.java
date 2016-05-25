package app.views.simulations;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.table.DefaultTableModel;

import app.controllers.VariableRateSimulationController;
import app.views.welcome.WelcomeViewClient;
import app.listeners.VariableRateSimulationListener;
import app.models.component.RoundJTextField;

public class VariableRateSimulationView extends JPanel implements VariableRateSimulationListener{

  private JPanel body;
  private Container cont;
  private VariableRateSimulationController controller;

  
  private JLabel label_result;
  private JLabel label_amount;
  private JLabel label_initial_rate;
  private JLabel label_cap;
  private JLabel label_time;
  private JLabel label_title;


  private JTextField answer_lastname;
  private JTextField answer_firstname;
  private JTextField answer_amount;
  private JComboBox answer_time;
  private JTextField answer_initial_rate;
  private JComboBox answer_cap;

  private JButton bouton1 = new JButton("Valider");
  private JButton bouton2 = new JButton("Afficher les sc�narios favorables");
  private JButton bouton3 = new JButton("Afficher les sc�narios d�favorables");
  private JPanel body1;
 

  //Interest rate max and min value
  private double lowerBoundary;
  private double upperBoundary;
  double stringToInt;


  public VariableRateSimulationView(VariableRateSimulationController controller, JPanel body, Container cont){
    this.controller = controller;
    this.body = body;
    this.cont = cont;
  }
  
  public void setIHM() {
      body.removeAll();
      body.add(initComponent());
      
      cont.revalidate();
      cont.repaint();
  }
 
  public JPanel initComponent(){
	  
	    // Instantiation of JComboBox for the cap
	    String[] tab1 = {"","-1%  et +1%", "-2%  et +2%"};
	    answer_cap = new JComboBox(tab1);
	    answer_cap.setPreferredSize(new Dimension (250,20));
	    answer_cap.setSelectedIndex(0); // definition of the default value
	    answer_cap.setFont(new Font(tab1.toString(), Font.ITALIC, 20));

	    // Instantiation of JComboBox for the time
	    String[] tab2 = {"","7","10","15","20","25","30"};
	    setAnswer_time(new JComboBox(tab2));
	    answer_time.setPreferredSize(new Dimension (250,20));
	    answer_time.setSelectedIndex(0);
	    answer_time.setFont(new Font(tab2.toString(), Font.ITALIC, 20));
	
	    label_amount= new JLabel("Montant");
	    setAnswer_amount(new RoundJTextField(20));
	    answer_amount.setColumns(20);
	    
	    
	    label_amount.setFont(new java.awt.Font("Verdana", 0,20)); // NOI18N
	    label_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	    
	    answer_amount.setFont(new java.awt.Font("Verdana", 0,20)); // NOI18N
	    answer_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);


	    label_initial_rate= new JLabel("Taux initiale :");
	    setAnswer_initial_rate(new RoundJTextField(20));
	    answer_initial_rate.setColumns(20);
	    
	    
	    label_initial_rate.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
	    label_initial_rate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	    
	    answer_initial_rate.setFont(new java.awt.Font("Verdana", 0,20)); // NOI18N
	    answer_initial_rate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

	    label_cap= new JLabel("Cap :");
	    label_cap.setFont(new java.awt.Font("Verdana", 0,20));
	    label_cap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	    answer_cap.setPreferredSize(new Dimension(30,30));
	    
	    
	    label_time= new JLabel("Dur�e :");
	    label_time.setFont(new java.awt.Font("Verdana", 0, 20));
	    label_time.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	    answer_time.setPreferredSize(new Dimension(30,30));
	    
	    label_result= new JLabel();
	    label_result.setFont(new java.awt.Font("Verdana", 0, 20));
	    
	    
	   
	  

	    body1= new JPanel();
		body1.setBackground(new Color(215,203,233,255));
		body1.setLayout(new BoxLayout(body1,BoxLayout.Y_AXIS));
		
		//body1.setLayout(new GridLayout(10,2,10,10));
		body1.add(label_amount);
		body1.add(answer_amount);
		body1.add(label_time);
	    body1.add(answer_time);
		body1.add(label_cap);
		body1.add(answer_cap);
		body1.add(label_initial_rate);
		body1.add(answer_initial_rate);
		body1.add(bouton1);
		
		
		// Add actionListener on answer_cap
	    answer_cap.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {

	        if (answer_cap.getSelectedItem()=="-1%  et +1%"){

	          switch(answer_time.getSelectedIndex())
	          {
	          case 1:
	            answer_initial_rate.setText("2.95");
	            break;
	          case 2:
	            answer_initial_rate.setText("2.17");
	            break;
	          case 3:
	            answer_initial_rate.setText("2.28");
	            break;
	          case 4:
	            answer_initial_rate.setText("2.56");
	            break;
	          case 5:
	            answer_initial_rate.setText("2.85");
	          case 6:
	            answer_initial_rate.setText("3.32");

	          }
	        }
	        
	        else if (answer_cap.getSelectedItem()=="-2%  et +2%"){

		          switch(answer_time.getSelectedIndex())
		          {
		          case 1:
		            answer_initial_rate.setText("2.92");
		            break;
		          case 2:
		            answer_initial_rate.setText("2.14");
		            break;
		          case 3:
		            answer_initial_rate.setText("2.19");
		            break;
		          case 4:
		            answer_initial_rate.setText("2.56");
		            break;
		          case 5:
		            answer_initial_rate.setText("2.75");
		          case 6:
		            answer_initial_rate.setText("3.29");

		          }
		        }
	          else answer_initial_rate.setText("0.00");

	    }});


	    // Add of an event when clicking on the bouton1
	    bouton1.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {
	    	  resultsIHM1(getAnswerInitialRate());
	        
	    	  cont.revalidate();
	    	  cont.repaint();
	      }
	    });

	    // Add of an event when clicking on the bouton2
	    bouton2.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {
	        resultsIHM2("Sc�narios Favorables");

	      }
	    });
	    // Add of an event when clicking on the bouton3
	    bouton3.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {
	        resultsIHM2("Sc�narios d�favorables");
	      }
	    });

	    this.add(body1);
	    this.setVisible(true);
		return body1;

  }
  
  //Creation of the results view
  private JPanel resultsIHM1(String interestRate) {
    body1.removeAll();
 
    
    lowerBoundary=labelString(interestRate)-borne();
    upperBoundary=labelString(interestRate)+borne();

    double lowerBoundaryRound= Math.floor(100*lowerBoundary)/100;
    double upperBoundaryRound=Math.floor(100*upperBoundary)/100;
    controller.initialization();
    double monthPayment1= controller.calculateMonthlyPayment(this.labelString(this.getAnswerInitialRate()));
    double totalToPay = monthPayment1*(this.labelString(this.getAnswerTime())*12);
    
    
 
    label_amount.setText("Montant du pr�t:  " + getAnswerAmount());
    label_time.setText("Dur�e du pr�t:   "+ answer_time.getSelectedItem()+ "ans");
    label_cap.setText("Le cap choisit est "+ answer_cap.getSelectedItem());
    label_initial_rate.setText("Taux d'int�r�t actuel: " + "  " + interestRate + "%");
    label_result.setText("Le taux d'int�r�t sera au minimum "+lowerBoundaryRound+"%  "+ "et au maximum "+upperBoundaryRound+ "%");
    
 
    body1.setLayout(new GridLayout(7,1,20,20));
    body1.add(label_amount);
    body1.add(label_time);
    body1.add(label_cap);
    body1.add(label_result);
    body1.add(bouton2);
    body1.add(bouton3);
   // body1.setBorder(BorderFactory.createTitledBorder(getAnswerFistname()+ " "+ getAnswerLastname()+ "  voici une premi�re simulation de votre pr�t: "));
    return body1;
  }



private void resultsIHM2(String title) {
	
    double interest= this.labelString(this.getAnswerInitialRate());
    controller.initialization();
    double monthPayment2= controller.calculateMonthlyPayment(interest);
    int duree= (int) (this.labelString(this.getAnswerTime()));
    double monthPayment; //Amount to pay for one year

    JFrame fenetre= new JFrame();
    fenetre.setExtendedState(fenetre.MAXIMIZED_BOTH);
    JPanel pan1= new JPanel();
    pan1.setLayout(new BorderLayout());

    JPanel pan2= new JPanel();
    pan2.setLayout(new BorderLayout());

    JPanel pan3= new JPanel();
    pan3.setLayout(new BorderLayout());

    JTabbedPane onglet;
    onglet = new JTabbedPane();

    fenetre.setLocationRelativeTo(null);
    fenetre.setTitle(title);


    //Instantiation of the titles
    String[] titles = {"Ann�e", "Taux", "Mensualit�", "Total Pay�"};
    Object[][] data = null;

    //Instantiation of the table
    DefaultTableModel model1 = new DefaultTableModel(data,titles);
    JTable table1 = new JTable(model1);
    
    DefaultTableModel model2 = new DefaultTableModel(data,titles);
    JTable table2 = new JTable(model2);
    
    DefaultTableModel model3 = new DefaultTableModel(data,titles);
    JTable table3 = new JTable(model3);
    
  
    

    if(title=="Sc�narios Favorables"){
    
     
    	//Scenario 1
    	double totalToPay1= 0;
    	for(int i=1; i<=duree; i++){
        monthPayment2=controller.calculateMonthlyPayment(interest);
        monthPayment=monthPayment2*12;
        model1.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
        totalToPay1=totalToPay1+labelString(model1.getValueAt(i - 1,3).toString());
        interest=Math.floor(100*(interest-(1.0/duree)))/100;
      }
    	model1.addRow(new Object[]{"Total",totalToPay1});
    	interest= this.labelString(this.getAnswerInitialRate());
    	
    	//Scenario 2
    	double totalToPay2= 0;
    	for(int i=1; i<=duree; i++){
            monthPayment2=controller.calculateMonthlyPayment(interest);
            monthPayment=monthPayment2*12;
            model2.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
            totalToPay2=totalToPay2+labelString(model2.getValueAt(i - 1,3).toString());
          }
    	model2.addRow(new Object[]{"Total",totalToPay2});
    	interest= this.labelString(this.getAnswerInitialRate());
    	
    	//Scenario 3
    	double totalToPay31=0 ;
    	for(int i=1; i<=(duree/2)+1; i++){
            interest=Math.floor(100*(interest+(1.0/duree)))/100;
            monthPayment2=controller.calculateMonthlyPayment(interest);
            monthPayment=monthPayment2*12;
            model3.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
            totalToPay31=totalToPay31+labelString(model2.getValueAt(i - 1,3).toString());
              }
    	
    	
    	// Scenario 3
    	double totalToPay32 = totalToPay31;
    	for(int i=(duree/2)+2; i<=duree; i++){
            interest=Math.floor(100*(interest-(1.0/duree)))/100;
            monthPayment2=controller.calculateMonthlyPayment(interest);
            monthPayment=monthPayment2*12;
            model3.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
            totalToPay32=totalToPay32+labelString(model3.getValueAt(i - 1,3).toString());
              }
    	model3.addRow(new Object[]{"Total",totalToPay32});
       }
    
    else{
    	
    	
      //Scenarios 1
      interest= this.labelString(this.getAnswerInitialRate());
      double totalToPay1= 0;
      for(int i=1; i<=duree; i++){

        interest=Math.floor(100*(interest+(1.0/duree)))/100;
        monthPayment2=controller.calculateMonthlyPayment(interest);
        monthPayment=monthPayment2*12;
        model1.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
        totalToPay1=totalToPay1+labelString(model1.getValueAt(i - 1,3).toString());
      }
      model1.addRow(new Object[]{"Total",totalToPay1});
      
     
      //Scenarios 2
      interest= this.labelString(this.getAnswerInitialRate());
      double totalToPay21= 0;
      for(int i=1; i<=(duree/2)+1; i++){
          monthPayment2=controller.calculateMonthlyPayment(interest);
          monthPayment=monthPayment2*12;
          model2.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
          totalToPay21=totalToPay21+labelString(model2.getValueAt(i - 1,3).toString());
        }
      
     
      double totalToPay22=totalToPay21;
      for(int i=(duree/2)+1; i<=duree; i++){
          interest=Math.floor(100*(interest+(1.0/duree)))/100;
          monthPayment2=controller.calculateMonthlyPayment(interest);
          monthPayment=monthPayment2*12;
          model2.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
          totalToPay22=totalToPay22+labelString(model2.getValueAt(i - 1,3).toString());
        }
       model2.addRow(new Object[]{"Total",totalToPay22});
      
      
       
       
       //Scenarios 3
       interest= this.labelString(this.getAnswerInitialRate());
       double totalToPay31= 0;
  	   for(int i=1; i<=(duree/2)+1; i++){
  	    //Update of the interest rate and the monthly payment
        interest=Math.floor(100*(interest-(1.0/duree)))/100;
        monthPayment2=controller.calculateMonthlyPayment(interest);
        monthPayment=monthPayment2*12;
        //Add of new line in the table
        model3.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
        totalToPay31=totalToPay31+labelString(model3.getValueAt(i - 1,3).toString());
        }
 
  	   double totalToPay32= totalToPay31;
  	   for(int i=(duree/2)+1; i<=duree; i++){
        interest=Math.floor(100*(interest+(1.0/duree)))/100;
        monthPayment2=controller.calculateMonthlyPayment(interest);
        monthPayment=monthPayment2*12;
        model3.addRow(new Object[]{i,interest,monthPayment2,monthPayment});
        totalToPay32=totalToPay32+labelString(model3.getValueAt(i - 1,3).toString());
        
      }
    model3.addRow(new Object[]{"Total",totalToPay32});
    
  	}

    pan1.add(new JScrollPane(table1),BorderLayout.CENTER);
    onglet.add("sc�nario1",pan1);
    
    
    pan2.add(new JScrollPane(table2),BorderLayout.CENTER);
    onglet.add("sc�nario2",pan2);
    
    
    pan3.add(new JScrollPane(table3),BorderLayout.CENTER);
    onglet.add("sc�nario3",pan3);

    fenetre.getContentPane().add(onglet);
    fenetre.setVisible(true);
}


  // Convert a string to int
  public double labelString(String g){
    try {
      stringToInt=Double.parseDouble(g);

    } catch (NumberFormatException e) {

    }
    return stringToInt;
  }



  // Return an int according to the cap
  public double borne(){
    final double cas1 =1;
    final double cas2 =2;

    if (answer_cap.getSelectedItem()== "-1%  et +1%")
      return cas1;
    else if (answer_cap.getSelectedItem()== "-2%  et +2%")
      return cas2 ;
    else
      return 0.0;

  }

  public String getAnswerAmount() {
    return answer_amount.getText();
  }

  public void setAnswer_amount(JTextField answer_amount) {
    this.answer_amount = answer_amount;
  }

  public String getAnswerInitialRate() {
    return answer_initial_rate.getText();
  }

  public void setAnswer_initial_rate(JTextField answer_initial_rate) {
    this.answer_initial_rate = answer_initial_rate;
  }

  public String getAnswerTime() {
    return answer_time.getSelectedItem().toString();
  }
  
  public String getAnswerLastname() {
	    return answer_lastname.getText();
	  }
  
  public String getAnswerFistname() {
	    return answer_firstname.getText();
	  }

  public void setAnswer_time(JComboBox answer_time) {
    this.answer_time = answer_time;
  }



  //public static void main(String[] args){
  //  VariableRateSimulationController c = new VariableRateSimulationController();
  //  VariableRateSimulationView ihm = new VariableRateSimulationView(c);
  //  c.addListener(ihm);
  //}

}


