# DAVE3600 Apputvíkling 2025

Tre native Android-applikasjoner utviklet med Kotlin og Jetpack Compose i forbindelse med kurset DAVE3600 Apputvikling, høsten 2025.


## Mappe 1 – Addisjonsspill for barn 
En matteopplæringsapplikasjon for barn utformet som et addisjonsspill der brukeren skal løse tilfeldig utvalgte regnestykker ved ved å skrive inn svar med talknapper i grensesnittet. Det kan velges mellom spilløkter på 5, 10 eller 15 oppgaver, og hver oppgave vises kun én gang per runde.

Spillet har 15 regnestykker tilgjengelig, som ligger i en xml fil under res/values. Antall oppgaver per spilløkt kan endres i preferanser, og brukerens valg lagres i SharedPreferences slik at innstillingen huskes mellom økter. 

Applikasjonen støtter både norsk og tysk språk.

## Mappe 2 – Bursdagsassistent
En applikasjon hvor brukeren kan registrere venner og holde oversikt over deres fødselsdager. Venner registreres med navn, telefonnummer og fødselsdato og lagres i en lokal SQLite-database via ROOM. Alle registrerte venner vises i en liste ved oppstart, og det er mulig å endre eller slette venner.

Appen har en SMS-tjeneste som automatisk sender bursdagshilsener til venner som har bursdag. Tjenesten styres av WorkManager, som daglig sjekker for bursdager og sender SMS til dem det gjelder.
SMS-tjenesten kan aktiveres/deaktiveres i preferanser. Der kan også standardmelding som skal sendes endres.


## Mappe 3 – Kartbasert favorittstedsapplikasjon
En kartapplikasjon som lar brukeren registrere sine favorittsteder i et interaktivt Google Maps-kart. Nye steder legges til ved å trykke direkte på kartet, som åpner et registreringsskjema i en BottomSheet der brukeren kan fylle inn navn og beskrivelse. For hvert registrerte sted lagres navn, beskrivelse, gateadresse og GPS-koordinater. Stedene lagres i en ekstern MariaDB-database. Registrerte steder vises som markører på kartet, og ved å trykke på en markør får brukeren opp en BottomSheet med lagret informasjon om stedet. Appen har også et søkefelt som lar brukeren søke etter land, byer, attraksjoner eller adresser for å navigere kartet direkte til ønsket sted.

Androids Geocoder benyttes til å oversette mellom koordinater og adresser i søk og registrering av steder. All datautveksling mellom applikasjonen og den eksterne MariaDB-databasen skjer via en webtjeneste. Denne håndterer lagring, henting og sletting av steder ved bruk av Retrofit, mens Moshi brukes for å konvertere mellom JSON-data og Kotlin-objekter. Databasen er hostet på OsloMet sine servere, og tilkobling krever VPN utenfor OsloMet sitt nettverk.
