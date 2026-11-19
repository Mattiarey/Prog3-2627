Progetto Mail Client-Server
1. Architettura Client
1.1. Struttura generale
Sono previsti N client, ad esempio C1, C2 e C3.
Il client è un'unica applicazione che può essere avviata da tanti utenti differenti.
Ogni utente avvia l'applicazione e inizia la comunicazione con il server.
La comunicazione è bidirezionale e contemporanea.
Devono poter girare in parallelo più istanze della stessa classe.
Il client è relativo a un singolo utente.
Si ipotizza che ogni persona utilizzi un solo dispositivo.
Si assume che gli utenti utilizzino esclusivamente le proprie email.

Configurazione dell'ambiente di sviluppo:

In IntelliJ, nella configurazione delle applicazioni, è necessario abilitare l'opzione Allow multiple instances. In caso contrario, non sarà possibile avviare contemporaneamente tanti client nello stesso ambiente.

1.2. Autenticazione e inizializzazione
Non è prevista alcuna autenticazione tramite password.
È necessario effettuare il login utilizzando solamente l'indirizzo email.
Non sono previste nuove registrazioni di utenti.
Si può assumere che, al momento del lancio, la prima operazione del client sia connettersi al server e richiedere la propria mailbox.

Esempio di inizializzazione:

Vengo lanciato, come prima cosa dico al server: sono mariorossi@outlook.com e mi inizializzo.

Il client comunica quindi al server quale account vuole utilizzare e richiede la propria casella di posta.

1.3. Interfaccia grafica (GUI)

L'interfaccia del client deve essere un po' più articolata, prendendo come riferimento Outlook o Thunderbird, NON Gmail.

Deve prevedere:

Un pannello con la lista delle email.
Pannelli per aprire i messaggi e visualizzarne il contenuto.
Funzionalità per inviare nuovi messaggi.

Per quanto riguarda la gestione delle cartelle:

CHISSENE FREGA DEI SENT E REMOVED.
NO TRASH.

L'interfaccia deve essere parzialmente responsive e reattiva.

Esempio: se il server consegna una nuova email al client 3, questa deve essere visualizzata immediatamente, senza che l'utente debba effettuare manualmente un refresh.

Interfaccia dinamica

Si utilizzano le Properties per rendere dinamica l'interfaccia.

Architettura MVC

Il client deve seguire il pattern MVC:

MODEL – Mailbox e utente.
VIEW – Scene Builder.
CONTROLLER – Nativo, permette di intercettare richieste di tipo diverso.

Si utilizza il pattern Observer/Observable attraverso le Properties, NON estendendo le classi Observer e Observable.

1.4. Funzionalità del client

Il client deve permettere di effettuare le seguenti operazioni:

Creazione di un messaggio.
Invio di un messaggio.
Lettura di un messaggio.
Inoltro di un messaggio.
Risposta a un messaggio.
Invio di email

Sia quando scriviamo una nuova email sia quando inoltriamo un messaggio, questo può essere inviato a N destinatari.

Risposta a un'email

Devono essere implementate entrambe le modalità di risposta:

Reply – Risposta al mittente del messaggio.
Reply all – Risposta a tutti i destinatari coinvolti nella conversazione.

Quando clicchiamo sul relativo bottoncino, si apre una nuova email già impostata, con sotto il body del messaggio precedente.

Dobbiamo implementare entrambe.

1.5. Controlli e validazioni effettuati dal client

Il mail client è cieco!

Può effettuare solamente controlli sintattici.

Ad esempio:

Può verificare che l'indirizzo email abbia una sintassi corretta.
Può verificare la possibilità di un account con un dominio come @gmail.com.
Quando invia una mail a qualcuno, può controllare che la sintassi di base dell'indirizzo sia corretta.

Il client, però, non sa se il destinatario a cui sta scrivendo esiste effettivamente sul server.

La verifica dell'esistenza degli account è responsabilità del server.

Anche al momento del login, il server deve rispondere comunicando eventuali problemi. Analogamente, se l'invio non è possibile, il server deve comunicarlo al client.

1.6. Thread e parallelismo lato client

Il client può avere un certo grado di parallelismo.

Sono previsti almeno:

Un Thread del client che effettua il polling.
Un Thread dell'interfaccia grafica, sul quale agisce effettivamente l'utente.

Il Thread di polling deve controllare periodicamente la connessione al server e verificare la presenza di nuove email.

Il client non deve andare in crash se il server si chiude.

Deve quindi gestire correttamente la perdita della connessione e le eventuali risposte negative del server.

2. Architettura Server
2.1. Struttura generale

È previsto un solo mail server.

