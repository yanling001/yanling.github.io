package Dao;

import pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?&serverTimezone=UTC","root","123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> show() throws SQLException {
        String sql="select * from user";
        List<User> list=new ArrayList<>();
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            User user=new User();
            user.setName(resultSet.getString(3));
            user.setAge(resultSet.getInt(2));
            user.setId(resultSet.getInt(1));
            list.add(user);
        }
        return list;
    }

    public User find(int id) throws SQLException {
        String sql="select * from user where id=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        resultSet=preparedStatement.executeQuery();
        resultSet.next();
        User user=new User();
        user.setId(resultSet.getInt(1));
        user.setAge(resultSet.getInt(2));
        user.setName(resultSet.getString(3));
        return user;
    }

    public void update(User user) throws SQLException {
        String sql="update user set age=?,name=? where id=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,user.getAge());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setInt(3,user.getId());
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        String sql="delete from user where id=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    public void add(User user) throws SQLException {
        String sql="insert into user(age,name) values(?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,user.getAge());
        preparedStatement.setString(2,user.getName());
        preparedStatement.execute();
    }
}
