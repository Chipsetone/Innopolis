package com.semakin.labs.lab2.XmlListEntities;

import com.semakin.labs.lab2.entitiessimple.Superuser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Семакин Виктор
 */
@XmlRootElement(name = "superusers")
public class SuperuserList  {
    private List<Superuser> superusers;

    public List<Superuser> getSuperusers() {
        return superusers;
    }

    @XmlElement(name = "superuser")
    public void setSuperusers(List<Superuser> superusers) {
        this.superusers = superusers;
    }
}
