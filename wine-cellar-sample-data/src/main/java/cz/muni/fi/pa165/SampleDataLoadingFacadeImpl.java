package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.UserRole;
import cz.muni.fi.pa165.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

/**
 * @author MarekScholtz
 * @version 2016.12.15
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Inject
    private WineService wineService;

    @Inject
    private MarketingEventService marketingEventService;

    @Inject
    private WineListService wineListService;

    @Inject
    private PackingService packingService;

    @Inject
    private PriceService priceService;

    @Inject
    private UserService userService;

    @Override
    @SuppressWarnings("unused")
    public void loadSampleData() throws IOException {

        Wine veltlinskeZelene = wine("Veltlínske zelené", Year.of(2014), "10/14", "kabinetní víno",
            "suché", "Elegantní, svěží víno s lehkou aromatikou angreštu a zeleného pepře." +
                " Chuťový vjem je tvořen pikantní kyselinkou a kořenito-ovocnými tóny.", "20,0°ČNM",
            new BigDecimal("10.94"), new BigDecimal("2.8"), new BigDecimal("7.5"), new BigDecimal("0"));

        Wine muskatMoravsky = wine("Muškát moravský", Year.of(2015), "1/14", "kabinetní víno",
            "suché", "Víno zlatavé barvy s ovocnou vůní citrusových plodů a muškátového oříšku." +
                " V chuti nabízí ovocné tóny grapefruitu a zralého citrónu. Ovocnou chuť provází příjemná kyselinka, " +
                "díky níž je víno pikantní se suchým závěrem.", "20,2°ČNM",
            new BigDecimal("12"), new BigDecimal("0.7"), new BigDecimal("6.1"), new BigDecimal("0"));

        Wine svatovavrinecke = wine("Svatovavřinecké", Year.of(2015), "6/14", "pozdní sběr",
            "suché", "Jiskrné víno rubínových odstínů barvy. Kořenitá vůně višní a třešňové kůry." +
                " Zabalená v nádechu kouře z dubového dřeva. Chuť charakterní pevná, v níž se snoubí tóny višní, svěží kyselinky a " +
                "příjemného třísla.", "30,2°ČNM",
            new BigDecimal("12"), new BigDecimal("6.2"), new BigDecimal("4.6"), new BigDecimal("0"));

        wineService.createWine(veltlinskeZelene);
        wineService.createWine(muskatMoravsky);
        wineService.createWine(svatovavrinecke);

        User user = user("User", "User", "user@wines.com",UserRole.MEMBER, "user123");
        User admin = user("Admin", "Admin", "admin@wines.com", UserRole.ADMIN, "admin123");

        MarketingEvent silvester = marketingEvent("silvester");
        MarketingEvent narodeniny = marketingEvent("narodeniny");
        MarketingEvent betatest = marketingEvent("betatest");

        marketingEventService.createMarketingEvent(silvester);
        marketingEventService.createMarketingEvent(narodeniny);
        marketingEventService.createMarketingEvent(betatest);

        WineList wineList1 = wineList("Svätomartinská ochutnávka", LocalDateTime.of(2016,12,31,22,0),
                Arrays.asList(veltlinskeZelene, muskatMoravsky, svatovavrinecke), silvester);
        WineList wineList2 = wineList("Degustace 09/2016", LocalDateTime.of(2017,1,25,0,0),
                Arrays.asList(veltlinskeZelene, svatovavrinecke), narodeniny);
        WineList wineList3 = wineList("Degustace 11/2016", LocalDateTime.of(2017,2,10,20,0),
                Arrays.asList(veltlinskeZelene, muskatMoravsky), betatest);

        wineListService.createWineList(wineList1);
        wineListService.createWineList(wineList2);
        wineListService.createWineList(wineList3);

        Packing veltlinskeZelene35 = packing(new BigDecimal("0.35"), LocalDateTime.of(2017,1,1,0,0),
            null, veltlinskeZelene);
        Packing veltlinskeZelene70 = packing(new BigDecimal("0.7"), LocalDateTime.of(2017,1,1,0,0),
            LocalDateTime.of(2017,1,31,0,0), veltlinskeZelene);
        Packing muskatMoravsky35 = packing(new BigDecimal("0.35"), LocalDateTime.of(2017,1,1,0,0),
            null, muskatMoravsky);
        Packing muskatMoravsky70 = packing(new BigDecimal("0.7"), LocalDateTime.of(2017,1,1,0,0),
            LocalDateTime.of(2017,1,31,0,0), muskatMoravsky);
        Packing svatovavrinecke35 = packing(new BigDecimal("0.35"), LocalDateTime.of(2017,1,1,0,0),
            null, svatovavrinecke);
        Packing svatovavrinecke70 = packing(new BigDecimal("0.7"), LocalDateTime.of(2017,1,1,0,0),
            LocalDateTime.of(2017,1,31,0,0), svatovavrinecke);

        packingService.createPacking(veltlinskeZelene35);
        packingService.createPacking(veltlinskeZelene70);
        packingService.createPacking(muskatMoravsky35);
        packingService.createPacking(muskatMoravsky70);
        packingService.createPacking(svatovavrinecke35);
        packingService.createPacking(svatovavrinecke70);

        Price veltlinskeZelene35CZK = price(new BigDecimal("50"), Currency.getInstance("CZK"), narodeniny, veltlinskeZelene35);
        Price veltlinskeZelene70CZK = price(new BigDecimal("100"), Currency.getInstance("CZK"), narodeniny, veltlinskeZelene70);
        Price veltlinskeZelene70EUR = price(new BigDecimal("2"), Currency.getInstance("EUR"), narodeniny, veltlinskeZelene70);

        Price muskatMoravsky35CZK = price(new BigDecimal("55"), Currency.getInstance("CZK"), narodeniny, muskatMoravsky35);
        Price muskatMoravsky70CZK = price(new BigDecimal("110"), Currency.getInstance("CZK"), narodeniny, muskatMoravsky70);
        Price muskatMoravsky70EUR = price(new BigDecimal("3"), Currency.getInstance("EUR"), narodeniny, muskatMoravsky70);

        Price svatovavrinecke35CZK = price(new BigDecimal("45"), Currency.getInstance("CZK"), narodeniny, svatovavrinecke35);
        Price svatovavrinecke70CZK = price(new BigDecimal("90"), Currency.getInstance("CZK"), narodeniny, svatovavrinecke70);
        Price svatovavrinecke70EUR = price(new BigDecimal("2.5"), Currency.getInstance("EUR"), narodeniny, svatovavrinecke70);

        priceService.createPrice(veltlinskeZelene35CZK);
        priceService.createPrice(veltlinskeZelene70CZK);
        priceService.createPrice(veltlinskeZelene70EUR);

        priceService.createPrice(muskatMoravsky35CZK);
        priceService.createPrice(muskatMoravsky70CZK);
        priceService.createPrice(muskatMoravsky70EUR);

        priceService.createPrice(svatovavrinecke35CZK);
        priceService.createPrice(svatovavrinecke70CZK);
        priceService.createPrice(svatovavrinecke70EUR);

    }

    public Wine wine(String name, Year vintage, String batch, String predicate, String predicateEquivalent,
                     String description, String notes, BigDecimal alcoholVolume, BigDecimal residualSugar,
                     BigDecimal acidity, BigDecimal grapeSugarContent) {
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


    private MarketingEvent marketingEvent(String description) {
        MarketingEvent marketingEvent = new MarketingEvent();
        marketingEvent.setDescription(description);
        return marketingEvent;
    }

    private WineList wineList(String name, LocalDateTime date, List<Wine> wines, MarketingEvent marketingEvent) {
        WineList wineList = new WineList();
        wineList.setName(name);
        wineList.setDate(date);
        wineList.setWines(wines);
        wineList.setMarketingEvent(marketingEvent);
        return wineList;
    }

    private Packing packing(BigDecimal volume, LocalDateTime validFrom, LocalDateTime validTo, Wine wine) {
        Packing packing = new Packing();
        packing.setVolume(volume);
        packing.setValidFrom(validFrom);
        packing.setValidTo(validTo);
        packing.setWine(wine);
        return packing;
    }

    private Price price(BigDecimal value, Currency currency, MarketingEvent marketingEvent, Packing packing) {
        Price price = new Price();
        price.setPrice(value);
        price.setCurrency(currency);
        price.setMarketingEvent(marketingEvent);
        price.setPacking(packing);
        return price;
    }

    private User user(String name, String surname, String email, UserRole role, String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(surname);
        user.setEmail(email);
        user.setUserRole(role);
        userService.registerUser(user, password);
        return user;
    }
}