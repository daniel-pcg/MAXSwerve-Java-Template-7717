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

Ejemplo de la página, el tag 1 del campo 2024:

```json
{
  "family": "apriltag3_36h11_classic",
  "id": 1,
  "size": 165.1,
  "transform": [-0.5, -0.866025, 0, 6.808597,
                 0.866025, -0.5, 0, -3.859403,
                 0, 0, 1, 1.355852,
                 0, 0, 0, 1],
  "unique": 1
}
```

La página no dice en qué posición de la matriz van la rotación y la traslación. Esta práctica usa la convención estándar de las matrices de 4×4: la rotación ocupa las 3 primeras filas y columnas, y la traslación `x`, `y`, `z` está en la última columna de las 3 primeras filas. Confírmalo comparando con el ejemplo.

## Ejercicios

Completa las cuatro funciones de `Fmap.java`:

1. `index`: posición en el array de 16 valores de la fila `row` y la columna `col`. La matriz se guarda fila por fila.
2. `translation`: devuelve `{x, y, z}` del tag, en metros.
3. `sizeMeters`: pasa el `size` de milímetros a metros.
4. `yawDegrees`: giro del tag alrededor del eje vertical, en grados, usando `atan2` con los valores de la primera columna de la matriz (filas 1 y 0).

## Para pensar

- La página mezcla milímetros (`size`) y metros (`transform`). ¿Qué error causaría confundirlos?
- El tag 1 del ejemplo tiene `y` negativa. ¿Qué te dice eso sobre dónde está el origen de ese mapa?

## Cómo correrlo

```bash
java Fmap.java
```

En un compilador online, cambia el nombre de la clase a `Main` y pulsa Run. El programa imprime `PASS` o `FAIL` por cada prueba. Al principio todas fallan con "sin implementar".

## Hecho cuando

La última línea dice `6 de 6 pruebas pasaron`.
