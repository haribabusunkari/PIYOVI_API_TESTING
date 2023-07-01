package com.piyovi.parsers;

import java.util.ArrayList;

public class Packages {
	private String trackingNumber;
	private double freight;
	private double discountfrieght;
	private String package_Id;
	private ArrayList label;
	
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public double getDiscountfrieght() {
		return discountfrieght;
	}
	public void setDiscountfrieght(double discountfrieght) {
		this.discountfrieght = discountfrieght;
	}
	public String getPackage_Id() {
		return package_Id;
	}
	public void setPackage_Id(String package_Id) {
		this.package_Id = package_Id;
	}
	public ArrayList getLabel() {
		return label;
	}
	public void setLabel(ArrayList label) {
		this.label = label;
	}
}
