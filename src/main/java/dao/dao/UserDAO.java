package dao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.model.User;

public class UserDAO implements IDAO<User>{
    
    private final static UserDAO INSTANCE = new UserDAO();
    
    private final static String INSERT_USER = "INSERT INTO USER (login, pwd, firstname, lastname) VALUES (?, ?, ?, ?)";
    
    private final static String REMOVE_USER = "DELETE FROM USER WHERE id=?";
    
    private final static String UPDATE_USER = "UPDATE USER SET login=?, pwd=?, firstname=?, lastname=? WHERE id=?";
    
    private final static String FIND_USER = "SELECT lastname, firstname, pwd, login FROM USER WHERE id=?";

    private final static String FIND_ALL_USER = "SELECT lastname, firstname, pwd, id login FROM USER";
    
    private UserDAO() {}
    
    public static UserDAO getInstance() {
        return INSTANCE;
    }
    
    public List<User> readAll() {
        PreparedStatement pst = null;
        List<User> result = new ArrayList<User>();

        try {
            pst = JdbcSingleton.getInstance().getConnection().prepareStatement(FIND_ALL_USER);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(4);
                String pwd = rs.getString("pwd");
                String login = rs.getString("login");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                

                User user = new User();
                user.setLogin(login);
                user.setPwd(pwd);
                user.setLastName(lastname);
                user.setFirstName(firstname);
                user.setId(id);

                result.add(user);
            }
            if (rs!=null) {
                rs.close();
            }
            if (pst!=null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public User read(Integer id) {
        PreparedStatement pst = null;
        User result = new User();

        try {
            pst = JdbcSingleton.getInstance().getConnection().prepareStatement(FIND_USER);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String pwd = rs.getString("pwd");
                String login = rs.getString("login");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                
                result.setLogin(login);
                result.setPwd(pwd);
                result.setLastName(lastname);
                result.setFirstName(firstname);
                result.setId(id);
            }
            if (rs!=null) {
                rs.close();
            }
            if (pst!=null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void update (User user) {
        PreparedStatement pst = null;

        try {
            pst = JdbcSingleton.getInstance().getConnection().prepareStatement(UPDATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPwd());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setInt(5, user.getId());
            pst.execute();            
            ResultSet rs = pst.getGeneratedKeys();
            
            if (rs!=null) {
                rs.close();
            }
            if (pst!=null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        PreparedStatement pst = null;

        try {
            pst = JdbcSingleton.getInstance().getConnection().prepareStatement(REMOVE_USER, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setInt(1, id);

            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void create(User user) {
        PreparedStatement pst = null;
        
        try {
            pst = JdbcSingleton.getInstance().getConnection().prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPwd());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.execute();            
            ResultSet rs = pst.getGeneratedKeys();
            
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            if (rs!=null) {
                rs.close();
            }
            if (pst!=null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}