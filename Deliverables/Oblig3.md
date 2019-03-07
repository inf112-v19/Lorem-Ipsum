# ***Oblogatorisk øvelse 3***
## Deloppgave 1: Prosjekt og prosjektstructur 

**1.** Rollene i teamet funker utmerket, vi trenger ikke å oppdaterer hvem som er teamleader eller kundekontakt, og i tillegg trenger vi midlertidig ingen nye roller heller. Teamleader for oss er en som sørger for å følge opp oppgaver og prosjektboard. Kundekontakt for oss er gruppens hovedekspertise på domenet.

**2.** Generelt er vi fornøyd med vår valgte prosjektmetodikk Kanban Project Board, men har erfart at vi kan bli flinkere på å formulere oppgaver. Vi må bli mer spesifikk på hva selve oppgaven innebærer og ikke beskrive oppgaver som “lag den klassen”.

**3.** Vi synes gruppedynamikken er god. Vi samhandler godt og er flinke til å spille på hverandres styrker og å gjøre hverandre gode. 

**4.** Kommunikasjonen i gruppen har fungert greit, men vi kan bli enda flinkere på jevn kommunikasjon via f.eks. Slack for å få raskere respons på Peer review osv.

**5.** Retrospektivt har vi blitt flinkere på å fordele arbeid på møter, jobbe selvstendig og i små grupper. Mellom møtene har vi opprettholdt kommunikasjonen gjennom Slack. Vi har også benyttet oss av ulike branches for segmentere prosjektets features. Dette gjorde parallelt arbeid på ulike deler av prosjektet enklere uten å interferere med hverandres midlertidig arbeid. Ting vi kan forbedre oss på er blant annet å fordele mer konkrete arbeidsoppgaver på møtene, raskere oppfølging på Slack, bli bedre på Peer review, å skrive fler og bedre tester og å skrive mer utfyllende møtereferater. 

**6.** Vi har relativt like megde commits, men våre ulike arbeidsprosess med tanke på når vi comitter og hvor ofte, medører at enkelte på gruppen har noe fler commits enn andre. Likvell har vi en oppfattelse av at vi har bidratt med samme mengde arbeid til kodebasen. Det har også oppstått enkelte uventede problemer med forskjellige Git brukernavn for samme person. Dette har medført at en del commits fra samme person har blitt fordelt på ulike brukernavn. Vi mener å ha løst dette problemet nå, men det ble løst relativt sent i denne perioden som medøfrer at en del commits er fra samme person, men med ulike brukernavn. 

**7.** Møtereferat ligger nederst i obligen

**8.** 
Vi er enige om at vi må bli bedre på: 
1. Fordele mer konkrete arbeidsoppgaver på møtene. 
2. Bli bedre på Peer review.
3. Skrive fler og bedre tester. 

 
 



## Deloppgave 2: krav
**1.** 
* **Krav fra kunden**:
    * Kunne få alle typene bevegelseskort
    * Dele ut 9 kort
    * Velge 5 kort (godkjenne valg/si “nå er jeg klar”)
    * Eksekvere program ut fra valgte kort
    * Besøke flagg
    * Hvis robot går av brettet blir den ødelagt og går tilbake til siste backup
    * Oppdatere backup hvis robot blir stående på skiftenøkkelrute i slutten av en fase
    * Flytte backup ved besøk på flagg
    * Kunne spille en fullverdig runde med alle faser
    * Få nye kort til ny runde
* **De faktiske oppgavene**
    * Bygge brett ut fra en txt fil med en logisk representasjon
    * Implementere logikk for retning basert bevegelse som tar hensyn til spillmekanikken (movePlayer metode)
    * tegne brettet basert på den logiske representasjonen
    * Delt opp spillet i states
    * Kunne få alle typene bevegelseskort 
    * Dele ut 9 kort
    * Velge 5 kort (og i tillegg kunne godkjenne valg)
    * Klart å lage et rammeverk for å senere eksekvere program ut fra valgte kort 
    * Oppdatere backup hvis en robot blir stående på skiftenøkkelrute
    * Flytte backup ved besøk på flagg
    * Hvis en robot går av brettet blir den ødelagt og går tilbake til siste backup
    * Laget rammeverket for at det skal bli lettere å senere implementere nye kort til ny runde
    * kunne spille en runde med faser

