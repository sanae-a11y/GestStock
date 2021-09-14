package com.projet.gestStock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.LRegClient;
import com.projet.gestStock.repository.LRegClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LRegClientController {
	@Autowired
    LRegClientRepository repository;
	 @GetMapping("/LLrecouvs")
	  public List<LRegClient> getAllLrecouvs() {
	    System.out.println("Get all Lrecouvs...");
	 
	    List<LRegClient> LRegClients = new ArrayList<>();
	    repository.findAll().forEach(LRegClients::add);
	 
	    return LRegClients;
	  }
	
	@GetMapping("/Lrecouvs/{id}")
	public ResponseEntity<LRegClient> getLrecouvById(@PathVariable(value = "id") Long LrecouvId)
			throws ResourceNotFoundException {
		LRegClient LRegClient = repository.findById(LrecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouv not found for this id :: " + LrecouvId));
		return ResponseEntity.ok().body(LRegClient);
	}

	@PostMapping("/Lrecouvs")
	public @Valid LRegClient createLrecouv(@Valid @RequestBody LRegClient LRegClient) {
		
		return repository.save(LRegClient);
		
		 
	}
	

	@DeleteMapping("/Lrecouvs/{id}")
	public Map<String, Boolean> deleteLrecouv(@PathVariable(value = "id") Long LrecouvId)
			throws ResourceNotFoundException {
		LRegClient LRegClient = repository.findById(LrecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouv not found  id :: " + LrecouvId));

		repository.delete(LRegClient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lrecouvs/delete")
	  public ResponseEntity<String> deleteAllLrecouvs() {
	    System.out.println("Delete All Lrecouvs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lrecouvs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
