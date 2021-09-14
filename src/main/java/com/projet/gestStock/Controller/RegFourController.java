package com.projet.gestStock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.LRegFour;
import com.projet.gestStock.model.RegFour;
import com.projet.gestStock.repository.LRegFourRepository;
import com.projet.gestStock.repository.RegFourRepository;
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
public class RegFourController {
	@Autowired
	RegFourRepository repository;
	@Autowired
	LRegFourRepository repo;

	@Autowired  ServletContext context;
	 @GetMapping("/Recoufs")
	  public List<RegFour> getAllRecoufs() {
	    System.out.println("Get all Recoufs...");
	    List<RegFour> regFours = new ArrayList<>();
	    repository.findAll().forEach(regFours::add);
	    return regFours;
	  }
	
	@GetMapping("/Recoufs/{id}")
	public ResponseEntity<RegFour> getRecoufById(@PathVariable(value = "id") Long RecoufId)
			throws ResourceNotFoundException {
		RegFour RegFour = repository.findById(RecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouf not found for this id :: " + RecoufId));
		return ResponseEntity.ok().body(RegFour);
	}

	@PostMapping("/Recoufs")
	public ResponseEntity<RegFour> createRecouf(@Valid @RequestBody RegFour RegFour)  throws JsonParseException , JsonMappingException , Exception{
		  repository.save(RegFour);
		  List<LRegFour> LRegFours = RegFour.getLRegFours();
		    for (LRegFour lc : LRegFours) {
	          	lc.setNumero(RegFour.getNumero());
	          	repo.save(lc);
		       }	 

			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/Recoufs/{id}")
	public Map<String, Boolean> deleteRecouf(@PathVariable(value = "id") Long RecoufId)
			throws ResourceNotFoundException {
		RegFour RegFour = repository.findById(RecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouf not found  id :: " + RecoufId));
		repository.delete(RegFour);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Recoufs/delete")
	  public ResponseEntity<String> deleteAllRecoufs() {
	    System.out.println("Delete All Recoufs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Recoufs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Recoufs/{id}")
	  public ResponseEntity<RegFour> updateRecouf(@PathVariable("id") long id, @RequestBody RegFour RegFour) {
	    System.out.println("Update Recouf with ID = " + id + "...");
	    Optional<RegFour> RecoufInfo = repository.findById(id);
	    if (RecoufInfo.isPresent()) {
	    	RegFour regFour = RecoufInfo.get();
	    	regFour.setLibelle(RegFour.getLibelle());
	      return new ResponseEntity<>(repository.save(RegFour), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }		
}
