# University Timetable
Timetable management application for university. Accounting students, teachers and groups, faculty, departments, subjects. Creating schedule items. 

## Deployment

### Configure Tomcat
Edit the conf/context.xml file in your Tomcat installation. 
Insert the ResourceLink element inside the Context element, directly after the opening <Context.../> line:
```
<ResourceLink 
          name="jdbc/UniversityTimetableApplication"
          global="jdbc/UniversityTimetableApplication"
          type="javax.sql.DataSource"/>
```
Edit the conf/server.xml file in your Tomcat installation. 
Insert the DataSource Resource element inside the Context element, directly after the opening <Context.../> line,  before Manager:
```
<Resource name="jdbc/UniversityTimetableApplication" auth="Container" type="javax.sql.DataSource"
          username="postgres"
          password="postgres"
          driverClassName="org.postgresql.Driver"
          url="jdbc:postgresql://localhost:5432/yourDatabaseName"
          maxTotal="25"
          maxIdle="10"
          validationQuery="select 1" /> 
```
Replace the username and password parameters with the correct values for your database
In the url parameter, replace the word 'yourDatabaseName' with the name of the database your UniversityTimetableApplication data will be stored in.