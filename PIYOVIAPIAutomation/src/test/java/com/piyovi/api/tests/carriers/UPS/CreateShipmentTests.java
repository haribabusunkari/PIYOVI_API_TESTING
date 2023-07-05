package com.piyovi.api.tests.carriers.UPS;

import com.piyovi.common.BasePage;
import com.piyovi.constants.FedExConstants;
import com.piyovi.constants.UPSConstants;
import com.piyovi.parsers.Packages;
import com.piyovi.parsers.PiyoviResponseParser;
import com.piyovi.parsers.ShipmentRate;
import com.piyovi.util.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class CreateShipmentTests extends BasePage {

    DateTimeUtil dttmUtil = new DateTimeUtil();
    String shipDtTmFormat = "yyyy-MM-dd'T'HH:mm:ssZ";
    String shipDate = "";
    PiyoviResponseParser responseParser = new PiyoviResponseParser();

    @BeforeClass
    public void initialize() throws IOException {
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + UPSConstants.CARRIER_SHIPMENT_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }

    @Test
    public void testCreateShipmentOK() {
        logger = extent.createTest("UPS API - Test Basic Create Shipment", "Basic Test");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);

        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        logger.info("Response JSON " + response.asPrettyString());
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Success: " + this.responseParser.getSuccess());
        logger.info("Expected Success: True");
        assertEquals(this.responseParser.getSuccess(),true);
    }
    
    @Test
    public void testCarrierName() {
        logger = extent.createTest("UPS API - Test Carrier Name", "Verify Expected Carrier Name is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Carrier Name: " + this.responseParser.getCarrierName());
        logger.info("Expected Carrier Name: UPS");
        assertEquals(this.responseParser.getCarrierName(),"UPS");
    }
    
    @Test
    public void testCurrencyCode() {
        logger = extent.createTest("UPS API - Test Currency Code", "Verify Expected Currency Code is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        this.responseParser.processShipRate();
        logger.info("Actual Currency Code: " + this.responseParser.getShipmentRate().getCurrencycode());
        logger.info("Expected Currency Code: USD");
        assertEquals(this.responseParser.getShipmentRate().getCurrencycode(),"USD");
    }
    
    @Test
    public void testShipmentId() {
        logger = extent.createTest("UPS API - Test Shipment ID", "Verify Expected Shipment ID is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        String shipmentID = RandomGenerator.getAlphaNumericString(25);

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "Shipment_Id", shipmentID);

        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Shipment ID: " + this.responseParser.getShipment_Id());
        logger.info("Expected Shipment ID: Not Null");
        assertEquals(this.responseParser.getShipment_Id(),shipmentID);
    }
    
    @Test
    public void testMasterTrackingNumberNotNull() {
        logger = extent.createTest("UPS API - Test Master Tracking Number not null", "Verify Master Tracking Number not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Master Tracking Number: " + this.responseParser.getMasterTrackingNumber());
        logger.info("Expected Master Tracking Number: Not Null");
        assertTrue(this.responseParser.getMasterTrackingNumber()!=null);
    }
    
    @Test
    public void testTotalfreightNotNull() {
        logger = extent.createTest("UPS API - Test Total freight not null", "Verify Total freight not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Total freight: " + this.responseParser.getTotalfreight());
        logger.info("Expected Total freight: Not equal to 0.0");
        assertTrue((this.responseParser.getTotalfreight() != 0.0));
    }

    @Test
    public void testTotalDiscountFreightNotNull() {
        logger = extent.createTest("UPS API - Test Total Discount Freight not null", "Verify Total Discount Freight not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Total Discount Freight: " + this.responseParser.getTotalDiscountFreight());
        logger.info("Expected Total Discount Freight: Not Null");
        assertNotNull(this.responseParser.getTotalDiscountFreight());
    }

    @Test
    public void testBaseChargesNotNull() {
        logger = extent.createTest("UPS API - Test Base Charges not null", "Verify Base Charges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Base Charges: " + this.responseParser.getBaseCharges());
        logger.info("Expected Base Charges: Not Null");
        assertNotNull(this.responseParser.getBaseCharges());
    }

    @Test
    public void testTotalSurchargesNotNull() {
        logger = extent.createTest("UPS API - Test Total Surcharges not null", "Verify Total Surcharges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Total Surcharges: " + this.responseParser.getTotalSurcharges());
        logger.info("Expected Total Surcharges: Not 0");
        assertTrue(this.responseParser.getTotalSurcharges() != 0);
    }

    @Test
    public void testTransportationChargesNotNull() {
        logger = extent.createTest("UPS API - Test Transportation Charges is not null", "Verify Transportation Charges is not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Transportation Charges: " + this.responseParser.getTransportationCharges());
        logger.info("Expected Transportation Charges: Not 0");
        assertTrue(this.responseParser.getTransportationCharges() != 0);
    }

    @Test
    public void testServiceOptionsChargesNotNull() {
        logger = extent.createTest("UPS API - Test Service Options Charges is not null", "Verify Service Options Charges is not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual ServiceOptions Charges: " + this.responseParser.getServiceOptionsCharges());
        logger.info("Expected Transportation Charges: Not 0");
        assertTrue(this.responseParser.getServiceOptionsCharges() != 0);
    }
    
    @Test
    public void testMoneybackGuaranteeTrue() {
        logger = extent.createTest("UPS API - Test Money Back Guarantee as True", "Verify Money Back Guarantee as True");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(UPSConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        logger.info("Actual Moneyback Guarantee: " + this.responseParser.getMoneybackGuarantee());
        logger.info("Expected Moneyback Guarantee: False");
        assertEquals(this.responseParser.getShipmentRate().getMoneybackGuarantee(),false);
    }
    
    @Test
    public void testPackageTrackingNumberNotNull() {
        logger = extent.createTest("UPS API - Test Package Tracking Number is not null", "Verify Package Tracking Number is not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        this.responseParser.processPackages();
        List<Packages> packs = this.responseParser.getPackagesList();
        Packages pack = packs.get(0); //Hardcode for Single Package 
        logger.info("Expected Tracking Number : Not null");
        logger.info("Actual Tracking Number : " + pack.getTrackingNumber());
        assertNotNull(pack.getTrackingNumber());
    }
    
    @Test
    public void testShipmentRate() {
        logger = extent.createTest("UPS API - Test Shipment Rate", "Verify Shipment Rate");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        logger.info("Request Payload " + payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        this.responseParser.processShipRate();
        
        ShipmentRate shipmentRateObject = this.responseParser.getShipmentRate();
        
        logger.info("Carrier Name in Shipment Rate : " + shipmentRateObject.getCarrierName());
        logger.info("Carrier Name in payload : " + this.responseParser.getCarrierName());
        assertEquals(this.responseParser.getCarrierName(),shipmentRateObject.getCarrierName());
        
        logger.info("Actual Service Name in Shipment Rate: " + shipmentRateObject.getServiceName());
        logger.info("Expected Service Name: 03");
        assertEquals(shipmentRateObject.getServiceName(),"03");
        
        logger.info("Actual Service Description in Shipment Rate: " + shipmentRateObject.getServiceDescription());
        logger.info("Expected Service Description: UPS Ground");
        assertEquals(shipmentRateObject.getServiceDescription(),"UPS Ground");
        
        logger.info("Estimated Delivery in Shipment Rate : " + shipmentRateObject.getEstimatedDelivery());
        logger.info("Estimated Delivery in Payload : " + this.responseParser.getEstimatedDelivery());
        assertEquals(this.responseParser.getEstimatedDelivery(), shipmentRateObject.getEstimatedDelivery());
        
        logger.info("Freight in Shipment Rate : " + shipmentRateObject.getFreight());
        logger.info("Freight in Payload : " + this.responseParser.getTotalfreight());
        assertEquals(this.responseParser.getTotalfreight(),shipmentRateObject.getFreight());
		
		logger.info("Discount Freight in Shipment Rate : " + shipmentRateObject.getDiscountFrieght());
        logger.info("Discount Freight in Payload : " + this.responseParser.getTotalDiscountFreight());
        assertEquals(this.responseParser.getTotalDiscountFreight(),shipmentRateObject.getDiscountFrieght());
		
		logger.info("Base Charges in Shipment Rate : " + shipmentRateObject.getBaseCharge());
        logger.info("Base Charges in Payload : " + this.responseParser.getBaseCharges());
        assertEquals(this.responseParser.getBaseCharges(),shipmentRateObject.getBaseCharge());
		
		logger.info("Total Surcharges in Shipment Rate : " + shipmentRateObject.getTotalSurcharges());
        logger.info("Total Surcharges in Payload : " + this.responseParser.getTotalSurcharges());
        assertEquals(this.responseParser.getTotalSurcharges(),shipmentRateObject.getTotalSurcharges());
		
		logger.info("Transportation Charges in Shipment Rate :" + shipmentRateObject.getTransportationCharges());
        logger.info("Transportation Charges in Payload : " + + this.responseParser.getTransportationCharges());
        assertEquals(shipmentRateObject.getTransportationCharges(),this.responseParser.getTransportationCharges());
		
		logger.info("ServiceOptions Charges in Shipment Rate : " + shipmentRateObject.getServiceOptionsCharges());
        logger.info("Transportation Charges in Payload : " + this.responseParser.getServiceOptionsCharges());
        assertEquals(shipmentRateObject.getServiceOptionsCharges(),this.responseParser.getServiceOptionsCharges());
    }
}
