package Service;

import Dao.UserDao;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDao dao=new UserDao();
    public List<User> show() throws SQLException {
        return dao.show();
    }

    public User find(int id) throws SQLException {
        return dao.find(id);
    }

    public void update(User user) throws SQLException {
        dao.update(user);
    }

    public void delete(int id) throws SQLException {
        dao.delete(id);
    }

    public void add(User user) throws SQLException {
        dao.add(user);
    }
}
