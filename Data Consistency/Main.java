import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String args[])throws Exception {
        int i,j,k;
        /*
         * Initialising the Reader Writer threads..
         * */
        Writer writers[]=new Writer[20];
        Reader readers[]=new Reader[20];
        System.out.println("Enter The Number Of Reader and Writer Threads");
        Scanner s=new Scanner(System.in);
        j=s.nextInt();
        i=s.nextInt();
        FileOutputStream fw=new FileOutputStream("File.txt",true);
        /*
         * Setting the monitor on the shared resource i.e. the text file
         * */
        System.out.println("Initializing Monitor");
        Monitor m=new Monitor(fw);
        System.out.println("Starting The Threads:");
        for(k=0;k<j;k++) {
            readers[k]=new Reader(m,new FileReader("File.txt"));
            readers[k].start();
        }
        for(k=0;k<i;k++) {
            writers[k]=new Writer(m);
            writers[k].start();

        }
    }
}
