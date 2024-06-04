package selenium.dataproviders;

import org.testng.annotations.DataProvider;
import selenium.objects.BillingAddress;
import selenium.objects.Product;
import selenium.utils.JacksonUtils;

import java.io.IOException;
import java.util.ArrayList;

public class DataProviders {
    @DataProvider(name = "getFeaturedProducts", parallel = false)
    public Object[] getFeaturedProducts() throws IOException {
        Product[] products= JacksonUtils.deserializeJson("products.json", Product[].class);
        ArrayList<Object> arrayList=new ArrayList<>();
        for(Product product:products){
            if(product.isFeatured()){
                arrayList.add(product);
            }
        }
        return arrayList.toArray();
    }
    @DataProvider(name = "getBillingDetails", parallel = false)
    public Object[] getBillingDetails() throws IOException {
        return JacksonUtils.deserializeJson
                ("myBillingDetails.json", BillingAddress[].class);
    }
}
