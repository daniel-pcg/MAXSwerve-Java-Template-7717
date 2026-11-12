# Práctica 4: el formato de mapa `.fmap`

Fuente: [Especificación de mapa AprilTag](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-map-specification)

## Qué dice la documentación

Un `.fmap` es un archivo JSON con `type` (por ejemplo `frc`) y una lista `fiducials`. Cada tag tiene:

| Campo | Significado |
|---|---|
| `family` | Familia del tag, por ejemplo `apriltag3_36h11_classic` |
| `id` | Número del tag |
| `size` | Tamaño del tag en milímetros |
| `transform` | Matriz de 4×4 con 16 valores, guardada fila por fila, en unidades del SI |
| `unique` | Si el tag es único en el mapa |

El archivo `src/main/deploy/aula.fmap` trae, como punto de partida, el tag 1 del ejemplo de la página (campo 2024).

La página no dice en qué posición de la matriz van la rotación y la traslación. Esta práctica usa la convención estándar de las matrices de 4×4: la rotación ocupa las 3 primeras filas y columnas, y la traslación `x`, `y`, `z` está en la última columna de las 3 primeras filas. Confírmalo comparando con el ejemplo.

## Tus funciones

Completa `src/main/java/frc/robot/practicas/Fmap.java`:

1. `index`: posición en el array de 16 valores de la fila `row` y la columna `col`. La matriz se guarda fila por fila.
2. `translation`: devuelve `{x, y, z}` del tag, en metros.
3. `sizeMeters`: pasa el `size` de milímetros a metros.
4. `yawDegrees`: giro del tag alrededor del eje vertical, en grados, usando `atan2` con los valores de la primera columna de la matriz (filas 1 y 0).

## Probar sin robot

```bash
.\gradlew.bat test
```

Hecho cuando las 6 pruebas de `FmapTest` pasan.

## Probar con el robot

`VisionLab` lee `src/main/deploy/aula.fmap` al arrancar. Al desplegar, todo lo que está en `src/main/deploy` se copia al robot.

1. Edita `aula.fmap` con los tags de su sala (la práctica 5 explica cómo armarlo) y sube el mismo archivo a la Limelight desde su interfaz web.
2. Despliega y abre Glass. `VisionLab/MapaCargado` debe estar en `true` y `VisionLab/TagsEnMapa` debe mostrar cuántos tags tiene el mapa.
3. Pon delante de la cámara un tag que esté en el mapa y mira `SmartDashboard/Practica4`.

| Clave | De dónde sale |
|---|---|
| `TagVisto` | El `tid` que reporta la cámara |
| `XYZ` | Tu `translation` sobre la matriz de ese tag en el mapa |
| `YawGrados` | Tu `yawDegrees` |
| `TamanoM` | Tu `sizeMeters` |

Si el `Estado` dice `el tag N no esta en el mapa`, la cámara ve un tag que tu `aula.fmap` no tiene.

## Para pensar

- La página mezcla milímetros (`size`) y metros (`transform`). ¿Qué error causaría confundirlos?
- El tag 1 del ejemplo tiene `y` negativa. ¿Qué te dice eso sobre dónde está el origen de ese mapa?
