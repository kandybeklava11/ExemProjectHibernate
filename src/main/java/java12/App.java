package java12;

import java12.entity.*;
import java12.enums.FamilyStatus;
import java12.enums.Gender;
import java12.enums.HouseType;
import java12.service.*;
import java12.service.impl.*;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        AddressService addressService=new AddressServiceImpl();
        AgencyService agencyService=new AgencyServiceImpl();
        CustomerService customerService=new CustomerServiceImpl();
        HouseService houseService=new HouseServiceImpl();
        OwnerService ownerService=new OwnerServiceImpl();
        Rent_InfoService rentInfoService=new Rent_InfoServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("-----------------------------------------------------");
            System.out.println(" CRUD OPTIONS : AGENCY ->   | 1. Save Agency        |");
            System.out.println("                            | 2. Delete Agency      |");
            System.out.println("                            | 3. Get all Agencies   |");
            System.out.println("                            | 4. Get Agency by id   |");
            System.out.println("                            | 5. Update Agency      |");
            System.out.println("                            -------------------------");
            System.out.println("             CUSTOMER  ->   | 6. Save Customer      |");
            System.out.println("                            | 7. Delete Customer    |");
            System.out.println("                            | 8. Get all Customers  |");
            System.out.println("                            | 9. Get Customer by id |");
            System.out.println("                            |10. Update Customer    |");
            System.out.println("                            -------------------------");
            System.out.println("              House    ->   |11. Save House         |");
            System.out.println("                            |12. Delete House       |");
            System.out.println("                            |13. Get all Houses     |");
            System.out.println("                            |14. Get House by id    |");
            System.out.println("                            |15. Update House       |");
            System.out.println("                            -------------------------");
            System.out.println("              OWNER    ->   |16. Save Owner         |");
            System.out.println("                            |17. Delete Owner       |");
            System.out.println("                            |18. Get all Owners     |");
            System.out.println("                            |19. Get Owner by id    |");
            System.out.println("                            |20. Update Owner       |");
            System.out.println("-----------------------------------------------------");
            System.out.println(" OTHERS -> |21. Get all Address                     |");
            System.out.println("           |22. Get Address by id                   |");
            System.out.println("           |23. Update Address                      |");
            System.out.println("           |24. Get Addresses with Agencies         |");
            System.out.println("           |25. Count of Agency from city           |");
            System.out.println("           |26. Group by region                     |");
            System.out.println("0. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Name :");
                    String name=scanner.nextLine();
                    System.out.println("Phone number (must start with '+996'): ");
                    String phoneNumber=scanner.nextLine();
                    if (!phoneNumber.startsWith("+996")) {
                        System.out.println("Invalid phone number format. Please start with '+996'.");
                    } else {
                        System.out.println("_____Write the Address_____");
                        System.out.println("City :");
                        String city = scanner.nextLine();
                        System.out.println("Region :");
                        String region = scanner.nextLine();
                        System.out.println("Street :");
                        String street = scanner.nextLine();
                        Address address = new Address(city, region, street);
                        System.out.println(agencyService.saveAgency(new Agency(name, phoneNumber, address)));
                    }
                    break;
                case 2:
                    System.out.println("Id :");
                    long Rid= scanner.nextLong();
                    System.out.println(agencyService.removeAgencyById(Rid));
                    break;
                case 3:
                    System.out.println(agencyService.getAllAgency());
                    break;
                case 4:
                    System.out.println("Id :");
                    long Gid= scanner.nextLong();
                    System.out.println(agencyService.getAgencyById(Gid));
                    break;
                case 5:
                    System.out.println("write the id of Agency to update");
                    long Uid= scanner.nextLong();
                    System.out.println("Name :");
                    String Uname=scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Phone number :");
                    String UphoneNumber=scanner.nextLine();
                    System.out.println("_____Write the Address_____");
                    System.out.println("City :");
                    String Ucity=scanner.nextLine();
                    System.out.println("Region :");
                    String Uregion=scanner.nextLine();
                    System.out.println("Street :");
                    String Ustreet=scanner.nextLine();
                    Address Uaddress=new Address(Ucity,Uregion,Ustreet);
                    System.out.println(agencyService.updateAgencyById(Uid, new Agency (Uname, UphoneNumber, Uaddress)));
                    break;
                case 6:
                    System.out.println("First name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Email:");
                    String email = scanner.nextLine();
                    Date dateOfBirth = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    while (dateOfBirth == null) {
                        System.out.println("Date of birth (yyyy-MM-dd):");
                        String dobInput = scanner.nextLine();
                        try {
                            dateOfBirth = dateFormat.parse(dobInput);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                        }
                    }
                    long ageInMillis = System.currentTimeMillis() - dateOfBirth.getTime();
                    long age = ageInMillis / (1000L * 60 * 60 * 24 * 365);
                    if (age < 18) {
                        System.out.println("You must be at least 18 years old.");
                        return;
                    }
                    System.out.println("Enter your gender (MALE/FEMALE/OTHER):");
                    String genderInput = scanner.nextLine().toUpperCase();
                    Gender gender = Gender.valueOf(genderInput);
                    System.out.println("Nationality:");
                    String nationality = scanner.nextLine();
                    System.out.println("Family status (SINGLE,\n" +
                            "    MARRIED,\n" +
                            "    DIVORCED,\n" +
                            "    WIDOW,\n" +
                            "    SEPARATED,\n" +
                            "    OTHER) :");
                    String familyStatus = scanner.nextLine().toUpperCase();
                    FamilyStatus familyStatus1=FamilyStatus.valueOf(familyStatus);
                    System.out.println(customerService.saveCustomer(new Customer(firstName,lastName,email,dateOfBirth,gender,nationality,familyStatus1)));
                    break;
                case 7:
                    System.out.println("Id :");
                    long Cid=scanner.nextLong();
                    System.out.println(customerService.removeCustomerById(Cid));
                    break;
                case 8:
                    System.out.println(customerService.getAllCustomers());
                    break;
                case 9:
                    System.out.println("Id :");
                    long Cid1=scanner.nextLong();
                    System.out.println(customerService.getCustomerById(Cid1));
                    break;
                case 10:
                    System.out.println("Write the id to update :");
                    long Cid2=scanner.nextLong();
                    System.out.println("First name:");
                    String CfirstName = scanner.nextLine();
                    System.out.println("Last name:");
                    String ClastName = scanner.nextLine();
                    System.out.println("Email:");
                    String Cemail = scanner.nextLine();
                    Date CdateOfBirth = null;
                    SimpleDateFormat CdateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    while (CdateOfBirth == null) {
                        System.out.println("Date of birth (yyyy-MM-dd):");
                        String dobInput = scanner.nextLine();
                        try {
                            dateOfBirth = CdateFormat.parse(dobInput);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                        }
                    }
                    long CageInMillis = System.currentTimeMillis() - CdateOfBirth.getTime();
                    long Cage = CageInMillis / (1000L * 60 * 60 * 24 * 365);
                    if (Cage < 18) {
                        System.out.println("You must be at least 18 years old.");
                        return;
                    }
                    System.out.println("Enter your gender (MALE/FEMALE/OTHER):");
                    String CgenderInput = scanner.nextLine().toUpperCase();
                    Gender Cgender = Gender.valueOf(CgenderInput);
                    System.out.println("Nationality:");
                    String Cnationality = scanner.nextLine();
                    System.out.println("Family status (SINGLE,\n" +
                            "    MARRIED,\n" +
                            "    DIVORCED,\n" +
                            "    WIDOW,\n" +
                            "    SEPARATED,\n" +
                            "    OTHER) :");
                    String CfamilyStatus = scanner.nextLine().toUpperCase();
                    FamilyStatus CfamilyStatus1=FamilyStatus.valueOf(CfamilyStatus);
                    System.out.println(customerService.updateCustomerById(Cid2,new Customer(CfirstName,ClastName,Cemail,CdateOfBirth,Cgender,Cnationality,CfamilyStatus1)));
                    break;
                case 11:
                    System.out.println("House type (HOUSE,APARTMENT :");
                    String houseType = scanner.nextLine().toUpperCase();
                    HouseType houseTyp = HouseType.valueOf(houseType);
                    System.out.println("Price :");
                    BigDecimal price=scanner.nextBigDecimal();
                    System.out.println("Rating :");
                    double rating=scanner.nextDouble();
                    System.out.println("Description :");
                    String description=scanner.nextLine();
                    System.out.println("Room :");
                    int room=scanner.nextInt();
                    System.out.println("Furniture :");
                    boolean furniture=scanner.nextBoolean();
                    System.out.println("_____Write the Address_____");
                    System.out.println("City :");
                    String Hcity=scanner.nextLine();
                    System.out.println("Region :");
                    String Hregion=scanner.nextLine();
                    System.out.println("Street :");
                    String Hstreet=scanner.nextLine();
                    Address Haddress=new Address(Hcity,Hregion,Hstreet);
                    houseService.saveHouse(new House(houseTyp,price,rating,description,room,furniture,Haddress));
                    break;
                case 12:
                    System.out.println("Id :");
                    long HId=scanner.nextLong();
                    System.out.println(houseService.removeHouseById(HId));
                    break;
                case 13:
                    System.out.println(houseService.getAllHouses());
                    break;
                case 14:
                    System.out.println("Id :");
                    long Hid=scanner.nextLong();
                    System.out.println(houseService.getHouseById(Hid));
                    break;
                case 15:
                    System.out.println("ID to update :");
                    long Hidd=scanner.nextLong();
                    System.out.println("House type (HOUSE,APARTMENT :");
                    String houseTypee = scanner.nextLine().toUpperCase();
                    HouseType HhouseTyp = HouseType.valueOf(houseTypee);
                    System.out.println("Price :");
                    BigDecimal Hprice=scanner.nextBigDecimal();
                    System.out.println("Rating :");
                    double Hrating=scanner.nextDouble();
                    System.out.println("Description :");
                    String Hdescription=scanner.nextLine();
                    System.out.println("Room :");
                    int Hroom=scanner.nextInt();
                    System.out.println("Furniture :");
                    boolean Hfurniture=scanner.nextBoolean();
                    System.out.println("_____Write the Address_____");
                    System.out.println("City :");
                    String HHcity=scanner.nextLine();
                    System.out.println("Region :");
                    String HHregion=scanner.nextLine();
                    System.out.println("Street :");
                    String HHstreet=scanner.nextLine();
                    Address HHaddress=new Address(HHcity,HHregion,HHstreet);
                    houseService.updateHouseById(Hidd,new House(HhouseTyp,Hprice,Hrating,Hdescription,Hroom,Hfurniture,HHaddress));
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    System.out.println(addressService.getAllAddress());
                    break;
                case 22:
                    System.out.println("Id :");
                    long AiD=scanner.nextLong();
                    System.out.println(addressService.getAddressById(AiD));
                    break;
                case 23:
                    System.out.println("write the id of Address to update");
                    long Aid= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("City :");
                    String Acity=scanner.nextLine();
                    System.out.println("Region :");
                    String Aregion=scanner.nextLine();
                    System.out.println("Street :");
                    String Astreet=scanner.nextLine();
                    Address Aaddress=new Address(Acity,Aregion,Astreet);
                    System.out.println(addressService.updateAddressById(Aid,Aaddress));
                    break;
                case 24:
                    System.out.println(addressService.GetAddressWithAgency());
                    break;
                case 25:
                    System.out.println("City :");
                    String nameOfCity=scanner.nextLine();
                    System.out.println(addressService.countOfAgencyFromCity(nameOfCity));
                    break;
                case 26:
                    System.out.println(addressService.groupByRegion());
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
