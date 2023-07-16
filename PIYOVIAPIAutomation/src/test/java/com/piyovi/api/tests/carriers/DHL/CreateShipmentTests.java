package com.piyovi.api.tests.carriers.DHL;

import com.piyovi.common.BasePage;
import com.piyovi.constants.DHLConstants;
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
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + DHLConstants.CARRIER_SHIPMENT_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }
    
    @Test
    public void testDestinationServiceArea() {
        logger = extent.createTest("DHL API - Test Destination Service Area as Expected", "Verify Destination Service Area as Expected");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Expected Destination Service Area : YOW");
        logger.info("Actual Destination Service Area  : " + this.responseParser.getDestinationServiceArea());
        assertEquals(this.responseParser.getDestinationServiceArea(),"YOW");
    }

    @Test
    public void testOriginServiceArea() {
        logger = extent.createTest("DHL API - Test Origin Service Area as Expected", "Verify Origin Service Area as Expected");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Expected Origin Service Area : FYV");
        logger.info("Actual Origin Service Area  : " + this.responseParser.getOriginServiceArea());
        assertEquals(this.responseParser.getOriginServiceArea(),"FYV");
    }

    @Test
    public void testshipmentDocumentsNotNull() {
        logger = extent.createTest("DHL API - Test Shipment Documents are not null", "Verify Shipment Documents are not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual umber of shipment documents  : " + shipDocs.size());
        assertTrue(shipDocs.size() > 0);
    }
    
    @Test
    public void testPackageTrackingNumberNotNull() {
        logger = extent.createTest("DHL API - Test Package Tracking Number is not null", "Verify Package Tracking Number is not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
    public void testMoneybackGuaranteeTrue() {
        logger = extent.createTest("DHL API - Test Money Back Guarantee as True", "Verify Money Back Guarantee as True");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Moneyback Guarantee in ShipmentRate: " + this.responseParser.getShipmentRate().getMoneybackGuarantee());
        logger.info("Expected Moneyback Guarantee: False");
        assertEquals(this.responseParser.getShipmentRate().getMoneybackGuarantee(),false);
    }
    
    @Test
    public void testTotalfreightNotNull() {
        logger = extent.createTest("DHL API - Test Total freight not null", "Verify Total freight not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        this.responseParser.processShipRate();
        logger.info("Actual Total freight in shop rate: " + this.responseParser.getShipmentRate().getFreight());
        logger.info("Actual Total freight: " + this.responseParser.getTotalfreight());
        logger.info("Expected Total freight: Not equal to 0.0");
        assertTrue((this.responseParser.getTotalfreight() != 0.0));
        assertTrue((this.responseParser.getShipmentRate().getFreight() != 0.0));
    }

    @Test
    public void testTotalDiscountFreightNotNull() {
        logger = extent.createTest("DHL API - Test Total Discount Freight not null", "Verify Total Discount Freight not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Total Discount freight in shop rate: " + this.responseParser.getShipmentRate().getDiscountFrieght());
        logger.info("Actual Total Discount Freight: " + this.responseParser.getTotalDiscountFreight());
        logger.info("Expected Total Discount Freight: Not Null");
        assertNotNull(this.responseParser.getTotalDiscountFreight());
        assertNotNull(this.responseParser.getShipmentRate().getDiscountFrieght());
    }
    
    @Test
    public void testMasterTrackingNumberNotNull() {
        logger = extent.createTest("DHL API - Test Master Tracking Number not null", "Verify Master Tracking Number not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("DHL API - Test Shipment ID", "Verify Expected Shipment ID is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("DHL API - Test Carrier Name", "Verify Expected Carrier Name is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Carrier Name in Shipment Rate: " + this.responseParser.getShipmentRate().getCarrierName());
        logger.info("Actual Carrier Name in payload: " + this.responseParser.getCarrierName());
        logger.info("Expected Carrier Name: DHL");
        assertEquals(this.responseParser.getShipmentRate().getCarrierName(),"DHL");
        assertEquals(this.responseParser.getCarrierName(),"DHL");
    }
    
    @Test
    public void testCurrencyCode() {
        logger = extent.createTest("DHL API - Test Currency Code", "Verify Expected Currency Code is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger.info("Actual Currency Code in Shipment Rate: " + this.responseParser.getShipmentRate().getCurrencycode());
        logger.info("Actual Currency Code in response: " + this.responseParser.getCurrencyCode());
        logger.info("Expected Currency Code: USD");
        assertEquals(this.responseParser.getShipmentRate().getCurrencycode(),"USD");
        assertEquals(this.responseParser.getCurrencyCode(),"USD");
    }
    
    @Test
    public void testCreateShipmentOK() {
        logger = extent.createTest("DHL API - Test Basic Create Shipment", "Basic Test");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + DHLConstants.CARRIER_SHIPMENT_PAYLOAD);
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
