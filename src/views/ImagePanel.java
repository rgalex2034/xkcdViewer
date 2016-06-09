/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author alex
 */
public class ImagePanel extends JPanel {

    public static final double MIN_ALIGN = 0;
    public static final double CENTERED = 1;
    public static final double MAX_ALIGN = 2;

    private BufferedImage image;
    private boolean scaled;
    private double verticalAlignment;
    private double horizontalAlignment;

    public ImagePanel(BufferedImage image) {
        super();
        this.image = image;
        this.scaled = false;
        this.verticalAlignment = 0;
        this.horizontalAlignment = 0;
    }

    public ImagePanel(BufferedImage image, boolean scaled) {
        super();
        this.image = image;
        this.scaled = scaled;
        this.verticalAlignment = 0;
        this.horizontalAlignment = 0;
    }

    public ImagePanel(BufferedImage image, boolean scaled, double verticalAlignment, double horizontalAlignment) {
        super();
        this.image = image;
        this.scaled = scaled;
        this.verticalAlignment = verticalAlignment;
        this.horizontalAlignment = horizontalAlignment;
    }

    public boolean isScaled() {
        return scaled;
    }

    public void setScaled(boolean scaled) {
        this.scaled = scaled;
    }

    public double isVerticalAligned() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(double verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public double isHorizontalAligned() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(double horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double factor = scaled ? calculateScaleFactor() : 1;
        int x = this.getX();
        int y = this.getY();

        Image resizedImage = image.getScaledInstance((int) (image.getWidth() * factor), (int) (image.getHeight() * factor), BufferedImage.SCALE_SMOOTH);

        //if (this.horizontalAlignment) {
            x = this.getX() + (int)((this.getWidth() / 2 - resizedImage.getWidth(null) / 2)*horizontalAlignment);
        //}
        //if (this.verticalAlignment) {
            y = this.getY() + (int)((this.getHeight() / 2 - resizedImage.getHeight(null) / 2)*verticalAlignment);
        //}

        g.drawImage(resizedImage, x, y, null);
    }

    private double calculateScaleFactor() {
        double factor;
        double xScale = (double) this.getWidth() / image.getWidth();
        double yScale = (double) this.getHeight() / image.getHeight();

        if (xScale > yScale) {
            factor = yScale;
        } else {
            factor = xScale;
        }
        
        return factor;
    }

}
