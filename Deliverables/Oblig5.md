# Obligatorisk øvelse 5
## Deloppgave 1: Prosjekt og prosjektstruktur
* Vi mener at vi har blitt flinkere til å sitte sammen og jobbe som en gruppe, istedenfor individuelt. Dette har gjort at vi har blitt enda mer produktive i kodesprintene og eliminerer mye av ventetiden som kan oppstå når man må vente på svar fra andre over slack eller på møter.

* Gruppedynamikken i teamet er god. Vi jobber godt sammen og er flinke til å hjelpe hverandre når det trengs. Under møtene er som regel alle med på planlegging og har forslag til videre arbeid på prosjektet. 

* Komunikasjonen i gruppen fungerer greit. Vi har funnet ut at et fast møte hver onsdag og muligheten til et møte på fredager når det er behov for det fungerer best for oss. Mellom møtene kommuniserer vi jevnlig over slack. Vi jobber også veldig mye sammen, som for eksempel da vi skulle teste networking. Det var Henrik (gruppeleder) som endret koden for hver test og endring, men vi var sammen om det med å diskutere, komme med forslag og teste networkingen, for det hadde funket å bare teste den lokalt på egen pc. Derfor så har Henrik så mange commits, selv om flere av oss var med på det.

* Til den siste obligatoriske innleveringen har medlemmene av teamet som har drevet mest med koding tatt på seg oppgaver som har med design å gøre og medlemmer som har drevet mer med design har tatt på seg opgaver med koding. Christoffer som hovedsaklig har jobbet med å kode spillbrettet med spillets logikk har blant annet designet og jobbet med lasere under den siste leveransen. Christoffer har i tilegg designet de nye spillbrettene som vi har laget til den siste leveransen. Kristoffer som har kodet minst og drevet mer med deign under de tidligere leveransene, har jobbet med å kode en AI. Henrik og Emily har tidligere drevet med design av states, under denne siste leveransen har Henrik tatt på seg hovedoppgaven med koding av LAN implementasjon i spillet. Emily har også tidligere kodet spillogikk men nå har hun gått tilbake til design av det visuelle til spillet. Øyvind har tidligere jobbet med GUI, under den siste leveransen har han hjulpet Henrik med nettverksimplementasjonen.  

* Møtereferater ligger vedlagt nederst

### Retrospektiv med fokus på hele prosjektet
* Vi har justert en del ting underveis, og det meste av det har gitt gode resultater. Ting vi har fikset er blant annet å endre rundelogikken til spillet. Vi fant ut at vi ikke hadde faser i spillet men bare runder, så det har vi justert, og nå funker spillrundene slik vi ønsker det. Etter hvert fant vi også ut at vi lagde veldig mange Stages under spillet, dette førte til at spillet vårt kjørte veldig tregt, så vi løste det ved å kun lage én Stage og at det er kun den som blir brukt gjennom hele spillet. Vi fant også senere ut at det var mye lurere å lage én AssetHandler (tidligere kalt Spritesheets), så det har vi også justert. Vi fant så ut at Table var ekstremt praktisk å bruke hvis vi skulle sette ting visuelt på brettet uten å måtte styre så veldig mye med koordinater og posisjoner, dermed ble Table brukt til å styre det visuelle i spillet. Vi har også blitt bedre på å klare å bryte store oppgaver ned til mindre mer spesifikke oppgaver, dette gjør at vi får mindre omfattende oppgaver og det er mer framgang siden vi ikke sitter lenge fast på store oppgaver. 

* Det som har fungert best er blant annet hvor bra vi jobber sammen som et lag. Uten den gode kommunikasjonen mellom oss, og de produktive dagene hvor vi frivillig møter opp sammen for å kode og diskutere, så hadde vi ikke vært der vi er nå. Vi setter veldig pris på at alle har til enhver tid satt litt av sin fritid for å jobbe med dette prosjektet, selv med forskjellige timeplaner og fag, slik at vi kan komme i mål sammen. I tillegg er vi veldig fornøyd med hvordan vi har kommet sammen og løst prosjektmetodikken vår.

