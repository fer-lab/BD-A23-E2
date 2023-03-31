Herramientas disponibles

# Banner
Se utiliza para imprimir headers.

### Ejemplo 1:
```
Banner.display("Hola Header")
```

Resultado:
```
------------------------------------------------------------
| Hola Header                                              |
------------------------------------------------------------
```

### Ejemplo 2:
```
Banner.display("Hola Header", "Hola Body")
```

Resultado:
```
------------------------------------------------------------
| Hola Header                                              |
------------------------------------------------------------
| Hola Body                                                |
------------------------------------------------------------
```

### Ejemplo 3:
```
Banner.display("Multi Linea", "Linea 1\nLinea 2")
```

Resultado:
```
------------------------------------------------------------
| Multi Linea                                              |
------------------------------------------------------------
| Linea 1                                                  |
| Linea 2                                                  |
------------------------------------------------------------
```


### Ejemplo 4:
```
Banner.display("Word Wrap", "Lorem ipsum dolor sit amet, .....")
```

Resultado:
```
------------------------------------------------------------
| Word Wrap                                                |
------------------------------------------------------------
| Lorem ipsum dolor sit amet, consectetur adipiscing elit. |
| Donec efficitur nunc a nunc blandit, vitae malesuada sa  |
| pien luctus. Sed eget ultricies nulla, et pellentesque o |
| rci. Nam sit amet lacus vel augue commodo ornare at vel  |
| eros. Aliquam fermentum massa eu lacus tincidunt, eu mal |
| esuada.                                                  |
------------------------------------------------------------
```

# Options
Se utiliza para mostrar opciones y que el usuario eliga una.

### Ejemplo 1:
```
val options = Options("Menú Principal.");
options.add("Uno")
options.add("Dos")
options.add("Tres")
options.add("Cuatro")

val response = options.get()
println("Respuesta: $response");
```
Resultado:

```
------------------------------------------------------------
| Menú Principal.                                          |
------------------------------------------------------------
| 1. Uno                                                   |
| 2. Dos                                                   |
| 3. Tres                                                  |
| 4. Cuatro                                                |
------------------------------------------------------------
: 3
Respuesta: 3
```


### Ejemplo 2:
```
val options = Options("Menú Principal - Opción Letras", OptionsKeyTypes.LETTER);
options.add("Uno")
options.add("Dos")
options.add("Tres")
options.add("Cuatro")

val response = options.get()
println("Respuesta: $response");
```
Resultado:

```
------------------------------------------------------------
| Menú Principal - Opción Letras                           |
------------------------------------------------------------
| a. Uno                                                   |
| b. Dos                                                   |
| c. Tres                                                  |
| d. Cuatro                                                |
------------------------------------------------------------
: D
Respuesta: d
```



### Ejemplo 3:
```
val options = Options("Menú Principal - key definido");
options.add("Uno", "111")
options.add("Dos", "222")
options.add("Tres", "333")
options.add("Cuatro", "444")

val response = options.get()
println("Respuesta: $response");
```
Resultado:

```
------------------------------------------------------------
| Menú Principal - key definido                            |
------------------------------------------------------------
| 111. Uno                                                 |
| 222. Dos                                                 |
| 333. Tres                                                |
| 444. Cuatro                                              |
------------------------------------------------------------
```


### Ejemplo 4:
Continua preguntando hasta que se obtenga una respuesta válida.
```
val options = Options("Menú Principal - intentar de nuevo");
options.add("Uno")
options.add("Dos")
options.add("Tres")
options.add("Cuatro")

val response = options.get(true)
println("Respuesta: $response");
```
Resultado:

```
------------------------------------------------------------
| Menú Principal - intentar de nuevo                       |
------------------------------------------------------------
| 1. Uno                                                   |
| 2. Dos                                                   |
| 3. Tres                                                  |
| 4. Cuatro                                                |
------------------------------------------------------------
: 6
*** Opción Inválida ***
: 
```

### Ejemplo 5:
Warning defniido.
```
val options = Options("Menú Principal - intentar de nuevo");
options.add("Uno")
options.add("Dos")
options.add("Tres")
options.add("Cuatro")

val response = options.get(true, "Maldita sea!!")
println("Respuesta: $response");
```
Resultado:

```
------------------------------------------------------------
| Menú Principal - intentar de nuevo                       |
------------------------------------------------------------
| 1. Uno                                                   |
| 2. Dos                                                   |
| 3. Tres                                                  |
| 4. Cuatro                                                |
------------------------------------------------------------
: 6
*** Maldita sea!! ***
: 
```

