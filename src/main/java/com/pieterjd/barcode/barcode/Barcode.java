package com.pieterjd.barcode.barcode;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

@Builder


@Entity
@Table(name = "BARCODE")

public class Barcode {
    @Id
    @GeneratedValue
    private Long id;    

    @Column
    private String barcode;
    @Builder.Default
    @OneToMany(mappedBy = "barcode", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Description> descriptions = new HashSet<>();
    public void addDescription(Description d) {
        descriptions.add(d);
        d.setBarcode(this);
    }
    public void removeDescription(Description d) {
        descriptions.remove(d);
        d.setBarcode(null);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Barcode other = (Barcode) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

    

    
}
