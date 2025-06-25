|!|<p>**Modelo de ingeniería**</p><p>**[Nombre documento]**</p>|<p>0\.3</p><p>Pág. 3</p>|
| :- | :-: | -: |































||
| :- |
**Especificación de requisitos de software**

**Proyecto:** ColCashFlow

Revisión 2.0

















|<br>||<p></p><p></p>|
| :-: | :-: | -: |

|||Descripción de requisitos del sofware|
| -: | -: | -: |

||<p>` `**MACROBUTTON **ColCashFlow**</p><p>**Especificación de requisitos de software**</p>|<p>Rev. 2.0</p><p>Pág. 8</p>|
| :-: | :-: | -: |

# <a name="_toc201752114"></a>**Ficha del documento**


|**Fecha**|**Revisión**|**Autor**|**Verificado dep. calidad.**|
| :-: | :-: | :-: | :-: |
|25/06/2025|V 2.0|Se plantean los requisitos del proyecto ColCashFlow||














Documento validado por las partes en fecha: 25/06/2025

|Por el cliente|Por la empresa suministradora|
| :-: | :-: |
||<p></p><p></p><p></p><p></p><p></p><p></p><p></p>|
|Leonardo Montes Marín|Universidad de Caldas|








# <a name="_toc201752115"></a>**Contenido**
[FICHA DEL DOCUMENTO	2****]()**

[**CONTENIDO	3****]()

[**1**	**INTRODUCCIÓN	5****]()

[**1.1**	**Propósito	5****]()

[**1.2**	**Alcance	6****]()

[**1.3**	**Personal involucrado	6****]()

[**1.4**	**Definiciones, acrónimos y abreviaturas	7****]()

[**1.5**	**Referencias	7****]()

[**1.6**	**Resumen	7****]()

[**2**	**DESCRIPCIÓN GENERAL	8****]()

[**2.1**	**Perspectiva del producto	8****]()

[**2.2**	**Funcionalidad del producto	8****]()

[2.2.1	Funciones principales	8]()

[2.2.1.1	Usuarios	8]()

[2.2.1.2	Administrado	9]()

[2.2.1.3	Sistema	9]()

[2.2.2	Funciones secundarias	9]()

[**2.3**	**Características de los usuarios	9****]()

[**2.4**	**Restricciones	9****]()

[**3**	**REQUISITOS ESPECÍFICOS	10****]()

[**3.1**	**Requisitos funcionales	12****]()

[3.1.1	Interfaz gráfica amigable (R01)	12]()

[3.1.2	Acceso exclusivo para administrador (R02)	13]()

[3.1.3	Autenticación de usuario (R05)	13]()

[3.1.4	Selección de moneda (R06)	13]()

[3.1.5	Manejo de errores (R07)	13]()

[3.1.6	Validación de montos (R08)	13]()

[3.1.7	Conversión de moneda (R09)	13]()

[3.1.8	Confirmación de transacción (R10)	13]()

[3.1.9	Generación de comprobantes (R13)	14]()

[3.1.10	Simulación sin dinero real (R14)	14]()

[**3.2**	**Requisitos no funcionales	14****]()

[3.2.1	Requisitos de rendimiento	14]()

[3.2.1.1	Tiempo de respuesta rápido	14]()

[3.2.1.2	Procesamiento eficiente de conversiones	14]()

[3.2.1.3	Capacidad de múltiples operaciones consecutivas	14]()

[3.2.1.4	Carga inicial ligera	14]()

[3.2.1.5	Manejo eficiente del historial de transacciones	14]()

[3.2.2	Seguridad	15]()

[3.2.2.1	Autenticación de usuario	15]()

[3.2.2.2	Autenticación de usuario	15]()

[3.2.3	Fiabilidad	15]()

[3.2.4	Disponibilidad	15]()

[3.2.5	Mantenibilidad	15]()

[3.2.6	Portabilidad	15]()


1. # <a name="_toc33238232"></a>**<a name="_toc201752116"></a>Introducción**
En un mundo donde el manejo del dinero y las transacciones digitales son cada vez más comunes, es importante entender cómo funcionan los sistemas que usamos a diario, como los cajeros automáticos. Por eso, en este proyecto desarrollamos una aplicación tipo cajero (ATM) que permite a los usuarios realizar operaciones básicas como consultar su saldo, hacer depósitos, retirar dinero y ver el historial de sus movimientos. Además, el sistema permite ver el saldo no solo en pesos colombianos (COP), sino también en dólares (USD) y euros (EUR), utilizando tasas de cambio para mostrar el equivalente en cada moneda.

