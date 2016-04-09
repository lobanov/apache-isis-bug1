The project illustrates an issue with Apache Isis Command add-on. It fails over when a background thread (it could be a Quartz job in real life) tries to schedule a domain action to be executed in the background via BackgroundService#execute(...).

How to use:

1. Clone the repo
1. Do "mvn clean package" and then "mvn jetty:run-war"
1. Open you browser and navigate to http://localhost:8080/wicket
1. Login with sven/pass
1. Create new SimpleObject with any name
1. Navigate back to the home page
1. Click on 'Schedule Command' button and enter anything
1. There should be exception in the console

Home page action scheduleCommand(String) starts a new thread, which passes control to domainapp.app.services.homepage.BackgroundJob class. The latter extends AbstractIsisSessionTemplate to simulate the environment of a Quartz job executing some business logic. The job tries to use BackgroundService#execute() to schedule an update of the name of the first SimpleObject instance it finds in the database, but it fails to write corresponding CommandJdo instance into the database.
