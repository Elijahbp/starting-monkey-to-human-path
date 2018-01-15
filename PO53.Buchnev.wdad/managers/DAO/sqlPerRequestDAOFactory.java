package managers.DAO;

public class sqlPerRequestDAOFactory extends DAOFactory {

    @Override
    public UsersDAO getUsersDAO() throws Exception {
        return new DataManagerUsersDAO();
    }

    @Override
    public NotesDAO getNotesDAO() throws Exception {
        return new DataManagerNotesDAO();
    }
}
