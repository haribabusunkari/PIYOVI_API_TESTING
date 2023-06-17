package com.piyovi.constants;

public class FedExConstants {


    /* ***********************   API URI's  ***********************************************/
    public static final String CARRIER_SHIPMENT_URL = "/fedex/api/v1/fedex/create_shipment";
    public static final String TRACKING_SHIPMENT_URL = "/fedex/api/v1/fedex/tracking";
    public static final String RATE_REQUEST_URL = "/fedex/api/v1/fedex/rate_request";
    public static final String CANCEL_SHIPMENT_URL = "/fedex/api/v1/fedex/cancel";
    public static final String VALIDATE_ADDRESS_URL = "/fedex/api/v1/fedex/validate_address";

    /* ***********************   PAYLOAD FILES  ***********************************************/
    public static final String CARRIER_SHIPMENT_PAYLOAD = "FedEx/createshipment.json";
    public static final String CARRIER_SHIPMENT_INTERNATIONAL_PAYLOAD = "FedEx/createInternationalShipment.json";
    public static final String TRACKING_SHIPMENT_PAYLOAD = "FedEx/trackingshipment.json";
    public static final String RATE_REQUEST_PAYLOAD = "FedEx/raterequest.json";
    public static final String CANCEL_SHIPMENTT_PAYLOAD = "FedEx/cancelshipment.json";
    public static final String VALIDATE_ADDRESS_PAYLOAD = "FedEx/validateaddress.json";

}
