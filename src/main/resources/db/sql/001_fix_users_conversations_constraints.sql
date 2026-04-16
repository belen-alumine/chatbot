-- Saneamiento y restricciones para users/conversations.
-- Ejecutar manualmente sobre la DB ChatBot antes de volver al mapeo estricto por PK.

BEGIN;

-- 1) Eliminar duplicados de users, conservando una fila por user_id.
WITH duplicated_users AS (
    SELECT ctid,
           ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY ctid) AS rn
    FROM users
)
DELETE FROM users u
USING duplicated_users d
WHERE u.ctid = d.ctid
  AND d.rn > 1;

-- 2) Eliminar conversaciones huérfanas (si user_id no existe en users).
DELETE FROM conversations c
WHERE c.user_id IS NOT NULL
  AND NOT EXISTS (
    SELECT 1
    FROM users u
    WHERE u.user_id = c.user_id
);

-- 3) Endurecer restricciones de users.
ALTER TABLE users
    ALTER COLUMN user_id SET NOT NULL;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'users_pkey'
    ) THEN
        ALTER TABLE users
            ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
    END IF;
END $$;

-- 4) Endurecer restricciones de conversations.
ALTER TABLE conversations
    ALTER COLUMN conversation_id SET NOT NULL,
    ALTER COLUMN user_id SET NOT NULL,
    ALTER COLUMN state SET NOT NULL;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'conversations_pkey'
    ) THEN
        ALTER TABLE conversations
            ADD CONSTRAINT conversations_pkey PRIMARY KEY (conversation_id);
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_conversations_user'
    ) THEN
        ALTER TABLE conversations
            ADD CONSTRAINT fk_conversations_user
            FOREIGN KEY (user_id)
            REFERENCES users(user_id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT;
    END IF;
END $$;

COMMIT;
