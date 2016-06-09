/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author alex
 */
public class Comic {
    private int month;
    private int num;
    private String link;
    private int year;
    private String news;
    private String safe_title;
    private String transcript;
    private String alt;
    private String img;
    private String title;
    private int day;
    
    @JsonIgnore
    private BufferedImage image = null;

    public Comic() {
    }
    
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getSafe_title() {
        return safe_title;
    }

    public void setSafe_title(String safe_title) {
        this.safe_title = safe_title;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public BufferedImage getImage() {
        if(image == null){
            loadImage();
        }
        
        return image;
    }

    public void loadImage() {
        try {
            
            URL imageUrl = new URL(img);
            image = ImageIO.read(imageUrl);
            
        } catch (MalformedURLException ex) {
            
            File imageFile = new File("src/assets/images/error.png");
            try {
                image = ImageIO.read(imageFile);
            } catch (IOException ex1) {
                Logger.getLogger(Comic.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (IOException ex) {
            File imageFile = new File("src/assets/images/error.png");
            try {
                image = ImageIO.read(imageFile);
            } catch (IOException ex1) {
                Logger.getLogger(Comic.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Comic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}
