
package com.superdeal.model;

import com.superdeal.entity.CustomerOrderline;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author udith dissanayake
 * @version 1.0 (20/8/2015)
 */
@Stateless
public class CustomerOrderlineFacade extends AbstractFacade<CustomerOrderline> {
    @PersistenceContext(unitName = "SuperDeal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderlineFacade() {
        super(CustomerOrderline.class);
    }
    
}
