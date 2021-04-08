package BusPackage;

import com.squareup.otto.Bus;

public class OttoBus extends Bus {
    private volatile static OttoBus ottoBus;

    private OttoBus() {

    }

    public static OttoBus getInstance() {
        if (ottoBus == null) {
            synchronized (OttoBus.class) {
                if (ottoBus == null) {
                    ottoBus = new OttoBus();
                }
            }
        }
        return ottoBus;
    }
}
