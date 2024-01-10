package be.condorcet.projet.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author noaht
 * @version 1.0
 * @see SessionCours
 */
@Data
@AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor
@ToString
@Entity
@Table(name = "APICOURS", schema = "ORA11", catalog = "orcl.condorcet.be")

public class Cours {


    /**
    * id du cours
    */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cours_generator")
    @SequenceGenerator(name="cours_generator", sequenceName = "APICOURS_SEQ", allocationSize=1)
    @Column(name = "id_cours")
    private Integer id;
    /**
     * nom de la matière
     */
    @NonNull
    @Column(name = "matière")

    private String matiere;
    /**
     * nombre d'heures de cette matière données
     */

    @NonNull
    @Column(name = "NBREHEURES")
    private int nbreHeures;
    /**
     * liste des sessions de ce cours
     */
    @JsonIgnore
    @OneToMany(mappedBy = "cours")

    @ToString.Exclude
    private List<SessionCours> session;

    public Cours(int id) {
        // Initialisez l'instance avec l'ID
        this.id = id;
    }
    /**
     * methode permettant d'afficher un cours
     * @return les informations du cours
     */
    @Override
    public String toString() {
        return "ID :"+id+", matiere :'" + matiere +"'"+ ", nombre d'heures de cours :" + nbreHeures+"\n";

    }
}
