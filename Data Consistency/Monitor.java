import java.io.*;
import java.util.Random;

class Monitor {

    private FileOutputStream fw;
    private Random random = new Random();
    private int readerCount=0;
    private boolean busy=false,print=true;

    Monitor(FileOutputStream fwr){ this.fw=fwr; }
    /*
     * Every monitor has a set of procedures..
     * */
    private void acquire() { this.busy=true; }
    private void release() { this.busy=false; }
    private void acquirePrint() { this.print=false; }
    private void releasePrint() { this.print=true; }
    private void incread() { this.readerCount++; }
    private void decread() { this.readerCount--; }

    void read(FileReader fr)throws Exception {
        int array[]=new int[10000];
        while(this.busy||this.readerCount>1)
            Thread.sleep(500);
        if(this.readerCount==0)
            this.acquire();
        this.incread();
        int j = -1;
        while(true){
            int i=fr.read();
            if(i==-1){
                array[++j]=i;
                break;
            }else array[++j]=i;
        }
        if(!this.print)
            Thread.sleep(100);
        this.acquirePrint();
        System.out.print("\nReading Data from File...\nData Read is : ");
        int k=0;
        while(array[k]!=-1)
            System.out.printf("%c",array[k++]);
        System.out.println();
        this.releasePrint();
        this.decread();
        if(this.readerCount==0)
            this.release();
    }

    void write()throws Exception {
        while(this.busy||this.readerCount>0)
            Thread.sleep(500);
        this.acquire();
        char x = (char)(random.nextInt(26)+65);
        System.out.println("\nWriting Data to File...\nData Written is : "+x);
        try {
            this.fw.write(x);
        }
        catch(Exception e) { e.printStackTrace(); }
        this.release();
    }

}
