package com.smartrecruiters;

import com.smartrecruiters.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        new AppConfig().bootstrap();
    }
}
