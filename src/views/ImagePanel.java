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

    public static final double LEFT_ALIGN = 0;
    public static final double CENTERED = 0.5;
    public static final double RIGHT_ALIGN = 1;

    private BufferedImage image;
    private boolean scaled;
    private boolean verticalAlignment;
    private boolean horizontalAlignment;

    public ImagePanel(BufferedImage image) {
        super();
        this.image = image;
        this.scaled = false;
        this.verticalAlignment = false;
        this.horizontalAlignment = false;
    }

    public ImagePanel(BufferedImage image, boolean scaled) {
        super();
        this.image = image;
        this.scaled = scaled;
        this.verticalAlignment = false;
        this.horizontalAlignment = false;
    }

    public ImagePanel(BufferedImage image, boolean scaled, boolean verticalAlignment, boolean horizontalAlignment) {
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

    public boolean isVerticalAligned() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(boolean verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public boolean isHorizontalAligned() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(boolean horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double factor = scaled ? calculateScaleFactor() : 1;
        int x = this.getX();
        int y = this.getY();

        Image resizedImage = image.getScaledInstance((int) (image.getWidth() * factor), (int) (image.getHeight() * factor), BufferedImage.SCALE_SMOOTH);

        if (this.horizontalAlignment) {
            x = this.getX() + this.getWidth() / 2 - resizedImage.getWidth(null) / 2;
        }
        if (this.verticalAlignment) {
            y = this.getY() + this.getHeight() / 2 - resizedImage.getHeight(null) / 2;
        }

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
