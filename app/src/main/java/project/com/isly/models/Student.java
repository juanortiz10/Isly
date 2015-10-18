package project.com.isly.models;

/**
 * Created by juan on 17/10/15.
 */
public class Student {
    private String name;
    private String mac;

    public Student(String name,String mac){
        this.name=name;
        this.mac=mac;
    }
    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
