package com.piyovi.constants;

public class UPSConstants {


    /* ***********************   API URI's  ***********************************************/
    public static final String CARRIER_SHIPMENT_URL = "/ups/api/v1/ups/create_shipment";
    public static final String TRACKING_SHIPMENT_URL = "/ups/api/v1/ups/tracking";
    public static final String RATE_REQUEST_URL = "/ups/api/v1/ups/rate_request";
    public static final String CANCEL_SHIPMENT_URL = "/ups/api/v1/ups/cancel";
    public static final String VALIDATE_ADDRESS_URL = "/ups/api/v1/ups/validate_address";

    /* ***********************   PAYLOAD FILES  ***********************************************/
    public static final String CARRIER_SHIPMENT_PAYLOAD = "UPS/createshipment.json";
    public static final String CARRIER_SHIPMENT_INTERNATIONAL_PAYLOAD = "UPS/createInternationalShipment.json";
    public static final String TRACKING_SHIPMENT_PAYLOAD = "UPS/trackingshipment.json";
    public static final String RATE_REQUEST_PAYLOAD = "UPS/raterequest.json";
    public static final String CANCEL_SHIPMENTT_PAYLOAD = "UPS/cancelshipment.json";
    public static final String VALIDATE_ADDRESS_PAYLOAD = "UPS/validateaddress.json";

}
