import java.time.LocalDate;
import java.util.Scanner;

import Pain.Horse;
import Pain.Stable;
import Pain.IUser;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stable stable = new Stable("John and Melk's Stable");
        System.out.println(stable.getLastMessage());

        int choice=-1;
        do {
            try {
                if (!stable.isLoggedIn()) {
                    printMainMenu();
                    try {
                        System.out.print("Choose: ");
                        choice = Integer.parseInt(sc.nextLine());
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
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number instead.");
                        choice = -1;
                    }
                } else {
                    printStaffMenu(stable);
                    System.out.print("Choose: ");
                    choice = sc.nextInt(); sc.nextLine();
                    switch (choice) {
                        case 1: {
                            System.out.print("Staff ID: ");            String id    = sc.nextLine();
                            System.out.print("Full Name: ");           String name  = sc.nextLine();
                            System.out.print("Phone: ");               String phone = sc.nextLine();
                            System.out.print("Role (ADMIN/MANAGER): "); String role  = sc.nextLine();
                            System.out.print("Username: ");            String user  = sc.nextLine();
                            System.out.print("Password: ");            String pass  = sc.nextLine();
                            float sal = (float) getSafeDouble(sc, "Salary: ");
                            stable.createStaff(id, name, phone, role, user, pass, sal);
                            System.out.println(stable.getLastMessage()); break;
                        }
                        case 2: {
                            System.out.print("Name: ");    String hName = sc.nextLine();
                            System.out.print("Breed: ");   String breed = sc.nextLine();
                            System.out.print("Color: ");   String color = sc.nextLine();

                            Horse.Gender gender = null;
                            while (gender==null) {
                                try {
                                    System.out.print("Gender (STALLION/MARE/GELDING/FILLY/COLT): ");
                                    gender = Horse.Gender.valueOf(sc.nextLine().toUpperCase());
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid gender. Please try again.");
                                }
                            }
                            
                            int stallId = getSafeInt(sc, "Stall ID: ");
                            double w    = getSafeDouble(sc, "Weight (kg): ");
                            double h    = getSafeDouble(sc, "Height (hands): ");
                            System.out.print("Sire: "); String sire = sc.nextLine();
                            System.out.print("Dam: ");  String dam  = sc.nextLine();
                            stable.createHorse(hName, breed, color, gender, LocalDate.now(), LocalDate.now(), stallId, sire, dam, w, h);
                            System.out.println(stable.getLastMessage()); break;
                        }
                        case 3: {
                            System.out.print("Customer ID: ");       String cId   = sc.nextLine();
                            System.out.print("Full Name: ");         String cName = sc.nextLine();
                            System.out.print("Phone: ");             String phone = sc.nextLine();
                            System.out.print("Password: ");          String pass  = sc.nextLine();
                            double bal   = getSafeDouble(sc, "Balance: ");
                            int hId      = getSafeInt(sc, "Horse ID (0=none): ");
                            stable.createCustomer(cId, cName, phone, pass, bal, hId);
                            System.out.println(stable.getLastMessage()); break;
                        }
                        case 4: {
                            System.out.print("Booking ID: "); String bId = sc.nextLine();
                            System.out.print("Customer ID: "); String cId = sc.nextLine();
                            int hId = getSafeInt(sc, "Horse ID: ");
                            int days = getSafeInt(sc, "Duration (Days): ");
                            stable.addBooking(bId, cId, hId, days);
                            System.out.println(stable.getLastMessage());
                            break;
                        }
                        case 5: {
                            int hId = getSafeInt(sc, "Horse ID: ");
                            int a   = getSafeInt(sc, "Available? (1=Yes, 0=No): ");
                            stable.setHorseAvailability(hId, a == 1);
                            System.out.println(stable.getLastMessage()); break;
                        }
                        case 6:  { stable.printStaff();     break; }
                        case 7:  { stable.printHorses();    break; }
                        case 8:  { stable.printCustomers(); break; }
                        case 9:  { stable.printBookings();  break; }
                        case 10:  { stable.printActiveStaff();       break; }
                        case 11:  { stable.printActiveStaffLambda(); break; }
                        case 12: {
                            System.out.print("Role to filter (ADMIN/MANAGER): ");
                            stable.printStaffByRole(sc.nextLine()); break;
                        }
                        case 13: { stable.logout(); System.out.println(stable.getLastMessage()); break; }
                        case 0:  { System.out.println("Goodbye!"); break; }
                        default: System.out.println("Invalid choice.");
                    }
                }    
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number instead.");
                choice = -1;
            }
        } while (choice != 0);
        sc.close();
    }

    // helper

    private static int getSafeInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number instead.");
            }
        }
    }

    private static double getSafeDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number instead.");
            }
        }
    }

    //

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