**2.** 
* **Teamets prioriterte oppgaver**
    * implementere bevegleseskort og samle disse i en kortstokk
    * implementere mulighet for å dele ut kort til spillere
    * implementere GameObjects
    * implementere Tiles
    * implementere Player
    * bygge et brett fra en txt fil 
    * tegne brettet bygget fra txt filen
    * implementere bevegelses logikk for spillere
    * lage en startscreeen og derfra kunne starte spillet.

**3.** Vi har overordnet fulgt prioriteten til de opprinnelige kravene som de ble gitt, selv om vi ikke fullførte alle kravene.

**4.** Vi verifisere at kravene er oppfylt ved en kombinasjon av manuelle og automatiske tester som bekrefter at koden vår gjør det den skal. I tillegg har vi hatt jevnlige møter for å planlegge og gjennomføre arbeid knyttet opp mot å oppfylle kravene. Akseptansekriteriene for kravene har vi valgt å tolke som “de faktiske oppgavene” som er listet tidligere. Ettersom at disse oppgavene er de opprinnelige kravene brutt ned i kortfattede kriterier mener vi at disse vil være ekvivalent med akseptansekriteriene. 

**5.** Siden forrige iterasjon har vi klart å gjennomføre flesteparten av kravene vi tok utgangspunkt i, men likevel var det enkelte krav vi ikke klare å fullstendig fullføre før innlevering. Vårt valg å gå bort i fra vår opprinnelige implementasjon av brettet fra første iterasjon og heller bygge brettet opp selv fra “bunnen av” medførte at mye av vår tid/arbeid i denne perioden gikk ut på dette. Vi mener dette var et godt valg ettersom at vi nå har en klarere forståelse for hvordan logikken henger sammen med det grafiske som vil gjøre fremtidig arbeid lettere. Dette medførte at vi ikke klarte å fullstendig implementere runde logikken og klarte da ikke å fullføre kravene tilknyttet å spille en fullstendig runde og krav tilknyttet runde logikken. Vi har likevel klart bygge et (etter vår mening) godt rammeverk for videre arbeid med en god plan for hvordan runde logikken skal implementeres fullstendig. 

