import java.rmi.*;
import java.util.*;
public interface RMIHellowInterface extends Remote {
    public String SayHellow(String name)throws RemoteException;
    public String Restaurantcheck(String restaurant)throws RemoteException;
    public String Menucheck(String restaurant,String item)throws RemoteException;
    public String itemPresent(String food) throws RemoteException;
}
