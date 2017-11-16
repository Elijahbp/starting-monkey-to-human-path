package learn.rmi;

import managers.PreferencesManager;
import utils.PreferencesConstantManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static utils.PreferencesConstantManager.*;

public class Client {
    public final static String EXECUTOR_NAME = "XmlDataManagerImpl";

    public static void main(String[] args) throws Exception {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        System.setProperty("java.security.policy", PATH_SECURITY_POLICY);
        Registry registry = LocateRegistry.getRegistry(preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_ADDRESS),
                Integer.parseInt(preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_PORT)));
        XmlDataManager Manager = (XmlDataManager) registry.lookup("XmlDataManager");

        System.out.println(Manager.getNote());
    }
}
