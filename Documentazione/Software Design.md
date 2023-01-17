<h3>Software Design </h3>
Per lo sviluppo dell’applicazione il team si è basato sulla programmazione con subroutine, <br>
utilizzando anche dove necessario lo stile con i dati astratti, allo scopo di suddividere il codice in parti che abbiano funzionalità distinte tra loro mediante l’approccio OOP<br>
Il team si è inoltre preposto di scrivere codice basandosi sui concetti di astrazione e modularità, con l’obiettivo finale di avere un programma che abbia:<br>
 &emsp; &emsp;●	Basso accoppiamento, ovvero avere moduli altamente indipendenti tra di loro; <br>
 &emsp; &emsp;●	Alta coesione, ovvero avere una alta correlazione delle funzionalità presenti dentro un singolo modulo;<br>
<h3> Pattern utilizzato<br></h3>
Nello sviluppo del codice, in particolare lato back-end, il team ha utilizzato il pattern:<br>
 &emsp; &emsp;“Singleton”: Nello specifico, è stato utilizzato per rendere Mappa e Gestore unici,rendendo impossibile creare altre istanze.<br>
 &emsp; &emsp;“Strategy”: Permettendoci di personalizzare il tipo di stampa su console, in particolare:<br>
 &emsp; &emsp; &emsp;●	printTot<br>
 &emsp; &emsp; &emsp;●	printNome<br>
