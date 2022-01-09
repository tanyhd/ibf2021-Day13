package nus.edu.workshop13.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Contacts {
    private String name;
    private String email;
    private String phoneNumber;
    private String id;


    public Contacts() {
        this.id = randomHex();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    //Use syncro?
    public String randomHex() {
        int minValue = 0;
        //max interger value use for the upper bound to generate the hex number
        int maxValue = Integer.MAX_VALUE;
        int ranNum = (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
        String hexValue = Integer.toHexString(ranNum);
        return hexValue;
    }

    public Contacts getContact(String id, String dir) {
        Contacts personContact = new Contacts();
        //use path to set the dir and file name
        Path path = Paths.get(dir + "/" + id + ".txt");
        BufferedReader br;
        try {
            //Use bufferedReader to read each line in the file\
            //set UTF-8 to read the correct format
            br = new BufferedReader(new FileReader(path.toFile(), Charset.forName("utf-8")));
            personContact.setName(br.readLine());
            personContact.setEmail(br.readLine());
            personContact.setPhoneNumber(br.readLine());
            br.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personContact; 
    }
}
