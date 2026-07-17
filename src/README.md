
## Requerimientos

- Git.
- Netbeans 12 o superior.
- Java 7 (Netbeans puede funcionar con una versión mayor, pero se necesita 7 para compilar).
- Maven (incluido en Netbeans).
- Docker
- Docker-compose.

## Ambiente de desarrollo local

### Clonado del proyecto

```bash
$> git clone git@git.sofis.lat:JAVA/AGESIC/AG-SIGES.git
```

Luego de clonar el proyecto, especificar los datos del usuario:

```bash
git config --local user.email "nombre.apellido@sofis.lat"
git config --local user.name "Nombre Apellido"
```

### Importación del proyecto en Netbeans

1. Crear un grupo de proyectos de tipo "Folder of Projects", usando como carpeta base la del proyecto.

### Compilación

0. Configurar el ambiente

```bash
$> cd /home/developer/Projects/AG-SIGES
$> export JAVA_HOME=/path/to/jdk7 
```

Nota: para los pasos siguientes, se requiere Maven 3.8.8 o anterior, pues lo siguientes no son compatibles con Java 7.
Todas las compilaciones pueden hacerse desde Netbeans (clic derecho sobre el proyecto y luego "Clean and build") aunque se recomienda hacerlo por línea de comandos, como se muestra a continuación.

Solo la primera vez, compilar librerías auxiliares de Sofis:

1. Compilar el proyecto PGE Client (install).
    ```bash
    $> cd pge-client-v2.4/
    $> mvn -Dhttps.protocols=TLSv1.2 -DadditionalJOption=-Xdoclint:none -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true install
    $> cd ..
    ```
2. Compilar el proyecto SS-GENERIDAO-TO-JPA2 (install).
    ```bash
    $> cd SS-GENERIDAO-TO-JPA2/
    $> mvn -Dhttps.protocols=TLSv1.2 -DadditionalJOption=-Xdoclint:none -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true install
    $> cd ..
    ```
3. Compilar el proyecto SS-GENERICDAO-BUSINESS-JPA2 (install).
    ```bash
    $> cd SS-GENERICDAO-BUSINESS-JPA2/
    $> mvn -Dhttps.protocols=TLSv1.2 -DadditionalJOption=-Xdoclint:none -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true install
    $> cd ..
    ```

Cada vez que se deba generar el artefacto deployable:

1. Compilar el proyecto SS-SIGES (package, este compila todos los demás).
    ```bash
    $> mvn -Dhttps.protocols=TLSv1.2 -DadditionalJOption=-Xdoclint:none -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true clean package
    ```
Al finalizar, el artefacto deployable queda en /AG-SIGES/SS-SIGES-mysql-ear/target/.

### Deploy

Para desplegar la nueva versión en el servidor JBoss:

1. Borrar de la carpeta "standalone/deployments" del JBoss el archivo EAR viejo (esperar a que finalice el undeploy, observando el log).
2. Copiar el EAR nuevo en la carpeta "standalone/deployments" del JBoss (esperar a que finalice el deploy, observando el log, determinando si finaliza correctamente o hay errores).

## Nuevas versiones

### Cambio de número de versión

Antes de comenzar a trabajar en una nueva versión, incrementar el número, según corresponda (major, minor o patch).

1. Cambiar la versión en los archivos pom.xml

Esto puede hacerse manualmente, cambiando todos los archivos pom.xml a mano, o mediante el siguiente comando (cambiar "x.y.z" por el valor apropiado):

```bash
$> mvn org.codehaus.mojo:versions-maven-plugin:2.8.1:set -DnewVersion=x.y.z
$> mvn org.codehaus.mojo:versions-maven-plugin:2.8.1:commit
```

Nota: se especifica la versión 2.8.1 del plugin porque las posteriores no son compatibles con Java 7.

2. Ajustar el código fuente para hacer referencia a la nueva versión cuando hace lookups: en la clase `SS-SIGES-web/src/main/java/com/sofis/web/utils/EJBUtils.java` buscar la versión anterior y cambiarla por la nueva.

Nota: se recomienda buscar en todo el proyecto que el número de versión no se utilice en otro lado.

### Cambios en base de datos

