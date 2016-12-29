
/**
 * Write a description of class LotteryTicket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//import number format
import java.text.NumberFormat;

public class LotteryTicket
{
    /** declare variables */
    private String first;
    private String last;
    private int day;
    private int month;
    private int year;
    private String city;
    private String state;
    private int zip;
    private int b1;
    private int b2;
    private int b3;
    private int b4;
    private int b5;
    private int megaball;
    private double prize;
    
    /** initialize variables */
    public LotteryTicket(String info)
    {
        // initialise instance variables
        String [] tokens = info.split(",|/");
        first = tokens[0].trim();
        last = tokens[1].trim();
        city = tokens[2].trim();
        state = tokens[3].trim();
        zip = Integer.parseInt(tokens[4].trim());
        month = Integer.parseInt(tokens[5].trim());
        day = Integer.parseInt(tokens[6].trim());
        year = Integer.parseInt(tokens[7].trim());
        b1 = Integer.parseInt(tokens[8].trim());
        b2 = Integer.parseInt(tokens[9].trim());
        b3 = Integer.parseInt(tokens[10].trim());
        b4 = Integer.parseInt(tokens[11].trim());
        b5 = Integer.parseInt(tokens[12].trim());
        megaball = Integer.parseInt(tokens[13].trim());
        
    }
    
    /** Get first name. */
    public String getFirst()
    {
        return first;
    }
    
    /** Get last name. */
    public String getLast()
    {
        return last;
    }
    
    /** Get the city */
    public String getCity()
    {
        return city;
    }
    
    /** Get the state. */
    public String getState()
    {
        return state;
    }
    
    /** Get the zipcode */
    public int getZipcode()
    {
        return zip;
    }
    
    /** Get the day of the DOB */
    public int getDay()
    {
        return day;
    }
    
    /** Get the month of the DOB */
    public int getMonth()
    {
        return month;
    }
    
    /** Get the year of the DOB */
    public int getYear()
    {
        return year;
    }
    
    /** Get the prize */
    public double getPrize()
    {
        return prize;
    }
    
    /** Set the prize */
    public void setPrize(double amount)
    {
        if( amount > 0)
        prize = amount;
    }
    
    /** Checks if the value sent matches and of the ball values */
    public boolean hasBall(int val)
    {
        if(val == b1 || val == b2 || val == b3 || val == b4 || val == b5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /** Checks if the value matches the Megaball value. */
    public boolean hasMegaBall(int val)
    {
        if( val == megaball)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /** Allows the to print the contents of the object in plain text */
    public String toString( )
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return first + " " + last + "\n" + city + ", " + state + " " + zip + "\n" + b1 + " " + b2 +
                " " + b3 + " " + b4 + " " + b5 + "\t" + megaball + "\n" +
                "Prize: " + fmt.format(prize);
    }
    
    /** The main tests all aspect of LotteryTicket */
    public static void main(String args[])
    {
        LotteryTicket a = new LotteryTicket("Amy,Zu,Phoenix,AZ,78234,4/20/1960,4,12,15,36,67,12");
        a.setPrize(1000);
        System.out.println(a.toString( ));
        System.out.println(a.getFirst()); 
        System.out.println(a.getLast());
        System.out.println(a.getCity());
        System.out.println(a.getState());
        System.out.println(a.getZipcode());
        System.out.println(a.getDay());
        System.out.println(a.getMonth());
        System.out.println(a.getYear());
        a.setPrize(100);
        System.out.println(a.getPrize());
        System.out.println(a.hasBall(12));
        
    }
}
