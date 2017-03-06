package students.models.dao;

import org.junit.jupiter.api.Test;
import students.models.pojo.Lection;
import students.services.LectionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectionDAOTest {
    @Test
    void getNearedLections() {
        List<Lection> lections = new LectionDAO().getNearedLections();

        fail("есть в логе исключения?");
    }
}