package com.semakin.jdbc.repositories;

import com.semakin.jdbc.DTO.Lection;
import com.semakin.jdbc.entitylogic.GodEntityRepository;

/**
 * @author Семакин Виктор
 */
public class LectionRepository extends GodEntityRepository<Lection> {
    public LectionRepository() {
        super(Lection.class);
    }
}
