package si.um.feri.aiv.verigaOdgovornosti;

import jakarta.ejb.EJB;
import si.um.feri.aiv.dao.MseDao;
import si.um.feri.aiv.jsf.MseJSFBean;
import si.um.feri.aiv.observer.MseObserver;
import si.um.feri.aiv.vao.MSE;

import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class ZivljenskaDoba implements VerigaOdgovornosti{


    public ZivljenskaDoba() {
    }
    private VerigaOdgovornosti veriga;

    public ZivljenskaDoba(VerigaOdgovornosti veriga) {
        this.veriga = veriga;
    }


    public void preveriDatum(MSE mse){
        int datumVgradnje = mse.getVgradnja().getYear();
        int datumZivljenskeDobe = datumVgradnje - (mse.getVgradnja().getYear() - 20);


        if(datumZivljenskeDobe  <= 0){
            MseObserver observer = new MseObserver();
            observer.update(new MseObserver());
        }
        else {
            System.out.println("Življenska doba je: " + datumZivljenskeDobe);
        }

    }

}
