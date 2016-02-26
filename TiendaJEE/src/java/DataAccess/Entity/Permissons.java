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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "Permissons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissons.findAll", query = "SELECT p FROM Permissons p"),
    @NamedQuery(name = "Permissons.findByIdPermissons", query = "SELECT p FROM Permissons p WHERE p.idPermissons = :idPermissons"),
    @NamedQuery(name = "Permissons.findByName", query = "SELECT p FROM Permissons p WHERE p.name = :name")})
public class Permissons implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPermisson")
    private Collection<RolesPermissions> rolesPermissionsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPermissons")
    private Integer idPermissons;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "RolesPermissions", joinColumns = {
        @JoinColumn(name = "idPermisson", referencedColumnName = "idPermissons")}, inverseJoinColumns = {
        @JoinColumn(name = "idRole", referencedColumnName = "idRoles")})
    @ManyToMany
    private Collection<Roles> rolesCollection;

    public Permissons() {
    }

    public Permissons(Integer idPermissons) {
        this.idPermissons = idPermissons;
    }

    public Integer getIdPermissons() {
        return idPermissons;
    }

    public void setIdPermissons(Integer idPermissons) {
        this.idPermissons = idPermissons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermissons != null ? idPermissons.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissons)) {
            return false;
        }
        Permissons other = (Permissons) object;
        if ((this.idPermissons == null && other.idPermissons != null) || (this.idPermissons != null && !this.idPermissons.equals(other.idPermissons))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Permissons[ idPermissons=" + idPermissons + " ]";
    }

    @XmlTransient
    public Collection<RolesPermissions> getRolesPermissionsCollection() {
        return rolesPermissionsCollection;
    }

    public void setRolesPermissionsCollection(Collection<RolesPermissions> rolesPermissionsCollection) {
        this.rolesPermissionsCollection = rolesPermissionsCollection;
    }
    
}
