package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Oseba;

import java.util.List;
@Local
public interface MseDao {
    List<MSE> getAllMse();
    MSE findMse(int id);
    void saveMse(MSE m);
    void deleteMse(int id);
    List<MSE> findMultiple(List<Integer> list);
}
