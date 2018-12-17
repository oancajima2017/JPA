/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.entidad.Modalidad;

/**
 *
 * @author PeruServiFast
 */
@Stateless
public class ModalidadFacade extends AbstractFacade<Modalidad> {

    @PersistenceContext(unitName = "BarSysPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModalidadFacade() {
        super(Modalidad.class);
    }
    
}
