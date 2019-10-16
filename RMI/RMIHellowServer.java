import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.*;

public class RMIHellowServer extends UnicastRemoteObject implements RMIHellowInterface {
    boolean flag=false;
    static HashMap<String,ArrayList<String>> obj=new HashMap<>();
    static ArrayList<String> food=new ArrayList<>();

    public RMIHellowServer() throws RemoteException {
        super();
    }

    public String SayHellow(String name) throws RemoteException {
        return "Hello   "+name;
    }
    public String Restaurantcheck(String restaurant) {
        String ans="";
        if(obj.containsKey(restaurant)) {
            flag=true;
            ans="Restaurant is present, Here's your menu!!\n\n";
            ans+= obj.get(restaurant).toString();
            //ans+=Menucheck(item,restaurant);
        }
        else
            ans="Restaurant not present";
        return ans;
    }
    public String Menucheck(String item,String restaurant) {
        String ans="";
        if(obj.get(restaurant).contains(item))
            ans="Item present in the menu";
        else
            ans="Item NOT present in the menu";
        return ans;
    }
    public String itemPresent(String food) throws RemoteException {
        for(Map.Entry i:obj.entrySet()){
            if(((ArrayList)i.getValue()).contains(food))
                return String.valueOf(i.getKey());
        }
        return "Restaurant not found";
    }
    public static void main(String[] args) {

        final String HOST= "rmi://localhost:1234//HellowServer";
        RMIHellowServer Server;

        //HashMap<String,ArrayList<String>> obj=new HashMap<String,ArrayList<String>>();
        //ArrayList<String> food=new ArrayList<>();
        food.add("CheesePizza");
        food.add("FarmhousePizza");
        food.add("CheeseBurst");
        obj.put("Dominos",food);
        System.out.println("Hashmap = "+obj);

        try {
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            System.out.println("Starting HellowServer........");
            Server = new RMIHellowServer();
            System.out.println("Hellow Server Instance Created.......");

            LocateRegistry.createRegistry(1234);

            Naming.bind(HOST,Server);

            System.out.println("Hellow Server bound to registry succesfully..");
            System.out.println(" Hello Server Ready....");
        }

        catch(Exception ex) {
            System.out.println("Exception   :"+ex.getMessage());
        }
    }
}
