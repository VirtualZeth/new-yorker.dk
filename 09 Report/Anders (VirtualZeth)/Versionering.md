# Versionering

Vi har anvendt Github til at holde styr på alle vores filer i dette projekt. Programkode, artefakter, og rapportafsnit kan alle findes findes under vores fælles repository.

Vi har også taget brug af githubs eget projektstyringssystem ”Github Projects” som har gjort det muligt for os at automatisere nogle organiseringsprocesser. Vi har anvendt issues til at beskrive manglende features, artefakter og dokumentation. Vi har anvendt branches til at holde vores arbejde adskilt, således at vi ikke overskriver hinandens arbejde og at vi får muligheden for at godkende hinandens arbejde gennem pull requests.

”git” er adskilt fra github og er et versionerings kontrol system. ”git” bruges til at holde styr på filændringer, commits, branches mm. lokalt på din computer i den mappe dit projekt ligger i. Når vi opretter et projekt i Android Studio eller IntelliJ IDEA, vil git allerede selv være initialiseret og klar til brug gennem en grafisk brugergrænseflade.

I forbindelse med udviklingen af CRUD applikationen (mere om det senere), anvendte jeg git CLI i stedet for GUI, eftersom at ved opsætningen af en web applikation i visual studio code, er der ikke et præinstalleret GUI ved et projekts opstart, også fordi det er det jeg er vant til at bruge og at CLI er mere fleksibelt end GUI i sidste ende alligevel.

Når jeg arbejder med git CLI initialiserer jeg først git med kommandoen:

```
git init
```

Derefter tilføjer jeg en remote med link til det repository jeg vil arbejde med og trækker master branchen ind i projektet:

```
git remote add origin https://github.com/VirtualZeth/new-yorker.dk
git pull origin master
```

Når jeg så ønsker at implementere en ny feature til et program, skifter jeg først til en ny branch, for at holde nye features separeret, og mulige at klargøre til review før de trækkes ind i hoved branchen.

```
git checkout -b NEW_BRANCH_NAME
```

Derefter implementerer jeg den nye feature med commits undervejs og pusher til branchen, hvorefter der kan oprettes en pull request til review med den nye feature.

```
nano NewFeature.java
git add .
git commit -m "Add new print feature"
git push origin NEW_BRANCH_NAME
```
