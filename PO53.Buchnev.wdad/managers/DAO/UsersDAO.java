package managers.DAO;

import data.modules.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UsersDAO {
    int insertUser(User user) throws SQLException;
    boolean deleteUser(User user) throws SQLException;
    User findUser(int id) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean saveOrUpdateUser(User user) throws SQLException;
    Collection<User> findUsersByName(Collection<String> names) throws SQLException;
    Collection<User> findUsersByMail(Collection<String> mails) throws SQLException;
}
