package myspring.api;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileProcessor {
    File file = new File("spring.txt");

    public void setFile(File file) {
        this.file = file;
    }

    public boolean save(String line) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public boolean delete(){
        return file.delete();
    }
    public List<String> read(){
        ArrayList<String> list = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            list = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
