package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClientController -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.client
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class ClientController implements IController {

    /**
     *  @access private
     *  @var String SERVER_ADDR
     */
    private final static String SERVER_ADDR = "localhost";

    /**
     *  @access private
     *  @var integer SERVER_PORT
     */
    private final static int SERVER_PORT = 8189;

    /**
     *  @access private
     *  @var Socket sock
     */
    private Socket sock;

    /**
     *  @access private
     *  @var Scanner in
     */
    private Scanner in;

    /**
     *  @access private
     *  @var PrintWriter out
     */
    private PrintWriter out;

    /**
     *  @access private
     *  @var Messenger messenger
     */
    private Messenger messenger;

    /**
     * constructor
     *
     */
    public ClientController() {
        initConnection();
    }

    /**
     * initConnection
     *
     * @access private
     */
    private void initConnection() {
        try {
            sock = new Socket( SERVER_ADDR, SERVER_PORT );
            in   = new Scanner( sock.getInputStream() );
            out  = new PrintWriter( sock.getOutputStream(), true );
            messenger = new Messenger( this );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while ( true ) {
                    if ( in.hasNext() ) {

                        String str = in.nextLine();

                        if ( str.startsWith( "end session" ) ) {
                            break;
                        }

                        messenger.insertMessage( str );
                    }
                }
            }
            catch ( Exception e ) {}
        }).start();
    }

    @Override
    public void sendMessage( String msg ) {
        out.println( msg );
    }

    @Override
    public void closeConnection() {
        try {
            sendMessage("/exit" );
            sock.close();
            out.close();
            in.close();
        }
        catch ( IOException exc ) {}
    }
}
