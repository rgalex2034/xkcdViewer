/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class URLAsyncReader extends Thread{
    
    private BufferedReader in;
    private URL url;
    private URLConnection con;
    private URLAsyncReaderHandler handler;
    private int code;
    
    public enum Status{
        OK, ERROR
    }

    public URLAsyncReader(URL url, int code, URLAsyncReaderHandler handler) throws IOException {
        this.url = url;
        this.code = code;
        this.handler = handler;
    }

    public URLAsyncReader(String url, int code, URLAsyncReaderHandler handler) throws MalformedURLException, IOException {
        this(new URL(url), code, handler);
    }
    
    @Override
    public void run(){
        try {
            openConnection();
            String content = getContent();
            handler.receiveURLcontent(code, Status.OK, content);
        } catch (IOException ex) {
            handler.receiveURLcontent(code, Status.ERROR, ex.getMessage());
        }
    }
    
    private String getContent() throws IOException{
        String content = "";
        String line = null;
        
        while((line = in.readLine())!=null){
            content += line + "\n";
        }
        
        return content;
    }
    
    private void openConnection() throws IOException {
        con = url.openConnection();
        InputStreamReader isr = new InputStreamReader(con.getInputStream());
        in = new BufferedReader(isr);
    }
    
    public interface URLAsyncReaderHandler{
        public void receiveURLcontent(int code, Status status, String content);
    }
    
}
