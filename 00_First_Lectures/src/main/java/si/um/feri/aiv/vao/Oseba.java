package si.um.feri.aiv.vao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class Oseba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String ime;
    private String priimek;
    private String mail;

    @Transient
    private LocalDateTime timestamp=LocalDateTime.now();

    public Oseba(String ime, String priimek, String mail) {
        this.ime = ime;
        this.priimek = priimek;
        this.mail = mail;
    }

    public Oseba(){
        this ("","","");
    }
}
