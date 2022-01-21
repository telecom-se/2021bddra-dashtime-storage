package tse.projects;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class DatabaseApplication extends UnicastRemoteObject implements DatabaseServerInterface {

    private static QueryService queryService;

    public DatabaseApplication() throws RemoteException {
        super(0);
    }

    public static void main(String[] args) throws Exception {
        DatabaseApplication.queryService = new QueryService();

        // FAKE DATA
        DatabaseApplication.queryService.generateFakeData();

        System.out.println("AionDB server started");

        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("AionDB registry created");
        } catch (RemoteException e) {
            System.out.println("AionDB registry already exists");
        }

        DatabaseApplication database = new DatabaseApplication();

        Naming.rebind("//localhost/AionDB", database);

        System.out.println("Server is running...");
    }

    @Override
    public String runQuery(String queryStr) throws RemoteException {
        return DatabaseApplication.queryService.runQuery(queryStr);
    }
}
