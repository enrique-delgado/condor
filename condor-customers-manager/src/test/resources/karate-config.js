function fn() {
  // Puerto asignado por Spring Boot en modo RANDOM_PORT
  var port = karate.properties['local.server.port'];

  console.log(`karate.properties['local.server.port'] ${port}`);
  // Si no está definido (por ejemplo, en dev), usar 8080 como fallback
  if (!port || port === 'null') {
    port = '8000';
  }

  // Perfil activo (puede venir de system property o variable de entorno)
  var profile = java.lang.System.getProperty('spring.profiles.active');
  if (!profile) {
    profile = 'test'; // valor por defecto
  }

  var config = {
    baseUrl: 'http://localhost:' + port,
    profile: profile
  };

  // Configuración específica por perfil
  if (profile === 'dev') {
    config.baseUrl = 'http://localhost:8080';
  }
  if (profile === 'prod') {
    config.baseUrl = 'http://prod-server:8080';
  }

  return config;
}
