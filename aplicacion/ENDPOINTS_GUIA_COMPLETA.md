# 📚 GUÍA COMPLETA DE ENDPOINTS - MÓDULO CLIENTES

## 📋 Tabla de Contenidos

- [Introducción](#introducción)
- [Configuración Inicial](#configuración-inicial)
- [ClienteControlador](#1-clientecontrolador)
- [DireccionClienteControlador](#2-direccionclientecontrolador)
- [TelefonoClienteControlador](#3-telefonoclientecontrolador)
- [ContactoTransaccionClienteControlador](#4-contactotransaccionclientecontrolador)
- [EmpresaControlador](#5-empresacontrolador)
- [PersonaControlador](#6-personacontrolador)
- [AccionistaControlador](#7-accionistacontrolador)
- [RepresentanteControlador](#8-representantecontrolador)
- [Códigos de Respuesta](#códigos-de-respuesta)
- [Notas Importantes](#notas-importantes)

---

## 🚀 Introducción

Esta guía documenta todos los endpoints disponibles en el módulo de clientes del sistema bancario. Todos los endpoints han sido optimizados para evitar referencias circulares y proporcionar respuestas JSON limpias.

### ✨ Características Principales:
- ✅ **Sin referencias circulares** - Respuestas JSON optimizadas
- ✅ **Paginación integrada** - Manejo eficiente de grandes datasets
- ✅ **Filtrado y ordenamiento** - Búsquedas flexibles
- ✅ **Versionado de API** - Todas las rutas usan `/v1/`
- ✅ **Manejo de errores** - Respuestas estructuradas
- ✅ **Idempotencia** - Headers de control de duplicados

---

## ⚙️ Configuración Inicial

### 🔧 Variables de Entorno en Postman:
```
base_url: http://localhost:8080
```

### 📝 Headers Globales:
```
Content-Type: application/json
Accept: application/json
```

### 🔐 Headers Opcionales:
```
X-Request-ID: req-123456  # Para idempotencia en POST/PUT
```

---

## 1. 👤 ClienteControlador

**Base URL:** `/v1/clientes`

### 📖 GET - Listar Todos los Clientes
```http
GET {{base_url}}/v1/clientes
```

**Query Parameters:**
```
pagina=0          # Número de página (0-based)
tamanio=20        # Elementos por página
ordenarPor=fechaCreacion  # Campo para ordenar
direccion=desc    # asc/desc
```

**Ejemplo de Respuesta:**
```json
{
    "content": [
        {
            "idCliente": 1,
            "tipoEntidad": "PERSONA",
            "nombre": "Juan Pérez",
            "estado": "ACTIVO",
            "numeroIdentificacion": "1234567890",
            "pais": {
                "id": "EC",
                "nombre": "Ecuador",
                "codigoTelefono": "593"
            },
            "sucursal": {
                "idSucursal": 1,
                "nombre": "Sucursal Principal"
            }
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 20
    },
    "totalElements": 150,
    "totalPages": 8
}
```

### 🔍 GET - Cliente por ID
```http
GET {{base_url}}/v1/clientes/1
```

### 🔍 GET - Cliente Completo (con relaciones)
```http
GET {{base_url}}/v1/clientes/1/completo
```
*Incluye direcciones y teléfonos activos*

### 🔍 GET - Cliente por Identificación
```http
GET {{base_url}}/v1/clientes/identificacion/1234567890
```

### 🔍 GET - Clientes por Estado
```http
GET {{base_url}}/v1/clientes/estado/ACTIVO
```
**Estados válidos:** `ACTIVO`, `INACTIVO`, `SUSPENDIDO`, `BLOQUEADO`, `PROSPECTO`

### 🔍 GET - Clientes por Sucursal
```http
GET {{base_url}}/v1/clientes/sucursal/1
```

### 🔍 GET - Buscar por Nombre
```http
GET {{base_url}}/v1/clientes/buscar?nombre=Juan
```

### 🔍 GET - Por Tipo de Entidad
```http
GET {{base_url}}/v1/clientes/tipo-entidad/PERSONA
```
**Tipos válidos:** `PERSONA`, `EMPRESA`

### 📊 GET - Estadísticas de Clientes
```http
GET {{base_url}}/v1/clientes/estadisticas
```

**Ejemplo de Respuesta:**
```json
{
    "totalClientes": 1500,
    "clientesActivos": 1200,
    "clientesInactivos": 200,
    "clientesSuspendidos": 50,
    "clientesBloqueados": 30,
    "clientesProspecto": 20,
    "porcentajeActivacion": 80.0,
    "segmentoPreferencial": 300,
    "segmentoMasivo": 800,
    "segmentoPyme": 250,
    "segmentoCorporativo": 150
}
```

### ➕ POST - Crear Cliente
```http
POST {{base_url}}/v1/clientes
Content-Type: application/json
X-Request-ID: req-{{$timestamp}}
```

**Body:**
```json
{
    "tipoEntidad": "PERSONA",
    "idEntidad": 1,
    "pais": {
        "id": "EC"
    },
    "sucursal": {
        "idSucursal": 1
    },
    "tipoIdentificacion": "CEDULA",
    "numeroIdentificacion": "1234567890",
    "tipoCliente": "NATURAL",
    "canalAfilicacion": "SUCURSAL",
    "nombre": "Juan Pérez"
}
```

### ✏️ PUT - Actualizar Cliente
```http
PUT {{base_url}}/v1/clientes/1
Content-Type: application/json
```

**Body:**
```json
{
    "nombre": "Juan Carlos Pérez",
    "tipoCliente": "PREFERENCIAL"
}
```

### 🔄 PATCH - Cambiar Estado
```http
PATCH {{base_url}}/v1/clientes/1/estado?estado=BLOQUEADO
```

### ✅ PATCH - Activar Cliente
```http
PATCH {{base_url}}/v1/clientes/1/activar
```

---

## 2. 🏠 DireccionClienteControlador

**Base URL:** `/v1/direcciones`

### 📖 GET - Listar Direcciones
```http
GET {{base_url}}/v1/direcciones?pagina=0&tamanio=20&ordenarPor=idDireccion&direccion=asc
```

### 📖 GET - Todas sin Paginación
```http
GET {{base_url}}/v1/direcciones/todas
```

### 🔍 GET - Dirección por ID
```http
GET {{base_url}}/v1/direcciones/1
```

### 🔍 GET - Direcciones por Cliente
```http
GET {{base_url}}/v1/direcciones/cliente/1
```

### 🔍 GET - Direcciones Activas por Cliente
```http
GET {{base_url}}/v1/direcciones/cliente/1/activas
```

### 🏠 GET - Dirección Principal
```http
GET {{base_url}}/v1/direcciones/cliente/1/principal
```

### 🔍 GET - Por Tipo
```http
GET {{base_url}}/v1/direcciones/tipo/DOMICILIO
```
**Tipos válidos:** `DOMICILIO`, `TRABAJO`, `CORRESPONDENCIA`, `TEMPORAL`

### 📊 GET - Estadísticas por Cliente
```http
GET {{base_url}}/v1/direcciones/cliente/1/estadisticas
```

### ➕ POST - Crear Dirección
```http
POST {{base_url}}/v1/direcciones
Content-Type: application/json
```

**Body:**
```json
{
    "cliente": {
        "idCliente": 1
    },
    "tipo": "DOMICILIO",
    "linea1": "Av. Amazonas 123",
    "linea2": "Entre Colón y Patria",
    "codigoPostal": "170515"
}
```

### ✏️ PUT - Actualizar Dirección
```http
PUT {{base_url}}/v1/direcciones/1
Content-Type: application/json
```

**Body:**
```json
{
    "linea1": "Av. Amazonas 456",
    "linea2": "Esquina",
    "codigoPostal": "170516"
}
```

### 🔄 PATCH - Cambiar Tipo
```http
PATCH {{base_url}}/v1/direcciones/1/tipo?nuevoTipo=TRABAJO
```

### 🗑️ DELETE - Eliminar Dirección
```http
DELETE {{base_url}}/v1/direcciones/1
```

---

## 3. 📞 TelefonoClienteControlador

**Base URL:** `/v1/telefonos`

### 📖 GET - Listar Teléfonos
```http
GET {{base_url}}/v1/telefonos?pagina=0&tamanio=20&ordenarPor=idTelefonoCliente&direccion=asc
```

### 🔍 GET - Teléfono por ID
```http
GET {{base_url}}/v1/telefonos/1
```

### 🔍 GET - Teléfonos por Cliente
```http
GET {{base_url}}/v1/telefonos/cliente/1
```

### 🔍 GET - Teléfonos Activos por Cliente
```http
GET {{base_url}}/v1/telefonos/cliente/1/activos
```

### 📱 GET - Teléfono Principal
```http
GET {{base_url}}/v1/telefonos/cliente/1/principal
```

### 🔍 GET - Por Tipo
```http
GET {{base_url}}/v1/telefonos/tipo/MOVIL
```
**Tipos válidos:** `MOVIL`, `FIJO`, `TRABAJO`

### 🔍 GET - Por Número
```http
GET {{base_url}}/v1/telefonos/numero/0987654321
```

### 🔍 GET - Buscar por Número Parcial
```http
GET {{base_url}}/v1/telefonos/buscar?numeroPartial=098
```

### 📊 GET - Estadísticas por Cliente
```http
GET {{base_url}}/v1/telefonos/cliente/1/estadisticas
```

### ➕ POST - Crear Teléfono
```http
POST {{base_url}}/v1/telefonos
Content-Type: application/json
```

**Body:**
```json
{
    "cliente": {
        "idCliente": 1
    },
    "tipoTelefono": "MOVIL",
    "numeroTelefono": "0987654321"
}
```

### ✏️ PUT - Actualizar Teléfono
```http
PUT {{base_url}}/v1/telefonos/1
Content-Type: application/json
```

**Body:**
```json
{
    "numeroTelefono": "0987654322"
}
```

### 🔄 PATCH - Cambiar Tipo
```http
PATCH {{base_url}}/v1/telefonos/1/tipo?nuevoTipo=FIJO
```

### 🗑️ DELETE - Eliminar Teléfono
```http
DELETE {{base_url}}/v1/telefonos/1
```

---

## 4. 📧 ContactoTransaccionClienteControlador

**Base URL:** `/v1/contactos`

### 📖 GET - Listar Contactos
```http
GET {{base_url}}/v1/contactos?pagina=0&tamanio=20
```

### 🔍 GET - Contacto por ID
```http
GET {{base_url}}/v1/contactos/1
```

### 🔍 GET - Contacto por Cliente
```http
GET {{base_url}}/v1/contactos/cliente/1
```

### ➕ POST - Crear Contacto
```http
POST {{base_url}}/v1/contactos
Content-Type: application/json
```

**Body:**
```json
{
    "cliente": {
        "idCliente": 1
    },
    "telefono": "0987654321",
    "correoElectronico": "cliente@email.com"
}
```

### ✏️ PUT - Actualizar Contacto
```http
PUT {{base_url}}/v1/contactos/1
Content-Type: application/json
```

**Body:**
```json
{
    "telefono": "0987654322",
    "correoElectronico": "nuevo@email.com"
}
```

### 🗑️ DELETE - Eliminar Contacto
```http
DELETE {{base_url}}/v1/contactos/1
```

---

## 5. 🏢 EmpresaControlador

**Base URL:** `/v1/empresas`

### 📖 GET - Listar Empresas
```http
GET {{base_url}}/v1/empresas?pagina=0&tamanio=20
```

### 🔍 GET - Empresa por ID
```http
GET {{base_url}}/v1/empresas/1
```

### 🔍 GET - Empresa por Identificación
```http
GET {{base_url}}/v1/empresas/identificacion/1791234567001
```

### ➕ POST - Crear Empresa
```http
POST {{base_url}}/v1/empresas
Content-Type: application/json
```

**Body:**
```json
{
    "tipoIdentificacion": "RUC",
    "numeroIdentificacion": "1791234567001",
    "nombreEmpresa": "Empresa ABC S.A.",
    "razonSocial": "ABC SOCIEDAD ANONIMA",
    "fechaConstitucion": "2020-01-15",
    "correoElectronico": "info@abc.com",
    "tipoCompania": "SA",
    "estado": "ACTIVO",
    "sectorEconomico": "TECNOLOGIA"
}
```

### ✏️ PUT - Actualizar Empresa
```http
PUT {{base_url}}/v1/empresas/1
Content-Type: application/json
```

**Body:**
```json
{
    "nombreEmpresa": "Empresa ABC Updated S.A.",
    "correoElectronico": "nuevoemail@abc.com"
}
```

### 🔄 PATCH - Cambiar Estado
```http
PATCH {{base_url}}/v1/empresas/1/estado?estado=INACTIVO
```

### 🗑️ DELETE - Eliminar Empresa
```http
DELETE {{base_url}}/v1/empresas/1
```

---

## 6. 👥 PersonaControlador

**Base URL:** `/v1/personas`

### 📖 GET - Listar Personas
```http
GET {{base_url}}/v1/personas?pagina=0&tamanio=20
```

### 🔍 GET - Persona por ID
```http
GET {{base_url}}/v1/personas/1
```

### 🔍 GET - Persona por Identificación
```http
GET {{base_url}}/v1/personas/identificacion/1234567890
```

### ➕ POST - Crear Persona
```http
POST {{base_url}}/v1/personas
Content-Type: application/json
```

**Body:**
```json
{
    "tipoIdentificacion": "CEDULA",
    "numeroIdentificacion": "1234567890",
    "nombres": "Juan Carlos Pérez",
    "genero": "MASCULINO",
    "fechaNacimiento": "1990-05-15",
    "nacionalidad": "ECUATORIANO",
    "estadoCivil": "SOLTERO",
    "nivelEstudio": "SUPERIOR",
    "correoElectronico": "juan@email.com",
    "estado": "ACTIVO"
}
```

### ✏️ PUT - Actualizar Persona
```http
PUT {{base_url}}/v1/personas/1
Content-Type: application/json
```

**Body:**
```json
{
    "nombres": "Juan Carlos Pérez Morales",
    "estadoCivil": "CASADO"
}
```

### 🔄 PATCH - Cambiar Estado
```http
PATCH {{base_url}}/v1/personas/1/estado?estado=INACTIVO
```

### 🗑️ DELETE - Eliminar Persona
```http
DELETE {{base_url}}/v1/personas/1
```

---

## 7. 💼 AccionistaControlador

**Base URL:** `/v1/accionistas`

### 📖 GET - Listar Accionistas
```http
GET {{base_url}}/v1/accionistas?pagina=0&tamanio=20
```

### 🔍 GET - Accionista por ID
```http
GET {{base_url}}/v1/accionistas/1
```

### 🔍 GET - Accionistas por Empresa
```http
GET {{base_url}}/v1/accionistas/empresa/1
```

### ➕ POST - Crear Accionista
```http
POST {{base_url}}/v1/accionistas
Content-Type: application/json
```

**Body:**
```json
{
    "empresa": {
        "idEmpresa": 1
    },
    "participacion": 25.50,
    "tipoEntidad": "PERSONA_NATURAL"
}
```

### ✏️ PUT - Actualizar Accionista
```http
PUT {{base_url}}/v1/accionistas/1
Content-Type: application/json
```

**Body:**
```json
{
    "participacion": 30.00
}
```

### 🗑️ DELETE - Eliminar Accionista
```http
DELETE {{base_url}}/v1/accionistas/1
```

---

## 8. 🤝 RepresentanteControlador

**Base URL:** `/v1/representantes`

### 📖 GET - Listar Representantes
```http
GET {{base_url}}/v1/representantes?pagina=0&tamanio=20
```

### 🔍 GET - Representante Específico
```http
GET {{base_url}}/v1/representantes/1/1/LEGAL
```
*{idEmpresa}/{idCliente}/{rol}*

### 🔍 GET - Por Empresa
```http
GET {{base_url}}/v1/representantes/empresa/1
```

### 🔍 GET - Por Cliente
```http
GET {{base_url}}/v1/representantes/cliente/1
```

### ➕ POST - Crear Representante
```http
POST {{base_url}}/v1/representantes
Content-Type: application/json
```

**Body:**
```json
{
    "id": {
        "idEmpresa": 1,
        "idCliente": 1,
        "rol": "LEGAL"
    },
    "empresa": {
        "idEmpresa": 1
    },
    "cliente": {
        "idCliente": 1
    },
    "fechaAsignacion": "2024-01-15",
    "estado": "ACTIVO"
}
```

### ✏️ PUT - Actualizar Representante
```http
PUT {{base_url}}/v1/representantes/1/1/LEGAL
Content-Type: application/json
```

**Body:**
```json
{
    "fechaAsignacion": "2024-02-01"
}
```

### 🔄 PATCH - Cambiar Estado
```http
PATCH {{base_url}}/v1/representantes/1/1/LEGAL/estado?estado=INACTIVO
```

### 🗑️ DELETE - Eliminar Representante
```http
DELETE {{base_url}}/v1/representantes/1/1/LEGAL
```

---

## 📊 Códigos de Respuesta

| Código | Estado | Descripción |
|--------|--------|-------------|
| **200** | OK | Consulta exitosa |
| **201** | Created | Recurso creado exitosamente |
| **204** | No Content | Eliminación exitosa |
| **400** | Bad Request | Datos inválidos o mal formados |
| **404** | Not Found | Recurso no encontrado |
| **409** | Conflict | Conflicto (ej: recurso ya existe) |
| **500** | Internal Server Error | Error interno del servidor |

---

## 📝 Notas Importantes

### 🔧 Funcionalidades Implementadas:
- ✅ **Referencias circulares eliminadas** - Sin bucles infinitos en JSON
- ✅ **Paginación completa** - Todos los listados soportan paginación
- ✅ **Filtrado avanzado** - Búsquedas por múltiples criterios
- ✅ **Ordenamiento flexible** - Ascendente y descendente
- ✅ **Validaciones de negocio** - Reglas bancarias implementadas
- ✅ **Eliminación lógica** - Preservación del historial
- ✅ **Estadísticas** - Dashboards ejecutivos

### 🎯 Casos de Uso Comunes:

**Crear un cliente completo:**
1. POST `/v1/personas` (si es persona natural)
2. POST `/v1/clientes`
3. POST `/v1/direcciones`
4. POST `/v1/telefonos`
5. POST `/v1/contactos`

**Consultar información completa:**
1. GET `/v1/clientes/{id}/completo`

**Gestión de estados:**
1. PATCH `/v1/clientes/{id}/estado`
2. PATCH `/v1/clientes/{id}/activar`

### ⚠️ Consideraciones:
- Los endpoints están optimizados para evitar cargar relaciones innecesarias
- Usar paginación para datasets grandes
- El header `X-Request-ID` es opcional pero recomendado para operaciones críticas
- Todas las fechas se manejan en formato ISO 8601 (`YYYY-MM-DD`)

### 🔍 Ejemplos de Búsquedas Avanzadas:
```http
# Clientes activos ordenados por nombre
GET /v1/clientes/estado/ACTIVO?ordenarPor=nombre&direccion=asc

# Direcciones de un cliente específico
GET /v1/direcciones/cliente/1/activas

# Búsqueda de clientes por nombre
GET /v1/clientes/buscar?nombre=Juan&pagina=0&tamanio=10

# Estadísticas generales
GET /v1/clientes/estadisticas
```

---

## 🎉 ¡Listo para usar!

Todos los endpoints han sido probados y optimizados. Las respuestas son limpias, rápidas y sin problemas de serialización. 

**¡Happy coding!** 🚀 