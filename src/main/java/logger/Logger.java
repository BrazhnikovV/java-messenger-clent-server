package logger;

import java.io.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Logger - класс реализующий логирование приложения
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Logger {

    /**
     *  @access private
     *  @var DataOutputStream out
     */
    private DataOutputStream out = null;

    /**
     *  @access private
     *  @var DataInputStream in
     */
    private DataInputStream in = null;

    /**
     *  @access private
     *  @var List<String> massagesList
     */
    private List<String> massagesList = new ArrayList<>();

    /**
     *  @access private
     *  @var String logName
     */
    private final String logName = "log.txt";

    /**
     * constructor
     */
    public Logger () {

    }

    /**
     * writeLog - клиенткая функция для записи в лог
     *
     * @access public
     * @param msg -
     * @return boolean
     */
    public boolean writeLog ( String msg ) {
        this.writeMessageToFile( msg );
        return true;
    }

    /**
     * readLog - клиентмкая функция чтения лога
     *
     * @access public
     * @return List<String>
     */
    public List<String> readLog () {
        this.readMessagesFormFile();

        // конвертируем ArrayList в массив
        String[] arrMessages = this.massagesList.toArray( new String[this.massagesList.size()] );

        String[] localArray = new String[0];
        if ( arrMessages.length < 10 ) {
            localArray = this.copyPartArray( arrMessages, 10 - arrMessages.length );
        }
        else {
            localArray = this.copyPartArray( arrMessages, ( arrMessages.length - 10 ) );
        }

        this.massagesList = new ArrayList( Arrays.asList( localArray ) );

        return this.massagesList;
    }

    /**
     * writeMessageToFile - записать сообщение в лог файл
     *
     * @access private
     * @param msg - текстовое сообщение
     * @return void
     */
    private void writeMessageToFile ( String msg ) {
        try {
            out = new DataOutputStream( new FileOutputStream( this.logName, true ) );
            out.writeUTF( msg + "\n" );
            out.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * readMessagesFormFile - получить все сообщения из лог файла и сохранить их
     *
     * @access private
     * @return
     */
    private void readMessagesFormFile() {
        try {
            in = new DataInputStream( new FileInputStream( this.logName ) );

            while ( in.available() > 0 ) {
                massagesList.add( in.readUTF() );
            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * copyPartArray - получить часть массива
     *
     * @access private
     * @param  a -
     * @param  start - индекс массива начиная с
     *               которого отрезается часть исходного массива
     * @return String[]
     */
    private String[] copyPartArray ( String[] a, int start ) {
        if ( a == null ) {
            return null;
        }
        if (start > a.length) {
            return null;
        }
        String [] r = new String[a.length - start];
        System.arraycopy(a, start, r, 0, a.length - start);
        return r;
    }
}
