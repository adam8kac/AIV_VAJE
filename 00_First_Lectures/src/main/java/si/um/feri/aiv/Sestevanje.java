package si.um.feri.aiv;

import jakarta.ejb.Remote;
import si.um.feri.aiv.vao.Skupnost;

@Remote
public interface Sestevanje {

    double sestej(String skupnostNaziv) throws Exception;
//    double sestej(Skupnost skupnost);
}

