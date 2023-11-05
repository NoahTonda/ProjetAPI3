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
 * @see SessionCours
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "APILOCAL", schema = "ORA11", catalog = "orcl.condorcet.be")

public class Local {

    /**
     * identifiant unique-numéro de local
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_generator")
    @SequenceGenerator(name="local_generator", sequenceName = "APILOCAL_SEQ", allocationSize=1)
    @Column(name = "id_local")
    private Integer id;
    /**
     * sigle du local
     */
    @NonNull
    private String sigle;
    /**
     * nombre de places maximum disponibles dans le local
     */
    @NonNull
    private int places;
    /**
     * Liste des sessions de cours ayant lieu dans ce local
     */
    @JsonIgnore
    @OneToMany(mappedBy = "local")

    @ToString.Exclude

    private List<SessionCours> sessions=new ArrayList<>();

    /**
     * methode permettant d'afficher un local
     * @return les informations du local
     */
    @Override
    public String toString() {
        return "ID :"+id+", sigle :'" + sigle +"'"+ ", places :" + places+"\n";
    }
}
