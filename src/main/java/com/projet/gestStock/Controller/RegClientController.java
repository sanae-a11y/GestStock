package com.projet.gestStock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.LRegClient;
import com.projet.gestStock.model.RegClient;
import com.projet.gestStock.repository.LRegClientRepository;
import com.projet.gestStock.repository.RegClientRepository;
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
public class RegClientController {
	@Autowired
	RegClientRepository repository;
	@Autowired
	LRegClientRepository repo;

	@Autowired  ServletContext context;
	 @GetMapping("/Recouvs")
	  public List<RegClient> getAllRecouvs() {
	    System.out.println("Get all Recouvs...");
	    List<RegClient> regClients = new ArrayList<>();
	    repository.findAll().forEach(regClients::add);
	    return regClients;
	  }
	
	@GetMapping("/Recouvs/{id}")
	public ResponseEntity<RegClient> getRecouvById(@PathVariable(value = "id") Long RecouvId)
			throws ResourceNotFoundException {
		RegClient RegClient = repository.findById(RecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouv not found for this id :: " + RecouvId));
		return ResponseEntity.ok().body(RegClient);
	}

	@PostMapping("/Recouvs")
	public ResponseEntity<RegClient> createRecouf(@Valid @RequestBody RegClient RegClient)  throws JsonParseException , JsonMappingException , Exception{
		  repository.save(RegClient);
		  List<LRegClient> LRegClients = RegClient.getLRegClients();
		    for (LRegClient lc : LRegClients) {
	          	lc.setNumero(RegClient.getNumero());
	          	repo.save(lc);
		       }	 

			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/Recouvs/{id}")
	public Map<String, Boolean> deleteRecouv(@PathVariable(value = "id") Long RecouvId)
			throws ResourceNotFoundException {
		RegClient RegClient = repository.findById(RecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouv not found  id :: " + RecouvId));
		repository.delete(RegClient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Recouvs/delete")
	  public ResponseEntity<String> deleteAllRecouvs() {
	    System.out.println("Delete All Recouvs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Recouvs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Recouvs/{id}")
	  public ResponseEntity<RegClient> updateRecouv(@PathVariable("id") long id, @RequestBody RegClient RegClient) {
	    System.out.println("Update Recouv with ID = " + id + "...");
	    Optional<RegClient> RecouvInfo = repository.findById(id);
	    if (RecouvInfo.isPresent()) {
	    	RegClient regClient = RecouvInfo.get();
	    	regClient.setLibelle(RegClient.getLibelle());
	      return new ResponseEntity<>(repository.save(RegClient), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }		
}
