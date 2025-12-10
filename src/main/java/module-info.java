module GUI {
    opens view to java.desktop;

    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
}