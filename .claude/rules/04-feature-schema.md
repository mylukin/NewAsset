# Modular Task/Feature Storage

Agent-foreman uses a modular markdown-based storage system where each task/feature is stored in its own file.

## Directory Structure

```
ai/tasks/
├── index.json              # Lightweight index for quick lookups
├── auth/                   # Module directory
│   ├── login.md           # Task: auth.login
│   └── logout.md          # Task: auth.logout
├── chat/
│   └── message.edit.md    # Task: chat.message.edit
└── ...
```

## Index Format (`ai/tasks/index.json`)

```json
{
  "version": "2.0.0",
  "updatedAt": "2024-01-15T10:00:00Z",
  "metadata": {
    "projectGoal": "Project goal description",
    "createdAt": "2024-01-15T10:00:00Z",
    "updatedAt": "2024-01-15T10:00:00Z",
    "version": "1.0.0"
  },
  "features": {
    "auth.login": {
      "status": "passing",
      "priority": 1,
      "module": "auth",
      "description": "User can log in"
    },
    "core.project-init": {
      "status": "passing",
      "priority": 1,
      "module": "core",
      "description": "Initialize project structure",
      "filePath": "core/01-project-init.md"
    }
  }
}
```

**Index Entry Fields**:
- `status` (required): Current task status
- `priority` (required): Priority number (1 = highest)
- `module` (required): Parent module name
- `description` (required): Human-readable description
- `filePath` (optional): Explicit file path when filename doesn't follow ID convention

**Note**: By default, file paths are derived from task IDs (e.g., `auth.login` → `auth/login.md`). Use `filePath` when the actual filename differs (e.g., numbered prefixes like `01-project-init.md`).

## Task/Feature Markdown Format

Each task/feature is stored as a markdown file with YAML frontmatter:

**Priority**: Determines `agent-foreman next` selection order. Lower number = selected first.

```yaml
---
id: module.task-name
module: module-name
priority: N  # Lower number = higher priority (1 is highest)
status: failing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags:
  - tag-name
---
# Human-readable description

## Acceptance Criteria

1. First acceptance criterion
2. Second acceptance criterion
3. Third acceptance criterion
```

## Task/Feature ID Convention

Task/Feature IDs use dot notation: `module.submodule.action`

Examples:
- `auth.login`
- `chat.message.edit`
- `api.users.create`

## Acceptance Criteria Format

Write criteria as testable statements:
- "User can submit the form and see a success message"
- "API returns 201 status with created resource"
- "Error message displays when validation fails"

## Field Reference

**Required fields**: `id`, `description`, `module`, `priority`, `status`, `acceptance`, `version`, `origin`

**Auto-generated fields**: `testRequirements` (auto-generated during init with pattern `tests/{module}/**/*.test.*`)

**Optional fields**: `testRequirements` (can be overridden), `e2eTags` (Playwright tags for E2E filtering)

**Status values**: `failing` | `passing` | `blocked` | `needs_review` | `failed` | `deprecated`

**Origin values**: `init-auto` | `init-from-routes` | `init-from-tests` | `manual` | `replan`
