package client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Messenger extends JFrame {

    /**
     *  @access private
     *  @var IController controller
     */
    private IController controller;

    /**
     *  @access private static
     *  @var integer windowWidth
     */
    private static final int windowWidth = 400;

    /**
     *  @access private static
     *  @var integer windowHeight
     */
    private static final int windowHeight = 400;

    /**
     *  @access private static
     *  @var JButton jbutton
     */
    private static final JButton jbutton = new JButton( "Отправить" );

    /**
     *  @access private static
     *  @var JTextArea textArea
     */
    private static final JTextArea textArea = new JTextArea( 10, 33 );

    /**
     *  @access private static
     *  @var JScrollPane scroll
     */
    private static JScrollPane scroll = null;

    /**
     *  @access private static
     *  @var JTextField textField
     */
    private static final JTextField textField = new JTextField(35 );

    /**
     *  @access private static
     *  @var String allMessages
     */
    private static String allMessages = "";

    /**
     * constructor
     *
     * @param controller
     */
    public Messenger( IController controller ) {
        // устанавливаем титульный заголовок окна
        setTitle( "Месенджер" );
        // подписываемся на событие клика по кнопки закрыть окна
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        // устанавливаем параметры формы
        setSize( windowWidth, windowHeight );
        setResizable( false );

        // Jpanel panel_top_buttons
        JPanel panel = new JPanel();
        panel.setBackground( Color.lightGray );
        // выбор компоновщика элементов
        panel.setLayout( new FlowLayout() );

        // Создаем текстовую область для отображения переписки
        textArea.setBorder( new LineBorder( Color.GRAY, 1 ) );
        textArea.setEditable( false );
        textArea.setLineWrap( true );

        // добавляем горизонтальный скрол
        scroll = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        // Добавление элементов к панели
        panel.add( textField );
        panel.add( scroll );
        panel.add( jbutton );

        // подписываемся на события клика по кнопке отправить
        jbutton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = textField.getText();
                controller.sendMessage( message );
            }
        });

        // подписываемся на события нажатия кнопки Enter
        textField.addKeyListener( new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                Integer key_code = e.getKeyCode();

                if ( key_code == 10 ) {
                    String message = textField.getText();
                    controller.sendMessage( message );
                }
            }
        });

        // подписываемся на события закрытия окна
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                super.windowClosing( e );
                controller.closeConnection();
            }
        });

        // Добавляем панель к окну
        add( panel );
        setVisible( true );
    }

    /**
     * insertMessage - вставить сообщение пользователя в
     * текстовую область для отображения переписки
     *
     * @param text - текст сообщения
     */
    public void insertMessage ( String text )  {
        textField.setText( "" );
        textArea.setText( allMessages += text + "\n" );
    }
}
