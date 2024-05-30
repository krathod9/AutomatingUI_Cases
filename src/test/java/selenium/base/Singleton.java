package selenium.base;

public class Singleton {
    private static Singleton singleton_ref = null;
    //declared constructor as private
    private Singleton()
    {
        System.out.println("This is Singleton Class");
    }

    //declared static method that returns the object of singleton class
    public static Singleton getInstance() {
        if(singleton_ref == null)
            singleton_ref = new Singleton();
        return singleton_ref;
    }
}
