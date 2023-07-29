import java.util.List;

public class Employee {
    // TODO: Tambahkan modifier untuk atribut
    String name;
    int yearsOfExperience;
    int salaryMultiplier;
    Division division;
    List<Project> projects;

    // TODO: Lengkapi constructor
    public Employee(String name, int yearsOfExperience, double salaryMultiplier) {
    }

    public int calculateSalary() {
        // TODO Lengkapi logika untuk menghitung gaji
        return 0;
    }

    public String getDivisionName() {
        //TODO: Lengkapi logika untuk mendapat string nama divisi
        return "";
    }

    public String getProjectsString() {
        //TODO: Lengkapi logika untuk mendapat string daftar proyek
        return "";
    }

    public String getName() {
        return name;
    }

    // Tambahkan getter & setter lainnya sesuai kebutuhan
}
