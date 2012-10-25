/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.conf;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author martin
 */
public class ConfigApp {
    
    private static final Logger LOG = Logger.getLogger(ConfigApp.class.getName());    
    
    /**
     * carpeta en el file system donde se encuentran los recursos estáticos
     */
    private final String staticResourceFolders;
    
    private final Integer pageSize ;
    
    
    public ConfigApp(Integer pageSize) {
        this.pageSize = pageSize ;
        this.staticResourceFolders =  getStaticResourceFoldersAux();
        
    }
    
    private String getStaticResourceFoldersAux(){
        
        /*URL folderEstatico = Thread.currentThread().getContextClassLoader().getResource("/");
        String pathStatic = "";
        File file = new File(folderEstatico.getPath());
        File parentFile = file.getParentFile().getParentFile();
        File[] listFiles = parentFile.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            File file1 = listFiles[i];
            if (file1.getAbsolutePath().indexOf("estatico")>0) {
                pathStatic=file1.getAbsolutePath();
            }
            
        } */
        
        //------------
        String pathStatic = "dummy";
        /*
        URL folderEstatico = Thread.currentThread().getContextClassLoader().getResource("nodelete.txt");
        LOG.log(Level.FINE, "path of the file: {0}", folderEstatico.getPath());
        System.out.println("path of the file: "+folderEstatico.getPath());
        File file = new File(folderEstatico.getPath());
        LOG.log(Level.FINE, "Searching form: -1 {0}",file.getParentFile().getParentFile().getAbsolutePath());
        System.out.println("Searching form: -1 "+file.getParentFile().getParentFile().getAbsolutePath());
        File parentFile = file.getParentFile().getParentFile().getParentFile();
        File[] listFiles = parentFile.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            File file1 = listFiles[i];
            LOG.log(Level.FINE, "Path iter: {0}",file1.getAbsolutePath());
            System.out.println("Path iter: "+file1.getAbsolutePath());
            if (file1.getAbsolutePath().indexOf("rep")>0) {
                pathStatic=file1.getAbsolutePath();
                LOG.log(Level.FINE, "Path selected: {0}",file1.getAbsolutePath());
                System.out.println("Path selected: "+file1.getAbsolutePath());
            }
            
        } */        
        return pathStatic;
    }   

    public String getStaticResourceFolders() {
        return staticResourceFolders;
    }

    public Integer getPageSize() {
        return pageSize;
    }
    
    
    
}
