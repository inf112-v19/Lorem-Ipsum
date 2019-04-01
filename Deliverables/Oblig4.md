# Obligatorisk Øvelse 4
## Deloppgave 1: Prosjekt og prosjektstruktur 
1. Kristoffer får rollen som testleder siden han har best kunnskap om hvordan spillet skal fungere og ulike edge cases som kan oppstå.
`Mauelle tester beskrives i deloppgave 3`

2. Vi føler vi har blitt mer produktive i kodesprintene og blitt flinkere til å følge opp oppgaver på project board. I tillegg har vi blitt flinkere til å lage mer presise tasks på prodject boardet. Teamet mener dette har gjort oppgaver mer oversiktlige og lettere å ta fatt i. 

3. Vi synes fortsatt gruppedynamikken er god. Vi samhandler godt og er flinke til å spille på hverandres styrker og å gjøre hverandre gode. Rollene i teamet fungerer utmerket og vi tror Kristoffer som testleder vil gi bedre struktur til de automatiske og manuelle testene. 

4. Kommunikasjonen i gruppen har fungert greit. Vi har blitt flinkere på jevn kommunikasjon via Slack og ved å ha jevnere fordelte møter.

5. Vi mener vi har fått til bedre kommunikasjon innad i gruppen siden forrige obligatoriske oppgave. Det har vi fått til gjennom flere møter og hyppigere kontakt via Slack. Siden vi er i en periode der mye funksjonalitet skal knyttes sammen har dette vært viktig. Vi bruker fortsatt prosjektmetodikken Kanban og bruker github sitt project board som kanban board. Oppgaver på kanbanboardet har blitt lagt til i all hovedsak på møter, slik at alle har en fortåelse av hva som blir gjort. Alle har hver sine ansvarsområder og vet med det hvilke oppgaver de kan ta fatt på fra kanbanboardet og eventuelt hvilke oppgaver man må hjelpe til med. Kanban boardet har blitt oppdatert i større grad med konkrete oppgaver fremfor større litt uklare oppgaver, noe vi mener har ført til bedre samhandling og koordinering i sammenkoblingsfasen vi har vært i. Vi har også hatt fokus på å skrive flere og bedre tester slik at edge cases blir plukket opp. Dette har både vært viktig og praktisk for oss i og med at mange deler av logikken kan inteferere med hverandre og ødelegge funksjonalitet når flere deler kobles sammen. Enkelte oppgaver har måttet vente på at andre oppgaver skal bli ferdig, noe som har gitt oss tid til å lage gode tester. Kristoffer som testleder har ført til at vi har fått på plass gode manuelle tester, samt flere automatiske tester. Vi mener vi fortsatt kan bli flinkere på å skrive gode og utfyllende oppgaver til Kanban boardet, siden dette er en essensiell del av prosjektmetodikken vår. Videre må vi ha fokus på dette slik av vi kan fortsette å jobbe godt hver for oss og heller koordinere arbeid sammen. Peer review er noe vi har hovedsaklig gjort via tester, så her har vi et forbedringspotensiale. Vi kan for eksempel i større grad benytte oss av parprogrammering for å gå gjennom koden sammen og for maksimal kunnskapsoverføring.  

6. Tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint:
* I større grad benytte parprogrammering for bedre kunnskapsoverføring og lettere per review
* Skrive enda mer konkrete tasks til kanbanboardet
* Prioritere å lage ny task sammen på møter, fremfor alene slik at alle har en større forståelse av progresjonen i arbeidet på prosjektet

7. `Møtereferat er vedlagt nederst i dokumentet`

8. Alle på teamet har sine ansvarsområder tilknyttet kodebasen. Dette medfører litt ulik mengde kode og kopleksitet men generelt mener at alle har bidrat like mye. For å koble sammen de forskjellige delene av programmet og for å sikre best mulig kompetanseoverføring har vi benyttet oss av parprogramering. Parprogramering har funnet sted både i og etter møter. 

9. Vårt project board er tilgjengelig på prosject siden på github.


