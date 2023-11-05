package be.condorcet.projet.modele;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author noaht
 * @version 1.0
 * @see Cours
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APISESSIONCOURS", schema = "ORA11", catalog = "ORCL.CONDORCET.BE")

public class SessionCours {

    /**
     * identifiant unique-numéro de la session de cours
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessioncours_generator")
    @SequenceGenerator(name="sessioncours_generator", sequenceName = "APISESSIONCOURS_SEQ", allocationSize=1)

    @Column(name="ID_SESSIONCOURS")
    private Integer id;
    /**
     * date de la première session de cours
     */
    @NonNull
    @Column(name="DATEDEBUT")
    private Date dateDebut;
    /**
     * date de la dernière session de cours
     */
    @NonNull
    @Column(name="DATEFIN")
    private Date dateFin;
    /**
     * nombre d'élèves inscrits au cours
     */
    @NonNull
    @Column(name="NBREINSCRITS")
    private int nbreInscrits;
    /**
     * Le cours donné lors des sessions
     */
    @NonNull
    @ManyToOne
    @JoinColumn(name = "ID_COURS")

    private Cours cours;



    /**
     * Le formateur qui donne le cours
     */
    @NonNull
    @ManyToOne
    @JoinColumn(name = "ID_FORMATEUR")

    private Formateur formateur;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "ID_LOCAL")

    private Local local;

    /**
     * methode permettant d'afficher une session de cours
     * @return les informations de la session de cours
     */
    @Override
    public String toString() {
        return "ID session :"+id+"\ncours :" + cours + "dateDebut :" + dateDebut + ", dateFin :" + dateFin + ", nbreInscrits :" + nbreInscrits +
                "\nformateur = " + formateur;
    }
}
