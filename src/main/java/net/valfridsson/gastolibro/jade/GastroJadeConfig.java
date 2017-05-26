package net.valfridsson.gastolibro.jade;

public class GastroJadeConfig {

    public final String mainTemplateAbsolutePath = "main.jade";
    public final String ENTRYKEY = "entries";
    public final String BOOKKEY = "book";

    private GastroJadeConfig() {
    }

    public static GastroJadeConfig getInstance() {
        return GastroJadeConfigHolder.INSTANCE;
    }

    private static class GastroJadeConfigHolder {
        private static final GastroJadeConfig INSTANCE = new GastroJadeConfig();
    }

}

