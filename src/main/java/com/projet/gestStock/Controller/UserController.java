package com.projet.gestStock.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.projet.gestStock.config.JwtTokenUtil;
import com.projet.gestStock.domaine.JwtResponse;
import com.projet.gestStock.domaine.Message;
import com.projet.gestStock.exception.ResourceNotFoundException;
import com.projet.gestStock.model.User;
import com.projet.gestStock.repository.UserRepository;
import com.projet.gestStock.request.LoginRequest;
import com.projet.gestStock.request.RegisterRequest;
import com.projet.gestStock.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository repository;

	@Autowired 	AuthenticationManager authenticationManager;

	@Autowired	UserRepository userRepository;

	@Autowired	PasswordEncoder encoder;

	@Autowired
	JwtTokenUtil jwtUtils;

	 @GetMapping("/users")
	  public List<User> getAllUtilisateur() {
	    System.out.println("Get all Utilisateur...");
	 
	    List<User> Utilisateur = new ArrayList<>();
	    repository.findAll().forEach(Utilisateur::add);
	 
	    return Utilisateur;
	  }
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUtilisateurById(@PathVariable(value = "id") Long UtilisateurId)
			throws ResourceNotFoundException {
		User Utilisateur = repository.findById(UtilisateurId)
				.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :: " + UtilisateurId));
		return ResponseEntity.ok().body(Utilisateur);
	}

	 
	
	
	
		@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUtilisateur(@PathVariable(value = "id") Long UtilisateurId)
			throws ResourceNotFoundException {
		User Utilisateur = repository.findById(UtilisateurId)
				.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found  id :: " + UtilisateurId));

		repository.delete(Utilisateur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/users/delete")
	  public ResponseEntity<String> deleteAllUtilisateur() {
	    System.out.println("Delete All Utilisateur...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Utilisateurs have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/users/{id}")
	  public ResponseEntity<User> updateUtilisateur(@PathVariable("id") long id, @RequestBody User Utilisateur) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	 
	    Optional<User> UtilisateurInfo = repository.findById(id);
	 
	    if (UtilisateurInfo.isPresent()) {
	    	User utilisateur = UtilisateurInfo.get();
	    	utilisateur.setRole(Utilisateur.getRole());
	    	utilisateur.setEmail(Utilisateur.getEmail());
	    	utilisateur.setUsername(Utilisateur.getUsername());
	    	utilisateur.setPassword(Utilisateur.getPassword());
	      return new ResponseEntity<>(repository.save(Utilisateur), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	
		@RequestMapping(value = "/authenticate",method = RequestMethod.POST)
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
			System.out.println("aaaa");
			System.out.println(data.getPassword());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							data.getUsername(),
							data.getPassword())
				
					);
			  System.out.println("bbbbb");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			System.out.println(userDetails.getUsername());
			System.out.println(userDetails.getRole());
			System.out.println(userDetails.getEmail());

			return ResponseEntity.ok(new JwtResponse(jwt,
													 userDetails.getId(), 
													 userDetails.getUsername(), 
													 userDetails.getEmail(), 
													 userDetails.getRole()));
		}

		@PostMapping("/users")
		public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Username is already taken!"));
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Email is already in use!"));
			}

			// Create new user's account
			User user = new User(signUpRequest.getUsername(),
								 signUpRequest.getEmail(),
								 encoder.encode(signUpRequest.getPassword()),
					             signUpRequest.getRole());
			userRepository.save(user);

			return ResponseEntity.ok(new Message("User registered successfully!"));
		}	  
	
}
