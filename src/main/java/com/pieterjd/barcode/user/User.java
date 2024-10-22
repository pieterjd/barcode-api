package com.pieterjd.barcode.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "USERNAME")
    private String userName;
    @Column
    private String password;
    @Column
    private boolean enabled;
    
}
