package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        Car car = new Car("Porshe", 911);
        User vanya = new User("Petr", "Petrov", "petrov@mail.ru");
        vanya.setCar(car);
        userService.add(vanya);

        Car car2 = new Car("BMW", 750);
        User vanya2 = new User("Ivan", "Ivanov", "ivanov@mail.ru");
        vanya2.setCar(car2);
        userService.add(vanya2);

        Car car3 = new Car("Mercedes", 600);
        User vanya3 = new User("Dima", "Sidorov", "sidorov@mail.ru");
        vanya3.setCar(car3);
        userService.add(vanya3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        System.out.println();
        User user = userService.getUserWithCar("BMW",750);
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        context.close();
    }
}
