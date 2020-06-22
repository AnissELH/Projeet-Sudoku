import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;

@SuppressWarnings("serial")
public class SudokuFrame extends JFrame {

	private SudokuPanel sudokuPanel;
	
	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Workspace Java\\Sudoku\\src\\Sudoku.jpg"));
		this.setMinimumSize(new Dimension(800,600));
		this.setResizable(false);
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game");
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new NewGameListener(26));
		
		
		
		
		file.add(newGame);
		menuBar.add(file);
		this.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
			

		sudokuPanel = new SudokuPanel();
		windowPanel.add(sudokuPanel);
		
		this.add(windowPanel);
		rebuildInterface( 26);
	}
	
	public void rebuildInterface(int fontSize) {
		SudokuPuzzle generatedPuzzle = new SudokuGenerator().generateRandomSudoku();
		sudokuPanel.newSudokuPuzzle(generatedPuzzle);
		sudokuPanel.setFontSize(fontSize);
		this.addKeyListener(sudokuPanel.new NumActionListener());
		sudokuPanel.repaint();
		
	}
	
	private class NewGameListener implements ActionListener {

		private int fontSize;
		
		public NewGameListener(int fontSize) {
			this.fontSize = fontSize;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface(fontSize);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SudokuFrame frame = new SudokuFrame();
				frame.setVisible(true);
			}
		});
	}
}