## Deloppgave 2: krav
**Dissen kravene fra MVP mener vi at vi allerede har fullført:**
* Det skal være lasere på brettet
* Det skal være hull på brettet
* Fungerende samlebånd på brettet som flytter robotene
* Fungerende gyroer på brettet som flytter robotene
* Samlebånd som går i dobbelt tempo

**Disse kravene fra MVP har vi lyst til å fullføre til denne leveringen:**
* Man må kunne spille en komplett runde
* Man må kunne vinne spillet spillet ved å besøke siste flagg (fullføre et spill)
* Skademekanismer (spilleren får færre kort ved skade)
* Game over etter 3 tapte liv 
* Plassere flagg selv før spillet starter

## Deloppgave 3: Kode
* Clone the [source repository](https://github.com/inf112-v19/Lorem-Ipsum)
    * On the command line, enter:
    ````
    git clone https://github.com/inf112-v19/Lorem-Ipsum.git
    ````

* Import and build as maven project

* Run the project
    * run Main.java located: `.../src/main/java/inf112/skeleton/app/Main.java`

* Run the tests
    * Automatic tests
        * tests are located in folder: `.../src/test/java/inf112/skeleton/app/`
        * tests can be run independently or as a whole in IntelliJ using `Run 'Tests in 'inf112.skeleton.app''`
    * Manual tests
        * Plassere spiller på annet enn spawntile test: Starter programmet og velger 1 spiller, prøver å trykke på           alle andre ruter enn spawntile, i tillegg til utenfor kartet. Programmet lar meg ikke fotsette før jeg velger en av Spawntilene.
        * Plassere flere spillere på samme spawntile test: Starter programmet med 2 spillere. Prøver å trykke flere ganger på samme spawntile, kun den første spilleren blir plassert.
        * Plassere flere flagg på samme rute test: Starter programmet og velger 2 spillere, plasserer de på ulige spawntiler og prøver å sette ut begge flaggene på samme rute, det første flagget blir plassert på ønsket rute men det andre vil ikke plasseres. Programmet lar meg ikke forstette før jeg velger en gyldig rute.
        * Spiller vil ikke få ny backup plassering ved å gå på en annen spawntile test: Starter programmet, velger 1 spiller, plasserer den på en tilfeldig spawntile og setter flagget på en tilfeldig gyldig rute. Velger kort som får spilleren til å gå til og stoppe på en annen spawntile. Neste runde velger kort som får spillern til å gå utenfor kartet og bli ødelagt. Runden etter plasseres spilleren på dens orginale spawntile. 
        * 2 spillere reagerer likt på å dytte hverandre på conveyorbeltTile test: Starter programmet og velger 2 spillere, plasserer de på tilfelige spawntiles og plasserer flagg på tilfeldige gyldige ruter. Velger kort for begge spillere så de ender ved siden av hverandre på hver sin ConveyorbeltTile. Oppsettet er nå: spiller 1 sår bak spiller 2 og vil dyttes inn i spiller 2. Spiller 2 skal også bli dyttet samme vei. Når runden avsluttes blir begge spillerene forskjøvet samme vei uten at noen dytter den andre lengere enn den skal. Derretter velger jeg kort slik at spillerene bytter plass. Denne gangen står spiller 2 bak spiller 1. Når runden avsluttes blir spiller 2 stående spille og spiller 1 blir dyttet en ekstra rute(altså 2 ruter, en på grunn av conveyorbeltTile og en som følge av en bug med hvordan spillere på conveyorbeltTiles håndteres).
            * Etter flere forsøk på å finne bugs i programmet har jeg sett at det noen ganger oppstår uønskede bevegelser av en spiller som blir dyttet av en annen spiller(som i testen over). Dette førte til at vi sammen debugget movePlayer metoden i Board som styrer bevegelse og fant ut at det oppstod en edge case ved spillerkolisjon. Løsningen var en forholdsvis enkel logisk feil i hva returnverdien i movePlayer var. Når vi fant feilen var dette lett å fikse. 
        * Kortene som vises representer riktig kort med riktig prioritet test: Kjører programmet og starter spillet med 2 spillere. Sjekker nøye at kortene som blir valgt blir spilt av riktig robot i riktig sekvens(har riktig prioritet). For å unngå feil ble valgte kort skrevet ned og regnet ut og det ble regnet ut riktig rekkefølge på kortene før runden startet. Jeg fant ingen feil i korttype, prioritet eller hvilken robot som spiller valgt kort.  


* Project tested on
    * Mac
    * Linux (Ubuntu)
    * Windows

## Klassediagram
![](https://github.com/inf112-v19/Lorem-Ipsum/blob/master/Deliverables/classdiagramOblig4.png)

# Møtereferat
## Møtereferat 13.02.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Støtte for flere runder. Board har nå en reset round funksjonalitet
* Player er utvidet. Holder nå på informasjon om retning, og også status på om kortvalg er utført.
* Forbedringer på skalering av spillbrett
* Klart å sammenkoble faselogikken med brettet 

**Diskusjon / problemer / løsninger**
* Diskuterte nye arbeidsoppgaver til spillet og deretter fordelte arbeidet
* Hadde peer review
* Diskuterte om vi ville ha 8 eller 6 spiller
* Kom fram til at vi synes 6 spillere var bedre for oss enn 8 pga. 8 blir for mange spillere på en gang

**Nye oppgaver / plan til neste møte**
* Meny state. Valg av brett, og å kunne velge antall spillere(1-6) **_Gjøres på ny branch_** (Emily)
* PendingCardsGUI. skal brukes i actionState og vise hvilke kort som spilles (Øyvind)
* Grafisk vise helse og liv (Øyvind)
* actionState skal dele opp moves som går over flere ruter. (Christopher)
* Lage top-down player bilde (Kristoffer)
* Static code analysis (Henrik)
* Håndtere skalering på det grafiske (Henrik)

## Møtereferat 20.03.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Startet med automatisk kode analyse
* Progresjon på meny skjermene. Utviklingen skjer i egen branch kalt menu
* HUD ferdig. Viser liv og helse på hver spiller.
* ActionState viser nå bare flytt over en rute per update.
* Ferdig med ChooseBoardState der du kan velge type brett til spillet.

**Diskusjon / problemer / løsninger**
* Gruppen skal begynne å bruke FindBugs plugin i IntelliJ. Dette vil gjøre det lettere å oppdage dårlig kodepraksis i kodebasen
* Problemer med å sette FindBugs plugin opp, men vi hjalp hverandre og dermed kom i mål sammen
* Oppdaterte hverandre om hva som hadde blitt gjort siden sist møte. 

**Nye oppgaver / plan til neste møte**
* Separere kortlogikk og CardGUI. (Øyvind)
* Jobbe videre med meny skjerm/ der du kan velge spiller (Emily)
* Utvide Player klassen. Indeks på hver spiller. boolean på om spiller lever. (Christopher)

## Møtereferat 22.03.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Valg av type brett til spillet før spillet begynner
* Separere kortlogikk og CardGUI
* Utvide Player klassen. Indeks på hver spiller. boolean på om spiller lever
* La til tilfeldige navn til spillerne som blir valgt 

**Diskusjon / problemer / løsninger**
* Hvilke buttons vi bruker og buttons.png fra assets funker bra
* Vi må finne nytt forsidebilde 
* fikk litt problemer med å koble antall spillere og brettype med hele spillet
* Drev med en del peer review
* Problemer med å håndtere input i LibGDX siden Skin må mest sannsynlig bli brukt

**Nye oppgaver / plan til neste møte**
* Koble valgt antall spillere og brettype med hele spillet (Henrik/Emily)
* Spillere får delt ut kort basert på antall helsepoeng, og kan låse kort (navn)
* Flytte logikken som angår kortlogikk fra CardHandGUI til ny klasse: CardManager (navn)
* Utvide spillerklassen, indeks og isDead-metode (navn) 

## Møtereferat 27.03.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Spillere får delt ut kort basert på antall helsepoeng. Kort kan også gå i lås.
* Logikken som angår kortlogikk er flyttet fra CardHandGUI til ny klasse: CardManager
* Spiller klassen utvidet. Indeks og isDead metode. 
* Koblet antall spillere og brettype med resten av spillet.

**Diskusjon / problemer / løsninger**
* Skriftlige delen av den obligatoriske oppgaven.
  * Ferdigstilles i fellesskap på førstkommende Fredag.
* Diskuterte prosejmetodedikken de foregående ukene.
  * Enighet om at hyppige møter bidrar til raskere progresjon på prosjektet. Gjør det også lettere å få eierskapsforhold til kodebasen.
* Kristoffer fikk tildelt testrolle og ansvar for testreferatet.
  *  Testene skal lages under tett samarbeid med den som har laget koden som testes.
* Løste koblingen mellom valg av antall spillere og brettype ved å endre på en del parametere til konstruktørene til de fleste statene, også lage CardManager i ChooseBoardState.

**Nye oppgaver / plan til neste møte**
* Spillere skal kunne sette ut flagg før spillet starter (Henrik)
* Game Over state og pauseknapp med funksjoner (Emily)
* Board skal motta liste med spillere fra menystate (Christopher)
* Fikse bedre testdekning av board(Christopher)
* Grunding testing av CardManager, kortlogikk (Øyvind)
* Implementere alle tile typene (Kristoffer)
* Grundig testing med referat (Kristoffer)

## Møtereferat 29.03.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Spillere skal kunne sette ut flagg før spillet starter
* Midlertidig Game Over state 
* Har omplassert hvor CardManager blir laget
* Laget klassen Text

**Diskusjon / problemer / løsninger**
* Diskuterer fortsatt litt av den skriftlige delen av den obligatoriske oppgaven. Vi tenker å bli ferdig med den i dag
* Diskuterte retteskjemaet vi fikk fra forrige obligatoriske oppgave og hva vi måtte jobbe med.
* Finner ut at spillet vårt tar veldig mye minne, så vi prøver å finne ut av det.
* Peer review 
* Planlegger å møte igjen én gang før innleveringsfristen (mest sannsynlig førstkommende søndag/mandag) 
* Diskuterte om pauseknappen trenger å ha sin egen knapp i spillet eller om det bare er et tastetrykk fra tastaturet

**Nye oppgaver / plan til neste møte**
* Pause State (Emily) 
* Finne en ny løsning for å hente resurser i BoardBuilder(Christopher)
* Legge til visuelt flagg (Kristoffer)
* Løse memoryleak-problemet (Henrik/Øyvind)

## Møtereferat 31.03.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Henrik

**Resultater siden sist møte**
* Klarte å løse input for navn til spillerne
* Pause State 
* Fikser spillets minnebruk, slik at det ikke bruker så veldig mye minne.
* Ny måte som håndterer innlastningen til brettet på
* Fått til det visuelle av ulike flagg til ulike spillere
* Fått på plass manuelle testbeskrivelser

**Diskusjon / problemer / løsninger**
* Snakket og diskutert innleveringen 
* Peer review
* Refaktorert SpriteSheets til AssetsHandler, der vi bruker AssetsManager fra libGDX (fikser minnet)

**Nye oppgaver / plan til neste møte**
* Klassediagram (Henrik)
* Gjøre ferdig hele innleveringen
* Levere obligatorisk innlevering 4

## Møtereferat 01.04.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Henrik

**Resultater siden sist møte**
* Ny forsidebilde
* Klassediagram
* Gjort ferdig hele innleveringen
* Levert innlevering 4
* Fikset Stage og Dispose

**Diskusjon / problemer / løsninger**
* PauseState sin resume fungerer ikke som den skal, vi finner ut at vi har en del stages og at det ikke er den riktige stagen som blir riktig brukt til enhver tid 
* Vi fikset det ved å bruke riktig stage til enhver tid
* Fant ut at vi ikke disposer helt riktig, men vi løste det også.
* Gikk sammen gjennom hele innleveringen og sjekket alle rundene i spillet
* Peer Review

**Nye oppgaver / plan til neste møte**
* Begynne å se på de resterende MVP kravene og planlegge videre arbeid for å oppnå disse