package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.Naming;
import java.time.Duration;
import java.time.Instant;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            Instant ts = Instant.now();
            while (true) {
                Integer n = service.pollElem();
                //int fibCount = service.pollElem();
                if (n == null) {
                    System.out.println("Received none! + queue is empty");
                    break;
                } else {
                    //System.out.println("Received: " + n);
                    //service.addElem(n);
                    File f1=new File("C:\\Users\\ulpan\\Downloads\\dc-task-4-new\\src\\main\\java\\example\\file01.txt");
                    String[] words=null;
                    int wc=0;
                    FileReader fr = new FileReader(f1);
                    BufferedReader br = new BufferedReader(fr);
                    String s;
                    while((s=br.readLine())!=null)
                    {
                        words=s.split(" ");
                        wc=wc+words.length;
                    }
                    fr.close();
                    service.countNumberOfWords(wc);
                }
            }
            Instant te = Instant.now();
            System.out.println("Time = " + Duration.between(ts, te).toNanos() / 1e9 + " sec.");

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}