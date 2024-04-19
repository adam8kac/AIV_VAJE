package si.um.feri.aiv.dao;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Skupnost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class SkupnostMemoryDao implements SkupnostDao{
    Logger log=Logger.getLogger(OsebaMemoryDao.class.toString());

    private List<Skupnost> skupnosti= Collections.synchronizedList(new ArrayList<Skupnost>());

    @PersistenceContext
    EntityManager em;

//    private static SkupnostMemoryDao instance=null;
//    public static synchronized SkupnostMemoryDao getInstance() {
//        if (instance==null) instance= new SkupnostMemoryDao();
//        return instance;
//    }
//
//    private SkupnostMemoryDao(){
//
//    };

    public SkupnostMemoryDao(){}

    @Override
    public List<Skupnost> getAllSkupnost() {
        log.info("DAO: get all");
//        return skupnosti;
        return em.createQuery("select s from Skupnost s").getResultList();
    }


    @Override
    public Skupnost findSkupnost(int id)  {
        log.info("DAO: finding " + id);
//        for (Skupnost s : skupnosti)
//            if (s.getNaziv().equals(naziv))
//                return s;
//        return null;
        return em.find(Skupnost.class, id);
    }

    @Override
    public void saveSkupnost(Skupnost s)  {
        log.info("DAO: saving " + s);
//        if(findSkupnost(s.getNaziv())!=null) {
//            log.info("DAO: editing " + s);
//            deleteSkupnost(s.getNaziv());
//        }
//        skupnosti.add(s);
//        em.persist(s.getMseList().iterator().next().getId());
//        em.merge(s.getMseList());
        em.merge(s);
    }

    @Override
    public void deleteSkupnost(int id) {
        log.info("DAO: deleting " + id);
//        for (Iterator<Skupnost> i = skupnosti.iterator(); i.hasNext();) {
//            if (i.next().getNaziv().equals(naziv))
//                i.remove();
//        }
//        em.remove(em.find(MSE.class, id));
        em.remove(em.find(Skupnost.class, id));
    }

    @Override
    public double vrniMocMse(Skupnost skupnost) {
        List<MSE>  mse = skupnost.getMseList();
        double moc = 0;
        for (MSE m : mse){
            moc += m.getStanje().racunajMoc(m);
        }
        return moc;
    }
}
