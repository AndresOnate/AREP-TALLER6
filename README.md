# TALLER DE TRABAJO INDIVIDUAL EN PATRONES ARQUITECTURALES

En este taller construimos una aplicación con la arquitectura  presentada en  la siguiente imagen. Los contenedores de la aplicación fueron desplegados en una máquina virtual alojada en la nube de AWS (Amazon Web Services).

![descargar (1)](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/bd721586-a2ea-4e8c-bbea-a78f21d42a78)


## Diseño de la aplicación

La aplicación está diseñada para cumplir con los requisitos especificados en el enunciado del taller y proporcionar una experiencia de usuario fluida y satisfactoria. A continuación, se describen los principales componentes y características de la aplicación:

- La clase `APPRoundRobin` define un servidor web Java utilizando el framework Spark. En su método principal, configura las rutas para manejar solicitudes HTTP GET, estableciendo también la ubicación de los archivos estáticos en el directorio /public. Al iniciar, la aplicación define dos rutas:  `/log` recupera `logs` mediante un algoritmo de balanceo de carga Round Robin, haciendo uso de la clase `RRInvoker` y `/hello` para verificar el estado del servicio.
- La clase `RRInvoker` implementa la lógica para invocar servicios de registro distribuidos, utilizando un algoritmo de balanceo de carga Round Robin. Dentro de su método `invoke`, primero codifica el mensaje de registro proporcionado en formato UTF-8. Luego, construye la URL del servidor de registro actual utilizando el arreglo de direcciones `LOG_SERVERS` y el índice `currentLogServer`, este arreglo contiene las referencias a los tres contenedores que ejecutan el `LogService` . Posteriormente, realiza una solicitud HTTP GET al `logservice`, capturando la respuesta y devolviéndola como una cadena.
- La clase `LogService` define un servidor web Java utilizando el framework Spark. Se configura un servicio que se encarga de recibir solicitudes HTTP GET y almacenar registros en una base de datos MongoDB. En su implementación,  establece una conexión con la base de datos y crea un objeto `LogDAO` para manipular los datos en la base de datos. Define dos rutas: una ruta `/hello` para verificar el estado del servicio, y otra ruta `/logservice` que maneja las solicitudes de registro. Cuando recibe una solicitud en esta ruta, registra el mensaje y la fecha actual en la base de datos, y devuelve una lista de registros. 
- La clase `LogDAO` encapsula la lógica de acceso a la base de datos MongoDB para la manipulación de registros de registro. Al ser instanciada, recibe una referencia a la base de datos MongoDB y crea un objeto `MongoCollection<Document>` para interactuar con la colección de registros en dicha base de datos. La función `addLog` recibe un mensaje de registro y una fecha, los formatea adecuadamente y los inserta como un nuevo documento en la colección de registros. La función `listLogs` recupera los últimos 10 registros de la base de datos, los convierte a formato JSON y los devuelve como una cadena.
- La clase `MongoUtil` proporciona un método estático `getDB()` para obtener una instancia de la base de datos MongoDB. Utiliza las constantes `CONNECTION_STRING` y `DATABASE_NAME` para establecer la cadena de conexión y el nombre de la base de datos respectivamente. Al llamar al método getDB(), se crea un cliente de MongoDB utilizando la cadena de conexión especificada, y luego se obtiene y devuelve la instancia de la base de datos con el nombre proporcionado

## Arquitectura

El archivo `docker-compose.yml` es un archivo de configuración utilizado por Docker Compose para definir y ejecutar múltiples contenedores como parte de una aplicación. Este archivo describe los servicios, configuraciones y relaciones entre los contenedores que componen la aplicación, facilitando su orquestación y despliegue. 

En este archivo, se definen cinco servicios: `log-service1`, `log-service2`, `log-service3`, `app_round_robin` y `db`. 

Cada uno de estos servicios utiliza una imagen Docker específica para construir su contenedor:
- los servicios `log-service1`, `log-service2` y `log-service3` utilizan la misma imagen `aonatecamilo/arep_taller6_logservice`
- El servicio `app_round_robin` utiliza la imagen `aonatecamilo/arep_taller6_approundrobin`
- El servicio `db` utiliza la imagen `mongo:3.6.1` para crear un contenedor de MongoDB.

Para cada servicio, se especifican configuraciones adicionales, como los puertos que se deben mapear desde el contenedor al host, los volúmenes que se deben montar, las dependencias de otros servicios y la red a la que pertenecen.

- `app_round_robin`: el puerto `46000` del contenedor se mapeará al puerto `38000` del host.
- `log-service1`: El puerto `35000` del contenedor se mapeará al puerto `35001` del host.
- `log-service2`: El puerto `35000` del contenedor se mapeará al puerto `35002` del host.
- `log-service3`: El puerto `35000` del contenedor se mapeará al puerto `35003` del host.
- `db`: El puerto `27017` del contenedor se mapeará al puerto `27017` del host.

