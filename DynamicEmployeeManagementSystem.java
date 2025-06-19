import java.util.Scanner;
class Employee {
    private double basicSalary;
    private String empId;
    private String name;
    public void setDetails(double basicSalary, String empId, String name) {
        this.basicSalary = basicSalary;
        this.empId = empId;
        this.name = name;
    }
    public double getBasicSal() {
        return basicSalary;
    }

    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }
}

class Developers extends Employee {
    double precentagehike =0;
    double totalSal;

    public void CalculateSal(double hike) {
        precentagehike = hike;
        totalSal = getBasicSal() + ((getBasicSal() * precentagehike) / 100);
        System.out.println("Calculated Total Salary: " + totalSal);
    }

    public void display() {
        System.out.println("-------------DEVELOPERS--------------");
        System.out.println("Basic salary : " + getBasicSal());
        System.out.println("EmployeeId : " + getEmpId());
        System.out.println("Employee Name : " + getName());
        System.out.println("Hike : " + precentagehike + "%");
        System.out.println("TotalSalary : " + totalSal);
    }
}

class Testers extends Employee {
    double percentageHike = 10;
    double totalSal;

    public void CalculateSal(double hike) {
        percentageHike = hike;
        totalSal = getBasicSal() + ((getBasicSal() * percentageHike) / 100);
        System.out.println("Calculated Total Salary: " + totalSal);
    }

    public void display() {
        System.out.println("------------TESTERS------------------");
        System.out.println("Basic salary : " + getBasicSal());
        System.out.println("EmployeeId : " + getEmpId());
        System.out.println("Employee Name : " + getName());
        System.out.println("Hike : " + percentageHike + "%");
        System.out.println("TotalSalary : " + totalSal);
    }
}

class Company {
    static Employee emp;
    static int employeeCapacity = 10;
    static int count = 0;

    public static void addEmployee(Employee e) {
        if (count < employeeCapacity) {
            emp = e;
            count++;
            System.out.println("Employee Added Successfully.");
        } else {
            System.out.println("Employee Capacity reached. No more hiring this year.");
        }
    }

    public static void removeEmployee() {
        if (count != 0) {
            count--;
            emp = null;
            System.out.println("Employee fired.");
        } else {
            System.out.println("No employees to remove. Start hiring first!");
        }
    }

    public static String checkEmployee() {
        if (count == 0) {
            return "No employees are there";
        } else if (emp instanceof Developers) {
            return "DEVELOPERS";
        } else {
            return "TESTERS";
        }
    }

    public static void checkVacant() {
        if (count == employeeCapacity) {
            System.out.println("No vacancies are there");
        } else {
            System.out.println((employeeCapacity - count) + " vacancies are there");
        }
    }
}

public class DynamicEmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("\n-----------------------------");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Check Employee Type");
            System.out.println("4. Check Vacancy");
            System.out.println("5. Display Details");
            System.out.println("6. Exit");
            System.out.println("------------------------------");
            System.out.print("Enter your Choice: ");
            int opt = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (opt) {
                case 1:
                    System.out.println("\nChoose Employee Type:");
                    System.out.println("1. Developer");
                    System.out.println("2. Tester");
                    int n = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Employee ID: ");
                    String empId = sc.nextLine();
                    System.out.print("Enter Basic Salary: ");
                    double basicSalary = sc.nextDouble();

                    if (n == 1) {
                        Developers dev = new Developers();
                        dev.setDetails(basicSalary, empId, name);
                        Company.addEmployee(dev);
                    } else if (n == 2) {
                        Testers tester = new Testers();
                        tester.setDetails(basicSalary, empId, name);
                        Company.addEmployee(tester);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 2:
                    Company.removeEmployee();
                    break;

                case 3:
                    System.out.println("Employee Type: " + Company.checkEmployee());
                    break;

                case 4:
                    Company.checkVacant();
                    break;

                case 5:
                    if (Company.count == 0) {
                        System.out.println("No employee to display. Hire someone first!");
                    } else if (Company.emp instanceof Developers) {
                        Developers d = (Developers) Company.emp;
                        System.out.print("Enter Hike % for Developer: ");
                        double hike = sc.nextDouble();
                        d.CalculateSal(hike);
                        d.display();
                    } else if (Company.emp instanceof Testers) {
                        Testers t = (Testers) Company.emp;
                        System.out.print("Enter Hike % for Tester: ");
                        double hike = sc.nextDouble();
                        t.CalculateSal(hike);
                        t.display();
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using EMS!");
                    flag = false;
                    break;

                default:
                    System.out.println("Please enter a valid option.");
            }
        }

        sc.close();
    }
}
