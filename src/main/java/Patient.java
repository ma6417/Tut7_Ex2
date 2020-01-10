import java.io.Serializable;

public class Patient implements Serializable {
    private static int nextId = 1;
    protected int id;
    protected String name;
    protected String phoneNumber;

    public Patient(String name){
        this.name = name;
        id = nextId++;

    }

}