* Hvis vi skulle ha fortsatt med prosjektet, så tror vi ikke at vi ville ha justert så mye. Vi har allerede justert det meste vi mener burde justeres. Likevel er det selvfølgelig alltid noe vi kunne ha justert for å få laget et langt bedre spill, og det er som for eksempel å fikse spillogikken fra starten igjen slik at vi kunne ha unngått flest mulige edge cases. Et annet eksempel er å justere spillet sånn at den er mer optimalisert for networking.

* Vi fått innsikt i systemkonstruksjonsmetoder og -arbeidsprosesser ved et lagarbeid. Vi har altså fått erfart hvordan det er å organisere et prosjekt sammen med et lag du ikke har dannet selv, og å modellere livssyklusen til et programvare. I tillegg til å ha blitt bedre på kommunisere og skildre koder til andre i teamet, så har vi også begynt å forstå mer av teorien bak vanlig notasjon for analyse og design. Vi har lært en del om LibGDX, og om hvordan du kan bruke det til å lage et spill fra bunnen av ved hjelp av objektorienterte metoder.

## Deloppgave2: Krav
* MVP krav vi har fullført:
  * Man må kunne spille en komplett runde
  * Man må kunne vinne spillet ved å besøke siste flagg (fullføre et spill)
  * Det skal være lasere på brettet
  * Det skal være hull på brettet
  * Skademekanismer (spilleren får færre kort ved skade)
  * Spillmekanismer for å skyte andre spillere innen rekkevidde med laser som peker rett frem
  * fungerende samlebånd på brettet som flytter robotene
  * fungerende gyroer på brettet som flytter robotene
  * game over etter 3 tapte liv
  * multiplayer over LAN eller Internet
  * Feilhåndtering ved disconnect.
  * Power down
  * samlebånd som går i dobbelt tempo
  * spille mot random-roboter offline

* Nice to have krav vi har fullført:
  * plassere flagg selv før spillet starter

## Deloppgave3: Kode

* Kjente feil/svakheter
    * Laseranimasjonen tegnes av og til før roboten er ferdig med flyttet sitt. Dette har ikke innvirkning på logikken til spillet, men kan oppfattes som litt merkelig i GUIen.
    * Pauseknapp (P) fungerer ikke i menyvinduet hvor man skriver inn navn til spillere for "local game"
Dette er fordi vi i denne staten bruker en metode som hører etter inndata fra tastaturet som skal brukes til å skrive inn navnene. Her har vi laget en knapp som har samme funksjon som pausekanppen.
    * For noen brukere, som har nettverksinstillinger som stopper aplikasjonen fra å se deres ip-adresse vil ikke deres ip-adresse komme opp når man prøver å velge "host". Dette endrer ikke annet enn at man må finne fram ip-adressen sin selv. Man kan fortsatt være både host og klient selv om man har disse innstillingene. Ip-adressen sin kan man finne under nettverksinstillinger

### Build instructions

* Import and build as maven project

* Run the project
    * run Main.java located: `.../src/main/java/inf112/skeleton/app/Main.java`
* Java 8 is recommended
### Testing


* Automatic tests
    * tests are located in folder: `.../src/test/java/inf112/skeleton/app/`
    * tests can be run independently or as a whole in IntelliJ using `Run 'Tests in 'inf112.skeleton.app''`
* Manual tests
    * Ai spilleres kort tegnes ikke: Starter spillet med en spiller + 1 Ai spiller. Ser at når roboter og flag er plassert ut vil kun brukerens kort tegnes og når de er valgt starter runden som vanlig.
    * Antall Ai spillere + brukerstyrte spillere kan ikke være fler enn 6: Starter spillet med og velger ulike kombinasjoner av brukerstyrte spillere og Ai spillere. Finner at det ikke er mulig å starte spillet med en kombinasjon av Aier og brukerstyrte spillere hvor det tilsammen er flere enn 6 roboter. 
        
        Under testingen fant jeg en bug hvor antall ai spillere ble satt til max(utfra hvor mange brukerstyrte spillere som ble valgt først) hvis "slideren" for å velge ikke blir endret på(denne skal ha 0 som standard) før spillet starter. Etter litt leting fant vi at variablen som bestemmer hvor mange Aier som skal brukes er den samme som sier hvor mange Aier som er max og oppdateres når slideren endres. Dette løste vi ved å lage 2 forskjellige variabler som holder på de forskjellige verdiene.

