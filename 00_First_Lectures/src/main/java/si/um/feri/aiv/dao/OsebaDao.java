package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Oseba;

import java.util.List;
@Local
public interface OsebaDao {

    List<Oseba> getAllOseba();
    Oseba findOseba(String email);
    void saveOseba(Oseba o);
    void deleteOseba(String o);
}
