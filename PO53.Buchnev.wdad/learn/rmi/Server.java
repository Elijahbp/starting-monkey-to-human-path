package learn.rmi;

import managers.PreferencesManager;
import utils.PreferencesConstantManager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static utils.PreferencesConstantManager.CREATE_REGISTRY;

public class Server {
    public final static String EXECUTOR_NAME = "XmlDataManagerImpl";
    public final static int EXECUTOR_PORT = 20349;
    public static void main(String[] args) throws Exception {


        PreferencesManager preferencesManager = new PreferencesManager();
        Registry registry = null;
        System.setProperty("java.rmi.server.logCalls","true");
        int RegPort =  Integer.parseInt(preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_PORT));

        if (preferencesManager.getProperty(PreferencesConstantManager.CREATE_REGISTRY).equals("yes")){
            registry = LocateRegistry.createRegistry(RegPort);
        }else if (preferencesManager.getProperty(PreferencesConstantManager.CREATE_REGISTRY).equals("no")){
            registry = LocateRegistry.getRegistry(RegPort);
        }
        System.setProperty("java.security.policy",preferencesManager.getProperty(PreferencesConstantManager.PATH_SECURITY_POLICY));
        System.setSecurityManager(new SecurityManager());
        if (registry != null) {
            XmlDataManagerImpl xdmi = new XmlDataManagerImpl();
            UnicastRemoteObject.exportObject(xdmi,EXECUTOR_PORT);
            registry.rebind(EXECUTOR_NAME, xdmi);
            preferencesManager.addBindedObject(EXECUTOR_NAME,XmlDataManager.class.getName());
            preferencesManager.writeXml();
            System.out.println("YEAP!");
        }
    }

}