## Klassediagram
![](https://github.com/inf112-v19/Lorem-Ipsum/blob/master/Deliverables/classdiagramOblig5.png)
        
# Møtereferat

## Møtereferat 03.04.2019
### Deltakere
* Øyvind
* Emily
* Christopher
* Henrik
* Kristoffer

### Resultater siden sist møte
* Levert obligatorisk innlevering 4

### Diskusjon / problemer / løsninger
* MVP krav og bryter store oppgaver ned til mindre mer spesifikke oppgaver.
  * Power Down
  * Health
  * LaserBaseTile/DoubleLaserBaseTile
  * Random AI
  * AssetHandler/Stage
  * LAN 
* Snakka om sist kundemøte (som var i går), og hva som ble diskutert der. 


### Nye oppgaver / plan til neste møte
* Random AI (Kristoffer) 
* LaserBaseTile and DoubleLaserBaseTile (Kristoffer)
* Det grafiske på Power Down og Health (Øyvind)
* Håndtere Power Down (Øyvind/Christopher)
* Lag én AssetHandler og én Stage som er tilgjengelig til alle klasser som trenger det (Henrik)
* Nye knapper som velger single-mode-player med AI, multiplayer lokalt eller multiplayer LAN (Emily)
* Gå gjennom alle States og legg alt til i Table (Emily)
* Legge Pause over til State sin Update (Emily)
* Alle skal se på mulige løsninger til LAN-opplegget

## Møtereferat 10.04.2019
### Deltakere
* Øyvind
* Emily
* Christopher
* Henrik
* Kristoffer

### Resultater siden sist møte
* Lag én AssetHandler og én Stage som er tilgjengelig til alle klasser

### Diskusjon / problemer / løsninger
* Diskuterte mulighetene på network. Skal bruke Netty. Henrik skal lage outline på nettverksløsningen

### Nye oppgaver / plan til neste møte
* Random AI (Kristoffer)
* LaserBaseTile and DoubleLaserBaseTile (Kristoffer)
* Håndtere Power Down

## Møtereferat 24.04.2019
### Deltakere
* Emily
* Christopher
* Henrik
* Kristoffer

### Resultater siden sist møte
* PendingCardsGUI og PlayerInfoGUI er endret slik at de bruker table. Gir bedre resize
* LaserBaseTile and DoubleLaserBaseTile

### Diskusjon / problemer / løsninger
* Diskutert om hvordan Table fungerte
* Snakka om hvordan vi har jobbet i påskeferien, og hvilke problemer vi fikk
* Peer Review

### Nye oppgaver / plan til neste møte
* Første del av deloppgave 1 (Kristoffer)
* Random AI (Kristoffer/Øyvind)
* Klassediagram (Henrik)
* Retrospektivoppgaven av deloppgave 1 (Emily)
* Power Down (Christopher)

## Møtereferat 30.04.2019
### Deltakere
* Øyvind
* Emily
* Christopher
* Henrik
* Kristoffer

### Resultater siden sist møte
* Har satt inn Table til alt det visuelle

### Diskusjon / problemer / løsninger
* Implementering av network/host.
  * Funket på egen lokal PC, men ikke med mange PCer.
* Diskutert hvordan vi skulle beregne AI sin fremgangsmåte til hvert flagg.
* Merging mellom branches og at det er lurt å pulle og merge ofte.
* Peer Review

### Nye oppgaver / plan til neste møte
* Fortsette på de samme oppgavene vi fikk tildelt forrige gang
* Fikse opp i koder som ble forandret under mergingen 
* Rydde i koden

## Møtereferat 02.05.2019
### Deltakere
* Øyvind
* Emily
* Henrik
* Christopher

### Resultater siden sist møte
* Random AI ferdig. Støtter bare offline
 
### Diskusjon / problemer / løsninger
* Problemer med networkingen, funksjonen funker midlertidig bare lokalt

### Nye oppgaver / plan til neste møte
* Implementere fungerende LAN modus
* Ferdigstille Oblig