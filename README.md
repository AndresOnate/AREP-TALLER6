# TALLER DE TRABAJO INDIVIDUAL EN PATRONES ARQUITECTURALES

En este taller construimos una aplicación con la arquitectura  presentada en  la siguiente imagen. Los contenedores de la aplicación fueron desplegados en una máquina virtual alojada en la nube de AWS (Amazon Web Services).

![descargar (1)](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/bd721586-a2ea-4e8c-bbea-a78f21d42a78)


## Diseño de la aplicación

La aplicación está diseñada para cumplir con los requisitos especificados en el enunciado del taller y proporcionar una experiencia de usuario fluida y satisfactoria. A continuación, se describen los principales componentes y características de la aplicación:

- La clase `APPRoundRobin` define un servidor web Java utilizando el framework Spark. En su método principal, configura las rutas para manejar solicitudes HTTP GET, estableciendo también la ubicación de los archivos estáticos en el directorio /public. Al iniciar, la aplicación define dos rutas:  `/log` recupera `logs` mediante un algoritmo de balanceo de carga Round Robin, haciendo uso de la clase `RRInvoker` y `/hello` para verificar el estado del servicio.
- La clase `RRInvoker` implementa la lógica para invocar servicios de registro distribuidos, utilizando un algoritmo de balanceo de carga Round Robin. Dentro de su método `invoke`, primero codifica el mensaje de registro proporcionado en formato UTF-8. Luego, construye la URL del servidor de registro actual utilizando el arreglo de direcciones `LOG_SERVERS` y el índice `currentLogServer`. Posteriormente, realiza una solicitud HTTP GET al `logservice`, capturando la respuesta y devolviéndola como una cadena.
- La clase `LogService` define un servidor web Java utilizando el framework Spark. Se configura un servicio que se encarga de recibir solicitudes HTTP GET y almacenar registros en una base de datos MongoDB. En su implementación,  establece una conexión con la base de datos y crea un objeto `LogDAO` para manipular los datos en la base de datos. Define dos rutas: una ruta `/hello` para verificar el estado del servicio, y otra ruta `/logservice` que maneja las solicitudes de registro. Cuando recibe una solicitud en esta ruta, registra el mensaje y la fecha actual en la base de datos, y devuelve una lista de registros. 
- La clase `LogDAO` encapsula la lógica de acceso a la base de datos MongoDB para la manipulación de registros de registro. Al ser instanciada, recibe una referencia a la base de datos MongoDB y crea un objeto `MongoCollection<Document>` para interactuar con la colección de registros en dicha base de datos. La función `addLog` recibe un mensaje de registro y una fecha, los formatea adecuadamente y los inserta como un nuevo documento en la colección de registros. La función `listLogs` recupera los últimos 10 registros de la base de datos, los convierte a formato JSON y los devuelve como una cadena.
- La clase `MongoUtil` proporciona un método estático `getDB()` para obtener una instancia de la base de datos MongoDB. Utiliza las constantes `CONNECTION_STRING` y `DATABASE_NAME` para establecer la cadena de conexión y el nombre de la base de datos respectivamente. Al llamar al método getDB(), se crea un cliente de MongoDB utilizando la cadena de conexión especificada, y luego se obtiene y devuelve la instancia de la base de datos con el nombre proporcionado

## Generando las imagenes

### App
![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/5cdfb3ac-80cf-43e2-ae86-766488189964)

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/f103e3fa-6f58-48c0-a595-4a21e780bcdf)

 ![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/3db2ea40-f0dd-4d0e-8bf2-cec10d3cfaea)

### LogService

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/27b3ba92-4d9c-4d9f-a028-27395129fcaf)

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/11f0ab32-9451-45e8-a12f-7bf13c15a3d8)

![image](https://github.com/AndresOnate/AREP-TALLER6/assets/63562181/cd6a2615-c262-4a06-a0d5-1248021b9a1e)



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

   ![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/828a8d77-43ac-4a8f-8d48-cf1025df14c1)

   Verifique en la aplicación de escritorio de Docker que los contenedores se esté ejecutando
   
   ![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/8ca8bc5c-0b3a-4623-999f-2ccaa638424c)




## Despliegue en EC2



## Probando la Aplicación.  

Ingrese a la siguiente URL para ingresar a el cliente: `http://localhost:34000/index.html`.
Si está ejecutando la aplicación desde la máquina virtual de java y no con el contenedor, el puerto es el `4567`.

Ingrese en los campos del formulario los valores, de clic en el botón `Search`:

![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/c5247754-f588-45cc-a17c-d3c1d6ec1523)

- Para las funciones Seno y Coseno ingresé el valor que desea calcular en radianes.
- Para la función palíndromo ingrese la palabra que quiere evaluar, si la palabra es palíndromo retornará true,  de lo contrario retornará false.
- Para la función Vector, ingrese las coordenadas de los puntos en el formato **x,y**

Función Seno

![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/d274867a-72f4-4836-928d-1bbfa156466a)

Función Coseno:

![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/ffd854f5-161e-488f-81cb-ee758fd96d32)

Función Palíndromo:

![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/f262cc30-8a0e-4bc4-8f60-fa69fd3dde0f)

Función Vector:

![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/edce534b-8d37-4587-9141-4caaafbd5aea)


## Construido Con. 

- Maven - Administrador de dependencias

## Versión
1.0.0
