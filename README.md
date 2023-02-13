# Simple-boat-reservation
-- Work in progress--

Jeg har skrevet denne oppgaven i sammenheng med en jobbsøknad. Oppgaveteksten og koden er endret slik at det ikke kan spores tilbake til den originale teksten.

Oppgaven fremstiller back-end av et enkelt system hvor man kan registrere og reservere en båt i en marina. Det er ikke nødvendig med UI (brukergrensesnitt).

Prosjektet inneholder flere funskjonaliteter for å registrere en båt med input parametere, samt å registrere en reservasjon av en båt. En reservasjon gjelder for kun en dag
og hvis man skal reservere en båt for flere dager, må man da lage flere reservasjoner.

Videre skal man kunne beregne total inntjening av en båt gjennom alle reservasjoner. Man skal kunne hente ut alle registrerte båter og sortere dem synkende etter total inntjening. Man skal også kunne finne ut det høyeste antallet av dager en båt ble reservert uavbrutt.

Man skal også sjekke for edge cases sånn som det skal ikke være mulig å registrere en reservasjon som ikke eksisterer, hvis det er flere personer ombord enn båten har plass til eller hvis båten er allerede reservert på den datoen.
