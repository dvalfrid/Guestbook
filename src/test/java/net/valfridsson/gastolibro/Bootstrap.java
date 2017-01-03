package net.valfridsson.gastolibro;

public class Bootstrap {

    public static void main(String... args) throws Exception{
        String configuration = "src/test/resources/gastolibro-test.yml";
        GastolibroApplication.main("db", "migrate", configuration);
        GastolibroApplication.main("server", configuration);
    }

}
