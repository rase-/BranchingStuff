/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author tonykovanen
 */

@Component
public class FileUserDao implements UserDao {

    File file;

    public FileUserDao(String file) {
        this.file = new File(file);
    }

    @Override
    public List<User> listAll() {
        ArrayList<User> usernames = new ArrayList<User>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String username = scanner.next();
                String password = scanner.next();
                usernames.add(new User(username, password));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usernames;
    }

    @Override
    public User findByName(String name) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String username = scanner.next();
                String password = scanner.next();
                if (username.equals(name)) {
                    return new User(username, password);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.append(user.getUsername()  + " " + user.getPassword() + "\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
