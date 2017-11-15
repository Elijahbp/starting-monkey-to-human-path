package learn.rmi;

import data.managers.PreferencesManager;
import learn.rmi.XmlDataManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    static final private String SECURITY_POLICY_PATH = "PO53.Buchnev.wdad.resources.security_policy.security.policy";
//    static final private String CODEBASE_URL = "E:\\Project\\starting-monkey-to-human-path\\PO53.Buchnev.wdad\\data\\managers\\";
//    static final private String REGISTRY_HOST = "localhost";
//    static final private int REGISTRY_PORT = 1099 ;
//    static final private int EXECUTOR_PORT = 53895 ;
//    static final private String EXECUTOR_NAME = "XmlDataManagerImpl" ;
//    static private boolean hasNextOperation = true ;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        //System.out.println("preparing");
//        System.setProperty("java.rmi.server.codebase",CODEBASE_URL);
//        System.setProperty("java.rmi.server.useCodebaseOnly","true");
          System.setProperty("java.security.policy",SECURITY_POLICY_PATH);

       // System.out.println("prepared");

        System.setSecurityManager(new SecurityManager());
        Registry registry = LocateRegistry.getRegistry("localhost");
        XmlDataManager xmlDataManager = (XmlDataManager) registry.lookup("XmlDataManagerImpl");

        System.out.println(xmlDataManager.getDoubleK(2));
    }
}
