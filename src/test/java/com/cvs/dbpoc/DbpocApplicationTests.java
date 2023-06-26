package com.cvs.dbpoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cvs.dbpoc.repository.neww.NewRepository;
import com.cvs.dbpoc.repository.oldd.OldRepository;
import com.cvs.dbpoc.entity.neww.NewDetails;
import com.cvs.dbpoc.entity.oldd.OldDetails;

@SpringBootTest
class DbpocApplicationTests {

	@Autowired
	OldRepository oldRepo;
		
	@Autowired
	NewRepository newRepo;

	@Test
	void testRepo() {
		oldRepo.deleteAll();
		newRepo.deleteAll();

		oldRepo.save(OldDetails.of("John", "johndoe@gmail.com"));
		oldRepo.save(OldDetails.of("Evan", "evandoe@gmail.com"));
		newRepo.save(NewDetails.of("Liam", "liamdoe@gmail.com"));
		newRepo.save(NewDetails.of("Roxane", "roxanedoe@gmail.com"));
		
		Assertions.assertEquals(oldRepo.count(), 2);
		Assertions.assertEquals(newRepo.count(), 2);
	}

}
