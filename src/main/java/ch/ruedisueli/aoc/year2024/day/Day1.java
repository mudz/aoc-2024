package ch.ruedisueli.aoc.year2024.day;

import ch.ruedisueli.aoc.commons.Day;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1 extends Day {

  public Day1() {
    super(2024, 1);
  }

  public static void main(String[] args) {
    new Day1().printParts();
  }

  @Override
  public Integer part1() {
    var locations = getLocations();

    var firstLocations = locations.stream().map(Location::first).sorted().toList();
    var secondLocations = locations.stream().map(Location::second).sorted().toList();

    return IntStream.range(0, firstLocations.size())
        .map(i -> Math.abs(firstLocations.get(i) - secondLocations.get(i)))
        .sum();
  }
  @Override
  public Long part2() {
    var locations = getLocations();

    var firstLocations = locations.stream().map(Location::first).toList();
    var secondLocation = locations.stream().map(Location::second).toList();

    var secondCounter = secondLocation.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    return firstLocations.stream()
        .mapToLong(firstLocation -> firstLocation * secondCounter.getOrDefault(firstLocation, 0L))
        .sum();
  }

  private List<Location> getLocations() {
    try (var reader = Files.newBufferedReader(getInput())) {
      return reader.lines()
          .map(this::getLocation)
          .collect(Collectors.toCollection(ArrayList::new));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private Location getLocation(String line) {
    var split = line.trim().split(" {3}");
    return new Location(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
  }

  private record Location(Integer first, Integer second) {
  }
}
