# Práctica 5: el mapa de la sala con el Map Builder

Fuente: [AprilTag Map Builder](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-map-builder)

## Qué dice la documentación

La página presenta dos herramientas de Limelight:

- **Map Builder**, un editor 3D completo para mapas de AprilTag: [tools.limelightvision.io/map-builder](https://tools.limelightvision.io/map-builder)
- **AprilTag Generator**, para imprimir AprilTags a un tamaño exacto: [tools.limelightvision.io/apriltag-generator](https://tools.limelightvision.io/apriltag-generator)

La página no explica cómo se usa el Map Builder. La parte de la herramienta es de exploración: lo que descubran lo anotan, sin dar por hecho cómo funciona. Según la página de sistemas de coordenadas, el Map Builder usa el sistema FRC, con el origen en el centro del campo.

## Parte 1: armar el mapa de la sala

1. Imprimen dos o tres AprilTags con el generador, todos del mismo tamaño, y los pegan en la sala.
2. Eligen un punto del piso como origen y miden con cinta dónde quedó el centro de cada tag: `x`, `y` y altura `z`, en metros.
3. En el Map Builder colocan esos tags con las medidas y el tamaño impreso. Anotan qué datos de cada tag muestra la herramienta y cuáles corresponden a los campos del `.fmap` (`id`, `size`, `transform`, `family`).
4. Exportan el mapa, lo suben a la Limelight desde su interfaz web y reemplazan `src/main/deploy/aula.fmap` con el mismo archivo.

## Tus funciones

Completa `src/main/java/frc/robot/practicas/Mapa.java`:

1. `distance3d`: distancia entre dos puntos `{x, y, z}`.
2. `midpoint`: punto medio entre dos puntos `{x, y, z}`.

## Probar sin robot

```bash
.\gradlew.bat test
```

Hecho cuando las 4 pruebas de `MapaTest` pasan.

## Probar con el robot

Esta práctica usa también tu `translation` de la práctica 4: si no la implementaste, `Estado` dirá `sin implementar`.

Despliega, pon el robot frente a uno de los tags del mapa y abre Glass en `SmartDashboard/Practica5`.

| Clave | De dónde sale |
|---|---|
| `RobotXYZ` | `botpose`: dónde cree la Limelight que está el robot, con origen en el centro, el mismo sistema del mapa |
| `TagXYZ` | Tu `translation` de la práctica 4, sobre el tag que ve la cámara |
| `DistanciaRobotTagM` | Tu `distance3d` entre el robot y el tag |
| `PuntoMedioXYZ` | Tu `midpoint` |

Experimento: mide con cinta la distancia entre el centro del robot y el tag, y compárala con `DistanciaRobotTagM`. Si no cuadra, revisen en este orden: las medidas del mapa, el tamaño impreso del tag y la posición de la cámara de la práctica 3.

## Para pensar

- ¿Para qué te serviría revisar la distancia entre dos tags de tu mapa?
- Si imprimes un tag a un tamaño distinto del que dice el mapa, ¿qué pasará con la pose que calcula la Limelight?
