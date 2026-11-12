# Prácticas de Limelight

Cada práctica tiene tres partes:

1. **Tus funciones**, en `src/main/java/frc/robot/practicas/`. Mientras no las implementes, lanzan `UnsupportedOperationException`.
2. **Pruebas sin robot**, en `src/test/java/frc/robot/practicas/`. Se saltan mientras la función no esté implementada; cuando la implementas, te dicen si está bien.
3. **Prueba con el robot.** El subsistema `VisionLab` lee la Limelight cada 20 ms, llama a tus funciones con datos reales y publica los resultados en el dashboard.

## Probar sin robot

```bash
.\gradlew.bat test
```

En Mac o Linux: `sh gradlew test`. El reporte HTML queda en `build/reports/tests/test/index.html`.

## Probar con el robot

1. Despliega el código: `Ctrl+Shift+P` → **WPILib: Deploy Robot Code**. El deploy no corre las pruebas, así que funciona aunque tus prácticas estén a medias.
2. Abre Glass y, en **NetworkTables**, busca `SmartDashboard`.
3. Cada práctica publica en su carpeta: `Practica1/…`, `Practica2/…`. La clave `Estado` dice `ok`, `sin implementar`, `sin datos de la camara`, `sin tag` o el error que lanzó tu código.

El código de los estudiantes nunca detiene el robot: si una función lanza un error, `VisionLab` lo muestra en `Estado` y sigue.

`VisionLab/VeTag` y `VisionLab/TagId` muestran lo que ve la cámara, sin pasar por ninguna práctica. Si `VeTag` nunca cambia, el problema es la cámara o su conexión, no tu código.

## Las prácticas

| Práctica | Tus funciones | Necesita en el robot |
|---|---|---|
| [1 Localización](limelight/01-localizacion/README.md) | `Localizacion.java` | Cámara con su posición configurada y un mapa cargado |
| [2 MegaTag2](limelight/02-megatag2/README.md) | `MegaTag2.java` | Lo mismo que la 1, más un giroscopio que funcione |
| [3 Coordenadas](limelight/03-coordenadas/README.md) | `Coordenadas.java` | Cámara montada y su posición medida con cinta |
| [4 Mapa `.fmap`](limelight/04-mapa-fmap/README.md) | `Fmap.java` | El `aula.fmap` de la sala, subido también a la cámara |
