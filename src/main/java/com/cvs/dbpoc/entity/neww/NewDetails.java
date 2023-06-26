package com.cvs.dbpoc.entity.neww;

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
@Table(name = "NEW_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDetails {
    
    @Id
    @GeneratedValue
    private long id;

    @CreationTimestamp
    private Date created;

    @Column
    private String name;

    @Column
    private String email;

    public static NewDetails of(String name, String email) {
        NewDetails details = new NewDetails();
        details.name = name;
        details.email = email;
        return details;
    }
}
