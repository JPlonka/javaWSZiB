import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    //creating an ArrayList to load and store all data
    static List<Employee> employeeList = new ArrayList<Employee>();

    public static void main(String[] args) {
        //loading data from a file
        loadData();

        //adding a new employee and creating an email address
        addEmployee();

        //saving data to a file
        saveData();

        //printing all data stored in the ArrayList
        printEmployees(employeeList);
    }

    //printing employee name, surname and email stored in the ArrayList
    public static void printEmployees(List<Employee> empList) {
        System.out.println("All employees: ");

        //iterating through every object in the list to print it in the console
        for(int j = 0; j < empList.size(); j++) {
            System.out.println(empList.get(j).name + " " + empList.get(j).surname + " " + empList.get(j).email);
        }
    }

    //adding a new employee
    private static void addEmployee() {
        Scanner scan = new Scanner(System.in);

        //value to end the while loop if user don't want to add a next employee
        boolean nextEmployee = true;

        //getting loaded List size to print a new added employee email
        int i = employeeList.size();

        //while loop for adding more than one new employee in one runtime of a program
        while(nextEmployee) {
            //getting a name of an employee
            System.out.print("Name: ");
            String name = scan.next();

            //getting a surname of an employee
            System.out.print("Surname: ");
            String surname = scan.next();

            //checking if there is already an employee with the same name and surname that user wrote
            int same = 0;
            for (int j = 0; j < employeeList.size(); j++) {
                if (employeeList.get(j).name.equals(name) && employeeList.get(j).surname.equals(surname)) {
                    //setting how many same name and surname employees are there in the list
                    same++;
                }
            }

            //creating a new employee object in the list
            employeeList.add(new Employee(name, surname, same));

            //printing a new added employee email
            System.out.println(employeeList.get(i).email);
            i++;

            //asking user if he/she wants to add a new employee or not
            System.out.println("Do you want to add a new employee? y/n");
            char newEmployee = scan.next().charAt(0);

            //if the answer was 'n' then change value of variable nextEmployee to exit the while loop
            if (newEmployee == 'n') {
                nextEmployee = false;
            }
        }
    }

    //saving data to a file
    private static void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream("data.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employeeList);
            oos.close();
            fos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //loading data from a file
    private static void loadData() {
        try {
            FileInputStream fis = new FileInputStream("data.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            employeeList = (ArrayList<Employee>)ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

}
