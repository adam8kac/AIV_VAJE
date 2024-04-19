package si.um.feri.aiv.rest.vao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Lokacija {

    private double x;
    private double y;

    public Lokacija(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Lokacija(){}
}