Se debe llevar un estricto control de los cambios de base de datos, los cuales deben ser documentados mediante scripts SQL.
Cada versión debe tener su propio script, que debe estar en `SS-SIGES-database/mysql/sql`, con el nombre `script_v_x.y.z.sql`.
Se sugiere que si una versión determinada no tiene cambios en la base de datos, igual se genere un script SQL con solo un comentario indicando que no hay cambios (para evitar la duda de si no nay cambios o no se hizo el script).

## Liberación de versión

### Liberación para QA

Para liberar una versión al ambiente de QA, se debe hacer lo siguiente:

1. Generar el artefacto EAR, probarlo en el JBoss y asegurarse de que está listo para pasar a QA.
2. Asegurarse de tener todo subido a la rama development; si no está, hacerlo.
3. Si hay cambios en base de datos, aplicarlos sobre la base de datos del ambiente de QA o solicitar a soporte que lo haga.
4. Pasar el código fuente a la rama release (merge y push); esto lanzará el pipeline que terminará actualizando automáticamente el ambiente de QA. 
    Nota: no debería haber cambios porque esta rama solo se modifica a partir de development; si los hay, habrá que unificarlos y volver a probar todo localmente antes de continuar. 
5. Avisar al coordinador y/o a QA que está lista la versión para probar. 

### Liberación al cliente

Para liberar una versión al cliente, se debe hacer lo siguiente:

1. Generar el artefacto EAR, probarlo en el JBoss y asegurarse de que está listo para ser entregado (debería ser exactamente el mismo que se probó antes de pasar a QA).
2. Asegurarse de tener todo subido a la rama development; si no está, hacerlo (si hubo cambios, habrá que pasarlos por QA antes de continuar).
4. Asegurarse de tener todo subido a la rama release; si no está, es porque no pasó lo último por QA.
5. Pasar el código fuente a la rama master (merge y push).
6. Generar el tag (tag y push).
7. Generar el paquete para entregar al cliente conteniendo:
    - Artefacto EAR deployable (el EAR generado en el punto 1).
    - Script SQL para ejecutar (en lo posible debe ser uno solo, y debe validarse que funciona).
    - Archivo PDF de liberación (usar la plantilla que corresponda).

## Configuraciones de JBoss

Respecto de un JBoss AS 7.1.1 limpio, los siguientes cambios han sido realizados hasta la versión 6.15.5:

* Crear o remplazar los siguientes módulos:
    - com.mysql.main
    - com.sun.jsf-impl.main
    - javax.faces.api.main
    - org.hibernate.envers.main
    - org.hibernate.main
    - org.javassist.main

