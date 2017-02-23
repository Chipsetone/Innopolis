package com.semakin.labs.lab2.XmlListEntities;

import com.semakin.labs.lab2.entities.Superuser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Семакин Виктор
 */
@XmlRootElement(name = "superusers")
public class SuperuserList implements IListEntities<Superuser> {
    private List<Superuser> superusers;

    public List<Superuser> getList() {
        return superusers;
    }

    @XmlElement(name = "superuser")
    public void setList(List<Superuser> superusers) {
        this.superusers = superusers;
    }
}
