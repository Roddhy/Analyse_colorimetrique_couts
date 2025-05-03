package services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import model.Fichier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Stateless
@LocalBean
public class FichierPdfService {
    private static final String UPLOAD_DIR = "uploads";

    @PersistenceContext
    EntityManager em;

    public List<Fichier> getAllField(){
      List<Fichier> fichierpdfList =em.createNamedQuery("F.getAllField",Fichier.class).getResultList();
      if(fichierpdfList.isEmpty()){
          return null;
      }
      return fichierpdfList;
    }

    public Fichier findFieldById(int id){
        List <Fichier> fichierpdfList = em.createNamedQuery("F.FindFieldById",Fichier.class)
                .setParameter("id",id).getResultList();
        if(fichierpdfList.isEmpty()){
            return null;
        }
        return fichierpdfList.get(0);
    }

    public String dateAnalayse(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     return currentDate.format(formatter);
    }
    public String heureAnalayse(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }

    public String chemin(File file) throws IOException {
        String fileName = file.getName();  // Utilisation du nom du fichier du File
        // Utiliser un répertoire externe pour le stockage
        String cheminDossierExterne = System.getProperty("user.home"); // Exemple avec le répertoire utilisateur
        Path dossierPath = Paths.get(cheminDossierExterne, "uploads");
        if (!Files.exists(dossierPath)) {
            try {
                Files.createDirectories(dossierPath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la création du dossier : " + e.getMessage());
            }
        }

        // Chemin où le fichier sera copié
        Path filePath = dossierPath.resolve(fileName);

        try (InputStream inputStream = new FileInputStream(file)) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Retourne le chemin relatif à l'intérieur du projet (par exemple "uploads/monfichier.pdf")
        return dossierPath + File.separator + fileName;
    }

}
