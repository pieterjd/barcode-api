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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder


@Entity
@Table(name = "BARCODE")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Barcode {
    @Id
    @GeneratedValue
    private Long id;    
    @EqualsAndHashCode.Include
    @Column
    private String barcode;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Description> descriptions = new HashSet<>();
}
