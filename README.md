<div align="center">

# 🏦 Simulador Bancario en Java — POO

[![Java CI](https://img.shields.io/github/actions/workflow/status/Matydesousa/simulador-bancario-java/java.yml?branch=main&label=Java%20CI&logo=githubactions&logoColor=white&style=flat-square)](https://github.com/Matydesousa/simulador-bancario-java/actions/workflows/java.yml)
[![Java Version](https://img.shields.io/badge/Java-JDK%208%2B%20%7C%2021-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Paradigm](https://img.shields.io/badge/Paradigma-POO%20%2F%20OOP-blue?style=flat-square)](src/)
[![Dependencies](https://img.shields.io/badge/Dependencies-Zero%20(Pure%20JDK)-success?style=flat-square)](src/)
[![License: MIT](https://img.shields.io/badge/Licencia-MIT-green?style=flat-square)](LICENSE)

---

Proyecto educativo de modelado orientado a objetos en Java que representa las operaciones e interacciones fundamentales de una red bancaria y sus cajeros automáticos.

</div>

## 📌 Descripción

El simulador ilustra cómo interactúan entidades del dominio bancario (bancos, cajeros automáticos y cuentas bancarias), enfatizando la **delegación de responsabilidades**, el **encapsulamiento**, y la **sincronización de estados** entre el dinero disponible en el cajero y el saldo de las cuentas durante extracciones y transferencias.

---

## 🚀 Diagrama de Clases UML

El diagrama resume los atributos y métodos de negocio principales; se omiten accesores y métodos de representación para mantenerlo legible.

```mermaid
classDiagram
    class Banco {
        -Integer codigo
        -String nombre
        -String cuit
        -String direccion
        -Date fechaAlta
        -Cajero[] cajeros
        -CuentaBancaria[] cuentas
        +Banco(Integer codigo, String nombre, String cuit, String direccion, Date fechaAlta)
        +agregarCajero(Cajero cajero) void
        +listarCajeros() Cajero[]
        +buscarCajero(Integer numeroSerie) Cajero
        +agregarCuenta(CuentaBancaria cuenta) void
        +buscarCuenta(Integer numeroCuenta) CuentaBancaria
    }

    class Cajero {
        -Integer numeroSerie
        -String ubicacion
        -String estado
        -Double saldoDisponible
        -int capacidadBilletes
        -Banco banco
        +Cajero(Integer numeroSerie, String ubicacion, String estado, Double saldoDisponible, int capacidadBilletes, Banco banco)
        +dispensarEfectivo(Double monto) Boolean
        +extraer(CuentaBancaria cuenta, Double monto) Boolean
        +consultarSaldo(CuentaBancaria cuenta) Double
        +recargarEfectivo(Double monto) void
    }

    class CuentaBancaria {
        -Integer numeroCuenta
        -double saldo
        -String titular
        -String tipoCuenta
        -String cbu
        +CuentaBancaria(Integer numeroCuenta, double saldo, String titular, String tipoCuenta, String cbu)
        +depositar(Double monto) void
        +extraer(Double monto) Boolean
        +transferir(CuentaBancaria destino, double monto) Boolean
        +consultarSaldo() Double
    }

    class Main {
        +main(String[] args) void
    }

    Banco "1" o-- "0..*" Cajero : registra
    Banco "1" o-- "0..*" CuentaBancaria : registra
    Cajero --> Banco : pertenece a
    Cajero --> CuentaBancaria : opera sobre
    CuentaBancaria --> CuentaBancaria : transfiere a
    Main ..> Banco : inicializa y prueba
```

---

## 🔄 Flujo de Extracción en Cajero

```mermaid
sequenceDiagram
    actor Usuario
    participant Cajero
    participant Cuenta as CuentaBancaria

    Usuario->>Cajero: solicitar extracción ($ monto)
    alt Monto inválido o efectivo insuficiente
        Cajero-->>Usuario: false (operación rechazada)
    else El cajero dispone de efectivo
        Cajero->>Cuenta: extraer(monto)
        alt Saldo insuficiente en Cuenta
            Cuenta-->>Cajero: false
            Cajero-->>Usuario: false (operación rechazada)
        else Saldo suficiente en Cuenta
            Cuenta->>Cuenta: saldo -= monto
            Cuenta-->>Cajero: true
            Cajero->>Cajero: dispensarEfectivo(monto)
            Cajero->>Cajero: saldoDisponible -= monto
            Cajero-->>Usuario: true (extracción completada)
        end
    end
```

---

## ✨ Principios de Diseño POO Demostrados

| Principio / Concepto | Implementación en el Proyecto |
| :--- | :--- |
| **🔒 Encapsulamiento** | Atributos privados con acceso controlado mediante métodos de negocio y getters estrictos. |
| **🤝 Delegación y Colaboración** | El cajero no descuenta dinero arbitrariamente: delega la validación y el débito a la entidad `CuentaBancaria`. |
| **⚡ Sincronización de Recursos** | Doble validación: la operación solo se consolida si tanto el cajero (efectivo físico) como la cuenta (saldo digital) disponen de fondos. |
| **📦 Independencia de Entornos** | Implementación pura en Java sin acoplamiento a frameworks, base de datos ni librerías de terceros. |

---

## 🛠️ Tecnologías

- **Lenguaje**: Java (compatible con JDK 8, 11, 17 y 21).
- **Herramientas**: `javac` y `java` estándar del JDK.
- **CI**: GitHub Actions con workflow automatizado sobre Ubuntu Linux.

---

## 💻 Compilación y Ejecución

Desde la raíz del proyecto:

```bash
# Compilar el código fuente
javac -encoding UTF-8 -d out src/*.java

# Ejecutar el flujo demostrativo
java -cp out Main
```

---

## 🧪 Pruebas Automatizadas

La suite de pruebas valida transferencias, extracciones con fondos insuficientes en cuenta, restricciones de efectivo en cajero y consultas de saldo:

```bash
# Compilar clases y suite de pruebas
javac -encoding UTF-8 -d out src/*.java tests/*.java

# Ejecutar pruebas con assertions habilitadas (-ea)
java -ea -cp out SimuladorBancarioTest
```

---

## 📂 Estructura del Repositorio

```text
simulador-bancario-java/
├── .github/workflows/
│   └── java.yml             # Integración continua con GitHub Actions
├── src/
│   ├── Banco.java           # Gestión central de cajeros y cuentas
│   ├── Cajero.java          # Entidad cajero y dispensador de efectivo
│   ├── CuentaBancaria.java  # Operaciones sobre fondos y titularidad
│   └── Main.java            # Caso de uso demostrativo
├── tests/
│   └── SimuladorBancarioTest.java # Suite de validación con assertions
├── LICENSE                  # Licencia de código abierto MIT
└── README.md                # Documentación del proyecto
```

---

## 👤 Autoría

Proyecto desarrollado por **[Matias De Sousa](https://github.com/Matydesousa)** con aportes de **Nahue Campos**.

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia **[MIT](LICENSE)**.
