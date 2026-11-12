# Práctica 2: MegaTag2

Fuente: [Localización con MegaTag2](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-robot-localization-megatag2)

## Qué dice la documentación

- MegaTag2 necesita conocer el giro (`yaw`) del robot, y hay que enviárselo a la Limelight en cada ciclo con `SetRobotOrientation`. MegaTag1 no lo necesita.
- Convención del `yaw`: positivo en sentido antihorario, y 0° mira hacia el muro de la alianza roja.
- La clave de NetworkTables recomendada para FRC es `botpose_orb_wpiblue`.
- El ejemplo rechaza una medición cuando el giroscopio gira a más de 360 grados por segundo, o cuando no hay tags.
- El ejemplo usa desviaciones estándar de `0.7`, `0.7` y `9999999`.
- La Limelight 4 trae un IMU interno con modos: `0` usa solo el giro externo; `1` usa el giro externo y copia ese valor al IMU interno; `2` usa el IMU interno. La página sugiere el modo 1 mientras el robot espera el autónomo y el modo 2 mientras está habilitado o girando.

## Ejercicios

Completa las cuatro funciones de `MegaTag2.java`:

1. `needsRobotYaw`: recibe `"MT1"` o `"MT2"` y dice si necesita el giro del robot.
2. `shouldRejectMt2`: rechaza si el valor absoluto del giro supera 360 °/s, o si no hay tags.
3. `stdDevsMt2`: devuelve las tres desviaciones estándar del ejemplo.
4. `imuMode`: devuelve el modo del IMU. Reglas de esta práctica: si está habilitado o girando, `2`; si no, y está esperando el autónomo, `1`; en cualquier otro caso, `0`. Habilitado tiene prioridad sobre esperar.

## Para pensar

- ¿Por qué MegaTag2 pide el giro y MegaTag1 no?
- ¿Qué cambia en el estimador de pose si en vez de `0.7` usas `0.1`?

## Cómo correrlo

```bash
java MegaTag2.java
```

En un compilador online, cambia el nombre de la clase a `Main` y pulsa Run. El programa imprime `PASS` o `FAIL` por cada prueba. Al principio todas fallan con "sin implementar".

## Hecho cuando

La última línea dice `12 de 12 pruebas pasaron`.
