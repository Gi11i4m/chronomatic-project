ChronoMatic
===========

Inleiding:
Chronomatic is een systeem voor timemanagement, het loggen van tijd die aan bepaalde taken van een project worden gespendeerd.


TODO Webservice:
	- Update tijdspanne op server aanpassen
	- bij verwijderen van taak:
	Fout met het uitvoeren van queryjava.sql.SQLException:
	You can't specify target table 'taken' for update in FROM clause


TODO Client:
	- padding Jlabels
	- loading animatie bij inloggen (en andere dingen?) inloggen met thread
	- register new user
	- Logindialog (spinner) laten zien tot logindialog is geladen
	
	- Overschakelen naar Client tab bij selectie van < new client > in projectClientsJCombobox
	- Gegevens behouden indien save project zonder geldige client
	- Bug bij het verwijderen van currentProject -> currentProjectIndex resetten, zie FIXME
	- Client-dropdown bij project-tab refreshen bij aanmaken van nieuwe client
	- Bericht weergeven in taskslist/task-tab indien geen currentProject selecteerd?	

	- Dialogbox voor gegevens indien extern account
	- saveShizzle -> updateShizzle, saveNewShizzle -> createShizzle of insertShizzle

	- Help tab? Eerste x openen, help dialog
	- Event handlers (bv. list) combineren