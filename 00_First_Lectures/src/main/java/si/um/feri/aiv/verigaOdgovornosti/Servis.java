package si.um.feri.aiv.verigaOdgovornosti;

import si.um.feri.aiv.observer.MseObserver;
import si.um.feri.aiv.vao.MSE;

public class Servis implements VerigaOdgovornosti{

    public Servis() {
    }

    private VerigaOdgovornosti veriga;

    public Servis(VerigaOdgovornosti veriga) {
        this.veriga = veriga;
    }

    @Override
    public void preveriDatum(MSE mse) {
        int datumVgradnje = mse.getVgradnja().getYear();

        if(datumVgradnje % 5 == 0){
            MseObserver observer = new MseObserver();
            observer.update(new MseObserver());
        }
        else {
            System.out.println("Še je čas");
        }
    }
}
