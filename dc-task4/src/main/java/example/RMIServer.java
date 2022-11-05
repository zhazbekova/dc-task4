package example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        int numberOfTasks = 1000;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Service service = new ServiceImpl();

            System.out.println("Enter the number till which count fibonacci sequence: " + "\n");
            //Scanner sc = new Scanner(System.in);
            //int n = sc.nextInt();
//            // Init service
            for (int i = 0; i < numberOfTasks; i++) {
                service.addElem(i * 100 + 1);
            }

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}