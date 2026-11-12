# Práctica 1: localización del robot con MegaTag

Fuente: [Localización del robot con AprilTags](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-robot-localization)

## Qué dice la documentación

- La pose del robot en el campo sale del array `botpose_wpiblue` de NetworkTables: `x`, `y`, `z` en metros, y `roll`, `pitch`, `yaw` en grados. La página recomienda esa clave para FRC.
- Para que funcione hay que configurar la posición de la cámara respecto al robot en la interfaz web y subir un mapa del campo.
- El ejemplo de la página rechaza una medición cuando: no hay tags; o hay un solo tag con ambigüedad mayor que 0.7; o hay un solo tag a más de 3 m de la cámara.
- El ejemplo usa desviaciones estándar de `0.5`, `0.5` y `9999999` (x, y y giro). El valor enorme del giro hace que el estimador ignore el ángulo que mide la visión.

## Tus funciones

Completa `src/main/java/frc/robot/practicas/Localizacion.java`:

1. `botposeX`: devuelve la posición `x` de un array `botpose`.
2. `botposeYawDegrees`: devuelve el `yaw` en grados.
3. `distanceMeters`: distancia en el plano del campo entre dos `botpose`, usando solo `x` e `y`.
4. `shouldReject`: aplica las tres reglas de rechazo. Un solo tag se juzga por ambigüedad y distancia; con dos tags o más, no se rechaza.
5. `stdDevsMt1`: devuelve las tres desviaciones estándar del ejemplo.

## Probar sin robot

```bash
.\gradlew.bat test
```

Hecho cuando las 9 pruebas de `LocalizacionTest` pasan.

## Probar con el robot

Despliega y abre Glass en `SmartDashboard/Practica1`. `VisionLab` lee `botpose_wpiblue` y `rawfiducials` de la cámara y llama a tus funciones.

| Clave | De dónde sale |
|---|---|
| `TagCount`, `Ambiguedad`, `DistanciaCamaraM` | Datos crudos de la cámara, se ven aunque no hayas implementado nada |
| `X`, `YawGrados` | Tus funciones `botposeX` y `botposeYawDegrees` |
| `DistanciaAlOrigenM` | Tu `distanceMeters`, entre el origen del mapa y el robot |
| `Rechazar` | Tu `shouldReject` con los datos reales |
| `StdDevs` | Tu `stdDevsMt1` |

Experimentos:

1. Un AprilTag a unos 1.5 m: `TagCount` en 1 y `Rechazar` en `false` si la ambigüedad es baja.
2. Aléjate a más de 3 m: `Rechazar` pasa a `true`.
3. Tapa la lente: `TagCount` en 0 y `Rechazar` en `true`.

Sin un mapa cargado en la cámara no hay pose. Si el mapa no es el de su sala, `X` y `YawGrados` salen como si el tag estuviera donde dice ese mapa: sirven para ver los valores cambiar, no para medir. El mapa de la sala se arma en las prácticas 4 y 5.

## Para pensar

- ¿Por qué el ejemplo le pasa al estimador el `timestamp` de la medición?
- ¿Qué pasaría si la ambigüedad y la distancia solo se revisaran cuando hay varios tags?
