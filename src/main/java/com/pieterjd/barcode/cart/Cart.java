package com.pieterjd.barcode.cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.pieterjd.barcode.barcode.Barcode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Table(name = "CART")
@Entity
public class Cart {
    private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("nl-BE");
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(name = "CART_BARCODE", joinColumns = @JoinColumn(name = "CART_ID"), inverseJoinColumns = @JoinColumn(name = "BARCODE_ID"))
    private List<Barcode> barcodes = new ArrayList<>();
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private Locale locale;

    public void addBarcode(Barcode b) {
        barcodes.add(b);
        b.getCarts().add(this);
    }

    public void removeBarcode(Barcode b) {
        barcodes.remove(b);
        b.getCarts().remove(this);
    }

    public List<String> getContent() {
        return barcodes.stream()
                .map(b -> b.getDescriptions())
                .flatMap(Set::stream)
                .filter(d -> d.getLocale().equals(DEFAULT_LOCALE))
                .map(d -> d.getText())
                .collect(Collectors.toList());
    }

    public Map<String, Long> getGroupedContent(){
        return getContent().stream()
        .collect(Collectors.groupingBy(s->s, Collectors.counting()));
    }
}
