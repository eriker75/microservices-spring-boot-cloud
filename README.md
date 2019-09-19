### Aplicación Java utilizando arquitectura de microservicios y Spring Boot Cloud ###

**Modulos que componenen la aplicación**


**Levantando el ambiente**


Aún tenemos que ver como vamos a levantar todos los microservicios de uma manera automatizada, por ahora tenemos que hacer un sprung

```
mvn spring-boot:run
```

[INFO] --- spring-boot-maven-plugin:2.1.7.RELEASE:run (default-cli) @ springboot-servicio-eureka-server ---
[INFO] Attaching agents: []
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.UnsupportedClassVersionError: com/capacitacion/springboot/app/eureka/SpringbootServicioEurekaServerApplication has been compiled by a more recent version of the Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:495)


```
docker-compose -f docker/postgresql/docker-compose.yml up -d
docker-compose -f docker/zipkin/docker-compose up -d
docker-compose -f docker/zipkin/docker-compose.yml up -d
```


RabbitMQ: localhost:15672
Zipkin: localhost:9411
PgAdmin: http://localhost:16543
EurekaServer: http://localhost:8761





### Trazabilidad de los microservicios con Spring Cloud Sleuth y Monitoreo con Zipkin ###

**TraceId**: identificador asociado a la petición que viaja entre los microservicios
**SpanId**: identificador de la unidad de trabajo de cada llamada a un microservicio


Un trace está formado por un conjunto de span.

**Modelo de un log:**

INFO[microservicio, traceID, spanID, <Boolean> exportar a Zipkin]

INFO[servicio-oauth, 8a40fc93ab4385007,f72188520726632d,false]


### TODO ###

[] Crear un docker-compose.yml y ubicarlo dentro del módulo de microservicio-productos
[] Instalar MySQL usando Docker - version 8 / buscar imagen oficial

```docker-compose build```


[] Crear un docker-compose.yml y ubicarlo dentro del módulo de microservicio-usuarios
[] Instalar PostgreSQL usando Docker - version 10.9 
```docker-compose build```


[] Entrar al contenedor y crear base de datos
o ejecutar un comando desde afuera del contenedor:

 ```docker exec -it [CONTAINER-NAME OR CONTAINER-ID] bash ```

[] Modificar servicio-productos-desarrollo.properties con la IP del contenedor. 
``` docker inspect [CONTAINER-NAME OR CONTAINER-ID] ```

[] Modificar servicio-usuarios-desarrollo.properties con la IP del contenedor. 
``` docker inspect [CONTAINER-NAME OR CONTAINER-ID] ```

[] Instalar herramienta visual - Workbench y configurar la IP del contenedor
[] Instalar herramienta visual pgAadmin y configurar la IP del contenedor

### Investigación ### 

[] Almacenar estructura del proyecto en un contenedor?
[] Instalar y configurar portainer, verificando el estado de los contenedores
[] Entender persistencia de los datos
[] ¿Cómo llevar la estructura del proyecto a la nuven AWS? Documentar el proceso
[] ¿Entender y configurar Kubernete y alternativas para la orquestación de los contenedores?


