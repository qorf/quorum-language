package plugins.quorum.Libraries.Robots.Lego;

public class Screen {
    public Object me_ = null;
    lejos.hardware.lcd.GraphicsLCD display = lejos.hardware.ev3.LocalEV3.get().getGraphicsLCD();
    
    public void Clear() {
        display.clear();
    }
    
    public void ErasePixel(int x, int y) {
        display.setPixel(x, 127-y, 0); //change the origin from the top-left to bottom-left
    }
    
    public void DrawPixel(int x, int y) {
        display.setPixel(x, 127-y, 1);
    }
    
    //I think their getPixel function is broken for x values...
    //x values 0 through 30 check pixels:
    //0   9   18  27  36  45  54  63  72  81
    //82  91  100 109 118 127 136 145 154 163
    //164 173 182 191 200 209 218 227 236 245
    //and I'm not sure how to check the pixels in between those
//    public boolean IsPixelPresent(int x, int y) {
//        x = x / 82 * 10 + x % 82 / 9;
//        System.out.println("x = " + x);
//        return display.getPixel(x, y) == 1; //getPixel returns 1 if a pixel is present
//    }
    
//    public void InvertPixel(int x, int y) {
//        display.setPixel(x, y, IsPixelPresent(x, y) ? 0 : 1);
//    }
    
    //should these methods clear the line before displaying on them or just leave what was on screen previously?
    public void Output(String message, int line) {
        lejos.hardware.lcd.LCD.clear(line);
        lejos.hardware.lcd.LCD.drawString(message, 0, line, false);
    }
    
    public void Output(String message, int line, int offset) {
        lejos.hardware.lcd.LCD.clear(line);
        lejos.hardware.lcd.LCD.drawString(message, offset, line, false);
    }
    
    public void OutputCenter(String string, int line) {
        display.setFont(lejos.hardware.lcd.Font.getDefaultFont());
        lejos.hardware.lcd.LCD.clear(line);
        display.drawString(string, 88, (line)*16, display.HCENTER); //88 pixels = center
    }
    
    public void OutputInvertedColor(String message, int line) {
        lejos.hardware.lcd.LCD.clear(line);
        lejos.hardware.lcd.LCD.drawString(message, 0, line, true);
    }
    
    public void OutputInvertedColor(String message, int line, int offset) {
        lejos.hardware.lcd.LCD.clear(line);
        lejos.hardware.lcd.LCD.drawString(message, offset, line, true);
    }
    
    //large fonts take up two lines
    public void OutputCenterLarge(String string, int line) {
        display.setFont(lejos.hardware.lcd.Font.getLargeFont());
        lejos.hardware.lcd.LCD.clear(line);
        lejos.hardware.lcd.LCD.clear(line+1);
        display.drawString(string, 88, line*16, display.HCENTER);
    }
    
    public void OutputLarge(String string, int line) {
        display.setFont(lejos.hardware.lcd.Font.getLargeFont());
        lejos.hardware.lcd.LCD.clear(line);
        lejos.hardware.lcd.LCD.clear(line+1);
        display.drawString(string, 0, (line)*16, 0);    //there are 16 pixels per line
    }
    
    public void OutputLarge(String string, int line, int indent) {
        display.setFont(lejos.hardware.lcd.Font.getLargeFont());
        lejos.hardware.lcd.LCD.clear(line);
        lejos.hardware.lcd.LCD.clear(line+1);
        display.drawString(string, indent*8, (line)*16, 0); //8 pixels per character
    }
    
    public void ScrollUp() {
        lejos.hardware.lcd.LCD.scroll();
    }
    
    //scroll up and place message on bottom line
    public void ScrollUp(String message) {
        lejos.hardware.lcd.LCD.scroll();
        display.setFont(lejos.hardware.lcd.Font.getDefaultFont());
        lejos.hardware.lcd.LCD.drawString(message, 0, 7);
    }
    
    public void ScrollUp(int lines) {
        for (int i = 0; i < lines; i++)
            lejos.hardware.lcd.LCD.scroll();
    }
    
    public void DrawLine(int x0, int y0, int x1, int y1) {
        display.drawLine(x0, 127-y0, x1, 127-y1);
    }
    
    public void DrawRectangleOutline(int x, int y, int width, int height) {
        display.drawRect(x, 127-y-height, width, height);
    }
    public void EraseRectangleOutline(int x, int y, int width, int height) {
        display.setColor(1);    //switch color to white
        display.drawRect(x, 127-y-height, width, height);
        display.setColor(0);    //switch color back to black
    }
    
    public void DrawRectangleFull(int x, int y, int width, int height) {
        display.fillRect(x, 127-y-height, width, height);
    }
    
    public void EraseRectangleFull(int x, int y, int width, int height) {
        display.setColor(1);
        display.fillRect(x, 127-y-height, width, height);
        display.setColor(0);
    }
    
    public void DrawEllipseOutline(int x, int y, int width, int height) {
        display.drawArc((int)(x - 0.5 * width), (int)(127 - y - height * 0.5), width, height, 0, 360); //sets x, y to the center of the ellipse
    }
    
    public void EraseEllipseOutline(int x, int y, int width, int height) {
        display.setColor(1);
        display.drawArc((int)(x - 0.5 * width), (int)(127 - y - height * 0.5), width, height, 0, 360); //sets x, y to the center of the ellipse
        display.setColor(0);
    }
    
    public void DrawEllipseFull(int x, int y, int width, int height) {
        display.fillArc((int)(x - 0.5 * width), (int)(127 - y - height * 0.5), width, height, 0, 360); //sets x, y to the center of the ellipse
    }
    
    public void EraseEllipseFull(int x, int y, int width, int height) {
        display.setColor(1);
        display.fillArc((int)(x - 0.5 * width), (int)(127 - y - height * 0.5), width, height, 0, 360); //sets x, y to the center of the ellipse
        display.setColor(0);
    }
    
    public void DrawCircleOutline(int x, int y, int radius) {
        display.drawArc(x-radius, 127-y-radius, radius*2, radius*2, 0, 360);
    }
    
    public void EraseCircleOutline(int x, int y, int radius) {
        display.setColor(1);
        display.drawArc(x-radius, 127-y-radius, radius*2, radius*2, 0, 360);
        display.setColor(0);
    }
    
    public void DrawCircleFull(int x, int y, int radius) {
        display.fillArc(x-radius, 127-y-radius, radius*2, radius*2, 0, 360);
    }
    
    public void EraseCircleFull(int x, int y, int radius) {
        display.setColor(1);
        display.fillArc(x-radius, 127-y-radius, radius*2, radius*2, 0, 360);
        display.setColor(0);
    }
}
