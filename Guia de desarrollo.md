# Guía de ambientación y desarrollo

## Ambientación

1. Java 21 SE. [Descarga aquí](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe).
2. Maven. [Descarga aquí](https://maven.apache.org/download.cgi). Ver [Instalar Maven](#instalar-maven).
3. Instalar un IDE que funcione con Spring Boot (Spring Tools Suite) o instalar la extensión de VSCode. Código para colocar en el buscador: `vmware.vscode-boot-dev-pack`.
4. Instalar Docker. [Link aquí](https://docs.docker.com/desktop/install/windows-install/).

## Instalar Maven
1. Descargar el zip. [Descarga aquí](https://maven.apache.org/download.cgi).
2. Descomprimir en el directorio deseado. Por ejemplo en `C:\Program Files\Maven`.
3. Ir a variables de entorno y agregar al `PATH` del sistema o del usuario la ruta al bin de Maven. Por ejemplo `C:\Program Files\Maven\apache-maven-3.9.6\bin`.
4. Corroborar la instalación usando el comando `mvn -v` en CDM.
5. Salida esperada:
   ```cmd
   Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
   Maven home: C:\Program Files\Maven\apache-maven-3.9.6
   Java version: 21.0.2, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-21
   Default locale: es_ES, platform encoding: UTF-8
   OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
   ```
