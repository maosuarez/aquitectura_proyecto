# 🚀 Guía de Instalación y Ejecución — SportManager

Este documento explica cómo instalar y ejecutar el proyecto **SportManager**, una aplicación Java con interfaz gráfica basada en `JOptionPane`, diseñada para la gestión de clubes deportivos.

---

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- [Java JDK 8 o superior](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- Un IDE compatible (recomendado: IntelliJ IDEA, Eclipse o VS Code con extensión Java)

---

## 📁 Clonar el Proyecto

```bash
git clone https://github.com/tu-usuario/sportmanager.git
cd sportmanager
```

## ⚙️ Estructura del Proyecto

El proyecto usa Maven como sistema de construcción. El archivo `pom.xml` define las dependencias y configuración:

```
- **groupId:** `com.magnix.app`
- **artifactId:** `sportmanager`
- **version:** `1.0-SNAPSHOT`
- **packaging:** `jar`
```

**Dependencia incluida:**

- `junit:junit:3.8.1` para pruebas unitarias.

---

## 🛠️ Compilar el Proyecto

Para compilar el proyecto y generar el archivo `.jar`, ejecuta el siguiente comando en la raíz del proyecto:

```bash
mvn clean install
```
