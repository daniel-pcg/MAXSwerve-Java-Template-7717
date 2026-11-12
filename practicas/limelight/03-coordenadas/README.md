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

## Tus funciones

La página no da fórmulas de conversión. En esta práctica las deduces de los ejes de la tabla, suponiendo que la cámara mira recto hacia adelante, sin inclinación ni giro. Completa `src/main/java/frc/robot/practicas/Coordenadas.java`:

1. `cameraPointToRobot`: convierte un punto `{x, y, z}` medido en el espacio de la cámara a `{adelante, derecha, arriba}` en el espacio del robot, sumando la posición de la cámara (`forward`, `right`, `up`).
2. `offsetErrorMeters`: distancia 3D entre dos puntos.
3. `isRecommendedForFrc`: recibe `"WPIBlue"`, `"WPIRed"` o `"FRC"` y dice si es el sistema preferido.

## Probar sin robot

```bash
.\gradlew.bat test
```

Hecho cuando las 8 pruebas de `CoordenadasTest` pasan.

## Probar con el robot

1. Mide con cinta dónde está el lente respecto al centro del robot, proyectado al piso: cuánto hacia adelante, hacia la derecha y hacia arriba, en metros.
2. Pon esos valores en `Constants.VisionLabConstants` (`kCameraForwardMeters`, `kCameraRightMeters`, `kCameraUpMeters`) y también en la interfaz web de la Limelight (`LL Forward`, `LL Right`, `LL Up`).
3. Despliega, pon un AprilTag delante de la cámara y abre Glass en `SmartDashboard/Practica3`.

| Clave | De dónde sale |
|---|---|
| `CamaraXYZ` | `targetpose_cameraspace`: dónde ve la cámara el tag, en su propio sistema |
| `LimelightRobotXYZ` | `targetpose_robotspace`: el mismo tag en el sistema del robot, calculado por la Limelight |
| `TagEnRobotXYZ` | Tu `cameraPointToRobot` con los valores medidos |
| `DiferenciaConLimelightM` | Tu `offsetErrorMeters` entre tu cálculo y el de la Limelight |

Experimentos:

1. Mide con cinta dónde está el tag respecto al centro del robot y compáralo con `TagEnRobotXYZ`.
2. Cambia `kCameraForwardMeters` en 0.10 m, despliega de nuevo y mira cuánto cambia `DiferenciaConLimelightM`.

La documentación de NetworkTables da el contenido de `targetpose_robotspace`, pero no aclara el orden de sus ejes. Si `DiferenciaConLimelightM` es grande y los números parecen los mismos con otro orden o con el signo cambiado, ese es el hallazgo: anótenlo y lo revisamos juntos. Tampoco cuadrará si la cámara está inclinada, porque esta práctica supone que mira recto.

## Para pensar

- ¿Por qué el eje Y de la cámara apunta hacia abajo y el del robot hacia la derecha?
- ¿Qué pasa con la pose del robot si la posición de la cámara que le diste a la Limelight es incorrecta?
