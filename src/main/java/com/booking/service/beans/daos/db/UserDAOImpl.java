package com.booking.service.beans.daos.db;

import com.booking.service.beans.daos.AbstractDAO;
import com.booking.service.beans.daos.UserDAO;
import com.booking.service.beans.models.User;
import com.booking.service.beans.models.UserRole;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 20/2/16
 * Time: 4:35 PM
 */
@Repository(value = "userDAO")
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public User create(User user) {
        UserDAO.validateUser(user);
        addDefaultRolesToUser(user);
        User byEmail = getByEmail(user.getEmail());
        if (Objects.nonNull(byEmail)) {
            throw new IllegalStateException(
                    String.format("Unable to store user: [%s]. User with email: [%s] is already created.", user,
                            user.getEmail()));
        } else {
            Long userId = (Long) getCurrentSession().save(user);
            return user.withId(userId);
        }
    }

    @Override
    public void delete(User user) {
        UserDAO.validateUser(user);
        getCurrentSession().delete(user);
    }

    @Override
    public User get(long id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return ((User) createBlankCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllByName(String name) {
        return createBlankCriteria(User.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("name", name)).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return ((List<User>) createBlankCriteria(User.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list());
    }

    private void addDefaultRolesToUser(User user) {
        if (!user.getRoles().contains(UserRole.REGISTERED_USER.name())) {
            user.addRole(UserRole.REGISTERED_USER.name());
        }
    }
}
