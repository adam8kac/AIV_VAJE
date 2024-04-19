package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Skupnost;

import java.util.List;
@Local
public interface SkupnostDao {

        List<Skupnost> getAllSkupnost() throws Exception;
        Skupnost findSkupnost(int id) throws Exception;
        void saveSkupnost(Skupnost s) throws Exception;
        void deleteSkupnost(int id) throws Exception;

        double vrniMocMse(Skupnost skupnost);


}
