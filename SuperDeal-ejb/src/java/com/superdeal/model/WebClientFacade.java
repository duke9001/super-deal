
package com.superdeal.model;

import com.superdeal.entity.WebClient;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author udith dissanayake
 * @version 1.0 (20/8/2015)
 */
@Stateless
public class WebClientFacade extends AbstractFacade<WebClient> {
    @PersistenceContext(unitName = "SuperDeal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WebClientFacade() {
        super(WebClient.class);
    }
    
}
