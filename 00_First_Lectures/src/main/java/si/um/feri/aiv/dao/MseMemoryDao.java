package si.um.feri.aiv.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Oseba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class MseMemoryDao implements MseDao{

    Logger log = Logger.getLogger(OsebaMemoryDao.class.toString());

    private List<MSE> mseList = Collections.synchronizedList(new ArrayList<MSE>());
    @PersistenceContext
    EntityManager em;

//    private static MseMemoryDao instance=null;
//    public static synchronized MseMemoryDao getInstance() {
//        if (instance==null) instance=new MseMemoryDao();
//        return instance;
//    }
//
//    private MseMemoryDao(){
//
//    };

    public MseMemoryDao(){}
    @Override
    public List<MSE> getAllMse(){
        log.info("DAO: Get all oseba:");
//        return mseList;
        return em.createQuery("select m from MSE m").getResultList();
    }

    @Override
    public MSE findMse(int id){
        log.info("DAO finfing email: " + id);
//        for(MSE m : mseList){
//            if(m.getOseba().getMail().equals(osebaMail)){
//                return m;
//            }
//        }
//        return null;
        return em.find(MSE.class, id);
    }

    @Override
    public void saveMse(MSE m){
        log.info("DAO saving oseba: " + m);
//        if(findMse(m.getOseba().getMail()) != null){
//            log.info("DAO editing: " + m);
//            deleteMse(m.getOseba().getMail());
//        }
            em.merge(m);
//        mseList.add(m);
        return;
    }

    @Override
    public void deleteMse(int id){
        log.info("DAO: deleting "+ id);
//        for (Iterator<MSE> i = mseList.iterator(); i.hasNext();) {
//            if (i.next().getOseba().getMail().equals(osebaMail))
//                i.remove();
//        }
        MSE mse = em.find(MSE.class, id);
        if(mse != null)
            em.remove(mse);
    }

    @Override
    public List<MSE> findMultiple(List<Integer> list) {
        List<MSE> noviMseList = new ArrayList<>();
        for(Integer i : list){
            noviMseList.add(this.findMse(i));
        }
        return noviMseList;
    }
}
