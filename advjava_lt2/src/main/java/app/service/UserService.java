package app.service;

import org.springframework.stereotype.Service;
import app.domain.User;
import app.repository.UserRepository;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserService {
    private Logger logger = Logger.getLogger(UserService.class.getName());

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean create(User user) throws SQLException {
        return userRepository.create(user);
    }

    public List<User> list() throws SQLException{
        return userRepository.list();
    }
    public User get(Long id) throws SQLException {
        return userRepository.get(id);
    }

    public boolean update(User user) throws SQLException {
        user.setName(user.getName().toUpperCase());
        return userRepository.update(user);
    }

    public boolean delete(Long id) throws SQLException {
        return userRepository.delete(get(id));
    }

    public int count() {
        return userRepository.count();
    }
}
