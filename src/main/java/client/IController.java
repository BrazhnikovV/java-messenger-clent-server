package client;

/**
 * IController - интерфейс для класса ClientController
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public interface IController {

    /**
     * sendMessage
     * @param msg - текст сообщения
     */
    void sendMessage(String msg);

    /**
     * closeConnection
     */
    void closeConnection();
}
