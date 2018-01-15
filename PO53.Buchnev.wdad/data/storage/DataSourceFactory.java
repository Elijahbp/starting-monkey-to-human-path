package data.storage;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import managers.JDBC.PreferencesManager;
import utils.PreferencesConstantManager;

import java.sql.*;

public class DataSourceFactory {


    private static MysqlDataSource dataSource;

    public DataSourceFactory(){}
        public static javax.sql.DataSource createDataSource() throws Exception {
        dataSource = new MysqlDataSource();
        PreferencesManager preferencesManager = new PreferencesManager();
        String className = preferencesManager.getProperty(PreferencesConstantManager.DS_CLASS_NAME);
        String driverType = preferencesManager.getProperty(PreferencesConstantManager.DS_DRIVER_TYPE);
        String hostName = preferencesManager.getProperty(PreferencesConstantManager.DS_HOST);
        int port = Integer.parseInt(preferencesManager.getProperty(PreferencesConstantManager.DS_PORT));
        String dbName = preferencesManager.getProperty(PreferencesConstantManager.DS_DATABASE_NAME);
        String user = preferencesManager.getProperty(PreferencesConstantManager.DS_USER);
        String password = preferencesManager.getProperty(PreferencesConstantManager.DS_PASSWORD);

        dataSource = (MysqlDataSource) createDataSource(className,driverType,hostName,port,dbName,user,password);
        return dataSource;
    }
    public static javax.sql.DataSource createDataSource(String className,String driverType,String host, int port,
                                                        String dbName, String user,String password) {


        String url = "jdbc:"+driverType+"://"+host+":"+port+"/"+dbName;

        //Class.forName(className);
        dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}
