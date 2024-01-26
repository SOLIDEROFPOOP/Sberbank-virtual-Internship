import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "Cities.csv";
        String filePath = Main.class.getResource(file).getPath();
        List<City> cities = readCsv(filePath);
        // Сортировка по имени по убыванию без учета регистра
        List<City> sortedByName = sortedByName(cities);
        // Сортировка по округу и наименованием города внутри каждого федерального округа
        List<City> sortedByNameAndDistrict = sortedByNameAndDistrict(cities);
        sortedByName.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------");
        sortedByNameAndDistrict.forEach(System.out::println);
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
    private static List<City> sortedByName(List<City> citiesUnsorted){
        List<City> sortedByName = citiesUnsorted.stream()
                .sorted(Comparator.comparing(City::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        return sortedByName;
    }
    private static List<City> sortedByNameAndDistrict(List<City> citiesUnsorted){
        List<City> sortedByNameAndDistrict = citiesUnsorted.stream()
                .sorted(Comparator.comparing(City::getDistrict)
                        .thenComparing(City::getName,String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        return sortedByNameAndDistrict;
    }
}