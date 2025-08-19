# 🧠 Wiki del Proyecto – MAGNIX Sports

Bienvenido al repositorio oficial de **MAGNIX Sports**, una iniciativa desarrollada como parte del curso de Ingeniería Informatica. Este proyecto busca aplicar principios sólidos de diseño, implementación y análisis técnico para construir una solución escalable, mantenible y bien documentada.

---

## 1. 🚀 Inicio / Presentación del Proyecto

**Descripción general:**  
Los clubes y centros deportivos suelen enfrentar dificultades al gestionar reservas, controlar el flujo de personas y administrar sus espacios deportivos. Esta falta de organización puede traducirse en pérdida de clientes, desorden en los horarios y una imagen poco profesional ante el público.

**MAGNIX Sports** surge como solución a estos desafíos. Es una aplicación intuitiva diseñada para facilitar la gestión de reservas, el control de torneos y la administración eficiente de espacios deportivos. Además, incorpora una sección de comunidad que convierte cada encuentro en una experiencia más divertida y social, fortaleciendo el vínculo entre jugadores y aficionados.

**Objetivos principales:**

- Optimizar la gestión de **reservas deportivas**, permitiendo a los usuarios agendar espacios de forma rápida y ordenada.
- Facilitar la organización y seguimiento de **torneos**, con herramientas para inscripciones, calendarios y resultados.
- Fomentar la interacción entre usuarios mediante una **sección de comunidad**, que promueve el juego social, la conexión entre deportistas y el sentido de pertenencia.


**Tecnologías utilizadas:**

- Lenguaje: `Java`
- Frameworks: `None`
- Herramientas: `Git`, `VS Code`

---

## 2. 🧩 Fundamentos de Ingeniería de Software

Este proyecto se desarrolló siguiendo principios sólidos de ingeniería de software, con especial énfasis en atributos de calidad clave para su contexto educativo y funcional. Se priorizó la **usabilidad**, ofreciendo una interfaz amigable y sencilla pensada para estudiantes y usuarios no técnicos. La **mantenibilidad** se garantizó mediante una arquitectura modular, donde cada componente cumple una responsabilidad clara y bien definida, facilitando futuras actualizaciones. Además, se promovió la **reusabilidad** al diseñar módulos de planificación y notificación que pueden integrarse fácilmente en otros sistemas o aplicaciones similares. Estas decisiones técnicas aseguran que MAGNIX Sports sea una solución robusta, adaptable y preparada para crecer.

---

## 3. 🧱 Diseño de Software

### 🔹 Principios SOLID
 - SRP: Cada Clase tiene su unica funcion
 - OCP: Se pueden agregar cuantos tipos de deporte se desee con la implementacion de extensiones
 - LSP: Liskov por la posibilidad de reemplazar las instanciaciones sin romper el sistema

### 🔹 Patrones de diseño aplicados

| Patrón        | Propósito                          | Ubicación en el código |
|---------------|------------------------------------|-------------------------|
| Bridge        | Separar abstracción de implementación   | Capeta tituada bridge (Cambio de tema claro o oscuroen comunity) |
| Factory       | Creación flexible de objetos       | Carpeta factory (Creacion del reservador y creador de torneo para cada uno de los deportes) |
| Observer      | Actualización reactiva de datos    | Carpeta con el titulo de observer (Notificar a un jugador en el procedimiento de reserva e inscripcion a torneos y reservas)   |
| Singleton     | Instancia unica en todo el codigo  | Carpeta con nombre singleton (Tener una lista unica de deportes) |

### 🔹 Diagramas UML 

> Ver carpeta `/docs/UML/` para los archivos `.png` y `.astah`.

---

## 4. 🛠️ Implementación

### 📁 Estructura del código
```
/src /controllers /models /views /services /tests /docs
```

### 🔗 Enlaces explicativos

- [Explicación de la arquitectura](src/docs/arquitectura.md)
- [Guía de instalación y ejecución](src/docs/instalacion.md)
- [Manual de usuario](src/docs/manual_usuario.md)

---

## 5. 📊 Análisis Técnico

### 🔸 Cohesión

Alta cohesión en módulos funcionales.

### 🔸 Acoplamiento

Bajo acoplamiento gracias al uso de interfaces y patrones.

### 🔸 Atributos de calidad evaluados

| Atributo        | Evaluación | Justificación |
|-----------------|------------|----------------|
| Mantenibilidad  | Alta       | Código modular y bien documentado |
| Escalabilidad   | Media      | Arquitectura preparada para microservicios, cambio de Joptions por nuevas tecnologias |

---

## 6. 👥 Créditos y Roles del Equipo

| Nombre     | Rol                    | Contribuciones Clave                                      |
|------------|------------------------|------------------------------------------------------------|
| Mauricio   | Líder de Desarrollo    | Diseño técnico, documentación modular, coordinación general |
| Nicolás    | Líder Arquitectónico   | Definición de patrones de diseño, aplicación de principios SOLID |
| Gabriela   | Líder de Negocio       | Modelado de lógica empresarial, alineación con requerimientos del cliente |

---

> 📌 *Este documento está en constante evolución. Para sugerencias o mejoras, por favor abre un issue o pull request.*
"# proyecto_arquitectura" 
