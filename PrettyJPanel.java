import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class PrettyJPanel extends JPanel{
    public enum Type
    {
        TRANSPARENT,
        OUTLINED
    }

    public PrettyJPanel(Type... types){
        for (Type t: types){
            switch(t){
                case TRANSPARENT:
                    setOpaque(false);
                    break;
                case OUTLINED:
                    setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.black, 3), new EmptyBorder(5, 5, 5, 5)));
                    break;
            }
        }
    }
}