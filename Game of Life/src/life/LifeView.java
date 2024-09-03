package life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class LifeView {

//	static {
//        try {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//	            if ("Nimbus".equals(info.getName())) {
//						UIManager.setLookAndFeel(info.getClassName());
//	                break;
//	            }
//	        }
//		} catch (Throwable ignored) {
//			// Nimbus L&F gick inte att hitta: ignorera
//		}
//	}

    private static final Color CELL_COLOR = new Color(0, 100, 0);
    
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    private final int rows, cols;
    private final LifeBoard board;
    
    private volatile int generation;

    private final LifeViewFrame frame;
    private final BoardView boardView;
    private final JButton nextButton;
    private final JButton quitButton;

    private int lastRow, lastCol;

    private final BlockingQueue<EventObject> events = new LinkedBlockingQueue<EventObject>();

    /** Skapar en vy till spelplanen board. */
    public LifeView(LifeBoard board) {
        this.board = board;
        rows = board.getRows();
        cols = board.getCols();

        nextButton = getConfined(() -> createButton("Next"));
        quitButton = getConfined(() -> createButton("Quit"));

        boardView = getConfined(() -> new BoardView());
        frame = getConfined(() -> new LifeViewFrame(boardView, board));
    }

    /**
     * Ritar upp de "fixa" delarna av spelplanen (rutnätet, generationsräknaren och
     * knapparna).
     */
    public void drawBoard() {
    	update();
    }

    /**
     * Ritar om de delar av ritfönstret som ändrats sedan föregående uppritning.
     */
    public void update() {
        generation = board.getGeneration();
        SwingUtilities.invokeLater(frame::update);
    }

    /**
     * Väntar tills användaren klickar med musen. Ger: 1: Klick i ruta på
     * spelplanen. Index för rutan kan hämtas med getRow och getCol. 2: Klick i
     * Next-rutan. 3: Klick i Quit-rutan.
     */
    public int getCommand() {
        try {
            while (true) {
                EventObject e = events.take();
                if (e.getSource() == boardView && e instanceof MouseEvent) {
                    MouseEvent me = (MouseEvent) e;
                    lastRow = boardView.getClickedRow(me.getY());
                    lastCol = boardView.getClickedCol(me.getX());
                    if (lastRow >= 0 && lastCol >= 0 && lastRow < board.getRows() && lastCol < board.getCols()) {
                        return 1;
                    }
                } else if (e instanceof KeyEvent && ((KeyEvent) e).getKeyChar() == 'x') {
                	setUpGliderGun();
                	update();
                } else if (e.getSource() == nextButton || (e instanceof KeyEvent && ((KeyEvent) e).getKeyChar() == 'f')) {
                    return 2;
                } else if (e.getSource() == quitButton) {
                    return 3;
                } else {
                    throw new Error("unexpected event: " + e);
                }
            }
        } catch (InterruptedException unexpected) {
            throw new Error("unexpected interruption");
        }
    }

    /** tag reda på radnummer för den klickade rutan */
    public int getRow() {
        return lastRow;
    }

    /** tag reda på kolonnummer för den klickade rutan */
    public int getCol() {
        return lastCol;
    }

    // returnerar ett värde av typ T från en getter, anropad i Swings EDT
    // (thread confinement)
    private static <T> T getConfined(Callable<T> getter) {
        try {
            CompletableFuture<T> result = new CompletableFuture<T>();
            SwingUtilities.invokeLater(() -> {
                try {
                    result.complete(getter.call());
                } catch (Exception e) {
                    throw new Error(e);
                }
            });
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new Error(e);
        }
    }

    // hjälpmetod: köa Swing-events
    private void submitUserEvent(EventObject e) {
        // undvik att blockera GUI-tråden
        if (!events.offer(e)) {
            throw new Error("Event unexpectedly lost: " + e);
        }
    }

    private static final String[] GOSPER_GLIDER_GUN = {
			"                                      ",
			"                         X            ",
			"                       X X            ",
			"             XX      XX            XX ",
			"            X   X    XX            XX ",
			" XX        X     X   XX               ",
			" XX        X   X XX    X X            ",
			"           X     X       X            ",
			"            X   X                     ",
			"             XX                       ",
			"                                      "
	};

    private void setUpGliderGun() {

		for (int r = 0; r < board.getRows(); r++) {
			for (int c = 0; c < board.getCols(); c++) {
				board.put(r, c, false);
			}
		}

		for (int r = 0; r < GOSPER_GLIDER_GUN.length && r < board.getRows(); r++) {
			String s = GOSPER_GLIDER_GUN[r];
			for (int c = 0; c < s.length() && c < board.getCols(); c++) {
				board.put(r, c, s.charAt(c) != ' ');
			}
		}
	}

    // -----------------------------------------------------------------------

    @SuppressWarnings("serial")
	private class LifeViewFrame extends JFrame {

        private final JLabel generationLabel = new JLabel("Generation: 1");

        private LifeViewFrame(BoardView boardView, LifeBoard board) {
            super("Game of Life");

            Box vbox = Box.createVerticalBox();
            boardView.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            vbox.add(boardView);

            generationLabel.setFont(FONT);
            Box buttonBox = Box.createHorizontalBox();
            buttonBox.add(nextButton);
            buttonBox.add(quitButton);
            buttonBox.add(Box.createHorizontalGlue());
            buttonBox.add(generationLabel);
            buttonBox.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

            vbox.add(buttonBox);
            add(vbox);

            boardView.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    submitUserEvent(e);
                }
            });
            ActionListener buttonListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    submitUserEvent(e);
                }
            };
            nextButton.addActionListener(buttonListener);
            quitButton.addActionListener(buttonListener);

            getRootPane().setDefaultButton(nextButton);

            nextButton.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == 'x' || e.getKeyChar() == 'f') {
                        submitUserEvent(e);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            pack();
            setLocationRelativeTo(null);

            setVisible(true);
        }

        private void update() {
            // För enkelhets skull ritas allt om varje gång
            generationLabel.setText("Generation: " + generation);
            repaint();
        }
    }

    private JButton createButton(String label) {
    	JButton b = new JButton(label);
    	b.setFont(FONT);
    	return b;
    }
    // -----------------------------------------------------------------------

    private class BoardView extends JComponent {
        private static final long serialVersionUID = 1L;
        
        public Dimension getPreferredSize() {
            return new Dimension(25 * cols + 1, 25 * rows + 1);
        }

        private double cellHeight() {
            return (getHeight() - getInsets().top - getInsets().bottom) / (double) rows;
        }

        private double cellWidth() {
            return (getWidth() - getInsets().left - getInsets().right) / (double) cols;
        }

        public int getClickedRow(int yClick) {
            return (int) ((yClick - getInsets().top) / cellHeight());
        }

        public int getClickedCol(int xClick) {
            return (int) ((xClick - getInsets().left) / cellWidth());
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int x = getInsets().left;
            int y = getInsets().top;

            g.setColor(Color.WHITE);
            g.fillRect(x, y, getWidth() - getInsets().left - getInsets().right,
                    getHeight() - getInsets().top - getInsets().bottom);
            g.setColor(Color.LIGHT_GRAY);

            double cWidth = cellWidth();
            double cHeight = cellHeight();
            for (int r = 0; r <= rows; r++) {
                g.drawLine(x, y + (int) Math.round(r * cHeight), x + (int) Math.round(cWidth * cols),
                        y + (int) Math.round(r * cHeight));
            }
            for (int c = 0; c <= cols; c++) {
                g.drawLine(x + (int) Math.round(c * cWidth), y, x + (int) Math.round(c * cWidth),
                        y + (int) Math.round(cHeight * rows));
            }

            g.setColor(CELL_COLOR);
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (board.get(r, c)) {
                        int x1 = x + (int) Math.round(c * cWidth);
                        int x2 = x + (int) Math.round((c + 1) * cWidth) - 1;
                        int y1 = y + (int) Math.round(r * cHeight);
                        int y2 = y + (int) Math.round((r + 1) * cHeight) - 1;
                        g.fillRect(x1 + 2, y1 + 2, x2 - x1 - 2, y2 - y1 - 2);
                    }
                }
            }
        }
    }
}