Uno de los aspectos clave del proyecto es la inclusión de dos tipos de usuarios: el usuario común y el administrador. El usuario común puede hacer todas las operaciones mencionadas, mientras que el administrador tiene un rol especial que le permite ver todas las transacciones realizadas en el cajero, lo cual es útil para llevar un control general del sistema.

Este proyecto se desarrolló como parte de la asignatura de Programación Orientada a Objetos. Durante su construcción aplicamos conceptos como clases, objetos, métodos, herencia, encapsulamiento y estructuras de control. También trabajamos en el diseño de una interfaz sencilla y amigable para que cualquier persona pueda usar el sistema sin dificultad.

Nuestro objetivo principal fue simular el funcionamiento de un cajero automático real, integrando varias funciones básicas de forma práctica y accesible, reforzando así nuestros conocimientos en programación y lógica computacional.
1. ## <a name="_toc201752117"></a>**Propósito**

El propósito de este proyecto es desarrollar una aplicación que simule el funcionamiento de un cajero automático (ATM), permitiendo a los usuarios realizar operaciones básicas como consultar su saldo, depositar dinero, hacer retiros y ver el historial de todas sus transacciones. Una característica destacada del sistema es que permite visualizar el saldo tanto en pesos colombianos (COP) como en otras monedas extranjeras como dólares estadounidenses (USD) y euros (EUR), realizando la conversión automática con base en tasas de cambio predefinidas.

Esta herramienta está pensada principalmente para personas que tienen cuentas bancarias en Colombia, pero que viajan o viven en el exterior, y necesitan saber de forma rápida y sencilla cuánto dinero tienen disponible en diferentes monedas. El sistema busca ofrecer una experiencia clara, útil y confiable al momento de gestionar el dinero desde una plataforma simulada.

Además, el cajero cuenta con dos tipos de usuarios: el **usuario común**, que puede realizar operaciones personales, y el **administrador**, quien tiene acceso especial para revisar el historial completo de transacciones realizadas por todos los usuarios del sistema. Esta funcionalidad refuerza la importancia del control y seguimiento en sistemas financieros.

Más allá del desarrollo técnico, este proyecto también resalta el valor de una documentación clara, bien organizada y completa. Durante el proceso se trabajará en cada una de las etapas del desarrollo de software: análisis del problema, diseño, codificación, pruebas y validación. Esto ayudará a consolidar tanto los conocimientos en programación orientada a objetos como la habilidad de comunicar soluciones de forma profesional y entendible.
1. ## <a name="_toc201752118"></a>**Alcance**

Este proyecto tiene como objetivo el desarrollo de una aplicación que simula el funcionamiento básico de un cajero automático. La aplicación permitirá realizar operaciones como: consultar el saldo actual, visualizar el saldo en diferentes monedas (COP, USD y EUR), hacer depósitos, retirar dinero y revisar el historial de transacciones realizadas por el usuario.

El sistema contará con dos tipos de roles:

- Usuario común, quien podrá acceder con sus datos personales para realizar operaciones básicas y consultar su historial.
- Administrador, quien tendrá la posibilidad de ver todas las transacciones hechas en el sistema, sin intervenir en las cuentas de los usuarios.

La aplicación está orientada a personas con cuentas bancarias en Colombia que, por motivos de viaje o residencia en el extranjero, necesitan conocer el equivalente de su dinero en otras monedas de forma rápida y sencilla. Las tasas de cambio serán establecidas manualmente dentro del sistema y no se conectarán a fuentes externas de datos. Por lo tanto, este proyecto no contempla integración con entidades financieras reales ni actualizaciones automáticas desde internet, ya que su enfoque es completamente académico y de simulación.

El sistema se desarrollará para funcionar en un entorno local, con énfasis en la lógica, la estructura del código y la organización de clases propias de la programación orientada a objetos.

1. ## <a name="_toc30323665"></a><a name="_toc33238235"></a><a name="_toc201752119"></a>**Personal involucrado**

