package com.piyovi.api.tests.headless;

import com.piyovi.common.BasePage;

import com.piyovi.constants.HeadlessConstants;
import com.piyovi.util.DateTimeUtil;
import com.piyovi.util.FileHelper;
import com.piyovi.util.JSONHelper;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateShipment extends BasePage {

    String shipDtTmFormat = "yyyy-MM-dd'T'HH:mm:ssZ";
    String shipDate = "";
    String referenceNumber = "";
    DateTimeUtil dttmUtil = new DateTimeUtil();
    @BeforeClass
    public void setAuthToken() throws IOException {
        generateAuthToken();
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURL_Headless") + HeadlessConstants.CREATE_SHIPMENT_URL;
    }
    @Test
    public void testCreateShipmentFedex() {
        logger = extent.createTest("Headless API - Test Create Shipment for Fedex", "Verify Shipment Creation for Fedex");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(headlessPayLoadPath + HeadlessConstants.CREATE_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "shipDate", this.shipDate);
        referenceNumber = "AUTREF" + RandomStringUtils.randomAlphanumeric(10) ;
        payload = jsonHelper.updateJsonValue(payload, "referenceNumber", this.referenceNumber);

        var response = given()
                .contentType(ContentType.JSON).header("authorization", "bearer " + authToken)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        logger.info("Status Code : " + responseMap.get("status").toString());
        assertEquals(responseMap.get("status").toString(),"200");
    }

    @Test
    public void testCreateShipmentUPSG() {
        logger = extent.createTest("Headless API - Test Create Shipment for Fedex", "Verify Shipment Creation for Fedex");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(headlessPayLoadPath + HeadlessConstants.CREATE_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "shipDate", this.shipDate);
        referenceNumber = "AUTREF" + RandomStringUtils.randomAlphanumeric(10) ;
        payload = jsonHelper.updateJsonValue(payload, "referenceNumber", this.referenceNumber);
        payload = jsonHelper.updateJsonValue(payload, "shipmentInfo.ShipMethod", "UPSG");

        var response = given()
                .contentType(ContentType.JSON).header("authorization", "bearer " + authToken)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        logger.info("Status Code : " + responseMap.get("status").toString());
        assertEquals(responseMap.get("status").toString(),"200");
    }

    @Test
    public void testCreateShipmentDHLBREAKBULK() {
        logger = extent.createTest("Headless API - Test Create Shipment for Fedex", "Verify Shipment Creation for Fedex");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(headlessPayLoadPath + HeadlessConstants.CREATE_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        payload = jsonHelper.updateJsonValue(payload, "shipDate", this.shipDate);
        referenceNumber = "AUTREF" + RandomStringUtils.randomAlphanumeric(10) ;
        payload = jsonHelper.updateJsonValue(payload, "referenceNumber", this.referenceNumber);
        payload = jsonHelper.updateJsonValue(payload, "shipmentInfo.ShipMethod", "B");

        var response = given()
                .contentType(ContentType.JSON).header("authorization", "bearer " + authToken)
                .body(payload)
                .when()
                .post();
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        logger.info("Status Code : " + responseMap.get("status").toString());
        assertEquals(responseMap.get("status").toString(),"200");
    }
}
