import java.io.Serializable;

//implement Serializable to be able to save data to a file
public class Employee implements Serializable{

    String name;
    String surname;
    int same;
    String email;

    //creating a constructor for an Employee class
    Employee(String name, String surname, int same) {
        //setting values
        this.name = name;
        this.surname = surname;
        this.same = same;

        //creating email address depending if there is already an employee with a same name and surname in the list
        if (same == 0) {
            //user put unique name and surname combination => email address without a number
            this.email = surname.toLowerCase() + "." + name.toLowerCase() + "@mex.com";
        } else {
            //user put name and surname combination that already exists in the list => adding a number to an email address
            this.email = surname.toLowerCase() + "." + name.toLowerCase() + same + "@mex.com";
        }
    }

}
