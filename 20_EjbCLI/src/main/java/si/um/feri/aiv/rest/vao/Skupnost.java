package si.um.feri.aiv.rest.vao;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Skupnost{

    private String naziv;
    private Oseba skrbnik;
    private List<MSE> mseList;

    public Skupnost(String naziv, List<MSE> mseList, Oseba skrbnik) {
        this.naziv = naziv;
        this.mseList = mseList;
        this.skrbnik = skrbnik;
    }

    public Skupnost(){

    }

}
