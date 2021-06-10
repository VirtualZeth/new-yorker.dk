OC09 gemÆndring

Operation:

gemÆndring(væg)

Cross-reference:

Bestil_Væg

Pre-conditions:

    • SharedViewmodel viewmodel eksisterer
    
    • Basket basket eksisterer
    
    • basket er associeret med viewmodel
    
    • Væg væg eksisterer
    
    • væg er associeret med basket
    
    • viewmodel.currentWall er sat til væg	
	
Post-conditions:

    • En ny Wall instans nyVæg er blevet skabt
    
    • væg er blevet afassocieret fra basket
    
    • nyVæg er blevet associeret med basket
