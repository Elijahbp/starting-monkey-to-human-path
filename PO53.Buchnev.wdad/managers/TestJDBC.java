package managers;

import data.modules.Note;
import data.modules.Owner;
import data.storage.DataSourceFactory;

import javax.sql.DataSource;
import java.util.List;

public class TestJDBC {

    public static void main(String[] args) throws Exception {
        DataSource dataSource = DataSourceFactory.createDataSource();
        JDBCDataManager jdbcDataManager = new JDBCDataManager(dataSource);

        Owner owner = new Owner("Ilya","ezatarri-8558@yopmail.com");
        List<Note> noteList = jdbcDataManager.getNotes(owner);
        for (Note note:noteList) {
            System.out.println(note.toString());
        }
    }
}
