package si.um.feri.aiv.vao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import si.um.feri.aiv.state.Izklopljena;
import si.um.feri.aiv.state.PolnaMoc;
import si.um.feri.aiv.state.PolovicnaMoc;
import si.um.feri.aiv.state.StanjeMse;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class MSE implements Serializable {

    @Serial
    private static final long serialVersionUID = -9181227337031536574L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Lokacija lokacija;

    private long zmogljivost;

    @OneToOne(cascade = CascadeType.ALL)
    private Oseba oseba;

    @Transient
    private StanjeMse stanje;

    private Date vgradnja;

    private Date zadnjiPregled;

    private Date zadnjiServis;



    public MSE(Lokacija lokacija, long zmogljivost, Oseba oseba, Date vgradnja, Date zadnjiPregled, Date zadnjiServis) {
        this.lokacija = lokacija;
        this.zmogljivost = zmogljivost;
        this.oseba = oseba;
        this.stanje = new PolovicnaMoc();
        this.vgradnja = vgradnja;
        this.zadnjiPregled = zadnjiPregled;
        this.zadnjiServis = zadnjiServis;
    }

    public MSE(){
        this.stanje = new PolovicnaMoc();
    }

    @Override
    public String toString() {
        return "MSE{" +
                "id=" + id +
                ", lokacija=" + lokacija +
                ", zmogljivost=" + zmogljivost +
                ", oseba=" + oseba +
                ", stanje=" + stanje +
                ", vgradnja=" + vgradnja +
                ", zadnjiPregled=" + zadnjiPregled +
                ", zadnjiServis=" + zadnjiServis +
                '}';
    }
}