* Archivo bin/standalone.conf: aumentar la memoria asignada (parámetros -Xms y -Xmx).
* Archivo standalone/configuration/mgmt-users.properties: añadir un usuario "siges", realm "ManagementRealm" y contraseña "sofis2uy": "siges=7025164a0ee11e9dfdbe7029c4b34d0a".
* Archivo standalone/configuration/standalone.xml:
    - Dentro de `<extensions>` añadir: 
        ```
        <extension module="org.jboss.as.messaging"/>
        ```
    - Despues (no dentro) de `<extensions>` añadir:
        ```
        <system-properties>
            <property name="org.apache.coyote.http11.Http11Protocol.COMPRESSION" value="on"/>
            <property name="org.apache.coyote.http11.Http11Protocol.COMPRESSION_MIME_TYPES" value="text/javascript,text/css,text/html,text/xml,text/json"/>
            <property name="org.apache.tomcat.util.http.Parameters.MAX_COUNT" value="2000"/>
            <property name="wekan_user_1" value="sofistest"/>
            <property name="wekan_password_1" value="c29maXMydXk="/>
        </system-properties>
        ```
        Nota: las propiedades "wekan_user_1" y "wekan_password_1" corresponden al usuario y contraseña de la organización 1 (Agesic) en Wekan (ver más abajo la sección 'Integración con Wekan').
    - En el subsistema `<subsystem xmlns="urn:jboss:domain:datasources:1.0">`, dentro de <datasources>, añadir:
        ```
        <datasource jta="true" jndi-name="java:jboss/datasources/MySqlDS" pool-name="MySqlDS" enabled="true">
            <connection-url>jdbc:mysql://SIGES-AGESIC-MYSQL:3306/siges</connection-url>
            <driver>com.mysql</driver>
            <pool>
                <min-pool-size>10</min-pool-size>
                <max-pool-size>100</max-pool-size>
            </pool>
            <security>
                <user-name>root</user-name>
                <password>siges</password>
            </security>
            <validation>
                <check-valid-connection-sql>SELECT 1</check-valid-connection-sql>
                <background-validation>true</background-validation>
                <background-validation-millis>2000</background-validation-millis>
            </validation>
            <statement>
                <share-prepared-statements>true</share-prepared-statements>
            </statement>
        </datasource>
        <datasource jta="false" jndi-name="java:jboss/datasources/MySqlDS2" pool-name="MySqlDS2" enabled="true" use-java-context="true" use-ccm="false">
            <connection-url>jdbc:mysql://SIGES-AGESIC-MYSQL:3306/siges</connection-url>
            <driver>com.mysql</driver>
            <security>
                <user-name>root</user-name>
                <password>siges</password>
            </security>
            <validation>
                <validate-on-match>false</validate-on-match>
                <background-validation>false</background-validation>
            </validation>
            <statement>
                <share-prepared-statements>true</share-prepared-statements>
            </statement>
        </datasource>
        <drivers>
            <driver name="h2" module="com.h2database.h2">
                <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
            </driver>
            <driver name="com.mysql" module="com.mysql">
                <xa-datasource-class>com.mysql.main.mysql-connector-java</xa-datasource-class>
            </driver>
        </drivers>
        ```
        Nota: ajustar los parámetros de conexión a MySQL (host, puerto, usuario y contraseña) según corresponda.
    - Dentro del subsistema `<subsystem xmlns="urn:jboss:domain:ejb3:1.2">`, después (no dentro) de <session-bean>, añadir:
        ```
        <mdb>
            <resource-adapter-ref resource-adapter-name="hornetq-ra"/>
            <bean-instance-pool-ref pool-name="mdb-strict-max-pool"/>
        </mdb>
        ```
    - Dentro del subsistema `<subsystem xmlns="urn:jboss:domain:ejb3:1.2">`, cambiar:
        ```
        <strict-max-pool name="slsb-strict-max-pool" max-pool-size="20" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
        ```
        por
        ```
        <strict-max-pool name="slsb-strict-max-pool" max-pool-size="200" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
        ```
    - Dentro del subsistema `<subsystem xmlns="urn:jboss:domain:mail:1.0">`, añadir:
        ```
        <mail-session jndi-name="java:jboss/mail/SigesMail">
            <smtp-server outbound-socket-binding-ref="mail-siges-smtp"/>
        </mail-session>
        ```
    - Dentro del subsistema `<subsystem xmlns="urn:jboss:domain:osgi:1.2" ...>`, cambiar:
        ```
        <property name="org.osgi.framework.startlevel.beginning">1</property>
        ```
        por
        ```
        <property name="org.osgi.framework.startlevel.beginning"></property>
        ```
    - Dentro del subsistema `<subsystem xmlns="urn:jboss:domain:security:1.1">`, dentro de <security-domains>, añadir:
        ```
        <security-domain name="form-auth" cache-type="default">
            <authentication>
                <login-module code="com.sofis.web.seguridad.SimpleUsernamePasswordLoginModule" flag="required"/>
            </authentication>
        </security-domain>
        ```
    - Dentro del subsistema `<subsystem xmlns="urn:jboss:domain:threads:1.1">`, añadir:
        ```
        <bounded-queue-thread-pool name="http-executor">
            <queue-length count="500"/>
            <max-threads count="500"/>
            <keepalive-time time="10" unit="seconds"/>
        </bounded-queue-thread-pool>
        ```
    - Dentro del subsistema `<subsystem xmlns="urn:jboss:domain:webservices:1.1">`, cambiar:
        ```
        <wsdl-host>${jboss.bind.address:127.0.0.1}</wsdl-host>
        ```
        por
        ```
        <wsdl-host>jbossws.undefined.host</wsdl-host>
        ```
    - Debajo (no dentro) del subsistema `<subsystem xmlns="urn:jboss:domain:weld:1.0"/>`, añadir:
        ```
        <subsystem xmlns="urn:jboss:domain:messaging:1.1">
            <hornetq-server>
                <persistence-enabled>true</persistence-enabled>
                <journal-file-size>102400</journal-file-size>
                <journal-min-files>2</journal-min-files>
                <connectors>
                    <netty-connector name="netty" socket-binding="messaging"/>
                    <netty-connector name="netty-throughput" socket-binding="messaging-throughput">
                        <param key="batch-delay" value="50"/>
                    </netty-connector>
                    <in-vm-connector name="in-vm" server-id="0"/>
                </connectors>
                <acceptors>
                    <netty-acceptor name="netty" socket-binding="messaging"/>
                    <netty-acceptor name="netty-throughput" socket-binding="messaging-throughput">
                        <param key="batch-delay" value="50"/>
                        <param key="direct-deliver" value="false"/>
                    </netty-acceptor>
                    <in-vm-acceptor name="in-vm" server-id="0"/>
                </acceptors>
                <security-settings>
                    <security-setting match="#">
                        <permission type="send" roles="guest"/>
                        <permission type="consume" roles="guest"/>
                        <permission type="createNonDurableQueue" roles="guest"/>
                        <permission type="deleteNonDurableQueue" roles="guest"/>
                    </security-setting>
                </security-settings>
                <address-settings>
                    <address-setting match="#">
                        <dead-letter-address>jms.queue.DLQ</dead-letter-address>
                        <expiry-address>jms.queue.ExpiryQueue</expiry-address>
                        <redelivery-delay>0</redelivery-delay>
                        <max-size-bytes>10485760</max-size-bytes>
                        <address-full-policy>BLOCK</address-full-policy>
                        <message-counter-history-day-limit>10</message-counter-history-day-limit>
                    </address-setting>
                </address-settings>
                <jms-connection-factories>
                    <connection-factory name="InVmConnectionFactory">
                        <connectors>
                            <connector-ref connector-name="in-vm"/>
                        </connectors>
                        <entries>
                            <entry name="java:/ConnectionFactory"/>
                        </entries>
                    </connection-factory>
                    <connection-factory name="RemoteConnectionFactory">
                        <connectors>
                            <connector-ref connector-name="netty"/>
                        </connectors>
                        <entries>
                            <entry name="RemoteConnectionFactory"/>
                            <entry name="java:jboss/exported/jms/RemoteConnectionFactory"/>
                        </entries>
                    </connection-factory>
                    <pooled-connection-factory name="hornetq-ra">
                        <transaction mode="xa"/>
                        <connectors>
                            <connector-ref connector-name="in-vm"/>
                        </connectors>
                        <entries>
                            <entry name="java:/JmsXA"/>
                        </entries>
                    </pooled-connection-factory>
                </jms-connection-factories>
                <jms-destinations>
                    <jms-queue name="cambioEntranteWekanQueue">
                        <entry name="queue/cambioEntranteWekanQueue"/>
                    </jms-queue>
                    <jms-queue name="cambioSalienteWekanQueue">
                        <entry name="queue/cambioSalienteWekanQueue"/>
                    </jms-queue>
                </jms-destinations>
            </hornetq-server>
        </subsystem>
        ```
    - Dentro de `<interface name="management">`, cambiar:
        ```
        <inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
        ```
        por
        ```
        <inet-address value="${jboss.bind.address.management:0.0.0.0}"/>
        ```
    - Dentro de `<interface name="public">`, cambiar:
        ```
        <inet-address value="${jboss.bind.address:127.0.0.1}"/
        ```
        por
        ```
        <inet-address value="${jboss.bind.address:0.0.0.0}"/>
        ```
    - Dentro de `<socket-binding-group...>`, añadir:
        ```
        <socket-binding name="messaging" port="5445"/>
        <socket-binding name="messaging-throughput" port="5455"/>
        ```
        y
        ```
        <outbound-socket-binding name="mail-siges-smtp">
            <remote-destination host="xxx" port="xxx"/>
        </outbound-socket-binding>
        ```
        Nota: ajustar los parámetros de conexión al servidor SMTP.

