package cz.muni.fi.pa165.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Year;

/**
 * @author Michaela Bamburová on 16.12.2016.
 */
public class WineCreateDto {

    @NotNull
    private String name;

    @NotNull
    private Year vintage;

    @NotNull
    private String batch;

    private String predicate;

    private String predicateEquivalent;

    private String description;

    private String notes;

    @Min(0)
    @Max(100)
    private BigDecimal alcoholVolume;

    @Min(0)
    @Max(100)
    private BigDecimal residualSugar;

    @Min(0)
    @Max(100)
    private BigDecimal acidity;

    @Min(0)
    @Max(100)
    private BigDecimal grapeSugarContent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getVintage() {
        return vintage;
    }

    public void setVintage(Year vintage) {
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
        if (!(o instanceof WineCreateDto)) return false;

        WineCreateDto that = (WineCreateDto) o;

        return getBatch().equals(that.getBatch());
    }

    @Override
    public int hashCode() {
        return getBatch().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }
}
