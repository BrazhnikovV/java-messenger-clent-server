package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Server - класс реализующий сервер
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Server {

    /**
     *  @access private
     *  @var integer SERVER_PORT
     */
    private final static int SERVER_PORT = 8189;

    /**
     *  @access private
     *  @var ServerSocket server
     */
    private ServerSocket server;

    /**
     *  @access private
     *  @var ClientList clientList
     */
    private ClientList clientList;

    /**
     *  @access private
     *  @var AuthService authService
     */
    private IAuthService authService;

    /**
     *  @access private
     *  @var Logger logger
     */
    private Logger logger;

    /**
     * constructor
     */
    public Server( IAuthService authService ) {

        // инициализируем функционал логирования приложения
        this.logger = new Logger();

        this.authService = authService;

        try {
            server     = new ServerSocket( SERVER_PORT );
            clientList = new ClientList();
            System.out.println( "Сервер запущен, ожидаем подключения..." );
            checkСlientssLoyalty();
        }
        catch ( IOException e ) {
            System.out.println( "Ошибка инициализации сервера" );
            close();
        }
    }

    /**
     * main -
     * @param args -
     */
    public static void main( String[] args ) {
        IAuthService baseAuthService = new BaseAuthService();
        Server server = new Server( baseAuthService );
        server.start();
    }

    /**
     * subscribe - подписать нового пользователя в чате
     * @access public
     * @param clientHandler -
     */
    public synchronized void subscribe( ClientHandler clientHandler ) {
        clientList.add( clientHandler );
        sendBroadcastMessage( clientHandler.name, clientHandler.name + ": подключен!" );
        broadcastClientsList();
        sendPrivateMessage( clientHandler.name, this.logger.readLog() );
    }

    /**
     * unsubscribe - отписать отключившегося пользователя
     *
     * @access public
     * @param clientHandler -
     */
    public synchronized void unsubscribe( ClientHandler clientHandler ) {
        String msg = "Клиент " + clientHandler.name + " отключился";
        sendBroadcastMessage( clientHandler.name, msg );
        clientList.remove( clientHandler );
        broadcastClientsList();

        // получаем список клиентов
        List<ClientHandler> localClientList = clientList.get();
        if ( localClientList.size() == 0 ) {
            //close();
        }
    }

    /**
     * getAuthService - получить объект для выполнения
     * функционала авторизации пользователя
     *
     * @access public
     */
    public IAuthService getAuthService() {
        return this.authService;
    }

    /**
     * sendBroadcastMessage - реализация широкополосного оповещения клиентов
     *
     * @access public
     * @param name - имя
     * @param msg - текст сообщения
     */
    public synchronized void sendBroadcastMessage( String name, String msg ) {
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> localClientList = clientList.get();

        for ( ClientHandler client : localClientList ) {
            client.sendMessage( msg );
        }

        this.logger.writeLog( msg );
    }

    /**
     * broadcastClientsList - отправить список клиентов всем пользователям
     *
     * @access public
     */
    public synchronized void broadcastClientsList() {
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> localClientList = clientList.get();

        String clientList = "/clients";
        for ( ClientHandler client : localClientList ) {
            clientList += " " + client.name;
        }

        for ( ClientHandler client : localClientList ) {
            client.sendMessage( clientList );
        }
    }

    /**
     * sendPrivateMessage - реализация личного сообщения для клиента
     *
     * @access public
     * @param name - имя клиента
     * @param msg - текст сообщения
     */
    public  void sendPrivateMessage( String name, String msg ) {
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> localClientList = clientList.get();

        for ( ClientHandler client : localClientList ) {
            if ( name.trim().equals( client.name.trim() ) ) {
                client.sendMessage( msg );
            }
        }
    }

    /**
     * sendPrivateMessage - отправить личное сообщение
     *
     * @access public
     * @return List<String>
     */
    private void sendPrivateMessage( String name, List<String> messages ) {

        // получаем список клиентов для обхода в цикле
        List<ClientHandler> localClientList = clientList.get();

        for ( ClientHandler client : localClientList ) {
            if ( name.trim().equals( client.name.trim() ) ) {
                for ( String msg : messages ) {
                    client.sendMessage( msg );
                }
            }
        }
    }

    /**
     * close - закрыть
     *
     * @access private
     */
    private void close() {
        try {
            server.close();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        System.out.println( "Сервер остановлен." );
        System.exit(0 );
    }

    /**
     * start - Запустить сервер
     *
     * @access private
     */
    private void start() {
        while ( true ) {
            try {
                Socket client_socket = server.accept();
                ClientHandler clientHandler = new ClientHandler( client_socket, this );
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * checkСlientssLoyalty - запускает реализацию setInterval
     * для опроса клиентов на предмет их активности
     *
     * @access private
     */
    private synchronized void checkСlientssLoyalty() {
        new Thread(() -> {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    // получаем список клиентов для обхода в цикле
                    List<ClientHandler> localClientList = clientList.get();

                    for ( ClientHandler client : localClientList ) {
                        long time_act = client.getLastActionTime();
                        long time_now = System.currentTimeMillis();
                        long difference = time_now - time_act;

                        if ( difference > 120000 ) {
                            unsubscribe( client );
                        }
                    }
                }
            };
            timer.schedule( task, 0L ,1000L );
        }).start();
    }
}
