package project.com.isly.models;

/**
 * Created by juan on 17/10/15.
 */
public class Student {
    private String name;
    private String mac;
    private String mat;

    public Student(String name,String mat,String mac){
        this.name=name;
        this.mac=mac;
        this.mat=mat;
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

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }
}
