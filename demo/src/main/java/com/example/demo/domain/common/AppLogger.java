package com.example.demo.domain.common;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AppLogger {
    private static MessageSource appLog;

    public AppLogger(MessageSource appLog) {
        AppLogger.appLog = appLog;
    }

    public static String readAppLogMsg(String code, Object... args) {
        return appLog.getMessage(code, args, Locale.JAPANESE);
    }
}
