import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "Cities.csv";
        String filePath = Main.class.getResource(file).getPath();
        List<City> cities = readCsv(filePath);
        Map<String, Long> regionCityCounter = groupByRegion(cities);
        regionCityCounter.forEach((region,count)->
                System.out.println(region + " - " + count));
    }
    private static List<City> readCsv(String filePath) throws FileNotFoundException {
        List<City> Cities= new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(filePath))){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data= line.split(";");
                // почему то когда вписал data[5] выходил error index out of bounds пришлось вписать data.length-1
                Cities.add(new City(data[1],data[2],data[3],Integer.parseInt(data[4]),data[data.length-1]));
            }
        }
        return Cities;
    }

    private static Map<String, Long> groupByRegion(List<City> cities){
        Map<String, Long> regionCityCounter = cities.stream().collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
        return regionCityCounter;
    }
}