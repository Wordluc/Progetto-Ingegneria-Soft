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
 
 <h3>Complessità ciclomatica di McCabe</h3>
 
 ![image](https://user-images.githubusercontent.com/40872910/213007982-c40a82b8-ee08-4214-8a84-e4b9efe9ce9b.png)<br>
 ![McCABE](https://user-images.githubusercontent.com/47183391/213032336-0c231b24-463d-4774-aab1-53ffd0840579.jpg)

 
 
 
 
 vc = r - n + p + 1<br>
 Il metodo utilizzato permette di stampare i nomi dei player togliendo il player con nome elementToRemove.<br>

 <h3>Metriche del software:</h3>
 
 ![image](https://user-images.githubusercontent.com/40872910/213008133-c21e9288-8017-4a67-b778-66df67e90cdd.png)<br>
  
  Legenda
  &emsp;●	verde chiaro: basso
  &emsp;●	verde scuro: basso-medio
  &emsp;●	giallo: medio-alto<br>
  
  Nei diagrammi sono mostrate le metriche, ritenuti da noi più importanti, per avere una buona manutenibilità:<br>
  &emsp;●	Coesione<br>
  &emsp;●	Accoppiamento<br>
  &emsp;●	Complessità<br>
  &emsp;●	Linee di codice per classe<br>

I diagrammi sono stati prodotti mediante l’uso di CodeMR, un'estensione di Intellij.<br>


