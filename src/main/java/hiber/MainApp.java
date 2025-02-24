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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Toyota", 2020)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Honda", 2021)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Ford", 2022)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("BMW", 2023)));

      List<User> users = userService.listUsers();
      printUser(users);

      System.out.println("List User by car model and series-----------------------------------------");

      List<User> usersByCar = userService.listUsersByCar("Toyota",2020);
      printUser(usersByCar);

      context.close();
   }

   private static void printUser(List<User> users){
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car Model = " + user.getCar().getModel());
         System.out.println("Car Series = " + user.getCar().getSeries());
         System.out.println();
      }
   }
}
