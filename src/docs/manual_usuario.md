# 📘 Manual de Usuario — SportManager

Este manual guía al usuario en el uso correcto de la aplicación **SportManager**, enfocada en la gestión de deportes dentro de un club. La interfaz se basa en ventanas emergentes (`JOptionPane`) que permiten ingresar datos paso a paso.

---

## 🏁 Inicio de la Aplicación

Al ejecutar el programa, se abrirá una serie de ventanas emergentes que te guiarán en la creación de un deporte. Este paso es **obligatorio** para que el sistema funcione correctamente.

---

## 🎯 Paso 1: Crear un Deporte

> ⚠️ **Importante:** Solo se aceptan los siguientes nombres de deporte:
> - `tennis`
> - `basket`
> - `soccer`

Si se ingresa un nombre diferente, el sistema **no funcionará correctamente** y puede lanzar errores o comportamientos inesperados.

---

### 🧩 Ingreso de Datos

Sigue las instrucciones en pantalla y completa los siguientes campos:

1. **Nombre del deporte**  
   - Escribe exactamente: `tennis`, `basket` o `soccer`.

2. **Número de espacios disponibles**  
   - Ingresa un número entero (por ejemplo: `3`).

3. **Costo por sesión**  
   - Ingresa un valor decimal (por ejemplo: `25.0`).

4. **Formato de juego**  
   - Selecciona una opción: `SINGLE`, `TEAM`, o `BOTH`.

5. **Equipamiento necesario**  
   - Ingresa el nombre del equipamiento (ej. `Raqueta`, `Balón`, `Red`).
   - Puedes agregar varios elementos seleccionando "Sí" cuando se te pregunte si deseas añadir otro.

---

## ✅ Confirmación

Una vez completados todos los pasos, aparecerá un mensaje:

***¡Deporte creado y agregado exitosamente!***

Esto indica que el deporte ha sido registrado correctamente en el sistema.

---

## 🧭 Navegación y Botones

La aplicación utiliza ventanas emergentes (`JOptionPane`) para cada paso. No hay botones visibles en pantalla, sino que debes seguir las instrucciones que aparecen en cada cuadro de diálogo:

- **InputDialog**: para escribir datos.
- **OptionDialog**: para seleccionar opciones.
- **ConfirmDialog**: para confirmar acciones.
- **MessageDialog**: para mostrar mensajes de éxito o error.

---

## 🛠️ Recomendaciones

- No cierres las ventanas emergentes sin completar los datos.
- Verifica que el nombre del deporte esté escrito correctamente (`tennis`, `basket`, `soccer`).
- Si ocurre un error, reinicia la aplicación y vuelve a intentarlo.

