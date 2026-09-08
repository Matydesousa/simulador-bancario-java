# Actividad 01 — Paradigma Orientado a Objetos

Base de trabajo grupal del sistema de red de cajeros. Sigue las entidades, tipos y responsabilidades del Word ACTIVIDAD_01_POO y el estilo del ejercicio de clases relacionadas: atributos privados, constructores, referencias, metodos simples y toString.

## Ejecutar

Abrir la carpeta como proyecto en IntelliJ, seleccionar un JDK (el proyecto original usa OpenJDK 26) y ejecutar Main.java. No requiere dependencias externas.

Desde una terminal con el JDK en PATH:

```text
javac -encoding UTF-8 -d out src/Banco.java src/Cajero.java src/CuentaBancaria.java src/Main.java
java -cp out Main
```

## Clases

- Banco: administra arreglos de cajeros y cuentas; agrega y busca por identificador.
- Cajero: conoce su banco, consulta cuentas, controla efectivo y coordina extracciones.
- CuentaBancaria: mantiene el saldo y valida depositos y extracciones.
- Main: crea objetos y muestra casos de uso con datos ficticios.

## Correspondencia con el Word

Se conservan los atributos del UML, incluido double saldo, int capacidadBilletes y Cajero[]. Se agregan Banco banco en Cajero y CuentaBancaria[] cuentas en Banco para representar las relaciones descritas en el texto. Las altas reemplazan el arreglo por uno mas grande mediante Arrays.copyOf; los arreglos no crecen por si solos.

Los metodos de alta y busqueda reciben el objeto o identificador necesario. Void del UML se implementa como void. Cajero.extraer(cuenta, monto) coordina la extraccion: comprueba el efectivo y solo descuenta ambos saldos si la cuenta acepta el movimiento. dispensarEfectivo solo modifica el efectivo de la terminal; para una extraccion de una cuenta se utiliza extraer.

Main demuestra el saldo inicial de 120000, la extraccion de 20000, dos referencias a la misma cuenta, getters y setters, recarga y busqueda. Un mismo objeto cuenta puede usarse desde distintos cajeros.

## Alcance de esta base

Es un ejemplo educativo para continuar en grupo. Los constructores reciben datos iniciales validos y cada cuenta se registra en un unico banco desde Main. No se implementa un registro global que impida compartir una cuenta entre bancos. capacidadBilletes es descriptiva: no se modelan denominaciones ni cantidad actual de billetes. No incluye interfaz grafica, persistencia ni autenticacion.

Los movimientos invalidos no cambian el saldo; las extracciones devuelven false. Los depositos y recargas invalidos se ignoran. No hay setters de saldo.

## Trabajo en grupo

Clonar el repositorio, abrirlo en IntelliJ y ejecutar Main antes de modificarlo. Coordinar los cambios de cada clase entre integrantes. Los archivos compilados de out no se suben al repositorio.

## Estilo de clase

Los constructores asignan los parametros con this, como en el ejercicio de Avion. Los getters devuelven un atributo y los setters asignan el nuevo valor, como en el ejercicio de Cliente de los apuntes. Se incluyen setters para datos descriptivos (nombre, direccion, ubicacion, estado, titular y tipo de cuenta). Los identificadores, el banco asignado y los saldos no tienen setters para conservar las relaciones y las reglas del Word.

Main usa objetos creados con new, llamadas a metodos y System.out.println. Las validaciones se escriben en bloques if separados. Se mantienen Arrays.copyOf para agregar posiciones y Double.isFinite en depositos y recargas para evitar ingresar infinito; no se usan frameworks, streams, lambdas, break ni continue. Los constructores completos son suficientes para este TP; el constructor vacio del ejercicio Cliente no es un requisito del Word.