## Integración con Wekan

La integración de Siges con Wekan es en ambos sentidos, es decir, Siges se comunica con Wekan, y Wekan se comunica con Siges.

### Siges -> Wekan

1. Crear un usuario en Wekan

Acceder a Wekan (por ejemplo, http://localhost:8200/sign-in) y usar la opción "Register".

Nota: se recomienda usar "siges" como nombre de usuario y "123456" como contraseña.

2. Configurar parámetros en el JBoss

Editar el archivo jboss_SIGES/standalone/configuration/standalone.xml y dentro de la sección <system-properties> añadir lo siguiente:

```
<property name="wekan_user_1" value="siges"/>
<property name="wekan_password_1" value="MTIzNDU2"/>
```

Notas:
- El 1 que figura tanto en el user como en el password se refiere al id del organismo en Siges. El particular, el 1 suele ser Agesic. Si hubiese que configurar otro organismo se debe cambiar el 1 por el id correspondiente.
- La contraseña debe ingresarse codificada en base 64 (por ejemplo, "123456" en base 64 es "MTIzNDU2"). Puede usarse el siguiente servicio para codificar valores en base 64: https://www.base64encode.org/.

Dentro de la sección <jms-destinations>, que está dentro de la sección <hornetq-server>, en el subsistema "urn:jboss:domain:messaging:1.1", añadir los siguientes elementos:

```
<jms-queue name="cambioEntranteWekanQueue">
    <entry name="queue/cambioEntranteWekanQueue"/>
</jms-queue>
<jms-queue name="cambioSalienteWekanQueue">
    <entry name="queue/cambioSalienteWekanQueue"/>
</jms-queue>
```

Nota: esto ya fue descrito en la sección "Configuración de JBoss".

3. Configurar SIGES

**Nota**: todo lo que sigue a continuación aplica al organismo activo. Debe repetirse para cada organismo que se requiera.

3.1. Habilitar el módulo Wekan

Acceder a Siges con un usuario administrador e ir a opción `Administración` -> `Configuración` -> `General Organismo`. Buscar la propiedad `SHOW_MODULO_WEKAN` y asignarle el valor `true`.

3.2. Configurar parámetros de conexión

Luego, allí mismo, configurar las siguientes propiedades:

* `WEKAN_ADMIN_USUARIO`: ingresar el código de usuario (puede ser el mismo utilizado en el punto 2).
* `WEKAN_ADMIN_CONTRASENIA`: ingresar la contraseña del usuario, codificada en base 64 (puede ser la misma utilizada en el punto 2).

4. Configurar el módulo Siges para cada proyecto

Acceder a cada proyecto que se desee integar con Wekan, y desplegar el módulo Wekan (si no se ve, es porque no se habilitó con la propiedad "SHOW_MODULO_WEKAN" como se indicó en el punto 3.1).
En las propiedades del módulo, especificar los siguientes datos:

* Enlace al tablero: ingresar la URL al tablero al cual se desee vincular el proyecto. Esta URL es la que se observa en la barra de direcciones del navegador web cuando se accede al tablero en Wekan (por ejemplo, "http://localhost:4200/b/swrZoJ92LkMtgfYbi/7457").
* Usuario Wekan: código de usuario con el cual conectar el proyecto a Wekan; no requiere que sea admin (puede ser el mismo utilizado en los puntos 2 y 3.
* Contraseña: contraseña dle usuario anterior, en texto plano (no en base 64).

**Nota**: en el caso del ambiente virtual de desarrollo usando docker-compose, debe utilizarse "WEKAN" como hostname (porque docker-compose que define un hostname por cada contenedor usado su nombre), y "8080" como puerto (porque entre contenedores del mismo compose se ven mediante el puerto interno); así, por ejemplo, una URL sería "http://WEKAN:8080/b/swrZoJ92LkMtgfYbi/7457".

### Wekan -> Siges

Este paso es para que Wekan pueda notificar a Siges de cambios en las tarjetas.

4.1. Desplegar el menú principal, acceder a `Settings` y elegir la opción `Ongoing Webhooks`.
4.2. Completar los datos y guardar (ToDo).

**Nota**: en el caso del ambiente virtual de desarrollo usando docker-compose, debe utilizarse "SIGES-AGESIC-JBOSS" como hostname (porque docker-compose que define un hostname por cada contenedor usado su nombre), y "8080" como puerto (porque entre contenedores del mismo compose se ven mediante el puerto interno); así, por ejemplo, una URL sería "http://SIGES-AGESIC-JBOSS:8080/SS-SIGES-web/rest/wekan/bidirectional-webhook".

## ToDo's

* Quitar la referencia a la versión en el código fuente.
   
Esto afecta a la clase `SS-SIGES-web/src/main/java/com/sofis/web/utils/EJBUtils.java`, la cual debe ser ajustada cada vez que se cambia la versión del proyecto.
Esto puede lograrse haciendo que los módulos internos al EAR (EJB y WEB) no incluyan la versión en el nombre del archivo, o que declaren un nombre diferente al nombre del archivo.
Dificultad: baja.





