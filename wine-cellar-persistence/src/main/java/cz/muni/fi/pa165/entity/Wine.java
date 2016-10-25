package cz.muni.fi.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date vintage;
    private String predicate;
    private String predicateEquivalent;
    private String description;
    private String notes;
    private Double alcoholVolume;
    private Double residualSugar;
    private Double acidity;
    private Double GrapeSugarContent;

}
