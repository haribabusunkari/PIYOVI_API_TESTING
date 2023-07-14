package com.piyovi.constants;

public class DHLConstants {


    /* ***********************   API URI's  ***********************************************/
    public static final String CARRIER_SHIPMENT_URL = "/dhl/api/v1/dhl/create_shipment";
    public static final String TRACKING_SHIPMENT_URL = "/dhl/api/v1/dhl/tracking";
    public static final String RATE_REQUEST_URL = "/dhl/api/v1/dhl/rate_request";

    /* ***********************   PAYLOAD FILES  ***********************************************/
    public static final String CARRIER_SHIPMENT_PAYLOAD = "DHL/createshipment.json";
    public static final String TRACKING_SHIPMENT_PAYLOAD = "DHL/trackingshipment.json";
    public static final String RATE_REQUEST_PAYLOAD = "DHL/raterequest.json";

}
