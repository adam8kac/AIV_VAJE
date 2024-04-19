package si.um.feri.aiv.state;

import si.um.feri.aiv.vao.MSE;

public class PolnaMoc implements StanjeMse{

    @Override
    public double racunajMoc(MSE mse) {
        return mse.getZmogljivost();
    }
}
