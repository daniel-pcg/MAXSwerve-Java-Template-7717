# Práctica 5: Map Builder y generador de AprilTags

Fuente: [AprilTag Map Builder](https://docs.limelightvision.io/es/docs/docs-limelight/pipeline-apriltag/apriltag-map-builder)

## Qué dice la documentación

La página presenta dos herramientas de Limelight:

- **Map Builder**, un editor 3D completo para mapas de AprilTag: [tools.limelightvision.io/map-builder](https://tools.limelightvision.io/map-builder)
- **AprilTag Generator**, para imprimir AprilTags a un tamaño exacto: [tools.limelightvision.io/apriltag-generator](https://tools.limelightvision.io/apriltag-generator)

La página no explica cómo se usa el Map Builder. Esta práctica es de exploración: lo que descubras lo anotas, sin dar por hecho cómo funciona.

## Parte 1: explorar (en grupo, sin código)

1. Abre el Map Builder y recorre la interfaz. Anota qué puedes hacer con un tag: qué datos muestra y qué puedes cambiar.
2. Compara lo que ves con los campos del `.fmap` de la práctica 4 (`id`, `size`, `transform`, `family`). Anota cuáles aparecen en la herramienta y cuáles no.
3. Abre el AprilTag Generator y anota qué opciones de tamaño y de hoja ofrece. Usa los resultados para planear el AprilTag de prueba de tu clase.

## Parte 2: código

Completa las dos funciones de `Mapa.java`, que sirven para revisar un mapa que armes:

1. `distance3d`: distancia entre dos puntos `{x, y, z}`, por ejemplo la posición de dos tags.
2. `midpoint`: punto medio entre dos puntos `{x, y, z}`.

## Para pensar

- ¿Para qué te serviría revisar la distancia entre dos tags de tu mapa?
- Si imprimes un tag a un tamaño distinto del que dice el mapa, ¿qué pasará con la pose que calcula la Limelight?

## Cómo correrlo

```bash
java Mapa.java
```

En un compilador online, cambia el nombre de la clase a `Main` y pulsa Run. El programa imprime `PASS` o `FAIL` por cada prueba. Al principio todas fallan con "sin implementar".

## Hecho cuando

La última línea dice `4 de 4 pruebas pasaron`.
