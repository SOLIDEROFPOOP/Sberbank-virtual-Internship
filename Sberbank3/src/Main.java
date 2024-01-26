import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "Cities.csv";
        String filePath = Main.class.getResource(file).getPath();
        List<City> cities = readCsv(filePath);
        cities.stream()
                .map(City::toString)
                .forEach(System.out::println);
    }
    private static List<City> readCsv(String filePath) throws FileNotFoundException {
        List<City> Cities= new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(filePath))){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data= line.split(";");
                // почему то когда вписал data[5] выходил error index out of bounds пришлось вписать data.length-1
                Cities.add(new City(data[1],data[2],data[3],data[4],data[data.length-1]));
            }
        }
        return Cities;
    }

}