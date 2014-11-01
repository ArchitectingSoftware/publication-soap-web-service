package edu.drexel.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import edu.drexel.ws.messages.*;
import edu.drexel.ws.model.*;

import java.util.Collection;

/**
 * Created by bsmitc on 10/28/14.
 */


@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    DBMock dbService;

    public PublicationResponse getPublicationsServiceRouter(PublicationRequest pubRequest)
    {
        PublicationResponse  rsp = new PublicationResponse();

        if((pubRequest.getRequestType().getGetById()== null) &&
                (pubRequest.getRequestType().getGetAll()== null))
        {
            rsp = buildErrorResponse(400,"Request type must be GetAll or GetById and not both!");
        }
        if(pubRequest.getRequestType().getGetById()!= null)
        {
            rsp = getAPublication(pubRequest.getRequestType().getGetById().intValue());
            System.out.println("Im Here - GetById!!!!");
        }
        else if (pubRequest.getRequestType().getGetAll() != null)
        {
            System.out.println("Im Here - GetByAll!!!!");
            rsp =  getAllPublications();
        }
        else
        {
            System.out.println("Im Here - ERROR!!!!");
            rsp = buildErrorResponse(400,"Unknown request type");
        }
        return rsp;
    }


    private PublicationResponse buildErrorResponse(int code, String message)
    {
        PublicationResponse rsp = new PublicationResponse();
        PublicationErrorType error = new PublicationErrorType();
        error.setCode(code);
        error.setMessage(message);
        rsp.setError(error);
        return rsp;
    }

    public PublicationResponse getAllPublications()
    {
        PublicationResponse rsp = new PublicationResponse();

        Collection<PublicationType> pList = dbService.getAllPubs();

        PublicationResponse.Publications pubList = new PublicationResponse.Publications();

        for(PublicationType aPub : pList)
        {
            pubList.getPublication().add(aPub);
        }

        rsp.setPublications(pubList);
        return rsp;
    }

    public PublicationResponse getAPublication(int id){

        PublicationResponse rsp = new PublicationResponse();
        PublicationType thePub = dbService.getPubById(id);
        if(thePub == null){
            rsp = buildErrorResponse(400,
                    "The requested index "+id+" is invalid or not in range");
        }else
        {
            rsp.setPublication(thePub);
        }

        return rsp;
    }
}
