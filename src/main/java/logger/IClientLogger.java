package logger;

import java.util.List;

/**
 * IClientLogger - интерфейс
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public interface IClientLogger {

    /**
     * writeLog - клиенткая функция для записи в лог
     * @access public
     * @param msg
     * @return boolean
     */
    boolean writeLog( String msg );

    /**
     * readLog - клиентмкая функция чтения лога
     * @access public
     * @return List<String>
     */
    List<String> readLog();
}
