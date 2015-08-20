
package com.superdeal.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author udith dissanayake
 * @version 1.0 (20/8/2015)
 */
@Entity
@Table(name = "CUSTOMER_ORDERLINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrderline.findAll", query = "SELECT c FROM CustomerOrderline c"),
    @NamedQuery(name = "CustomerOrderline.findByLineNo", query = "SELECT c FROM CustomerOrderline c WHERE c.lineNo = :lineNo"),
    @NamedQuery(name = "CustomerOrderline.findByPartNo", query = "SELECT c FROM CustomerOrderline c WHERE c.partNo = :partNo"),
    @NamedQuery(name = "CustomerOrderline.findByAmount", query = "SELECT c FROM CustomerOrderline c WHERE c.amount = :amount")})
public class CustomerOrderline implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_NO")
    private Integer lineNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PART_NO")
    private String partNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private double amount;
    @JoinColumn(name = "ORDER_NO", referencedColumnName = "ORDER_NO")
    @ManyToOne(optional = false)
    private CustomerOrder orderNo;

    public CustomerOrderline() {
    }

    public CustomerOrderline(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public CustomerOrderline(Integer lineNo, String partNo, double amount) {
        this.lineNo = lineNo;
        this.partNo = partNo;
        this.amount = amount;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CustomerOrder getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(CustomerOrder orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineNo != null ? lineNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrderline)) {
            return false;
        }
        CustomerOrderline other = (CustomerOrderline) object;
        if ((this.lineNo == null && other.lineNo != null) || (this.lineNo != null && !this.lineNo.equals(other.lineNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+lineNo;
    }
    
}
