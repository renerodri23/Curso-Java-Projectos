module org.rpalacios.javaxf.app.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.webflux;
    requires reactor.core;
    requires java.net.http;
    requires  com.fasterxml.jackson.databind;


    opens org.rpalacios.javaxf.app.javafxapp to javafx.fxml;
    opens org.rpalacios.javaxf.app.javafxapp.models to javafx.base,com.fasterxml.jackson.databind;

    exports org.rpalacios.javaxf.app.javafxapp;
}