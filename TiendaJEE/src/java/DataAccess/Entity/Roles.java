/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author japrietov
 */
@Entity
@Table(name = "Roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByIdRoles", query = "SELECT r FROM Roles r WHERE r.idRoles = :idRoles"),
    @NamedQuery(name = "Roles.findByName", query = "SELECT r FROM Roles r WHERE r.name = :name")})
public class Roles implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRole")
    private Collection<RolesPermissions> rolesPermissionsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRoles")
    private Integer idRoles;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "rolesCollection")
    private Collection<Permissons> permissonsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoles")
    private Collection<Users> usersCollection;

    public Roles() {
    }

    public Roles(Integer idRoles) {
        this.idRoles = idRoles;
    }

    public Integer getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Integer idRoles) {
        this.idRoles = idRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Permissons> getPermissonsCollection() {
        return permissonsCollection;
    }

    public void setPermissonsCollection(Collection<Permissons> permissonsCollection) {
        this.permissonsCollection = permissonsCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoles != null ? idRoles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idRoles == null && other.idRoles != null) || (this.idRoles != null && !this.idRoles.equals(other.idRoles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Roles[ idRoles=" + idRoles + " ]";
    }

    @XmlTransient
    public Collection<RolesPermissions> getRolesPermissionsCollection() {
        return rolesPermissionsCollection;
    }

    public void setRolesPermissionsCollection(Collection<RolesPermissions> rolesPermissionsCollection) {
        this.rolesPermissionsCollection = rolesPermissionsCollection;
    }
    
}
