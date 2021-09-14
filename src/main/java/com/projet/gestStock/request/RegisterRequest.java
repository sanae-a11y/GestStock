package com.projet.gestStock.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	@NotBlank
    @Size(min = 3, max = 40)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
	private boolean isActive;
	@NotBlank
	private String role;
    public RegisterRequest(String username,String email,String password , String role){
        this.username = username ;
        this.email = email;
        this.password = password ;
        this.role = role ;
    }


	public boolean getIsActive() {
		return this.isActive ;
	}
}
