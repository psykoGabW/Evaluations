# Session2_EvaluationJDBC

> First... Sorry for my english... Writing in progress

## Deployment
Currently, this program is launched through Eclipse.
Below, some requirements to fulfill before launching it.

### Database Creation
2 files are available in directory *__resources__* to initiate database.
  * dbInit.sql  : It contains sql queries to create all necessary tables in a given schema.
  * dbInsertions.sql : This file contains some data to insert in tables. Feel free to add some more in it before launching this program.
</p>Those 2 files will delete all previous tables and previous data contained in specified schema.

### Connection to database
This program get necessary data for database connection in a properties file.</br>
A template is available in directory *__resources__* : AppStoreDAO.properties.template</br>
This file has to be renamed to AppStoreDAO.properties. It must be placed to the root of your project directory.

## User Guideline
This program is so userfriendly that user guideline is not necessary....<br/>
But just in case...The purpose is to simulate an online store of ... videogames :)<br/>
You can buy credits on this store and use them to buy videogames.<br/>
With this program you can manage :
  * customers
  * videogames (references)
  * sales of credits
  * sales of videogames

For each category, you can :
  * list all items
  * create item
  * delete item

Adding records on *credits sales* and on *purchased video games* will impact *customers* by changing his available credits.

## Troubleshooting
A lot... I guess.<br/>
I don't had a lot of time to test...

## Technical documentation
### Database
I try to use some PF pattern to name tables (prefix with TB_).
It's important to notice that postgresql is not case sensitive... except for schema matching. :-( <br/>
I choose to store date and datetime <br/>

### Java
