package main.Reto1;

public enum DataType {
    HTML("text/html"),
    JS("text/javascript"),
    JPEG("image/jpeg"),
    JPG("image/jpg"),
    PNG("image/png"),
    CSS("text/css");

    public final String value;

    DataType(String value) {
        this.value = value;
    }
}
