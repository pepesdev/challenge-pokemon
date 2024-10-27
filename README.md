## Dockerizacion servicios SOAP y REST

Descripcion General del proyecto.

## Tabla de Contenidos

- [API SOAP](#api-soap-pokemon)
  - [Resumen](#resumen)
  - [Peticiones](#peticiones)
    - [getPokemonRequestByName](#get-pokemon-byName)
    - [getHeldItemsRequest](#get-pokemon-heldItems)
    - [getPokemonRequestAbilities](#get-pokemon-abilities)
- [API REST](#api-rest)
  - [Resumen](#resume-1)
  - [Peticiones](#peticiones-1)
    - [getAll](#input-files)
    - [finById](#oracle-tables)
- [Docker Compose](#docker-compose)
  - [Resumen](#resume-3)
  - [Levantar entorno](#levantar-entorno)
- [Validar Entorno](#probar-servicios)
  - [Servicios SOAP y REST](#servicios-soap-rest)
  - [Swagger](#swagger)
  - [Sonar Qube](#sonar-qube)

## API SOAP 

### Resumen

**[API POKEMON]  ES una API SOAP construida con spring-boot que consume un servicio REST https://pokeapi.co/** y resguarda sus peticiones en una base de datos **POSTGRES** esta.


### Peticiones
API SOAP con 3 metodos de consulta distintos para poder realizar peticiones a la API Rest de Pokeapi, cada una de las peticiones se encuentras en el archivo **api-pokemon-soap-soapui-project.xml** que se encuentra en el directorio raíz.

### getPokemonRequestByName

  **caso de prueba**

Párametro de entrada **name** que es el nombre del pokemon a buscar.

  **endpoint**
   ` http://localhost:8080/pokemon `

**Request del metodo**

    
     <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gen="http://www.api-pokemon-soap.com/pokemon/gen">
     <soapenv:Header/>
        <soapenv:Body>
         <gen:getPokemonRequestAbilities>
            <gen:name>ditto</gen:name>
         </gen:getPokemonRequestAbilities>
      </soapenv:Body>
    </soapenv:Envelope>

  **Response del metodo**
 Devolera un response con los datos de un pokemon en este caso serian:

  * **id**
  * **name**
  * **base_experience**
  * **locarion_area_encountre**

    **Salida** **200** **OK** 
  ```
    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
      <SOAP-ENV:Header/>
       <SOAP-ENV:Body>
         <ns2:getPokemonResponseByName xmlns:ns2="http://www.api-pokemon-soap.com/pokemon/gen">
           <ns2:pokemon>
                <ns2:name>ditto</ns2:name>
                <ns2:base_experience>101</ns2:base_experience>
                <ns2:id>132</ns2:id>
                <ns2:location_area_encounters>https://pokeapi.co/api/v2/pokemon/132/encounters</ns2:location_area_encounters>
            </ns2:pokemon>
         </ns2:getPokemonResponseByName>
      </SOAP-ENV:Body>
    </SOAP-ENV:Envelope>
  ```
### getHeldItemsRequest

  **caso de prueba**

Párametro de entrada **name**  es el nombre del pokemon a buscar y devolvera un listado de items.

  **endpoint**
 `http://localhost:8080/pokemon/held_items`

**Request del metodo**

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gen="http://www.api-pokemon-soap.com/pokemon/gen">
        <soapenv:Header/>
          <soapenv:Body>
            <gen:getHeldItemsRequest>
              <gen:name>ditto</gen:name>
            </gen:getHeldItemsRequest>
          </soapenv:Body>
    </soapenv:Envelope>

**Response del metodo**
 Devolera un listado de items con los siguientes valores:

  * **name**
  * **url**
  * **version**
  * **rarity**
  
  **Respuesta** **200** **OK** 

```
  <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:getHeldItemsResponse xmlns:ns2="http://www.api-pokemon-soap.com/pokemon/gen">
         <ns2:held_items>
            <ns2:item>
               <ns2:name>metal-powder</ns2:name>
               <ns2:url>https://pokeapi.co/api/v2/item/234/</ns2:url>
            </ns2:item>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>ruby</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/7/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>sapphire</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/8/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>emerald</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/9/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>firered</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/10/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>leafgreen</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/11/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>diamond</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/12/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>pearl</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/13/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>platinum</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/14/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>heartgold</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/15/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>soulsilver</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/16/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>black</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/17/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>5</ns2:rarity>
               <ns2:version>
                  <ns2:name>white</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/18/</ns2:url>
               </ns2:version>
            </ns2:version_details>

             .
             .
             .

            <ns2:version_details>
               <ns2:rarity>50</ns2:rarity>
               <ns2:version>
                  <ns2:name>ultra-sun</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/29/</ns2:url>
               </ns2:version>
            </ns2:version_details>
            <ns2:version_details>
               <ns2:rarity>50</ns2:rarity>
               <ns2:version>
                  <ns2:name>ultra-moon</ns2:name>
                  <ns2:url>https://pokeapi.co/api/v2/version/30/</ns2:url>
               </ns2:version>
            </ns2:version_details>
         </ns2:held_items>
      </ns2:getHeldItemsResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope> 
```
### getPokemonRequestAbilities

  **caso de prueba**

Párametro de entrada **name**  es el nombre del pokemon a buscar y devolvera un listado de abilidades.

  **endpoint**
 `http://localhost:8080/pokemon/abilities`

**Request del metodo**
 
 ```
   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gen="http://www.api-pokemon-soap.com/pokemon/gen">
      <soapenv:Header/>
      <soapenv:Body>
         <gen:getPokemonRequestAbilities>
            <gen:name>ditto</gen:name>
         </gen:getPokemonRequestAbilities>
      </soapenv:Body>
   </soapenv:Envelope>
```

**Response del metodo**
 Devolera un listado de habilidades con los siguientes valores:

  * **ability**
  * **name**
  * **is_hidden**
  * **slot**
  
  **Respuesta** **200** **OK** 

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:getPokemonResponseAbilities xmlns:ns2="http://www.api-pokemon-soap.com/pokemon/gen">
         <ns2:abilities>
            <ns2:ability>
               <ns2:name>limber</ns2:name>
               <ns2:url>https://pokeapi.co/api/v2/ability/7/</ns2:url>
            </ns2:ability>
            <ns2:is_hidden>false</ns2:is_hidden>
            <ns2:slot>1</ns2:slot>
         </ns2:abilities>
         <ns2:abilities>
            <ns2:ability>
               <ns2:name>imposter</ns2:name>
               <ns2:url>https://pokeapi.co/api/v2/ability/150/</ns2:url>
            </ns2:ability>
            <ns2:is_hidden>true</ns2:is_hidden>
            <ns2:slot>3</ns2:slot>
         </ns2:abilities>
      </ns2:getPokemonResponseAbilities>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

## API REST

## Resumen

**[API Log]  es una api rest construida en  spring-boot que devuelve la información obtenida de las consultas realizadas por el API SOAP mencionada en el punto anterior, esta data esta en una tabla  **POSTGRES**.


## Peticiones
Esta api cuenta con 2 peticiones en un enpoint las cuales, perimitiran devolver la lista de peticiones o un peticion en especifico por Id.

### getAll

  **caso de prueba**

Para el listado de polizas no se requieren parametros de entrada.
* **Request.**
* Method: `GET` 
   * Path: `http://localhost:8090/api/logs`
   * URI parameters: N/A
   * Query parameters: N/A
* **Response.**
   * HTTP Status: `200`
   * Payload:
   ```json
      [
         {
            "id": 1,
            "ip_origin": "172.19.0.1",
            "date_request": "2024-10-23T00:00:00.000+00:00",
            "method": "\"http://www.api-pokemon-soap.com/pokemon/gen/getPokemon\"",
            "elapsedTime": "764ms",
            "request": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><gen:getPokemonRequestByName xmlns:gen=\"http://www.api-pokemon-soap.com/pokemon/gen\">\n         <gen:name>ditto</gen:name>\n      </gen:getPokemonRequestByName>",
            "response": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getPokemonResponseByName xmlns:ns2=\"http://www.api-pokemon-soap.com/pokemon/gen\"><ns2:pokemon><ns2:name>ditto</ns2:name><ns2:base_experience>101</ns2:base_experience><ns2:id>132</ns2:id><ns2:location_area_encounters>https://pokeapi.co/api/v2/pokemon/132/encounters</ns2:location_area_encounters></ns2:pokemon></ns2:getPokemonResponseByName>"
         },
         {
            "id": 2,
            "ip_origin": "172.19.0.1",
            "date_request": "2024-10-23T00:00:00.000+00:00",
            "method": "\"http://www.api-pokemon-soap.com/pokemon/gen/getAbilities\"",
            "elapsedTime": "179ms",
            "request": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><gen:getPokemonRequestAbilities xmlns:gen=\"http://www.api-pokemon-soap.com/pokemon/gen\">\n         <gen:name>di</gen:name>\n      </gen:getPokemonRequestAbilities>",
            "response": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang=\"en\">Pokemon no encontrado..</faultstring></SOAP-ENV:Fault>"
         },
         {
            "id": 3,
            "ip_origin": "172.19.0.1",
            "date_request": "2024-10-23T00:00:00.000+00:00",
            "method": "\"http://www.api-pokemon-soap.com/pokemon/gen/getAbilities\"",
            "elapsedTime": "183ms",
            "request": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><gen:getPokemonRequestAbilities xmlns:gen=\"http://www.api-pokemon-soap.com/pokemon/gen\">\n         <gen:name>ditto</gen:name>\n      </gen:getPokemonRequestAbilities>",
            "response": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getPokemonResponseAbilities xmlns:ns2=\"http://www.api-pokemon-soap.com/pokemon/gen\"><ns2:abilities><ns2:ability><ns2:name>limber</ns2:name><ns2:url>https://pokeapi.co/api/v2/ability/7/</ns2:url></ns2:ability><ns2:is_hidden>false</ns2:is_hidden><ns2:slot>1</ns2:slot></ns2:abilities><ns2:abilities><ns2:ability><ns2:name>imposter</ns2:name><ns2:url>https://pokeapi.co/api/v2/ability/150/</ns2:url></ns2:ability><ns2:is_hidden>true</ns2:is_hidden><ns2:slot>3</ns2:slot></ns2:abilities></ns2:getPokemonResponseAbilities>"
         }
      ]
   ```
### getAfinById

**caso de prueba**

Devolvera un registro de la tabla de **request-log** en **POSTGRES**.

* **Request.**
* Method: `GET` 
   * Path: `http://localhost:8090/api/logs/{id}`
   * URI parameters: id
   * Query parameters: N/A
* **Response.**
   * HTTP Status: `200`
   * Payload:
   ```json
      {
        "id": 1,
        "ip_origin": "172.19.0.1",
        "date_request": "2024-10-23T00:00:00.000+00:00",
        "method": "\"http://www.api-pokemon-soap.com/pokemon/gen/getPokemon\"",
        "elapsedTime": "764ms",
        "request": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><gen:getPokemonRequestByName xmlns:gen=\"http://www.api-pokemon-soap.com/pokemon/gen\">\n         <gen:name>ditto</gen:name>\n      </gen:getPokemonRequestByName>",
        "response": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getPokemonResponseByName xmlns:ns2=\"http://www.api-pokemon-soap.com/pokemon/gen\"><ns2:pokemon><ns2:name>ditto</ns2:name><ns2:base_experience>101</ns2:base_experience><ns2:id>132</ns2:id><ns2:location_area_encounters>https://pokeapi.co/api/v2/pokemon/132/encounters</ns2:location_area_encounters></ns2:pokemon></ns2:getPokemonResponseByName>"
    }


## Docker Compose

### Resumen

**[Docker Compose]**  Se encuentra un archivo **docker-compose** en el cual se encuntran las instrucciones para levantar todo el entorno necesario para realizar las pruebas del proyecto, las imagenes necesarias los 2 servicios mencionados anteriormente, una Base de datos **postgres** y un servicio sonarQube. 

### Levantar Entorno
Situado en el direcctorio **/challenge-pokemon** bastaria con ejecutar el comando `docker-compose up` esta comando levantara los contenedores necesarias y descargara las imagenes necesarias para tener el entorno arriba y poder probar.


## Validar Entorno

### Servicios SOAP  y REST

**API SOAP**
 - Para probar la API SOAP  se cuenta con un archivo xml listo para ejecutarse en **SOAP UI** `api-pokemon-soap-soapui-project.xml` 
 - Se levantara en  el puerto 8080.

**API REST**
- Para probar la api rest se tienes lo siguientes enpoints:
 - `http://localhost:8090/api/logs`
 - `http://localhost:8090/api/logs/{id}`

### Swagger

 **API REST**
   - Para la documentación del api-rest que consulta las peticiones realizadas al api rest **Pokeapi.co**
   - `http://localhost:8090/swagger-ui/index.html` 

### Sonar Qube 

Los proyectos spring-boot **/challenge-pokemon/** cuentan con una dependencia en sus `pom.xml` que permitira llevar acabo su validación en SonarQube. Para hacerlo bastaría con acceder al enlance: `http://localhost:9000/` , Se deja el siguiente enlace de apoyo para validarlo https://immja.medium.com/sonarqube-y-maven-f9985394a5dc .

























 