package managers.DAO;

import data.modules.Note;

import java.sql.SQLException;
import java.util.Collection;

public interface NotesDAO {
    boolean insertNote(Note note) throws SQLException;
    boolean deleteNote(Note note) throws SQLException;
    Note findNote(int id) throws SQLException;
    boolean updateNote(Note note) throws SQLException;
    boolean saveOrUpdateNote(Note note) throws SQLException;
    Collection<Note> findNotesByTitle(String title) throws SQLException;
    Collection<Note> findNoteTextFragment(String fragment) throws SQLException;
}
