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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juansaab
 */
@Entity
@Table(name = "RolesPermissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesPermissions.findAll", query = "SELECT r FROM RolesPermissions r"),
    @NamedQuery(name = "RolesPermissions.findByIdRolesPermissions", query = "SELECT r FROM RolesPermissions r WHERE r.idRolesPermissions = :idRolesPermissions"),
    @NamedQuery(name = "RolesPermissions.findByIdRole", query = "SELECT r FROM RolesPermissions r WHERE r.idRole = :idRole"),
    @NamedQuery(name = "RolesPermissions.findByIdPermission", query = "SELECT r FROM RolesPermissions r WHERE r.idPermission = :idPermission")})
public class RolesPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRolesPermissions")
    private Integer idRolesPermissions;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRole")
    private int idRole;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPermission")
    private int idPermission;

    public RolesPermissions() {
    }

    public RolesPermissions(Integer idRolesPermissions) {
        this.idRolesPermissions = idRolesPermissions;
    }

    public RolesPermissions(Integer idRolesPermissions, int idRole, int idPermission) {
        this.idRolesPermissions = idRolesPermissions;
        this.idRole = idRole;
        this.idPermission = idPermission;
    }

    public Integer getIdRolesPermissions() {
        return idRolesPermissions;
    }

    public void setIdRolesPermissions(Integer idRolesPermissions) {
        this.idRolesPermissions = idRolesPermissions;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
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
