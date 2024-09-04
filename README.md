## Work in progress!

## Target Platform
1. Android
2. iOS
3. Web (WASM)
4. Server (Ktor)

## Configuration
Create shared/key.properties
```
# server config
SERVER_PORT=

# database config
SUPABASE_URL=
SUPABASE_KEY=

# jwt config
JWT_USER_AUTH=
JWT_SECRET=
JWT_ISSUER=
JWT_AUDIENCE=
JWT_REALM=
JWT_CLAIM_USERNAME=

# shared config
FALLBACK_ERROR_MESSAGE=
```

## Popular tools used
1.  Koin - dependency injection
2.  kotlinx-datetime - date and time utility
3.  kotlinx-serialization - serialization and deserialization