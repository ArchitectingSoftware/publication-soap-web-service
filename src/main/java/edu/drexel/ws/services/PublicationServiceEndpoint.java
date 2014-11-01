package edu.drexel.ws.services;

import edu.drexel.ws.model.DBMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;


import edu.drexel.ws.messages.*;

/**
 * Created by bsmitc on 10/28/14.
 */
@Endpoint
public class PublicationServiceEndpoint {

    private  PublicationService pubService;

    @Autowired
    public PublicationServiceEndpoint(PublicationService pubService) {
        this.pubService = pubService;
    }

    @PayloadRoot(localPart = "PublicationRequest", namespace = "http://drexel.edu/ws/messages")
    @ResponsePayload
    public PublicationResponse getPublications(@RequestPayload PublicationRequest pubRequest) {
        //checkSoapHeaderForSomething(header);

        //Check if getting pubs by ID

        return pubService.getPublicationsServiceRouter(pubRequest);
    }


}

/*
@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private DBMock countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
*/
