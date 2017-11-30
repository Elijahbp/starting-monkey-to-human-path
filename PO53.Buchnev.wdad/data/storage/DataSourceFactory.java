import managers.PreferencesManager;
import oracle.jdbc.pool.OracleDataSource;
import utils.PreferencesConstantManager;

import java.sql.*;

import static com.sun.xml.internal.ws.encoding.xml.XMLMessage.createDataSource;

public class DataSourceFactory {


    private static OracleDataSource dataSource;

    public DataSourceFactory(){}
        public static javax.sql.DataSource createDataSource() throws Exception {
        dataSource = new OracleDataSource();
        PreferencesManager preferencesManager = new PreferencesManager();
        String className = preferencesManager.getProperty(PreferencesConstantManager.DS_CLASS_NAME);
        String driverType = preferencesManager.getProperty(PreferencesConstantManager.DS_DRIVER_TYPE);
        String hostName = preferencesManager.getProperty(PreferencesConstantManager.DS_HOST);
        int port = Integer.parseInt(preferencesManager.getProperty(PreferencesConstantManager.DS_PORT));
        String dbName = preferencesManager.getProperty(PreferencesConstantManager.DS_DATABASE_NAME);
        String user = preferencesManager.getProperty(PreferencesConstantManager.DS_USER);
        String password = preferencesManager.getProperty(PreferencesConstantManager.DS_USER);

        dataSource = (OracleDataSource) createDataSource(className,driverType,hostName,port,dbName,user,password);
        return dataSource;
    }
    public static javax.sql.DataSource createDataSource(String className,String driverType,String host, int port,
                                                        String dbName, String user,String password) throws ClassNotFoundException, SQLException {
        dataSource = new OracleDataSource();
        dataSource.setDataSourceName(className);
        dataSource.setDriverType(driverType);
        dataSource.setServerName(host);
        dataSource.setPortNumber(port);
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
