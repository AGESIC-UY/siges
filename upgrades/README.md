# Actualizaciones SIGES

Este directorio contiene scripts para la actualización de la base de datos de SIGES.

Los scripts deben ser ejecutados en orden creciente hasta llegar a la versión deseada antes de realizar el deploy del ear.

Dentro de `upgrade-6.17.5.sql` están los scripts desde la versión **6.7.1** a la **6.17.5**.

La versión 6.17.5 no incluye cambios de base de datos propios (solo aplicación). El script llega hasta 6.17.1e.

## Orden de ejecución de scripts individuales (referencia)

| Versión | Archivo origen |
|---------|----------------|
| 6.8.0 | script_6.8.0.sql |
| 6.9.2 | script_6.9.2.sql |
| 6.10.0 | script_6.10.0.sql |
| 6.11.0 | script_6.11.0.sql |
| 6.11.1 | script_6.11.1.sql |
| 6.12.0 | script_6.12.0.sql |
| 6.13.0 | script_6.13.0.sql |
| 6.13.1 | script_v_6.13.1.sql |
| 6.14.0 | script_v_6.14.0.sql |
| 6.15.0 | script_v_6.15.0.sql |
| 6.15.4 | script_v_6.15.4.sql |
| 6.16.4 | script_v_6.16.4.sql |
| 6.17.1b | script_v_6.17.1b.sql |
| 6.17.1e | script_v_6.17.1e.sql |

Los scripts individuales se encuentran en `src/SS-SIGES-database/mysql/sql/`.

## Nota sobre script_v_6.17.1b.sql

Incluye configuración de segundo factor (2FA) y actualizaciones de correo electrónico
específicas del entorno AGESIC. Revisar antes de ejecutar en otros organismos.
