package myspring.api;

import java.io.*;

public class Files {

    public static void main(String[] args) {


        File file = new File("test.txt");

        try(FileWriter writer = new FileWriter(file, true)){

        writer.write(System.lineSeparator() + "Stringi");
        BufferedReader br = new BufferedReader(new FileReader(file));
        writer.flush();
        System.out.println(br.readLine());

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
