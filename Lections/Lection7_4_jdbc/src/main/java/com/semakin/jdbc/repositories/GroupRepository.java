package com.semakin.jdbc.repositories;

import com.semakin.jdbc.DTO.Group;
import com.semakin.jdbc.entitylogic.GodEntityRepository;

/**
 * @author Семакин Виктор
 */

public class GroupRepository extends GodEntityRepository<Group> {
    public GroupRepository() {
        super(Group.class);
    }
}
