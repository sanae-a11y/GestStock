package com.projet.gestStock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.Flivr;
import com.projet.gestStock.model.Lflivr;
import com.projet.gestStock.repository.FlivrRepository;
import com.projet.gestStock.repository.LflivrRepository;
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
public class FlivrController {
	@Autowired
	FlivrRepository repository;
	@Autowired
	LflivrRepository repo;

	@Autowired  ServletContext context;
	 @GetMapping("/flivrs")
	  public List<Flivr> getAllFlivrs() {
	    System.out.println("Get all Flivrs...");
	    List<Flivr> Flivrs = new ArrayList<>();
	    repository.findAll().forEach(Flivrs::add);
	    return Flivrs;
	  }
	
	@GetMapping("/flivrs/{id}")
	public ResponseEntity<Flivr> getFlivrById(@PathVariable(value = "id") Long FlivrId)
			throws ResourceNotFoundException {
		Flivr Flivr = repository.findById(FlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Flivr not found for this id :: " + FlivrId));
		return ResponseEntity.ok().body(Flivr);
	}

	@PostMapping("/flivrs")
	public ResponseEntity<Flivr> createBs1016(@Valid @RequestBody Flivr Flivr)  throws JsonParseException , JsonMappingException , Exception{
		  repository.save(Flivr);
		  List<Lflivr> lflivrs = Flivr.getLflivrs();
		    for (Lflivr lc : lflivrs) {
	          	lc.setNumero(Flivr.getNumero());
	          	repo.save(lc);
		       }	 

			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/flivrs/{id}")
	public Map<String, Boolean> deleteFlivr(@PathVariable(value = "id") Long FlivrId)
			throws ResourceNotFoundException {
		Flivr Flivr = repository.findById(FlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Flivr not found  id :: " + FlivrId));
		repository.delete(Flivr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/flivrs/delete")
	  public ResponseEntity<String> deleteAllFlivrs() {
	    System.out.println("Delete All Flivrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Flivrs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/flivrs/{id}")
	  public ResponseEntity<Flivr> updateFlivr(@PathVariable("id") long id, @RequestBody Flivr Flivr) {
	    System.out.println("Update Flivr with ID = " + id + "...");
	    Optional<Flivr> FlivrInfo = repository.findById(id);
	    if (FlivrInfo.isPresent()) {
	    	Flivr flivr = FlivrInfo.get();
	    	flivr.setLibelle(Flivr.getLibelle());
	      return new ResponseEntity<>(repository.save(Flivr), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
