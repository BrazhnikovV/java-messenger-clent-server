package logger;

import java.util.List;

/**
 * XmlClientLogger - класс реализующий логирование приложения с использованеим формата XML
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class XmlClientLogger extends BaseLogger implements IClientLogger {

    /**
     * constructor
     */
    public XmlClientLogger() { super(); }

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
