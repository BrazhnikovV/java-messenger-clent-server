package client;

import lombok.NoArgsConstructor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

/**
 * Messenger - класс для представления UI мессенджера
 *
 * @version 1.0.1
 * @package client
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
@NoArgsConstructor
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
     * Messenger - конструктор
     * @param controller - контракт клиентского контроллера
     */
    public Messenger( IController controller ) {

        MessengerBuilder messengerBuilder = new MessengerBuilder();
        messengerBuilder
                .withBaseOptions( "Месенджер", this.windowWidth, this.windowHeight, false )
                .withTextArea()
                .withScrollHorizontal()
                .withActionListeners( controller ).build();
    }

    /**
     * insertMessage - вставить сообщение пользователя в
     * текстовую область для отображения переписки
     *
     * @param text - текст сообщения
     */
    public void insertMessage ( String text )  {
        this.textField.setText( "" );
        this.textArea.setText( this.allMessages += text + "\n" );
    }

    /**
     * MessengerBuilder - внутренний класс
     * для сборки мессанджера из компонентов
     */
    public class MessengerBuilder {

        /**
         *  @access private
         *  @var Messenger messenger
         */
        private Messenger messenger;

        /**
         * MessengerBuilder - конструктор
         */
        public MessengerBuilder() {
            this.messenger = new Messenger();
        }

        /**
         * withBaseOptions - построить базовые настройки для месенджера
         * @param title        - титульный заголовок
         * @param windowWidth  - ширина окна
         * @param windowHeight - высота окна
         * @param isResize     - возможность ресайза
         * @return MessengerBuilder
         */
        public MessengerBuilder withBaseOptions( String title, int windowWidth, int windowHeight, boolean isResize ) {

            this.messenger.setTitle( title );
            this.messenger.setSize( windowWidth, windowHeight );
            this.messenger.setResizable( isResize );

            return this;
        }

        /**
         * withTextArea - Создает текстовую область для отображения переписки
         * @return MessengerBuilder
         */
        public MessengerBuilder withTextArea() {

            this.messenger.textArea.setBorder( new LineBorder( Color.GRAY, 1 ) );
            this.messenger.textArea.setEditable( false );
            this.messenger.textArea.setLineWrap( true );

            return this;
        }

        /**
         * withScrollHorizontal - добавляет горизонтальный скрол к полю textArea
         * @return MessengerBuilder
         */
        public MessengerBuilder withScrollHorizontal() {

            this.messenger.scroll = new JScrollPane(
                    this.messenger.textArea,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
            );

            return this;
        }

        /**
         * withActionListeners - здесь нужно подписаться
         * на необходимые действия целевых компонентов
         * @param controller - контракт клиентского контроллера
         * @return MessengerBuilder
         */
        public MessengerBuilder withActionListeners ( IController controller ) {

            // подписываемся на события клика по кнопке отправить
            this.messenger.jbutton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e ) {
                    String message = textField.getText();
                    controller.sendMessage( message );
                }
            });

            // подписываемся на события нажатия кнопки Enter
            this.messenger.textField.addKeyListener( new KeyAdapter() {
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
            this.messenger.addWindowListener( new WindowAdapter() {
                @Override
                public void windowClosing( WindowEvent e ) {
                    super.windowClosing( e );
                    controller.closeConnection();
                }
            });

            // подписываемся на событие клика по кнопки закрыть окна
            this.messenger.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

            return this;
        }

        /**
         * build -
         * @return Messenger
         */
        public Messenger build() {

            // Jpanel panel_top_buttons
            JPanel panel = new JPanel();
            panel.setBackground( Color.lightGray );
            // выбор компоновщика элементов
            panel.setLayout( new FlowLayout() );

            // Добавление элементов к панели
            panel.add( this.messenger.textField );
            panel.add( this.messenger.scroll );
            panel.add( this.messenger.jbutton );

            // Добавляем панель к окну
            this.messenger.add( panel );
            this.messenger.setVisible( true );

            return this.messenger;
        }
    }
}
