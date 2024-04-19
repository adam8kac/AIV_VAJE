package si.um.feri.aiv.dao;

import jakarta.ejb.Stateless;
import org.w3c.dom.ls.LSOutput;
import si.um.feri.aiv.vao.Oseba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class OsebaMemoryDao implements OsebaDao{

    Logger log = Logger.getLogger(OsebaMemoryDao.class.toString());

    private List<Oseba> osebe = Collections.synchronizedList(new ArrayList<Oseba>());

//    private static OsebaMemoryDao instance=null;
//    public static synchronized OsebaMemoryDao getInstance() {
//        if (instance==null) instance=new OsebaMemoryDao();
//        return instance;
//    }
//
//    private OsebaMemoryDao(){
//
//    };

    public OsebaMemoryDao(){}

    @Override
    public List<Oseba> getAllOseba(){
        log.info("DAO: Get all oseba:");
        return osebe;
    }

    @Override
    public Oseba findOseba(String mail){
        log.info("DAO finfing email: " + mail);
        for(Oseba o : osebe){
            if(o.getMail().equals(mail)){
                return o;
            }
        }
        return null;
    }



    @Override
    public void saveOseba(Oseba o)  {
        log.info("DAO: saving "+o);
        if(findOseba(o.getMail())!=null) {
            log.info("DAO: editing "+o);
            deleteOseba(o.getMail());
        }
        osebe.add(o);
    }

    @Override
    public void deleteOseba(String mail){
        log.info("DAO: deleting "+ mail);
        for (Iterator<Oseba> i = osebe.iterator(); i.hasNext();) {
            if (i.next().getMail().equals(mail))
                i.remove();
        }
    }
}
