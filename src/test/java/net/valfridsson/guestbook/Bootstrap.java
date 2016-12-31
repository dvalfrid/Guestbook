package net.valfridsson.guestbook;

public class Bootstrap {

    public static void main(String... args) throws Exception{
        String configuration = "src/test/resources/guest-book-test.yml";
        GuestBookApplication.main("db", "migrate", configuration);
        GuestBookApplication.main("server", configuration);
    }

}
