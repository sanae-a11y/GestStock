package com.projet.gestStock.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.servlet.ServletContext;
import javax.validation.Valid;

import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.Comm;
import com.projet.gestStock.model.Lcomm;
import com.projet.gestStock.repository.CommRepository;
import com.projet.gestStock.repository.LcommRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommController {
	@Autowired
	CommRepository repository;
	@Autowired
	LcommRepository repo;


	
	 @GetMapping("/comms")
	  public List<Comm> getAllComms() {
	    System.out.println("Get all Comms...");
	    List<Comm> Comms = new ArrayList<>();
	    repository.findAll().forEach(Comms::add);
	    return Comms;
	  }
	
	@GetMapping("/comms/{id}")
	public ResponseEntity<Comm> getCommById(@PathVariable(value = "id") Long CommId)
			throws ResourceNotFoundException {
		Comm Comm = repository.findById(CommId)
		
				.orElseThrow(() -> new ResourceNotFoundException("Comm not found for this id :: " + CommId));
		return ResponseEntity.ok().body(Comm);
	}
	
	@PostMapping("/comms")
	public ResponseEntity<Comm> createComm(@Valid @RequestBody Comm Comm)  
			throws JsonParseException , JsonMappingException , Exception{
		  
		repository.save(Comm);
		List<Lcomm> lcomms = Comm.getLcomms();
	    for (Lcomm lc : lcomms) {
	        lc.setNumero(Comm.getNumero());
       		repo.save(lc);
	       }	 
	

		 return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@DeleteMapping("/comms/{id}")
	public ResponseEntity<Comm>  deleteComm(@PathVariable(value = "id") Long CommId)
	{
		Optional<Comm> CommInfo = repository.findById(CommId);
	  if (CommInfo.isPresent()) {
		  System.out.println("Commande 11");
		  Comm Comm = CommInfo.get();
		  repo.deleteByNumero(Comm.getNumero());
		  repository.delete(Comm);
		  return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	 
	  @DeleteMapping("/comms/delete")
	  public ResponseEntity<String> deleteAllComms() {
	    System.out.println("Delete All Comms...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Comms have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/comms/{id}")
	  public ResponseEntity<Comm> updateComm(@PathVariable("id") long id, @RequestBody Comm Comm) {
	    System.out.println("Update Comm with ID = " + id + "...");
	    Optional<Comm> CommInfo = repository.findById(id);
	    if (CommInfo.isPresent()) {
	    	Comm comm = CommInfo.get();
	    	comm.setLibelle(Comm.getLibelle());
	      return new ResponseEntity<>(repository.save(comm), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	
	 
}
