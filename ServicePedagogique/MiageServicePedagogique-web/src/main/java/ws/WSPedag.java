/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import ServicesP.ServicePedagLocal;
import Util.HashMapWrapper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Quentin
 */
@WebService(serviceName = "WSPedag")
@Stateless()
public class WSPedag {

    @EJB(beanName = "ServicePedag") 
    private ServicePedagLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "createConvention")
    @Oneway
    public void createConvention(@WebParam(name = "idConvention") Long idConvention, @WebParam(name = "dateDebut") String dateDebut, @WebParam(name = "dateFin") String dateFin, @WebParam(name = "statut") String statut, @WebParam(name = "resume") String resume, @WebParam(name = "intule") String intule, @WebParam(name = "niveau") String niveau, @WebParam(name = "profref") String profref) {
        ejbRef.createConvention(idConvention, dateDebut, dateFin, statut, resume, intule, niveau, profref);
    }

    @WebMethod(operationName = "setProfRef")
    @Oneway
    public void setProfRef(@WebParam(name = "idConv") Long idConv, @WebParam(name = "prof") String prof) {
        ejbRef.setProfRef(idConv, prof);
    }

    @WebMethod(operationName = "annulerConvention")
    @Oneway
    public void annulerConvention(@WebParam(name = "idConv") Long idConv) {
        ejbRef.annulerConvention(idConv);
    }

    @WebMethod(operationName = "validerConvention")
    @Oneway
    public void validerConvention(@WebParam(name = "idConv") Long idConv) {
        ejbRef.validerConvention(idConv);
    }

    @WebMethod(operationName = "getConventions")
    public List<String> getConventions() {
        return ejbRef.getConventions();
    }

    @WebMethod(operationName = "getConvention")
    public HashMapWrapper getConvention(@WebParam(name = "id") Long id) {
        return ejbRef.getConvention(id);
    }
    
}
