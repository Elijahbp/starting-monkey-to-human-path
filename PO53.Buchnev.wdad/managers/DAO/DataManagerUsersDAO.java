package managers.DAO;

import data.modules.User;
import data.storage.DataSourceFactory;
import managers.JDBC.PreferencesManager;

import javax.sql.DataSource;
import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

public class DataManagerUsersDAO implements UsersDAO{
    DataSource dataSource;
    Connection connection;

    public DataManagerUsersDAO() throws Exception {
        this.dataSource = DataSourceFactory.createDataSource();
    }

    @Override
    public int insertUser(User user) throws SQLException {
        connection = dataSource.getConnection();
        java.sql.PreparedStatement preparedStatement =  connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
        preparedStatement.setInt(1,user.getId());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setString(3,user.getMail());
        preparedStatement.setString(4,user.getMail());

        connection.close();
        return 0;
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        connection = dataSource.getConnection();
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE users.id =?");
        preparedStatement.setInt(1,user.getId());
        preparedStatement.close();
        connection.close();
        return true;
    }

    @Override
    public User findUser(int id) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement userStatement = connection.prepareStatement("SELECT * FROM users WHERE users.id = ?");
        userStatement.setInt(1,id);
        ResultSet resultUser = userStatement.executeQuery();

        String name = resultUser.getString("name");
        String mail = resultUser.getString("mail");
        String encrypted_password = resultUser.getString("encrypted_password");
        User user = new User(id,name,mail,encrypted_password);

        PreparedStatement rightsStatement = connection.prepareStatement(
                "SELECT users_privileges.notes_id AS 'n_id',users_privileges.privilege AS 'pr' FROM users_privileges" +
                        " WHERE users_privileges.users_id = ?");
        rightsStatement.setInt(1,id);
        ResultSet resultRights = rightsStatement.executeQuery();
        HashMap<Integer,Integer> mapRights = new HashMap<>();
        int idNote,rights;
        while (resultRights.next()){
            idNote = resultRights.getInt("n_id");
            rights = resultRights.getInt("pr");
            mapRights.put(idNote,rights);
        }
        user.setRights(mapRights);
        rightsStatement.close();
        userStatement.close();
        connection.close();
        return user;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement userStatement = connection.prepareStatement("UPDATE users SET users.id = ?, users.name = ?" +
                ",users.mail = ?, users.encrypted_password = ? WHERE users.id = ?");
        userStatement.setInt(1,user.getId());
        userStatement.setString(2,user.getName());
        userStatement.setString(3,user.getMail());
        userStatement.setString(4,user.getEncrypted_password());

        PreparedStatement rigtsUserStatement = connection.prepareStatement("UPDATE users_privileges SET users_privileges.users_id = ?," +
                " users_privileges.notes_id = ?,users_privileges.privilege = ?");
        for (Entry<Integer,Integer> nk :user.getRights().entrySet()) {
            rigtsUserStatement.setInt(1,user.getId());
            rigtsUserStatement.setInt(2,nk.getKey());
            rigtsUserStatement.setInt(3,nk.getValue());
            rigtsUserStatement.executeUpdate();
        }
        userStatement.close();
        rigtsUserStatement.close();
        connection.close();
        return false;
    }

    @Override
    public boolean saveOrUpdateUser(User user) throws SQLException {
        updateUser(user);
        return false;
    }

    @Override
    public Collection<User> findUsersByName(Collection<String> names) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE users.name = ?");

        List<User> userList = new LinkedList<>();
        int id;
        String mail,password;
        PreparedStatement rightsStatement = connection.prepareStatement("SELECT users_privileges.notes_id AS 'n_id',users_privileges.privilege AS 'pr' FROM users_privileges" +
                " WHERE users_privileges.users_id = ?");
        ResultSet resultRights;
        HashMap<Integer,Integer> mapRights = new HashMap<>();
        User user;
        int note_id,privileges;

        for (String bName:names) {
            preparedStatement.setString(1,bName);
            ResultSet resultSet = preparedStatement.executeQuery();

            id = resultSet.getInt("id");
            mail = resultSet.getString("mail");
            password = resultSet.getString("encrypted_password");
            rightsStatement.setInt(1,id);
            resultRights = rightsStatement.executeQuery();
            while (resultRights.next()){
                note_id = resultRights.getInt("n_id");
                privileges = resultRights.getInt("pr");
                mapRights.put(note_id,privileges);
            }
            user = new User(id,bName,mail,password);
            user.setRights(mapRights);
            userList.add(user);
            mapRights.clear();
        }
        preparedStatement.close();
        rightsStatement.close();
        connection.close();
        return userList;
    }

    @Override
    public Collection<User> findUsersByMail(Collection<String> mails) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE users.mail = ?");

        List<User> userList = new LinkedList<>();
        int id;
        String name,password;
        PreparedStatement rightsStatement = connection.prepareStatement("SELECT users_privileges.notes_id AS 'n_id',users_privileges.privilege AS 'pr' FROM users_privileges" +
                " WHERE users_privileges.users_id = ?");
        ResultSet resultRights;
        HashMap<Integer,Integer> mapRights = new HashMap<>();
        User user;
        int note_id,privileges;

        for (String bMail:mails) {
            preparedStatement.setString(1,bMail);
            ResultSet resultSet = preparedStatement.executeQuery();

            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            password = resultSet.getString("encrypted_password");
            rightsStatement.setInt(1,id);
            resultRights = rightsStatement.executeQuery();
            while (resultRights.next()){
                note_id = resultRights.getInt("n_id");
                privileges = resultRights.getInt("pr");
                mapRights.put(note_id,privileges);
            }
            user = new User(id,name,bMail,password);
            user.setRights(mapRights);
            userList.add(user);
            mapRights.clear();
        }
        preparedStatement.close();
        rightsStatement.close();
        connection.close();
        return null;
    }
}