|Nombre|JHAN POOL POSADA CASAS|
| :- | :- |
|Rol|Desarrollador|
|Categoría profesional|Estudiante de Ingeniería Informática|
|Responsabilidades|BackEnd|
|Información de contacto|[jhan.posada53831@ucaldas.edu.co]() - 3196151237|

|Nombre|CAMILA TOBON LOPEZ|
| :- | :- |
|Rol|Desarrolladora|
|Categoría profesional|Estudiante de Ingeniería Informática|
|Responsabilidades|FrontEnd|
|Información de contacto|[camila.tobon59676@ucaldas.edu.co]() – 3215724007|

|Nombre|CAMILO ANDRES LONDOÑO QUINTERO|
| :- | :- |
|Rol|Desarrollador|
|Categoría profesional|Estudiante de Ingeniería Informática|
|Responsabilidades|Backend y base de datos|
|Información de contacto|[camilo.londono59637@ucaldas.edu.co]() - 3104681501|

|Nombre|JULIANA VALENCIA ARIAS|
| :- | :- |
|Rol|Desarrolladora|
|Categoría profesional|Estudiante de Ingeniería Informática|
|Responsabilidades|Documentación y lógica|
|Información de contacto|<Juliana.valencia15566@ucaldas.edu.co> - 3118421074|

1. ## <a name="_toc33238236"></a><a name="_toc201752120"></a>**Definiciones, acrónimos y abreviaturas**
- ATM: Automated Teller Machine / Cajero Automático.
- COP: peso colombiano.
- USD: dólar estadounidense.
- EUR: euro.
- ERS: Especificación de Requisitos de Software.
  1. ## <a name="_toc33238237"></a><a name="_toc201752121"></a>**Referencias**

|**Referencia**|**Titulo**|**Ruta**|**Fecha**|**Autor**|
| :-: | :-: | :-: | :-: | :-: |
|1\.|OCA: Oracle Certified Associate Java SE 8 Programmer I Study Guide: Exam 1Z0-808|https://www.amazon.com/OCA-Certified-Associate-Programmer-1Z0-808/dp/1118957407|11 de diciembre de 2014|Jeanne Boyarsky y Scott Selikoff|
||||||


1. ## <a name="_toc201752122"></a>**Resumen**
Este documento presenta la Especificación de Requisitos de Software (ERS) del sistema "ColCashFlow", una aplicación diseñada como simulador de cajero automático (ATM). Su objetivo es permitir a los usuarios realizar operaciones bancarias básicas como consultar su saldo, hacer depósitos, retirar dinero, ver su saldo en diferentes monedas (COP, USD y EUR) y revisar el historial de sus transacciones.

La aplicación está especialmente pensada para personas con cuentas bancarias en Colombia que se encuentran en el exterior y necesitan conocer el valor de su dinero en otras monedas de forma rápida y sencilla. El sistema incluye un segundo rol de usuario: el **administrador**, quien podrá supervisar todas las transacciones del cajero.

Este documento describe de forma clara las principales características del sistema, incluyendo:

- **Funcionalidades:** Operaciones que los usuarios pueden realizar, como conversión automática de monedas, consultas de saldo, depósitos, retiros y acceso al historial.
- **Perfiles de usuario:** El sistema tendrá dos tipos de usuario: el usuario común, que realiza transacciones personales, y el administrador, que supervisa el funcionamiento general.
- **Restricciones:** El sistema no estará conectado a entidades financieras reales ni a internet para obtener tasas de cambio. Toda la información será manejada localmente y con fines académicos.
- **Requisitos:** Se detallan los requisitos funcionales (lo que el sistema debe hacer) y no funcionales (cómo debe comportarse), como la seguridad en el acceso y la rapidez en las respuestas del sistema.

El desarrollo de este proyecto busca no solo aplicar los conceptos aprendidos en programación orientada a objetos, sino también documentar correctamente el proceso, desde el análisis hasta la implementación y pruebas, reforzando buenas prácticas tanto en el diseño de software como en la comunicación técnica.

1. # <a name="_toc33238239"></a><a name="_toc201752123"></a>**Descripción general**
   1. ## <a name="_toc33238240"></a><a name="_toc201752124"></a>**Perspectiva del producto**

