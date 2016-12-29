
/**
 * Project 4
 * Lottery Machine code
 * 
 * Author: Aaron Teague
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.text.NumberFormat;

public class LotteryMachine
{
    private ArrayList<LotteryTicket> ticket;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int megaball;

    /** Constructor instantiate instance variables */
    public LotteryMachine()
    {
        ticket = new ArrayList<LotteryTicket>();
        num1 = 0;
        num2 = 0;
        num3 = 0;
        num4 = 0;
        num5 = 0;
        megaball = 0;
    }

    /** Adds LotteryTicket object t to the LotteryTicket ArrayList ticket */
    public void addTicket (LotteryTicket t)
    {
        ticket.add(t);
    }

    /** Adds LotteryTicket object t to the LotteryTicket ArrayList ticket */
    public int countTickets()
    {
        return ticket.size();
    }

    private void pickNumbers()
    {
        //I was assisted by Jordan Zomerlei in programming this method

        //Creates an array list to hold numbers 1 - 75
        ArrayList<Integer> lottoarray = new ArrayList<Integer>();

        for(int i = 1; i <= 75; i++)
        {
            lottoarray.add(i);
        }

        //Creates random generator
        Random generator = new Random();

        int a = generator.nextInt(75);

        //Create and assign random numbers to integers and removes from lottoarray
        num1 = lottoarray.get(a);
        lottoarray.remove(a);
        a = generator.nextInt(74); 
        num2 = lottoarray.get(a);
        lottoarray.remove(a);
        a = generator.nextInt(73);
        num3 = lottoarray.get(a);
        lottoarray.remove(a);
        a = generator.nextInt(72);
        num4 = lottoarray.get(a);
        lottoarray.remove(a);
        a = generator.nextInt(71);
        num5 = lottoarray.get(a);
        lottoarray.remove(a);

        //sorts the numbers in ascending numbers
        int [] nums = {num1, num2, num3, num4, num5};
        Arrays.sort(nums);
        num1 = nums[0];
        num2 = nums[1];
        num3 = nums[2];
        num4 = nums[3];
        num5 = nums[4];
        megaball = generator.nextInt(15) + 1;
    }

    /** Counts how many ball numbers Lottery t contains */
    private int countMatches(LotteryTicket t)
    {
        int i = 0;
        if(t.hasBall(num1))
            i++;
        if(t.hasBall(num2))
            i++;
        if(t.hasBall(num3))
            i++;
        if(t.hasBall(num4))
            i++;
        if(t.hasBall(num5))
            i++;

        return i;
    }

    /**Calculates the pay out for each LotteryTicket */
    private void makePayouts()
    {   
        for(LotteryTicket t: ticket)
        {
            switch(countMatches(t))
            {

                case 5:
                if(t.hasMegaBall(megaball))
                {
                    t.setPrize(5000000);
                }
                else
                {
                    t.setPrize(1000000);
                }
                break;
                case 4:
                if(t.hasMegaBall(megaball))
                {
                    t.setPrize(5000);
                }
                else
                {
                    t.setPrize(500);
                }
                break;
                case 3:
                if(t.hasMegaBall(megaball))
                {
                    t.setPrize(50);
                }
                else
                {
                    t.setPrize(5);
                }
                break;
                case 2:
                if(t.hasMegaBall(megaball))
                {
                    t.setPrize(5);
                }
                break;
                case 1:
                if(t.hasMegaBall(megaball))
                {
                    t.setPrize(2);
                }
                break;
                case 0:
                if(t.hasMegaBall(megaball))
                {
                    t.setPrize(1);
                }
                break;
                default:
                t.setPrize(0);

            }

        }

    }

    /** Prints out the choosen numbers */
    private String formatNumbers()
    {
        return "Selected Numbers: " + num1 + " " + num2 + " " + num3 + " " + num4 + " " + num5 + "\t" + megaball;
    }

    /** Picks the winning numbers and makes the payouts to the tickets */
    public void drawTicket()
    {
        //calls the pickNumbers() method
        pickNumbers();
        //calls the makePayouts() method
        makePayouts();
    }

    /** Assigns the lotto balls and mega ball to the values passed in and makes payments */
    public void drawTicket(int b1, int b2, int b3, int b4, int b5, int m)
    {
        num1 = b1;
        num2 = b2;
        num3 = b3;
        num4 = b4;
        num5 = b5;
        megaball = m;
        makePayouts();
    }

    /** Reads the file, creates LotteryTicket objects and adds them to ArrayList ticket */
    public void readTickets(String filename)
    {
        try
        {
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            String info;

            while(sc.hasNext()) 
            {
                info = sc.nextLine();


                LotteryTicket x = new LotteryTicket(info);
                ticket.add(x);

            }
            sc.close();
        }
        catch(IOException e) 
        {
            System.out.println("Failed to read the data file: " +
                filename);
        }

    }

    /** Creates a report that calcualtes the number of tickets sold, average price, and prints the biggest winner */
    public String createReport()
    {
        double total = 0.0;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        for(LotteryTicket t: ticket)
        {
            total = total + t.getPrize();
        }

        return "Report for All Sales" + "\n" +
        formatNumbers() + "\n" +
            //Calculates total tickets
        "Tickets sold: " + ticket.size() + "\n" +
            //Calculates Average price
        "Average prize: " + fmt.format(total/ticket.size()) + "\n" + "\n" +
            //Finds biggest winner
        "Biggest Winnter" + "\n" +
        getBiggestWinner().toString();

    }

    /** Creates a report that calculates the number of tickets sold, average price, based on the selected State */
    public String createReport(String st)
    {
        double a = 0.0;
        int b = 0;
        double x = 0;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        for(LotteryTicket t: ticket)
        {
            if(t.getState().equals(st))
            {
                a = a + t.getPrize();
                b++;
            }
        }
        if(b == 0)
        {
            x = 0;
        }
        else
        {
            x = a/b;
        }

        return "Report for all sales in" + " " + st + "\n" +
        formatNumbers() + "\n" +
        "Tickets sold: " + b + "\n" +
        "Average prize: " + fmt.format(x) + "\n" + "\n" +
        "Biggest Winnter" + "\n" +
        getBiggestWinner().toString();

    }

    /** Finds the biggest winner */
    public LotteryTicket getBiggestWinner()
    {
        LotteryTicket biggest = ticket.get(0);
        for(LotteryTicket t: ticket)
        {
            if(t.getPrize() > biggest.getPrize())
                biggest = t;
        }
        return biggest;

    }

    /** Finds the oldest player */
    public LotteryTicket getOldestPlayer()
    {
        LotteryTicket oldest = ticket.get(0);
        for(LotteryTicket t: ticket)
        {
            if(t.getYear() < oldest.getYear())
            {
                oldest = t;
            }
            else if(t.getYear() == oldest.getYear())
            {
                if(t.getMonth() < oldest.getMonth())
                {
                    oldest = t;
                }
                else if(t.getMonth() == oldest.getMonth())
                {
                    if(t.getDay() < oldest.getDay())
                    {
                        oldest = t;
                    }
                }
            }

        }
        return oldest;
    }

    /** Finds winners that are greater than or equal to amount */
    public ArrayList <LotteryTicket> getMajorWinners(double amount)
    {
        ArrayList<LotteryTicket> x = new ArrayList<LotteryTicket>();
        for(LotteryTicket t: ticket)
        {
            if(t.getPrize() >= amount)
            {
                x.add(t);
            }

        }
        return x;
    }
    
    /** Draws tickets until a player wins a Jackpot. After ever drawing that doesn't yield a jackpot 1.5million dollars is added to the Jackpot */
    public String multipleGames()
    {
        //Holds the true/false if Jackpot was won
        boolean jackpotWinner = false;
        double prize = 5000000;
        //Keeps track of how many drawings have occured
        int i = 0;
        //Starts a new drawing
        drawTicket();
        //Continues until jackpot is won
        while(jackpotWinner == false)
        {
            //Itterates through the tickets
            for(LotteryTicket t: ticket)
            {
                //Checks if the LotteryTicket objects has all 5 matches and megaball.
                if(t.hasMegaBall(megaball) && countMatches(t) == 5)
                {
                    jackpotWinner = true;
                    t.setPrize(prize);
                }
            }
            //Increments the amount of drawings
            i++;
            //Adds 1.5million to the jackpot
            prize += 1500000;
            //Starts new drawing
            drawTicket();

        }
        return "Biggest Winner" + "\n" +
        "Number of games: " + i + "\n" +
        getBiggestWinner().toString();
    }
}