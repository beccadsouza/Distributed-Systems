import java.io.FileReader;

public class Reader extends Thread {

    private FileReader fr;
    private Monitor m;
    Reader(Monitor m, FileReader fr) {
        this.fr=fr;
        this.m=m;
    }
    public void run() {
        try {
            m.read(fr);
            fr.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

}
