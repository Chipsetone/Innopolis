package com.semakin.jdbc.Repositories;

import com.semakin.jdbc.DTO.Journal;
import com.semakin.jdbc.entitylogic.GodEntityRepository;

/**
 * @author Семакин Виктор
 */
public class JournalRepository extends GodEntityRepository<Journal> {
    public JournalRepository() {
        super(Journal.class);
    }
}
