package learn.rmi.Clent;

import managers.DataManager;
import managers.PreferencesManager;
import modules.Owner;
import utils.PreferencesConstantManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public final static String EXECUTOR_NAME = "XmlDataManagerImpl";

    public static void main(String[] args) throws Exception {
        
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        System.setProperty("java.security.policy", preferencesManager.getProperty(PreferencesConstantManager.PATH_SECURITY_POLICY));
        System.setSecurityManager(new SecurityManager());
        Registry registry = LocateRegistry.getRegistry(preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_ADDRESS),
                Integer.parseInt(preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_PORT)));
        DataManager Manager = (DataManager) registry.lookup(EXECUTOR_NAME);

        Owner owner = new Owner("Ilya","bip250997@gmail.com");
        System.out.println(Manager.getNotes(owner).toString());
    }
}
