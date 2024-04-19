package si.um.feri.aiv.rest.vao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
public class MSE implements Serializable {

    @Serial
    private static final long serialVersionUID = -9181227337031536574L;

    private Lokacija lokacija;

    private long zmogljivost;


    private Oseba oseba;


    private Date vgradnja;

    private Date zadnjiPregled;

    private Date zadnjiServis;



    public MSE(Lokacija lokacija, long zmogljivost, Oseba oseba) {
        this.lokacija = lokacija;
        this.zmogljivost = zmogljivost;
        this.oseba = oseba;
    }

    public MSE(){
    }

}
