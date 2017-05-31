package net.valfridsson.gastolibro.jade;

public class GastroJadeConfig {

    public final String mainTemplateAbsolutePath = "mainpage.jade";
    public final String EntryKey = "entries";
    public final String BookKey = "book";

    private GastroJadeConfig() {
    }

    public static GastroJadeConfig getInstance() {
        return GastroJadeConfigHolder.INSTANCE;
    }

    private static class GastroJadeConfigHolder {
        private static final GastroJadeConfig INSTANCE = new GastroJadeConfig();
    }

}

