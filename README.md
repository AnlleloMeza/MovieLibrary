# MovieLibrary

Aplicación Android para gestionar un catálogo personal de películas, desarrollada como parte del Taller 4 de desarrollo móvil.

## Descripción

MovieLibrary permite llevar un registro de películas que has visto o quieres ver, almacenando toda la información en una base de datos local.

## Funcionalidades

- Ver lista completa de películas
- Agregar nuevas películas
- Ver detalle de cada película
- Editar información de una película
- Marcar película como vista / no vista
- Eliminar películas
- Persistencia de datos local con Room

## Arquitectura

El proyecto implementa el patrón MVVM (Model - View - ViewModel):
com.meza.movielibrary/
├── model/          # Clase de dominio Movie
├── db/             # Room: MovieEntity, MovieDao, AppDatabase
├── repository/     # MovieRepository
├── viewmodel/      # MovieViewModel
└── ui/
├── list/       # MovieListFragment + MovieAdapter
├── detail/     # MovieDetailFragment
└── edit/       # MovieEditFragment

## Tecnologías utilizadas

- Kotlin
- Room - Base de datos local
- LiveData - Observación reactiva de datos
- ViewModel - Gestión del estado de la UI
- Navigation Component - Navegación entre fragments
- ViewBinding - Acceso seguro a vistas
- KSP - Procesador de anotaciones para Room
- Coroutines - Operaciones asíncronas

## Requisitos

- Android Studio Hedgehog o superior
- SDK minimo: API 24 (Android 7.0)
- SDK objetivo: API 36

## Instalación

1. Clona el repositorio:

git clone https://github.com/AnlleloMeza/MovieLibrary.git

2. Abre el proyecto en Android Studio
3. Sincroniza el proyecto con Gradle
4. Ejecuta la app en un emulador o dispositivo físico

## Autor

Anllelo Meza
Taller 4 - Arquitectura MVVM con Navigation, Safe Args, LiveData y Room