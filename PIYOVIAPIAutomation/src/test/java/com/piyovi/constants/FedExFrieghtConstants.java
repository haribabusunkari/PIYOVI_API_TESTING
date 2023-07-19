package com.piyovi.constants;

public class FedExFrieghtConstants {


    /* ***********************   API URI's  ***********************************************/
    public static final String CARRIER_SHIPMENT_URL = "/fedex_freight/api/v1/fedex_freight/create_shipment";
    public static final String TRACKING_SHIPMENT_URL = "/fedex_freight/api/v1/fedex_freight/tracking";
    public static final String RATE_REQUEST_URL = "/fedex_freight/api/v1/fedex_freight/rate_request";
    public static final String CANCEL_SHIPMENT_URL = "/fedex_freight/api/v1/fedex_freight/cancel";
    public static final String VALIDATE_ADDRESS_URL = "/fedex_freight/api/v1/fedex_freight/validate_address";

    /* ***********************   PAYLOAD FILES  ***********************************************/
    public static final String CARRIER_SHIPMENT_PAYLOAD = "FedExFreight/createshipment.json";
    public static final String CARRIER_SHIPMENT_INTERNATIONAL_PAYLOAD = "FedExFreight/createInternationalShipment.json";
    public static final String TRACKING_SHIPMENT_PAYLOAD = "FedExFreight/trackingshipment.json";
    public static final String RATE_REQUEST_PAYLOAD = "FedExFreight/raterequest.json";
    public static final String CANCEL_SHIPMENTT_PAYLOAD = "FedExFreight/cancelshipment.json";
    public static final String VALIDATE_ADDRESS_PAYLOAD = "FedExFreight/validateaddress.json";

}
