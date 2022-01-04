# Setup and run

- base url host and port are `http://localhost:8080`.
- database and user are `postgres` as it exists on all postgres base CLI programs and has access to
  all tables.
- `src/rest_client` contains rest client manual tests.
- initial tables' records are created under `configs` folder.
- docs are accessed through `/api-docs`.
- docs swagger-ui is set to the base url.

# Thoughts

Since the problem tries to solve the manager's issue of finding which feature can the user access,
it seemed that this can't be solved with a simple table consisting of:

| email | featureName |
| ----- | ----------- |

instead, we need 2 tables to ensure the uniqueness of users and features independently, then a
merged table with unique record sets. This means a `users` table and a `features` table with an
additional table that merges records from both based on sets within the `users` entity.

users table

| email |
| ----- |

features table

| name |
| ---- |

joined table (users_features)

| user_email | feature_name |
| ---------- | ------------ |
