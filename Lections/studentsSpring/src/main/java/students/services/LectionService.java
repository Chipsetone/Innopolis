package students.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students.models.dao.LectionDAO;
import students.models.pojo.Lection;

import java.util.List;

@Service
public class LectionService {

    @Autowired
    private LectionDAO lectionDAO;

    public List<Lection> getAllLections(){
        return lectionDAO.getAllLections();
    }

    public int deleteLectioOnId(int id){

        return lectionDAO.deleteLection(id);
    }

    public int updateLectionOnId(Lection lection){

        return lectionDAO.updateLection(lection);
    }

    public int insertLection(Lection lection){

        return lectionDAO.insertLection(lection);
    }

    public List<Lection> getNearedLection() {
        return lectionDAO.getNearedLections();
    }
}
