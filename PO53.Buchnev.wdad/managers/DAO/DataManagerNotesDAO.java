package managers.DAO;

import data.modules.Note;
import data.modules.Owner;
import data.modules.User;
import data.storage.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DataManagerNotesDAO implements NotesDAO{
    DataSource dataSource;
    Connection connection;

    public DataManagerNotesDAO() throws Exception {
        this.dataSource = DataSourceFactory.createDataSource();
    }

    @Override
    public boolean insertNote(Note note) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement insertNoteStatement = connection.prepareStatement("INSERT INTO notes" +
                " VALUES (?,?,?,?,?)");
        insertNoteStatement.setInt(1,note.getId());
        insertNoteStatement.setString(2,note.getTitle());
        insertNoteStatement.setDate(3, (Date) note.getDate());
        insertNoteStatement.setString(4,note.getText());
        insertNoteStatement.setInt(5,note.getId());
        insertNoteStatement.executeQuery();

        for (User user:note.getUsers()) {
            PreparedStatement insertUsersPrivileges = connection.prepareStatement("INSERT INTO users_privileges (privilege,users_id,notes_id)" +
                    " VALUES(?,?,?)");
            insertUsersPrivileges.setInt(1,user.getRights().get(note.getId()));
            insertUsersPrivileges.setInt(2,user.getId());
            insertUsersPrivileges.setInt(3,note.getId());
            insertUsersPrivileges.executeQuery();
            insertUsersPrivileges.close();
        }
        insertNoteStatement.close();
        connection.close();
        return true;
    }

    @Override
    public boolean deleteNote(Note note) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement deleteNoteStatement = connection.prepareStatement("DELETE FROM notes WHERE notes.id = ?");
        deleteNoteStatement.setInt(1,note.getId());
        deleteNoteStatement.executeQuery();
        deleteNoteStatement.close();
        connection.close();
        return true;
    }

    @Override
    public Note findNote(int idNote) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement findNoteStatement = connection.prepareStatement(
                "SELECT notes.id AS 'note_id',notes.title AS 'note_title'" +
                        ",notes.creation_date AS 'note_creationDate',notes.text AS 'note_text',users.id AS 'author_id'" +
                        ",users.name AS 'author_name',users.mail AS 'author_mail', users.encrypted_password AS 'author_password'" +
                        " FROM notes LEFT OUTER JOIN users ON notes.author_id = users.id WHERE notes.id = ?");
        findNoteStatement.setInt(1,idNote);
        ResultSet resultNote = findNoteStatement.executeQuery();
        String title = resultNote.getString("note_title");
        Date creationDate = resultNote.getDate("note_creationDate");
        String text = resultNote.getString("note_text");


        int ownerId = resultNote.getInt("author_id");
        String ownerName = resultNote.getString("author_name");
        String ownerMail = resultNote.getString("author_mail");
        String ownerPassword = resultNote.getString("author_password");
        Owner bOwner = new Owner(ownerId,ownerName,ownerMail,ownerPassword,idNote);

        PreparedStatement findUsersForNote = connection.prepareStatement("SELECT  users.id AS 'user_id',users.name AS 'user_name'" +
                ",users.mail AS 'user_mail',users.encrypted_password AS 'user_password',users_privileges.privilege AS 'user_privilige'" +
                "  FROM users LEFT OUTER JOIN users_privileges  ON users.id = users_privileges.users_id "+
                "WHERE users_privileges.notes_id = ?");
        findUsersForNote.setInt(1,idNote);
        ResultSet users = findUsersForNote.executeQuery();
        List<User> userList = new LinkedList<>();
        User bu;
        while (users.next()){
            int userId = users.getInt("user_id");
            String userName = users.getString("users_name");
            String userMail = users.getString("users_mail");
            String userPassword = users.getString("user_password");
            int userPrivileges= users.getInt("users_privileges");
            bu = new User(userId,userName,userMail,userPassword);
            bu.setRight(idNote,userPrivileges);
            userList.add(bu);
        }
        findUsersForNote.close();
        findNoteStatement.close();
        connection.close();
        Note foundNote = new Note(idNote,title,bOwner,text,userList,creationDate);
        return foundNote;
    }

    @Override
    public boolean updateNote(Note note) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE notes SET notes.id =?,notes.title =?," +
                "notes.text = ?, notes.creation_date =?, notes.author_id = ? WHERE notes.id = ?");
        preparedStatement.setInt(1,note.getId());
        preparedStatement.setString(2,note.getTitle());
        preparedStatement.setString(3,note.getText());
        preparedStatement.setDate(4,(Date) note.getDate());
        preparedStatement.setInt(5,note.getOwner().getId());
        preparedStatement.setInt(6,note.getId());
        preparedStatement.executeUpdate();
        connection.close();
        return true;
    }

    @Override
    public boolean saveOrUpdateNote(Note note) throws SQLException {
        updateNote(note);
        return true;
    }

    @Override
    public Collection<Note> findNotesByTitle(String title) throws SQLException {
        Collection<Note> notes = new LinkedList<>();
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT notes.id FROM notes WHERE notes.title = ?");
        preparedStatement.setString(1,title);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            notes.add(findNote(resultSet.getInt("id")));
        }
        return notes;
    }

    @Override
    public Collection<Note> findNoteTextFragment(String fragment) throws SQLException {
        connection = dataSource.getConnection();
        Collection<Note> notes = new LinkedList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT notes.id FROM notes WHERE notes.text LIKE ? ");
        StringBuilder fragmentBuilder = new StringBuilder();
        fragmentBuilder.append("%").append(fragment).append("%");
        preparedStatement.setString(1,fragmentBuilder.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            notes.add(findNote(resultSet.getInt("id")));
        }
        return notes;
    }
}
