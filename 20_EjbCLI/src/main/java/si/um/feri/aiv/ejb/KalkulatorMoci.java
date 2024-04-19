package si.um.feri.aiv.ejb;

import si.um.feri.aiv.Sestevanje;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;


public class KalkulatorMoci {
    public static void main(String[] args) throws Exception {
        Properties props=new Properties();
        props.put("java.naming.factory.initial","org.jboss.naming.remote.client.InitialContextFactory");
        props.put("java.naming.provider.url","http-remoting://127.0.0.1:8080");
        props.put("jboss.naming.client.ejb.context","true");
        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
        InitialContext ctx=new InitialContext(props);

        Sestevanje s = (Sestevanje) ctx.lookup("FirstLectures/SestejMocBean!si.um.feri.aiv.Sestevanje");
//        s.sestej("dd");
        System.out.println(s.sestej("a"));

    }
}
