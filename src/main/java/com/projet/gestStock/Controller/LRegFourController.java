package com.projet.gestStock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.LRegFour;
import com.projet.gestStock.repository.LRegFourRepository;
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
public class LRegFourController {
	@Autowired
    LRegFourRepository repository;
	 @GetMapping("/LLrecoufs")
	  public List<LRegFour> getAllLrecoufs() {
	    System.out.println("Get all Lrecoufs...");
	 
	    List<LRegFour> LRegFours = new ArrayList<>();
	    repository.findAll().forEach(LRegFours::add);
	 
	    return LRegFours;
	  }
	
	@GetMapping("/Lrecoufs/{id}")
	public ResponseEntity<LRegFour> getLrecoufById(@PathVariable(value = "id") Long LrecoufId)
			throws ResourceNotFoundException {
		LRegFour LRegFour = repository.findById(LrecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouf not found for this id :: " + LrecoufId));
		return ResponseEntity.ok().body(LRegFour);
	}

	@PostMapping("/Lrecoufs")
	public @Valid LRegFour createLrecouf(@Valid @RequestBody LRegFour LRegFour) {
		
		return repository.save(LRegFour);
		
		 
	}
	

	@DeleteMapping("/Lrecoufs/{id}")
	public Map<String, Boolean> deleteLrecouf(@PathVariable(value = "id") Long LrecoufId)
			throws ResourceNotFoundException {
		LRegFour LRegFour = repository.findById(LrecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouf not found  id :: " + LrecoufId));

		repository.delete(LRegFour);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lrecoufs/delete")
	  public ResponseEntity<String> deleteAllLrecoufs() {
	    System.out.println("Delete All Lrecoufs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lrecoufs have been deleted!", HttpStatus.OK);
	  }
	 
	

	

}
