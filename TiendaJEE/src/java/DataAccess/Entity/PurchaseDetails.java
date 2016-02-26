/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author japrietov
 */
@Entity
@Table(name = "PurchaseDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseDetails.findAll", query = "SELECT p FROM PurchaseDetails p"),
    @NamedQuery(name = "PurchaseDetails.findByIdPurchasesDetails", query = "SELECT p FROM PurchaseDetails p WHERE p.idPurchasesDetails = :idPurchasesDetails"),
    @NamedQuery(name = "PurchaseDetails.findByQuantity", query = "SELECT p FROM PurchaseDetails p WHERE p.quantity = :quantity")})
public class PurchaseDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPurchasesDetails")
    private Integer idPurchasesDetails;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "idProduc", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private Product idProduc;
    @JoinColumn(name = "idPurchase", referencedColumnName = "idPurchases")
    @ManyToOne(optional = false)
    private Purchases idPurchase;

    public PurchaseDetails() {
    }

    public PurchaseDetails(Integer idPurchasesDetails) {
        this.idPurchasesDetails = idPurchasesDetails;
    }

    public PurchaseDetails(Integer idPurchasesDetails, int quantity) {
        this.idPurchasesDetails = idPurchasesDetails;
        this.quantity = quantity;
    }

    public Integer getIdPurchasesDetails() {
        return idPurchasesDetails;
    }

    public void setIdPurchasesDetails(Integer idPurchasesDetails) {
        this.idPurchasesDetails = idPurchasesDetails;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getIdProduc() {
        return idProduc;
    }

    public void setIdProduc(Product idProduc) {
        this.idProduc = idProduc;
    }

    public Purchases getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(Purchases idPurchase) {
        this.idPurchase = idPurchase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPurchasesDetails != null ? idPurchasesDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseDetails)) {
            return false;
        }
        PurchaseDetails other = (PurchaseDetails) object;
        if ((this.idPurchasesDetails == null && other.idPurchasesDetails != null) || (this.idPurchasesDetails != null && !this.idPurchasesDetails.equals(other.idPurchasesDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.PurchaseDetails[ idPurchasesDetails=" + idPurchasesDetails + " ]";
    }
    
}
