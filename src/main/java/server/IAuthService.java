package server;

/**
 * IAuthService - интерфейс базового класса авторизации
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public interface IAuthService {

    //void start();

    String getLoginByLoginPass(String login, String pass);

    //void stop();

}
