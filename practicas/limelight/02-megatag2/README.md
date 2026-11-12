# Práctica 2: MegaTag2

Fuente: [Localización con MegaTag2](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-robot-localization-megatag2)

## Qué dice la documentación

- MegaTag2 necesita conocer el giro (`yaw`) del robot, y hay que enviárselo a la Limelight en cada ciclo. MegaTag1 no lo necesita.
- Convención del `yaw`: positivo en sentido antihorario, y 0° mira hacia el muro de la alianza roja.
- La clave de NetworkTables recomendada para FRC es `botpose_orb_wpiblue`.
- El ejemplo rechaza una medición cuando el giroscopio gira a más de 360 grados por segundo, o cuando no hay tags.
- El ejemplo usa desviaciones estándar de `0.7`, `0.7` y `9999999`.
- La Limelight 4 trae un IMU interno con modos: `0` usa solo el giro externo; `1` usa el giro externo y copia ese valor al IMU interno; `2` usa el IMU interno. La página sugiere el modo 1 mientras el robot espera el autónomo y el modo 2 mientras está habilitado o girando.

## Tus funciones

Completa `src/main/java/frc/robot/practicas/MegaTag2.java`:

1. `needsRobotYaw`: recibe `"MT1"` o `"MT2"` y dice si necesita el giro del robot.
2. `shouldRejectMt2`: rechaza si el valor absoluto del giro supera 360 °/s, o si no hay tags.
3. `stdDevsMt2`: devuelve las tres desviaciones estándar del ejemplo.
4. `imuMode`: devuelve el modo del IMU. Reglas de esta práctica: si está habilitado o girando, `2`; si no, y está esperando el autónomo, `1`; en cualquier otro caso, `0`. Habilitado tiene prioridad sobre esperar.

## Probar sin robot

```bash
.\gradlew.bat test
```

Hecho cuando las 12 pruebas de `MegaTag2Test` pasan.

## Probar con el robot

`VisionLab` ya le envía a la cámara el giro del robot en cada ciclo (`robot_orientation_set`), como pide la documentación. Despliega y abre Glass en `SmartDashboard/Practica2`.

| Clave | De dónde sale |
|---|---|
| `GiroGradosPorSeg`, `TagCount`, `X`, `Y` | Datos crudos del giroscopio y de `botpose_orb_wpiblue` |
| `NecesitaYaw` | Tu `needsRobotYaw("MT2")` |
| `Rechazar` | Tu `shouldRejectMt2` con el giro real del robot |
| `ModoIMU` | Tu `imuMode`: "esperando el autónomo" es el robot deshabilitado, y "girando" es más de 10 °/s |
| `StdDevs` | Tu `stdDevsMt2` |

Experimentos:

1. Con el robot deshabilitado, `ModoIMU` debe ser 1. Habilítalo y debe pasar a 2.
2. Gira el robot rápido con el control: `GiroGradosPorSeg` sube y, si pasa de 360, `Rechazar` se vuelve `true`.

El modo del IMU solo se publica en el dashboard. Para enviárselo de verdad a la cámara, cambia `kSendImuModeToCamera` a `true` en `Constants.java`, y hazlo solo con la cámara montada en horizontal, como pide la documentación para usar su IMU.

**Ojo con el giroscopio.** El template usa un `ADIS16470_IMU`. Si su chasis tiene otro modelo, el giro y la velocidad de giro se leen en 0. La práctica corre igual, pero MegaTag2 calcula la pose suponiendo que el robot mira siempre a 0°, y `Rechazar` nunca se activa por giro.

## Para pensar

- ¿Por qué MegaTag2 pide el giro y MegaTag1 no?
- ¿Qué cambia en el estimador de pose si en vez de `0.7` usas `0.1`?
