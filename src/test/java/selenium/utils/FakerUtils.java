package selenium.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public long generateRandongNumber(){
        Faker faker=new Faker();
        return faker.number().randomNumber();
    }
}