El sistema “ColCashFlow” es una aplicación de escritorio desarrollada con fines académicos, que simula el funcionamiento de un cajero automático (ATM) con funciones básicas de manejo de cuenta y conversión de divisas. Su diseño permite al usuario realizar operaciones como consultar saldo, hacer depósitos, retirar dinero y revisar su historial de transacciones, con la particularidad de poder visualizar el saldo en diferentes monedas: pesos colombianos (COP), dólares estadounidenses (USD) y euros (EUR), aplicando tasas de cambio definidas previamente.

Este producto no está pensado para integrarse con sistemas bancarios reales ni para operar en línea con servidores o bases de datos externas. Toda la información se gestionará localmente dentro de la misma aplicación, simulando el entorno de un cajero sin necesidad de conexión a internet.

.
1. ## <a name="_toc532878319"></a><a name="_toc33238241"></a><a name="_toc201752125"></a>**Funcionalidad del producto**
   1. ### <a name="_toc201752126"></a>**Funciones principales**
      1. ### <a name="_toc201752127"></a>**Usuarios**
- Creación de cuenta.
- Inicio de sesión.
- Consulta de saldo en diferentes monedas.
- Depositar dinero en la cuenta.
- Retiro de dinero de la cuenta.
- Visualización del histórico de las transacciones de la cuenta.

1. ### <a name="_toc201752128"></a>**Administrado**
- Inicio de sesión.
- Ver histórico de transacciones realizadas en las diferentes cuentas.
  1. ### <a name="_toc201752129"></a>**Sistema**
- Conversión de moneda.
- Control básico de seguridad.
- Validación de valores.

  1. ### <a name="_toc201752130"></a>**Funciones secundarias**
- Creación de usuarios
- Validación de credenciales.
- Indicar tasas de cambio.
- Inicio y cierre de sesión.
- Mensajes de información.
- Menús interactivos.


1. ## <a name="_toc532878320"></a><a name="_toc33238242"></a><a name="_toc201752131"></a>**Características de los usuarios**

|Tipo de usuario|Administrador|
| :- | :- |
|Formación|tecnólogo o profesional en Áreas administrativas con conocimientos en sistemas.|
|Habilidades|experiencia o interés en la administración de sistemas, manejo de información, servicio al cliente.|
|Actividades|Supervisión de transacciones, toma de decisiones administrativas, comprensión funcional del sistema, capacitar a los usuarios en el uso del sistema, soporte de la herramienta.|

|Tipo de usuario|Usuario|
| :- | :- |
|Formación|Público general con conocimientos básicos en el uso de dispositivos electrónicos. No se requiere formación técnica especializada, debe saber leer.|
|Habilidades|Conocimientos básicos sobre operaciones bancarias y uso de cajeros automáticos, capacidad para seguir instrucciones y procedimientos básicos.|
|Actividades|Autenticarse en el sistema para garantizar la seguridad de la operación, recibir asistencia básica o instrucciones sobre el uso del sistema cuando sea necesario.|


1. ## <a name="_toc532878321"></a><a name="_toc33238243"></a><a name="_toc201752132"></a>**Restricciones**
- **Actualización manual de tasas de cambio**: Las tasas de conversión entre COP, USD y EUR deberán ser ingresadas manualmente en el código del programa. El sistema no está conectado a internet ni tiene acceso a fuentes externas para obtener datos en tiempo real. Por tanto, toda conversión depende de los valores definidos internamente.
- **Almacenamiento local**: Toda la información del sistema (usuarios, saldos, historial de transacciones, tasas de cambio) se almacenará localmente o en bases de datos simples, sin capacidad para gestión avanzada o escalabilidad inmediata. No se utilizarán bases de datos avanzadas ni servicios en la nube, ya que el enfoque del proyecto es académico y de simulación.
- **Seguridad básica**: La autenticación de los usuarios y la protección de los datos se basarán en mecanismos sencillos como contraseñas. No se incluirán sistemas avanzados como encriptación, autenticación biométrica o validación en múltiples pasos.
- **Uso exclusivo del lenguaje Java**: Toda la aplicación será desarrollada únicamente con el lenguaje de programación Java. Esto implica que la interfaz, la lógica del programa y el manejo de datos deben estar diseñados respetando las características, limitaciones y buenas prácticas de este lenguaje.

