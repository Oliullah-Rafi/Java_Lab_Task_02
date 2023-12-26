package app.repository;

import app.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> list() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> departmentQuery = session.createQuery("from User", User.class);
        return departmentQuery.getResultList();
    }

    public boolean create(User user) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return true;
    }
    public User get(Long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    public boolean update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return true;
    }

    public boolean delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
        return true;
    }
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Long> query = session.createQuery("select count(*) from User", Long.class);
        return query.getSingleResult().intValue();
    }
}
