package server;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientList - класс представляющий список клиентов с которым работает сервер
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.client
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class ClientList {

    /**
     *  @access private
     *  @var ClientHandler clients
     */
    private List<ClientHandler> clients = new ArrayList<>();

    /**
     * constructor
     *
     */
    public ClientList() {

    }

    /**
     * add - добавить клиента
     *
     * @access public
     * @param  client_handler
     */
    public void add( ClientHandler client_handler ) {
        clients.add( client_handler );
    }

    /**
     * remove - удалить клиента
     *
     * @access public
     * @param  client_handler
     */
    public void remove( ClientHandler client_handler ) {
        clients.remove( client_handler );
    }

    /**
     * get - получить список клиентов
     *
     * @access public
     */
    public List<ClientHandler> get() {
        return clients;
    }
}
