package logger;

import java.util.List;

/**
 * JsonClientLogger - класс реализующий логирование приложения с использованием формата JSON
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class JsonClientLogger extends BaseLogger implements IClientLogger {

    /**
     * constructor
     */
    public JsonClientLogger() { super(); }

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
