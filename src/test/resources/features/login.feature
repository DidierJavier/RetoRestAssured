#language: es

Característica: Inicios de sesión
  Como un usuario registrado del sistema
  nececito validar que las operaciones de logueo y disponibilidad al sitio web
  para poder tener seguridad en el perfil de los usuarios

  Escenario: : login exitoso
    Dado que el usuario esta en la pagina de inicio de sesion con el correo de usuario "eve.holt@reqres.in" y la contraseña "cityslicka"
    Cuando el usuario hace una petición de inicio
    Entonces el usuario debera ver un codigo de respuesta exitoso y un token de respuesta
