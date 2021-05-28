# Redigere Vare

Scope: New Yorker Web Application

Level: User-Goal

Primary Actor: Salgsdirektøren

Description: Systemet skal være i stand til at redigere vareinformationer.

## Stakeholder and interest

Salgsdirektøren

## Preconditions

Salgsdirektøren skal være logget ind i webapplikationen

## Success Guarantee

Varen er redigeret i databasen

## Main Success Scenario

Scenario A
 1. SalgsDirektøren åbner Webapplikationen.
 2. SalgsDirektøren vælger den vare de vil ændre pris på, og trykker på rediger knappen
 3. SalgsDirektøren angiver ny pris for varen.
 4. Programmet spørger om bekræftelse for ændringen af varen
 5. Salgsdirektøren bekræfter ændringen af varen
 5. Webapplikationen ændrer prisen for varen.

Scenario b
 1. SalgsDirektøren åbner Webapplikationen.
 2. SalgsDirektøren vælger den vare de vil ændre navn på, og trykker på rediger knappen
 3. SalgsDirektøren angiver et ny navn for varen.
 4. Programmet spørger om bekræftelse for ændringen af varen
 5. Salgsdirektøren bekræfter ændringen af varen
 5. Webapplikationen ændrer navnet for varen.

## Extensions

Scenario A

 1. SalgsDirektøren åbner Webapplikationen.
 2. SalgsDirektøren vælger den vare de vil ændre pris på, og trykker på rediger knappen
 3. SalgsDirektøren angiver en negativ pris for varen.
 4. Programmet oplyser at prisen ikke må være negativ, og beder om et nyt tal.
 5. Processen forsætter fra 3. indtil han giver et positiv tal.

## Special Requirements
 --
## Technology and Data Variations List
 --
