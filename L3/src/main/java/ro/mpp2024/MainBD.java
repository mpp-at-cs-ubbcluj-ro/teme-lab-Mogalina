package ro.mpp2024;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainBD {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileReader("/Users/mogalina/Desktop/sem-4/medii-de-proiectare-si-programare/laborator/Proiect/L3/bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }

        CarRepository carRepo = new CarsDBRepository(props);

        carRepo.add(new Car("Tesla", "Model S", 2019));
        System.out.println("\nToate masinile din db");
        for (Car car : carRepo.findAll())
            System.out.println(car);

        String manufacturer = "Tesla";
        System.out.println("\nMasinile produse de " + manufacturer);
        for (Car car : carRepo.findByManufacturer(manufacturer))
            System.out.println(car);

        carRepo.add(new Car("BMW", "E46", 2010));
        int minYear = 2009;
        int maxYear = 2011;
        System.out.println("\nMasinile produse in anii " + minYear + "-" + maxYear);
        for (Car car : carRepo.findBetweenYears(minYear, maxYear)) {
            System.out.println(car);
        }

        Car updatedCar = new Car("BMW", "E46", 2020);
        int id = 8;
        carRepo.update(id, updatedCar);
        for (Car car : carRepo.findAll()) {
            System.out.println(car);
        }
    }
}
