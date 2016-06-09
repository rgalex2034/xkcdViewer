/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.pojo.Comic;

/**
 *
 * @author alex
 */
public class ComicAsyncDAO implements URLAsyncReader.URLAsyncReaderHandler {

    private static final String basicUrl = "http://xkcd.com/:num:info.0.json";
    private ObjectMapper mapper;
    private ComicReceiver handler;

    public ComicAsyncDAO(ComicReceiver handler) {
        mapper = new ObjectMapper();
        this.handler = handler;
    }

    public void getComic(Integer num, int code) {

        String url = null;

        if (num != null) {
            url = basicUrl.replace(":num:", num + "/");
        } else {
            url = basicUrl.replace(":num:", "");
        }

        try {
            URLAsyncReader reader = new URLAsyncReader(url, code, this);
            reader.start();
        } catch (IOException ex) {

        }
    }

    public void getLastComic(int code) {
        getComic(null, code);
    }

    @Override
    public void receiveURLcontent(int code, URLAsyncReader.Status status, String content) {
        if (status == URLAsyncReader.Status.OK) {
            try {
                Comic c = mapper.readValue(content, Comic.class);
                c.loadImage();
                handler.reveiceComic(c, code);
            } catch (IOException ex) {
                Logger.getLogger(ComicAsyncDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (status == URLAsyncReader.Status.ERROR) {
            Logger.getLogger(ComicAsyncDAO.class.getName()).log(Level.SEVERE, null, content);
        }
    }

    public interface ComicReceiver {

        public void reveiceComic(Comic c, int code);
    }

}
