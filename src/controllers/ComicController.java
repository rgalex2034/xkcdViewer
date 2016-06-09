/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.EventQueue;
import views.ComicView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.dao.ComicAsyncDAO;
import model.pojo.Comic;

/**
 *
 * @author alex
 */
public class ComicController implements ActionListener, ComicAsyncDAO.ComicReceiver {

    private ComicView view;
    private ComicAsyncDAO daoAsync;
    private int lastComic = -1;
    private int actualComic;
    private final int FIRST_COMIC = 1;
    private final int COMIC_RECEIVED = 2;

    public ComicController(ComicView view) {
        this.view = view;
        this.daoAsync = new ComicAsyncDAO(this);

        daoAsync.getLastComic(FIRST_COMIC);
    }

    private void loadComic(Comic c) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.setComicImage(c.getImage());
                view.setComicTitle(c.getTitle());
                view.setComicAltText(c.getAlt());
                view.setComicNum(c.getNum());
                view.refresh();
            }
        });
        this.actualComic = c.getNum();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        System.out.println(e.getModifiers());
        System.out.println(e.paramString());
        System.out.println(e.getID());

        if (e.getActionCommand().equals(ComicView.NEXT_COMMAND)) {
            if (view.getComicNum() < this.lastComic) {
                daoAsync.getComic(actualComic + 1, COMIC_RECEIVED);
            }
        } else if (e.getActionCommand().equals(ComicView.PREV_COMMAND)) {
            if (view.getComicNum() > 1) {
                daoAsync.getComic(actualComic - 1, COMIC_RECEIVED);
            }
        } else if (e.getActionCommand().equals(ComicView.NEW_COMMAND)) {
            
            daoAsync.getLastComic(FIRST_COMIC);
        }

    }

    @Override
    public void reveiceComic(Comic c, int code) {
        switch (code) {
            case FIRST_COMIC:
                this.lastComic = c.getNum();
                this.loadComic(c);
                break;
            case COMIC_RECEIVED:
                this.loadComic(c);
                break;
            default:
                throw new AssertionError();
        }
    }

}
