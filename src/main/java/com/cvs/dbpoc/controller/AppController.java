package com.cvs.dbpoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cvs.dbpoc.repository.neww.NewRepository;
import com.cvs.dbpoc.repository.oldd.OldRepository;
import com.cvs.dbpoc.entity.neww.NewDetails;
import com.cvs.dbpoc.entity.oldd.OldDetails;

@RestController
public class AppController {
    
    @Autowired
    private OldRepository oldRepo;

    @Autowired
    private NewRepository newRepo;

    // Change this to change where the saved records are stored
    private static final String dbswitch = "new";

    @PostMapping("/save")
    public ResponseEntity<String> addRecord(@RequestBody OldDetails data) {
        if (dbswitch.equals("old")) {
            oldRepo.save(OldDetails.of(data.getName(), data.getEmail()));
        } else if (dbswitch.equals("new")) {
            newRepo.save(NewDetails.of(data.getName(), data.getEmail()));
        } 
        return new ResponseEntity<>("Record added", HttpStatus.OK);
    }

    @GetMapping("/getOldRecords")
    public List<OldDetails> getOldRecords() {
        List<OldDetails> details = new ArrayList<>();
        oldRepo.findAll().forEach(details::add);
        return details;
    }

    @GetMapping("/getNewRecords")
    public List<NewDetails> getNewRecords() {
        List<NewDetails> details = new ArrayList<>();
        newRepo.findAll().forEach(details::add);
        return details;
    }
}
