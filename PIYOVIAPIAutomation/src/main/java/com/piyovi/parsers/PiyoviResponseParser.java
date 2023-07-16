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
	private String carrierscac; //Only FedEx
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
	private double totalDiscounts; //ESTES
	private String quoteId; //ESTES
	private String proNumber; //ESTES
	private double transportationCharges; //UPS
	private double serviceOptionsCharges; //UPS
	private String currencyCode;
	private String remarks;
	private ArrayList shipmentDocumnets; //DHL
	private double billingWeight; //DHL
	private double dimensionalWeight; //DHL
	private boolean moneybackGuarantee;
	private String destinationServiceArea;
	private String originServiceArea;
	private String destinationAirportId;
	private Map<String, Object> customProperties;
	private Map<String, Object> shipmentRateResp;
	private ArrayList rateResp;
	private boolean success;
	private ArrayList errors;
	/* Process packages from response packages */
	private List<Packages> pacakgesList;
	private ShipmentRate shipmentRateObj;
	
	public double getTotalDiscounts() {
		return totalDiscounts;
	}
	public void setTotalDiscounts(double value) {
		this.totalDiscounts = value;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String value) {
		this.quoteId = value;
	}
	public String getProNumber() {
		return proNumber;
	}
	public void setProNumber(String value) {
		this.proNumber = value;
	}
	
	public ArrayList getshipmentDocumnets() {
		return shipmentDocumnets;
	}
	public void setshipmentDocumnets(ArrayList docs) {
		this.shipmentDocumnets =  docs;
	}
	public double getBillingWeight() {
		return billingWeight;
	}
	public void setBillingWeight(double value) {
		this.billingWeight = value;
	}
	public double getDimensionalWeight() {
		return dimensionalWeight;
	}
	public void setDimensionalWeight(double value) {
		this.dimensionalWeight = value;
	}
	
	public ShipmentRate getShipmentRate() {
		return shipmentRateObj;
	}
	public void setShipmentRate(ShipmentRate shipmentRateObj) {
		this.shipmentRateObj = shipmentRateObj;
	}
	public ArrayList getRatesResp() {
		return rateResp;
	}
	public void setRatesResp(ArrayList orginalRatesFromResp) {
		this.rateResp = orginalRatesFromResp;
	}
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
	public Map<String, Object> getShipmentRateResp() {
		return shipmentRateResp;
	}
	public void setShipmentRateResp(Map<String, Object> shipmentRate) {
		this.shipmentRateResp = shipmentRate;
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
	public void setTransportationCharges(double charges) {
		this.transportationCharges = charges;
	}
	public double getTransportationCharges() {
		return transportationCharges;
	}
	public void setServiceOptionsCharges(double charges) {
		this.serviceOptionsCharges = charges;
	}
	public double getServiceOptionsCharges() {
		return serviceOptionsCharges;
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
			 case "TransportationCharges":
				 this.setTransportationCharges(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "ServiceOptionsCharges":
				 this.setServiceOptionsCharges(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "CurrencyCode":
				 this.setCurrencyCode(m1.getValue().toString());
				 break;
			 case "Remarks":
				 this.setRemarks(m1.getValue().toString());
				 break;
			 case "ShipmentDocuments":
				 this.setshipmentDocumnets((ArrayList)m1.getValue());
				 break;
			 case "BillingWeight":
				 this.setBillingWeight(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "DimensionalWeight":
				 this.setBillingWeight(Double.parseDouble(m1.getValue().toString()));
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
			 case "TotalDiscounts":
				 this.setTotalDiscounts(Double.parseDouble(m1.getValue().toString()));
				 break;
			 case "QuoteId":
				 this.setQuoteId(m1.getValue().toString());
				 break;
			 case "ProNumber":
				 this.setProNumber(m1.getValue().toString());
				 break;
			 case "ShipmentRate":
				 this.setShipmentRateResp((Map<String, Object>)m1.getValue());
				 break;
			 case "Rates":
				 this.setRatesResp((ArrayList)m1.getValue());
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
					 case "LastMileTrackingNumber":
						 packageObj.setLastMileTrackingNumber(packageEntry.getValue().toString());
						 break;
					 }
             }
			 pacakgesList.add(packageObj);
		}
	}
	
	public void processShipRate() {
	 this.shipmentRateObj = new ShipmentRate();
	 for(Map.Entry shipmentRateEntry : this.shipmentRateResp.entrySet()) {
		 switch(shipmentRateEntry.getKey().toString()) {
			 case "CarrierName":
				 shipmentRateObj.setCarrierName(shipmentRateEntry.getValue().toString());
				 break;
			 case "ServiceName":
				 shipmentRateObj.setServiceName(shipmentRateEntry.getValue().toString());
				 break;
			 case "ServiceDescription":
				 shipmentRateObj.setServiceDescription(shipmentRateEntry.getValue().toString());
				 break;
			 case "Freight":
				 shipmentRateObj.setFreight(Double.parseDouble(shipmentRateEntry.getValue().toString()));
				 break;
			 case "DiscountFrieght":
				 shipmentRateObj.setDiscountFrieght(Double.parseDouble(shipmentRateEntry.getValue().toString()));
				 break;
			 case "BaseCharge":
				 shipmentRateObj.setBaseCharge(Double.parseDouble(shipmentRateEntry.getValue().toString()));
				 break;
			 case "TotalSurcharges":
				 shipmentRateObj.setTotalSurcharges(Double.parseDouble(shipmentRateEntry.getValue().toString()));
				 break;
			 case "Currencycode":
				 shipmentRateObj.setCurrencycode(shipmentRateEntry.getValue().toString());
				 break;
			 case "EstimatedDelivery":
				 shipmentRateObj.setEstimatedDelivery(shipmentRateEntry.getValue().toString());
				 break;
			 case "Surcharges":
				 shipmentRateObj.setSurcharges((ArrayList)shipmentRateEntry.getValue());
				 break;
			 case "CarrierType":
				 shipmentRateObj.setCarrierType(shipmentRateEntry.getValue().toString());
				 break;
			 case "BillingWeight":
				 shipmentRateObj.setBillingWeight(shipmentRateEntry.getValue().toString());
				 break;
			 case "ServiceOptionsCharges":
				 shipmentRateObj.setServiceOptionsCharges(Double.parseDouble(shipmentRateEntry.getValue().toString()));
				 break;
			 case "TransportationCharges":
				 shipmentRateObj.setTransportationCharges(Double.parseDouble(shipmentRateEntry.getValue().toString()));
				 break;
			 case "MoneybackGuarantee":
				 shipmentRateObj.setMoneybackGuarantee(Boolean.parseBoolean(shipmentRateEntry.getValue().toString()));
				 break;
		 }
	 }
	}
}
