package pizzatales;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 161805385643332207L;
	private static int diff=1;
	private static int level=1;
	
	Container contentPane;
	
	public MainMenu(){
		initUI();
	}
	
	private void initUI() {

        JButton quitButton = new JButton("Quit");
        JButton startButton = new JButton("Start");
        JButton levelButton = new JButton("Level: "+level);
        JButton diffButton = new JButton("Difficulty: "+diff);
        
        levelButton.setBounds(500, 100, 100, 50);
        diffButton.setBounds(680, 100, 100, 50);
        startButton.setBounds(590, 250, 100, 50);
        quitButton.setBounds(590, 300, 100, 50);
        
        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(level == 9){
                	level = 1;
                } else {
                	level++;
                }
                levelButton.setText("Level: "+level);
            }
        });
        
        diffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	if(diff == 4){
            		diff = 1;
            	} else {
            		diff++;
            	}
                diffButton.setText("Difficulty: "+diff);
            }
        });
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	contentPane = getContentPane();
            	contentPane.removeAll();
                contentPane.invalidate();
                StartingClass sc = new StartingClass();
                sc.init();
                //sc.frame.setVisible(true);
                //contentPane.add(sc.frame);
                //contentPane.revalidate();
                //contentPane.repaint();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        createLayout(levelButton);
        createLayout(diffButton);
        createLayout(startButton);
        createLayout(quitButton);

        setTitle("Pizza Tales");
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	private void createLayout(JComponent... arg) {

		contentPane = getContentPane();
        GroupLayout gl = new GroupLayout(contentPane);
        contentPane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }
	
	public static int getDiff() {
		return diff;
	}

	public static int getLevel() {
		return level;
	}

	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                MainMenu mm = new MainMenu();
                mm.setVisible(true);
            }
        });
    }

}
