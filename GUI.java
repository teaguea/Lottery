import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

/***********************************************************************
 * GUI front end for Lottery Simulation 
 * 
 * @author Scott Grissom
 * @version February 1, 2013
 **********************************************************************/
public class GUI extends JPanel{

    /** buttons */
    JButton randomNum = new JButton ("Random Numbers");
    JButton pickNum = new JButton ("Pick Numbers");
    JButton multiGames = new JButton ("Multiple Games");
    JButton bigWin = new JButton ("Biggest Winner");
    JButton oldWin = new JButton ("Oldest Winner");
    JButton winners = new JButton ("All Major Winners");

    JLabel checkWinners = new JLabel ("Check Winners");
    JLabel prizeL = new JLabel ("Prize $");
    JLabel stateL = new JLabel ("ST");

    JTextField prizeT = new JTextField();
    JTextField stateT = new JTextField();
    LotteryMachine lm;

    /** text fields */

    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenu reportsMenu;
    private JMenuItem quitItem;
    private JMenuItem openItem; 
    private JMenuItem stateItem;
    private JMenuItem reportItem;

    public static void main(String arg[]){
        // the tradition five lines of code
        // normally place here are 
        // inserted throughout the construtor
        new GUI();

    }

    /*********************************************************************
    Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
    public GUI(){
        //new LotteryMachine and reads tickets
        lm = new LotteryMachine();
        lm.readTickets("TicketInfo.txt");

        theGUI = new JFrame("Mega Million Lottery");
        theGUI.setVisible(true);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the Results Area for the Center area
        results = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(results);
        theGUI.add(BorderLayout.CENTER, scrollPane);

        // create the South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));

        theGUI.add(BorderLayout.SOUTH, southPanel);
        southPanel.add(prizeL);
        southPanel.add(prizeT);
        southPanel.add(stateL);
        southPanel.add(stateT);

        // create the East Panel  
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        theGUI.add(BorderLayout.EAST, eastPanel);
        eastPanel.add(Box.createVerticalGlue());  
        eastPanel.add(new JLabel("Draw Ticket"));
        eastPanel.add(Box.createVerticalGlue());

        eastPanel.add(randomNum);
        ButtonListener listener = new ButtonListener();
        randomNum.addActionListener(listener);

        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(pickNum);
        pickNum.addActionListener(listener);

        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(multiGames);
        multiGames.addActionListener(listener);

        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(checkWinners);

        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(bigWin);

        bigWin.addActionListener(listener);

        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(oldWin);
        oldWin.addActionListener(listener);

        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(winners);
        winners.addActionListener(listener);

        // set up File menus
        setupMenus();
        theGUI.pack();
    }

    /*********************************************************************
    List all entries given an ArrayList of tickets

    @param tix list of all tickets
     *********************************************************************/
    public void displayTickets(ArrayList <LotteryTicket> tix){
        for (LotteryTicket t: tix){
            results.append(t + "\n");
        }

    }

    //Accepts values for ball from user and passes them to the drawticket Method
    private void pickNumbers()
    {
        String str = JOptionPane.showInputDialog("Enter Ball #1:");
        int b1 = Integer.parseInt(str);
        String str2 = JOptionPane.showInputDialog("Enter Ball #2:");
        int b2 = Integer.parseInt(str2);
        String str3 = JOptionPane.showInputDialog("Enter Ball #3:");
        int b3 = Integer.parseInt(str3);
        String str4 = JOptionPane.showInputDialog("Enter Ball #4:");
        int b4 = Integer.parseInt(str4);
        String str5 = JOptionPane.showInputDialog("Enter Ball #5:");
        int b5 = Integer.parseInt(str5);
        String strM = JOptionPane.showInputDialog("Enter Megaball:");
        int m = Integer.parseInt(strM);

        lm.drawTicket(b1, b2, b3, b4, b5, m);
    }

    /*********************************************************************
    Respond to menu selections and button clicks

    @param e the button or menu item that was selected
     *********************************************************************/
    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            LotteryTicket c = null;

            // menu item - quit
            if (e.getSource() == quitItem){
                System.exit(1);
            }

            //Finds oldest player
            if (e.getSource() == oldWin){
                results.setText("Oldest Player" + "\n" +
                    lm.getOldestPlayer().toString());
            }

            //Finds oldest player
            if (e.getSource() == bigWin){
                results.setText("Biggest Winner" + "\n");
                results.append(lm.getBiggestWinner().toString());
            }

            //Creates random numbers and pays out tickets
            if (e.getSource() == randomNum){
                lm.drawTicket();
            }

            //Plays multiple games
            if (e.getSource() == multiGames){
                results.setText(lm.multipleGames());
            }

            //Prints Winners whose prize is as big or bigger than the amount passed
            if (e.getSource() == winners){

                //Displays error message if Prize is left blank
                if(prizeT.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,
                        "Please enter an amount for Prize.",
                        "Prize Error",
                        JOptionPane.ERROR_MESSAGE);

                }
                else
                {
                    double a = Double.parseDouble(prizeT.getText());
                    displayTickets(lm.getMajorWinners(a));
                }
            }

            //Prints report of information about tickets
            if (e.getSource() == reportItem){
                results.setText(lm.createReport());
            }

            //Prints report based on the State passed
            if (e.getSource() == stateItem){

                //Prints error message if State is left blank
                if (stateT.getText().equals(""))
                {

                    JOptionPane.showMessageDialog(null,
                        "Please enter a state.",
                        "State error",
                        JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    results.setText(lm.createReport(stateT.getText()));
                }
            }
            
            //Picks numbers
            if (e.getSource() == pickNum)
            {
                pickNumbers();
            }
        }
    }

    /*********************************************************************
    Set up the menu items
     *********************************************************************/
    private void setupMenus(){

        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        reportsMenu = new JMenu("Reports");
        stateItem = new JMenuItem("by State");
        reportItem = new JMenuItem("All Tickets");

        // assign action listeners
        ButtonListener ml = new ButtonListener();
        quitItem.addActionListener(ml);
        openItem.addActionListener(ml);
        stateItem.addActionListener(ml);
        reportItem.addActionListener(ml);

        // display menu components
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        reportsMenu.add(reportItem);
        reportsMenu.add(stateItem);    
        menus = new JMenuBar();

        menus.add(fileMenu);
        menus.add(reportsMenu);
        theGUI.setJMenuBar(menus);
    } 

    /*********************************************************************
    In response to the menu selection - open a data file
     *********************************************************************/
    private void openFile(){
        JFileChooser fc = new JFileChooser(new File(System.getProperty("user.dir")));
        int returnVal = fc.showOpenDialog(theGUI);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            //db.readTickets(filename);          
        }
    }       
}