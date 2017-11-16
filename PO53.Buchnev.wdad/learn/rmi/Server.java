package learn.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public final static String PATH_SECURITY_POLICY = "E:\\Project\\starting-monkey-to-human-path\\PO53.Buchnev.wdad\\resources\\security_policy\\security.policy";
    public final static int REGISTRY_PORT = 1099;
    public final static String EXECUTOR_NAME = "XmlDataManagerImpl";

    public static void main(String[] args) throws RemoteException {

        System.setProperty("java.security.policy",PATH_SECURITY_POLICY);
        Registry registry = null;
        LocateRegistry.createRegistry(REGISTRY_PORT);
        if (registry != null){
            XmlDataManager xmlDataManager = new XmlDataManagerImpl();
            XmlDataManager xdmi = (XmlDataManager) UnicastRemoteObject.exportObject(xmlDataManager,REGISTRY_PORT);
            registry.rebind(EXECUTOR_NAME,xdmi);
        }
    }
}