## Deloppgave 3/4: kode
* Clone the [source repository](https://github.com/inf112-v19/Lorem-Ipsum)
    * On the command line, enter:
    ````
    git clone https://github.com/inf112-v19/Lorem-Ipsum.git
    ````

* Import and build as maven project

* Run the project
    * run Main.java located: `.../src/main/java/inf112/skeleton/app/Main.java`

* Run the tests
    * tests are located in folder: `.../src/test/java/inf112/skeleton/app/`
    * tests can be run independently or as a whole in IntelliJ using `Run 'Tests in 'inf112.skeleton.app''`


# Møtereferat
## Møtereferat 13.02.2019
**Deltakere**
* Øyvind
* Emily
* Kristoffer

**Resultater siden sist møte**
* GUI med flyttbar bil ferdig. "Proof of concept"

**Diskusjon / problemer / løsninger**
* Laget powerpoint til presentasjonen
* Code review av GUI og spillobjekter. Sørger for at alle har eierskapsforhold til kodebasen.

**Nye oppgaver / plan til neste møte**
* Få dypere kunnskap om LibGDX
* Presentasjon skal holdes. Blir derfor litt mindre fokus på programmering

## Møtereferat 20.01.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Holdt en vellykket presentasjon 

**Diskusjon / problemer / løsninger**
* Diskusjon om hvordan GUI skal implementeres. Løsning: En mer manuell tilnærming til GUI. Skal fortsatt bruke LibGDX. Men brettet skal rendres med forløkker og egenprodusert mapreader istedenfor å bruke LibGDX sin innebygde TiledMap.

**Nye oppgaver / plan til neste møte**
* Implementere kortstokk med program kort (Øyvind)
* Implementere states, menuState (Emily)
* Implementere spillerobjekt (Kristoffer)
* Begynne på GUI, og lage en boardbuilder som leser inn kart (Henrik, Christopher)

## Møtereferat 27.02.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Implementert states
* Laget outline på GUI. boardbuilder.
* Implementert programkort og kortstokk
* implementert spiller objektet så langt det er mulig før brettet er ferdig

**Diskusjon / problemer / løsninger**
* Diskuterte spørsmålene på oblig 3

**Nye oppgaver / plan til neste møte**
* Gjøre oppgave 2 på obligen (Emily)
* Fikse alle møtereferatene og putt det i GitHub wiki (Øyvind)
* Tegne spill objekter på skjermen og gjøre det mulig å flytte disse (Henrik/Chris)
* Lage alle de ulike type Tiles og GameObjects på brettet og implementere alle de ulike typene brikker (Kristoffer)
* Fikse klassediagram 
* Lage forskjellige playstates utifra hva som skal skje. (Emily/henrik)
* Rydde opp assets (bla. endre navn også)

## Møtereferat 01.03.2019
**Deltakere**
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

**Resultater siden sist møte**
* Fikse alle møtereferatene og putt det i GitHub wiki

**Diskusjon / problemer / løsninger**
* Diskuterte oppsettet på spilleskjermen

**Nye oppgaver / plan til neste møte**
* Gameloop. Rundebasert spill (Emily, Henrik)
* ferdigstille player og gameObjects (Kristoffer)
* implementere alle de ulike rutene i brettet og legge inn spiller (Christopher)
* Implementere kortene i GUI (Øyvind)

## Møtereferat 06.03.2019
### Deltakere
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

### Resultater siden sist møte
* Delt opp spillet i states
* Implementere laging, deling og bruk av kort
* Implementert kortene i det visuelle
* Ferdigstille player og GameObjects 
* Delvis implementert alle de ulike rutene i brettet og å legge inn spiller

### Diskusjon / problemer / løsninger
* Diskuterte mye om hvordan alt henger sammen. Fant også ut at mye av det er avhengig av hverandre slik at vi måtte vente på hverandre og snakke sammen for å kunne komme oss videre. Eksempler på det er første og andre punkt (under resultater siden sist møte), og tredje og fjerde punkt.

### Nye oppgaver / plan til neste møte
* Jobbe med de 5 fasene under spillet (ActionState) (Henrik/Emily)
* Sammenkoble faselogikken sammen med brettet (Henrik/Emily/Christopher)
* Sammenkoble kortlogikken sammen med brettet (Øyvind/ Christopher)
* Lage flere tester (generelt alle)
* Lage en checkTile til alle tilene for å sjekke at de gjør de gjør det de skal (Kristoffer)

## Møtereferat 07.03.2019
### Deltakere
* Øyvind
* Emily
* Christopher
* Kristoffer
* Henrik

### Resultater siden sist møte
* Delvis alt

### Diskusjon / problemer / løsninger
* Jobber sammen med siste oppgavefordeling. Mye av det henger sammen og dermed har vi valgt å møte opp igjen dagen etter for å løse problemer sammen i lag.

### Nye oppgaver / plan til neste møte
(fortsatt de samme oppgavene)
* Jobbe med de 5 fasene under spillet (ActionState) (Henrik/Emily)
* Sammenkoble faselogikken sammen med brettet (Henrik/Emily/Christopher)
* Sammenkoble kortlogikken sammen med brettet (Øyvind/ Christopher)
* Lage flere tester (generelt alle)
* Lage en checkTile til alle tilene for å sjekke at de gjør de gjør det de skal (Kristoffer)