package net.valfridsson.gastolibro.jade;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.JadeConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.ImmutableList;

import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;

public class Models {

  // Takes care of Caching
  JadeConfiguration jadeconfig;


  public Models(boolean caching) {
    this.jadeconfig = new JadeConfiguration();
  
    this.jadeconfig.setCaching(caching);
  }

  public String updateModel(String jadeTemplateAbsolutePath, Map<String, Object> model) throws IOException {

    return jadeconfig.renderTemplate(jadeconfig.getTemplate(jadeTemplateAbsolutePath), model);
  }

  public ModelBuilder populateModel() {
    return new ModelBuilder();
  }

  static class ModelBuilder {
    Map<String, Object> model;

    public ModelBuilder() {
      this.model = new HashMap<>();
    }

    public ModelBuilder updateEntries(ImmutableList<Entry> entries) {
      this.model.put("entries", entries);

      return this;
    }

    public ModelBuilder updateBooks(ImmutableList<Book> books) {
      this.model.put("books", books);

      return this;
    }

    public Map<String, Object> pack() {
      return model;
    }
  }
}

