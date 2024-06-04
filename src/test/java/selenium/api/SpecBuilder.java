package selenium.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import selenium.constants.EndPoints;
import selenium.utils.ConfigLoader;

public class SpecBuilder {
    public static RequestSpecification getReqSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseURL())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getRespSpec(){
        return new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .log(LogDetail.HEADERS)
                .build();
    }

}
