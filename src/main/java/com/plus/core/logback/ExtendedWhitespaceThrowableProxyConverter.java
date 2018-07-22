package com.plus.core.logback;

import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;

/**
 * @author plus me
 * @date 2018/6/29
 */
public class ExtendedWhitespaceThrowableProxyConverter
        extends ExtendedThrowableProxyConverter {

    @Override
    protected String throwableProxyToString(IThrowableProxy tp) {
        return CoreConstants.LINE_SEPARATOR + super.throwableProxyToString(tp)
                + CoreConstants.LINE_SEPARATOR;
    }

}