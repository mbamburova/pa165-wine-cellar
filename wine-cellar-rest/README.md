# PA165 Wine Cellar - REST module guide

## Install

Run server by command:

```
mvn tomcat7:run
```

**Note:** If your default port (8080) is occupied e.g. by web server you can run rest module in different port by using

```
mvn -Dmaven.tomcat.port=XXXX tomcat7:run  
```
*(XXXX is port)* </br>

**Note 2:** If you use the Windows commandline you should adjust commands because of escaping

##Main Controller

Used as the main page. Shows possible reachable entities

**API URL** (GET method)

```
/pa165/rest/
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/
```

##Wine List entity

Below are the examples of the REST requests.
**Please change port in commands accordingly to your server**

###Get wine lists

**API URL** (GET method)

```
/pa165/rest/winelists
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/winelists
```

###Get wine lists by title

**API URL** (GET method)

```
/pa165/rest/winelists?name={name}
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/winelists?name=Silvester
```

###Create wine list

**API URL** (POST method)

```
/pa165/rest/winelists/create
```

**Example**

```
curl -i -X POST -H "Content-Type: application/json" --data '{"name":"Silvester","date":"31.12.2016"}' http://localhost:8080/pa165/rest/winelists/create
```

### Get wine list by id

**API URL** (GET method)

```
/pa165/rest/winelists/{id}
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/winelists/1
```

###Update wine list

**API URL** (PUT method)

```
/pa165/rest/winelists/{id}
```

**Example**

```
curl -i -X PUT -H "Content-Type: application/json" --data '{"Nový Rok":"","date":"1.1.2017"}' http://localhost:8080/pa165/rest/winelists/1
```

###Delete wine list

**API URL** (DELETE method)

```
/pa165/rest/winelists/{id}
```

**Example**

```
curl -i -X DELETE http://localhost:8080/pa165/rest/winelists/1
```