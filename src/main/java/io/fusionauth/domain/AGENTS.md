# AGENTS.md — src/main/java/io/fusionauth/domain

Rules for domain model classes under `io.fusionauth.domain`. These are the classes shared with client libraries and must maintain Java 8 compatibility.

## Java 8 Compatibility (critical)
- All code in `io.fusionauth.domain.*` **must be Java 8 compatible**.
- Do not use Java 9+ language features (e.g., pattern variables with `instanceof`, `var`, records, sealed classes).
- The rest of the codebase targets Java 21 — this directory is the exception.
- Build and verify client libraries before pushing a PR that touches domain objects.

## Domain Object Standards

### Copy Constructors (required)
Every domain object must provide a copy constructor.

### equals / hashCode
Use the IntelliJ default generator (Java 7+ style) for `equals` and `hashCode`. Ensure all fields are included.

### toString
Must use:
```java
return com.inversoft.json.ToString.toString(this);
```

### Field Visibility
Prefer `public` fields over private fields with getters/setters.

## Immutability and Constraints
When a field is intentionally immutable or constrained, add a short comment explaining **why** — not just that it is.

## Copy Constructor Completeness
Ensure every field is handled in:
- Copy constructor
- `equals`
- `hashCode`

Missing fields in any of these are a common source of subtle bugs.

## Client Library Impact
Changes to domain objects ripple into client libraries published via git subtrees. After modifying domain objects:
- Reflect changes in `fusionauth-client-builder`.
- Build and verify client libraries before merging.
