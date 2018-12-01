/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import service.ServiceBDSLocal;

/**
 *
 * @author Quentin
 */
@WebService(serviceName = "WSOpenData")
@Stateless()
public class WSOpenData {

    @EJB
    private ServiceBDSLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerEtudiant")
    @Oneway
    public void creerEtudiant(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "password") String password, @WebParam(name = "num") int num, @WebParam(name = "idForm") Long idForm) {
        ejbRef.creerEtudiant(nom, prenom, password, num, idForm);
    }

    @WebMethod(operationName = "creerEntreprise")
    @Oneway
    public void creerEntreprise(@WebParam(name = "nom") String nom, @WebParam(name = "siren") int siren) {
        ejbRef.creerEntreprise(nom, siren);
    }

    @WebMethod(operationName = "creerFormation")
    @Oneway
    public void creerFormation(@WebParam(name = "intitule") String intitule, @WebParam(name = "niv") String niv, @WebParam(name = "dep") String dep, @WebParam(name = "code") String code) {
        ejbRef.creerFormation(intitule, niv, dep, code);
    }

    @WebMethod(operationName = "creerConvention")
    @Oneway
    public void creerConvention(@WebParam(name = "annee") int annee, @WebParam(name = "datedeb") Date datedeb, @WebParam(name = "datefin") Date datefin, @WebParam(name = "gratification") int gratification, @WebParam(name = "resume") String resume, @WebParam(name = "dureeEssai") int dureeEssai, @WebParam(name = "contrat") int contrat, @WebParam(name = "nomE") String nomE, @WebParam(name = "sirenE") int sirenE, @WebParam(name = "idEtu") Long idEtu) {
        ejbRef.creerConvention(annee, datedeb, datefin, gratification, resume, dureeEssai, contrat, nomE, sirenE, idEtu);
    }

    @WebMethod(operationName = "modifierConvention")
    @Oneway
    public void modifierConvention(@WebParam(name = "id") Long id, @WebParam(name = "prof") String prof) {
        ejbRef.modifierConvention(id, prof);
    }

    @WebMethod(operationName = "setStatutJuridique")
    @Oneway
    public void setStatutJuridique(@WebParam(name = "id") Long id, @WebParam(name = "value") String value) {
        ejbRef.setStatutJuridique(id, value);
    }

    @WebMethod(operationName = "setStatutAdministratif")
    @Oneway
    public void setStatutAdministratif(@WebParam(name = "id") Long id, @WebParam(name = "value") String value) {
        ejbRef.setStatutAdministratif(id, value);
    }

    @WebMethod(operationName = "setStatutPedagogique")
    @Oneway
    public void setStatutPedagogique(@WebParam(name = "id") Long id, @WebParam(name = "value") String value) {
        ejbRef.setStatutPedagogique(id, value);
    }

    @WebMethod(operationName = "getEtudiant")
    public Long getEtudiant(@WebParam(name = "pseudo") String pseudo, @WebParam(name = "pass") String pass) {
        return ejbRef.getEtudiant(pseudo, pass);
    }

    @WebMethod(operationName = "getConventions")
    public List<String> getConventions(@WebParam(name = "idEtu") Long idEtu) {
        return ejbRef.getConventions(idEtu);
    }

    @WebMethod(operationName = "getConvention")
    public Map<String, String> getConvention(@WebParam(name = "idConv") Long idConv) {
        return ejbRef.getConvention(idConv);
    }
    
}