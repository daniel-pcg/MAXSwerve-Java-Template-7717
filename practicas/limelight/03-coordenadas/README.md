# Práctica 3: sistemas de coordenadas

Fuente: [Sistemas de coordenadas 3D en detalle](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-coordinate-systems)

## Qué dice la documentación

| Sistema | Origen | Ejes |
|---|---|---|
| Cámara | El lente | X derecha, Y abajo, Z hacia afuera de la cámara |
| Robot | El centro del chasis, proyectado al piso | X adelante, Y derecha, Z arriba |
| Campo FRC | El centro del campo | X por el lado largo, Y hacia el lado corto, Z al cielo |
| WPIBlue | La esquina de la alianza azul | Los mismos ejes del campo FRC |
| WPIRed | La esquina de la alianza roja, girado 180° | No se recomienda |

La página indica que WPIBlue es el sistema preferido por los equipos FRC. En la interfaz web, la posición de la cámara se da como `LL Forward`, `LL Right` y `LL Up`, en metros, respecto al centro del robot.

## Ejercicios

La página no da fórmulas de conversión. En esta práctica las deduces de los ejes de la tabla, suponiendo que la cámara mira recto hacia adelante, sin inclinación ni giro. Completa las tres funciones de `Coordenadas.java`:

1. `cameraPointToRobot`: convierte un punto `{x, y, z}` medido en el espacio de la cámara a `{adelante, derecha, arriba}` en el espacio del robot, sumando la posición de la cámara (`forward`, `right`, `up`).
2. `offsetErrorMeters`: distancia 3D entre dos puntos. Sirve para ver cuánto se corre un punto si mides mal la posición de la cámara.
3. `isRecommendedForFrc`: recibe `"WPIBlue"`, `"WPIRed"` o `"FRC"` y dice si es el sistema preferido.

## Experimento

Cuando termines, cambia `forward` en 0.10 m en una llamada y compara los resultados con `offsetErrorMeters`. ¿Cuánto se mueve el punto?

## Para pensar

- ¿Por qué el eje Y de la cámara apunta hacia abajo y el del robot hacia la derecha?
- ¿Qué pasa con la pose del robot si la posición de la cámara que le diste a la Limelight es incorrecta?

## Cómo correrlo

```bash
java Coordenadas.java
```

En un compilador online, cambia el nombre de la clase a `Main` y pulsa Run. El programa imprime `PASS` o `FAIL` por cada prueba. Al principio todas fallan con "sin implementar".

## Hecho cuando

La última línea dice `8 de 8 pruebas pasaron`.
