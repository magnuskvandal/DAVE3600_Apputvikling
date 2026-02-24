# Mappe 1 – Addisjonsspill for barn

En matteopplæringsapplikasjon for barn utformet som et addisjonsspill der brukeren skal løse tilfeldig utvalgte regnestykker ved ved å skrive inn svar med talknapper i grensesnittet. Det kan velges mellom spilløkter på 5, 10 eller 15 oppgaver, og hver oppgave vises kun én gang per runde.

Spillet har 15 regnestykker tilgjengelig, som ligger i en xml fil under res/values. Antall oppgaver per spilløkt kan endres i preferanser, og brukerens valg lagres i SharedPreferences slik at innstillingen huskes mellom økter.

Applikasjonen støtter både norsk og tysk språk.

## Skjermbilder og demo
<div style="display: flex; flex-wrap: wrap; gap: 20px; justify-content: flex-start;">

  <div style="width: 320px; text-align: center;">
    <h3>Startskjerm</h3>
    <img src="screenshots/startskjerm.png" width="300"/>
  </div>

  <div style="width: 320px; text-align: center;">
    <h3>Om spillet</h3>
    <img src="screenshots/info.png" width="300"/>
  </div>

  <div style="width: 320px; text-align: center;">
    <h3>Preferanser</h3>
    <img src="screenshots/preferanser.png" width="300"/>
  </div>

  <div style="width: 320px; text-align: center;">
    <h3>Demo av spillet</h3>
    <video src="screenshots/mattespill.mp4" width="300" controls></video>
  </div>

</div>