## Work in progress!

Client MVP Features

| Feature   | Status      |
|-----------|-------------|
| Login     | in progress |
| Signup    | in progress |
| Home      | backlog     |
| Subreddit | backlog     |

Backend MVP Features

| Endpoint               | Status |
|------------------------|--------|
| Login                  | done   |
| Register               | done   |
| Create Subreddit       | done   |
| Join Subreddit         | done   |
| Create Post            | done   |
| Create Post Attachemnt | done   |
| Vote Post              | done   |
| Private Post List      | done   |
| Public Post List       | done   |

## Target Platform

1. Android
2. iOS
3. Web (WASM) - might remove
4. Server (Ktor)

## Challenges

1. The navigation in the WASM JS target for Compose is currently inadequate, as it does not support the back and forward
   buttons of web browsers.
2. Variable-width fonts are not yet supported in the WASM JS target. Here is
   the [link to the issue](https://youtrack.jetbrains.com/issue/CMP-4635/Wasm-Variable-fonts-are-displayed-as).
3. The Compose Multiplatform preview still doesn't work consistently; it sometimes functions and other times does not,
   atleasst thats my experience using the Fleet IDE.

## Recommendation

1. Not a fan of the web target since it uses Canvas to display the UI (Might remove the web target)

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
JWT_CLAIM_USER_ID=
JWT_CLAIM_USERNAME=

# shared config
FALLBACK_ERROR_MESSAGE=
```

## Popular tools used

1. Koin - dependency injection
2. kotlinx-datetime - date and time utility
3. kotlinx-serialization - serialization and deserialization