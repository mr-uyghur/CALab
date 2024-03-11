package mvc;

import java.io.*;

public class Model extends Publisher implements Serializable {
    protected boolean unsavedChanges = false;
    protected String fileName = null;

    public void changed() {
        unsavedChanges = true;
        notifySubscribers("Changes", null, null);
    }

    public boolean getUnsavedChanges() { return unsavedChanges; }
    public void setUnsavedChanges(boolean unsavedChanges) { this.unsavedChanges = unsavedChanges; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
}
