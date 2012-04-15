/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author tonykovanen
 */
public class GsonReader implements Reader {
    private String url;
    
    
    public GsonReader(String url) {
        this.url = url;
    }
    
    @Override
    public Palautukset read(String studentNr) {
        InputStream stream = null;
        Palautukset palautukset = null;
        try {
            HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(url);
            client.executeMethod(method);
            stream = method.getResponseBodyAsStream();
            String bodyText = IOUtils.toString(stream);
            Gson mapper = new Gson();
            palautukset = mapper.fromJson(bodyText, Palautukset.class);
        } catch (IOException ex) {
            Logger.getLogger(GsonReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(GsonReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return palautukset;
    }
    
}