All'interno del server deve essere presente una lista o una mappa, ovvero una collezione di account di posta elettronica.

Ogni account di posta elettronica appartiene a un utente e contiene:

La mailbox dell'utente, ovvero la lista dei messaggi che riceve.
Le informazioni dell'utente.

Il server deve quindi mantenere una collezione di mailbox, una per ciascun utente.

2.2. Gestione degli utenti e delle mailbox

Sono previsti:

3 utenti.
3 mailbox.

Questi sono gli account iniziali.

ATTENZIONE: dobbiamo pensare che il sistema possa arrivare a gestire 1000 utenti, anche se incidentalmente ne inseriamo solamente 3.

Nel codice non devono essere inseriti direttamente i nomi degli utenti: altrimenti, per aggiornare la lista degli utenti, sarebbe necessario ricompilare tutto.

Sul server potremmo avere non solo le mailbox, ma anche i nomi e i cognomi degli utenti.

Non sono previste nuove registrazioni di utenti.

2.3. Struttura dei messaggi email

Le email contengono solamente:

Autori.
Mittente.
Uno o più destinatari.
Body, che è SOLO testo.

I messaggi devono quindi avere una struttura dati comune, composta dai campi sopra indicati.

Non è necessario gestire allegati o contenuti diversi dal testo.

2.4. Persistenza dei dati

Per il server è obbligatorio gestire la persistenza dei dati.

Il server deve mantenere i dati anche dopo la propria chiusura e successiva riapertura.

La persistenza può essere implementata:

Su un singolo file.
Su più file differenti.

È possibile utilizzare il formato JSON.

La persistenza riguarda i dati gestiti dal server, in particolare gli account, le mailbox e i messaggi.

Per il client, invece, la persistenza non è obbligatoria.

2.5. Concorrenza e gestione dei Thread

Il mail server deve utilizzare i Thread.

Deve essere in grado di gestire contemporaneamente più richieste.

Esempio:

Se arrivano più richieste di invio email verso due mailbox appartenenti a utenti diversi, il server deve poterle gestire subito e contemporaneamente.

È quindi necessario gestire la mutua esclusione all'interno del server.

Accesso concorrente alle mailbox

È necessario prevedere l'accesso in mutua esclusione a una casella di posta elettronica.

Quando più richieste coinvolgono la stessa mailbox, bisogna evitare accessi concorrenti non controllati.

Si dovranno quindi utilizzare Lock o altri meccanismi di sincronizzazione.

Gestione delle richieste

Il server dovrà:

Ricevere sull'endpoint più richieste.
Passare le richieste ai Thread correnti.
Gestire la concorrenza tramite Lock o altri meccanismi.
Garantire la mutua esclusione quando più operazioni accedono alla stessa mailbox.

L'obiettivo è permettere la gestione contemporanea delle richieste, evitando conflitti nell'accesso ai dati.

2.6. Gestione dell'invio a più destinatari

Quando viene inviata una mail a più destinatari, può verificarsi che uno sia sbagliato e un altro sia corretto.

Il comportamento previsto è il seguente:

Prima di procedere con l'invio, il server controlla tutti i destinatari.
Se almeno uno dei destinatari è sbagliato, non invia la mail a nessuno.
Risponde solamente al client che ha effettuato la richiesta.
Comunica che l'invio non è stato possibile.

Quindi l'invio deve essere effettuato solamente se tutti i destinatari risultano validi.

Il client non può sapere autonomamente se un destinatario è presente sul server: questa verifica spetta al server.

2.7. Interfaccia amministrativa del server

Il server deve avere una piccola interfaccia grafica.

Le caratteristiche richieste sono:

Un'interfaccia molto piccola.
Architettura MVC.
Presenza di log.
Bottoni per spegnere e accendere il server.

L'interfaccia deve permettere di controllare le operazioni principali del server.

3. Comunicazione tra Client e Server
3.1. Principi generali della comunicazione

Client e server devono comunicare esclusivamente attraverso lo scambio di messaggi.

CLIENT E SERVER DEVONO ESSERE PROGETTI DIFFERENTI, APPLICAZIONI DIFFERENTI.

Non devono condividere gli stessi file.

Il client non deve leggere direttamente la mailbox che si trova sul server.

L'unico modo per accedere ai dati è attraverso la comunicazione client-server.

SI SCAMBIANO SOLO STRINGHE.

3.2. Protocollo di comunicazione

Il client manda richieste contenenti dei codici.

Il server deve interpretare questi codici per capire quale operazione deve eseguire.

Le operazioni previste comprendono:

