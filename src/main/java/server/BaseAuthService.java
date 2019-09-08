package server;

import java.util.ArrayList;

/**
 * BaseAuthService - базовый класс авторизации
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class BaseAuthService implements IAuthService {

    /**
     *  @access private
     *  @var String login
     */
    private ArrayList<AuthEntry> entries;

    /**
     * constructor
     */
    public BaseAuthService() {

        entries = new ArrayList<>();
        entries.add( new AuthEntry("Ivan", "pass1", "nickIvan" ) );
        entries.add( new AuthEntry("Vasya","pass2", "nickVasya" ) );
        entries.add( new AuthEntry("Petr", "pass3", "nickPetr" ) );
    }

    @Override
    public String getLoginByLoginPass( String login, String pass ) {

        for ( AuthEntry o : entries ) {
            if (o.getLogin().equals( login ) && o.getPass().equals( pass ) ) {
                return o.getLogin();
            }
        }

        return null;
    }
}
