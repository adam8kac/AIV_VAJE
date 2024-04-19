package si.um.feri.aiv.vao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import si.um.feri.aiv.observer.MseObserver;
import si.um.feri.aiv.observer.Observable;
import si.um.feri.aiv.observer.Observer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Skupnost implements Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String naziv;
//    private MSE mse;
    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    private Oseba skrbnik;
    @Getter
    @Setter
//    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MSE> mseList;

//    @OneToMany(cascade = CascadeType.ALL)
//    public List<MSE> getMseList() {
//        return mseList;
//    }


    @Transient
    private List<Observer> observers = new ArrayList<>();


    public Skupnost(String naziv, List<MSE> mseList, Oseba skrbnik) {
        this.naziv = naziv;
        this.mseList = mseList;
        this.skrbnik = skrbnik;
        observers.add(new MseObserver());
    }

    public Skupnost(){
        observers.add(new MseObserver());
    }

    //    public MSE getMse() {
//        return mse;
//    }
//
//    public void setMse(MSE mse) {
//        this.mse = mse;
//    }


    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers){
            o.update(this);
        }
    }

    @Override
    public String toString() {
        return "Skupnost{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", skrbnik=" + skrbnik +
                ", mseList=" + mseList +
                ", observers=" + observers +
                '}';
    }
}
