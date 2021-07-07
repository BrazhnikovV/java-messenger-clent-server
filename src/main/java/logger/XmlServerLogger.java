package logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XmlServerLogger - класс реализующий логирование приложения с использованеим формата XML
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class XmlServerLogger extends BaseLogger implements IServerLogger {

    /**
     * constructor
     */
    public XmlServerLogger() {
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
