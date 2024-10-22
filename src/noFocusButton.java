import javax.swing.*;
import java.awt.*;

public class noFocusButton extends JButton {
    public noFocusButton(String write){
        super(write);
        setFocusable(false);
        setFont(new Font("Chinese Rocks Rg", Font.PLAIN, 25));
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(new Color(23, 22, 22));
        setForeground(new Color(248, 114, 23));
    }
}
