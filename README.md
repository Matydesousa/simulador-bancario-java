# Simulador bancario en Java

[![Java CI](https://github.com/Matydesousa/simulador-bancario-java/actions/workflows/java.yml/badge.svg)](https://github.com/Matydesousa/simulador-bancario-java/actions/workflows/java.yml)

Proyecto educativo de programación orientada a objetos que representa las operaciones básicas de una red bancaria. El ejemplo modela bancos, cajeros automáticos y cuentas, y muestra cómo colaboran los objetos durante extracciones y transferencias.

## Funcionalidades

- Registrar y buscar cajeros dentro de un banco.
- Registrar y buscar cuentas bancarias.
- Depositar, extraer y consultar saldo.
- Transferir dinero entre cuentas.
- Coordinar una extracción entre la cuenta y el efectivo disponible en el cajero.
- Recargar el efectivo de un cajero.

## Diseño

- `Banco`: administra arreglos de cajeros y cuentas.
- `Cajero`: gestiona su efectivo y coordina extracciones sobre una cuenta.
- `CuentaBancaria`: contiene el saldo y las operaciones de depósito, extracción y transferencia.
- `Main`: ejecuta un recorrido demostrativo del sistema.

El proyecto utiliza únicamente clases del JDK y no requiere dependencias externas.

## Requisitos

- JDK 8 o superior.

## Compilación y ejecución

Desde la raíz del repositorio:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

## Pruebas

La prueba automatizada cubre registros y búsquedas, depósitos, extracciones, transferencias, fondos insuficientes y sincronización de saldos con el cajero.

```bash
javac -encoding UTF-8 -d out src/*.java tests/*.java
java -ea -cp out SimuladorBancarioTest
```

También se ejecuta automáticamente en GitHub Actions con cada cambio enviado al repositorio.

## Alcance

Es una simulación académica en memoria: no posee interfaz gráfica, persistencia, autenticación ni las validaciones necesarias para operar como un sistema bancario real.

## Autoría

Proyecto realizado por Matias De Sousa con aportes de Nahue Campos. El historial de Git conserva la contribución individual de cada participante.

## Licencia

Distribuido bajo la licencia MIT. Consultá [LICENSE](LICENSE) para más información.
