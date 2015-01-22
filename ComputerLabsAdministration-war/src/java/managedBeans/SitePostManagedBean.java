/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.SitePost;
import helperClasses.JsfUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sessionBeans.ComputerLabsFacade;
import sessionBeans.SitePostFacade;
import sessionBeans.UsersFacade;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class SitePostManagedBean implements Serializable {

    //injection of EJBs used in the class
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private ComputerLabsFacade computerLabsFacade;
    @EJB
    private SitePostFacade sitePostFacade;

    //declaration of private variables
    Part file;
    private SitePost post;
    private int labId;

    //base path where the uploaded files will be stored to
    private final static String UPLOAD_BASE_PATH = "C:" + File.separator + "uploads" + File.separator;

    //getters and setters for private variables
    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public SitePost getPost() {
        return post;
    }

    public void setPost(SitePost post) {
        this.post = post;
    }

    /**
     * Creates a new instance of SitePostManagedBean
     */
    public SitePostManagedBean() {
    }

    /**
     * Initialisation of private variables;
     */
    @PostConstruct
    public void init() {
        post = new SitePost();
    }

    public List<String> listAllUploadedFiles(){
         File directory = new File(UPLOAD_BASE_PATH);
         List<String> fileNames=new ArrayList<>();

    // get all the files from a directory
    File[] fList = directory.listFiles();
    for (File f : fList) {
        if (f.isFile()) {
           fileNames.add(f.getName());
        } 
    }
    return fileNames;
    }
    
    /**
     * Method saves uploaded file to local disk.
     * @throws IOException
     */
    public void uploadFile() throws IOException {

        // Extract file name from content-disposition header of file part
        if (file!=null){
        String fileName = file.getSubmittedFileName();
        System.out.println("***** fileName: " + fileName);

        File outputFilePath = new File(UPLOAD_BASE_PATH + fileName);

        // Copy uploaded file to destination path
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);

            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

//			statusMessage = "File upload successfull !!";
        } catch (IOException e) {
            e.printStackTrace();
//			statusMessage = "File upload failed !!";
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        }

    }
    
    /**
     * Method persists the SitePost object into the DB.
     */
    public void publishPost() {
        try {
            uploadFile();
        } catch (IOException ex) {
            Logger.getLogger(SitePostManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        post.setSubmissionDate(new Date());
        post.setLabId(computerLabsFacade.find(labId));
        if (file!=null){
        post.setAttachmentFileName(file.getSubmittedFileName());}
        post.setSubmittedBy(usersFacade.getUserByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()));
        sitePostFacade.create(post);
        JsfUtil.addSuccessMessage("The post has been successfully added to the Lab news page.");
        post = new SitePost();
    }

    /**
     * Counts all the posts for a specific computer lab.
     * @return 
     */
    public List<Integer> countLabsWithPosts() {
        return sitePostFacade.countPostsByLabId();
    }

    /**
     * Fetches records from the SitePost which belong to the specified computerlab.
     * @param computerLabId - id of the computerLab stored in the DB
     * @return 
     */
    public List<SitePost> postsByLabId(int computerLabId) {
        return sitePostFacade.getPostsByLabId(computerLabId);
    }

}
