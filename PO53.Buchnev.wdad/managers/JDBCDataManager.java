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

public class JDBCDataManager implements DataManager{

    private PreparedStatement statement;
    private Connection connection;

    JDBCDataManager(DataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }
    @Override
    public String getNote(Owner owner, String title) throws RemoteException, SQLException {
        String result;
        statement = connection.prepareStatement("SELECT text FROM notes WHERE ? = ANY(users.name) AND ? = title");
        statement.setString(1,owner.getName());
        statement.setString(2,title);
        result = statement.executeQuery().toString();
        return result;
    }

    @Override
    public void updateNote(Owner owner, String title, String newText) throws RemoteException, SQLException {
        statement = connection.prepareStatement("UPDATE  notes SET text = ? WHERE ? = ANY(users.name) AND ? = title");
        statement.setString(1,newText);
        statement.setString(2,owner.getName());
        statement.setString(3,title);
        statement.executeUpdate();
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException, SQLException {
        statement = connection.prepareStatement("UPDATE users_privileges SET privelege = ? WHERE ? = ANY (users.name)AND text = ?");
        statement.setInt(1,newRights);
        statement.setString(2,user.getName());
        statement.setString(3,noteTitle);
        statement.executeUpdate();
    }

    @Override
    public List<Note> getNotes(Owner owner) throws RemoteException, SQLException {
        List<Note> bufNotes = new ArrayList<>();
        statement = connection.prepareStatement("SELECT * FROM Custom_View WHERE Author_id IN " +
                "(SELECT users.id FROM users WHERE users.name = ?) ORDER BY Title ASC");
        statement.setString(1,owner.getName());
        ResultSet result = statement.executeQuery();
        boolean closed = false;
        String bufTitle = ""; String noteTitles = null;
        String bufText = "";  String noteText = null;
        Date bufDate = null; Date noteCrDate = null;
        List<User> userList = new LinkedList<>();
        String userName; String userMail; int userPrivileges;
        while (result.next()){
            noteTitles = result.getString("Title");
            noteText = result.getString("Text");
            noteCrDate = result.getDate("Creation_Date");

            if (result.isFirst()) {
                bufTitle = noteTitles;
                bufText = noteText;
                bufDate = noteCrDate;
            }

            if (result.isLast()){
                userName = result.getString("User_name");
                userMail = result.getString("User_mail");
                userPrivileges = result.getInt("Privileges");
                userList.add(new User(userName,userMail,userPrivileges));

                bufNotes.add(new Note(bufTitle,owner,bufText,userList,bufDate));
                userList = null;
                break;
            }

            if (!bufTitle.equals(noteTitles)){
                bufNotes.add(new Note(bufTitle,owner,bufText,userList,bufDate));
                bufTitle = noteTitles; bufText = noteText; bufDate = noteCrDate;
                userList = new LinkedList<>();
            }
            userName = result.getString("User_name");
            userMail = result.getString("User_mail");
            userPrivileges = result.getInt("Privileges");
            userList.add(new User(userName,userMail,userPrivileges));


        }
        return bufNotes;
    }

}
