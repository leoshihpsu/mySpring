package myspring.api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    FileProcessor fileProcessor;
    private final AtomicLong counter = new AtomicLong();

    public FileController(@Autowired FileProcessor fp){
        this.fileProcessor = fp;
    }

    @RequestMapping(path="/file", method={RequestMethod.POST})
    public boolean write(@RequestBody String line) throws IOException {
        return fileProcessor.save(line);
    }
    @RequestMapping(path="/file", method={RequestMethod.GET})
    public List<String> read(String name)  throws IOException {
        return fileProcessor.read();
    }
    @RequestMapping(path="/file", method={RequestMethod.DELETE})
    public boolean delete(String name)  throws IOException{
        return fileProcessor.delete();
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