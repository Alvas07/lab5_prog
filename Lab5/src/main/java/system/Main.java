package system;

import managers.Console;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Не указан путь до файла с коллекцией.");
    } else {
      System.out.println(args[0]);
      Console console = new Console();
      console.start(args);
    }
  }
}
