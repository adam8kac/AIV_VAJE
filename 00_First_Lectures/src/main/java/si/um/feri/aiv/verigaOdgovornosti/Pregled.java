package si.um.feri.aiv.verigaOdgovornosti;

import si.um.feri.aiv.observer.MseObserver;
import si.um.feri.aiv.vao.MSE;

public class Pregled implements VerigaOdgovornosti{

    public Pregled() {
    }

    private VerigaOdgovornosti veriga;

    public Pregled(VerigaOdgovornosti veriga) {
        this.veriga = veriga;
    }

    @Override
    public void preveriDatum(MSE mse) {
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
