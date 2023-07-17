package com.piyovi.api.tests.carriers.RL;

import com.piyovi.common.BasePage;
import com.piyovi.constants.RLConstants;
import com.piyovi.parsers.Packages;
import com.piyovi.parsers.PiyoviResponseParser;
import com.piyovi.util.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
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
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + RLConstants.CARRIER_SHIPMENT_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }
    
    @Test
    public void testshipmentDocumentsNotNull() {
        logger = extent.createTest("RL API - Test Shipment Documents are not null", "Verify Shipment Documents are not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        ArrayList shipDocs = this.responseParser.getshipmentDocumnets();
       
        logger.info("Expected number of shipment documents : Not null");
        logger.info("Actual number of shipment documents  : " + shipDocs.size());
        assertTrue(shipDocs.size() > 0);
    }
    
    @Test
    public void testPackageTrackingNumberNotNull() {
        logger = extent.createTest("RL API - Test Package Tracking Number is not null", "Verify Package Tracking Number is not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
    public void testTotalfreightNotNull() {
        logger = extent.createTest("RL API - Test Total freight not null", "Verify Total freight not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("RL API - Test Total Discount Freight not null", "Verify Total Discount Freight not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
    public void testTotalDiscountNotNull() {
        logger = extent.createTest("RL API - Test Total Discount not null", "Verify Total Discount not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Total Discount : " + this.responseParser.getTotalDiscounts());
        logger.info("Expected Total Discount : Not Null");
        assertNotNull(this.responseParser.getTotalDiscounts());
    }
    
    @Test
    public void testQuoteIDNotNull() {
        logger = extent.createTest("RL API - Test Quote ID not null", "Verify Quote ID  not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Quote ID  : " + this.responseParser.getQuoteId());
        logger.info("Expected Quote ID  : Not Null");
        assertNotNull(this.responseParser.getQuoteId());
    }
    
    @Test
    public void testProNumberNotNull() {
        logger = extent.createTest("RL API - Test Pro Number not null", "Verify Pro Number not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Pro Number  : " + this.responseParser.getProNumber());
        logger.info("Expected Pro Number  : Not Null");
        assertNotNull(this.responseParser.getProNumber());
    }
    
    @Test
    public void testMasterTrackingNumberNotNull() {
        logger = extent.createTest("RL API - Test Master Tracking Number not null", "Verify Master Tracking Number not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
    public void testShipmentId() {
        logger = extent.createTest("RL API - Test Shipment ID", "Verify Expected Shipment ID is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
    public void testCarrierName() {
        logger = extent.createTest("RL API - Test Carrier Name", "Verify Expected Carrier Name is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Expected Carrier Name: RL");
        assertEquals(this.responseParser.getCarrierName(),"RL");
    }
    
    @Test
    public void testBaseChargesNotNull() {
        logger = extent.createTest("RL API - Test Base Charges not null", "Verify Base Charges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Base Charges : " + this.responseParser.getBaseCharges());
        logger.info("Expected  Base Charges : Not Null");
        assertNotNull(this.responseParser.getBaseCharges());
    }

    @Test
    public void testTotalSurchargesNotNull() {
        logger = extent.createTest("RL API - Test Total Surcharges not null", "Verify Total Surcharges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual SurCharges : " + this.responseParser.getTotalSurcharges());
        logger.info("Expected  SurCharges : Not Null");
        assertTrue(this.responseParser.getTotalSurcharges() != 0);
    }
    
    @Test
    public void testCreateShipmentOK() {
        logger = extent.createTest("RL API - Test Basic Create Shipment", "Basic Test");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + RLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
}