Mandami la mailbox.
Mandami le nuove mail.
Ti sto mandando una nuova mail che devi distribuire.
Sto cancellando una mail.

Il protocollo deve permettere di distinguere i diversi tipi di richiesta.

Il server deve interpretare le richieste e fornire le risposte appropriate al client.

3.3. Connessione iniziale e login

Il rapporto tra client e server inizia quando il client si connette.

La prima operazione è il login, effettuato solamente attraverso l'indirizzo email.

Non è prevista una password.

Quando il client si connette, richiede al server di scaricare la propria mailbox.

Il server identifica l'account e restituisce i dati corrispondenti.

3.4. Polling

La comunicazione prevede un meccanismo di polling.

Quando il client si connette e richiede la propria mailbox, dovrà poi effettuare polling ogni 4 secondi.

Si tratta di una sorta di ping per verificare se sono presenti nuove email.

Deve essere sempre il client a iniziare la richiesta.

Il server non deve conoscere i link diretti ai client.

Il client ciclicamente richiede informazioni al server.

Il polling deve permettere di:

Verificare la connessione al server.
Controllare se sono arrivate nuove email.
Recuperare gli aggiornamenti della mailbox.

Il client non deve ricevere necessariamente l'intera inbox a ogni polling.

3 utenti ma non vuol dire che ogni polling mi deve arrivare tutta la inbox.

È necessario distinguere il recupero iniziale della mailbox dal successivo recupero delle nuove email.

3.5. Gestione delle nuove email

Quando un utente invia una nuova email, il client comunica al server la richiesta di invio.

Il server:

Interpreta la richiesta.
Controlla i destinatari.
Verifica che tutti i destinatari siano presenti.
Se tutti sono validi, distribuisce il messaggio nelle mailbox corrispondenti.
Se almeno uno è errato, non effettua alcun invio.
Comunica al client l'esito dell'operazione.

Il client destinatario recupera successivamente la nuova email tramite polling.

Non è necessario che il server contatti direttamente il client destinatario.

3.6. Risposte del server

Il server deve rispondere alle richieste dei client.

Tra le possibili risposte:

Restituzione della mailbox.
Restituzione delle nuove email.
Conferma o errore durante l'invio.
Comunicazione di un destinatario inesistente.
Risposta relativa al login.
Comunicazione di eventuali problemi di connessione.

Se il server non riesce a effettuare un'operazione, deve comunicarlo al client.

Ad esempio, quando il client tenta di inviare una mail a un account inesistente, il server risponde che l'invio non è stato possibile.

3.7. Gestione della connessione e arresto del server

È previsto un polling della connessione al server.

Se il server va giù, si deve notificare ai client.

I client non devono andare in crash se il server si chiude.

Devono quindi poter gestire la perdita della connessione e informare l'utente del problema.

Il controllo periodico della connessione è affidato al Thread di polling del client.

3.8. Socket e modalità di comunicazione

Per quanto riguarda i socket, il riferimento concettuale è il funzionamento delle richieste HTTP.

Ogni volta che una richiesta HTTP viene inviata (stateless):

Richiedo una connessione.
Invio la richiesta.
La connessione termina.

Il nostro sistema non è un'applicazione in real-time.

Non servono notifiche push.

Non è necessario creare canali persistenti, ovvero connessioni che rimangono aperte nel tempo.

Saranno i singoli client che ogni volta effettuano una richiesta.

NO SOCKET PERMANENTI.

La comunicazione deve quindi basarsi su richieste indipendenti, avviate dai client, senza mantenere connessioni permanenti.

3.9. Trasmissione dei dati: stringhe e oggetti

Su un socket si può far transitare di tutto, anche oggetti.

Tuttavia, trasportare oggetti significa fare due assunzioni:

Sia client che server sono implementati in JAVA.
Anche se entrambi utilizzassero JAVA, il client non è detto che abbia le stesse classi di libreria del server.

Ognuno avrà la propria implementazione interna.

Quello che conta è la comunicazione client-server.

L'unico punto di contatto sono i campi della mail.

Dal punto di vista dei dati esiste un formato comune: ad esempio, sender è sender per entrambi.

Tuttavia, non trasmetteremo oggetti.

Verranno trasmesse solamente stringhe, che client e server interpreteranno secondo un formato comune.

4. Vincoli e Decisioni Progettuali Trasversali
4.1. Separazione dei progetti

Il client e il server devono essere due applicazioni distinte.

Sono previsti:

Due progetti Java FXML.
Se si vuole unificare la gestione, due Maven differenti.
Due pom.xml distinti.

NON SOTTO PACKAGE DELLA STESSA APPLICAZIONE.

