package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michaela Bamburová on 25.10.2016.
 */
@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "WINE_ID")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int vintage;

    @NotNull
    private String batch;

    private String predicate;

    private String predicateEquivalent;

    private String description;

    private String notes;

    @DecimalMin("0.0")
    private BigDecimal alcoholVolume;

    @DecimalMin("0.0")
    private BigDecimal residualSugar;

    @DecimalMin("0.0")
    private BigDecimal acidity;

    @DecimalMin("0.0")
    private BigDecimal grapeSugarContent;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "wines")
    private List<WineList> wineLists = new ArrayList<>();

    public Wine(Long id) {
        this.id = id;
    }

    public Wine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVintage() {
        return vintage;
    }

    public void setVintage(int vintage) {
        this.vintage = vintage;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getPredicateEquivalent() {
        return predicateEquivalent;
    }

    public void setPredicateEquivalent(String predicateEquivalent) {
        this.predicateEquivalent = predicateEquivalent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getAlcoholVolume() {
        return alcoholVolume;
    }

    public void setAlcoholVolume(BigDecimal alcoholVolume) {
        this.alcoholVolume = alcoholVolume;
    }

    public BigDecimal getResidualSugar() {
        return residualSugar;
    }

    public void setResidualSugar(BigDecimal residualSugar) {
        this.residualSugar = residualSugar;
    }

    public BigDecimal getAcidity() {
        return acidity;
    }

    public void setAcidity(BigDecimal acidity) {
        this.acidity = acidity;
    }

    public BigDecimal getGrapeSugarContent() {
        return grapeSugarContent;
    }

    public void setGrapeSugarContent(BigDecimal grapeSugarContent) {
        this.grapeSugarContent = grapeSugarContent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wine)) return false;

        Wine wine = (Wine) o;

        return getId() != null && getId().equals(wine.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
