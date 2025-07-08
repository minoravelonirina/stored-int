package com.example.demo.endpoint.rest.controller.health;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoredIntController {

  private static final String FILE_PATH = System.getProperty("java.io.tmpdir") + "/stored-int.txt";

  @GetMapping("/stored-int")
  public int getStoredInt() throws IOException {
    File file = new File(FILE_PATH);

    if (file.exists()) {
      String content = Files.readString(file.toPath());
      return Integer.parseInt(content.trim());
    } else {
      int randomValue = new Random().nextInt(1000);
      Files.writeString(Path.of(FILE_PATH), String.valueOf(randomValue));
      return randomValue;
    }
  }
}
