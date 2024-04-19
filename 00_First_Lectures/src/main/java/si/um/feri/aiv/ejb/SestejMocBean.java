//package si.um.feri.aiv.ejb;
//
//import jakarta.ejb.EJB;
//import jakarta.ejb.Stateless;
//import si.um.feri.aiv.Sestevanje;
//import si.um.feri.aiv.dao.SkupnostDao;
//import si.um.feri.aiv.dao.SkupnostMemoryDao;
//import si.um.feri.aiv.vao.MSE;
//import si.um.feri.aiv.vao.Skupnost;
//
//import java.util.List;
//
//@Stateless
//public class SestejMocBean implements Sestevanje {
//    @EJB
//    SkupnostDao skupnostDao;
//
//    @Override
//    public double sestej(String naziv) throws Exception {
////        System.out.println(naziv);
//
//        double sestevekMoci = 0;
//        Skupnost skupnost = skupnostDao.findSkupnost(naziv);
//        System.out.println(skupnostDao.findSkupnost(naziv));
//
//
////        System.out.println(skupnostDao.getAllSkupnost());
////        System.out.println("Skupnost: " + skupnost.getMseList());
//
//        if (skupnost != null && skupnost.getNaziv().equals(naziv)) {
//            List<MSE> mseList = skupnost.getMseList();
////            System.out.println(mseList.size());
//
//            if (mseList != null) {
//                for (MSE mse : mseList) {
//                    if (mse != null) {
////                        System.out.println(mse.getZmogljivost());
//                        sestevekMoci += mse.getZmogljivost();
//                    }
//                }
//            }
//        }
//        return sestevekMoci;
//    }
//
//
//}
