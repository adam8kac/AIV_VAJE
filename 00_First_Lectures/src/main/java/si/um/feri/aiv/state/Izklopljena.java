package si.um.feri.aiv.state;

import si.um.feri.aiv.vao.MSE;

public class Izklopljena implements StanjeMse{

    @Override
    public double racunajMoc(MSE mse) {
        return 0;
    }

}
