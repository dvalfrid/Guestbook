package net.valfridsson.gastolibro.jade;

import java.io.File;
public class GastroJadeConfig {
  
  public final String mainTemplateAbsolutePath;  
  public final String ENTRYKEY = "entries";
  public final String BOOKSKEY = "books";

  private GastroJadeConfig() {
    this.mainTemplateAbsolutePath = (new File(GastroJadeConfig
          .class
          .getClassLoader()
          .getResource("main.jade")
          .getFile()))
      .getAbsolutePath();
  }

  public static GastroJadeConfig getInstance() {
    return GastroJadeConfigHolder.INSTANCE;
  }

  private static class GastroJadeConfigHolder {
    private static final GastroJadeConfig INSTANCE = new GastroJadeConfig();
  }

}

