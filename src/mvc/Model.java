package mvc;

import java.io.*;

public class Model extends Publisher implements Serializable {
    protected boolean unsavedChanges = false;
    protected String fileName = null;

    public void changed() {
        unsavedChanges = true;
    }
}
