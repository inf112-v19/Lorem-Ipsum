Manuelle Tester

Plassere spiller på annet enn spawntile test: Starter programmet og velger 1 spiller, prøver å trykke på alle andre ruter enn spawntile, i tillegg til utenfor kartet. Programmet lar meg ikke fotsette før jeg velger en av Spawntilene.

Plassere flere spillere på samme spawntile test: Starter programmet med 2 spillere. Prøver å trykke flere ganger på samme spawntile, kun den første spilleren blir plassert.

Plassere flere flagg på samme rute test: Starter programmet og velger 2 spillere, plasserer de på ulige spawntiler og prøver å sette ut begge flaggene på samme rute, det første flagget blir plassert på ønsket rute men det andre vil ikke plasseres. Programmet lar meg ikke forstette før jeg velger en gyldig rute.

Spiller vil ikke få ny backup plassering ved å gå på en annen spawntile test: Starter programmet, velger 1 spiller, plasserer den på en tilfeldig spawntile og setter flagget på en tilfeldig gyldig rute. Velger kort som får spilleren til å gå til og stoppe på en annen spawntile. Neste runde velger kort som får spillern til å gå utenfor kartet og bli ødelagt. Runden etter plasseres spilleren på dens orginale spawntile. 

2 spillere reagerer likt på å dytte hverandre på conveyorbeltTile test: Starter programmet og velger 2 spillere, plasserer de på tilfelige spawntiles og plasserer flagg på tilfeldige gyldige ruter. Velger kort for begge spillere så de ender ved siden av hverandre på hver sin ConveyorbeltTile. Oppsettet er nå: spiller 1 sår bak spiller 2 og vil dyttes inn i spiller 2. Spiller 2 skal også bli dyttet samme vei. Når runden avsluttes blir begge spillerene forskjøvet samme vei uten at noen dytter den andre lengere enn den skal. Derretter velger jeg kort slik at spillerene bytter plass. Denne gangen står spiller 2 bak spiller 1. Når runden avsluttes blir spiller 2 stående spille og spiller 1 blir dyttet en ekstra rute(altså 2 ruter, en på grunn av conveyorbeltTile og en som følge av en bug med hvordan spillere på conveyorbeltTiles håndteres).

Etter flere forsøk på å finne bugs i programmet har jeg sett at det noen ganger oppstår uønskede bevegelser av en spiller som blir dyttet av en annen spiller(som i testen over). Dette førte til at vi sammen debugget movePlayer metoden i Board som styrer bevegelse og fant ut at det oppstod en edge case ved spillerkolisjon. Løsningen var en forholdsvis enkel logisk feil i hva returnverdien i movePlayer var. Når vi fant feilen var dette lett å fikse. 
