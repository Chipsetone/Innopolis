package com.semakin.jdbc.Repositories;

import com.semakin.jdbc.DTO.Group;
import com.semakin.jdbc.entitylogic.EntityRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Семакин Виктор
 */

public class GroupRepository extends EntityRepository<Group> {
    public GroupRepository() {
        super(Group.class);
    }

    @Override
    public void insert(Group entity) throws SQLException, IllegalAccessException {

    }

    @Override
    public Group selectById(long id) {
        return null;
    }

    @Override
    public List<Group> selectAll() {
        return null;
    }

    @Override
    public void update(Group entity) {

    }

    @Override
    public void deleteById(Group entity) {

    }
}
