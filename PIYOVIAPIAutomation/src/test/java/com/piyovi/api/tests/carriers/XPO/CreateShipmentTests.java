package com.piyovi.api.tests.carriers.XPO;

import com.piyovi.common.BasePage;
import com.piyovi.constants.ABFConstants;
import com.piyovi.constants.DHLConstants;
import com.piyovi.constants.XPOConstants;
import com.piyovi.parsers.PiyoviResponseParser;
import com.piyovi.util.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
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
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + XPOConstants.CARRIER_SHIPMENT_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }
    
    @Test
    public void testCreateShipmentOK() {
        logger = extent.createTest("XPO API - Test Basic Create Shipment", "Basic Test");
        
        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        boolean actualValue = Boolean.parseBoolean(jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.Success"));
        logger.info("Actual Success: " + actualValue);
        logger.info("Expected Success: true");
        
        /* Compare actual vs expected */
        assertEquals(actualValue,true);
    }
    
    @Test
    public void testMasterTrackingNumberNotNull() {
        logger = extent.createTest("XPO API - Test Master Tracking Number not null", "Verify Master Tracking Number not nul");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.MasterTrackingNumber");
        logger.info("Actual Master Tracking Number: " + actualValue);
        logger.info("Expected Master Tracking Number: Not Null");
        
        /* Compare actual vs expected */
        assertTrue(actualValue != null);
    }
    
    @Test
    public void testCarrierName() {
        logger = extent.createTest("XPO API - Test Carrier Name", "Verify Expected Carrier Name is returned");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.CarrierName");
        logger.info("Actual Carrier Name in payload: " + actualValue);
        logger.info("Expected Carrier Name: XPOLogistics");
        
        /* Compare actual vs expected */
        assertEquals(actualValue,"XPOLogistics");
    }
    
    @Test
    public void testTotalfreightNotNull() {
        logger = extent.createTest("XPO API - Test Total freight not null", "Verify Total freight not nul");

        /* Get payload*/
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /*Update ship date and pickup date in the payload*/
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response*/
        logger.info("Response JSON " + response.asPrettyString());
        double actualValue = Double.parseDouble(jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.Totalfreight"));
        logger.info("Actual Total freight: " + actualValue);
        logger.info("Expected Total freight: Greater than or equal to Zero (>=0)");
        
        /* Compare actual vs expected */
        assert(actualValue >= 0);
    }
    
    @Test
    public void testTotalDiscountFreightNotNull() {
        logger = extent.createTest("XPO API - Test Total Discount Freight not null", "Verify Total Discount Freight not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        double actualValue = Double.parseDouble(jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.TotalDiscountFreight"));
        logger.info("Actual Total Discount Freight: " + actualValue);
        logger.info("Expected Total Discount Freight: Greater than or equal to Zero (>=0)");
        
        /* Compare actual vs expected */
        assert(actualValue >= 0);
    }
    
    @Test
    public void testBaseChargesNotNull() {
        logger = extent.createTest("XPO API - Test Base Charges not null", "Verify Base Charges not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        double actualValue = Double.parseDouble(jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.BaseCharges"));
        logger.info("Actual Base Charges: " + actualValue);
        logger.info("Expected Base Charges: Greater than or equal to Zero (>=0)");
        
        /* Compare actual vs expected */
        assert(actualValue >= 0);
    }
    
    @Test
    public void testTotalDiscountNotNull() {
        logger = extent.createTest("XPO API - Test Total Discount not null", "Verify Total Discount not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        double actualValue = Double.parseDouble(jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.TotalDiscounts"));
        logger.info("Actual Total Discount : " + actualValue);
        logger.info("Expected Total Discount : Greater than or equal to Zero (>=0)");
        
        /* Compare actual vs expected */
        assert(actualValue >= 0);
    }
    
    @Test
    public void testTotalSurchargesNotNull() {
        logger = extent.createTest("XPO API - Test Total Surcharges not null", "Verify Total Surcharges not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        double actualValue = Double.parseDouble(jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.TotalSurcharges"));
        logger.info("Actual Total Surchanges : " + actualValue);
        logger.info("Expected Total Surchanges : Greater than or equal to Zero (>=0)");
        
        /* Compare actual vs expected */
        assert(actualValue >= 0);
    }
    
    @Test
    public void testProNumberNotNull() {
        logger = extent.createTest("XPO API - Test Pro Number not null", "Verify Pro Number not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.ProNumber");
        logger.info("Actual Pro Number  : " + actualValue);
        logger.info("Expected Pro Number  : Not Null");
        
        /* Compare actual vs expected */
        assertNotNull(actualValue);
    }
    
    @Test
    public void testQuoteIDNotNull() {
        logger = extent.createTest("XPO API - Test Quote ID not null", "Verify Quote ID  not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.QuoteId");
        logger.info("Actual Quote ID  : " + actualValue);
        logger.info("Expected Quote ID  : Not Null");
        
        /* Compare actual vs expected */
        assertNotNull(actualValue);
    }
    
    @Test
    public void testShipmentId() {
        logger = extent.createTest("XPO API - Test Shipment ID", "Verify Expected Shipment ID is returned");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        String expectedShipmentID = RandomGenerator.getAlphaNumericString(25);
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "Shipment_Id", expectedShipmentID);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response*/
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.Shipment_Id");
        logger.info("Actual Shipment ID: " + actualValue);
        logger.info("Expected Shipment ID: " + expectedShipmentID);
        
        /* Compare actual vs expected */
        assertEquals(actualValue,expectedShipmentID);
    }
    
    @Test
    public void testPickupConfirmationNumberNotNull() {
        logger = extent.createTest("XPO API - Pickup Confirmation Number not null", "Verify Pickup Confirmation Number not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.PickupConfirmationNumber");
        logger.info("Actual Pickup Confirmation Number  : " + actualValue);
        logger.info("Expected Pickup Confirmation Number  : Not Null");
        
        /* Compare actual vs expected */
        assertNotNull(actualValue);
    }
    
    @Test
    public void testEstimatedDeliveryNotNull() {
        logger = extent.createTest("XPO API - Estimated Delivery not null", "Verify Estimated Delivery not null");

        /* Get payload */
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + XPOConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        /* POST */
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.EstimatedDelivery");
        logger.info("Actual Estimated Delivery : " + actualValue);
        logger.info("Expected PEstimated Delivery : Not Null");
        
        /* Compare actual vs expected */
        assertNotNull(actualValue);
    }
}