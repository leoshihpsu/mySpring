package myspring.api;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    FileProcessor fileProcessor;

    public FileController(@Autowired FileProcessor fp){
        this.fileProcessor = fp;
    }


    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(path="/file", method={RequestMethod.POST})
    public String write(@RequestBody String line) throws IOException {
        File file = new File("spring.txt");
        FileWriter fw = new FileWriter(file, true);
        fw.write(line + System.lineSeparator());
        fw.close();
        return "zapisano do pliku";
    }
    @RequestMapping(path="/file", method={RequestMethod.GET})
    public ArrayList<String> read(String name)  throws IOException {
        File file = new File("spring.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<String> list = new ArrayList<String>();
        while((line=br.readLine())!=null){
           list.add(line);
        }
        return list;
    }
    @RequestMapping(path="/file", method={RequestMethod.DELETE})
    public String delete(String name)  throws IOException {
        new File("spring.txt").delete();
        return "deleted";
    }

    ////////////////////////////////////////////////////


    @RequestMapping(path="/object", method={RequestMethod.POST})
    public String writeObj(@RequestParam(value="value") String value) throws IOException {
        Greeting obj = new Greeting(counter.getAndIncrement(), value);
        FileOutputStream fout = new FileOutputStream("obj.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(obj);
        return "zapisano do pliku";
    }
    @RequestMapping(path="/object", method={RequestMethod.GET})
    public Greeting readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois
                = new ObjectInputStream(new FileInputStream("obj.ser"));
        Greeting newObj = (Greeting) ois.readObject();
        return newObj;
    }




}