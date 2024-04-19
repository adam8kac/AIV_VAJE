package si.um.feri.aiv.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.aiv.dao.MseDao;
import si.um.feri.aiv.dao.SkupnostDao;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Skupnost;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("skupnost")
public class SkupnostController {

    private static Logger logger = Logger.getLogger("MseController");

    @EJB
    SkupnostDao dao;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public List<Skupnost> allMse() throws Exception {
        List<Skupnost> skupnostList = new ArrayList<>();

        for(Skupnost m : dao.getAllSkupnost()){
            skupnostList.add(m);
        }
        return skupnostList;
    }

    @POST()
    @Path("/")
    public void dodajMse(Skupnost skupnost) throws Exception {
        dao.saveSkupnost(skupnost);
    }
}
