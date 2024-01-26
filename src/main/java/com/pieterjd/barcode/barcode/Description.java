package com.pieterjd.barcode.barcode;

import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name="DESCRIPTION")
public class Description {
    @Id
    @GeneratedValue
    @Exclude
    private Long id;
    @Column
    private Locale locale;
    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Barcode barcode;
}
