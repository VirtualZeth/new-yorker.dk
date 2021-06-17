# CRUD App

## Motivation

Hvis vi vælger at mobilapplikationen skal holde alle data som pris, tilføjelser, email mm. Hvad sker der så når vores klient får ny e-mail, ændrer priserne på deres varer eller vil give deres kunder mulighed for yderligere tilføjelser til deres vægge? De bliver nødt til at betale en udvikler for at gå ind og ændrer deres data og genudgive applikationen. Vi blev derfor enige med vores klient om at der var behov for at de selv kan ændre deres data uden at det kræver en genudgivelse af applikationen eller ekstern hjælp.

Vi havde flere muligheder for at løse dette problem. Først og fremmest var der behov for at kunne lagre data eksternt fra applikationen, således at deres data kan tilgås og ændres uden at ændre selve applikationen. Desuden skal applikationen samtidig have mulighed for at tilgå den data. Vi kunne løse dette ved at anvende en database og få applikationen til at hente den data, således at vores klient kan gå ind og ændre i selve databasen. Det er en god idé at anvende en database til lagringen af data, men det at klienten selv skal ændre i databasen for at få lavet de ændringer de har behov for, er uønsket, eftersom at klienten selv har givet udtryk for ikke at have en særlig trænet teknologisk forståelse og selv for en udvikler kan de være nemt at lave fejl, hvis der skal laves ændringer direkte i et datasæt. Vi tænkte derfor at der var behov for software med CRUD funktionalitet. Dette vil nemlig give klienten mulighed for at ændre i deres data, uden ekstern hjælp og brug for genudgivelse af applikationen og det vil give os mulighed for at lave mere brugervenlig software som de kan anvende.

## Planlægning

Der var flere muligheder vi så, for udviklingen af benævnte CRUD software. Den første mulighed var at integrere CRUD systemet i mobilapplikationen. Dette ville give klienten mulighed for nemt at tilgå og ændre i deres data direkte fra samme applikation, men for brugeren, der skal anvende applikationen, ville dette ikke have relevans og vil kun gøre applikationen mere uoverskuelig. Det ønsker vi ikke da vores klient netop gerne vil holde applikationen simpel og overskuelig.

En anden mulighed ville være at lave en desktop applikation, men det ville ikke give fleksibiliteten af at kunne ændre i deres data fra flere platforme. Vi valgte derfor at bygge en web applikation. At anvende en webapplikation vil give klienten mulighed for at tilgå og ændre i deres data fra flere platforme, holde mobil applikationen fri for uoverskuelighed, behov for genudgivelser og ekstra omkostninger der kunne følge med administrationen af databasen. En web applikation vil dog være offentligt tilgængelig, hvilket er positivt i forbindelse med tilgængelighed, men negativt i forbindelse med sikkerhed. Det er dog ikke et problem da applikationen ikke kommer til at være forbundet med personlig eller anden form for private og sårbare data.

Eftersom at jeg var den eneste i gruppen der havde erfaring med udviklingen af web applikationer, og at vi på daværende tidspunkt var i begyndelsen af tredje uge, med 2 applikationer under udvikling, vurderede vi at det ville være bedst at allokere vores ressourcer således at jeg tog mig af udviklingen af webapplikationen, imens resten af gruppen fortsatte udviklingen af mobil applikationen.

## Krav

Det er vigtigt at forstå præcist hvilke features applikationen skal have og hvilke features der skal prioriteres inden påbegyndelsen af udviklingsfasen. Ganske rigtigt er det en CRUD applikation, men jeg skal jo først finde ud af hvordan den skal se ud, hvordan jeg tror at vores klient ønsker at interagere med den og hvordan dens features skal fungere, og spille sammen.

Applikationen skal først og fremmest give vores klient mulighed for at logge ind med en bruger, da applikationen er offentlig tilgængelig og vi ikke ønsker at andre end vores klient skal kunne ændre i deres data.

Applikationen skal også give vores klient et godt og overskueligt overblik over tilføjelser der kan lægges til en væg, herunder deres pris, varenummer og navn. Det kunne f.eks. være at der ønskes en bestemt type dørhåndtag, glasfarve mm. Der skal kunne tilføjes, ændres og fjernes nye mulige tilføjelser. Disse tilføjelser skal kunne inddeles i kategorier således at de kan blive fremvist korrekt i mobil applikationen.

