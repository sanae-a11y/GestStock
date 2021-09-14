package com.projet.gestStock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.FactClient;
import com.projet.gestStock.model.Lfact;
import com.projet.gestStock.repository.FactRepository;
import com.projet.gestStock.repository.LfactRepository;
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
public class FactController {
	@Autowired
	FactRepository repository;
	@Autowired
	LfactRepository repo;

	@Autowired  ServletContext context;
	 @GetMapping("/facts")
	  public List<FactClient> getAllFacts() {
	    System.out.println("Get all Facts...");
	    List<FactClient> factClients = new ArrayList<>();
	    repository.findAll().forEach(factClients::add);
	    return factClients;
	  }
	
	@GetMapping("/facts/{id}")
	public ResponseEntity<FactClient> getFactById(@PathVariable(value = "id") Long FactId)
			throws ResourceNotFoundException {
		FactClient FactClient = repository.findById(FactId)
				.orElseThrow(() -> new ResourceNotFoundException("Fact not found for this id :: " + FactId));
		return ResponseEntity.ok().body(FactClient);
	}

	@PostMapping("/facts")
	public ResponseEntity<FactClient> createBs1016(@Valid @RequestBody FactClient FactClient)  throws JsonParseException , JsonMappingException , Exception{
		  repository.save(FactClient);
		  List<Lfact> lfacts = FactClient.getLfacts();
		    for (Lfact lc : lfacts) {
	          	lc.setNumero(FactClient.getNumero());
	          	repo.save(lc);
		       }	 

			 return new ResponseEntity<>(HttpStatus.OK);
		} 
	

	@DeleteMapping("/facts/{id}")
	public Map<String, Boolean> deleteFact(@PathVariable(value = "id") Long FactId)
			throws ResourceNotFoundException {
		FactClient FactClient = repository.findById(FactId)
				.orElseThrow(() -> new ResourceNotFoundException("Fact not found  id :: " + FactId));
		repository.delete(FactClient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/facts/delete")
	  public ResponseEntity<String> deleteAllFacts() {
	    System.out.println("Delete All Facts...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Facts have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Facts/{id}")
	  public ResponseEntity<FactClient> updateFact(@PathVariable("id") long id, @RequestBody FactClient FactClient) {
	    System.out.println("Update Fact with ID = " + id + "...");
	    Optional<FactClient> FactInfo = repository.findById(id);
	    if (FactInfo.isPresent()) {
	    	FactClient factClient = FactInfo.get();
	    	factClient.setLibelle(FactClient.getLibelle());
	      return new ResponseEntity<>(repository.save(FactClient), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
