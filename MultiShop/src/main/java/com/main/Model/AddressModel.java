package com.main.Model;

import com.main.Entity.Category;
import com.main.Entity.Product;
import com.main.Entity.ProductCate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class AddressModel {
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String provincial;
	private String address;
	private boolean payment = true;
	
	//user
	
//	public String email2;
//	public String fullname;
//	private String oldPassword;
//	private String newPassword;
//	private String confirm;
	
}
