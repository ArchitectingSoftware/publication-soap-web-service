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

        //Use the publication service POJO to handle reading from the JSON database

        return pubService.getPublicationsServiceRouter(pubRequest);
    }


}
