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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juansaab
 */
@Entity
@Table(name = "PurchaseDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseDetails.findAll", query = "SELECT p FROM PurchaseDetails p"),
    @NamedQuery(name = "PurchaseDetails.findByIdPurchaseDetails", query = "SELECT p FROM PurchaseDetails p WHERE p.idPurchaseDetails = :idPurchaseDetails"),
    @NamedQuery(name = "PurchaseDetails.findByIdPurchase", query = "SELECT p FROM PurchaseDetails p WHERE p.idPurchase = :idPurchase"),
    @NamedQuery(name = "PurchaseDetails.findByIdProduct", query = "SELECT p FROM PurchaseDetails p WHERE p.idProduct = :idProduct"),
    @NamedQuery(name = "PurchaseDetails.findByQuantity", query = "SELECT p FROM PurchaseDetails p WHERE p.quantity = :quantity")})
public class PurchaseDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPurchaseDetails")
    private Integer idPurchaseDetails;
    @Column(name = "idPurchase")
    private Integer idPurchase;
    @Column(name = "idProduct")
    private Integer idProduct;
    @Column(name = "quantity")
    private Integer quantity;

    public PurchaseDetails() {
    }

    public PurchaseDetails(Integer idPurchaseDetails) {
        this.idPurchaseDetails = idPurchaseDetails;
    }

    public Integer getIdPurchaseDetails() {
        return idPurchaseDetails;
    }

    public void setIdPurchaseDetails(Integer idPurchaseDetails) {
        this.idPurchaseDetails = idPurchaseDetails;
    }

    public Integer getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPurchaseDetails != null ? idPurchaseDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseDetails)) {
            return false;
        }
        PurchaseDetails other = (PurchaseDetails) object;
        if ((this.idPurchaseDetails == null && other.idPurchaseDetails != null) || (this.idPurchaseDetails != null && !this.idPurchaseDetails.equals(other.idPurchaseDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.PurchaseDetails[ idPurchaseDetails=" + idPurchaseDetails + " ]";
    }
    
}
