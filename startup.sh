#!/usr/bin/env bash

# ============================
# MELI ORDER SERVICE STARTUP
# ============================

echo " Iniciando MELI Order Service..."

# (Opcional) Compilar el proyecto — puedes comentar esta parte si ya tienes el .jar listo
echo "🔧 Compilando aplicación..."
./mvnw clean package -DskipTests

# Configurar variables de entorno (puedes editar estas)
export DB_USER=${DB_USER:-postgres}
export DB_PASSWORD=${DB_PASSWORD:-MySecurePass123}
export DB_NAME=${DB_NAME:-meliordersdb}
export DB_HOST=${DB_HOST:-localhost}

# Verificar que el JAR exista
JAR_FILE="target/meliorderservice.jar"

if [ ! -f "$JAR_FILE" ]; then
  echo " Error: No se encontró $JAR_FILE"
  echo "Asegúrate de haber ejecutado './mvnw clean package' correctamente."
  exit 1
fi

#  Ejecutar el servicio (modifica el perfil si deseas)
echo " Ejecutando aplicación en perfil 'prod'..."
java -jar "$JAR_FILE" \
  --spring.profiles.active=prod \
  --DB_USER=$DB_USER \
  --DB_PASSWORD=$DB_PASSWORD \
  --DB_NAME=$DB_NAME \
  --DB_HOST=$DB_HOST

echo " Aplicación iniciada correctamente."