1. # <a name="_toc532878324"></a><a name="_toc33238246"></a><a name="_toc201752133"></a>**Requisitos específicos**

|<a name="_hlk201755789"></a>Número de requisito|` `MACROBUTTON R01|
| :- | :- |
|Nombre de requisito|Interfaz gráfica amigable|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe contar con una interfaz gráfica clara, que muestre las opciones de retiro, selección de moneda, y confirmación de operación de forma sencilla y accesible.|
|Prioridad del requisito|` `FORMCHECKBOX  Alta/Esencial|X Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R02|
| :- | :- |
|Nombre de requisito|Acceso exclusivo para administrador|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe permitir un acceso diferenciado para el administrador, con credenciales especiales para seguimiento de transacciones.|
|Prioridad del requisito|X Alta/Esencial|<a name="casilla4"></a> FORMCHECKBOX  Media/Deseado|<a name="casilla5"></a> FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R03|
| :- | :- |
|Nombre de requisito|Manejo de alertas y notificaciones|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El Sistema tendrá mensajes de alerta para notificar a los usuarios sobre condiciones especiales, errores o acciones realizadas.|
|Prioridad del requisito|` `FORMCHECKBOX  Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|X Baja/ Opcional|


|Número de requisito|` `MACROBUTTON R04|
| :- | :- |
|Nombre de requisito|Autenticación de usuario|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe permitir que el usuario final ingrese sus credenciales para validar su identidad antes de acceder a las funciones de la cuenta.|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R05|
| :- | :- |
|Nombre de requisito|Validación de montos|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe validar que el monto ingresado para retiro sea positivo, no exceda el saldo disponible en COP y cumpla con los límites establecidos para cada operación.|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R06|
| :- | :- |
|Nombre de requisito|Conversión de moneda|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe calcular y mostrar el monto equivalente en las monedas usadas, utilizando tasas de cambio ingresadas manualmente.|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R07|
| :- | :- |
|Nombre de requisito|Retiro de dinero|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El usuario podrá realizar retiros simulados del monto equivalente en la moneda seleccionada, descontando el saldo en pesos colombianos|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R08|
| :- | :- |
|Nombre de requisito|ingreso de dinero|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El usuario podrá realizar ingresos simulados del monto equivalente en la moneda seleccionada, aumentando el saldo en pesos colombianos|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R09|
| :- | :- |
|Nombre de requisito|Generación de comprobantes|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe generar un comprobante con detalles de la transacción, incluyendo monto original en COP, moneda destino, tasa de cambio aplicada y fecha.|
|Prioridad del requisito|` `FORMCHECKBOX   Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|X Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R10|
| :- | :- |
|Nombre de requisito|Simulación sin dinero real|
|Tipo|` `FORMCHECKBOX  Requisito|X Restricción|
|Fuente del requisito|La aplicación funcionará en modo simulación, no se procesarán transacciones monetarias reales, pero reflejará actualizaciones de saldo para fines didácticos.|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R11|
| :- | :- |
|Nombre de requisito|Uso exclusivo del lenguaje Java|
|Tipo|` `FORMCHECKBOX  Requisito|X Restricción|
|Fuente del requisito|El desarrollo del sistema se realizará únicamente con el lenguaje de programación Java, respetando las limitaciones propias de este lenguaje.|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R12|
| :- | :- |
|Nombre de requisito|Seguridad|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe proteger la información de los usuarios mediante autenticación básica y manejo confidencial de datos.|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R13|
| :- | :- |
|Nombre de requisito|Portabilidad|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|El sistema debe poder ejecutarse en ambientes compatibles con Java sin requerir configuraciones adicionales complejas.|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

|Número de requisito|` `MACROBUTTON R14|
| :- | :- |
|Nombre de requisito|Facilidad de uso|
|Tipo|X Requisito|` `FORMCHECKBOX  Restricción|
|Fuente del requisito|Se implementarán ayudas visuales como botones, mensajes emergentes y descripciones breves para guiar al usuario durante su interacción|
|Prioridad del requisito|X Alta/Esencial|` `FORMCHECKBOX  Media/Deseado|` `FORMCHECKBOX  Baja/ Opcional|

