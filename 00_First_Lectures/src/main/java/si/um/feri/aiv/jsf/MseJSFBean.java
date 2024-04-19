package si.um.feri.aiv.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import si.um.feri.aiv.dao.MseDao;
import si.um.feri.aiv.dao.MseMemoryDao;
import si.um.feri.aiv.vao.Lokacija;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Oseba;
import si.um.feri.aiv.verigaOdgovornosti.Pregled;
import si.um.feri.aiv.verigaOdgovornosti.Servis;
import si.um.feri.aiv.verigaOdgovornosti.VerigaOdgovornosti;
import si.um.feri.aiv.verigaOdgovornosti.ZivljenskaDoba;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
@Named("mseBEAN")
public class MseJSFBean implements Serializable {

    private static final long serialVersionUID = -6224370815015560216L;
    Logger log = Logger.getLogger(MseJSFBean.class.toString());
//    private MseDao dao = MseMemoryDao.getInstance();
    @EJB
    private  MseDao dao;
    @Setter
    @Getter
    private MSE selectedMse = new MSE();
    @Getter
    private String selectedEmail;
    @Getter
    @Setter
    private int selectedID;


    public MseJSFBean(){}

    @PostConstruct
    public void init() {
        this.selectedMse = new MSE();
        this.selectedMse.setLokacija(new Lokacija());
        this.selectedMse.setOseba(new Oseba());
    }

    public List<MSE> getAllMSE() throws Exception{
        return dao.getAllMse();
    }

    public String saveMse() throws Exception {
        this.dao.saveMse(this.selectedMse);
        init();
        return "all";
    }

    public void deleteMse(MSE mse) throws Exception {
        this.dao.deleteMse(mse.getOseba().getId());
    }

    public void setSelectedID(int id) throws Exception {
        this.selectedID = id;
        this.selectedMse = this.dao.findMse(id);
        if (this.selectedMse == null) {
            init();
        }

    }

    public void preveri(MSE mse){
        VerigaOdgovornosti verige = new ZivljenskaDoba(new Servis(new Pregled()));
        verige.preveriDatum(mse);
    }

}