Se define la sección networks, que especifica la red a la que pertenecen los servicios. En este caso, todos los servicios están conectados a la misma red llamada `my_network`. Además, se define la sección volumes, que especifica los volúmenes que se utilizan para persistir los datos de MongoDB.

## Guía de Inicio

Las siguientes instrucciones le permitirán descargar una copia y ejecutar la aplicación en su máquina local.

### Prerrequisitos

- Java versión 8 OpenJDK
- Docker
- Maven
- Git

## Instalación 

1. Abra la aplicación de escritorio de Docker.

   ![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/f1051197-c00b-4603-9dfd-d427a26d0eab)

2. Ubíquese sobre el directorio donde desea realizar la descarga y ejecute el siguiente comando:

    ``` git clone https://github.com/AndresOnate/AREP-TALLER6.git ```

3. Navegue al directorio del proyecto

   ``` cd AREP-TALLER6 ```

4. Ejecute el siguiente comando para  iniciar los contenedores definidos en el archivo `docker-compose.yml`:
   
      ``` docker-compose up -d ``` 
   
   Debería ver algo así en consola:

   ![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/8914eb81-f40d-456b-8c8f-25d76e2512fe)

   Verifique en la aplicación de escritorio de Docker que los contenedores se esté ejecutando

   ![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/e784076f-d88d-4124-9536-e785c307be1b)

   
El comando `docker-compose up -d` inicia los servicios definidos en el archivo `docker-compose.yml`.
   
## Probando la Aplicación.  

Ingrese a la siguiente URL para ingresar a el cliente: `http://localhost:38000/index.html`.

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/d6bb1df5-fe2d-4adb-9ea5-69260f5a676f)

Ingrese el valor del mensaje y de clic en el botón `Send`:

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/ed754812-8647-4cea-81de-0a5954a7b5f5)


La aplicación mostrará las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/39eb7e7d-be76-4262-ba28-7ba99a79e99b)


## Generando las imágenes para el despliegue.

Para generar las imágenes de Docker se crearon los archivos `Dockerfile.app` y `Dockerfile.logservice`, ubicados en el directorio `Dockerfiles`. Cada archivo define una imagen basada en la imagen oficial de OpenJDK 17. Estos archivos configuran el directorio de trabajo, establecen variables de entorno para los puertos, copian los archivos de clases y dependencias de la aplicación al contenedor, y especifican el comando para ejecutar la aplicación Java cuando el contenedor esté en funcionamiento. `Dockerfile.app` se encarga de crear la imagen del servidor Fachada, mientras que `Dockerfile.logservice` crea la imagen que será instanciada tres veces según la arquitectura definida.

### Servidor Fachada:

Ubiquese sobre el directorio raiz del repositorio, ejecute el siguiente comando para construir la imagen:

``` docker build -f .\Dockerfiles\Dockerfile.app -t approundrobin . ```

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/5cdfb3ac-80cf-43e2-ae86-766488189964)

Puede agregar una referencia a su imagen con el nombre del repositorio a donde desea subirla, por ejemplo:

``` docker tag approundrobin aonatecamilo/arep_taller6_approundrobin  ``` 

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/f103e3fa-6f58-48c0-a595-4a21e780bcdf)

Empuje la imagen al repositorio en DockerHub

``` docker push aonatecamilo/arep_taller6_approundrobin ``` 

 ![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/3db2ea40-f0dd-4d0e-8bf2-cec10d3cfaea)

### LogService

Ubiquese sobre el directorio raiz del repositorio, ejecute el siguiente comando para construir la imagen:

``` docker build -f .\Dockerfiles\Dockerfile.logservice -t logservice . ```

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/27b3ba92-4d9c-4d9f-a028-27395129fcaf)

Puede agregar una referencia a su imagen con el nombre del repositorio a donde desea subirla, por ejemplo:

``` docker tag logservice aonatecamilo/arep_taller6_logservice ``` 

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/11f0ab32-9451-45e8-a12f-7bf13c15a3d8)

Empuje la imagen al repositorio en DockerHub

``` docker push aonatecamilo/arep_taller6_logservice ``` 

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/cd6a2615-c262-4a06-a0d5-1248021b9a1e)


## Despliegue en AWS

Se creó una instancia de EC2 en AWS con las siguientes características.

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/8edad574-fb65-4bc9-bca5-751189a6f1b2)

En el siguiente video se muestran los despliegues funcionando en la máquina virtual: 

## Construido Con. 

- Maven - Administrador de dependencias

## Autores 

- Andrés Camilo Oñate Quimbayo

## Versión
1.0.0
