/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author japrietov
 */
@Entity
@Table(name = "Purchases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchases.findAll", query = "SELECT p FROM Purchases p"),
    @NamedQuery(name = "Purchases.findByIdPurchases", query = "SELECT p FROM Purchases p WHERE p.idPurchases = :idPurchases"),
    @NamedQuery(name = "Purchases.findByDate", query = "SELECT p FROM Purchases p WHERE p.date = :date"),
    @NamedQuery(name = "Purchases.findByTotal", query = "SELECT p FROM Purchases p WHERE p.total = :total")})
public class Purchases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPurchases")
    private Integer idPurchases;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Float total;
    @JoinColumn(name = "idSeller", referencedColumnName = "idUsers")
    @ManyToOne(optional = false)
    private Users idSeller;
    @JoinColumn(name = "idBuyer", referencedColumnName = "idUsers")
    @ManyToOne(optional = false)
    private Users idBuyer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPurchase")
    private Collection<PurchaseDetails> purchaseDetailsCollection;

    public Purchases() {
    }

    public Purchases(Integer idPurchases) {
        this.idPurchases = idPurchases;
    }

    public Integer getIdPurchases() {
        return idPurchases;
    }

    public void setIdPurchases(Integer idPurchases) {
        this.idPurchases = idPurchases;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Users getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Users idSeller) {
        this.idSeller = idSeller;
    }

    public Users getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(Users idBuyer) {
        this.idBuyer = idBuyer;
    }

    @XmlTransient
    public Collection<PurchaseDetails> getPurchaseDetailsCollection() {
        return purchaseDetailsCollection;
    }

    public void setPurchaseDetailsCollection(Collection<PurchaseDetails> purchaseDetailsCollection) {
        this.purchaseDetailsCollection = purchaseDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPurchases != null ? idPurchases.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchases)) {
            return false;
        }
        Purchases other = (Purchases) object;
        if ((this.idPurchases == null && other.idPurchases != null) || (this.idPurchases != null && !this.idPurchases.equals(other.idPurchases))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Purchases[ idPurchases=" + idPurchases + " ]";
    }
    
}
