package com.piyovi.api.tests.carriers.ABF;

import com.piyovi.common.BasePage;
import com.piyovi.constants.ABFConstants;
import com.piyovi.parsers.PiyoviResponseParser;
import com.piyovi.util.DateTimeUtil;
import com.piyovi.util.FileHelper;
import com.piyovi.util.JSONHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class RateRequestTests extends BasePage {
	DateTimeUtil dttmUtil = new DateTimeUtil();
	String shipDtTmFormat = "yyyy-MM-dd'T'HH:mm:ssZ";
	String shipDate = "";
	PiyoviResponseParser responseParser = new PiyoviResponseParser();
	
    @BeforeClass
    public void initialize() throws IOException {
        RestAssured.baseURI = propertyReader.getApplicationProperty("baseURI") + ABFConstants.RATE_REQUEST_URL;
        this.shipDate = dttmUtil.getDateTimeInFormat(this.shipDtTmFormat);
    }

    @Test
    public void testRateRequestOK() {
        logger = extent.createTest("ABF API - Test Basic Rate Request", "Verify Basic Rate Request");

        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile(carriersPayLoadPath+ABFConstants.RATE_REQUEST_PAYLOAD);
       
        /* Update ship date and pickup date in the payload */
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
        int actualValue = Integer.parseInt(jsonHelper.getJsonValue(response.asPrettyString(), "$.status"));
        logger.info("Actual Success: " + actualValue);
        logger.info("Expected Success: 200");
        
        /* Compare actual vs expected */
        assertEquals(actualValue,200);
        
        String data = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.Rates");
        JSONArray array = new JSONArray(data);  
        for(int index=0; index < array.length(); index++) {  
	        //JSONObject object = array.getJSONObject(i);  
	        //System.out.println(object.getString("CarrierName"));  
	        //System.out.println(object.getString("ServiceName"));  
        	String carrierName = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.Rates["+index+"].CarrierName");
        	String serviceName = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.Rates["+index+"].ServiceName");
        	String description = jsonHelper.getJsonValue(response.asPrettyString(), "$.payload.Rates["+index+"].ServiceDescription");
        	
        	/* Compare actual vs expected */
        	logger.info("Actual Carrier Name: " + carrierName);
            logger.info("Expected Carrier Name: ABF");
            assertEquals(carrierName,"ABF");
            
            if(serviceName.toUpperCase().contains("STD")) {
            	logger.info("Actual Service Name: " + serviceName);
                logger.info("Expected Service Name: ABFSTD");
            	assertEquals(serviceName,"ABFSTD");
            	
            	logger.info("Actual Service Description: " + description);
                logger.info("Expected Service Description: Standard");
            	assertEquals(description,"Standard");
            }else if (serviceName.toUpperCase().contains("GSS5")) {
            	logger.info("Actual Service Name: " + serviceName);
                logger.info("Expected Service Name: ABFGSS5");
            	assertEquals(serviceName,"ABFGSS5");
            	
            	logger.info("Actual Service Description: " + description);
                logger.info("Expected Service Description: Guaranteed by 5 PM");
            	assertEquals(description,"Guaranteed by 5 PM");
            }else if (serviceName.toUpperCase().contains("GSS12")) {
            	logger.info("Actual Service Name: " + serviceName);
                logger.info("Expected Service Name: ABFGSS12");
            	assertEquals(serviceName,"ABFGSS12");
            	
            	logger.info("Actual Service Description: " + description);
                logger.info("Expected Service Description: Guaranteed by 12 PM");
            	assertEquals(description,"Guaranteed by 12 PM");
            }
        }  
    }
}
