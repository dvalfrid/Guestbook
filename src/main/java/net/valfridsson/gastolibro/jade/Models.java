package net.valfridsson.gastolibro.jade;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.ClasspathTemplateLoader;

import java.io.IOException;
import java.util.HashMap;

public class Models {

    private static final JadeConfiguration configuration = new JadeConfiguration();

    static {
        configuration.setTemplateLoader(new ClasspathTemplateLoader());
    }


    private final HashMap<String, Object> model;
    private final String template;

    public static Models buildModel(String jadeTemplateAbsolutePath, HashMap<String, Object> model) {
        return new Models(jadeTemplateAbsolutePath, model);
    }

    public static Models buildModel(String jadeTemplateAbsolutePath) {
        return new Models(jadeTemplateAbsolutePath);
    }

    public static Models buildModel() {
        return new Models();
    }

    public Models(String template, HashMap<String, Object> model) {
        this.template = template;
        this.model = model;
    }

    public Models(String template) {
        this.template = template;
        this.model = new HashMap<>();
    }

    public Models() {
        this.template = GastroJadeConfig
                .getInstance()
                .mainTemplateAbsolutePath;

        this.model = new HashMap<>();
    }

    public Models data(String key, Object data) {
        this.model.put(key, data);

        return this;
    }

  public String render() throws IOException {
    return Models
      .configuration
      .renderTemplate(
          Models
          .configuration
          .getTemplate(this.template),
          this.model);
  }

}

