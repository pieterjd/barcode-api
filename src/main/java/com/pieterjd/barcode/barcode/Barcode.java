package com.pieterjd.barcode.barcode;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder


@Table("BARCODE")
public class Barcode {
    @Id
    private Long id;    
    private String barcode;
    @Builder.Default
    private Set<Description> descriptions = new HashSet<>();
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
        result = prime * result + ((descriptions == null) ? 0 : descriptions.hashCode());
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
        if (barcode == null) {
            if (other.barcode != null)
                return false;
        } else if (!barcode.equals(other.barcode))
            return false;
        return true;
    }

    
}
