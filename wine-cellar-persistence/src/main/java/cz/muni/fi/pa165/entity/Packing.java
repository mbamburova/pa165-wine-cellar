package cz.muni.fi.pa165.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Silvia Borzová
 *         25/10/2016
 */
@Entity
public class Packing {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double volume;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Currency currency;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
}
