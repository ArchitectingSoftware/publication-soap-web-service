package edu.drexel.ws.services;

/**
 * Created by bsmitc on 10/28/14.
 */
import edu.drexel.ws.messages.*;

public interface PublicationService {
    PublicationResponse getPublicationsServiceRouter(PublicationRequest req);
    PublicationResponse getAllPublications();
    PublicationResponse getAPublication(int id);
}

