# Práctica 1: localización del robot con MegaTag

Fuente: [Localización del robot con AprilTags](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-robot-localization)

## Qué dice la documentación

- La pose del robot en el campo sale del array `botpose_wpiblue` de NetworkTables: `x`, `y`, `z` en metros, y `roll`, `pitch`, `yaw` en grados. La página recomienda esa clave para FRC.
- Para que funcione hay que configurar la posición de la cámara respecto al robot en la interfaz web y subir un mapa del campo.
- El ejemplo de la página rechaza una medición cuando: no hay tags; o hay un solo tag con ambigüedad mayor que 0.7; o hay un solo tag a más de 3 m de la cámara.
- El ejemplo de la página usa desviaciones estándar de `0.5`, `0.5` y `9999999` (x, y y giro). El valor enorme del giro hace que el estimador ignore el ángulo que mide la visión.

## Ejercicios

Completa las cinco funciones de `Localizacion.java`:

1. `botposeX`: devuelve la posición `x` de un array `botpose`.
2. `botposeYawDegrees`: devuelve el `yaw` en grados.
3. `distanceMeters`: distancia en el plano del campo entre dos `botpose`, usando solo `x` e `y`.
4. `shouldReject`: aplica las tres reglas de rechazo de arriba. Un solo tag se juzga por ambigüedad y distancia; con dos tags o más, no se rechaza.
5. `stdDevsMt1`: devuelve las tres desviaciones estándar del ejemplo.

## Para pensar

- ¿Por qué el ejemplo le pasa al estimador el `timestamp` de la medición?
- ¿Qué pasaría si la ambigüedad y la distancia solo se revisaran cuando hay varios tags?

## Cómo correrlo

```bash
java Localizacion.java
```

En un compilador online, cambia el nombre de la clase a `Main` y pulsa Run. El programa imprime `PASS` o `FAIL` por cada prueba. Al principio todas fallan con "sin implementar".

## Hecho cuando

La última línea dice `9 de 9 pruebas pasaron`.
