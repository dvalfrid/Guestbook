package net.valfridsson.gastolibro.jade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ModelsTest {

  @Test
  public void kalle() throws IOException {

    Models models = new Models(true);

    ImmutableList<Entry> entry = ImmutableList
      .<Entry>builder()
      .add(Entry.newBuilder()
          .id(103)
          .name("Smith")
          .ip("IPSmith")
          .headline("HeadlineSmith")
          .email("EmailSmith")
          .city("CitySmith")
          .country("CountrySmith")
          .message("MessageSmith")
          .viewAble(true)
          .build())
          .build();
 
 
   ImmutableList<Book> book = ImmutableList.<Book>builder()
      .add(new Book(103, "Smith", true))
      .build(); 

    File file = new File(ModelsTest.class.getClassLoader().getResource("assets/main.jade").getFile());


    String html = models
      .updateModel(file.getAbsolutePath(), models.populateModel()
          .updateEntries(entry)
          .updateBooks(book)
          .pack());


    assertNotNull(html);
  }
}

