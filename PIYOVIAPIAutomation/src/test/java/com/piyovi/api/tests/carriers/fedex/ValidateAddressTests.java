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

public class ValidateAddressTests extends BasePage {

    @BeforeClass
    public void initialize() throws IOException {
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + FedExConstants.VALIDATE_ADDRESS_URL;
    }

    @Test
    public void testRateRequestOK() {
        logger = extent.createTest("FedEx API - Test Basic Rate Request", "Verify Basic Rate Request");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(FedExConstants.VALIDATE_ADDRESS_PAYLOAD);
        var jsonHelper = new JSONHelper();

        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();

        verifyTextAndLog("Verify Rate Request", response.statusCode(),200);
    }
}
