import java.rmi.*;
import java.util.*;
public class RMIHellowClient {
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);
        final String HOST= "rmi://localhost:1234//HellowServer";
        String name, result,res,item,ans;
        RMIHellowInterface Server;

        try {
            System.out.println("Enter your name");
            name=sc.next();
            System.out.println("Start HelloClient..........");

            Server=(RMIHellowInterface)Naming.lookup(HOST);
            System.out.println("Look_up successfull.....\n\n");

            result = Server.SayHellow(name);
            System.out.println(result);
            System.out.println("1.Search by restaurant\n2. Search by Food\n");
            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Enter the restaurant name");
                    res=sc.next();
                    //System.out.println("Enter the item name");
                    //item=sc.next();
                    ans=Server.Restaurantcheck(res);
                    System.out.println(ans);
                    break;
                case 2:
                    System.out.println("Enter the Food name");
                    String res1=sc.next();
                    String rest = Server.itemPresent(res1);
                    System.out.println("Available restaurant:"+rest);
            }
        }
        catch(Exception ex) {
            System.out.println("Exception    "+ex.getMessage());
            ex.printStackTrace();

        }
    }
}
