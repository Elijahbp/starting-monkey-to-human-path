package learn.rmi;

import data.managers.PreferencesManager;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executor;
import java.util.prefs.Preferences;

public class Server {
    static final private String SECURITY_POLICY_PATH = "PO53.Buchnev.wdad.resources.security_policy.security.policy";
    static final private String CODEBASE_URL = "E:\\Project\\starting-monkey-to-human-path\\PO53.Buchnev.wdad\\data\\managers\\";
    static final private String REGISTRY_HOST = "localhost";
    static final private int REGISTRY_PORT = 1099 ;
    static final private int EXECUTOR_PORT = 40562 ;
    static final private String EXECUTOR_NAME = "XmlDataManagerImpl" ;
    static private boolean hasNextOperation = true ;

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
       //PreferencesManager preferencesManager = new PreferencesManager();
       System.out.println("preparing");
//       System.setProperty("java.rmi.server.codebase",CODEBASE_URL);
//       System.setProperty("java.rmi.server.logCalls","true");
       System.setProperty("java.security.policy",SECURITY_POLICY_PATH);
       System.setSecurityManager(new SecurityManager());
       System.out.println("prepared");


       XmlDataManager xmlDataManager = new XmlDataManagerImpl();
       XmlDataManager xdmi = (XmlDataManager) UnicastRemoteObject.exportObject(xmlDataManager,0);
       Registry registry = LocateRegistry.createRegistry(1099);

       System.out.println("exporting object...");
       registry.bind(EXECUTOR_NAME,xdmi);
       System.out.println("idl-ing");
    }
}