1. ## <a name="_toc33238252"></a><a name="_toc201752134"></a>**Requisitos funcionales**
En esta sección se describen las **funciones principales** que debe cumplir el sistema ***ColCashFlow***. Estas funciones son las tareas básicas que permiten simular el uso de un cajero automático, incluyendo el inicio de sesión de los usuarios, la conversión de moneda, el retiro y consignación de dinero simulado. Cada uno de estos requisitos funcionales representa una acción que el sistema debe ejecutar para ofrecer una experiencia clara, práctica y eficaz al usuario.
1. ### <a name="_toc33238257"></a><a name="_toc200562879"></a><a name="_toc201752135"></a>**Interfaz gráfica amigable (R01)**
- Función: Permitir que el usuario interactúe con el sistema de forma intuitiva y clara.
- Implementación: Se puede usar Java Swing o JavaFX para crear una interfaz con botones bien identificados, menús desplegables para seleccionar monedas, campos para ingresar montos y mensajes de confirmación claros. Se deben utilizar etiquetas descriptivas, iconos y colores que guíen al usuario.
  1. ### <a name="_toc200562880"></a><a name="_toc200562881"></a><a name="_toc201752136"></a>**Acceso exclusivo para administrador (R02)**
- Función: Realizar seguimiento a las transacciones realizadas en el simulador.
- Implementación: Después de la autenticación, visualizar la información de las transacciones que se han realizado en el simulador del cajero automático.
  1. ### **Manejo de alertas y notificaciones (R03)**
- Función: Informar al usuario o administrador sobre eventos importantes del sistema, errores, advertencias o confirmaciones de operaciones realizadas.
- Implementación: Se utilizarán cuadros de diálogo (como JOptionPane en Java Swing) o ventanas emergentes para mostrar mensajes claros durante el uso del sistema.
  1. ### <a name="_toc201752137"></a>**Autenticación de usuario (R04)**
- Función: Verificar la identidad del usuario antes de permitir el acceso a las funciones del sistema.
- Implementación: Incluir una pantalla de inicio de sesión donde el usuario introduzca nombre de usuario y contraseña. Para propósitos de simulación, puede usarse una lista de usuarios predefinida.
  1. ### **Validación de montos (R05)**
- Función: Garantizar que los valores ingresados por el usuario (para retiros, consignaciones o conversiones) sean válidos y coherentes con las reglas del sistema.
- Implementación: Verificar que el monto ingresado sea numérico y mayor a cero, asegurar que el monto no exceda el saldo disponible en caso de retiros.
  1. ### **Conversión de moneda (R06)**
- Función: Calcular el valor equivalente en una moneda extranjera (USD, EUR, COP) a partir de un monto ingresado en pesos colombianos
- Implementación: La tasa de cambio será previamente configurada dentro del código.
  1. ### **Retiro de dinero (R07)**
- Función: Permitir al usuario retirar un monto específico desde su cuenta simulada
- Implementación: Permitir al usuario elegir la moneda a retirar e ingresar el monto deseado.
  1. ### **ingreso de dinero (R08)**
- Función: Permitir al usuario elegir el monto a consignar en la cuenta
- Implementación: Por medio de botones indicar el monto que se requiere ingresar a la cuenta.
  1. ### <a name="_toc201752143"></a>**Generación de comprobantes (R09)**
- Función: Emitir un resumen de la operación como comprobante para el usuario.
- Implementación: Al finalizar la operación, generar un archivo .txt o mostrar en pantalla un resumen con fecha, monto original, moneda convertida, tasa aplicada y resultado.
  1. ### <a name="_toc201752144"></a>**Simulación sin dinero real (R10)**
- Función: Simular operaciones bancarias sin realizar transacciones reales.
- Implementación: Al confirmar una operación, el sistema debe ajustar el saldo simulado del usuario.
  1. ## <a name="_toc201752145"></a>**Requisitos no funcionales**
     1. ### <a name="_toc33238258"></a><a name="_toc201752146"></a>**Requisitos de rendimiento**
        1. ### <a name="_toc201752147"></a>**Tiempo de respuesta rápido**
- Función: Asegurar que el sistema responda rápidamente a las acciones del usuario para brindar una experiencia fluida.
- Implementación: Optimizar el flujo del programa para que acciones como iniciar sesión, consultar saldo o cambiar de pantalla se ejecuten en un tiempo máximo de 3 segundos.
  1. ### <a name="_toc201752148"></a>**Procesamiento eficiente de conversiones**
