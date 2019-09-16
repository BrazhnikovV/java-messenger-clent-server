package logger;

/**
 * LoggerFactory - интерфейс Абстрактная фабрика
 * знает обо всех абстрактных типах логгеров
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public interface LoggerFactory {

    /**
     * createClientLog - создать логгер для клиентской части
     * @access public
     * @return IClientLogger
     */
    IClientLogger createClientLog();

    /**
     * createServerLog - создать логгер для серверной части
     * @access public
     * @return IServerLogger
     */
    IServerLogger createServerLog();
}
