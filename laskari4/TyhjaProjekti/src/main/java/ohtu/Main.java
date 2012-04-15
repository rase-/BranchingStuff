package ohtu;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        String studentNr = "138669";
        if ( args.length>0) {
            studentNr = args[0];
        }
 
        String url = "http://ohtustats.herokuapp.com/opiskelija/"+studentNr+".json";
 
        GsonReader reader = new GsonReader(url);
        StudentInformation info = new StudentInformation(studentNr, reader);
        System.out.println(info);
    }
}