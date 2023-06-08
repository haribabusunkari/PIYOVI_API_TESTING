package com.piyovi.api.tests.carriers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.piyovi.common.BasePage;
import com.piyovi.util.*;

public class FedExAPITests extends BasePage{

    @BeforeMethod
    public void initialize() {
        RestAssured.baseURI = "https://carrierdev.piyovi.io/fedex/api/v1/fedex/create_shipment";
    }

    @Test
    public void testCreateShipment() throws Exception{

    	logger=extent.createTest("PIYOVI API Test - Create Shippment Fedex", "International Consignee");
		
        var fileHelper = new FileHelper();
        var payload = fileHelper.getFile("FedEx/createshipment.json");
        var jsonHelper = new JSONHelper();
        
        /* Update international address*/
        JSONObject obj=new JSONObject(); 
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
        System.out.println(response.statusCode());
        if(response.statusCode() == 200) {
        	logger.pass("200 OK");
        }else {
        	logger.fail("Status : " +response.statusCode());
        }
    }
}