![Mockup](https://raw.githubusercontent.com/VirtualZeth/new-yorker.dk/master/03%20Design/mockup/forside.png)

På figuren ses hvordan tilføjelser skal fremvises i mobil applikationen. Læg mærke til de to kategorier, døre og paneler, det er meningen at kasserne der indeholder tilføjelser skal genereres baseret på indholdet i databasen. Det skal derfor også være muligt at tilføje og fjerne kategorier fra databasen.

Det skal også være muligt at kunne opbevare vores klients og leverandørernes kontakt mails da de kunne ændre sig, samt et link til deres hjemmesides katalog, da den bliver anvendt i applikationen.

## Stak Analyse

Da vi havde besluttet at vi ville anvende en database til persistens, kiggede jeg først på mulighederne mellem databaser. Jeg har selv erfaring med MySql, MongoDB og i forbindelse med undervisningen har vi kigget på SQLite og Firebase. Eftersom at tid er vores mest værdifulde ressource i dette projekt, giver Firebase nogle klare fordele. Platformen tilbyder ”Back-end as a Service” (BaaS), hvilket vil sige jeg ikke behøver at lave back-end delen af programmet selv, men at jeg blot kan bruge deres API til at interagere med databasen. Desuden er Firebase også nem at bruge både i forbindelse med web udvikling, men også android udvikling og det er ligeledes nemt at koble begge applikationer til samme database, hvilket netop er det vi ønsker. Den eneste ulempe ved Firebase i dette tilfælde, var at jeg ikke havde udviklet et program med servicen før, så det ville tage, og tog, lidt ekstra tid at lære.

Da Firebase tager sig af back-end, var der i virkeligheden kun front-end teknologier tilbage at overveje. Da jeg er mest komfortabel med react.js, et javascript front-end framework, og med vores sparsomme ressourcer i tankerne, gav det god mening at anvende det. Jeg ved også af erfaring at styling af en hjemmeside kan tage rigtig lang tid, derfor er der redskaber som bootstrap og materialize, der kan hjælpe dig med hurtigt at få stylet en responsiv hjemmeside. Ved nærmere eftertanke, burde jeg nok have valgt materialize til styling af applikationen, men af personlige grunde var jeg interesseret i bootstrap og ville gerne lære at anvende det. Det var ikke svært at skifte fra den ene teknologi til den anden, da de fundamentalt fungerer på samme måde, men det tog lidt ekstra tid at udvikle. Det er ikke et problem at lære ny teknologi at kende, men vores klient ønsker blot hurtig og effektiv udvikling af deres produkt.

Til state management anvender jeg redux. Mere om det senere.

## Teknologisk fremgangsmetode

Mit udviklingsmiljø består primært af Visual Studio Code og terminalen hvor jeg bruger git til versionering og npm til pakkehåndtering. Til opsætningen af projektet kørte jeg kommandoen:

```
git init && npm init -y
```

Dette laver en folder kaldet .git til at håndtere lokale fil ændringer, branches, commits mm. Samt en package.json fil hovedsageligt til at holde styr på dependencies. For hurtigt at starte et React projekt brugte jeg kommandoen:

```
npx create-react-app .
```

### Kort introduktion til React.js

React er et framework som fungerer som en single page applikation. Den måde det fungere på er at index.html filen, som er indgangen til hjemmesiden, holder et div tag med et id kaldet ”root”:

```html
<div id="root"></div>
```

Et div tager bare en container der kan holde yderligere markup. React applikationer er sammensat af komponenter og dette div tag holder disse komponenter når siden bliver læst. Et komponent kunne f.eks. være en navigationsbar, en tabel, en side, eller tekst sektion på en side.

State er reacts måde at holde information på, ligesom en variabel. Forskellen er at hvis der forekommer ændringer i state, vil komponenter der er afhængige af den state genindlæses og state kan blive anvendt til at bestemme hvad information og komponenter der skal vises frem for brugeren.

React anvender JSX, som også er et markdown language og det ligner næsten html. Forskellen ligger på nogle navneændringer som f.eks. at class hedder className, men den største ændring, og det der gør at det bliver brugt sammen med react, er at man kan skrive kode direkte i sproget. Mere om JSX, state, komponenter senere.

### Firebase opsætning

Firebase er ikke en database du tilgår gennem HTTP, som med andre databaser. Firebase anvender en WebSocket forbindelse, som er en vedholdende forbindelse, hvilket gør den meget hurtigere og det gør det også muligt at opdatere data i “real time”. Når applikationen skal oprette forbindelse til firebase gennem metoden firebase.initializeApp, skal den bruge et objekt der indeholder konfigurationsdata.

```js
const firebaseConfig = {
  apiKey: process.env.REACT_APP_FIREBASE_apiKey,
  authDomain: process.env.REACT_APP_FIREBASE_authDomain,
  databaseURL: process.env.REACT_APP_FIREBASE_databaseURL,
  projectId: process.env.REACT_APP_FIREBASE_projectId,
  storageBucket: process.env.REACT_APP_FIREBASE_storageBucket,
  messagingSenderId: process.env.REACT_APP_FIREBASE_messagingSenderId,
  appId: process.env.REACT_APP_FIREBASE_appId,
};

firebase.initializeApp(firebaseConfig);

export default firebase;
```

Disse konfigurationer som f.eks. API nøglen, skal ikke være offentligt tilgængelige da de giver adgang til databasen. React kommer med dotenv pakken der gør det muligt at bruge environment variabler som kan gemmes i en .env fil. Denne fil vil ikke blive lagt på github da den indeholder sårbar data. Konfigurationerne kan findes på firebase ved oprettelsen af en applikation.

Jeg eksporterer derefter firebase klassen som jeg så kan bruge i projektet og så er det ellers nemt at komme igang efter at du har installeret firebase pakken.

## Login Feature Code Coverage

Jeg ville gerne lave 2 komponenter som skulle fungerer som sider i webapplikationen. Den første hedder Dashboard.js som giver brugeren CRUD funktionalitet og den anden hedder Login.js som skal være den første side man kommer ind på. Jeg bruger pakken react-router-dom til at håndtere stier og side komponenter.

```js
return auth.isAuth ? (
  <Fragment>
    <Toolbar />
    <Table />
  </Fragment>
) : (
  <Redirect to="/" />
);
```

Det er vigtigt at brugere der ikke er autoriserede ikke kan tilgå /dashboard sti-komponentet, derfor anvender jeg state og en ternary operator til at bestemme hvad JSX, Dashboard.js komponentet skal indlæse. “auth” er et prop der kommer fra redux, i react kan du sende props gennem komponenter således at information, som f.eks. state kan blive delt på tværs af komponenterne. “auth” er et objekt og holder en boolean kaldet isAuth som jeg bruger til at tjekke om brugeren er logget ind. Hvis brugeren er logget ind, vil komponenterne Toolbar.js og Table.js indlæses, men hvis brugeren ikke er logget ind vil de blive sendt til forsiden.

```js
useEffect(() => {
  const close = firebase.auth().onAuthStateChanged((user) => {
    if (user && isAuth === false) setIsAuth(true);
    else if (!user && isAuth === true) setIsAuth(false);
  });
  return close;
}, [isAuth, setIsAuth]);
```

Jeg anvender en hook kaldet useEffect til at instantiere en observe. useEffect er en funktion der tager 2 parameter, en funktion der f.eks. skal køre når komponentet genindlæses og et array af dependencies. Hvis der er givet det andet parameter, vil funktionen kun køres hvis der forekommer ændringer til det array af variabler der er blevet givet. Dette bruger jeg til at tjekke om en bruger er logget ind, da firebase giver mulighed for at bruge en onAuthStateChanged observer. Da firebase fungerer som en BaaS, holder den også styr på brugere i systemet. Funktionen givet i parameteren på linje 17 vil blive kørt hver gang der sker en ændring i bruger objektet. Hvis brugeren bliver logget af vil observeren sætte user til null, ellers vil user være et bruger objekt. Dette bruger jeg til at sende brugeren enten til forsiden eller til dashboard siden.

Observeren returnerer en funktion som jeg returnerer til useEffect. Denne funktion vil blive kørt og stoppe observeren når komponentet frakobles, således at der ikke skabes unødvendigt mange observere.

```js
const onSubmit = (e) => {
  e.preventDefault();
  firebase
    .auth()
    .signInWithEmailAndPassword(formData.email, formData.password)
    .then(({ user }) => {
      console.log(`Logged in as ${user.email}`);
    })
    .catch((error) => {
      console.log(error.code);
      console.log(error.message);
    });
};
```

Når vores klient trykker på log ind knappen i applikationen, vil denne funktion onSubmit bliver kørt. e.preventDefault er blot en funktion på event objektet, der stopper vores form submit fra at genlæse siden. På linje 28 sender vi email og password til firebase med signInWithEmailAndPassword metoden. Denne metode returnerer et promise da der laves et asynkront kald til firebase serveren. Et promise fungerer som en placeholder, imens der ventes på at data returneres fra serveren. Et asynkront kald gør det muligt for programmer at tilsidesætte opgaver til der f.eks. returneres data og skal ikke forveksles med en ny tråd der køres separat.

Hvis brugeren i dette tilfælde har skrevet den korrekte email og password, vil funktionen i “then” blive kørt. Hvis der skete en fejl som f.eks. at mailen blev skrevet forkert, vil funktionen givet til “catch” metoden kører og vise fejlen i konsollen. Jeg ønsker senere at bruge denne funktion til at fremvise en fejlbesked for brugeren, hvis firebase sender en fejl tilbage.

Brugeren logges derefter ind grundet observeren.

## CRUD Feature Code Coverage

Dashboard komponenten består af et Table og et Toolbar komponent. Table komponentet bliver brugt til at fremvise alle de mulige tilføjelser, kaldet vare, der kan tilføjes til en væg med varenummer, navn og pris. Der skal også vises kategori, når alle vare skal vises, og det mangler for nu at blive implementeret. Det er muligt at slette og redigere hver tilføjelse.

![Toolbar komponent](https://github.com/VirtualZeth/new-yorker.dk/blob/master/10%20Assets/Website_Dashboard.png)

Ovenover ses toolbar komponentet og det giver mulighed for at sortere efter kategori, tilføje og slette kategorier og tilføje en vare. Klienten kan også logge ud.

Databasen indeholder 2 collections. Products og Categories.

```js
useEffect(() => {
  const close = firebase
    .firestore()
    .collection("products")
    .onSnapshot((querySnapshot) => {
      const items = [];
      querySnapshot.forEach((doc) => items.push({ ...doc.data(), id: doc.id }));
      setProducts(items);
    });
  return close;
}, []);
```

Når varerne skal indlæses i komponentet, anvender jeg firebase firestores onSnapshot metode. Metoden “lytter” efter ændringer i den valgte collection. Hvis der er forekommet ændringer, vil funktionen givet som parameter blive kørt. I funktionen looper jeg igennem querySnapshot arrayet, som holder alle dokumenter, med forEach metoden. forEach metoden tager en funktion som parameter hvor det første parameter vil være elementet i arrayet. doc.data() returnerer den relevante data fra dokumentet, men jeg ønsker også at bruge et id til hvis klienten gerne vil slette en vare igen. Derfor anvender jeg en spread operator til at “sprede” objektets data ud i det nye objekt og tilføjer doc.id som id til samme objekt. Derefter skubbes det nye objekt som nu holder varenummer, navn, kategori, pris og id ind i et array kaldet “items”. Når alle dokumenter er gennemgået, bliver det nye items array tilføjet til komponentens state og fremvist for brugeren.

```html
<tbody>
  {products.map((e) => tableItem(e))}
</tbody>
```

map er en metode der fungerer næsten som forEach, men hvert gennemgået element vil blive returneret og kan gemmes som en array variabel. Læg mærke til at ES6 opdateringen til javascript gør det muligt at bruge arrowfunctions som, hvis brugt på en linje, returnerer direkte uden behov for at bruge nøgleordet return eller klammer.
