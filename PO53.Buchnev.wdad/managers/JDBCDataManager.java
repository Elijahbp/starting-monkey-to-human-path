package managers;

import data.modules.Note;
import data.modules.Owner;
import data.modules.User;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JDBCDataManager implements DataManager {

    private PreparedStatement statement;
    private DataSource dataSource;
    private Connection connection;

    JDBCDataManager(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    @Override
    public String getNote(Owner owner, String title) throws RemoteException, SQLException {
        String result;
        connection = dataSource.getConnection();
        statement = connection.prepareStatement("SELECT text FROM notes WHERE author_id = ?  AND ? = title");
        statement.setInt(1, owner.getId());
        statement.setString(2, title);
        result = statement.executeQuery().toString();
        connection.close();
        return result;
    }

    @Override
    public void updateNote(Owner owner, String title, String newText) throws RemoteException, SQLException {
        connection = dataSource.getConnection();
        statement = connection.prepareStatement("UPDATE  notes SET text = ? WHERE ? = author_id AND ? = title");
        statement.setString(1, newText);
        statement.setString(2, owner.getName());
        statement.setString(3, title);
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException, SQLException {
        connection = dataSource.getConnection();
        statement = connection.prepareStatement("UPDATE users_privileges SET privelege = ? WHERE ? = user_id " +
                "AND text IN (SELECT text FROM notes WHERE text = ?)");
        statement.setInt(1, newRights);
        statement.setString(2, user.getName());
        statement.setString(3, noteTitle);
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public List<Note> getNotes(Owner owner) throws RemoteException, SQLException {
        connection = dataSource.getConnection();

        List<Note> bufNotes = new ArrayList<>();
        statement = connection.prepareStatement("SELECT * FROM notes WHERE Author_id = ?");
        statement.setInt(1, owner.getId());
        ResultSet result = statement.executeQuery();
        boolean closed = false;
        int bufNoteIndex = 0;
        String bufTitle = "";
        String bufText = "";
        Date noteCrDate = null;

        List<User> userList = new LinkedList<>();
        int bufUserId = 0;
        String bufUserName = "";
        String bufUserMail = "";
        int bufUserPrivileges = 0;
        while (result.next()) {
            userList.clear();
            bufNoteIndex = result.getInt("id");
            bufTitle = result.getString("title");
            bufText = result.getString("text");
            noteCrDate = result.getDate("creation_date");
            PreparedStatement statementUser = connection.prepareStatement("SELECT users.id, users.name,users.mail,users_privileges.privilege " +
                    "FROM users INNER JOIN users_privileges ON users_privileges.users_id = users.id " +
                    "WHERE users_privileges.notes_id = ?");
            statementUser.setInt(1, bufNoteIndex);
            ResultSet resultSetUsers = statementUser.executeQuery();
            while (resultSetUsers.next()) {
                bufUserId = resultSetUsers.getInt("id");
                bufUserName = resultSetUsers.getString("name");
                bufUserMail = resultSetUsers.getString("mail");
                bufUserPrivileges = resultSetUsers.getInt("privilege");
                userList.add(new User(bufUserId, bufUserName, bufUserMail, bufUserPrivileges));
            }
            statementUser.close();
            bufNotes.add(new Note(bufTitle, owner, bufText, userList, noteCrDate));
        }
        connection.close();
        return bufNotes;
    }
}
