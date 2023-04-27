package dao;

import dao.dao.IDAO;
import dao.dao.UserDAO;
import dao.model.User;

public class DaoMain {
    public static void main(String[] args) {
        User user1=new User();
        user1.setFirstName("pouet");
        user1.setLastName("pouet");
        user1.setLogin("pouet");
        user1.setPwd("pouet1");
        user1.setId(5);
        // User user2=new User();
        // user2.setFirstName("Jean-Bernard");
        // user2.setLastName("Buffalo");
        // user2.setLogin("bbuffalo");
        // user2.setPwd("pwd2");
        // User user3=new User();
        // user3.setFirstName("Jean-Yves");
        // user3.setLastName("Doeuf");
        // user3.setLogin("jdoeuf");
        // user3.setPwd("pwd3");

        IDAO<User> userDAO = UserDAO.getInstance();
        System.out.println(userDAO.readAll());
        // System.out.println(userDAO.read(4));
        
        // userDAO.delete(2);
        // userDAO.update(user1);
        // userDAO.create(user1);
        // userDAO.create(user2);
        // userDAO.create(user3);
    }
}
