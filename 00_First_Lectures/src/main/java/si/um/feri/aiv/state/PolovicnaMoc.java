package si.um.feri.aiv.state;

import si.um.feri.aiv.vao.MSE;

public class PolovicnaMoc implements StanjeMse{

    @Override
    public double racunajMoc(MSE mse) {
        return (double) mse.getZmogljivost() / 2;
    }

}
