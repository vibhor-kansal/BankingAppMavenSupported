package com.capgemini;

import com.capgemini.initializer.AppInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AppInitializer appInitializer = applicationContext.getBean(AppInitializer.class);
        appInitializer.showMenu();
    }
}