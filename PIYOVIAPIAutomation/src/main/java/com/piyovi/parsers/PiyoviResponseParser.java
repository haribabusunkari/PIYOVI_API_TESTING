package com.piyovi.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PiyoviResponseParser {
	/* Response has 2 parts <1>. Status <2>.Payload */
	private String statusCode;
	private Map<String, Object> orginalPayloadFromResp;
	/* Retrieve data from response payload */
	private ArrayList Packages;
	private String carrierName;
	private String carrierscac;
	private String masterTrackingNumber;
	private String estimatedDelivery;
	private String shipment_Id;
	private double totalfreight;
	private double totalDiscountFreight;
	private double baseCharges;
	private double totalSurcharges;
	private double totalTaxes;
	private double totalDutiesAndTaxes;
	private ArrayList surcharges;
	private String currencyCode;
	private String remarks;
	private boolean moneybackGuarantee;
	private String destinationServiceArea;
	private String originServiceArea;
	private String destinationAirportId;
	private Map<String, Object> customProperties;
	private boolean success;
	private ArrayList errors;
	/* Process packages from response packages */
	private List<Packages> pacakgesList;
	
	public List<Packages> getPackagesList() {
		return pacakgesList;
	}
	public void setPackagesList(List packages) {
		this.pacakgesList = packages;
	}
	public ArrayList<Packages> getPackages() {
		return Packages;
	}
	public void setPackages(ArrayList packages) {
		this.Packages = packages;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Map<String, Object> getOrginalPayloadFromResp() {
		return orginalPayloadFromResp;
	}
	public void setOrginalPayloadFromResp(Map<String, Object> orginalPayloadFromResp) {
		this.orginalPayloadFromResp = orginalPayloadFromResp;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getCarrierscac() {
		return carrierscac;
	}
	public void setCarrierscac(String carrierscac) {
		this.carrierscac = carrierscac;
	}
	public String getMasterTrackingNumber() {
		return masterTrackingNumber;
	}
	public void setMasterTrackingNumber(String masterTrackingNumber) {
		this.masterTrackingNumber = masterTrackingNumber;
	}
	public String getEstimatedDelivery() {
		return estimatedDelivery;
	}
	public void setEstimatedDelivery(String estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	}
	public String getShipment_Id() {
		return shipment_Id;
	}
	public void setShipment_Id(String shipment_Id) {
		this.shipment_Id = shipment_Id;
	}
	public double getTotalfreight() {
		return totalfreight;
	}
	public void setTotalfreight(double totalfreight) {
		this.totalfreight = totalfreight;
	}
	public double getTotalDiscountFreight() {
		return totalDiscountFreight;
	}
	public void setTotalDiscountFreight(double totalDiscountFreight) {
		this.totalDiscountFreight = totalDiscountFreight;
	}
	public double getBaseCharges() {
		return baseCharges;
	}
	public void setBaseCharges(double baseCharges) {
		this.baseCharges = baseCharges;
	}
	public double getTotalSurcharges() {
		return totalSurcharges;
	}
	public void setTotalSurcharges(double totalSurcharges) {
		this.totalSurcharges = totalSurcharges;
	}
	public double getTotalTaxes() {
		return totalTaxes;
	}
	public void setTotalTaxes(double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}
	public double getTotalDutiesAndTaxes() {
		return totalDutiesAndTaxes;
	}
	public void setTotalDutiesAndTaxes(double totalDutiesAndTaxes) {
		this.totalDutiesAndTaxes = totalDutiesAndTaxes;
	}
	public ArrayList getSurcharges() {
		return surcharges;
	}
	public void setSurcharges(ArrayList Surcharges) {
		this.surcharges = Surcharges;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public boolean getMoneybackGuarantee() {
		return moneybackGuarantee;
	}
	public void setMoneybackGuarantee(boolean moneybackGuarantee) {
		this.moneybackGuarantee = moneybackGuarantee;
	}
	public String getDestinationServiceArea() {
		return destinationServiceArea;
	}
	public void setDestinationServiceArea(String destinationServiceArea) {
		this.destinationServiceArea = destinationServiceArea;
	}
	public String getOriginServiceArea() {
		return originServiceArea;
	}
	public void setOriginServiceArea(String originServiceArea) {
		this.originServiceArea = originServiceArea;
	}
	public String getDestinationAirportId() {
		return destinationAirportId;
	}
	public void setDestinationAirportId(String destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}
	public Map<String, Object> getCustomProperties() {
		return customProperties;
	}
	public void setCustomProperties(Map<String, Object> customProperties) {
		this.customProperties = customProperties;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ArrayList getErrors() {
		return errors;
	}
	public void setErrors(ArrayList success) {
		this.errors = success;
	}

	public void parseResponse(Map<String, Object> response) {
		for(Map.Entry m : response.entrySet()) {
			 switch(m.getKey().toString()) {
				 case "status":
					 this.setStatusCode(m.getValue().toString());
					 break;
				 case "payload":
					 this.setOrginalPayloadFromResp((Map<String, Object>)m.getValue());
					 break;
			 }
		}
		 
		for(Map.Entry m1 : this.getOrginalPayloadFromResp().entrySet()) {
			switch(m1.getKey().toString()) {
			case "Packages":
				this.setPackages((ArrayList)this.getOrginalPayloadFromResp().get("Packages"));
				 break;
			 case "CarrierName":
				 this.setCarrierName(m1.getValue().toString());
				 break;
			 case "Carrierscac":
				 this.setCarrierscac(m1.getValue().toString());
				 break;
			 case "MasterTrackingNumber":
				 this.setMasterTrackingNumber(m1.getValue().toString());
				 break;
			 case "EstimatedDelivery":
				 this.setEstimatedDelivery(m1.getValue().toString());
				 break;
			 case "Shipment_Id":
				 this.setShipment_Id(m1.getValue().toString());
				 break;
			 case "Totalfreight":
				 this.setTotalfreight(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "TotalDiscountFreight":
				 this.setTotalDiscountFreight(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "BaseCharges":
				 this.setBaseCharges(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "TotalSurcharges":
				 this.setTotalSurcharges(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "TotalTaxes":
				 this.setTotalTaxes(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "TotalDutiesAndTaxes":
				 this.setTotalDutiesAndTaxes(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "Surcharges":
				 this.setSurcharges((ArrayList)m1.getValue());
				 break;
			 case "CurrencyCode":
				 this.setCurrencyCode(m1.getValue().toString());
				 break;
			 case "Remarks":
				 this.setRemarks(m1.getValue().toString());
				 break;
			 case "MoneybackGuarantee":
				 this.setMoneybackGuarantee(Boolean.parseBoolean(m1.getValue().toString()));
				 break;
			 case "DestinationServiceArea":
				 this.setDestinationServiceArea(m1.getValue().toString());
				 break;
			 case "OriginServiceArea":
				 this.setOriginServiceArea(m1.getValue().toString());
				 break;
			 case "DestinationAirportId":
				 this.setDestinationAirportId(m1.getValue().toString());
				 break;
			 case "CustomProperties":
				 this.setCustomProperties((Map<String, Object>)this.getOrginalPayloadFromResp().get("CustomProperties"));
				 break;
			 case "Success":
				 this.setSuccess(Boolean.parseBoolean(m1.getValue().toString()));
				 break;
			 case "Errors":
				 this.setErrors((ArrayList)m1.getValue());
				 break;
		 }
		}
	}
	
	public void processPackages() {
		pacakgesList = new ArrayList<Packages>();
		for(int packageIndex = 0;packageIndex < this.Packages.size();packageIndex++) {
			 Map<String, Object> packgeResp = (Map<String, Object>) this.Packages.get(packageIndex);
			 Packages packageObj = new Packages();
			 for(Map.Entry packageEntry : packgeResp.entrySet()) {
				 switch(packageEntry.getKey().toString()) {
					 case "TrackingNumber":
						 packageObj.setTrackingNumber(packageEntry.getValue().toString());
						 break;
					 case "Freight":
						 packageObj.setFreight(Double.parseDouble(packageEntry.getValue().toString()));
						 break;
					 case "Discountfrieght":
						 packageObj.setDiscountfrieght(Double.parseDouble(packageEntry.getValue().toString()));
						 break;
					 case "Package_Id":
						 packageObj.setPackage_Id(packageEntry.getValue().toString());
						 break;
					 case "Label":
						 packageObj.setLabel((ArrayList)packageEntry.getValue());
						 break;
					 }
             }
			 pacakgesList.add(packageObj);
		}
	}
}
