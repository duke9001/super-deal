package com.superdeal.entity;

import com.superdeal.entity.Customer;
import com.superdeal.entity.CustomerOrderline;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2015-08-20T23:57:07")
@StaticMetamodel(CustomerOrder.class)
public class CustomerOrder_ { 

    public static volatile SingularAttribute<CustomerOrder, Double> amount;
    public static volatile SingularAttribute<CustomerOrder, String> orderNo;
    public static volatile SingularAttribute<CustomerOrder, Customer> customerId;
    public static volatile ListAttribute<CustomerOrder, CustomerOrderline> customerOrderlineList;
    public static volatile SingularAttribute<CustomerOrder, String> comment;
    public static volatile SingularAttribute<CustomerOrder, Date> dueDate;

}