package com.booking.service.beans.daos.db;

import com.booking.service.beans.daos.AbstractDAO;
import com.booking.service.beans.daos.RoleDAO;
import com.booking.service.beans.models.Role;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */
@Repository(value = "roleDAO")

public class RoleDAOImpl extends AbstractDAO implements RoleDAO {
    @Override
    public Role create(Role role) {
        Role byName = getByName(role.getName());
        if (Objects.nonNull(byName)) {
            throw new IllegalStateException(
                    String.format("Unable to store role: [%s]. Role with name: [%s] is already created.", role,
                            role.getName()));
        } else {
            Long roleId = (Long) getCurrentSession().save(role);
            role.setId(roleId);
            return role;
        }
    }

    @Override
    public void delete(Role role) {
        getCurrentSession().delete(role);
    }

    @Override
    public Role get(long id) {
        return getCurrentSession().get(Role.class, id);
    }

    @Override
    public Role getByName(String name) {
        return (Role) createBlankCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        return ((List<Role>)createBlankCriteria(Role.class).list());
    }
}
