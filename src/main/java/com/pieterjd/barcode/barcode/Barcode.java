package com.pieterjd.barcode.barcode;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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


@Table("BARCODE")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Barcode {
    @Id
    private Long id;    
    @EqualsAndHashCode.Include
    private String barcode;
    @Builder.Default
    private Set<Description> descriptions = new HashSet<>();
}
