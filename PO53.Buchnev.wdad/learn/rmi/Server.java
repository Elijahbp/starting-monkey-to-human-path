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
    public static void main(String[] args) throws Exception {
        PreferencesManager preferencesManager = new PreferencesManager();
        preferencesManager = new PreferencesManager();
        Registry registry = null;
        if (preferencesManager.getProperty("CREATE_REGISTRY") == "yes"){
            registry = LocateRegistry.createRegistry(Integer.parseInt(PreferencesConstantManager.REGISTRY_PORT));
        }else if (preferencesManager.getProperty("CREATE_REGISTRY") == "no"){
            registry = LocateRegistry.getRegistry(PreferencesConstantManager.REGISTRY_PORT);
        }
        System.setProperty("java.security.policy", PreferencesConstantManager.PATH_SECURITY_POLICY);
        if (registry != null) {
            XmlDataManager xmlDataManager = new XmlDataManagerImpl();
            XmlDataManager xdmi = (XmlDataManager) UnicastRemoteObject.exportObject(xmlDataManager, Integer.parseInt(PreferencesConstantManager.REGISTRY_PORT));
            registry.rebind(EXECUTOR_NAME, xdmi);
            preferencesManager.addBindedObject("XmlDataManager",xmlDataManager.getClass().getName());
            preferencesManager.writeXml();
        }
    }

}
