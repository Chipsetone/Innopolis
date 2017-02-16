package com.semakin.jdbc.entitylogic;

/**
 * @author Семакин Виктор
 */

public abstract class Entity {
    /**
     * id для всех объектов БД
     */
    protected long id;

    /**
     * Конструктор по умолчанию. Должен присутствовать во всех наследниках для рефлексии
     */
    public Entity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
