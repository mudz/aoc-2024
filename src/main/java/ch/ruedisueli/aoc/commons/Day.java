package ch.ruedisueli.aoc.commons;

import java.nio.file.Path;

public abstract class Day {

  protected final int year;
  protected final int day;
  private final String resourcePath;

  public Day(int year, int day) {
    this.day = day;
    this.year = year;
    this.resourcePath = "src/main/resources/" + year + "/day" + day + ".txt";
  }

  public void printParts() {
    System.out.println("Part 1: " + part1());
    System.out.println("Part 2: " + part2());
  }

  public Path getInput() {
    return Path.of(resourcePath);
  }

  public abstract Object part1();
  public abstract Object part2();

}
