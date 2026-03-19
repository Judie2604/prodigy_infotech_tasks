package contactmanagementapp_tasks_3;
import java.util.ArrayList;
import java.util.Scanner;
public class ContactManagementApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();
        boolean isRunning = true;
        System.out.println("WELCOME TO CONTACT MANAGEMENT APPLICATION");

        while(isRunning){
            System.out.println("1.Add \n2.view \n3.Edit \n4.delete \n5.exit \nEnter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(ch){
                case 1:
                    //To Add the data
                    System.out.println("To Add the Contact please enter the following details");
                    System.out.println("-----------------------------------------------------");
                    //getting data from the user
                    System.out.println("Enter your Name :");
                    String name = sc.nextLine();
                    System.out.println("Enter your Email :");
                    String email = sc.nextLine();
                    System.out.println("Enter your Phone Number/Contact Number :");
                    String phno = sc.nextLine();
                    // creating the object for the contacts
                    Contact c = new Contact();
                    c.Name = name;
                    c.Email = email;
                    c.Phno = phno;

                    contacts.add(c);

                    System.out.println("Contact added..");
                    System.out.println();
                    break;

                case 2:
                    if(contacts.isEmpty())
                    {
                      System.out.println("No contacts available");
                    }
                    else
                    {
                        for(Contact ct : contacts)
                        {
                           System.out.println("Name: " + ct.Name);
                           System.out.println("Phone: " + ct.Phno);
                           System.out.println("Email: " + ct.Email);
                           System.out.println("-------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Edit");
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                case 5:
                    System.out.println("Exiting");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
        sc.close();

    }
    
}
