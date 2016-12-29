
/**
 * Write a description of class example here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class example
{ public static void main(String args[])
    {
        LotteryMachine m = new LotteryMachine();
        String info;
        String info2;
        String info3;
        
        info = "Jane, Smith, San Francisco, CA, 49401, 2/14/1983, 1,2,3,4,5,6";
        LotteryTicket tix = new LotteryTicket(info);
        m.addTicket(tix);
        
        info = "Aaron, Teague, Zeeland, MI, 49464, 7/26/1985, 8,27,35,46,25,6";
        LotteryTicket tix2 = new LotteryTicket(info);
        m.addTicket(tix2);
        
        info = "Barak, Obama, Washington DC, VA, 20004, 7/26/1985, 6,5,4,3,2,1";
        LotteryTicket tix3 = new LotteryTicket(info);
        m.addTicket(tix3);
        
        m.readTickets("TicketInfo.txt");
        
        m.drawTicket();
        
        System.out.println(m.createReport());
        System.out.println("");
        System.out.println(m.createReport("NY"));
        System.out.println(m.getOldestPlayer().toString());
        System.out.println("");
        System.out.println(m.getBiggestWinner().toString());
        System.out.println("");
        System.out.println(m.multipleGames());
    }
}
