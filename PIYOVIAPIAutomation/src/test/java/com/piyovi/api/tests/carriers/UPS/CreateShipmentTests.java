package com.piyovi.api.tests.carriers.UPS;

import com.piyovi.common.BasePage;
import com.piyovi.constants.FedExConstants;
import com.piyovi.constants.UPSConstants;
import com.piyovi.parsers.PiyoviResponseParser;
import com.piyovi.util.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
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
        assertEquals(this.responseParser.getCurrencyCode(),"USD");
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
        assertTrue(this.responseParser.getShipment_Id()!=null);
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
        assertTrue(this.responseParser.getTotalSurcharges() != 0);
    }

    @Test
    public void testTotalTaxesNotNull() {
        logger = extent.createTest("UPS API - Test Total Taxes not null", "Verify Total Taxes not null");

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
        assertTrue(this.responseParser.getTotalTaxes() != 0);
    }

    @Test
    public void testTotalDutiesAndTaxesNotNull() {
        logger = extent.createTest("UPS API - Test Total Duties and Taxes not null", "Verify Total Duties and Taxes not null");

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
        assertTrue(this.responseParser.getTotalDutiesAndTaxes() != 0);
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
        assertEquals(this.responseParser.getMoneybackGuarantee(),false);
    }

}
