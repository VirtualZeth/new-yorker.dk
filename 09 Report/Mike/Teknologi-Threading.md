# Teknologi

Trådeprogrammering(Mike)
***MANGLER KODEEKSEMPLER*****

Begge applikationer kan passende benytte sig af trådeprogrammering til at udføre opgaver i baggrunden, som er kritiske for applikationernes evne til at udføre deres use cases på en hensigtsmæssig måde, således at disses brugergrænseflade ikke bliver blokeret. Dette medfører en bedre oplevelse for brugeren.

I Android appen benyttes der tråde til at håndtere generering af CAD preview, og til at sende forespørgslen via mail. I desktop admin-applikationen benyttes der tråde til diverse database kald, eksempelvis er “Tilføj vare”-use casen et tilfælde hvor der benyttes en separat tråd.

Ved at implementere tråde i applikationerne demonstreres det at følgende læringsmål fra faget programmering og teknologi bliver opfyldt:

• anvende centrale metoder og teknikker til at designe og konstruere programmer som samarbejdende processer/tråde

• anvende centrale metoder og redskaber til synkronisering af processer og tråde


En overvejelse der er blevet gjort er hvorvidt det er nødvendigt at benytte sig af en implementation af en såkaldt ExecutorService, eller om det er mest hensigtsmæssigt at danne en ny tråd hver gang der laves asynkrone metodekald.

En klar fordel ved at benytte ExecutorService er det lave overhead forbundet ved hver opgave denne blive givet, da der ikke skal spawnes en ny tråd hver gang, desuden slipper man også for at skulle kalde en tråds start() metode hvilket reducere kognitivt overhead, hvilket er generelt ønskværdigt for asynkrone implementationer.
Ved brug af ExecutorService spawner man en enkelt gang en såkaldt threadpool, hvori disse tråde benyttes til at håndtere de asynkrone opgaver.
Der er et betydeligt overhead forbundet ved dannelse af threadpools, dog skal dette kun gøres en enkelt gang ved start af applikationen, omvendt spares der ressourcer da der ikke skal startes en ny tråd hver gang man giver ExecutorService en opgave, da den bruger de tilgængelige tråde i dets pool.

På baggrund af disse overvejelser har vi besluttet os for bruge ExecutorService, da denne faciliterer nemmere og mere effektiv håndtering af tråde i applikationerne.

