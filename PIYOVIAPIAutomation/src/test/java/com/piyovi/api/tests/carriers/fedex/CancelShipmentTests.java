package com.piyovi.api.tests.carriers.fedex;

import com.piyovi.common.BasePage;
import com.piyovi.constants.FedExConstants;
import com.piyovi.util.FileHelper;
import com.piyovi.util.JSONHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CancelShipmentTests extends BasePage {

    @BeforeClass
    public void initialize() throws IOException {
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + FedExConstants.CARRIER_SHIPMENT_URL;
    }

    @Test
    public void testRateRequestOK() {
        logger = extent.createTest("FedEx API - Test Basic Cancel Shipment ", "Verify Basic Cancel Shipment");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.CANCEL_SHIPMENTT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();

        assertEquals(response.statusCode(), 200);
        // verifyTextAndLog("Verify Basic Cancel Shipment", response.statusCode(),200);
    }
}
