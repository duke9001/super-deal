package com.superdeal.entity;

import com.superdeal.entity.CustomerOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2015-08-20T23:57:07")
@StaticMetamodel(CustomerOrderline.class)
public class CustomerOrderline_ { 

    public static volatile SingularAttribute<CustomerOrderline, Double> amount;
    public static volatile SingularAttribute<CustomerOrderline, CustomerOrder> orderNo;
    public static volatile SingularAttribute<CustomerOrderline, String> partNo;
    public static volatile SingularAttribute<CustomerOrderline, Integer> lineNo;

}