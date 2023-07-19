package com.piyovi.api.tests.carriers.fedex_freight;

import com.piyovi.common.BasePage;
import com.piyovi.constants.FedExFrieghtConstants;
import com.piyovi.parsers.Packages;
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
import java.util.HashMap;
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
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + FedExFrieghtConstants.CARRIER_SHIPMENT_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }

    @Test
    public void testCreateShipmentIntAddrsWithWrongArguments() throws Exception{
        logger = extent.createTest("FedEx Freight API - Test Create Shipment for International Address With WrongArguments", "Verify International Consignee Negative Test");
       
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        /* Update international address*/
        JSONObject obj = new JSONObject();
        obj.put("Id", null);
        obj.put("Name", "Adithya");
        obj.put("BusinessName", "Piyovi");
        obj.put("AddressLine1", "445 Hurricane Trail");
        obj.put("AddressLine2", "");
        obj.put("AddressLine3", null);
        obj.put("City", "Hyderabad");
        obj.put("State", "TS");
        obj.put("Zipcode", "500089");
        obj.put("CountryCode", "IN");
        obj.put("Phone", "1122334455");
        obj.put("Email", null);
        obj.put("VatorTax", null);
        obj.put("VatorTaxType", null);
        obj.put("VatorTaxTypeCountryCode", null);
        obj.put("AddressType", null);

        payload = jsonHelper.updateJsonValue(payload, "Consignee", obj);
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();

        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        logger.info("Status Code : " + responseMap.get("status").toString());
        logger.info("Errors in response JSON : " + responseMap.get("errors"));
        assertEquals(responseMap.get("status").toString(),"500");
        assertNotEquals(responseMap.get("errors").toString(),"Object reference not set to an instance of an object.");
    }

    @Test
    public void testCreateShipmentIntAddrs() throws Exception{
        logger = extent.createTest("FedEx Freight API - Test Create Shipment for International Address", "Verify International Consignee");
       
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_INTERNATIONAL_PAYLOAD);
        var jsonHelper = new JSONHelper();
        
        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();

        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        logger.info("Status Code : " + responseMap.get("status").toString());
        assertEquals(responseMap.get("status").toString(),"200");
    }
    
    @Test
    public void testCreateShipmentOK() {
        logger = extent.createTest("FedEx Freight API - Test Basic Create Shipment", "Basic Test");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("FedEx Freight API - Test Carrier Name", "Verify Expected Carrier Name is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        assertEquals(this.responseParser.getCarrierName(),"FEDEX FREIGHT");
    }

    @Test
    public void testCarrierscac() {
        logger = extent.createTest("FedEx Freight API - Test Carrierscac", "Verify Expected Carrierscac is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        assertEquals(this.responseParser.getCarrierscac(),"FXFR");
    }

    @Test
    public void testCurrencyCode() {
        logger = extent.createTest("FedEx Freight API - Test Currency Code", "Verify Expected Currency Code is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("FedEx Freight API - Test Shipment ID", "Verify Expected Shipment ID is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("FedEx Freight API - Test Master Tracking Number not null", "Verify Master Tracking Number not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        assertTrue(this.responseParser.getMasterTrackingNumber()!=null);
    }

    @Test
    public void testTotalfreightNotNull() {
        logger = extent.createTest("FedEx Freight API - Test Total freight not null", "Verify Total freight not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("FedEx Freight API - Test Total Discount Freight not null", "Verify Total Discount Freight not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("FedEx Freight API - Test Base Charges not null", "Verify Base Charges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("FedEx Freight API - Test Total Surcharges not null", "Verify Total Surcharges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        logger = extent.createTest("FedEx Freight API - Test Total Taxes not null", "Verify Total Taxes not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        assertTrue(this.responseParser.getTotalTaxes() >= 0);
    }

    @Test
    public void testTotalDutiesAndTaxesNotNull() {
        logger = extent.createTest("FedEx Freight API - Test Total Duties and Taxes not null", "Verify Total Duties and Taxes not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        assertTrue(this.responseParser.getTotalDutiesAndTaxes() >= 0);
    }

    @Test
    public void testMoneybackGuaranteeTrue() {
        logger = extent.createTest("FedEx Freight API - Test Money Back Guarantee as True", "Verify Money Back Guarantee as True");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        assertEquals(this.responseParser.getMoneybackGuarantee(),true);
    }
    
    @Test
    public void testPastShipDate() {
        logger = extent.createTest("FedEx Freight API - Test Error Message when Ship Date is Past", "Verify Error Message when Ship Date is Past");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(200);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response JSON " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getRemarks(),"2469:shipTimestamp is invalid, 7000:Unable to obtain courtesy rates.");
    }

    @Test
    public void testPackageTrackingNumberNotNull() {
        logger = extent.createTest("FedEx Freight API - Test Package Tracking Number is not null", "Verify Package Tracking Number is not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
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
    public void testDocumentType() {
        logger = extent.createTest("FedEx Freight API - Test Document Type is carrier_label_46", "Verify Document Type is carrier_label_46");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

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
        ArrayList labels = pack.getLabel();
        String acutalLabel = "";
        for (int j = 0; j < labels.size(); j++) {
        	HashMap<String,String> labelItem = (HashMap<String, String>) labels.get(j);
        	if(labelItem.containsKey("DocumentType")) {
        		acutalLabel = labelItem.get("DocumentType");
        	}
        }
        logger.info("Expected Label : " + "carrier_label_46");
        logger.info("Actual Label : " + acutalLabel);
        assertEquals(acutalLabel,"carrier_label_46");
    }

    @Test
    public void testDocumentTypeDockTabFalse() {
        logger = extent.createTest("FedEx Freight API - Test Document Type is carrier_label_46 when DocTab is false", "Verify Document Type is carrier_label_46 when DocTab is false");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "DocumentOptions.Documents[0].DocumentType", "carrier_label_46");
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
        ArrayList labels = pack.getLabel();
        String acutalLabel = "";
        for (int j = 0; j < labels.size(); j++) {
        	HashMap<String,String> labelItem = (HashMap<String, String>) labels.get(j);
        	if(labelItem.containsKey("DocumentType")) {
        		acutalLabel = labelItem.get("DocumentType");
        	}
        }
        
        logger.info("DocTab: False");
        logger.info("Expected Label : " + "carrier_label_46");
        logger.info("Actual Label : " + acutalLabel);
        assertEquals(acutalLabel,"carrier_label_46");
    }

    @Test
    public void testDocumentTypeDockTabTrue() {
        logger = extent.createTest("FedEx Freight API - Test Document Type is carrier_label_48 when DocTab is true", "Verify Document Type is carrier_label_48 when DocTab is true");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + FedExFrieghtConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "DocumentOptions.Documents[0].DocumentType", "carrier_label_48");
        payload = jsonHelper.updateJsonValue(payload, "SpecialServices.DocTab", true);

        System.out.println(payload);
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
        ArrayList labels = pack.getLabel();
        String acutalLabel = "";
        for (int j = 0; j < labels.size(); j++) {
        	HashMap<String,String> labelItem = (HashMap<String, String>) labels.get(j);
        	if(labelItem.containsKey("DocumentType")) {
        		acutalLabel = labelItem.get("DocumentType");
        	}
        }
        
        logger.info("DocTab: True");
        logger.info("Expected Label : " + "carrier_label_48");
        logger.info("Actual Label : " + acutalLabel);
        assertEquals(acutalLabel,"carrier_label_48");
    }
}
