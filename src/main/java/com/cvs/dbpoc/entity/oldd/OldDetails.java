package com.cvs.dbpoc.entity.oldd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OLD_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OldDetails {
    
    @Id
    @GeneratedValue
    private long id;

    @CreationTimestamp
    private Date created;

    @Column
    private String name;

    @Column
    private String email;

    public static OldDetails of(String name, String email) {
        OldDetails details = new OldDetails();
        details.name = name;
        details.email = email;
        return details;
    }
}
