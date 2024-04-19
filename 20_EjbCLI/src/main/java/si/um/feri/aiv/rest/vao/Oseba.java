package si.um.feri.aiv.rest.vao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Oseba {

    private int id;

    private String ime;
    private String priimek;
    private String mail;

    public Oseba(String ime, String priimek, String mail) {
        this.ime = ime;
        this.priimek = priimek;
        this.mail = mail;
    }

    public Oseba(){
        this ("","","");
    }
}
