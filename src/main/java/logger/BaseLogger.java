package logger;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * BaseLogger - базовый класс для логгеров
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class BaseLogger {
    /**
     *  @access protected
     *  @var DataOutputStream out
     */
    protected DataOutputStream out = null;

    /**
     *  @access protected
     *  @var DataInputStream in
     */
    protected DataInputStream in = null;

    /**
     *  @access protected
     *  @var List<String> massagesList
     */
    protected List<String> massagesList = new ArrayList<>();

    /**
     *  @access private
     *  @var String logName
     */
    protected final String logName = "log.txt";

    /**
     * constructor
     */
    public BaseLogger() {}

    /**
     * writeMessageToFile - записать сообщение в лог файл
     *
     * @access protected
     * @param msg - текстовое сообщение
     * @return void
     */
    protected void writeMessageToFile ( String msg ) {
        try {
            this.out = new DataOutputStream( new FileOutputStream( this.logName, true ) );
            this.out.writeUTF( msg + "\n" );
            this.out.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * readMessagesFormFile - получить все сообщения из лог файла и сохранить их
     *
     * @access protected
     * @return void
     */
    protected void readMessagesFormFile() {
        try {
            this.in = new DataInputStream( new FileInputStream( this.logName ) );

            while ( this.in.available() > 0 ) {
                this.massagesList.add( in.readUTF() );
            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
