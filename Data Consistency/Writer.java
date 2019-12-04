import java.io.FileReader;

public class Writer extends Thread {

    private Monitor m;
    Writer(Monitor m) {
        this.m=m;
    }
    public void run() {
        try {
            m.write();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
