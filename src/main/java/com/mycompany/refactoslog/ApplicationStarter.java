package com.mycompany.refactoslog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.mycompany.refactoslog.AdaptableServices.TotalPrice.TotalPriceCalculator;
import com.mycompany.refactoslog.AdaptableServices.TotalPrice.TotalPriceCalculatorAdapter;
import com.mycompany.refactoslog.Composite.Box;
import com.mycompany.refactoslog.Composite.Item;
import com.mycompany.refactoslog.Composite.Pack;
import com.mycompany.refactoslog.Decorators.*;
import com.mycompany.refactoslog.Model.Address;
import com.mycompany.refactoslog.Model.Company;
import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Model.Residence;
import com.mycompany.refactoslog.Model.User;
import com.mycompany.refactoslog.Utils.Transport;


public class ApplicationStarter {

    private static final Scanner s = new Scanner(System.in);
    private static Map<String, User> registeredUsers = new HashMap<>();
    private List<Address> registeredAddresses = new ArrayList<>();

    public ApplicationStarter(){
        populateUsers();
        populateAddresses();
    }

    public void startSystem(){

        System.out.println("Welcome to RefactosLog");
        
        while(true){
            System.out.println("1 - Register user");
            System.out.println("2 - Register address");
            System.out.println("3 - Create order");
            System.out.println("4 - Exit");
            System.out.print("Choose an option: ");
            int option = s.nextInt();
            s.nextLine();

            switch(option){
                case 1:
                    register();
                    break;
                case 2: 
                    createAddress(chooseUserRoutine());
                    break;
                case 3: 
                    createOrder();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    s.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }


    }

    private void createAddress(User user){
        System.out.println("Create Address");
        System.out.print("Street: ");
        String street = s.nextLine();
        System.out.print("City: ");
        String city = s.nextLine();
        System.out.print("State: ");
        String state = s.nextLine();
        System.out.print("Zip Code: ");
        String zipCode = s.nextLine();
        System.out.print("Country: ");
        String country = s.nextLine();
        System.out.print("Number: ");
        String number = s.nextLine();
        System.out.print("Complement: ");
        String complement = s.nextLine();
        System.out.print("Neighborhood: ");
        String neighborhood = s.nextLine();
        System.out.print("Phone: ");
        String phone = s.nextLine();
        System.out.println("Region: \n" +
                            "1 - Distrito Industrial \n" +
                            "2 - Quarteirão dos Artistas \n" +
                            "3 - Sussurros \n" +
                            "4 - Vigilância \n" +
                            "5 - Enigmas \n" +
                            "6 - Inovação \n" +
                            "7 - Beco Sereno \n" +
                            "8 - Setor Resiliente \n" +
                            "9 - Esquina Criação \n" +
                           "10 - Vila Harmoniosa ");
        int region = s.nextInt();

        System.out.println("Is your job address? yes[1] no[2]");
        int job = s.nextInt();

        Address address;

        if(job == 1 || job == 2){
            address = job == 2 ? 
                        new Residence(street, city, state, zipCode, country, number, complement, neighborhood, user.getEmail(), phone, user, region)
                        : 
                        new Company(street, city, state, zipCode, country, number, complement, neighborhood, user.getEmail(), phone, user, region);
        } else {
            System.out.println("Invalid option");
            return;
        }

        user.addAddress(address);
        registeredAddresses.add(address);
        System.out.println("Address created successfully");
    }

    private void createOrder(){
        System.out.println("Create Order");
        System.out.println("Select the address to send the order");
        Address fromAddress = chooseAddressRoutine();
        System.out.println("Select the address to receive the order");
        Address toAddress = chooseAddressRoutine();

        Random random = new Random();
        String randomOrdeCode = String.valueOf(random.nextInt(1000));
        Order order = new Order(randomOrdeCode, fromAddress, toAddress);

        System.out.println("Do you want to add packs to the order? (y/n)");

        String addPacks = s.nextLine();

        if(addPacks.equals("y")){
            order.addPacks(createPacks());
        }

        System.out.println("Do you want to add a secure to the order? (y/n)");
        String addSecure = s.nextLine();
        System.out.println("Do you want to add express delivery to the order? (y/n)");
        String addExpress = s.nextLine();

        CustomService services;
        if (addSecure.equals("y") && addExpress.equals("y")){
            services = new OrderSecureDecorator(new OrderExpressDeliveryDecorator(order));
        } else if (addSecure.equals("y")){
            services = new OrderSecureDecorator(order);
        } else if (addExpress.equals("y")){
            services = new OrderExpressDeliveryDecorator(order);
        } else {
            services = order;
        }

        var costCalculator = new TotalPriceCalculatorAdapter(new TotalPriceCalculator());
        float totalPrice = costCalculator.calculateTotal(order, services);

        order.setEstimatedTime(services.calculateTime());
        order.setPrice(totalPrice);
        order.setChanceToLose(services.IncreaseSecurity());

        System.out.println("Order created successfully");
        System.out.println(order.toString());
        new Transport(order).deliver();
    }

    private void register(){
        System.out.println("Register");
        System.out.print("Name: ");
        String name = s.nextLine();
        System.out.print("Email: ");
        String email = s.nextLine();
        System.out.print("Password: ");
        String password = s.nextLine();
        System.out.print("CPF: ");
        String cpf = s.nextLine();

        User user = new User(name, email, password, cpf);
        registeredUsers.put(email, user);
        System.out.println("User registered successfully");
    }

    private void populateUsers(){
        User user1 = new User("John", "john@email.com", "123456789", "12345678901");
        User user2 = new User("Jane", "jane@email.com", "password123", "9876543210");   

        registeredUsers.put(user1.getEmail(), user1);
        registeredUsers.put(user2.getEmail(), user2);
    }

    private void populateAddresses(){
        Address a1 = new Company  ("123 Main St", "City", "State", "12345", "Country", "Building", "Floor", "Apartment", "Landmark", "Postal Code", registeredUsers.get("john@email.com"), 1);
        Address a2 = new Residence("456 Elm St", "City", "State", "54321", "Country", "Building", "Floor", "Apartment", "Landmark", "Postal Code", registeredUsers.get("jane@email.com"), 2);
        
        registeredAddresses.add(a1);
        registeredAddresses.add(a2); 
    }

    private User chooseUserRoutine(){
        System.out.println("Choose a user");
        for(String email : registeredUsers.keySet()){
            System.out.println(email);
        }
        String email = s.nextLine();
        return registeredUsers.get(email);
    }

    private Address chooseAddressRoutine(){
        System.out.println("Choose an address");
        for(int i = 0; i < registeredAddresses.size(); i++){
            System.out.println(i + " - " + registeredAddresses.get(i).getStreet());
        }
        int option = s.nextInt();
        s.nextLine();
        return registeredAddresses.get(option);
    }

    public static List<Pack> createPacks(){

        //Criei 7 itens, uns serão encaixotados e outros não
        Pack i1 = new Item("Celular" , 0.3f);
        Pack i2 = new Item("Notebook" , 2.0f);
        Pack i3 = new Item("Vassoura" , 1.5f);
        Pack i4 = new Item("Cadeira" , 3.0f);
        Pack i5 = new Item("Mesa" , 5.0f);
        Pack i6 = new Item("Caderno" , 0.1f);
        Pack i7 = new Item("Lapis" , 0.01f);

        //Criei 2 caixas com os itens
        List<Pack> boxContent1 = List.of(i1, i2);
        List<Pack> boxContent2 = List.of(i6, i7);

        Box b1 = new Box("Caixa 1");
        Box b2 = new Box("Caixa 2");

        //Adicionei os itens nas caixas
        b1.add(boxContent1);
        b2.add(boxContent2);

        //O pedido é 2 caixas e 3 itens soltos
        return List.of(b1, b2, i3, i4, i5);
    }

    public void startDemo(){
        Random random = new Random();
        User user1 = new User("John", "john@example.com", "password", "12345678901");
        User user2 = new User("Jane", "jane@example.com", "password123", "9876543210");

        int region1 = random.nextInt(11);
        int region2 = random.nextInt(11);

        Address address1 = new Residence("123 Main St", "Tupinambazinho do leste", "Bahia", "12345", "Brazil", "Building", "Floor", "Apartment", "Landmark", "8080808", user1, region1);
        Address address2 = new Company("456 Elm St", "Tupinambazinho do norte", "Brasilia", "54321", "Brazil", "Building", "Floor", "Apartment", "Landmark", "7070070", user2, region2);
    
        user1.addAddress(address1);
        user2.addAddress(address2);
        
        Order order = new Order("123", address1, address2);
        order.addPacks(createPacks());

        // * Voce pode descomentar para testar os diferentes serviços

        CustomService orderServices = new OrderSecureDecorator(new OrderExpressDeliveryDecorator(order)); //Both services
        //CustomService orderServices = new OrderSecureDecorator(order); //Only secure
        //CustomService orderServices = new OrderExpressDeliveryDecorator(order); //Only express
        //CustomService orderServices = order; //No service

        var costCalculator = new TotalPriceCalculatorAdapter(new TotalPriceCalculator());
        float totalPrice = costCalculator.calculateTotal(order, orderServices);
        
        order.setEstimatedTime(orderServices.calculateTime());
        order.setPrice(totalPrice);
        order.setChanceToLose(orderServices.IncreaseSecurity());

        System.out.println(order.toString());

        new Transport(order).deliver();
    }
}
