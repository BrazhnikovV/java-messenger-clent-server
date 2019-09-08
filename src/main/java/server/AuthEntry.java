package server;

/**
 * AuthEntry -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class AuthEntry {

    /**
     *  @access private
     *  @var String login
     */
    private String login;

    /**
     *  @access private
     *  @var String pass
     */
    private String pass;

    /**
     *  @access private
     *  @var String nick
     */
    private String nick;

    /**
     * constructor
     */
    public AuthEntry( String login, String pass, String nick ) {
        this.login = login;
        this.pass = pass;
        this.nick = nick;
    }

    /**
     * getLogin - получить логин
     * @return String
     */
    public String getLogin() {
        return login;
    }

    /**
     * getPass - получить пароль
     * @return String
     */
    public String getPass() {
        return pass;
    }

    /**
     * getNick - получить никнэйм
     * @return String
     */
    public String getNick() {
        return nick;
    }
}
