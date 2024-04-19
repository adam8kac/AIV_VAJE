package si.um.feri.aiv.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.aiv.dao.OsebaDao;
import si.um.feri.aiv.dao.OsebaMemoryDao;
import si.um.feri.aiv.vao.Oseba;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("oseba")
@SessionScoped
public class OsebaJSFBean implements Serializable{

        private static final long serialVersionUID = 56493274189921966L;
        Logger log=Logger.getLogger(si.um.feri.aiv.jsf.OsebaJSFBean.class.toString());

//        private OsebaDao dao= OsebaMemoryDao.getInstance();
        @EJB
        private OsebaDao dao;
        private Oseba selectedPerson=new Oseba();

        private String selectedEmail;

        public List<Oseba> getAllPeople() throws Exception {
            return dao.getAllOseba();
        }

        public String savePerson() throws Exception {
            dao.saveOseba(selectedPerson);
            return "all";
        }

        public void deletePerson(Oseba o) throws Exception {
            dao.deleteOseba(o.getMail());
        }

        public void setSelectedEmail(String email) throws Exception {
            System.out.println(selectedEmail);
            selectedEmail =email;
            System.out.println(selectedEmail);
            selectedPerson =dao.findOseba(email);
            if(selectedPerson ==null) selectedPerson =new Oseba();
            System.out.println(selectedEmail);
        }

        public String getSelectedEmail() {
            return selectedEmail;
        }

        public Oseba getSelectedPerson() {
            return selectedPerson;
        }

        public void setSelectedPerson(Oseba selectedPerson) {
            this.selectedPerson = selectedPerson;
        }



}
