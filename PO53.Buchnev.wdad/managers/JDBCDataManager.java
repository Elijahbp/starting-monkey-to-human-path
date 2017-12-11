package managers;

import modules.Note;
import modules.Owner;
import modules.User;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDataManager implements DataManager{

    Statement statement;

    @Override
    public String getNote(Owner owner, String title) throws RemoteException, SQLException {
        String result;

        ResultSet resultSet = statement.executeQuery("SELECT text FROM notes WHERE "+owner.getName()+" = ANY(users.name)" +
                                                                                                "AND "+title+" = title");
        result = resultSet.toString();
        return result;
    }

    @Override
    public void updateNote(Owner owner, String title, String newText) throws RemoteException, SQLException {
        statement.executeUpdate("UPDATE  notes SET text = "+newText+"WHERE "+owner.getName()+
                                                " = ANY(users.name) AND "+title+" = title");
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException, SQLException {
        statement.executeUpdate("UPDATE users_privileges SET privelege = "+newRights+"WHERE "+user.getName()+
                                                            " = ANY (users.name) AND text = "+noteTitle);
    }

    @Override
    public List<Note> getNotes(Owner owner) throws RemoteException, SQLException {
        List<Note> bufNotes = new ArrayList<>();

        ResultSet result = statement.executeQuery("SELECT * FROM notes WHERE "+owner.getName()+" = ANY(users.name)");
        while (result.next()){
        //Заместо users подставляю null, т.к. логика задания была изменена, либо я не дум дум, и чего-то не вижу
            bufNotes.add(new Note(result.getString("title"),owner,result.getString("text"),null
                                                ,result.getDate("creation_date")));
        }
        return bufNotes;
    }
}
