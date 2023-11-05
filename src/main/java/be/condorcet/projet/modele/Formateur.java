package be.condorcet.projet.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * @author noaht
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIFORMATEUR", schema = "ORA11", catalog = "ORCL.CONDORCET.BE")

public class Formateur {


    /**
     * identifiant unique numero de formateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formateur_generator")
    @SequenceGenerator(name="formateur_generator", sequenceName = "APIFORMATEUR_SEQ", allocationSize=1)
    @Column(name = "id_formateur")
    private Integer id;
    /**
     * mail unique du formateur
     */
    @NonNull
    private String mail;
    /**
     * nom du formateur
     */
    @NonNull
    private String nom;
    /**
     * prénom du formateur
     */
    @NonNull
    private String prenom;
    /**
     * liste des sessions de cours données par le formateur
     */
    @JsonIgnore
    @OneToMany(mappedBy = "formateur")

    @ToString.Exclude
    private List<SessionCours> session=new ArrayList<>();


    /**
     * methode permettant d'afficher un formateur
     * @return les informations du formateur
     */
    @Override
    public String toString() {
        return "id :"+ id+", nom :'" + nom +"'"+ ", prenom :'" + prenom +"'"+ ", mail :'" + mail+"'\n";
    }
}
