OC17 Send Forespørgsel

Operation:

angivNote(String forhandler)

Cross-reference:

Bestil_Væg

Pre-conditions:
   
    • Request forespørgsel eksisterer
	
     • ContactForm kontaktformular eksisterer
  
     • kontaktformular er associeret med forespørgsel
   
     • Basket kurv eksisterer
   
     • kuv er associeret med kontaktformular
   
     • MailService mailService eksisterer
	
Post-conditions:

    • der er blevet sendt en forespørgsel via mailService
