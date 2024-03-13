package membershipconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import membershippublisher.membershipInterface;
import membershippublisher.Member;

public class Activator implements BundleActivator {

    ServiceReference serviceReference;

    public void start(BundleContext context) throws Exception {
        System.out.println("Start Membership Service Consumer");
        serviceReference = context.getServiceReference(membershipInterface.class.getName());
        if (serviceReference != null) {
            membershipInterface membershipInterface = (membershipInterface) context.getService(serviceReference);
            displayMainMenu(membershipInterface);
        } else {
            System.out.println("Error: MembershipInterface service reference not found!");
        }
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Membership Service Consumer Stopped!");
        context.ungetService(serviceReference);
    }

    public void displayMainMenu(membershipInterface membershipInterface) {

        int option;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("---------- Membership Management System ----------");
        System.out.println("1  - Add Member");
        System.out.println("2  - List All Members");
        System.out.println("3  - Search Member by ID");
        System.out.println("4  - Delete Member by ID");
        System.out.print("Select an option : ");

        try {
            option = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            displayMainMenu(membershipInterface);
            return;
        }

        switch (option) {
            case 1:
                addMember(membershipInterface, scanner);
                break;
            case 2:
                listAllMembers(membershipInterface);
                displayMainMenu(membershipInterface);
                break;
            case 3:
                searchMember(membershipInterface, scanner);
                break;
            case 4:
                deleteMember(membershipInterface, scanner);
                break;
            default:
                System.out.println("Incorrect Input... Try Again");
                displayMainMenu(membershipInterface);
        }

        scanner.close(); // Close the scanner to prevent resource leak
    }

    private void addMember(membershipInterface membershipInterface, Scanner scanner) {
        Member member = new Member();
        System.out.println("Enter Member ID: ");
        String memberId = scanner.nextLine().trim();
        member.setMemberId(memberId);

        System.out.println("Enter Member Name: ");
        String name = scanner.nextLine().trim();
        member.setName(name);

        System.out.println("Enter Member Address: ");
        String address = scanner.nextLine().trim();
        member.setAddress(address);

        System.out.println("Enter Member Telephone Number: ");
        String teleNo = scanner.nextLine().trim();
        member.setTeleNo(teleNo);

        System.out.println("Enter Number of Borrowed Books: ");
        int noOfBorrowedBooks = Integer.parseInt(scanner.nextLine().trim());
        member.setNoOfBorrowedBooks(noOfBorrowedBooks);

        membershipInterface.addMember(member);
        displayMainMenu(membershipInterface);
    }

    private void listAllMembers(membershipInterface membershipInterface) {
        List<Member> members = membershipInterface.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found!");
        } else {
            System.out.println("List of Members:");
            for (Member member : members) {
                System.out.println("Member ID: " + member.getMemberId() + ", Name: " + member.getName() + ", Address: " + member.getAddress() + ", Telephone Number: " + member.getTeleNo() + ", No. of Borrowed Books: " + member.getNoOfBorrowedBooks());
            }
        }
    }

    private void searchMember(membershipInterface membershipInterface, Scanner scanner) {
        System.out.println("Enter Member ID to search: ");
        String memberId = scanner.nextLine().trim();
        Member foundMember = membershipInterface.searchMember(memberId);
        if (foundMember != null) {
            System.out.println("Member found: ID: " + foundMember.getMemberId() + ", Name: " + foundMember.getName() + ", Address: " + foundMember.getAddress() + ", Telephone Number: " + foundMember.getTeleNo() + ", No. of Borrowed Books: " + foundMember.getNoOfBorrowedBooks());
        } else {
            System.out.println("Member not found!");
        }
        displayMainMenu(membershipInterface);
    }

    private void deleteMember(membershipInterface membershipInterface, Scanner scanner) {
        System.out.println("Enter Member ID to delete: ");
        String memberId = scanner.nextLine().trim();
        membershipInterface.deleteMember(memberId);
        System.out.println("Member deleted successfully!");
        displayMainMenu(membershipInterface);
    }
}
