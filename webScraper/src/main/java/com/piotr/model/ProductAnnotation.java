package com.piotr.model;
import javax.persistence.*;

@Entity
@Table(name="product")
public class ProductAnnotation {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "brand")
	private int brand;

	@Column(name = "size")
	private int size;



	/** Empty constructor */
	public ProductAnnotation(){
	}

	//Getters and setters



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	} 


	/** Returns a String representation of the Cereal */
	public String toString(){
		String str = "Cereal. id: " + id + "; name: " + name + "; brand: " +
				brand + "; size: " + size;
		return str;
	}


}