- Función: Calcular y mostrar el resultado de la conversión de moneda en un tiempo mínimo.
- Implementación: Implementar funciones de conversión que trabajen con operaciones matemáticas directas y sin demoras.
  1. ### <a name="_toc201752149"></a>**Capacidad de múltiples operaciones consecutivas**
- Función: Permitir que el usuario realice varias operaciones seguidas sin reiniciar el sistema.
- Implementación: Mantener activa la sesión del usuario mientras no cierre la aplicación o finalice la sesión manualmente. Utilizar estructuras que mantengan el estado del sistema sin recargar la interfaz constantemente.
  1. ### <a name="_toc201752150"></a>**Carga inicial ligera**
- Función: Iniciar el sistema rápidamente al abrir la aplicación.
- Implementación: Evitar cargas innecesarias durante el arranque. Cargar solo los datos esenciales (usuarios, tasas de cambio, interfaz principal) para garantizar que la aplicación esté lista para usarse en menos de 5 segundos.
  1. ### <a name="_toc201752151"></a>**Manejo eficiente del historial de transacciones**
- Función: Permitir al usuario o al administrador ver el historial de transacciones de forma rápida.
- Implementación: Al consultar el historial, el sistema debe generar el listado completo en menos de 2 segundos, incluso si hay más de 20 registros.
  1. ### <a name="_toc33238259"></a><a name="_toc201752152"></a>**Seguridad**
     1. ### <a name="_toc201752153"></a>**Autenticación de usuario**
- Función: Verificar la identidad del usuario antes de permitir el acceso al sistema.
- Implementación: Incluir una pantalla de inicio de sesión con campos de nombre de usuario y contraseña. Para la simulación, no se permite el acceso sin autenticación.
  1. ### **Seguridad básica en almacenamiento**
- <a name="_hlk201752541"></a>Función: Proteger la información guardada en el sistema, aunque sea en almacenamiento local.
- Implementación: Los archivos donde se guarde información (como historial de transacciones o usuarios) no deben estar disponibles para edición directa desde la interfaz. Aunque no se utilizará cifrado avanzado, se puede usar una estructura de datos organizada y protegida por el programa para acceder y modificar estos archivos.

  1. ### <a name="_toc33238260"></a><a name="_toc201752155"></a>**Fiabilidad**
     1. ### **Integridad de los datos**
- Función: Asegurar que los datos como el saldo, el historial de transacciones y las tasas de cambio se mantengan correctos y sin alteraciones.
- Implementación: Validar cada operación antes de guardar los cambios. Toda transacción debe registrar correctamente la fecha, el valor y la moneda. En caso de error, los datos no deben modificarse.

1. ### <a name="_toc33238262"></a><a name="_toc201752157"></a>**Mantenibilidad**
   1. ### **Código modular**
- Función: Permitir que el sistema sea fácil de entender, actualizar y mantener con el paso del tiempo.
- Implementación: El código estará dividido en clases y métodos bien organizados, cada uno con una responsabilidad específica.
  1. ### **Comentarios en el código**
- Función: Ayudar a otros programadores o estudiantes a entender la lógica del sistema.
- Implementación: Incluir comentarios claros que expliquen el propósito de los métodos, clases y secciones importantes del código. 
  1. ### <a name="_toc33238263"></a><a name="_toc201752158"></a>**Portabilidad**
     1. ### **Uso sin conexión a internet**
- Función: Permitir que el sistema funcione en cualquier entorno, incluso sin acceso a red.
- Implementación: Todas las funciones del sistema estarán integradas de forma local, incluyendo las tasas de cambio definidas manualmente. No se requerirá conexión para ninguna operación.
  1. ### **Uso exclusivo del lenguaje Java**
- Función: Garantizar que todo el sistema sea mantenido y ejecutado en un solo lenguaje de programación para mayor portabilidad y consistencia.
- Implementación: La totalidad de la aplicación, incluyendo la lógica del negocio, interfaz gráfica y gestión de datos, se desarrollará en Java.

[ref1]: Aspose.Words.409c32a1-1c27-4a10-8cca-5aec8dc0c10f.001.png "LOGO2"

