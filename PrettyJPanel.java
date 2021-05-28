import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Custom JPanel.
 *
 * @author Kyle He
 * @author Angela Jiao
 * @version May 26, 2021
 */
class PrettyJPanel
    extends JPanel
{
    /**
     * Style
     */
    public enum Type
    {
        /**
         * transparent
         */
        TRANSPARENT,
        /**
         * outlined
         */
        OUTLINED
    }

    /**
     * Constructor
     *
     * @param types
     *            styles to adopt
     */
    public PrettyJPanel(Type... types)
    {
        for (Type t : types)
        {
            switch (t)
            {
                case TRANSPARENT:
                    setOpaque(false);
                    break;
                case OUTLINED:
                    setBorder(
                        BorderFactory.createCompoundBorder(
                            new LineBorder(Color.black, 3),
                            new EmptyBorder(5, 5, 5, 5)));
                    break;
            }
        }
    }
}
