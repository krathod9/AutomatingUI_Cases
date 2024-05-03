package selenium.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.bidi.module.Input;
import selenium.objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {
    public static <T> T deserializeJson( String fileName , Class<T> T) throws IOException {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objMapper=new ObjectMapper();
        return objMapper.readValue(is, T);
    }
}
