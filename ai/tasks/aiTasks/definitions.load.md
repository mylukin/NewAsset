---
id: aiTasks.definitions.load
module: aiTasks
priority: 22
status: failing
version: 1
origin: init-auto
dependsOn: []
supersedes: []
tags:
  - inferred
testRequirements:
  unit:
    required: false
    pattern: tests/aiTasks/**/*.test.*
---
# Load AI task definitions from an ai/tasks directory (YAML/JSON) with schema validation and versioning.

## Acceptance Criteria

1. Load AI task definitions from an ai/tasks directory (YAML/JSON) with schema validation and versioning.
