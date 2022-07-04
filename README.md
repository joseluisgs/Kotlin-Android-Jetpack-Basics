# Kotlin Android Jetpack Basics

Ejemplos para usar Kotlin a nivel básico para el desarrollo de aplicaciones Android.

[![Kotlin](https://img.shields.io/badge/Code-Kotlin-blueviolet)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Code-Android-green)](https://developer.android.com/jetpack)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Kotlin-Android-Jetpack-Basics)

![imagen](./images/logo.png)

- [Kotlin Android Jetpack Basics](#kotlin-android-jetpack-basics)
  - [Acerca de](#acerca-de)
  - [Lección 1: Compila tu primera app](#lección-1-compila-tu-primera-app)
  - [Lección 2: Diseños](#lección-2-diseños)
  - [Lección 3: Navegación](#lección-3-navegación)
  - [Lección 4: Ciclos de vida de actividades y fragmentos](#lección-4-ciclos-de-vida-de-actividades-y-fragmentos)
  - [Lección 5: Componentes de arquitectura](#lección-5-componentes-de-arquitectura)
  - [Lección 6: Corrutinas y bases de datos de Room](#lección-6-corrutinas-y-bases-de-datos-de-room)
  - [Lección 7: RecyclerView](#lección-7-recyclerview)
  - [Lección 8: Cómo conectarse a Internet](#lección-8-cómo-conectarse-a-internet)
  - [Autor](#autor)
    - [Contacto](#contacto)
  - [Licencia](#licencia)

## Acerca de
Los siguientes proyectos muestran como usar Kotlin para aplicaciones Android en modo básico siguiendo los code examples de[ Android Jetpack de Google](https://developer.android.com/courses/kotlin-android-fundamentals/toc).

![imagen](./images/android-advanced-topics.svg)

## Lección 1: Compila tu primera app
En la Lección 1, aprenderás a configurar Android Studio para usar Kotlin y a compilar apps. Comenzarás con "Hello World" y avanzarás hacia una app que utiliza archivos de imagen y un controlador de clics. Aprenderás cómo se estructuran los proyectos de Android, cómo usar y modificar vistas en tu app de Kotlin para Android, y cómo asegurarte de que tus apps admitan retrocompatibilidad. También aprenderás sobre los niveles de API y las bibliotecas de Android Jetpack.

## Lección 2: Diseños
En la Lección 2, aprenderás a usar el editor de diseño de Android Studio para crear diseños lineales y de restricciones. Crearás apps que obtengan y muestren entradas del usuario, que respondan a los toques de los usuarios y que cambien la visibilidad y el color de las vistas. En esta lección, también se explica cómo usar la vinculación de datos para eliminar llamadas ineficientes con el método findViewById().

## Lección 3: Navegación
En la lección 3, aprenderás a crear una navegación útil en una app. Crearás un fragmento, lo agregarás a una app y, por último, agregarás la navegación a la app con el gráfico de navegación de Android Studio. Agregarás un panel lateral de navegación y un menú de opciones a tu app, y trabajarás con la pila de actividades, cambiando el destino del botón Atrás del sistema. Por último, aprenderás a invocar una actividad externa desde la app.

## Lección 4: Ciclos de vida de actividades y fragmentos
En la Lección 4, aprenderás sobre los ciclos de vida de las actividades y los fragmentos, y aprenderás a administrar situaciones de ciclo de vida complejas. Trabajarás con una app introductoria que contiene varios errores relacionados con el ciclo de vida de Android. Deberás agregar registros a la app para comprender mejor los eventos de ciclo de vida, corregir los errores que contiene y agregar algunas mejoras. También aprenderás sobre la biblioteca de ciclo de vida de Android Jetpack, que puede ayudarte a administrar los eventos de ciclo de vida con código mejor organizado y fácil de mantener.

## Lección 5: Componentes de arquitectura
En la lección 5, aprenderás a usar los objetos ViewModel y LiveData. Aprenderás a usar objetos ViewModel para permitir que los datos sobrevivan a cambios de configuración, como las rotaciones de pantallas. Convertirás los datos de la IU de una app en objetos LiveData encapsulados y agregarás métodos de observador que reciban notificaciones cuando cambie el valor de LiveData.

También deberás integrar LiveData y ViewModel con la vinculación de datos, de modo que las vistas de tu diseño se comuniquen directamente con los objetos ViewModel, sin usar los fragmentos de la app para transmitir información. Esta técnica simplifica tu código y elimina la necesidad de controladores de clics en los controladores de IU.

## Lección 6: Corrutinas y bases de datos de Room
En la lección 6, aprenderás a usar la biblioteca de base de datos de Room. Room se encarga de muchas de las tareas de ajuste y configuración de una base de datos, y simplifica el código para interactuar con ella. Aprenderás a usar las corrutinas de Kotlin para alejar las operaciones de la base de datos del subproceso principal y descubrirás cómo usar ViewModel y LiveData con la navegación de apps.

## Lección 7: RecyclerView
En la lección 7, se explica cómo usar una RecyclerView para mostrar listas y cuadrículas de elementos de manera eficiente. Para cuadrículas y listas complejas, aprenderás a hacer que RecyclerView sea más eficiente y que tu código sea más fácil de mantener y extender. Aprenderás a hacer que los elementos de una RecyclerView admitan clics. También aprenderás a agregar más de un contenedor de vistas y un diseño a listas y cuadrículas en RecyclerView, por ejemplo, para agregar un encabezado a tu app.

## Lección 8: Cómo conectarse a Internet
La Lección 8 te enseña a usar las bibliotecas desarrolladas por la comunidad para conectarte a un servicio web a fin de recuperar y mostrar datos. Aprenderás a manejar posibles errores de red y a usar la biblioteca Glide para cargar y mostrar fotos de Internet. También compilarás una RecyclerView y la usarás para mostrar una cuadrícula de imágenes.


## Autor

Codificado con :sparkling_heart: por [José Luis González Sánchez](https://twitter.com/joseluisgonsan)

[![Twitter](https://img.shields.io/twitter/follow/joseluisgonsan?style=social)](https://twitter.com/joseluisgonsan)
[![GitHub](https://img.shields.io/github/followers/joseluisgs?style=social)](https://github.com/joseluisgs)

### Contacto

<p>
  Cualquier cosa que necesites házmelo saber por si puedo ayudarte 💬.
</p>
<p>
    <a href="https://twitter.com/joseluisgonsan" target="_blank">
        <img src="https://i.imgur.com/U4Uiaef.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://github.com/joseluisgs" target="_blank">
        <img src="https://distreau.com/github.svg" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://www.linkedin.com/in/joseluisgonsan" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/768px-LinkedIn_logo_initials.png" 
    height="30">
    </a>  &nbsp;&nbsp;
    <a href="https://joseluisgs.github.io/" target="_blank">
        <img src="https://joseluisgs.github.io/img/favicon.png" 
    height="30">
    </a>
</p>

## Licencia

Este proyecto está licenciado bajo licencia **MIT**, si desea saber más, visite el fichero [LICENSE](./LICENSE) para su
uso docente y educativo.
