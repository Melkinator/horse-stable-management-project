import model.horse;
import model.horse.Gender;

public class Main {
    public static void main(String[] args) {
       horse uma = new horse();
       uma.setName("Wonder Acute");
       uma.setGender(Gender.MARE);

       System.out.println(uma.getName());
       System.out.println(uma.getGender());
    }
}