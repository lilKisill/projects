import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyFrame extends  JFrame{
    private JButton button1 = new JButton("Сделать график");
    private JRadioButton radio1 = new JRadioButton("Парето");
    private JRadioButton radio2 = new JRadioButton("Слейтер");

    public  EasyFrame(){
        super("Easy Example");
        this.setBounds(100, 100, 200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Container container = this.getContentPane();
        JPanel contents = new JPanel();
        //add(button1,BorderLayout.NORTH);
        //contents.add(input);
        contents.add(button1);
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        contents.add(radio1);
        radio1.setSelected(true);
        contents.add(radio2);
        setContentPane(contents);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radio1.isSelected()){
                    //String k = input.getText();
                    JDialog dialog = createDialogPareto("График Байєса", false);
                    dialog.setVisible(true);
                }
                else if(radio2.isSelected()){
                    JDialog dialog = createDialogSleyter("График Слейтер", false);
                    dialog.setVisible(true);
                }
            }
        });
    }

    private JDialog createDialogPareto(String title, boolean modal)
    {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(420, 420);
        Bayes bayes = new Bayes();
        dialog.add(bayes);
        return dialog;
    }

    private JDialog createDialogSleyter(String title, boolean modal)
    {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(400, 420);
        //Sleyter sleyter = new Sleyter();
        //dialog.add(sleyter);
        return dialog;
    }
}
