package co.matt.domain;

import java.util.List;

public class Device {
	
	private String brand;
	private String model;
	private String formFactor;
	private String fullName;
	private List<Attributes> attributes;
	
	public Device(String brand, String model, String formFactor, List<Attributes> attributes) {
		this.brand = brand;
		this.model = model;
		this.formFactor = formFactor;
		this.attributes = attributes;
		this.fullName = brand + " " + model;
	}
	
	public void setFullName() {
		this.fullName = brand + " " + model;
	}
	public String getFullName() {
		return fullName;
	}
	public List<Attributes> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attributes> attributes) {
		this.attributes = attributes;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFormFactor() {
		return formFactor;
	}
	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}
}
