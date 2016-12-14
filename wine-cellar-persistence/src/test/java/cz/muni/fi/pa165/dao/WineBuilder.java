package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Wine;

import java.math.BigDecimal;

/**
 * @author MarekScholtz
 * @version 2016.10.31
 */
public class WineBuilder {

    private String name;
    private int vintage;
    private String batch;
    private String predicate;
    private String predicateEquivalent;
    private String description;
    private String notes;
    private BigDecimal alcoholVolume;
    private BigDecimal residualSugar;
    private BigDecimal acidity;
    private BigDecimal grapeSugarContent;

    public WineBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public WineBuilder vintage(int vintage) {
        this.vintage = vintage;
        return this;
    }

    public WineBuilder batch(String batch) {
        this.batch = batch;
        return this;
    }

    public WineBuilder predicate(String predicate) {
        this.predicate = predicate;
        return this;
    }

    public WineBuilder predicateEquivalent(String predicateEquivalent) {
        this.predicateEquivalent = predicateEquivalent;
        return this;
    }

    public WineBuilder description(String description) {
        this.description = description;
        return this;
    }

    public WineBuilder notes(String notes) {
        this.notes = notes;
        return this;
    }

    public WineBuilder alcoholVolume(BigDecimal alcoholVolume) {
        this.alcoholVolume = alcoholVolume;
        return this;
    }

    public WineBuilder residualSugar(BigDecimal residualSugar) {
        this.residualSugar = residualSugar;
        return this;
    }

    public WineBuilder acidity(BigDecimal acidity) {
        this.acidity = acidity;
        return this;
    }

    public WineBuilder grapeSugarContent(BigDecimal grapeSugarContent) {
        this.grapeSugarContent = grapeSugarContent;
        return this;
    }

    public Wine build() {
        Wine wine = new Wine();
        wine.setName(name);
        wine.setVintage(vintage);
        wine.setBatch(batch);
        wine.setPredicate(predicate);
        wine.setPredicateEquivalent(predicateEquivalent);
        wine.setDescription(description);
        wine.setNotes(notes);
        wine.setAlcoholVolume(alcoholVolume);
        wine.setResidualSugar(residualSugar);
        wine.setAcidity(acidity);
        wine.setGrapeSugarContent(grapeSugarContent);
        return wine;
    }
}