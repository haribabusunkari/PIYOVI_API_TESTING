package com.piyovi.parsers;

import java.util.ArrayList;

public class ShipmentRate {
	private String carrierName;
	private String serviceName;
	private String serviceDescription;
	private double freight;
	private double discountFrieght;
	private double baseCharge;
	private double totalSurcharges;
	private String currencycode;
	private String estimatedDelivery;
	private ArrayList surcharges;
	private String carrierType;
	private int transitDays;
	private String billingWeight;
	private double serviceOptionsCharges;
	private double transportationCharges;
	private boolean moneybackGuarantee;
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public double getDiscountFrieght() {
		return discountFrieght;
	}
	public void setDiscountFrieght(double discountFrieght) {
		this.discountFrieght = discountFrieght;
	}
	public double getBaseCharge() {
		return baseCharge;
	}
	public void setBaseCharge(double baseCharge) {
		this.baseCharge = baseCharge;
	}
	public double getTotalSurcharges() {
		return totalSurcharges;
	}
	public void setTotalSurcharges(double totalSurcharges) {
		this.totalSurcharges = totalSurcharges;
	}
	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public String getEstimatedDelivery() {
		return estimatedDelivery;
	}
	public void setEstimatedDelivery(String estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	}
	public ArrayList getSurcharges() {
		return surcharges;
	}
	public void setSurcharges(ArrayList surcharges) {
		this.surcharges = surcharges;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public int getTransitDays() {
		return transitDays;
	}
	public void setTransitDays(int transitDays) {
		this.transitDays = transitDays;
	}
	public String getBillingWeight() {
		return billingWeight;
	}
	public void setBillingWeight(String billingWeight) {
		this.billingWeight = billingWeight;
	}
	public double getServiceOptionsCharges() {
		return serviceOptionsCharges;
	}
	public void setServiceOptionsCharges(double serviceOptionsCharges) {
		this.serviceOptionsCharges = serviceOptionsCharges;
	}
	public double getTransportationCharges() {
		return transportationCharges;
	}
	public void setTransportationCharges(double transportationCharges) {
		this.transportationCharges = transportationCharges;
	}
	public boolean getMoneybackGuarantee() {
		return moneybackGuarantee;
	}
	public void setMoneybackGuarantee(boolean moneybackGuarantee) {
		this.moneybackGuarantee = moneybackGuarantee;
	}
	
}