La separazione deve essere mantenuta anche a livello di struttura dei progetti.

Client e server devono poter essere sviluppati, avviati e gestiti indipendentemente.

Non devono condividere file o accedere direttamente alle rispettive strutture interne.

4.2. Pattern architetturali

Il pattern MVC viene utilizzato sia per organizzare il client sia per organizzare l'interfaccia del server.

Per il client:

MODEL: mailbox e utente.
VIEW: Scene Builder.
CONTROLLER: nativo, permette di intercettare richieste di tipo diverso.

Per rendere dinamica l'interfaccia si utilizzano le Properties.

Per il pattern Observer/Observable:

Pattern Observer Observable, CON LE PROPERTIES, NON ESTENDENDO OBSERVER OBSERVABLE.

4.3. Assunzioni e limitazioni del sistema

Le seguenti assunzioni definiscono il perimetro del progetto:

Non è prevista autenticazione tramite password.
Il login avviene solamente tramite email.
Non sono previste nuove registrazioni di utenti.
Sono inizialmente presenti 3 utenti e 3 mailbox.
Il sistema deve essere progettato pensando a una possibile espansione fino a 1000 utenti.
Si assume che ogni persona utilizzi un solo dispositivo.
Si assume che gli utenti utilizzino esclusivamente le proprie email.
Il body delle email contiene solamente testo.
Non sono previsti allegati.
Il client non deve conoscere direttamente le strutture dati interne del server.
Il client non deve leggere direttamente le mailbox sul server.
La persistenza è obbligatoria solamente lato server.
Non sono necessari canali di comunicazione permanenti.
Non sono necessarie notifiche push.
Il sistema non è un'applicazione real-time.
Il polling viene effettuato dal client ogni 4 secondi.
4.4. Configurabilità

È presente il requisito:

Permette di cambiare impostazione senza

La frase originale risulta incompleta e non specifica quale impostazione debba essere modificata né quale sia la condizione successiva a «senza». Il requisito viene quindi mantenuto senza attribuirgli un significato non esplicitato.

Riepilogo delle responsabilità
Funzionalità	Client	Server
Interfaccia grafica	Sì	Sì, minimale
MVC	Sì	Sì
Gestione delle mailbox	Visualizzazione della propria mailbox	Gestione di tutte le mailbox
Persistenza	Non obbligatoria	Obbligatoria
Login	Invia l'indirizzo email	Verifica l'account
Password	Non prevista	Non prevista
Validazione sintattica email	Sì	Può effettuare ulteriori verifiche
Verifica esistenza destinatari	No	Sì
Invio email	Richiede l'invio	Distribuisce i messaggi
Invio a più destinatari	Sì	Verifica tutti i destinatari
Lettura email	Sì	Fornisce i messaggi
Inoltro email	Sì	Distribuisce i messaggi
Reply	Sì	Gestisce la richiesta di invio
Reply all	Sì	Gestisce la richiesta di invio
Polling	Sì, ogni 4 secondi	Risponde alle richieste
Thread	Polling e interfaccia grafica	Gestione delle richieste concorrenti
Mutua esclusione	Non centrale	Necessaria sulle mailbox
Gestione errori di connessione	Sì	Deve gestire le richieste
Comunicazione	Invia e riceve stringhe	Riceve e invia stringhe
Riepilogo del flusso operativo
L'utente avvia il client.
Il client stabilisce una connessione con il server.
Il client comunica il proprio indirizzo email.
Il server verifica che l'account esista.
Il server restituisce la mailbox dell'utente.
Il client visualizza i messaggi nell'interfaccia grafica.
Il Thread di polling del client effettua periodicamente richieste al server, ogni 4 secondi.
Il server risponde comunicando eventuali nuove email.
Il client aggiorna dinamicamente l'interfaccia tramite le Properties.
Quando l'utente scrive o inoltra un messaggio, il client invia al server una richiesta contenente i dati del messaggio.
Il server controlla tutti i destinatari.
Se tutti i destinatari sono validi, il server distribuisce il messaggio nelle rispettive mailbox.
Se almeno un destinatario è errato, il server non invia il messaggio a nessuno e comunica l'errore al mittente.
I client destinatari recuperano le nuove email attraverso il polling.
Il server mantiene persistenti i dati delle mailbox e dei messaggi.

Principio fondamentale del progetto: client e server sono due applicazioni indipendenti che comunicano esclusivamente attraverso lo scambio di stringhe. Il server è responsabile della gestione centralizzata degli account, delle mailbox, della persistenza e della concorrenza; il client è responsabile dell'interazione con l'utente e dell'aggiornamento della propria interfaccia.