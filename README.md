# App_Group: AgroAgenda

Repositorio de aplicaciones de uso general. Por ahora contiene **AgroAgenda**, una app Android para **llevar el control de cosechas agrícolas en sociedad**: qué se sembró, quiénes son los socios y su porcentaje, cuánto se invirtió y cuánto se vendió.

![Android](https://img.shields.io/badge/Android-API%2015%E2%80%9323-3DDC84?logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/DB-SQLite-003B57?logo=sqlite&logoColor=white)
![Gradle](https://img.shields.io/badge/build-Gradle-02303A?logo=gradle&logoColor=white)

## Funcionalidades

- **Nueva cosecha:** registra parcela, producto y fecha de siembra (con `DatePicker`).
- **Socios:** agrega socios a cada siembra con su nombre, apodo y porcentaje de participación.
- **Inversiones y ventas:** modelo de datos para registrar gastos (tipo, detalle, costo) y ventas (cantidad, precio).
- Menú lateral (Navigation Drawer) con las opciones *Nueva*, *Administrar*, *Vender* y *Calcular cosecha*.
- Persistencia local con SQLite, sin necesidad de conexión a internet.

## Modelo de datos

```
Siembro (id, parcela, fecha, producto)
   ├── Socio     (id, id_siembro, nombre, apodo, porcentaje)
   ├── Inversion (id, id_siembro, tipo, detalle, costo)
   └── Venta     (id, id_siembro, cantidad, precio)
```

Los identificadores se generan con UUID y un prefijo (`SI-`, `SO-`, ...). El acceso a datos está en `OperacionesBaseDatos`, un singleton con operaciones CRUD.

## Requisitos

- Android Studio
- Android SDK 23 (min SDK 15)
- JDK compatible con Gradle y el Android Gradle Plugin de la época (el proyecto usa `compile`/`testCompile` y Support Library 23)

## Ejecución

```bash
git clone https://github.com/jairoe05/App_Group.git
```

1. En Android Studio: **File → Open** y selecciona la carpeta `AgroAgenda2/`.
2. Deja que Gradle sincronice. En versiones modernas de Android Studio puede ser necesario migrar `compile` → `implementation` y la Support Library → AndroidX.
3. Ejecuta en un emulador o en un dispositivo (▶ Run).

## Estructura

```
AgroAgenda2/app/src/main/java/alexis_agro_agenda/agroagenda/
├── MainActivity.java        # Pantalla principal + Navigation Drawer
├── nuevaCosecha.java        # Registro de una nueva siembra
├── nombreSocio.java         # Diálogo para agregar socios
├── modelo/                  # Siembro, Socios, Inversion, Venta
├── sqlite/                  # Contrato, helper y operaciones de BD
└── ventanas/LoginDialog.java
```

## Estado

Prototipo en desarrollo: el registro de siembras y socios está implementado. Las pantallas de administración, venta y cálculo de la cosecha están planificadas en el menú.
