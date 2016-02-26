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
@Table(name = "RolesPermissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesPermissions.findAll", query = "SELECT r FROM RolesPermissions r"),
    @NamedQuery(name = "RolesPermissions.findByIdRolesPermissions", query = "SELECT r FROM RolesPermissions r WHERE r.idRolesPermissions = :idRolesPermissions")})
public class RolesPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRolesPermissions")
    private Integer idRolesPermissions;
    @JoinColumn(name = "idRole", referencedColumnName = "idRoles")
    @ManyToOne(optional = false)
    private Roles idRole;
    @JoinColumn(name = "idPermisson", referencedColumnName = "idPermissons")
    @ManyToOne(optional = false)
    private Permissons idPermisson;

    public RolesPermissions() {
    }

    public RolesPermissions(Integer idRolesPermissions) {
        this.idRolesPermissions = idRolesPermissions;
    }

    public Integer getIdRolesPermissions() {
        return idRolesPermissions;
    }

    public void setIdRolesPermissions(Integer idRolesPermissions) {
        this.idRolesPermissions = idRolesPermissions;
    }

    public Roles getIdRole() {
        return idRole;
    }

    public void setIdRole(Roles idRole) {
        this.idRole = idRole;
    }

    public Permissons getIdPermisson() {
        return idPermisson;
    }

    public void setIdPermisson(Permissons idPermisson) {
        this.idPermisson = idPermisson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolesPermissions != null ? idRolesPermissions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPermissions)) {
            return false;
        }
        RolesPermissions other = (RolesPermissions) object;
        if ((this.idRolesPermissions == null && other.idRolesPermissions != null) || (this.idRolesPermissions != null && !this.idRolesPermissions.equals(other.idRolesPermissions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.RolesPermissions[ idRolesPermissions=" + idRolesPermissions + " ]";
    }
    
}
