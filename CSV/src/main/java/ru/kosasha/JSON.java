package ru.kosasha;

import java.io.IOException;

public interface JSON {
    String toJSON(String address_to) throws IOException;
    void fromJSON(String address_from) throws IOException;
}
