package edu.drexel.ws.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

//import javax.servlet.ServletContext;
import java.io.File;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;
import java.net.URL;

import edu.drexel.ws.messages.*;

/**
 * Created with IntelliJ IDEA.
 * User: bsmitc
 * Date: 11/4/13
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */


@Service
public class DBMock {

    private HashMap<Integer,PublicationType> pubCache = null;

    public HashMap<Integer,PublicationType> getFullPubList()
    {
        return queryPubs();
    }

    public PublicationType getPubById(int id)
    {
        HashMap<Integer,PublicationType> pubDB = queryPubs();
        Integer index = new Integer(id);
        return pubDB.get(index);
    }

    public Collection<PublicationType> getAllPubs()
    {
        HashMap<Integer,PublicationType> pubDB = queryPubs();
        return pubDB.values();
    }

    private HashMap<Integer,PublicationType> queryPubs()
    {
        if(pubCache != null) return pubCache;

        ObjectMapper mapper = new ObjectMapper();
        HashMap<Integer,PublicationType> pubDB = new HashMap<Integer,PublicationType>();

        try{

            File jsonDB  = new ClassPathResource("pubs.json").getFile();

            List<HashMap<String,Object>> pubList = mapper.readValue(jsonDB, new TypeReference<List<HashMap<String,Object>>>(){});

            for( HashMap<String,Object> p : pubList)
            {
                PublicationType pType = new PublicationType();
                Integer idx = new Integer(p.get("id").toString());
                pType.setAbstract(p.get("abstract").toString());
                pType.setCite(p.get("cite").toString());
                pType.setId(idx.intValue());
                pType.setTitle(p.get("title").toString());

                pubDB.put(idx,pType);
            }

            pubCache = pubDB;
            return pubDB;
        }catch(Exception e)
        {
            e.printStackTrace();
            pubCache = null;
            return null;
        }
    }
}

