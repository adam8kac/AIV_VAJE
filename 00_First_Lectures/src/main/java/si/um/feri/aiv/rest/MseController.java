package si.um.feri.aiv.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.aiv.dao.MseDao;
import si.um.feri.aiv.vao.MSE;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("mse")
public class MseController {

    private static Logger logger = Logger.getLogger("MseController");

    @EJB
    MseDao dao;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public List<MSE> allMse(){
        List<MSE> mseList = new ArrayList<>();

        for(MSE m : dao.getAllMse()){
            mseList.add(m);
        }
        return mseList;
    }

    @POST()
    @Path("/")
    public void dodajMse(MSE mse){
        dao.saveMse(mse);
    }



}
