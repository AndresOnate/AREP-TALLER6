# TALLER DE TRABAJO INDIVIDUAL EN PATRONES ARQUITECTURALES

En este  taller se creó una aplicación web usando el micro-framework de Spark java, la aplicación web ofrece las siguientes funciones: Seno, Coseno, Palíndromo, Vector. Una vez implementada esta aplicación se procede a construir un container docker, desplegarlo y configurarlo en nuestra máquina local. Luego, creamos un repositorio en DockerHub y subiremos la imagen al repositorio.

## Diseño de la aplicación

La aplicación está diseñada para cumplir con los requisitos especificados en el enunciado del taller y proporcionar una experiencia de usuario fluida y satisfactoria. A continuación, se describen los principales componentes y características de la aplicación:

- La clase `SparkWebServer` define un servidor web Java utilizando el framework Spark, configurando rutas para manejar solicitudes HTTP GET en diferentes endpoints. Inicia el servidor en un puerto específico, y establece la ubicación de los archivos estáticos en el directorio `/public`. Además, implementa endpoints para calcular funciones trigonométricas, verificar palíndromos y calcular la distancia entre dos puntos en un plano cartesiano, extrayendo parámetros de las solicitudes HTTP.
- Se creó un contenedor Docker que encapsula la aplicación `SparkWebServer`, lo que facilita su despliegue y ejecución en diferentes entornos de manera consistente. Este contenedor se configuró para incluir todas las dependencias necesarias y se definió un archivo `Dockerfile` para construirlo adecuadamente. 

## Extensión de la aplicación.

- Se podrían implmentar características adicionales como soporte para otros métodos HTTP.

## Guía de Inicio

Las siguientes instrucciones le permitirán descargar una copia y ejecutar la aplicación en su máquina local.

### Prerrequisitos

- Java versión 8 OpenJDK
- Docker
- Maven
- Git

## Instalación 

### Docker

1. Abra la aplicación de escritorio de Docker.

   ![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/f1051197-c00b-4603-9dfd-d427a26d0eab)

2. Ejecute el siguiente comando para descargar la imagen del repositorio de docker:
   
     ``` docker run -d -p 34000:46000 --name dockercontainer aonatecamilo/arep_taller5```

   Debería ver algo así en consola:

   ![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/828a8d77-43ac-4a8f-8d48-cf1025df14c1)

   Verifique en la aplicación de escritorio de Docker que el contenedor se esté ejecutando
   
   ![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/8ca8bc5c-0b3a-4623-999f-2ccaa638424c)

### Respositorio en GitHub

1. Ubíquese sobre el directorio donde desea realizar la descarga y ejecute el siguiente comando:
   
     ``` git clone https://github.com/AndresOnate/AREP_TALLER5.git ```

2. Navegue al directorio del proyecto:
   
      ``` cd AREP_TALLER5 ```

3. Ejecute el siguiente comando para compilar el código:

      ``` mvn compile ```

5.  Ejecute el siguiente comando para empaquetar el proyecto:
   
      ``` mvn package ``` 

6. Para iniciar el servidor, ejecute el siguiente comando:

    ``` java -cp "target/classes;target/dependency/*" org.eci.arep.app.SparkWebServer ```

7. Verifique en la linea de comanos que se está ejecutando la aplicación:
   
   ![image](https://github.com/AndresOnate/AREP_TALLER5/assets/63562181/f11f64cd-7c95-4dda-825d-4f1ad2b42fe0)


   
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
