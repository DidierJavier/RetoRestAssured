#language: es
# Tags: optional

Característica: Uso de metodo post
  Como estudiante de SofkaU
  requiero hacer uso del metodo post de la api
  para generar aprendizaje

  Escenario: Autenticacion de usuario
    Dado que el estudiante cuenta con el usuario "admin" y la contrasena "password123" validas
    Cuando el estudiante emplea el metodo post para loguearse
    Entonces obtiene el token de autenticacion

