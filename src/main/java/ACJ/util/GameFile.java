package ACJ.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameFile {

    private List<String> data = new ArrayList<String>();
    private String path;

    public GameFile(String path){
        this.path = path;
        read(path);
    }

    public void read(String path){
        InputStreamReader isr = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(path));
        BufferedReader br = new BufferedReader(isr);
        String line;
        try{
            while((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<String> getData(){
        return data;
    }

    public InputStream getStream(){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    //getOutputStream
    //getReader
    //getByteBuffer
    
}
