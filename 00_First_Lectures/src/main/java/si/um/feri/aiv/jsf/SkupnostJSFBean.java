package si.um.feri.aiv.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import si.um.feri.aiv.dao.MseDao;
import si.um.feri.aiv.dao.SkupnostDao;
import si.um.feri.aiv.dao.SkupnostMemoryDao;
import si.um.feri.aiv.observer.Email;
import si.um.feri.aiv.observer.MseObserver;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Oseba;
import si.um.feri.aiv.vao.Skupnost;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("skupnost")
@SessionScoped
public class SkupnostJSFBean implements Serializable {

    private static long serialVersionUID = 1090572189723549287L;
    Logger log = Logger.getLogger(SkupnostJSFBean.class.toString());
//    private SkupnostDao dao = SkupnostMemoryDao.getInstance();

    @EJB
    private SkupnostDao dao;
    @EJB
    private MseDao mseDao;

    @Setter
    @Getter
    private Skupnost selectedSkupnost;
    @Getter
    private String selectedNaziv;
    @Getter
    private int selectedID;
    private List<MSE> selectedMSE = new ArrayList();
    @Getter
    @Setter
    private List<Integer> selectedMSEsID;

    @Inject
    private SkupnostJSFBean skupnostBean;

    @PostConstruct
    public void init() {
        this.selectedSkupnost = new Skupnost();
        this.selectedSkupnost.setSkrbnik(new Oseba());
        this.selectedMSEsID = new ArrayList<>();
    }

    public List<Skupnost> getAllSkupnost() throws Exception{
        return dao.getAllSkupnost();
    }




    public String saveSkupnost() throws Exception{

//        selectedSkupnost.setMseList(selectedMSE);
        selectedSkupnost.setMseList(mseDao.findMultiple(selectedMSEsID));
        dao.saveSkupnost(selectedSkupnost);
        init();

//        for(MSE mse : selectedSkupnost.getMseList()){
//            Skupnost currentSkupnost = skupnostBean.getSelectedSkupnost();
//            currentSkupnost.addObserver(new MseObserver());
//
//            selectedSkupnost.notifyObservers();
//        }

        System.out.println("Skupnost: " + selectedSkupnost + " mseList: " + selectedMSE);
//        selectedSkupnost.notifyObservers();

        return "editSkupnost";
    }

    public void deleteSkupnost(Skupnost s) throws Exception{
        dao.deleteSkupnost(s.getId());
    }

    public void setSelectedID(int id) throws Exception {
        this.selectedID = id;
        this.selectedSkupnost = this.dao.findSkupnost(id);

        if(this.selectedSkupnost == null){
            init();
        }
    }

    public double djMiMoc(){
        Skupnost skup = getSelectedSkupnost();
        return dao.vrniMocMse(skup);
    }

    public List<MSE> getSelectedMSEs() {
        return this.selectedMSE;
    }

    public void setSelectedMSEs(List<MSE> selectedMSEs) {
        this.selectedMSE = selectedMSEs;
    }

}
