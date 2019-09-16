package logger;

import java.util.List;

/**
 * JsonServerLogger - класс реализующий логирование приложения с использованием формата JSON
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class JsonServerLogger extends BaseLogger implements IServerLogger {

    /**
     * constructor
     */
    public JsonServerLogger() {
        super();
    }

    @Override
    public boolean writeLog(String msg) {
        this.writeMessageToFile( msg );
        return true;
    }

    @Override
    public List<String> readLog() {
        return null;
    }
}
