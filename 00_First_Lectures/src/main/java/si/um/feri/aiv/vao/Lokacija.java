package si.um.feri.aiv.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Lokacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double x;
    private double y;

    public Lokacija(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Lokacija(){}
}
