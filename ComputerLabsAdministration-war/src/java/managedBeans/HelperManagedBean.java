/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author t.valkovic
 */
@ManagedBean
@SessionScoped
public class HelperManagedBean implements Serializable {

     public void handleFileUpload(FileUploadEvent event) {
        try {
            File targetFolder = new File("/uploads/labMaterials/");
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates a new instance of HelperManagedBean
     */
    public HelperManagedBean() {
    }
    
}
