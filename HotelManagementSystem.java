import java.util.*;

// Guest class
class Guest {
    String name;
    String phone;

    Guest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

// Room class
class Room {
    int roomNumber;
    String type;
    boolean isBooked;
    Guest guest;

    Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isBooked = false;
        this.guest = null;
    }
}

// Hotel Service class
class HotelService {
    ArrayList<Room> rooms = new ArrayList<>();

    // Initialize rooms
    void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Single"));
        rooms.add(new Room(201, "Double"));
        rooms.add(new Room(202, "Double"));
        rooms.add(new Room(301, "Suite"));
    }

    // View available rooms
    void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room No: " + r.roomNumber + " | Type: " + r.type);
            }
        }
    }

    // Book room
    void bookRoom(Scanner sc) {
        System.out.print("Enter Room Number to Book: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && !r.isBooked) {
                System.out.print("Enter Guest Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Phone Number: ");
                String phone = sc.nextLine();

                r.guest = new Guest(name, phone);
                r.isBooked = true;

                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available!");
    }

    // View all bookings
    void viewBookings() {
        System.out.println("\nBooked Rooms:");
        for (Room r : rooms) {
            if (r.isBooked) {
                System.out.println("Room No: " + r.roomNumber +
                        " | Type: " + r.type +
                        " | Guest: " + r.guest.name +
                        " | Phone: " + r.guest.phone);
            }
        }
    }

    // Cancel booking
    void cancelBooking(Scanner sc) {
        System.out.print("Enter Room Number to Cancel Booking: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && r.isBooked) {
                r.isBooked = false;
                r.guest = null;
                System.out.println("Booking cancelled successfully!");
                return;
            }
        }
        System.out.println("Invalid room number or not booked!");
    }
}

// Main class
public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelService service = new HotelService();
        service.initializeRooms();

        int choice;

        do {
            System.out.println("\n===== HOTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    service.viewAvailableRooms();
                    break;
                case 2:
                    service.bookRoom(sc);
                    break;
                case 3:
                    service.viewBookings();
                    break;
                case 4:
                    service.cancelBooking(sc);
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}