package com.piyovi.api.tests.carriers.UPS;

import com.piyovi.common.BasePage;
import com.piyovi.constants.FedExConstants;
import com.piyovi.constants.UPSConstants;
import com.piyovi.parsers.PiyoviResponseParser;
import com.piyovi.util.FileHelper;
import com.piyovi.util.JSONHelper;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RateRequestTests extends BasePage {
	PiyoviResponseParser responseParser = new PiyoviResponseParser();
	
    @BeforeClass
    public void initialize() throws IOException {
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + UPSConstants.RATE_REQUEST_URL;
    }

    @Test
    public void testRateRequestOK() {
        logger = extent.createTest("UPS API - Test Basic Rate Request", "Verify Basic Rate Request");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath + UPSConstants.RATE_REQUEST_PAYLOAD);
        var jsonHelper = new JSONHelper();

        var response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        logger.info("Response Payload " + response.asPrettyString());
        var responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        this.responseParser.parseResponse(responseMap);
        assertTrue(this.responseParser.getRatesResp().size() > 0);
        assertEquals(response.statusCode(), 200);
    }
}
