package com.piyovi.api.tests.carriers.fedex;

import com.piyovi.common.BasePage;
import com.piyovi.constants.FedExConstants;
import com.piyovi.util.DateTimeUtil;
import com.piyovi.util.FileHelper;
import com.piyovi.util.JSONHelper;
import com.piyovi.util.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TrackingShipmentTests extends BasePage {

    PropertyReader propertyReader = new PropertyReader();
    DateTimeUtil dttmUtil = new DateTimeUtil();

    @BeforeClass
    public void initialize() throws IOException {
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + FedExConstants.TRACKING_SHIPMENT_URL;
    }

    @Test
    public void testTrackShipmentOKOK() {
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.TRACKING_SHIPMENT_PAYLOAD);
        var jsonHelper = new JSONHelper();

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(200);
    }
}
