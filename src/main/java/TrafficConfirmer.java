import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import javax.xml.ws.Response;


public class TrafficConfirmer {

    private static Logger log = LoggerFactory.getLogger(TrafficConfirmer.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // application.confからURLのリストを取得
        Config config = ConfigFactory.load();
        List<String> urls = config.getStringList("urls");

        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);

        urls.stream().forEach(url -> {
            if (urlValidator.isValid(url)) {
                try {
                    HttpResponse response = Request.Get(url).execute().returnResponse();
                    int statusCode = response.getStatusLine().getStatusCode();
                    String result = statusCode == HttpStatus.SC_OK ? "OK" : "NG";
                    log.info(url + " [" + result + "], status_code:" + statusCode);
                } catch (Exception e) {
                    log.error("can not access " + url, e);
                }
            } else {
                log.info(url + " is invalid.");
            }
        });


    }

}
