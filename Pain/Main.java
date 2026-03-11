package Pain;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stable stable = new Stable("John and Melk's Stable");
        System.out.println(stable.getLastMessage());

        int choice;
        do {
            if (!stable.isLoggedIn()) {
                printMainMenu();
                System.out.print("Choose: ");
                choice = sc.nextInt(); sc.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.print("Username: "); String u = sc.nextLine();
                        System.out.print("Password: "); String p = sc.nextLine();
                        stable.login(u, p); System.out.println(stable.getLastMessage()); break;
                    }
                    case 2: { stable.printHorses(); break; }
                    case 0: { System.out.println("Goodbye!"); break; }
                    default: System.out.println("Invalid choice.");
                }
            } else {
                printStaffMenu(stable);
                System.out.print("Choose: ");
                choice = sc.nextInt(); sc.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.print("Staff ID: ");            String id   = sc.nextLine();
                        System.out.print("Full Name: ");           String name = sc.nextLine();
                        System.out.print("Role (ADMIN/MANAGER): "); String role = sc.nextLine();
                        System.out.print("Username: ");            String user = sc.nextLine();
                        System.out.print("Password: ");            String pass = sc.nextLine();
                        System.out.print("Salary: ");              float sal   = sc.nextFloat(); sc.nextLine();
                        stable.createStaff(id, name, role, user, pass, sal);
                        System.out.println(stable.getLastMessage()); break;
                    }
                    case 2: {
                        System.out.print("Name: ");    String hName = sc.nextLine();
                        System.out.print("Breed: ");   String breed = sc.nextLine();
                        System.out.print("Color: ");   String color = sc.nextLine();
                        System.out.print("Gender (STALLION/MARE/GELDING/FILLY/COLT): ");
                        Horse.Gender gender = Horse.Gender.valueOf(sc.nextLine().toUpperCase());
                        System.out.print("Stall ID: ");       int stallId = sc.nextInt();  sc.nextLine();
                        System.out.print("Weight (kg): ");    double w    = sc.nextDouble(); sc.nextLine();
                        System.out.print("Height (hands): "); double h    = sc.nextDouble(); sc.nextLine();
                        System.out.print("Sire: "); String sire = sc.nextLine();
                        System.out.print("Dam: ");  String dam  = sc.nextLine();
                        stable.createHorse(hName, breed, color, gender,
                            LocalDate.now(), LocalDate.now(), stallId, sire, dam, w, h);
                        System.out.println(stable.getLastMessage()); break;
                    }
                    case 3: {
                        System.out.print("Customer ID: ");       String cId   = sc.nextLine();
                        System.out.print("Full Name: ");         String cName = sc.nextLine();
                        System.out.print("Phone: ");             String phone = sc.nextLine();
                        System.out.print("Password: ");          String pass  = sc.nextLine();
                        System.out.print("Balance: ");           double bal   = sc.nextDouble(); sc.nextLine();
                        System.out.print("Horse ID (0=none): "); int hId      = sc.nextInt();   sc.nextLine();
                        stable.createCustomer(cId, cName, phone, pass, bal, hId);
                        System.out.println(stable.getLastMessage()); break;
                    }
                    case 4: {
                        System.out.print("Horse ID: "); int hId = sc.nextInt(); sc.nextLine();
                        System.out.print("Available? (1=Yes, 0=No): "); int a = sc.nextInt(); sc.nextLine();
                        stable.setHorseAvailability(hId, a == 1);
                        System.out.println(stable.getLastMessage()); break;
                    }
                    case 5:  { stable.printStaff();     break; }
                    case 6:  { stable.printHorses();    break; }
                    case 7:  { stable.printCustomers(); break; }
                    case 8:  { stable.printActiveStaff();       break; }
                    case 9:  { stable.printActiveStaffLambda(); break; }
                    case 10: {
                        System.out.print("Role to filter (ADMIN/MANAGER): ");
                        stable.printStaffByRole(sc.nextLine()); break;
                    }
                    case 11: { stable.logout(); System.out.println(stable.getLastMessage()); break; }
                    case 0:  { System.out.println("Goodbye!"); break; }
                    default: System.out.println("Invalid choice.");
                }
            }
        } while (choice != 0);
        sc.close();
    }

    private static void printMainMenu() {
        System.out.println("\n=== HORSE STABLE SYSTEM (Not Logged In) ===");
        System.out.println("1) Login");
        System.out.println("2) View Horses");
        System.out.println("0) Exit");
    }

    private static void printStaffMenu(Stable stable) {
        IUser u = stable.getLoggedInUser();
        System.out.println("\n=== HORSE STABLE SYSTEM (Logged in: " + u.getName() + " | " + u.getRole() + ") ===");
        System.out.println("1)  Create Staff");
        System.out.println("2)  Add Horse");
        System.out.println("3)  Register Customer");
        System.out.println("4)  Set Horse Availability");
        System.out.println("5)  List Staff");
        System.out.println("6)  List Horses");
        System.out.println("7)  List Customers");
        System.out.println("--- Week 9: Abstraction ---");
        System.out.println("8)  Active Staff  [anonymous inner class]");
        System.out.println("9)  Active Staff  [lambda]");
        System.out.println("10) Filter Staff by Role [lambda]");
        System.out.println("11) Logout");
        System.out.println("0)  Exit");
    }
}
