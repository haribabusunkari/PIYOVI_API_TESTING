package com.piyovi.api.tests.carriers.fedex;

import com.piyovi.common.BasePage;
import com.piyovi.constants.FedExConstants;
import com.piyovi.util.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
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
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + FedExConstants.CARRIER_SHIPMENT_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }

    @Test
    public void testCreateShipmentIntAddrs() throws Exception{

        logger = extent.createTest("FedEx API - Test Create Shipment for International Address", "Verify International Consignee");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
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

        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();

        assertEquals(response.statusCode(),200);
        //verifyTextAndLog("Verify International Consignee", response.statusCode(),200);
    }

    @Test
    public void testCreateShipmentOK() {
        logger = extent.createTest("FedEx API - Test Basic Create Shipment", "Basic Test");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);

        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getSuccess(),true);
        // verifyTextAndLog("Basic Test",((Map<String, Object>) responseMap.get("payload")).get("Success"),true);
    }

    @Test
    public void testServiceName() {
        logger = extent.createTest("FedEx API - Test Service Name", "Verify Expected Service Name is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(((Map<String, Object>) responseMap.get("payload")).get("ServiceName"),"FEDEX_2_DAY");
        //verifyTextAndLog("Service Name",((Map<String, Object>) responseMap.get("payload")).get("ServiceName"),"FEDEX_2_DAY");
    }

    @Test
    public void testCarrierName() {
        logger = extent.createTest("FedEx API - Test Carrier Name", "Verify Expected Carrier Name is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getCarrierName(),"FEDEX");
        //verifyTextAndLog("Carrier Name",((Map<String, Object>) responseMap.get("payload")).get("CarrierName"),"FEDEX");
    }

    @Test
    public void testCarrierscac() {
        logger = extent.createTest("FedEx API - Test Carrierscac", "Verify Expected Carrierscac is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getCarrierscac(),"FDXE");
        // verifyTextAndLog("Carrierscac",((Map<String, Object>) responseMap.get("payload")).get("Carrierscac"),"FDXE");
    }

    @Test
    public void testCurrencyCode() {
        logger = extent.createTest("FedEx API - Test Currency Code", "Verify Expected Currency Code is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getCurrencyCode(),"USD");
        //verifyTextAndLog("CurrencyCode",((Map<String, Object>) responseMap.get("payload")).get("CurrencyCode"),"USD");
    }

    @Test
    public void testShipmentId() {
        logger = extent.createTest("FedEx API - Test Shipment ID", "Verify Expected Shipment ID is returned");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        String shipmentID = RandomGenerator.getAlphaNumericString(25);

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        payload = jsonHelper.updateJsonValue(payload, "Shipment_Id", shipmentID);

        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getShipment_Id(),shipmentID);
        // verifyTextAndLog("Shipment_Id",((Map<String, Object>) responseMap.get("payload")).get("Shipment_Id"),shipmentID);
    }

    @Test
    public void testMasterTrackingNumberNotNull() {
        logger = extent.createTest("FedEx API - Test Master Tracking Number not null", "Verify Master Tracking Number not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertTrue(this.responseParser.getShipment_Id()!=null);
        // verifyBooleanStatus("Master Tracking Number",((Map<String, Object>) responseMap.get("payload")).get("Shipment_Id")!=null,true);
    }

    @Test
    public void testTotalfreightNotNull() {
        logger = extent.createTest("FedEx API - Test Total freight not null", "Verify Total freight not nul");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        assertTrue((this.responseParser.getTotalfreight() != 0.0));
        // verifyBooleanStatus("Totalfreight",((Map<String, Object>) responseMap.get("payload")).get("Totalfreight")!=null,true);
    }

    @Test
    public void testTotalDiscountFreightNotNull() {
        logger = extent.createTest("FedEx API - Test Total Discount Freight not null", "Verify Total Discount Freight not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        assertNotNull(this.responseParser.getTotalDiscountFreight());
        // verifyBooleanStatus("TotalDiscountFreight",((Map<String, Object>) responseMap.get("payload")).get("TotalDiscountFreight")!=null,true);
    }

    @Test
    public void testBaseChargesNotNull() {
        logger = extent.createTest("FedEx API - Test Base Charges not null", "Verify Base Charges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertNotNull(this.responseParser.getBaseCharges());
        // verifyBooleanStatus("BaseCharges",((Map<String, Object>) responseMap.get("payload")).get("BaseCharges")!=null,true);
    }

    @Test
    public void testTotalSurchargesNotNull() {
        logger = extent.createTest("FedEx API - Test Total Surcharges not null", "Verify Total Surcharges not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        assertTrue(this.responseParser.getTotalSurcharges() != 0);
        // verifyBooleanStatus("TotalSurcharges",((Map<String, Object>) responseMap.get("payload")).get("TotalSurcharges")!=null,true);
    }

    @Test
    public void testTotalTaxesNotNull() {
        logger = extent.createTest("FedEx API - Test Total Taxes not null", "Verify Total Taxes not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        assertTrue(this.responseParser.getTotalTaxes() != 0);
        // verifyBooleanStatus("TotalTaxes",((Map<String, Object>) responseMap.get("payload")).get("TotalTaxes")!=null,true);
    }

    @Test
    public void testTotalDutiesAndTaxesNotNull() {
        logger = extent.createTest("FedEx API - Test Total Duties and Taxes not null", "Verify Total Duties and Taxes not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(responseMap);
        this.responseParser.parseResponse(responseMap);
        assertTrue(this.responseParser.getTotalDutiesAndTaxes() != 0);
        // verifyBooleanStatus("TotalDutiesAndTaxes",((Map<String, Object>) responseMap.get("payload")).get("TotalDutiesAndTaxes")!=null,true);
    }

    @Test
    public void testMoneybackGuaranteeTrue() {
        logger = extent.createTest("FedEx API - Test Money Back Guarantee as True", "Verify Money Back Guarantee as True");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getMoneybackGuarantee(),true);
        //verifyTextAndLog("MoneybackGuarantee",((Map<String, Object>) responseMap.get("payload")).get("MoneybackGuarantee"),"true");
    }

    @Test
    public void testDestinationServiceArea() {
        logger = extent.createTest("FedEx API - Test Destination Service Area as Expected", "Verify Destination Service Area as Expected");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getDestinationServiceArea(),"A2");
        // verifyTextAndLog("DestinationServiceArea",((Map<String, Object>) responseMap.get("payload")).get("DestinationServiceArea"),"A2");
    }

    @Test
    public void testOriginServiceArea() {
        logger = extent.createTest("FedEx API - Test Origin Service Area as Expected", "Verify Origin Service Area as Expected");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getOriginServiceArea(),"PM");
        // verifyTextAndLog("OriginServiceArea",((Map<String, Object>) responseMap.get("payload")).get("OriginServiceArea"), "PM");
    }

    @Test
    public void testDestinationAirportId() {
        logger = extent.createTest("FedEx API - Test Destination Airport ID as Expected", "Verify Destination Airport ID as Expected");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getDestinationAirportId(),"ATL");
        // verifyTextAndLog("DestinationAirportId",((Map<String, Object>) responseMap.get("payload")).get("DestinationAirportId"), "ALT");
    }

    @Test
    public void testPastShipDate() {
        logger = extent.createTest("FedEx API - Test Error Message when Ship Date is Past", "Verify Error Message when Ship Date is Past");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
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
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertEquals(this.responseParser.getRemarks(),"2469:shipTimestamp is invalid, 7000:Unable to obtain courtesy rates.");
        // verifyTextAndLog("Remarks",((Map<String, Object>) responseMap.get("payload")).get("Remarks"), "2469:shipTimestamp is invalid");
    }

    @Test
    public void testPackageTrackingNumberNotNull() {
        logger = extent.createTest("FedEx API - Test Package Tracking Number is not null", "Verify Package Tracking Number is not null");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "ShipDate", this.shipDate);
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        ArrayList<Packages> packs = this.responseParser.getPackages();
        assertNotNull(packs.get(0).getTrackingNumber());

    }

    @Test
    public void testDocumentType() {
        logger = extent.createTest("FedEx API - Test Document Type is carrier_label_46", "Verify Document Type is carrier_label_46");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        ArrayList<Packages> packs = this.responseParser.getPackages();
        assertEquals(packs.get(0).getLabel(),"carrier_label_46");
    }

    @Test
    public void testDocumentTypeDockTabFalse() {
        logger = extent.createTest("FedEx API - Test Document Type is carrier_label_46 when DocTab is false", "Verify Document Type is carrier_label_46 when DocTab is false");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "DocumentOptions.Documents[0].DocumentType", "carrier_label_48");
        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        ArrayList<Packages> packs = this.responseParser.getPackages();
        assertEquals(packs.get(0).getLabel(),"carrier_label_46");
    }

    @Test
    public void testDocumentTypeDockTabTrue() {
        logger = extent.createTest("FedEx API - Test Document Type is carrier_label_48 when DocTab is true", "Verify Document Type is carrier_label_48 when DocTab is true");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CARRIER_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "DocumentOptions.Documents[0].DocumentType", "carrier_label_48");
        payload = jsonHelper.updateJsonValue(payload, "SpecialServices.DocTab", true);

        System.out.println(payload);
        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        ArrayList<Packages> packs = this.responseParser.getPackages();
        assertEquals(packs.get(0).getLabel(),"carrier_label_48");

    }
}
