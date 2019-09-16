package logger;

/**
 * JsonLoggerFactory - Каждая конкретная фабрика знает и создаёт только продукты своей вариации.
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class JsonLoggerFactory implements LoggerFactory {

    @Override
    public IClientLogger createClientLog() {
        return new JsonClientLogger();
    }

    @Override
    public IServerLogger createServerLog() {
        return new JsonServerLogger();
    }
}
