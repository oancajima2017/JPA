/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.entidad.Personal;

/**
 *
 * @author PeruServiFast
 */
@Stateless
public class PersonalFacade extends AbstractFacade<Personal> {

    @PersistenceContext(unitName = "BarSysPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalFacade() {
        super(Personal.class);
    }

    public Personal validarUsuario(Personal usu) {
        Personal user = null;
        String jpql = "SELECT u FROM Personal u WHERE u.userPer = :usuusu and u.passPer = :passusu";
        Query p = em.createQuery(jpql);
        p.setParameter("usuusu", usu.getUserPer());
        p.setParameter("passusu", usu.getPassPer());
        List<Personal> lstUsuario = p.getResultList();
        if (lstUsuario.size() > 0) {
            user = lstUsuario.get(0);
        }
        return user;
    }

}
