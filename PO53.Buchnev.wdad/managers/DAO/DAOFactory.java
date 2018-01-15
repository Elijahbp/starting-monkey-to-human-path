package managers.DAO;

public abstract class DAOFactory {
    protected DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return new sqlPerRequestDAOFactory();
    }

    public abstract UsersDAO getUsersDAO() throws Exception;

    public abstract NotesDAO getNotesDAO() throws Exception;
}
