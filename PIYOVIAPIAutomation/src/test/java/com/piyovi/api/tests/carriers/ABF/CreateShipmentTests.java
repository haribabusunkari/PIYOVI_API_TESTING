package com.piyovi.api.tests.carriers.ABF;

import com.piyovi.common.BasePage;
import com.piyovi.constants.ABFConstants;
import com.piyovi.constants.DHLConstants;
import com.piyovi.constants.ESTESConstants;
import com.piyovi.constants.FedExConstants;
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
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + ABFConstants.CARRIER_SHIPMENT_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }
    
    @Test
    public void testCreateShipmentOK() {
        logger = extent.createTest("ABF API - Test Basic Create Shipment", "Basic Test");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);

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
        logger = extent.createTest("ABF API - Test Master Tracking Number not null", "Verify Master Tracking Number not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
       
        var jsonHelper = new JSONHelper();
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
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
        assertNotNull(actualValue);
    }
    
    @Test
    public void testCarrierName() {
        logger = extent.createTest("ABF API - Test Carrier Name", "Verify Expected Carrier Name is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.CarrierName");
        logger.info("Actual Carrier Name in payload: " + actualValue);
        logger.info("Expected Carrier Name: ABF");
        
        /* Compare actual vs expected */
        assertEquals(actualValue,"ABF");
    }
    
    @Test
    public void testCurrencyCode() {
        logger = extent.createTest("ABF API - Test Currency Code", "Verify Expected Currency Code is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        String actualValue = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.CurrencyCode");
        logger.info("Actual Currency Code in response: " + actualValue);
        logger.info("Expected Currency Code: USD");
        
        /* Compare actual vs expected */
        assertEquals(actualValue,"USD");
    }
    
    @Test
    public void testTotalfreightNotNull() {
        logger = extent.createTest("ABF API - Test Total freight not null", "Verify Total freight not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
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
        logger = extent.createTest("ABF API - Test Total Discount Freight not null", "Verify Total Discount Freight not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
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
        logger = extent.createTest("ABF API - Test Base Charges not null", "Verify Base Charges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
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
        logger = extent.createTest("ABF API - Test Total Discount not null", "Verify Total Discount not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
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
        logger = extent.createTest("ABF API - Test Total Surcharges not null", "Verify Total Surcharges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        
        /* Read required data from response */
        logger.info("Response JSON " + response.asPrettyString());
        double actualValue = Double.parseDouble(jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.TotalSurcharges"));
        logger.info("Actual Total Surcharges : " + actualValue);
        logger.info("Expected Total Surcharges : Greater than or equal to Zero (>=0)");
        
        /* Compare actual vs expected */
        assert(actualValue >= 0);
    }
    
    @Test
    public void testProNumberNotNull() {
        logger = extent.createTest("ABF API - Test Pro Number not null", "Verify Pro Number not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
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
        logger = extent.createTest("ABF API - Test Quote ID not null", "Verify Quote ID  not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + ABFConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "$.SpecialServices.PickupDetails.PickupDate", this.shipDate);
        logger.info("Request Payload " + payload);
        
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
}
