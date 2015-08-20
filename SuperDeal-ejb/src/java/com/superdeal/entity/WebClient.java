
package com.superdeal.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "WEB_CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WebClient.findAll", query = "SELECT w FROM WebClient w"),
    @NamedQuery(name = "WebClient.findByUsername", query = "SELECT w FROM WebClient w WHERE w.username = :username"),
    @NamedQuery(name = "WebClient.findByPassword", query = "SELECT w FROM WebClient w WHERE w.password = :password")})
public class WebClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;

    public WebClient() {
    }

    public WebClient(String username) {
        this.username = username;
    }

    public WebClient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebClient)) {
            return false;
        }
        WebClient other = (WebClient) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return username+password;
    }
    
}
